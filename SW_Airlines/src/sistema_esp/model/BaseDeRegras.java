package sistema_esp.model;

import java.util.ArrayList;

public class BaseDeRegras {
	
	private ArrayList<Regra> regras;
	
	public BaseDeRegras() {
		this.regras = new ArrayList<Regra>();
	}

	public ArrayList<Regra> getRegras() {
		return regras;
	}

	public void setRegras(ArrayList<Regra> regras) {
		this.regras = regras;
	}
	
	public void adicionarRegra(Regra regra){
		this.regras.add(regra);
	}

	@Override
	public String toString() {
		return "BaseDeRegras [regras=" + regras + "]";
	}
	
	
	
	

}
