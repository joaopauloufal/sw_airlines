package swairlines.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name ConsultasBancoBagagem
 */

import javafx.collections.ObservableList;
import swairlines.model.Bagagem;

public interface ConsultasBancoBagagem {
	
	public boolean insereBagagem(Bagagem bagagem);
	public boolean removeBagagem(Bagagem bagagem);
	public boolean alteraBagagem(Bagagem bagagem);
	public ObservableList<Bagagem> buscaBagagens();
	public ObservableList<Bagagem> buscaBagagensPorCpfCliente(String cpfCnpjCliente);

}
