package sistema_esp.model;

import java.util.ArrayList;

public class MotorDeInferencia {
	
	private MemoriaDeFatos memoriaDeFatos;
	private ArrayList<Regra> baseDeRegras;
	
	public MotorDeInferencia(MemoriaDeFatos memoriaDeFatos) {
		this.memoriaDeFatos = memoriaDeFatos;
		this.baseDeRegras = new ArrayList<Regra>();
	}	

	public ArrayList<Regra> getBaseDeRegras() {
		return baseDeRegras;
	}


	public void setBaseDeRegras(ArrayList<Regra> baseDeRegras) {
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
	
	private boolean avaliarOperadoresLogicos(Premissa fato, Premissa fato2){
		if (fato.getSimbolo().equals("^")){
			return fato.getValorLogico() && fato2.getValorLogico();
			
		}
		
		if (fato.getSimbolo().equals("|")){
			return fato.getValorLogico() || fato2.getValorLogico();
			
		}
		
		return false;
		
	}
	
	private boolean temConclusaoNaBaseDeRegras(Premissa conclusao){
		for (int i = 0; i < baseDeRegras.size(); i++){
			if (conclusao.getVariavel().getValor().equals(baseDeRegras.get(i).getConclusao().getVariavel().getValor())){
				return true;
			}
		}
		return false;
	}
	
	private Premissa retornaPremissaNaBaseDeRegras(Premissa conclusao){
		for (int i = 0; i < baseDeRegras.size(); i++){
			if (conclusao.getVariavel().getValor().equals(baseDeRegras.get(i).getConclusao().getVariavel().getValor())){
				return baseDeRegras.get(i).getPremissas().get(i);
			}
		}
		return null;
	}
	
	public void inferir(Regra regra){
		if (temFatoNaMemoriaDeFatos(regra.getConclusao())){
			buscarFatoNaMemoriaDeFatos(regra.getConclusao());
		} else {
			
		}
		
	}
	
	

}
