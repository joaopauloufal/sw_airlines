package swairlines.model;

public class Endereco {
	
	private String rua;
	private String cidade;
	private String bairro;
	private String numero;
	private String estado;	
	
	
	public Endereco(String rua, String cidade, String bairro, String numero, String estado) {
		this.rua = rua;
		this.cidade = cidade;
		this.bairro = bairro;
		this.numero = numero;
		this.estado = estado;
	}
	
	public Endereco() {
		
	}

	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
