package sistema_esp.model;

public class ArvoreBinaria {
	
	private No raiz;
	private boolean valorLogicoEsq;
	private boolean valorLogicoDir;
	
	public ArvoreBinaria(Premissa item) {
		this.raiz = new No(item);
	}
	
	public ArvoreBinaria() {
		this.raiz = new No();
	}



	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}
	
	public boolean estaVazia(){
		return this.raiz == null;
	}
	
	public void inserirElemento(No no, Premissa item){
		if (item.getVariavel().getValor().length() < no.getItem().getVariavel().getValor().length()){
			if (no.esquerda != null){
				inserirElemento(no.esquerda, item);
			} else {
				no.esquerda = new No(item);
			}
		} else if (item.getVariavel().getValor().length() > no.getItem().getVariavel().getValor().length()){
			if (no.direita != null){
				inserirElemento(no.direita, item);
			} else {
				no.direita = new No(item);
			}
			
		} 
	}
	
	public void percorrerEmOrdem(No no){
		if (no != null){
			percorrerEmOrdem(no.esquerda);
			System.out.println(no.getItem().getVariavel().getValor());
			System.out.println(no.getItem().getSimbolo());
			percorrerEmOrdem(no.direita);
		}
	}
	
	public boolean inferir(No no){
		if (no != null){
			if (no.getItem().getSimbolo().equals("^")){
				inferir(no.esquerda);
				valorLogicoEsq = no.getItem().getValorLogico();				
			} else {
				inferir(no.direita);
				valorLogicoDir = no.getItem().getValorLogico();
			}
			
			
		
			
		}
		
		return valorLogicoEsq && valorLogicoDir;
	}
	
	

}
