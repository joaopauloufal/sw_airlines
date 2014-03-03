package swairlines.bd;

import javafx.collections.ObservableList;
import swairlines.modelo.Cliente;

public interface AcessoBancoOperador {
	
	public boolean insereCliente(Cliente cliente);
	public boolean excluiCliente(Cliente cliente);
	public void alteraCliente(Cliente cliente);
	public ObservableList<Cliente> buscaClientes();

}
