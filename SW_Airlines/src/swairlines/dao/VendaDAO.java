package swairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.model.Cliente;
import swairlines.model.Venda;
import swairlines.model.Voo;

public class VendaDAO implements ConsultasBancoVenda {

	@Override
	public boolean insereVenda(Venda venda) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			conDao.executar("INSERT INTO sw_airlines.vendas (cpf_cliente, id_voo_venda, tipo_venda, data_venda) " + 
			"VALUES('"+ venda.getCliente().getCpfCnpj() +"','" + venda.getVoo().getId() +"','" + venda.getTipoVenda() + "','" + venda.getDataVenda() +"');");
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
			conDao.executar("DELETE FROM sw_airlines.vendas WHERE id_voo_venda='" + venda.getVoo().getId() + "';");
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
			conDao.executar("UPDATE sw_airlines.vendas SET cpf_cliente='" + venda.getCliente().getRg() + "', tipo_venda='" + venda.getTipoVenda() + "', data_venda='" + venda.getDataVenda() + "' WHERE id_voo_venda='" + venda.getVoo().getId() + "';");
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
				Voo voo = new Voo();
				Cliente cliente = new Cliente();
				cliente.setRg(rs.getString("cpf_cliente"));
				voo.setId(rs.getInt("id_voo_venda"));
				venda.setTipoVenda(rs.getString("tipo_venda"));
				venda.setDataVenda("data_venda");
				venda.setVoo(voo);
				venda.setCliente(cliente);
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
