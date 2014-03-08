package swairlines.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.model.Gerente;
import swairlines.model.Voo;

public class VooDAO {	
	
	public boolean insereVoo(Voo v1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();			
			if(cbd.executar("INSERT INTO sw_airlines.voo (origem, destino, quantidadeDePassageiros, rota, horaPartida, horaChegada, dataPartida, dataChegada, tipo_voo) " +
					"VALUES('" + v1.getOrigem() +"','" + v1.getDestino() +"','" + v1.getQuantidadeDePassageiros() +"','" + v1.getRota() +"','" + v1.getHoraPartida() +"','" + v1.getHoraChegada() +"','" + v1.getDataPartida() + "','" + v1.getDataChegada() + "','" + v1.getTipoVoo() + "');")) {
				return true;
			}			
		} catch (SQLException ex) {
			Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
		
	}
	
	public boolean excluiVoo(Voo v1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if (cbd.executar("DELETE FROM sw_airlines.voo WHERE id= '" + v1.getId() +"';")) {
				return true;
			}			

		} catch (SQLException ex) {
			Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	public boolean alteraVoo(Voo v1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if (cbd.executar("UPDATE sw_airlines.voo SET origem='" + v1.getOrigem() +"', destino='" + v1.getDestino() + "', rota='" + v1.getRota() +"', horaPartida='" + v1.getHoraPartida() +"', horaChegada='" + v1.getHoraChegada() +"', dataPartida='" + v1.getDataPartida() + "', dataChegada='" + v1.getDataChegada() + "', tipo_voo='" + v1.getTipoVoo() + "' WHERE id='" + v1.getId() +"';")) {
				return true;
			}			

		} catch (SQLException ex) {
			Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	public ObservableList<Voo> buscaVoos() {
		ObservableList<Voo> voos;
		voos = FXCollections.observableArrayList();
		Date horaAtual = new Date();
		ConexaoDAO cbd = new ConexaoDAO();		
		try {						
			Connection con = cbd.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.voo;");
			ResultSet rs = stm.executeQuery();			
			
			while (rs.next()) {	
				Voo v1 = new Voo();	
				v1.setId(rs.getInt("id"));
				v1.setOrigem(rs.getString("origem"));
				v1.setDestino(rs.getString("destino"));
				v1.setQuantidadeDePassageiros(rs.getInt("quantidadeDePassageiros"));
				v1.setRota(rs.getString("rota"));
				v1.setHoraPartida(rs.getString(("horaPartida")));
				v1.setHoraChegada(rs.getString("horaChegada"));
				v1.setDataPartida(rs.getString("dataPartida"));
				v1.setDataChegada(rs.getString("dataChegada"));
				v1.setTipoVoo(rs.getString("tipo_voo"));		
				voos.add(v1);
				
				try {
					if (horaAtual.before(v1.retornaHoraDataPartida())) {
						v1.setStatus("Não iniciado");
					}
					if (horaAtual.after(v1.retornaHoraDataPartida()) && horaAtual.before(v1.retornaHoraDataChegada())) {
						v1.setStatus("Em andamento");
					}
					
					if (horaAtual.after(v1.retornaHoraDataChegada())) {
						v1.setStatus("Concluido");
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}			
			rs.close();
			stm.close();
			con.close();
			return voos;
			
		} catch (SQLException e) {
			Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, e);
			return null;			
		}
		
	}
	

}
