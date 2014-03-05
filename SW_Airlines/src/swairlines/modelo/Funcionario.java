package swairlines.modelo;

public abstract class Funcionario {
	
	private String nome;
	private String sexo;
	private String cpf;
	private String rg;
	private String dataDeNascimento;
	private String telefoneCelular;
	private String telefoneResidencial;
	private String nacionalidade;
	private String estadoCivil;
	private ContaDeUsuario conta = new ContaDeUsuario();
	private String cargo;
	private Endereco endereco = new Endereco();

	public Funcionario(String nome, String sexo, String cpf, String rg,
			String dataDeNascimento, String telefoneCelular,
			String cargo, String telefoneResidencial, String nacionalidade, String estadoCivil, Endereco endereco) {
		
		this.nome = nome;
		this.sexo = sexo;
		this.cpf = cpf;
		this.rg = rg;
		this.dataDeNascimento = dataDeNascimento;
		this.telefoneCelular = telefoneCelular;
		this.telefoneResidencial = telefoneResidencial;
		this.cargo = cargo;
		this.nacionalidade = nacionalidade;
		this.estadoCivil = estadoCivil;
		this.endereco = endereco;
		
		
	}
	
	public Funcionario() {
		
	}
	

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	
	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}
	
	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public ContaDeUsuario getConta() {
		return conta;
	}

	public void setConta(ContaDeUsuario conta) {
		this.conta = conta;
	}
		

}
