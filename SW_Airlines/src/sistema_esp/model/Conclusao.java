package sistema_esp.model;

import sistema_esp.model.Variavel;

public class Conclusao extends Premissa {
	

	public Conclusao() {
		this.setSimbolo("");
	}

	public Conclusao(Variavel variavel) {
		this.setVariavel(variavel);
	}
	
	@Override
	public String toString() {
		return this.getVariavel().toString();

	}
	
}
