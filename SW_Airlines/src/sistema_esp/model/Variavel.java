package sistema_esp.model;

public class Variavel {
	
	private String valor;
	
	public Variavel() {
		
	}

	public Variavel(String valor){
		this.valor = valor;
	}	
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor;
	}

}
