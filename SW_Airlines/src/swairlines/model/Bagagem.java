package swairlines.model;

public class Bagagem {
	
	private String cpfCnpjCliente;
	private String nomeCliente;
	private int vooId;
	private String origemVoo;
	private String destinoVoo;
	private double pesoBagagem;
	private double precoTotalBagagem;
	
	public Bagagem (String cpfCnpjCliente, String nomeCliente, int vooId, String origemVoo, String destinoVoo, double pesoBagagem, double precoTotalBagagem) {
		this.cpfCnpjCliente = cpfCnpjCliente;
		this.nomeCliente = nomeCliente;
		this.vooId = vooId;
		this.origemVoo = origemVoo;
		this.destinoVoo = destinoVoo;
		this.pesoBagagem = pesoBagagem;
		this.precoTotalBagagem = precoTotalBagagem;
	}
	
	public Bagagem() {
		
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public int getVooId() {
		return vooId;
	}

	public void setVooId(int vooId) {
		this.vooId = vooId;
	}

	public String getOrigemVoo() {
		return origemVoo;
	}

	public void setOrigemVoo(String origemVoo) {
		this.origemVoo = origemVoo;
	}

	public String getDestinoVoo() {
		return destinoVoo;
	}

	public void setDestinoVoo(String destinoVoo) {
		this.destinoVoo = destinoVoo;
	}

	public double getPesoBagagem() {
		return pesoBagagem;
	}

	public void setPesoBagagem(double pesoBagagem) {
		this.pesoBagagem = pesoBagagem;
	}

	public double getPrecoTotalBagagem() {
		return precoTotalBagagem;
	}

	public void setPrecoTotalBagagem(double precoTotalBagagem) {
		this.precoTotalBagagem = precoTotalBagagem;
	}
	
	

}
