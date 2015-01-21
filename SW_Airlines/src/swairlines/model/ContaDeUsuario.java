package swairlines.model;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name ContaDeUsuario
 */
import swairlines.dao.ContaDeUsuarioDAO;

public class ContaDeUsuario {
	
	private String login;
	private String senha;
	private String tipoConta;
	private String cpfFuncionario;
	private Funcionario funcionario;
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

	public String getCpfFuncionario(){		
		return cpfFuncionario;
	}

	public void setCpfFuncionario(String cpfFuncionario){
		this.cpfFuncionario = cpfFuncionario;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getLogin(){
		
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
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public boolean autenticar(ContaDeUsuario c1){
		ContaDeUsuarioDAO contaDao = new ContaDeUsuarioDAO();
		for (ContaDeUsuario temp : contaDao.buscaContasDeUsuario()) {
			if (temp.getLogin().equals(c1.getLogin()) && temp.getSenha().equals(c1.getSenha())) {
				if (temp.getTipoConta().equals(ContaDeUsuario.TIPO_CONTA_ADMIN)) {
					Gerente gerente = new Gerente();
					c1.setFuncionario(gerente);
					gerente.setConta(temp);
					return true;
				} else if (temp.getTipoConta().equals(ContaDeUsuario.TIPO_CONTA_OPERADOR)){
					Operador operador = new Operador();
					operador.setConta(temp);
					c1.setFuncionario(operador);
					return true;
				}												
			}
		}
		return false;
		
	}
	
	

}
