package swairlines.dao;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name VooDAO
 */

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
import swairlines.model.Voo;

public class VooDAO implements ConsultasBancoVoo {
	
	/**
	 * insereVoo, insere voo no banco
	 * @param Voo
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean insereVoo(Voo v1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();			
			if(cbd.executar("INSERT INTO sw_airlines.voo (aeronave_numero, origem, destino, quantidadeDePassageiros, rota, horaPartida, horaChegada, dataPartida, dataChegada, tipo_voo, valor, status) " +
					"VALUES('" + v1.getAeronaveNumero() + "','" + v1.getOrigem() +"','" + v1.getDestino() +"','" + v1.getQuantidadeDePassageiros() +"','" + v1.getRota() +"','" + v1.getHoraPartida() +"','" + v1.getHoraChegada() +"','" + v1.getDataPartida() + "','" + v1.getDataChegada() + "','" + v1.getTipoVoo() + "','" + v1.getValor() + "', 'Não Estipulado');")) {
				return true;
			}
			
						
		} catch (SQLException ex) {
			Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
		
	}
	/**
	 * exlui voo no banco
	 * @param Voo
	 * @return boolean
	 * @throws SQLException
	 */
	
	@Override
	public boolean excluiVoo(Voo v1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("DELETE FROM sw_airlines.voo WHERE id= '" + v1.getId() +"';")) {
				return true;
			}
			
						

		} catch (SQLException ex) {
			Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	/**
	 * altera voo no banco
	 * @param Voo
	 * @return boolean
	 * @throws SQLException
	 */
	
	@Override
	public boolean alteraVoo(Voo v1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if(cbd.executar("UPDATE sw_airlines.voo SET aeronave_numero='" + v1.getAeronaveNumero() + "', origem='" + v1.getOrigem() +"', destino='" + v1.getDestino() +"', quantidadeDePassageiros='" + v1.getQuantidadeDePassageiros() + "', rota='" + v1.getRota() +"', horaPartida='" + v1.getHoraPartida() +"', horaChegada='" + v1.getHoraChegada() +"', dataPartida='" + v1.getDataPartida() + "', dataChegada='" + v1.getDataChegada() + "', tipo_voo='" + v1.getTipoVoo() + "', valor='" + v1.getValor() + "' WHERE id='" + v1.getId() +"';")) {
				return true;
			}
			
						

		} catch (SQLException ex) {
			Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	
	/**
	 * insere passageiro no banco
	 * @param Voo
	 * @return boolean
	 * @throws SQLException
	 */
	
	@Override
	public boolean inserirPassageiro(Voo v1) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if (v1.getQuantidadeDePassageiros() == 100) {
				return false;
			} else {
				int quant = v1.getQuantidadeDePassageiros() + 1;
				if (cbd.executar("UPDATE sw_airlines.voo SET quantidadeDePassageiros='" + quant + "' WHERE id='" + v1.getId() +"';")) {
					return true;
				}	
			}
		} catch (SQLException ex) {
			Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	/**
	 * busca voos no banco
	 * @return {@link ObservableList}
	 * @throws SQLException
	 */
	
	@Override
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
				v1.setAeronaveNumero(rs.getString("aeronave_numero"));
				v1.setOrigem(rs.getString("origem"));
				v1.setDestino(rs.getString("destino"));
				v1.setQuantidadeDePassageiros(rs.getInt("quantidadeDePassageiros"));
				v1.setRota(rs.getString("rota"));
				v1.setHoraPartida(rs.getString(("horaPartida")));
				v1.setHoraChegada(rs.getString("horaChegada"));
				v1.setDataPartida(rs.getString("dataPartida"));
				v1.setDataChegada(rs.getString("dataChegada"));
				v1.setTipoVoo(rs.getString("tipo_voo"));
				v1.setValor(rs.getDouble("valor"));
				v1.setStatus(rs.getString("status"));
				
				try {
					if (horaAtual.before(v1.retornaHoraDataPartida()) && !v1.getStatus().equals("Cancelado") && !v1.getStatus().equals("Atrasado")) {
						v1.setStatus("Não iniciado");
					}
					if (horaAtual.after(v1.retornaHoraDataPartida()) && horaAtual.before(v1.retornaHoraDataChegada()) && !v1.getStatus().equals("Cancelado")) {
						v1.setStatus("Em andamento");
					}
					
					if (horaAtual.after(v1.retornaHoraDataChegada()) && !v1.getStatus().equals("Cancelado")) {
						v1.setStatus("Concluido");
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				voos.add(v1);
			}			
			rs.close();
			stm.close();
			con.close();
			return voos;
			
		} catch (SQLException e) {
			Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, e);
			return null;			
		}
		
	}
	
	/**
	 * busca por voo no banco por id
	 * @param int
	 * @return Voo
	 * @throws SQLException
	 */
	
	@Override
	public Voo buscaVooPorId(int id) {		
		Voo v1 = new Voo();	
		Date horaAtual = new Date();
		ConexaoDAO cbd = new ConexaoDAO();		
		try {						
			Connection con = cbd.abreConexao();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM sw_airlines.voo where id ='" + id + "';");
			ResultSet rs = stm.executeQuery();			
			
			while (rs.next()) {	
				v1.setId(rs.getInt("id"));
				v1.setAeronaveNumero(rs.getString("aeronave_numero"));
				v1.setOrigem(rs.getString("origem"));
				v1.setDestino(rs.getString("destino"));
				v1.setQuantidadeDePassageiros(rs.getInt("quantidadeDePassageiros"));
				v1.setRota(rs.getString("rota"));
				v1.setHoraPartida(rs.getString(("horaPartida")));
				v1.setHoraChegada(rs.getString("horaChegada"));
				v1.setDataPartida(rs.getString("dataPartida"));
				v1.setDataChegada(rs.getString("dataChegada"));
				v1.setTipoVoo(rs.getString("tipo_voo"));
				v1.setValor(rs.getDouble("valor"));
				v1.setStatus(rs.getString("status"));
				
				try {
					if (horaAtual.before(v1.retornaHoraDataPartida()) && !v1.getStatus().equals("Cancelado") && !v1.getStatus().equals("Atrasado")) {
						v1.setStatus("Não iniciado");
					}
					if (horaAtual.after(v1.retornaHoraDataPartida()) && horaAtual.before(v1.retornaHoraDataChegada()) && !v1.getStatus().equals("Cancelado")) {
						v1.setStatus("Em andamento");
					}
					
					if (horaAtual.after(v1.retornaHoraDataChegada()) && !v1.getStatus().equals("Cancelado")) {
						v1.setStatus("Concluido");
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}			
			rs.close();
			stm.close();
			con.close();
			return v1;
			
		} catch (SQLException e) {
			Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, e);
			return null;			
		}
		
	}
	
	/**
	 * busca voo não iniciados
	 * @return {@link ObservableList}
	 * @throws SQLException
	 */
	public ObservableList<Voo> buscarVooNaoIniciados(){
		ObservableList<Voo> todosVoos;
		todosVoos = FXCollections.observableArrayList();
		
		for(Voo v: buscaVoos()){
			if(v.getStatus().equals("Não iniciado")){
				todosVoos.add(v);
			}
		}
		return todosVoos;
	}
	
	/**
	 * cancela voo
	 * @param Voo
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean cancelarVoo(Voo voo) {
		ConexaoDAO conexaoDao = new ConexaoDAO();
		try {
			if (conexaoDao.executar("UPDATE sw_airlines.voo SET status='Cancelado' WHERE id='" + voo.getId() + "';")) {
				conexaoDao.executar("DELETE FROM sw_airlines.vendas WHERE id_voo_venda='" + voo.getId() + "';");
				return true;
			}
			
		} catch (SQLException ex) {
			Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}
	
	/**
	 * atrasa voo
	 * @param Voo
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean atrasarVoo(Voo voo) {
		ConexaoDAO conDao = new ConexaoDAO();
		try {
			if (conDao.executar("UPDATE sw_airlines.voo SET status='Atrasado' WHERE id='" + voo.getId() + "';")) {
				return true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	/**
	 * remove passageiro no banco
	 * @param Voo
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean removerPassageiro(Voo voo) {
		try {
			ConexaoDAO cbd = new ConexaoDAO();
			if (voo.getQuantidadeDePassageiros() > 0) {
				int quant = voo.getQuantidadeDePassageiros() - 1;
				if (cbd.executar("UPDATE sw_airlines.voo SET quantidadeDePassageiros='" + quant + "' WHERE id='" + voo.getId() +"';")) {
					return true;
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
			
		}
		return false;
	}
	

}
