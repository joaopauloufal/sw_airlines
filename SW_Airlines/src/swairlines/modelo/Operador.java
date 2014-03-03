package swairlines.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.bd.AcessoBancoOperador;
import swairlines.bd.Autenticavel;
import swairlines.bd.ConexaoBD;
import swairlines.gui.TelaCadCliente;

public class Operador extends Funcionario implements AcessoBancoOperador, Autenticavel {
	
	public Operador(String nome, String sexo, String cpf, String rg,
			String cargo, String dataDeNascimento, String telefoneCelular,
			String telefoneResidencial, String nacionalidade, String estadoCivil) {
		super(nome, sexo, cpf, rg, cargo, dataDeNascimento, telefoneCelular, telefoneResidencial, nacionalidade, estadoCivil);
	}
	
	public Operador() {
		
	}

	@Override
	public boolean insereCliente(Cliente cliente) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("INSERT INTO sw_airlines.cliente (rg, cpf_cnpj, nome, sexo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, cartao_de_credito, rua, cidade, bairro, numero, estado) " +
					"VALUES('" + cliente.getRg() +"','" + cliente.getCpfCnpj() +"','" + cliente.getNome() +"','" + cliente.getSexo() +"','" + cliente.getDataDeNascimento() +"','" + cliente.getEstadoCivil() +"','" + cliente.getNacionalidade() +"','" + cliente.getTelefoneCelular() +"','" + cliente.getTelefoneResidencial() +"','" + cliente.getCartaoDeCredito() +"','" 
					+ cliente.getEndereco().getRua() + "','" + cliente.getEndereco().getCidade() + "','" + cliente.getEndereco().getBairro() + "','" + cliente.getEndereco().getNumero() + "','" + cliente.getEndereco().getEstado() + "');");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public boolean excluiCliente(Cliente cliente) {

		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("DELETE FROM sw_airlines.cliente WHERE rg= '" + cliente.getRg()+"';");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	@Override
	public void alteraCliente(Cliente cliente) {
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("UPDATE sw_airlines.cliente SET cpf_cnpj='" + cliente.getCpfCnpj() +"', nome='" + cliente.getNome() +"', sexo='" + cliente.getSexo() +"', data_de_nascimento='" + cliente.getDataDeNascimento() +"', estado_civil='" + cliente.getEstadoCivil() +"', nacionalidade='" + cliente.getNacionalidade() +"', telefone_celular='" + cliente.getTelefoneCelular() +"', telefone_residencial='" + cliente.getTelefoneResidencial() +"', cartao_de_credito='" + cliente.getCartaoDeCredito()
					+ "', rua='" + cliente.getEndereco().getRua() +", 'cidade='"+ cliente.getEndereco().getCidade() + ", 'bairro='"+ cliente.getEndereco().getBairro() + ", 'numero='" + cliente.getEndereco().getNumero() +", 'estado='" + cliente.getEndereco().getEstado() + "' WHERE rg='" + cliente.getRg() +"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	@Override
	public ObservableList<Cliente> buscaClientes() {
		ObservableList<Cliente> clientes;
		clientes = FXCollections.observableArrayList();
		try {
			ConexaoBD cbd = new ConexaoBD();
			Connection con = cbd.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.cliente;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setRg("rg");
				cliente.setCpfCnpj("cpf_cnpj");
				cliente.setNome("nome");
				cliente.setSexo("sexo");
				cliente.setDataDeNascimento("data_de_nascimnento");
				cliente.setEstadoCivil("estado_civil");
				cliente.setNacionalidade("nacionalidade");
				cliente.setTelefoneCelular("telefone_celular");
				cliente.setTelefoneResidencial("telefone_residencial");
				cliente.setCartaoDeCredito("cartao_de_credito");
				cliente.getEndereco().setRua("rua");
				cliente.getEndereco().setCidade("cidade");
				cliente.getEndereco().setBairro("bairro");
				cliente.getEndereco().setNumero("numero");
				cliente.getEndereco().setEstado("estado");
				clientes.add(cliente);
			}
			
			rs.close();
			stm.close();
			con.close();
			return clientes;
		} catch (SQLException e) {
			Logger.getLogger(TelaCadCliente.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
		
	}

	@Override
	public boolean autenticar(ContaDeUsuario c1) {
		for (ContaDeUsuario temp : c1.buscaContasDeUsuario()) {
			if (temp.getLogin().equals(c1.getLogin()) && temp.getSenha().equals(c1.getSenha())){
				return true;				
			}
		}		
		return false;
	}
	

}
