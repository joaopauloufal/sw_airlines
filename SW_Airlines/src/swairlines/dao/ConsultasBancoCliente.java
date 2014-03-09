package swairlines.dao;

import javafx.collections.ObservableList;
import swairlines.model.Cliente;

public interface ConsultasBancoCliente {
	
	public boolean insereCliente(Cliente c1);
	public boolean excluiCliente(Cliente c1);
	public boolean alteraCliente(Cliente c1);
	public ObservableList<Cliente> buscaClientes();

}
