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
import swairlines.model.Operador;

public class ClienteDAO {
	
	public boolean insereCliente(Cliente cliente) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("INSERT INTO sw_airlines.cliente (rg, cpfcnpj, nome, sexo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, cartao_de_credito, rua, cidade, bairro, numero, estado) " +
					"VALUES('" + cliente.getRg() +"','" + cliente.getCpfCnpj() +"','" + cliente.getNome() +"','" + cliente.getSexo() +"','" + cliente.getDataDeNascimento() +"','" + cliente.getEstadoCivil() +"','" + cliente.getNacionalidade() +"','" + cliente.getTelefoneCelular() +"','" + cliente.getTelefoneResidencial() +"','" + cliente.getCartaoDeCredito() +"','" 
					+ cliente.getEndereco().getRua() + "','" + cliente.getEndereco().getCidade() + "','" + cliente.getEndereco().getBairro() + "','" + cliente.getEndereco().getNumero() + "','" + cliente.getEndereco().getEstado() + "');")) {
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(Operador.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	public boolean excluiCliente(Cliente cliente) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("DELETE FROM sw_airlines.cliente WHERE rg= '" + cliente.getRg()+"';")) {
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(Operador.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	public boolean alteraCliente(Cliente cliente) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("UPDATE sw_airlines.cliente SET cpf_cnpj='" + cliente.getCpfCnpj() +"', nome='" + cliente.getNome() +"', sexo='" + cliente.getSexo() +"', data_de_nascimento='" + cliente.getDataDeNascimento() +"', estado_civil='" + cliente.getEstadoCivil() +"', nacionalidade='" + cliente.getNacionalidade() +"', telefone_celular='" + cliente.getTelefoneCelular() +"', telefone_residencial='" + cliente.getTelefoneResidencial() +"', cartao_de_credito='" + cliente.getCartaoDeCredito()
					+ "', rua='" + cliente.getEndereco().getRua() +", 'cidade='"+ cliente.getEndereco().getCidade() + ", 'bairro='"+ cliente.getEndereco().getBairro() + ", 'numero='" + cliente.getEndereco().getNumero() +", 'estado='" + cliente.getEndereco().getEstado() + "' WHERE rg='" + cliente.getRg() +"';")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Operador.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
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
				cliente.setRg(rs.getString("rg"));
				cliente.setCpfCnpj(rs.getString("cpfcnpj"));
				cliente.setNome(rs.getString("nome"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setDataDeNascimento(rs.getString("data_de_nascimento"));
				cliente.setEstadoCivil(rs.getString("estado_civil"));
				cliente.setNacionalidade(rs.getString("nacionalidade"));
				cliente.setTelefoneCelular(rs.getString("telefone_celular"));
				cliente.setTelefoneResidencial(rs.getString("telefone_residencial"));
				cliente.setCartaoDeCredito(rs.getString("cartao_de_credito"));
				cliente.setRua(rs.getString("rua"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setBairro(rs.getString("bairro"));
				cliente.setNumero(rs.getString("numero"));
				cliente.setEstado(rs.getString("estado"));	
				clientes.add(cliente);
			}
			
			rs.close();
			stm.close();
			con.close();
			return clientes;
		} catch (SQLException e) {
			Logger.getLogger(Operador.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

}