package swairlines.modelo;

import javafx.beans.property.SimpleStringProperty;


public class Cliente {

	private SimpleStringProperty nome;
	private SimpleStringProperty cpfCnpj;
	private SimpleStringProperty sexo;
	private SimpleStringProperty rg;
	private SimpleStringProperty dataDeNascimento;
	private SimpleStringProperty estadoCivil;
	private SimpleStringProperty nacionalidade;
	private SimpleStringProperty telefoneCelular;
	private SimpleStringProperty telefoneResidencial;
	private SimpleStringProperty cartaoDeCredito;
	
	public Cliente(String nome, String cpfCnpj, String sexo, String rg, String dataDeNascimento, String estadoCivil, String nacionalidade,
			String telefoneCelular, String telefoneResidencial, String cartaoDeCredito) {
		
		this.nome = new SimpleStringProperty(nome);
		this.cpfCnpj = new SimpleStringProperty(cpfCnpj);
		this.sexo = new SimpleStringProperty(sexo);
		this.rg = new SimpleStringProperty(rg);
		this.dataDeNascimento = new SimpleStringProperty(dataDeNascimento);
		this.estadoCivil = new SimpleStringProperty(estadoCivil);
		this.nacionalidade = new SimpleStringProperty(nacionalidade);
		this.telefoneCelular = new SimpleStringProperty(telefoneCelular);
		this.telefoneResidencial = new SimpleStringProperty(telefoneResidencial);
		this.cartaoDeCredito = new SimpleStringProperty(cartaoDeCredito);
		
	}
	
	

	public String getNome() {
		return nome.get();
	}
	
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	public String getCpfCnpj() {
		return cpfCnpj.get();
	}
	
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj.set(cpfCnpj);;
	}
	
	public String getSexo() {
		return sexo.get();
	}
	
	public void setSexo(String sexo) {
		this.sexo.set(sexo);;
	}
	
	public String getRg() {
		return rg.get();
	}
	
	public void setRg(String rg) {
		this.rg.set(rg);;
	}
	
	public String getDataDeNascimento() {
		return dataDeNascimento.get();
	}
	
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento.set(dataDeNascimento);;
	}
	
	public String getEstadoCivil() {
		return estadoCivil.get();
	}
	
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil.set(estadoCivil);;
	}
	
	public String getNacionalidade() {
		return nacionalidade.get();
	}
	
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade.set(nacionalidade);;
	}
	
	public String getTelefoneCelular() {
		return telefoneCelular.get();
	}
	
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular.set(telefoneCelular);;
	}
	
	public String getTelefoneResidencial() {
		return telefoneResidencial.get();
	}
	
	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial.set(telefoneResidencial);;
	}
	
	public String getCartaoDeCredito() {
		return cartaoDeCredito.get();
	}
	
	public void setCartaoDeCredito(String cartaoDeCredito) {
		this.cartaoDeCredito.set(cartaoDeCredito);
	}

	
	
	

}