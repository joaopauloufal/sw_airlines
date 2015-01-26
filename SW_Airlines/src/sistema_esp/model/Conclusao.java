package sistema_esp.model;

import sistema_esp.model.Variavel;

public class Conclusao extends Premissa {
	
	private double fatorDeCerteza;

	public Conclusao() {
		
	}
	
	public Conclusao(double fatorDeCerteza){
		this.fatorDeCerteza = fatorDeCerteza;
	}
	
	public double getFatorDeCerteza() {
		return fatorDeCerteza;
	}

	public void setFatorDeCerteza(double fatorDeCerteza) {
		this.fatorDeCerteza = fatorDeCerteza;
	}

	public Conclusao(Variavel variavel) {
		this.setVariavel(variavel);
	}

	@Override
	public String toString() {
		
		return "#->#"+this.getVariavel().toString();
	}
}
