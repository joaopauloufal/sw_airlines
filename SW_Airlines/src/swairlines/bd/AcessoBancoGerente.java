package swairlines.bd;

import javafx.collections.ObservableList;
import swairlines.modelo.ContaDeUsuario;
import swairlines.modelo.Endereco;
import swairlines.modelo.Funcionario;
import swairlines.modelo.Voo;

public interface AcessoBancoGerente extends AcessoBancoOperador {
	
	public boolean insereFuncionario(Funcionario f1, Endereco e1);
	public boolean excluiFuncionario(Funcionario f1);
	public void alteraFuncionario(Funcionario f1, Endereco e1);
	public ObservableList<Funcionario> buscaFuncionarios();
	
	public boolean insereVoo(Voo v1);
	public boolean excluiVoo(Voo v1);
	public boolean alteraVoo(Voo v1);
	public ObservableList<Voo> buscaVoos();
	
	public boolean insereContaDeUsuario(ContaDeUsuario c1);
	public boolean excluiContaDeUsuario(ContaDeUsuario c1);
	public void alteraContaDeUsuaio(ContaDeUsuario c1);
	public ObservableList<ContaDeUsuario> buscaContasDeUsuario();
	

}
