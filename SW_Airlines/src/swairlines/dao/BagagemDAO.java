package swairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.model.Bagagem;
import swairlines.model.Venda;

public class BagagemDAO implements ConsultasBancoBagagem {

	@Override
	public boolean insereBagagem(Bagagem bagagem) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			if (conDao.executar("INSERT INTO sw_airlines.bagagens (cpf_cliente_bagagem, nome_cliente, voo_id, origem_voo, destino_voo, peso_bagagem)" + 
					"VALUES('" + bagagem.getCpfCnpjCliente() + "','" + bagagem.getNomeCliente() + "','" + bagagem.getVooId() + "','" + bagagem.getOrigemVoo() + "','" + bagagem.getDestinoVoo() + "','" + bagagem.getPesoBagagem() + "');")) {
				if (bagagem.getTaxa() != 0.0) {
					double valor = retornaValorVooPorid(bagagem.getCpfCnpjCliente());
					valor += valor * bagagem.getTaxa();
					conDao.executar("UPDATE sw_airlines.vendas SET valor_voo='" + valor + "' WHERE cpf_cliente='" + bagagem.getCpfCnpjCliente() + "';");
				}
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(BagagemDAO.class.getName()).log(Level.SEVERE, null, ex);

		}
		return false;
	}

	@Override
	public boolean removeBagagem(Bagagem bagagem) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			if (conDao.executar("DELETE FROM sw_airlines.bagagem WHERE cpf_cliente='" + bagagem.getCpfCnpjCliente() + "';")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(BagagemDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public boolean alteraBagagem(Bagagem bagagem) {
		try {
			ConexaoDAO conDao = new ConexaoDAO();
			if (conDao.executar("UPDATE sw_airlines.bagagem SET peso_bagagem='" + bagagem.getPesoBagagem() + "' WHERE cpf_cliente='" + bagagem.getCpfCnpjCliente() + "';")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(BagagemDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	@Override
	public ObservableList<Bagagem> buscaBagagens() {
		ObservableList<Bagagem> bagagens;
		bagagens = FXCollections.observableArrayList();
		ConexaoDAO conDao = new ConexaoDAO();
		try {
			Connection con = conDao.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.bagagens;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Bagagem bagagem = new Bagagem();
				bagagem.setCpfCnpjCliente(rs.getString("cpf_cliente_bagagem"));
				bagagem.setNomeCliente(rs.getString("nome_cliente"));
				bagagem.setVooId(rs.getInt("voo_id"));
				bagagem.setOrigemVoo(rs.getString("origem_voo"));
				bagagem.setDestinoVoo(rs.getString("destino_voo"));
				bagagem.setPesoBagagem(rs.getDouble("peso_bagagem"));
				bagagens.add(bagagem);
			}
			
			rs.close();
			stm.close();
			con.close();
			return bagagens;
		} catch (SQLException ex) {
			Logger.getLogger(BagagemDAO.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		
	}

	@Override
	public ObservableList<Bagagem> buscaBagagensPorCpfCliente(String cpfCnpCliente) {
		ObservableList<Bagagem> bagagens;
		bagagens = FXCollections.observableArrayList();
		ConexaoDAO conDao = new ConexaoDAO();
		try {
			Connection con = conDao.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.bagagens;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Bagagem bagagem = new Bagagem();
				bagagem.setCpfCnpjCliente(rs.getString("cpf_cliente_bagagem"));
				bagagem.setNomeCliente(rs.getString("nome_cliente"));
				bagagem.setVooId(rs.getInt("voo_id"));
				bagagem.setOrigemVoo(rs.getString("origem_voo"));
				bagagem.setDestinoVoo(rs.getString("destino_voo"));
				bagagem.setPesoBagagem(rs.getDouble("peso_bagagem"));
				bagagens.add(bagagem);
			}
			
			rs.close();
			stm.close();
			con.close();
			return bagagens;
		} catch (SQLException ex) {
			Logger.getLogger(BagagemDAO.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	private Double retornaValorVooPorid(String cpf) {
		ConexaoDAO conDao = new ConexaoDAO();
		Connection con;
		Venda venda = new Venda();
		try {
			con = conDao.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT valor_voo FROM sw_airlines.vendas WHERE cpf_cliente='"+cpf+"';");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {				
				venda.setValorVoo(rs.getDouble("valor_voo"));
			}
			rs.close();
			stm.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venda.getValorVoo();
		
	}

}
