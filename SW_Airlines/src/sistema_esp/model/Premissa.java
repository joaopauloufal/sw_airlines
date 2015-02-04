package sistema_esp.model;

import sistema_esp.model.Variavel;

public class Premissa {
	
	private Variavel variavel;
	private String simbolo;
	private boolean valorLogico;
	private double fatorCerteza;
	
	public Premissa() {
		
	}
	
	public Premissa(Variavel variavel) {
		this.variavel = variavel;
		
	}
	
	public double getFatorCerteza() {
		return fatorCerteza;
	}

	public void setFatorCerteza(double fatorCerteza) {
		this.fatorCerteza = fatorCerteza;
	}

	public boolean getValorLogico() {
		return valorLogico;
	}

	public void setValorLogico(boolean valorLogico) {
		this.valorLogico = valorLogico;
	}

	public Variavel getVariavel() {
		return variavel;
	}
	public void setVariavel(Variavel variavel) {
		this.variavel = variavel;
	}
	public String getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}	

	@Override
	public String toString() {
		if (this.simbolo != null){
			return variavel +"#"+ simbolo;
		}
		
		return variavel.toString();
		
		
		
	}

}
