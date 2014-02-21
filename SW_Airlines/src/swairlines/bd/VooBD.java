package swairlines.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.gui.TelaCadVoo;
import swairlines.gui.TelaEditVoo;
import swairlines.gui.TelaTabelaVoos;
import swairlines.modelo.Voo;

public class VooBD {

	public boolean insere(Voo v1){
		try {
			ConexaoBD cbd = new ConexaoBD();			
			cbd.executar("insert into sw_airlines.voo (origem, destino, quantidadeDePassageiros, rota, horaPartida, horaChegada, dataPartida, dataChegada, tipo_voo) " +
					"values('" + v1.getOrigem() +"','" + v1.getDestino() +"','" + v1.getQuantidadeDePassageiros() +"','" + v1.getRota() +"','" + v1.getHoraPartida() +"','" + v1.getHoraChegada() +"','" + v1.getDataPartida() + "','" + v1.getDataChegada() + "','" + v1.getTipoVoo() + "');");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadVoo.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	public boolean altera(Voo v1){
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("update sw_airlines.voo set origem='" + v1.getOrigem() +"', destino='" + v1.getDestino() + "', rota='" + v1.getRota() +"', horaPartida='" + v1.getHoraPartida() +"', horaChegada='" + v1.getHoraChegada() +"', dataPartida='" + v1.getDataPartida() + "', dataChegada='" + v1.getDataChegada() + "', tipo_voo='" + v1.getTipoVoo() + "' where id='" + v1.getId() +"';");
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(TelaEditVoo.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}


	}
	public void exclui(Voo v1){

		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("delete from sw_airlines.voo where id = '" + v1.getId() +"';");

		} catch (SQLException ex) {
			Logger.getLogger(TelaCadVoo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public ObservableList<Voo> busca() {
		ObservableList<Voo> voos;
		voos = FXCollections.observableArrayList();
		Date horaAtual = new Date();		
		
		try {			
			ConexaoBD cbd = new ConexaoBD();
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
			
		} catch(SQLException e) {
			Logger.getLogger(TelaTabelaVoos.class.getName()).log(Level.SEVERE, null, e);
			return null;
			
		}
		

	}
	
	
	
	


}
