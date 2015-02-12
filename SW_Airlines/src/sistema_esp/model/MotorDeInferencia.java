package sistema_esp.model;

import sistema_esp.controller.TelaPerguntaController;
import sistema_esp.view.TelaPergunta;

public class MotorDeInferencia implements Interativo{
	
	private MemoriaDeFatos memoriaDeFatos;
	private BaseDeRegras baseDeRegras;
	private boolean valorLogicoFatoAnterior;
	private boolean valorLogicoFatoAtual;
	private boolean valorLogicoFinal;
	private float fatorCertezaFatoAnterior;
	private float fatorDeCertezaFatoAtual;
	private float fatorDeConfiancaTotal;
	
	public MotorDeInferencia(MemoriaDeFatos memoriaDeFatos, BaseDeRegras baseDeRegras) {
		this.memoriaDeFatos = memoriaDeFatos;
		this.baseDeRegras = baseDeRegras;
		
	}
	
	public MotorDeInferencia() {
		
	}
	

	public float getFatorDeConfiancaTotal() {
		return fatorDeConfiancaTotal;
	}

	public void setFatorDeConfiancaTotal(float fatorDeConfiancaTotal) {
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
				return fatoTemp;
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
	
	public boolean perguntarNaInterface(Premissa p){
		
		TelaPergunta telaP = new TelaPergunta(p);
		
		TelaPerguntaController telaController = new TelaPerguntaController(telaP);
		telaController.getView();
		
		if (telaP.isCancelaConsulta()){			
			throw new NullPointerException("Consulta Cancelada.");
			
		}		
		p.setFatorCerteza(telaP.getFatorDeCertezaFato());	
		boolean valorLogico = telaP.isValorLogicoFato();
		return valorLogico;
	}
	
	
	
	public boolean inferir(Premissa fato){
		
		if (temFatoNaMemoriaDeFatos(fato)){
			boolean temp = buscarFatoNaMemoriaDeFatos(fato).getValorLogico();
			if (fato.getEstaNegada()){
				fato.setValorLogico(!temp);
				return !temp;
			} else {
				fato.setValorLogico(temp);
				return temp;
			}
			
		} else {
			if (temConclusaoNaBaseDeRegras(fato)){
				Regra r = buscarConclusaoNaBaseDeRegras(fato);
				if (r.getPremissas().size() > 1){
					for (int i = 0; i < r.getPremissas().size(); i++){
						if (r.getPremissas().get(i).getSimbolo().equals("^")){
							if (i > 0){
								valorLogicoFatoAnterior = valorLogicoFinal;
								fatorCertezaFatoAnterior = fatorDeConfiancaTotal;
								// Revisar isso depois.
								if (valorLogicoFatoAnterior == false && fatorCertezaFatoAnterior > 0.5){
									valorLogicoFinal = false;
									fato.setValorLogico(valorLogicoFinal);
									fato.setFatorCerteza(fatorCertezaFatoAnterior);
									this.memoriaDeFatos.adicionarFato(fato);
									return valorLogicoFinal;
								}
							} else {
								valorLogicoFatoAnterior = inferir(r.getPremissas().get(i));
								fatorCertezaFatoAnterior = r.getPremissas().get(i).getFatorCerteza()/100;
								// Revisar isso depois.
								if (valorLogicoFatoAnterior == false && fatorCertezaFatoAnterior > 0.5 && r.getPremissas().size() == 2){
									valorLogicoFinal = false;
									fato.setValorLogico(valorLogicoFinal);
									fato.setFatorCerteza(fatorCertezaFatoAnterior);
									this.memoriaDeFatos.adicionarFato(fato);
									return valorLogicoFinal;
								}
								
							}
							
							
							i++;
							valorLogicoFatoAtual = inferir(r.getPremissas().get(i));
							fatorDeCertezaFatoAtual = r.getPremissas().get(i).getFatorCerteza()/100;
							valorLogicoFinal = valorLogicoFatoAnterior && valorLogicoFatoAtual;
							if (fatorCertezaFatoAnterior < 1 || fatorDeCertezaFatoAtual < 1){
								fatorDeConfiancaTotal = fatorCertezaFatoAnterior * fatorDeCertezaFatoAtual * fato.getFatorCerteza()/100;
							} else {
								fatorDeConfiancaTotal = fato.getFatorCerteza()/100;
							}
							
							r.getPremissas().get(i).setValorLogico(valorLogicoFatoAtual);
							if (!temFatoNaMemoriaDeFatos(r.getPremissas().get(i))){
								this.memoriaDeFatos.adicionarFato(r.getPremissas().get(i));
							}
							
							
						}
						if (r.getPremissas().get(i).getSimbolo().equals("|")){
							if (i > 0){
								valorLogicoFatoAnterior = valorLogicoFinal;
								fatorCertezaFatoAnterior = fatorDeConfiancaTotal;
								// Revisar isso depois.
								if (valorLogicoFatoAnterior == true && fatorCertezaFatoAnterior > 0.5){
									valorLogicoFinal = true;
									fato.setValorLogico(valorLogicoFinal);
									fato.setFatorCerteza(fatorCertezaFatoAnterior);
									this.memoriaDeFatos.adicionarFato(fato);
									return valorLogicoFinal;
								}
							} else {
								valorLogicoFatoAnterior = inferir(r.getPremissas().get(i));
								fatorCertezaFatoAnterior = r.getPremissas().get(i).getFatorCerteza()/100;
								// Revisar isso depois.
								if (valorLogicoFatoAnterior == true && fatorCertezaFatoAnterior > 0.5){
									valorLogicoFinal = true;
									fato.setValorLogico(valorLogicoFinal);
									fato.setFatorCerteza(fatorCertezaFatoAnterior);
									this.memoriaDeFatos.adicionarFato(fato);
									return valorLogicoFinal;
								}
								
							}
							
							i++;
							valorLogicoFatoAtual = inferir(r.getPremissas().get(i));
							fatorDeCertezaFatoAtual = r.getPremissas().get(i).getFatorCerteza()/100;
							valorLogicoFinal = valorLogicoFatoAnterior || valorLogicoFatoAtual;
							
							if (fatorCertezaFatoAnterior < 1 || fatorDeCertezaFatoAtual < 1){
								if (!valorLogicoFatoAnterior){
									fatorDeConfiancaTotal = (fatorCertezaFatoAnterior + fatorDeCertezaFatoAtual - fatorCertezaFatoAnterior * fatorDeCertezaFatoAtual);								

								} else {
									fatorDeConfiancaTotal = fatorCertezaFatoAnterior * fato.getFatorCerteza()/100;
								}
								
							} else {
								fatorDeConfiancaTotal = fato.getFatorCerteza()/100;
							}
							
							r.getPremissas().get(i).setValorLogico(valorLogicoFatoAtual);
							if (!temFatoNaMemoriaDeFatos(r.getPremissas().get(i))){
								this.memoriaDeFatos.adicionarFato(r.getPremissas().get(i));
							}
							
							
						}
						if (r.getPremissas().get(i).getSimbolo().equals("")){
							valorLogicoFatoAtual = inferir(r.getPremissas().get(i));
							r.getPremissas().get(i).setValorLogico(valorLogicoFatoAtual);
							if (!temFatoNaMemoriaDeFatos(r.getPremissas().get(i))){
								this.memoriaDeFatos.adicionarFato(r.getPremissas().get(i));
							}
						}
					}
					
				} else {			
					boolean temp = inferir(r.getPremissas().get(0));
					r.getPremissas().get(0).setValorLogico(temp);
					if (!temFatoNaMemoriaDeFatos(r.getPremissas().get(0))){
						this.memoriaDeFatos.adicionarFato(r.getPremissas().get(0));
					}
			
				}
			} else {
				boolean temp = perguntarNaInterface(fato);
				if (fato.getEstaNegada()){
					fato.setValorLogico(!temp);
					if (!temFatoNaMemoriaDeFatos(fato)){
						this.memoriaDeFatos.adicionarFato(fato);
					}
					return !temp;
				} else {
					fato.setValorLogico(temp);
					if (!temFatoNaMemoriaDeFatos(fato)){
						this.memoriaDeFatos.adicionarFato(fato);
					}
					return temp;
				}				
				
			}
			if (fatorDeConfiancaTotal < fato.getFatorCerteza()/100 && fatorDeConfiancaTotal < 0.5 && valorLogicoFinal == true){
				fato.setValorLogico(!valorLogicoFinal);
				if (!temFatoNaMemoriaDeFatos(fato)){
					fato.setFatorCerteza(fatorDeConfiancaTotal);
					this.memoriaDeFatos.adicionarFato(fato);
					return !valorLogicoFinal;
				} 
			
			} else {
				fato.setValorLogico(valorLogicoFinal);
				fato.setFatorCerteza(fatorDeConfiancaTotal);
				if (!temFatoNaMemoriaDeFatos(fato)){
					this.memoriaDeFatos.adicionarFato(fato);
					
				} 
			}
			return valorLogicoFinal;
		}
		
	}
	
	
	
}
