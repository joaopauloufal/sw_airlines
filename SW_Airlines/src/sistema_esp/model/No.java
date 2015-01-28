package sistema_esp.model;

public class No {
	
	public Premissa item;
	public No esquerda;
	public No direita;
	
	public No(){
		
	}
	
	public No(Premissa item) {
		this.item = item;
		this.esquerda = null;
		this.direita = null;
		
	}

	public Premissa getItem() {
		return item;
	}

	public void setItem(Premissa item) {
		this.item = item;
	}

	public No getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}

	public No getDireita() {
		return direita;
	}

	public void setDireita(No direita) {
		this.direita = direita;
	}
	
	

}
