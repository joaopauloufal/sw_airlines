package swairlines.dao;

import swairlines.model.Funcionario;
import javafx.collections.ObservableList;

public interface ConsultasBancoFuncionario {
	
	public boolean insereFuncionario(Funcionario f1);
	public boolean excluiFuncionario(Funcionario f1);
	public boolean alteraFuncionario(Funcionario f1);
	public ObservableList<Funcionario> buscaFuncionarios();
	
	

}
