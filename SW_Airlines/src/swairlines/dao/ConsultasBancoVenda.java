package swairlines.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name ConsultasBancoVenda
 */
import javafx.collections.ObservableList;
import swairlines.model.Venda;

public interface ConsultasBancoVenda {
	
	public boolean insereVenda(Venda venda);
	public boolean excluiVenda(Venda venda);
	public boolean alteraVenda(Venda venda);
	public ObservableList<Venda> buscaVendas();

}
