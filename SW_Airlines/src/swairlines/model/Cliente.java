package swairlines.model;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name Cliente
 */

public class Cliente {

	private String nome;
	private String cpfCnpj;
	private String sexo;
	private String rg;
	private String dataDeNascimento;
	private String estadoCivil;
	private String nacionalidade;
	private String telefoneCelular;
	private String telefoneResidencial;
	private String cartaoDeCredito;
	private String passaporteNumero;
	private Endereco endereco;
	
	public Cliente(String nome, String cpfCnpj, String sexo, String rg, String dataDeNascimento, String estadoCivil, String nacionalidade,
			String telefoneCelular, String telefoneResidencial, String cartaoDeCredito, String passaporteNumero, Endereco endereco) {
		
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.sexo = sexo;
		this.rg = rg;
		this.dataDeNascimento = dataDeNascimento;
		this.estadoCivil = estadoCivil;
		this.nacionalidade = nacionalidade;
		this.telefoneCelular = telefoneCelular;
		this.telefoneResidencial = telefoneResidencial;
		this.cartaoDeCredito = cartaoDeCredito;
		this.passaporteNumero = passaporteNumero;
		this.endereco = endereco;		
		
	}
	
	
	public Cliente() {
		
	}


	public String getPassaporteNumero() {
		return passaporteNumero;
	}


	public void setPassaporteNumero(String passaporteNumero) {
		this.passaporteNumero = passaporteNumero;
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
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
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
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
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
	
	public String getCartaoDeCredito() {
		return cartaoDeCredito;
	}
	
	public void setCartaoDeCredito(String cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}
	
	

}