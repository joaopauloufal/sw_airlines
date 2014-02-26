package swairlines.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.gui.TelaCadConta;
import swairlines.modelo.ContaDeUsuario;

public class UsuarioBD {

	public boolean insere(ContaDeUsuario c1){
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("insert into sw_airlines.usuario (login, senha, tipo_conta) values('" + c1.getLogin() +"','" + c1.getSenha() +"','" + c1.getTipoConta() +"');");
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	public void altera(ContaDeUsuario c1){

		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("update sw_airlines.usuario set senha='" + c1.getSenha() + "' where login='" + c1.getSenha() +"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	public void exclui(ContaDeUsuario c1){
		try {
			ConexaoBD cbd = new ConexaoBD();
			cbd.executar("delete from sw_airlines.usuario where login = '" + c1.getLogin() +"';");
		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public ObservableList<ContaDeUsuario> busca(){
		ObservableList<ContaDeUsuario> usuarios;
		usuarios = FXCollections.observableArrayList();
		try {
			ConexaoBD cbd = new ConexaoBD();
			//Abre a conexão com o banco de dados
			Connection con = cbd.abreConexao();
			//Cria um statement para realizar comandos no BD
			PreparedStatement stm = con.prepareStatement("select * from sw_airlines.usuario;");
			//Armazena o valor da pesquisa e no rs
			ResultSet rs = stm.executeQuery();
			//Com o while ele vai rodar linha por linha sendo o parâmetro o rs.next(), que retorna V ou F se a tabela tiver ou não linhas.
			while (rs.next()){
				ContaDeUsuario u = new ContaDeUsuario();
				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setTipoConta("tipo_conta");
				//Adiciona o objeto a (usuario) a lista usuarios, usando o método add
				usuarios.add(u);
			}
			rs.close();
			stm.close();
			con.close();
			//O retorno pode ser tanto dentro do try-catch sendo return usuario e return null ou fora e assim só sendo preciso um return
			return usuarios;

		} catch (SQLException ex) {
			Logger.getLogger(TelaCadConta.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		} 
	}
	
	public boolean validarUsuarioBanco(ContaDeUsuario c1) {
		for (ContaDeUsuario temp : busca()) {
			if (temp.getLogin().equals(c1.getLogin()) && temp.getSenha().equals(c1.getSenha())){
				return true;
				
			}
		}		
		return false;
	}
		

}
