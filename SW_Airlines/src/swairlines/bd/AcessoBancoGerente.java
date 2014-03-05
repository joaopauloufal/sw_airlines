package swairlines.bd;

import javafx.collections.ObservableList;
import swairlines.modelo.ContaDeUsuario;
import swairlines.modelo.Funcionario;
import swairlines.modelo.Voo;

public interface AcessoBancoGerente extends AcessoBancoOperador {
	
	public boolean insereFuncionario(Funcionario f1);
	public boolean excluiFuncionario(Funcionario f1);
	public boolean alteraFuncionario(Funcionario f1);
	public ObservableList<Funcionario> buscaFuncionarios();
	
	public boolean insereVoo(Voo v1);
	public boolean excluiVoo(Voo v1);
	public boolean alteraVoo(Voo v1);
	public ObservableList<Voo> buscaVoos();
	
	public boolean insereContaDeUsuario(ContaDeUsuario c1);
	public boolean excluiContaDeUsuario(ContaDeUsuario c1);
	public boolean alteraContaDeUsuario(ContaDeUsuario c1);
	

}
