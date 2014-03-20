package swairlines.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name ConexaoBDMySql
 */

import java.sql.Connection;
import java.sql.SQLException;

public interface ConexaoBDMySql {
	
	/** <h1>Abre a coneção com o banco de dados</h1>
	 * @return {@link Connection}
	 * @throws SQLException Erro na conexão
	 */
	public Connection abreConexao() throws SQLException;
	/** <h1>Fecha a coneção com o banco de dados</h1>
	 * @return {@link Void}
	 * @throws SQLException Erro na conexão
	 */
	public void desconectar() throws SQLException;
	/** <h1>Executa um comando no banco de dados</h1>
	 * @param String representando um comando SQL
	 * @return {@link Boolean}
	 * @throws SQLException Expressão inválida
	 * @see SQL
	 */
	public boolean executar(String sql) throws SQLException;	
	

}
