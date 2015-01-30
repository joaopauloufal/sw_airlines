package sistema_esp.model;

import java.util.ArrayList;

public class MemoriaDeFatos {
	
	public ArrayList<Premissa> listaDeFatos;
	
	public MemoriaDeFatos() {
		this.listaDeFatos = new ArrayList<Premissa>();
	}

	public ArrayList<Premissa> getListaDeFatos() {
		return listaDeFatos;
	}

	public void setListaDeFatos(ArrayList<Premissa> listaDeFatos) {
		this.listaDeFatos = listaDeFatos;
	}
	
	
	public void adicionarFato(Premissa premisa){
		this.listaDeFatos.add(premisa);
	}
	

	@Override
	public String toString() {
		return "MemoriaDeFatos [listaDeFatos=" + listaDeFatos + "]";
	}
	
	
	
	

}
