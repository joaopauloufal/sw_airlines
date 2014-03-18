package swairlines.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda {

	private String tipoVenda;
	private String dataVenda;
	private int parcelas;
	private int idVoo;
	private double valorParcela;
	private double valorVoo;
	private String origemVoo;
	private String destinoVoo;
	private String nomeCliente;
	private String cpfCnpjCliente;
	private String cartaoCreditoCliente;
	
	public Venda (String tipoVenda, int idVoo, String origemVoo, String destinoVoo, String nomeCliente, String cpfcnpjCliente, int parcelas, double valorParcela, double ValorVoo, String cartaoCreditoCliente) {
		Date dataHoraAtual = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyy HH:mm");
		this.tipoVenda = tipoVenda;
		this.dataVenda = sd.format(dataHoraAtual);
		this.parcelas = parcelas;
		this.valorParcela = valorParcela;
		this.valorVoo = ValorVoo;
		this.origemVoo = origemVoo;
		this.destinoVoo = destinoVoo;
		this.nomeCliente = nomeCliente;
		this.cpfCnpjCliente = cpfcnpjCliente;
		this.cartaoCreditoCliente = cartaoCreditoCliente;
		this.idVoo = idVoo;
		
	}
	
	public Venda (String tipoVenda, int idVoo, String origemVoo, String destinoVoo, Double valorVoo, String nomeCliente, String cpfcnpjCliente, String cartaoCreditoCliente) {
		Date dataHoraAtual = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyy HH:mm");
		this.tipoVenda = tipoVenda;
		this.dataVenda = sd.format(dataHoraAtual);
		this.origemVoo = origemVoo;
		this.destinoVoo = destinoVoo;
		this.valorVoo = valorVoo;
		this.nomeCliente = nomeCliente;
		this.cpfCnpjCliente = cpfcnpjCliente;
		this.idVoo = idVoo;
		this.parcelas = 1;
		this.cartaoCreditoCliente = cartaoCreditoCliente;
	}
	
	public Venda() {
		
	}

	public double getValorVoo() {
		return valorVoo;
	}

	public void setValorVoo(double valorVoo) {
		this.valorVoo = valorVoo;
	}

	public int getIdVoo() {
		return idVoo;
	}

	public void setIdVoo(int idVoo) {
		this.idVoo = idVoo;
	}

	public String getCartaoCreditoCliente() {
		return cartaoCreditoCliente;
	}

	public void setCartaoCreditoCliente(String cartaoCreditoCliente) {
		this.cartaoCreditoCliente = cartaoCreditoCliente;
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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String cpfcnpjCliente) {
		this.cpfCnpjCliente = cpfcnpjCliente;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}


	public String getTipoVenda() {
		return tipoVenda;
	}

	public void setTipoVenda(String tipoVenda) {
		this.tipoVenda = tipoVenda;
	}

}