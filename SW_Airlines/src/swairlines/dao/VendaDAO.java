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
			if(conDao.executar("INSERT INTO sw_airlines.vendas (cpf_cliente, nome_cliente, cartao_cliente, id_voo_venda, tipo_venda, data_venda, parcelas, valor_parcela, valor_voo, origem_voo, destino_voo) " + 
			"VALUES('"+ venda.getCpfCnpjCliente() +"','" + venda.getNomeCliente() +"','" + venda.getCartaoCreditoCliente() + "','" + venda.getIdVoo() +"','"+ venda.getTipoVenda() + "','" + venda.getDataVenda() + "','" + venda.getParcelas() + "','" + venda.getValorParcela() + "','" + venda.getValorVoo() + "','" + venda.getOrigemVoo() + "','" + venda.getDestinoVoo() +"');")) {
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
			if(conDao.executar("DELETE FROM sw_airlines.vendas WHERE id_voo_venda='" + venda.getIdVoo() + "';")) {
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
			if (conDao.executar("UPDATE sw_airlines.vendas SET cpf_cliente='" + venda.getCpfCnpjCliente() + "', tipo_venda='" + venda.getTipoVenda() + "', data_venda='" + venda.getDataVenda() + "', parcelas='" + venda.getParcelas() + "', valor_parcela='" + venda.getValorParcela() + "', valor_voo='" + venda.getValorVoo() +"', origem_voo='" + venda.getOrigemVoo() + "', destino_voo='" + venda.getDestinoVoo() + "' WHERE id_voo_venda='" + venda.getIdVoo() + "';")) {
				return true;
			}
			
			
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
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.vendas;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setCpfCnpjCliente(rs.getString("cpf_cliente"));
				venda.setNomeCliente(rs.getString("nome_cliente"));
				venda.setCartaoCreditoCliente(rs.getString("cartao_cliente"));
				venda.setIdVoo(rs.getInt("id_voo_venda"));
				venda.setTipoVenda(rs.getString("tipo_venda"));
				venda.setDataVenda(rs.getString("data_venda"));
				venda.setParcelas(rs.getInt("parcelas"));
				venda.setValorParcela(rs.getDouble("valor_parcela"));
				venda.setValorVoo(rs.getDouble("valor_voo"));
				venda.setOrigemVoo(rs.getString("origem_voo"));
				venda.setDestinoVoo(rs.getString("destino_voo"));
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