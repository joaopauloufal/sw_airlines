package swairlines.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConexaoBDMySql {
	
	public Connection abreConexao() throws SQLException;
	public void desconectar() throws SQLException;
	public boolean executar(String sql) throws SQLException;	
	

}
