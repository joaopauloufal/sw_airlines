package swairlines.model;

public class Bagagem {
	
	private String cpfCnpjCliente;
	private String nomeCliente;
	private int vooId;
	private String origemVoo;
	private String destinoVoo;
	private double pesoBagagem;
	private double taxa;
	
	public Bagagem (String cpfCnpjCliente, String nomeCliente, int vooId, String origemVoo, String destinoVoo, double pesoBagagem) {
		this.cpfCnpjCliente = cpfCnpjCliente;
		this.nomeCliente = nomeCliente;
		this.vooId = vooId;
		this.origemVoo = origemVoo;
		this.destinoVoo = destinoVoo;
		this.pesoBagagem = pesoBagagem;
	}
	
	public Bagagem() {
		
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
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
	
	

}
