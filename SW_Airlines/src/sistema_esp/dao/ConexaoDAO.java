package sistema_esp.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name ConexaoDAO
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ConexaoDAO implements ConexaoBDMySql {
	
	private Connection c;
	private String sql;
	private Statement stm;
	
	/**
	 * Construtor ConexaoDAO, conecta com o banco
	 * @param 
	 * @return 
	 */
	public ConexaoDAO() {
		
		try {
			criarBanco();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}	
	
	/**
	 * abreConexao, abre conexão com o banco
	 * @return Connection
	 * @throws SQLException
	 */
	
	@Override
	public Connection abreConexao() throws SQLException {
		//35216493 senha bd do Cid
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost/","root","35216493");
            return c;
        } catch (SQLException ex) {
        	JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados. Tente novamente mais tarde.", "Erro de Conexão Com o Banco de Dados", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ConexaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
	/**
	 * desconecta conexão com o banco
	 * @return Connection
	 * @throws SQLException
	 */
	@Override
	public void desconectar() throws SQLException{
		c.close();
	}
	/**
	 * executa conexão com o banco
	 * @param String
	 * @return Connection
	 * @throws SQLException
	 */
	@Override
	public boolean executar(String sql) throws SQLException {
		try {
			abreConexao();
			stm = null;
			stm = c.createStatement();
			stm.executeUpdate(sql);
			return true;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados. Tente novamente mais tarde.", "Erro de Conexão Com o Banco de Dados", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
		} finally {
			desconectar();
		}
		return false;
		
	}
	
	/**
	 * <h1>Criação da tabela Banco</h1>
	 * @throws SQLException Erro na criação da tabela
	 */
	private void criarBanco() throws SQLException {
		sql = "CREATE DATABASE IF NOT EXISTS sist_esp DEFAULT CHARACTER SET utf8 ";
		executar(sql);		
		criarTabelaRegras();
	}

	
	private void criarTabelaRegras() throws SQLException {
		sql = "CREATE TABLE IF NOT EXISTS sist_esp.regras("
				+ " id INT NOT NULL AUTO_INCREMENT,"
				+ " nome VARCHAR(120) NOT NULL,"
				+ " premissas VARCHAR(120) NOT NULL,"
				+ " conclusao VARCHAR(120) NOT NULL,"
				+ " fator_de_confianca FLOAT(4, 1) NOT NULL,"
				+ " PRIMARY KEY(id)) "
				+ " ENGINE = InnoDB "
				+ " DEFAULT CHARACTER SET = utf8;";
		executar(sql);
	}
	
	

}
