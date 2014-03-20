package swairlines.dao;

import javafx.collections.ObservableList;
import swairlines.model.Cliente;

public interface ConsultasBancoCliente {
	/**<h1>Inserir novo cliente no banco</h1>
	 * @param {@link Cliente} Objeto cliente em questão 
	 * @return {@link Boolean} Confirmação ou não da operação realizada
	 */
	public boolean insereCliente(Cliente c1);
	/**<h1>Excluir cliente existente no banco</h1>
	 * @param {@link Cliente} Objeto cliente em questão 
	 * @return {@link Boolean} Confirmação ou não da operação realizada
	 */
	public boolean excluiCliente(Cliente c1);
	/**<h1>Alterar cliente existente no banco</h1>
	 * @param {@link Cliente} Objeto cliente em questão 
	 * @return {@link Boolean} Confirmação ou não da operação realizada
	 */
	public boolean alteraCliente(Cliente c1);
	/** <h1>Inserir novo cliente no banco</h1>
	 * @return {@link {@link ObservableList}} Lista de objetos clientes 
 	 * <p>Todos os cliente do banco</p>
	 */
	public ObservableList<Cliente> buscaClientes();

}
