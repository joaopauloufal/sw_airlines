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
	private Statement stm;
	public static String bancoDeTrabalho;
	
	/**
	 * Construtor ConexaoDAO, conecta com o banco
	 * @param 
	 * @return 
	 */
	public ConexaoDAO() {
		
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
            c = DriverManager.getConnection("jdbc:mysql://localhost/","swairlines","swairlines");
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

}
