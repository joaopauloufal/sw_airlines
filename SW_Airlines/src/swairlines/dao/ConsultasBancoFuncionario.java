package swairlines.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name ConsultasBancoFuncionario
 */

import swairlines.model.Funcionario;
import javafx.collections.ObservableList;

public interface ConsultasBancoFuncionario {
	
	public boolean insereFuncionario(Funcionario f1);
	public boolean excluiFuncionario(Funcionario f1);
	public boolean alteraFuncionario(Funcionario f1);
	public ObservableList<Funcionario> buscaFuncionarios();
	
	

}
