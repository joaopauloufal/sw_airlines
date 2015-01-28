package sistema_esp.model;

import java.util.ArrayList;

public class MotorDeInferencia {
	
	private MemoriaDeFatos memoriaDeFatos;
	private ArrayList<Regra> baseDeRegras;
	private ArvoreBinaria arvore;
	
	public MotorDeInferencia(MemoriaDeFatos memoriaDeFatos, Premissa premissa) {
		this.memoriaDeFatos = memoriaDeFatos;
		this.baseDeRegras = new ArrayList<Regra>();
		this.arvore = new ArvoreBinaria(premissa);
		
	}
	
	public MotorDeInferencia() {
		
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
	

	
	private boolean temConclusaoNaBaseDeRegras(Premissa conclusao){
		for (int i = 0; i < baseDeRegras.size(); i++){
			if (conclusao.getVariavel().getValor().equals(baseDeRegras.get(i).getConclusao().getVariavel().getValor())){
				return true;
			}
		}
		return false;
	}
	
	private Regra retornaConclusaoNaBaseDeRegras(Regra regra){
		for (int i = 0; i < baseDeRegras.size(); i++){
			if (regra.getConclusao().getVariavel().getValor().equals(baseDeRegras.get(i).getConclusao().getVariavel().getValor())){
				return baseDeRegras.get(i);
			}
		}
		return null;
	}
	
	public void inferir(Regra regra){
		
		if (temFatoNaMemoriaDeFatos((regra.getConclusao()))){
			regra.getConclusao().setValorLogico(buscarFatoNaMemoriaDeFatos(regra.getConclusao()).getValorLogico());
		} else {
			if (temConclusaoNaBaseDeRegras(regra.getConclusao())){
				inferir(retornaConclusaoNaBaseDeRegras(regra));
				regra.getConclusao().setValorLogico(inferirRegra(regra));
			}
		}
		
	}
	
	public boolean inferirRegra(Regra regra){
		for (int i = 0; i < regra.getPremissas().size(); i++){
			if (i % 2 != 0){
				this.arvore.inserirEsquerda(this.arvore.getRaiz(), regra.getPremissas().get(i));
			} else {
				this.arvore.inserirDireita(this.arvore.getRaiz(), regra.getPremissas().get(i));
			}
		}
		return this.arvore.avaliarOperadoresLogicos(this.arvore.getRaiz());
	}
	
	

}
