package sistema_esp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BaseDeRegras {
	
	private ObservableList<Regra> regras;
	
	public BaseDeRegras() {
		this.regras = FXCollections.observableArrayList();
	}

	public ObservableList<Regra> getRegras() {
		return regras;
	}

	public void setRegras(ObservableList<Regra> regras) {
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
