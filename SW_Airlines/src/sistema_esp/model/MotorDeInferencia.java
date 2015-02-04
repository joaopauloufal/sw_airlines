package sistema_esp.model;

import java.util.Scanner;

public class MotorDeInferencia {
	
	private MemoriaDeFatos memoriaDeFatos;
	private BaseDeRegras baseDeRegras;
	private boolean valorLogicoFatoAtual;
	private double fatorDeConfiancaTotal;
	
	public MotorDeInferencia(MemoriaDeFatos memoriaDeFatos, BaseDeRegras baseDeRegras) {
		this.memoriaDeFatos = memoriaDeFatos;
		this.baseDeRegras = baseDeRegras;
		
	}
	
	public MotorDeInferencia() {
		
	}
	

	public double getFatorDeConfiancaTotal() {
		return fatorDeConfiancaTotal;
	}

	public void setFatorDeConfiancaTotal(double fatorDeConfiancaTotal) {
		this.fatorDeConfiancaTotal = fatorDeConfiancaTotal;
	}

	public boolean isValorLogicoFatoAtual() {
		return valorLogicoFatoAtual;
	}

	public void setValorLogicoFatoAtual(boolean valorLogicoFatoAtual) {
		this.valorLogicoFatoAtual = valorLogicoFatoAtual;
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
		boolean valorLogico = p.getValorLogico();		
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual é o valor de " + p.getVariavel() + "? ");
		valorLogico = sc.nextBoolean();
		return valorLogico;
	}
	
	public boolean inferir(Premissa fato){
		if (temFatoNaMemoriaDeFatos(fato)){
			valorLogicoFatoAtual = buscarFatoNaMemoriaDeFatos(fato).getValorLogico();
			fato.setValorLogico(valorLogicoFatoAtual);
			return valorLogicoFatoAtual;
		} else {
			if (temConclusaoNaBaseDeRegras(fato)){
				Regra r = buscarConclusaoNaBaseDeRegras(fato);
				
				if (r.getPremissas().size() > 1){
					for (int i = 0; i < r.getPremissas().size(); i++){
						if (r.getPremissas().get(i).getSimbolo().equals("^")){
							valorLogicoFatoAtual = inferir(r.getPremissas().get(i)) && r.getPremissas().get(i).getValorLogico();
							// TODO Estabelecer um grau de confiança.
							
							fato.setValorLogico(valorLogicoFatoAtual);
							if (!temFatoNaMemoriaDeFatos(fato)){
								this.memoriaDeFatos.adicionarFato(fato);
							}
							
							
						}
						if (r.getPremissas().get(i).getSimbolo().equals("|")){
							valorLogicoFatoAtual = inferir(r.getPremissas().get(i)) || r.getPremissas().get(i).getValorLogico();
							fato.setValorLogico(valorLogicoFatoAtual);
							if (!temFatoNaMemoriaDeFatos(fato)){
								this.memoriaDeFatos.adicionarFato(fato);
							}
							
						}
						if (r.getPremissas().get(i).getSimbolo().equals("")){
							valorLogicoFatoAtual = inferir(r.getPremissas().get(i));
							this.setValorLogicoFatoAtual(valorLogicoFatoAtual);
							if (!temFatoNaMemoriaDeFatos(fato)){
								this.memoriaDeFatos.adicionarFato(fato);
							}
							
						}
						
												
					}
					
				} else {
					valorLogicoFatoAtual = inferir(r.getPremissas().get(0));
					this.setValorLogicoFatoAtual(valorLogicoFatoAtual);
					if (!temFatoNaMemoriaDeFatos(fato)){
						this.memoriaDeFatos.adicionarFato(fato);
					}
					
				}
				
				
			} else {
				valorLogicoFatoAtual = perguntarNaInterface(fato);
				fato.setValorLogico(valorLogicoFatoAtual);
				if (!temFatoNaMemoriaDeFatos(fato)){
					this.memoriaDeFatos.adicionarFato(fato);
				}
				
			}
			
		}
		return valorLogicoFatoAtual;
		
				
		
	}
	
	

}
