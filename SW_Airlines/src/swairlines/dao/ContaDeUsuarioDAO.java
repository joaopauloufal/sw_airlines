package swairlines.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name ContaDeUsuarioDAO
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.model.ContaDeUsuario;

public class ContaDeUsuarioDAO implements ConsultasBancoContaDeUsuario {
	
	/**
	 * insereContaDeUsuario, insere conta no banco
	 * @param ContaDeUsuario
	 * @return boolean
	 * @throws SQLException
	 */
	
	
	@Override
	public boolean insereContaDeUsuario(ContaDeUsuario c1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if (cbd.executar("INSERT INTO sw_airlines.usuario (login, senha, tipo_conta, cpf_func) VALUES('" + c1.getLogin() +"','" + c1.getSenha() +"','" + c1.getTipoConta() +"','" + c1.getCpfFuncionario() +"');")){
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(ContaDeUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	/**
	 * exclui conta no banco
	 * @param ContaDeUsuario
	 * @return boolean
	 * @throws SQLException
	 */
	
	
	@Override
	public boolean excluiContaDeUsuario(ContaDeUsuario c1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("DELETE FROM sw_airlines.usuario WHERE cpf_func= '" + c1.getCpfFuncionario() +"';")) {
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(ContaDeUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	/**
	 * altera conta no banco
	 * @param ContaDeUsuario
	 * @return boolean
	 * @throws SQLException
	 */
	
	@Override
	public boolean alteraContaDeUsuario(ContaDeUsuario c1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("UPDATE sw_airlines.usuario SET senha='" + c1.getSenha() + "', login='" + c1.getLogin() + "' WHERE cpf_func='" + c1.getCpfFuncionario() +"';")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ContaDeUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
		
	}
	/**
	 * busca contas no banco
	 * @return {@link ObservableList}
	 * @throws SQLException
	 */
	
	@Override
	public ObservableList<ContaDeUsuario> buscaContasDeUsuario() {
		ObservableList<ContaDeUsuario> usuarios;
		usuarios = FXCollections.observableArrayList();
		ConexaoDAO cbd = new ConexaoDAO();
		try {			
			//Abre a conexão com o banco de dados
			Connection con = cbd.abreConexao();
			//Cria um statement para realizar comandos no BD
			PreparedStatement stm = con.prepareStatement("SELECT login, senha, tipo_conta, cpf_func FROM sw_airlines.funcionario, sw_airlines.usuario WHERE cpf = cpf_func;");
			//Armazena o valor da pesquisa e no rs
			ResultSet rs = stm.executeQuery();
			//Com o while ele vai rodar linha por linha sendo o parâmetro o rs.next(), que retorna V ou F se a tabela tiver ou não linhas.
			while (rs.next()){
				ContaDeUsuario u = new ContaDeUsuario();
				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setTipoConta(rs.getString("tipo_conta"));
				u.setCpfFuncionario(rs.getString("cpf_func"));
				//Adiciona o objeto a (usuario) a lista usuarios, usando o método add
				usuarios.add(u);
			}
			rs.close();
			stm.close();
			con.close();
			//O retorno pode ser tanto dentro do try-catch sendo return usuario e return null ou fora e assim só sendo preciso um return
			return usuarios;

		} catch (SQLException ex) {
			Logger.getLogger(ContaDeUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		} 
	}
	
	

}
