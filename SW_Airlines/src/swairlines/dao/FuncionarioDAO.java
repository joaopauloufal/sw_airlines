package swairlines.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name FuncionarioDAO
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.model.Endereco;
import swairlines.model.Funcionario;
import swairlines.model.Gerente;

public class FuncionarioDAO implements ConsultasBancoFuncionario {
	
	/**
	 * insereFuncionario, insere funcionario no banco
	 * @param Funcionario
	 * @return boolean
	 * @throws SQLException
	 */
	
	@Override
	public boolean insereFuncionario(Funcionario f1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("INSERT INTO sw_airlines.funcionario(cpf, nome, sexo, rg, cargo, data_de_nascimento, estado_civil, nacionalidade, telefone_celular, telefone_residencial, rua, cidade, bairro, numero, estado) " +
					"VALUES('" + f1.getCpf() +"','" + f1.getNome() +"','" + f1.getSexo() +"','" + f1.getRg() +"','" + f1.getCargo() +"','" + f1.getDataDeNascimento() +"','" + f1.getEstadoCivil() +"','" + f1.getNacionalidade() +"','" + f1.getTelefoneCelular() +"','" + f1.getTelefoneResidencial() + "','" 
					+ f1.getEndereco().getRua() + "','" + f1.getEndereco().getCidade() + "','" + f1.getEndereco().getBairro() + "','" + f1.getEndereco().getNumero() + "','" + f1.getEndereco().getEstado() + "');")) {
				return true;
			}
			
			
			
		} catch (SQLException ex) {
			Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
	
	/**
	 * exclui funcionario no banco
	 * @param Funcionario
	 * @return boolean
	 * @throws SQLException
	 */
	
	@Override
	public boolean excluiFuncionario(Funcionario f1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("DELETE FROM sw_airlines.funcionario WHERE cpf= '" + f1.getCpf() +"';")) {
				return true;
			}
			
			
			
		} catch (SQLException ex) {
			Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	/**
	 * altera funcionario no banco
	 * @param Funcionario
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean alteraFuncionario(Funcionario f1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("UPDATE sw_airlines.funcionario set nome='" + f1.getNome() +"', sexo='" + f1.getSexo() +"', rg='" + f1.getRg() +"', cargo='" + f1.getCargo() +"', data_de_nascimento='" + f1.getDataDeNascimento() +"', estado_civil='" + f1.getEstadoCivil() +"', nacionalidade='" + f1.getNacionalidade() +"', telefone_celular='" + f1.getTelefoneCelular() +"', telefone_residencial='" + f1.getTelefoneResidencial() 
					+"', rua='" + f1.getEndereco().getRua() +"', cidade='" + f1.getEndereco().getCidade() +"', bairro='" + f1.getEndereco().getBairro() +"', numero='" + f1.getEndereco().getNumero() +"', estado='"+ f1.getEndereco().getEstado() +"' WHERE cpf='" + f1.getCpf() +"';")) {
				return true;
			}
			
			
		} catch (SQLException ex) {
			Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	
	/**
	 * busca funcionarios no banco
	 * @return {@link ObservableList}
	 * @throws SQLException
	 */
	
	@Override
	public ObservableList<Funcionario> buscaFuncionarios() {
		ObservableList<Funcionario> funcionarios;
		funcionarios = FXCollections.observableArrayList();		
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			Connection con = cbd.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.funcionario;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				Funcionario func = new Gerente();
				Endereco endereco = new Endereco();
				func.setCpf(rs.getString("cpf"));
				func.setNome(rs.getString("nome"));
				func.setSexo(rs.getString("sexo"));
				func.setRg(rs.getString("rg"));
				func.setCargo(rs.getString("cargo"));
				func.setDataDeNascimento(rs.getString("data_de_nascimento"));
				func.setEstadoCivil(rs.getString("estado_civil"));
				func.setNacionalidade(rs.getString("nacionalidade"));
				func.setTelefoneCelular(rs.getString("telefone_celular"));
				func.setTelefoneResidencial(rs.getString("telefone_residencial"));
				endereco.setRua(rs.getString("rua"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setEstado(rs.getString("estado"));
				func.setEndereco(endereco);
				funcionarios.add(func);				
			}
			
			rs.close();
			stm.close();
			con.close();
			return funcionarios;
		} catch (SQLException e) {
			Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, e);	
			return null;
		}
		
		
	}

}
