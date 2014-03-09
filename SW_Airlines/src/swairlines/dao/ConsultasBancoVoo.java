package swairlines.dao;

import javafx.collections.ObservableList;
import swairlines.model.Voo;

public interface ConsultasBancoVoo {
	
	public boolean insereVoo(Voo f1);
	public boolean excluiVoo(Voo f1);
	public boolean alteraVoo(Voo f1);
	public ObservableList<Voo> buscaVoos();

}
