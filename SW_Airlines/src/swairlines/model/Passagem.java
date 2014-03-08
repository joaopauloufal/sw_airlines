package swairlines.model;

import java.util.Date;

public class Passagem {
	
	private int id;
	private float preco;
	private Voo voo;
	private Date dataDeValidade;

	
	public Passagem(int id, float preco, Voo voo, Date dataDeValidade) {
		super();
		this.id = id;
		this.preco = preco;
		this.voo = voo;
		this.dataDeValidade = dataDeValidade;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public Voo getVoo() {
		return voo;
	}
	
	public void setVoo(Voo voo) {
		this.voo = voo;
	}
	
	public Date getDataDeValidade() {
		return dataDeValidade;
	}
	
	public void setDataDeValidade(Date dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}
	
	

}
