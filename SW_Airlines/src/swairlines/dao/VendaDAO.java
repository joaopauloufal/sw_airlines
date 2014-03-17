package swairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.model.Venda;

public class VendaDAO implements ConsultasBancoVenda {

	@Override
	public boolean insereVenda(Venda venda) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			conDao.executar("INSERT INTO sw_airlines.vendas (rg_cliente, id_voo, tipo_venda, data_venda) " + 
			"VALUES('"+ venda.getCliente().getRg() +"','" + venda.getVoo().getId() +"','" + venda.getTipoVenda() + "','" + venda.getDataVenda() +"');");
			return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
		}		
		return false;
	}

	@Override
	public boolean excluiVenda(Venda venda) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			conDao.executar("DELETE FROM sw_airlines.vendas WHERE id_voo='" + venda.getVoo().getId() + "';");
			return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public boolean alteraVenda(Venda venda) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			conDao.executar("UPDATE sw_airlines.vendas SET cpf_cliente='" + venda.getCliente().getRg() + "', tipo_venda='" + venda.getTipoVenda() + "', data_venda='" + venda.getDataVenda() + "' WHERE voo_id='" + venda.getVoo().getId() + "';");
			return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public ObservableList<Venda> buscaVendas() {
		ObservableList<Venda> vendas;
		vendas = FXCollections.observableArrayList();
		ConexaoDAO conDao = new ConexaoDAO();
		try {
			Connection con = conDao.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.clientes;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Venda venda = new Venda();
				venda.getCliente().setRg(rs.getString("cpf_cliente"));
				venda.getVoo().setId(rs.getInt("voo_id"));
				venda.setTipoVenda(rs.getString("tipo_venda"));
				venda.setDataVenda("data_venda");
				vendas.add(venda);
			}
			rs.close();
			stm.close();
			con.close();
			return vendas;
		} catch (SQLException ex) {
			Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		
	}
	

}
