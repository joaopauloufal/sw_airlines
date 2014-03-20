package swairlines.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name ConsultasBancoVoo
 */

import javafx.collections.ObservableList;
import swairlines.model.Voo;

public interface ConsultasBancoVoo {
	
	public boolean insereVoo(Voo f1);
	public boolean excluiVoo(Voo f1);
	public boolean alteraVoo(Voo f1);
	public ObservableList<Voo> buscaVoos();
	public Voo buscaVooPorId(int id);
	public boolean cancelarVoo(Voo voo);
	public boolean atrasarVoo(Voo voo);
	public boolean inserirPassageiro(Voo voo);
	public boolean removerPassageiro(Voo voo);

}
