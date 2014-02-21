package swairlines.modelo;

public class ContaDeUsuario {
	
	private String login;
	private String senha;
	private String tipoConta;
	
	public ContaDeUsuario() {
		
	}	


	public ContaDeUsuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}


	public ContaDeUsuario(String login, String senha, String tipoConta) {
		this.login = login;
		this.senha = senha;
		this.tipoConta = tipoConta;
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
	
	

}
