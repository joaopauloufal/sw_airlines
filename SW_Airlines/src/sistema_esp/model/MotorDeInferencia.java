package sistema_esp.model;

import java.util.Scanner;

public class MotorDeInferencia {
	
	private MemoriaDeFatos memoriaDeFatos;
	private BaseDeRegras baseDeRegras;
	private ArvoreBinaria arvore;
	
	public MotorDeInferencia(MemoriaDeFatos memoriaDeFatos, BaseDeRegras baseDeRegras) {
		this.memoriaDeFatos = memoriaDeFatos;
		this.baseDeRegras = baseDeRegras;
		
	}
	
	public MotorDeInferencia() {
		
	}

	public BaseDeRegras getBaseDeRegras() {
		return baseDeRegras;
	}

	public void setBaseDeRegras(BaseDeRegras baseDeRegras) {
		this.baseDeRegras = baseDeRegras;
	}

	public MemoriaDeFatos getMemoriaDeFatos() {
		return memoriaDeFatos;
	}

	public void setMemoriaDeFatos(MemoriaDeFatos memoriaDeFatos) {
		this.memoriaDeFatos = memoriaDeFatos;
	}
	
	private Premissa buscarFatoNaMemoriaDeFatos(Premissa fato){
		for (Premissa fatoTemp : memoriaDeFatos.getListaDeFatos()){
			if (fato.getVariavel().getValor().equals(fatoTemp.getVariavel().getValor())){
				return fato;
			}
		}
		return null;
	}
	
	private boolean temFatoNaMemoriaDeFatos(Premissa fato){
		for (Premissa fatoTemp : memoriaDeFatos.getListaDeFatos()){
			if (fato.getVariavel().getValor().equals(fatoTemp.getVariavel().getValor())){
				return true;
			}
		}
		return false;
	}
	
	
	private boolean temConclusaoNaBaseDeRegras(Premissa conclusao){
		for (int i = 0; i < baseDeRegras.getRegras().size(); i++){
			if (conclusao.getVariavel().getValor().equals(baseDeRegras.getRegras().get(i).getConclusao().getVariavel().getValor())){
	//			System.out.println(baseDeRegras.getRegras().get(i).getConclusao().getVariavel().getValor());
	//			System.out.println(conclusao.getVariavel().getValor());
				return true;
			}
		}
		return false;
	}
	
	private Regra buscarConclusaoNaBaseDeRegras(Premissa conclusao){
		for (int i = 0; i < baseDeRegras.getRegras().size(); i++){
			if (conclusao.getVariavel().getValor().equals(baseDeRegras.getRegras().get(i).getConclusao().getVariavel().getValor())){
				return baseDeRegras.getRegras().get(i);
			}
		}
		return null;
	}
	
	private boolean perguntarNaInterface(Premissa p){
		boolean valorLogico = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual é o valor de " + p.getVariavel() + " ?");
		valorLogico = sc.nextBoolean();
		return valorLogico;
	}
	
	public boolean inferir(Premissa fato){		
		if (temFatoNaMemoriaDeFatos(fato)){
			return buscarFatoNaMemoriaDeFatos(fato).getValorLogico();
		} else {
			if (temConclusaoNaBaseDeRegras(fato)){
				Regra r = buscarConclusaoNaBaseDeRegras(fato);
				System.out.println(r.getPremissas());
				if (r.getPremissas().size() > 1){
					this.arvore = new ArvoreBinaria(fato);
					for (int i = 0; i < r.getPremissas().size(); i++){
						if (i % 2 == 0){
							if (temConclusaoNaBaseDeRegras(r.getPremissas().get(i))){
								this.arvore.inserirEsquerda(this.arvore.getRaiz(), r.getPremissas().get(i));
							} else {
								boolean valorLogicoFatoAtual = perguntarNaInterface(r.getPremissas().get(i));
								r.getPremissas().get(i).setValorLogico(valorLogicoFatoAtual);
								this.arvore.inserirEsquerda(this.arvore.getRaiz(), r.getPremissas().get(i));
								this.memoriaDeFatos.adicionarFato(r.getPremissas().get(i));
			
							}
							
						} else {
							if (temConclusaoNaBaseDeRegras(r.getPremissas().get(i))){
								this.arvore.inserirDireita(this.arvore.getRaiz(), r.getPremissas().get(i));
							} else {
								boolean valorLogicoFatoAtual = perguntarNaInterface(r.getPremissas().get(i));
								r.getPremissas().get(i).setValorLogico(valorLogicoFatoAtual);
								this.arvore.inserirDireita(this.arvore.getRaiz(), r.getPremissas().get(i));
								this.memoriaDeFatos.adicionarFato(r.getPremissas().get(i));
							}
							
						}
						
					
						
					}
					boolean valorLogicoFatoAtual = this.arvore.avaliarOperadoresLogicos(this.arvore.getRaiz());
					fato.setValorLogico(valorLogicoFatoAtual);
					this.memoriaDeFatos.adicionarFato(fato);
					return valorLogicoFatoAtual;
				} else {
					boolean valorLogicoFatoAtual = inferir(r.getPremissas().get(0)); // Premissa obtida.
					fato.setValorLogico(valorLogicoFatoAtual);
					this.memoriaDeFatos.adicionarFato(fato);
					return valorLogicoFatoAtual;
				}
			} else {
				boolean valorLogicoFatoAtual = perguntarNaInterface(fato);
				fato.setValorLogico(valorLogicoFatoAtual);
				this.memoriaDeFatos.adicionarFato(fato);
				return valorLogicoFatoAtual;
			}
		}
		
		
	}
	
	

}
