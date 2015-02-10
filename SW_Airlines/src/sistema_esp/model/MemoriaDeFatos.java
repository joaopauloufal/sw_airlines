package sistema_esp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemoriaDeFatos {
	
	public ObservableList<Premissa> listaDeFatos;
	
	public MemoriaDeFatos() {
		this.listaDeFatos = FXCollections.observableArrayList();
	}

	public ObservableList<Premissa> getListaDeFatos() {
		return listaDeFatos;
	}

	public void setListaDeFatos(ObservableList<Premissa> listaDeFatos) {
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
