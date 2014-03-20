package swairlines.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name ConsultasBancoContaDeUsuario
 */

import javafx.collections.ObservableList;
import swairlines.model.ContaDeUsuario;

public interface ConsultasBancoContaDeUsuario {
	
	public boolean insereContaDeUsuario(ContaDeUsuario c1);
	public boolean excluiContaDeUsuario(ContaDeUsuario c1);
	public boolean alteraContaDeUsuario(ContaDeUsuario c1);
	public ObservableList<ContaDeUsuario> buscaContasDeUsuario();

}
