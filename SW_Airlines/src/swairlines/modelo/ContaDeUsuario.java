package swairlines.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.bd.ConexaoBD;
import swairlines.gui.TelaCadConta;

public class ContaDeUsuario {
	
	private String login;
	private String senha;
	private String tipoConta;
	public static final String TIPO_CONTA_ADMIN = "Administrador";
	public static final String TIPO_CONTA_OPERADOR = "Operador";
	
	public ContaDeUsuario() {
		
	}	


	public ContaDeUsuario(String login, String senha, String tipoConta) {
		this.login = login;
		this.senha = senha;
		if (tipoConta.equals(TIPO_CONTA_ADMIN)){
			this.tipoConta = ContaDeUsuario.TIPO_CONTA_ADMIN;
		} else if (tipoConta.equals(TIPO_CONTA_OPERADOR)) {
			this.tipoConta = ContaDeUsuario.TIPO_CONTA_OPERADOR;
		}
	}
	
	@Override
	public boolean equals(Object obj) {  
        if (obj == null) return false;  
        if (obj.getClass() != this.getClass()) return false;  
        return this.senha.equalsIgnoreCase(senha);  
    }

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public ObservableList<ContaDeUsuario> buscaContasDeUsuario() {
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
	
	

}
