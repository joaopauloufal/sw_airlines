package sistema_esp.model;

import sistema_esp.model.Variavel;

public class Conclusao extends Premissa {
	

	public Conclusao() {
		this.isConclusao = true;
	}

	public Conclusao(Variavel variavel) {
		this.setVariavel(variavel);
		this.isConclusao = true;
	}
	
	@Override
	public String toString() {
		return this.getVariavel().toString();

	}
	
}
