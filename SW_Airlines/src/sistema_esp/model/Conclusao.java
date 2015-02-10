package sistema_esp.model;

import sistema_esp.model.Variavel;

public class Conclusao extends Premissa {
	

	public Conclusao() {
		
	}

	public Conclusao(Variavel variavel) {
		this.setVariavel(variavel);
		
	}
	
	@Override
	public String toString() {
		return this.getVariavel().toString();

	}
	
}
