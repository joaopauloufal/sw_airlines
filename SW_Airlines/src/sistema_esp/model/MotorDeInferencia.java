package sistema_esp.model;

import java.util.Scanner;

public class MotorDeInferencia {
	
	private MemoriaDeFatos memoriaDeFatos;
	private BaseDeRegras baseDeRegras;
	private boolean valorLogicoFatoAnterior;
	private boolean valorLogicoFatoAtual;
	private boolean valorLogicoFinal;
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
			boolean temp = buscarFatoNaMemoriaDeFatos(fato).getValorLogico();
			fato.setValorLogico(temp);
			return temp;
		} else {
			if (temConclusaoNaBaseDeRegras(fato)){
				Regra r = buscarConclusaoNaBaseDeRegras(fato);
				if (r.getPremissas().size() > 1){
					for (int i = 0; i < r.getPremissas().size(); i++){
						if (r.getPremissas().get(i).getSimbolo().equals("^")){
							valorLogicoFatoAnterior = inferir(r.getPremissas().get(i));
							i++;
							valorLogicoFatoAtual = inferir(r.getPremissas().get(i));
							valorLogicoFinal = valorLogicoFatoAnterior && valorLogicoFatoAtual;
							fato.setValorLogico(valorLogicoFinal);
							if (!temFatoNaMemoriaDeFatos(r.getPremissas().get(i))){
								this.memoriaDeFatos.adicionarFato(r.getPremissas().get(i));
							}
						}
						if (r.getPremissas().get(i).getSimbolo().equals("|")){
							valorLogicoFatoAnterior = inferir(r.getPremissas().get(i));
							i++;
							valorLogicoFatoAtual = inferir(r.getPremissas().get(i));
							valorLogicoFinal = valorLogicoFatoAnterior || valorLogicoFatoAtual;
							fato.setValorLogico(valorLogicoFatoAtual);
							if (!temFatoNaMemoriaDeFatos(r.getPremissas().get(i))){
								this.memoriaDeFatos.adicionarFato(r.getPremissas().get(i));
							}
						}
						if (r.getPremissas().get(i).getSimbolo().equals("")){
							boolean temp = inferir(r.getPremissas().get(i));
							this.setValorLogicoFatoAtual(temp);
							if (!temFatoNaMemoriaDeFatos(r.getPremissas().get(i))){
								this.memoriaDeFatos.adicionarFato(r.getPremissas().get(i));
							}
						}
					}
				} else {
					boolean temp = inferir(r.getPremissas().get(0));
					this.setValorLogicoFatoAtual(temp);
					if (!temFatoNaMemoriaDeFatos(r.getPremissas().get(0))){
						this.memoriaDeFatos.adicionarFato(r.getPremissas().get(0));
					}
				}
		} else {
			boolean temp = perguntarNaInterface(fato);
			fato.setValorLogico(temp);
			if (!temFatoNaMemoriaDeFatos(fato)){
				this.memoriaDeFatos.adicionarFato(fato);
			}
		}
	}
	return valorLogicoFinal;
				
		
	}
	
	
}
