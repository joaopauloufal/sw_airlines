package swairlines.bd;

import javafx.collections.ObservableList;
import swairlines.modelo.Cliente;
import swairlines.modelo.ContaDeUsuario;
import swairlines.modelo.Endereco;

public interface AcessoBancoOperador {
	
	public boolean insereCliente(Cliente cliente, Endereco e1);
	public boolean excluiCliente(Cliente cliente);
	public void alteraCliente(Cliente cleinte);
	public ObservableList<Cliente> buscaClientes();
	public boolean autenticar(ContaDeUsuario c1);

}
