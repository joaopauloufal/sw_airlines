package sistema_esp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListaBancosDAO {
	private ConexaoDAO dao;
	private Connection connDAO;
	
	public ListaBancosDAO () {
		dao = new ConexaoDAO();
	}
	
	public ObservableList<String> consultaBanco() throws SQLException {
		connDAO = dao.abreConexao();
		ObservableList<String> lista = FXCollections.observableArrayList();
		
		PreparedStatement stmt = connDAO.prepareStatement("show databases");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()){
			String dbNome = rs.getString(1);
			if (!(dbNome.equals("mysql") || dbNome.equals("information_schema") || dbNome.equals("performance_schema")))
				lista.add(dbNome);
		}
		
		rs.close();
		stmt.close();
		connDAO.close();
		return lista;
	}
}
