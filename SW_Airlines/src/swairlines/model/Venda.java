package swairlines.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda {
	
	private static final String TIPO_VENDA_A_VISTA = "À Vista";
	private static final String TIPO_VENDA_CARTAO = "Cartão";
	private String tipoVenda;
	private Voo voo;
	private Cliente cliente;
	private String dataVenda;
	
	public Venda (String tipoVenda, Voo voo, Cliente cliente) {
		Date dataHoraAtual = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyy HH:mm");
		if (tipoVenda.equals(Venda.TIPO_VENDA_A_VISTA)) {
			this.tipoVenda = Venda.TIPO_VENDA_A_VISTA;
		} else if (tipoVenda.equals(Venda.TIPO_VENDA_CARTAO)) {
			this.tipoVenda = Venda.TIPO_VENDA_CARTAO;
		}
		this.voo = voo;
		this.cliente = cliente;
		this.dataVenda = sd.format(dataHoraAtual);
		
	}
	
	public Venda() {
		
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
