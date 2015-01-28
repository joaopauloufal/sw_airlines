package sistema_esp.model;

public class ArvoreBinaria {
	
	private No raiz;
	private boolean valorLogicoEsq;
	private boolean valorLogicoDir;
	private boolean valorLogicoRaiz;
	
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
		if (no.getItem().getSimbolo().length() < item.getVariavel().getValor().length()){
			if (no.esquerda != null){
				inserirElemento(no.esquerda, item);
			} else {
				no.esquerda = new No(item);
			}
		} else if (item.getVariavel().getValor().length() > no.getItem().getSimbolo().length()){
			if (no.direita != null){
				inserirElemento(no.direita, item);
			} else {
				no.direita = new No(item);
			}
			
		}
		
		
	}
	
	public void inserirEsquerda(No no, Premissa item){
		if (no.esquerda != null){
			inserirEsquerda(no.esquerda, item);
		} else {
			no.esquerda = new No(item);
		}
	}
	
	public void inserirDireita(No no, Premissa item){
		if (no.direita != null){
			inserirDireita(no.esquerda, item);
		} else {
			no.direita = new No(item);
		}
	}
	
	public void percorrerEmOrdem(No no){
		if (no != null){
			System.out.println(no.getItem().getVariavel().getValor());
			System.out.println(no.getItem().getSimbolo());
			percorrerEmOrdem(no.esquerda);
			percorrerEmOrdem(no.direita);
		}
	}
	
	public boolean avaliarOperadoresLogicos(No no){
		if (no.esquerda != null && no.direita != null){
			valorLogicoEsq = no.esquerda.getItem().getValorLogico();
			valorLogicoDir = no.direita.getItem().getValorLogico();
			if (no.getItem().getSimbolo().equals("^")){
				return valorLogicoEsq && valorLogicoDir;
			}
			
			if (no.getItem().getSimbolo().equals("|")){
				return valorLogicoEsq || valorLogicoDir;
			}
			
			
		}
		return false;
	}
	
	

}
