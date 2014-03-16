package swairlines.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import swairlines.model.Venda;

public class VendaDAO implements ConsultasBancoVenda {

	@Override
	public boolean insereVenda(Venda venda) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			if (conDao.executar("INSERT INTO sw_airlines.vendas (rg_cliente, id_voo, tipo_venda, data_venda) " + 
			"VALUES('"+ venda.getCliente().getRg() +"','" + venda.getVoo().getId() +"','" + venda.getTipoVenda() + "','" + venda.getDataVenda() +"');")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
		}		
		return false;
	}

	@Override
	public boolean excluiVenda(Venda venda) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			if (conDao.executar("DELETE FROM sw_airlines.vendas WHERE id_voo='" + venda.getVoo().getId() + "';")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public boolean alteraVenda(Venda venda) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			if (conDao.executar("UPDATE sw_airlines.vendas SET rg_cliente'" + venda.getCliente().getRg() + "', tipo_venda='" + venda.getTipoVenda() + "', data_venda='" + venda.getDataVenda() + "' WHERE voo_id='" + venda.getVoo().getId() + "';")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public ObservableList<Venda> buscaVendas() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
