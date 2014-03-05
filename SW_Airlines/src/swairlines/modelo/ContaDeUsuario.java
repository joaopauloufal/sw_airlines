package swairlines.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import swairlines.Main;
import swairlines.bd.ConexaoBD;
import swairlines.gui.TelaCadConta;
import swairlines.gui.TelaPrincipal;

public class ContaDeUsuario {
	
	private String login;
	private String senha;
	private String tipoConta;
	private String cpfFuncionario;
	public static final String TIPO_CONTA_ADMIN = "Administrador";
	public static final String TIPO_CONTA_OPERADOR = "Operador";
	
	public ContaDeUsuario() {
		
	}
	
	public ContaDeUsuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
		
	}

	public ContaDeUsuario(String login, String senha, String tipoConta, String cpfFuncionario) {
		this.login = login;
		this.senha = senha;
		this.tipoConta = tipoConta;
		this.cpfFuncionario = cpfFuncionario;
	}
	
	@Override
	public boolean equals(Object obj) {  
        if (obj == null) return false;  
        if (obj.getClass() != this.getClass()) return false;  
        return this.senha.equalsIgnoreCase(senha);  
    }

	public String getCpfFuncionario() {
		return cpfFuncionario;
	}

	public void setCpfFuncionario(String cpfFuncionario) {
		this.cpfFuncionario = cpfFuncionario;
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
			PreparedStatement stm = con.prepareStatement("SELECT login, senha, tipo_conta, cpf_func FROM sw_airlines.funcionario, sw_airlines.usuario WHERE cpf = cpf_func;");
			//Armazena o valor da pesquisa e no rs
			ResultSet rs = stm.executeQuery();
			//Com o while ele vai rodar linha por linha sendo o parâmetro o rs.next(), que retorna V ou F se a tabela tiver ou não linhas.
			while (rs.next()){
				ContaDeUsuario u = new ContaDeUsuario();
				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setTipoConta(rs.getString("tipo_conta"));
				u.setCpfFuncionario(rs.getString("cpf_func"));
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
	
	public boolean autenticar(ContaDeUsuario c1) {
		for (ContaDeUsuario temp : c1.buscaContasDeUsuario()) {
			if (temp.getLogin().equals(c1.getLogin()) && temp.getSenha().equals(c1.getSenha())) {
				if (temp.getTipoConta().equals(ContaDeUsuario.TIPO_CONTA_ADMIN)) {
					Gerente gerente = new Gerente();
					gerente.setConta(temp);
					Main.alterarTela(new TelaPrincipal(gerente));
					return true;
				} else if (temp.getTipoConta().equals(ContaDeUsuario.TIPO_CONTA_OPERADOR)){
					Operador operador = new Operador();
					operador.setConta(temp);
					Main.alterarTela(new TelaPrincipal(operador));
					return true;
				}												
			}
		}
		return false;
		
	}
	
	

}
