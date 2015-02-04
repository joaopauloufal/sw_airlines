package sistema_esp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sistema_esp.model.Conclusao;
import sistema_esp.model.Premissa;
import sistema_esp.model.Regra;
import sistema_esp.model.Variavel;

public class RegraDAO {
	
	public void insereRegra(Regra regra){
		try {
		ConexaoDAO cbd = new ConexaoDAO();
		cbd.executar("INSERT INTO sist_esp.regras (nome, premissas, conclusao) " + "VALUES('" + regra.getNome() +"','"+ regra + "','" + regra.getConclusao() + "');");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void excluirRegra(Regra regra){
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			cbd.executar("DELETE FROM sist_esp.regras WHERE id= '" + regra.getId() + "';");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void alteraRegra(Regra regra){
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			cbd.executar("UPDATE sist_esp.regras set nome='" + regra.getNome() + "', premissas='" + regra.getPremissas() + "', conclusao='" + regra.getConclusao() + "' WHERE id= '" + regra.getId() +"';");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public ObservableList<Regra> retornaTodasAsRegrasDoBanco(){
		ObservableList<Regra> regras;
		regras = FXCollections.observableArrayList();
		
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			Connection con = cbd.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sist_esp.regras;");
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				Regra regra = new Regra();
				regra.setId(rs.getInt("id"));
				regra.setNome(rs.getString("nome"));
				String[] premissaArray = rs.getString("premissas").split("#");
				Premissa premissa = null;
				if (premissaArray.length > 1){
					for (int i = 0; i < premissaArray.length; i++){
						if (!premissaArray[i].equals("^") || !premissaArray[i].equals("|")){
							Variavel variavel = new Variavel(premissaArray[i]);
							premissa = new Premissa(variavel);
							regra.adicionarPremissa(premissa);
						} else {
							premissa.setSimbolo(premissaArray[i]);
						}
					}
				} else {
					Variavel variavel = new Variavel(rs.getString("premissas"));
					premissa = new Premissa(variavel);
					regra.adicionarPremissa(premissa);
				}
				Variavel conclusaoV = new Variavel(rs.getString("conclusao"));
				Conclusao conclusao = new Conclusao(conclusaoV);
				regra.setConclusao(conclusao);
				regras.add(regra);
		
			}
			rs.close();
			stm.close();
			con.close();
			return regras;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	

}
