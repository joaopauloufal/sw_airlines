package sistema_esp.model;

import sistema_esp.model.Variavel;

public class Premissa {
	
	private Variavel variavel;
	private String simbolo;
	private boolean valorLogico;
	private double fatorCerteza;
	private boolean estaNegada;
	private String simboloNegacao;
	
	public Premissa() {
		
	}
	
	public Premissa(Variavel variavel) {
		this.variavel = variavel;
		this.estaNegada = false;
		this.simboloNegacao = "";
	}
	
	public String getSimboloNegacao() {
		return simboloNegacao;
	}

	public void setSimboloNegacao(String simboloNegacao) {
		this.simboloNegacao = simboloNegacao;
	}

	public boolean getEstaNegada() {
		return estaNegada;
	}

	public void setEstaNegada(boolean estaNegada) {
		this.estaNegada = estaNegada;
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
			if (estaNegada == true){
				return "~#"+variavel +"#"+ simbolo;
			} else {
				return variavel +"#"+ simbolo;
			}
			
		}
		if (estaNegada == true){
			return "~#"+variavel.toString();
		}
		return variavel.toString();
		
		
		
	}

}
