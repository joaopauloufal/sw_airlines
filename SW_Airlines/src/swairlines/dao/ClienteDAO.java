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
import swairlines.model.Endereco;

public class ClienteDAO implements ConsultasBancoCliente {
	
	@Override
	public boolean insereCliente(Cliente cliente) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			cbd.executar("INSERT INTO sw_airlines.cliente (rg, cpfcnpj, nome, sexo, passaporte_numero, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, cartao_de_credito, rua, cidade, bairro, numero, estado) " +
					"VALUES('" + cliente.getRg() +"','" + cliente.getCpfCnpj() +"','" + cliente.getNome() +"','" + cliente.getSexo() +"','" + cliente.getPassaporteNumero() + "','" + cliente.getDataDeNascimento() +"','" + cliente.getEstadoCivil() +"','" + cliente.getNacionalidade() +"','" + cliente.getTelefoneCelular() +"','" + cliente.getTelefoneResidencial() +"','" + cliente.getCartaoDeCredito() +"','" 
					+ cliente.getEndereco().getRua() + "','" + cliente.getEndereco().getCidade() + "','" + cliente.getEndereco().getBairro() + "','" + cliente.getEndereco().getNumero() + "','" + cliente.getEndereco().getEstado() + "');");
				return true;
			
			
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	@Override
	public boolean excluiCliente(Cliente cliente) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			cbd.executar("DELETE FROM sw_airlines.cliente WHERE cpfcnpj= '" + cliente.getCpfCnpj()+"';");
			return true;
			
			
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	@Override
	public boolean alteraCliente(Cliente cliente) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			cbd.executar("UPDATE sw_airlines.cliente SET rg='" + cliente.getRg() +"', nome='" + cliente.getNome() +"', sexo='" + cliente.getSexo() + "', passaporte_numero='" + cliente.getPassaporteNumero() +"', data_de_nascimento='" + cliente.getDataDeNascimento() +"', estado_civil='" + cliente.getEstadoCivil() +"', nacionalidade='" + cliente.getNacionalidade() +"', telefone_celular='" + cliente.getTelefoneCelular() +"', telefone_residencial='" + cliente.getTelefoneResidencial() +"', cartao_de_credito='" + cliente.getCartaoDeCredito()
					+ "', rua='" + cliente.getEndereco().getRua() +"', cidade='"+ cliente.getEndereco().getCidade() +"', bairro='"+ cliente.getEndereco().getBairro() +"', numero='" + cliente.getEndereco().getNumero() +"', estado='" + cliente.getEndereco().getEstado() +"' WHERE cpfcnpj='" + cliente.getCpfCnpj() +"';");
			return true;
			
		} catch (SQLException ex) {
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	@Override
	public ObservableList<Cliente> buscaClientes() {
		ObservableList<Cliente> clientes;
		clientes = FXCollections.observableArrayList();
		ConexaoDAO cbd = new ConexaoDAO();
		try {
			
			Connection con = cbd.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.cliente;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente();
				Endereco endereco = new Endereco();
				cliente.setRg(rs.getString("rg"));
				cliente.setCpfCnpj(rs.getString("cpfcnpj"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setPassaporteNumero(rs.getString("passaporte_numero"));
				cliente.setDataDeNascimento(rs.getString("data_de_nascimento"));
				cliente.setEstadoCivil(rs.getString("estado_civil"));
				cliente.setNacionalidade(rs.getString("nacionalidade"));
				cliente.setTelefoneCelular(rs.getString("telefone_celular"));
				cliente.setTelefoneResidencial(rs.getString("telefone_residencial"));
				cliente.setCartaoDeCredito(rs.getString("cartao_de_credito"));
				endereco.setRua(rs.getString("rua"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setEstado(rs.getString("estado"));
				cliente.setEndereco(endereco);
				clientes.add(cliente);
			}
			
			rs.close();
			stm.close();
			con.close();
			return clientes;
		} catch (SQLException e) {
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}
	public Cliente buscaClientePorCpf(String cpf) {
		ConexaoDAO cbd = new ConexaoDAO();
		Cliente cliente = new Cliente();
		try {
			
			Connection con = cbd.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.cliente where cpfcnpj='" + cpf +"';");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Endereco endereco = new Endereco();
				cliente.setRg(rs.getString("rg"));
				cliente.setCpfCnpj(rs.getString("cpfcnpj"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setPassaporteNumero(rs.getString("passaporte_numero"));
				cliente.setDataDeNascimento(rs.getString("data_de_nascimento"));
				cliente.setEstadoCivil(rs.getString("estado_civil"));
				cliente.setNacionalidade(rs.getString("nacionalidade"));
				cliente.setTelefoneCelular(rs.getString("telefone_celular"));
				cliente.setTelefoneResidencial(rs.getString("telefone_residencial"));
				cliente.setCartaoDeCredito(rs.getString("cartao_de_credito"));
				endereco.setRua(rs.getString("rua"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setEstado(rs.getString("estado"));
				cliente.setEndereco(endereco);
			}
			
			rs.close();
			stm.close();
			con.close();
			return cliente;
		} catch (SQLException e) {
			Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

}
