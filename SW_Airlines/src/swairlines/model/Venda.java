package swairlines.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda {

	private String tipoVenda;
	private Voo voo;
	private Cliente cliente;
	private String dataVenda;
	private int parcelas;
	private double valorParcela;
	
	public Venda (String tipoVenda, Voo voo, Cliente cliente, int parcelas, double valorParcela) {
		Date dataHoraAtual = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyy HH:mm");
		this.tipoVenda = tipoVenda;
		this.voo = voo;
		this.cliente = cliente;
		this.dataVenda = sd.format(dataHoraAtual);
		this.parcelas = parcelas;
		this.valorParcela = valorParcela;
		
	}
	
	public Venda(String tipoVenda, Voo voo, Cliente cliente) {
		Date dataHoraAtual = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyy HH:mm");
		this.tipoVenda = tipoVenda;
		this.voo = voo;
		this.cliente = cliente;
		this.dataVenda = sd.format(dataHoraAtual);
	}
	
	public Venda() {
		
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

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
