package sistema_esp.model;

import java.util.ArrayList;

import sistema_esp.model.Conclusao;
import sistema_esp.model.Premissa;

public class Regra {
	
	private String nome;
	private ArrayList<Premissa> premissas;
	private Conclusao conclusao;
	private int Id;

	public Regra(String nome, Conclusao conclusao) {
		this.premissas = new ArrayList<Premissa>();
		this.conclusao = conclusao;
		this.nome = nome;
	}
	
	public Regra(){
		this.premissas = new ArrayList<Premissa>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public ArrayList<Premissa> getPremissas() {
		return premissas;
	}

	public void setPremissas(ArrayList<Premissa> premissas) {
		this.premissas = premissas;
	}

	public Conclusao getConclusao() {
		return conclusao;
	}

	public void setConclusao(Conclusao conclusao) {
		this.conclusao = conclusao;
	}
	
	public void adicionarPremissa(Premissa premissa){
		this.premissas.add(premissa);
	}

	@Override
	public String toString() {
		return premissas.toString().replace("[", "").
				replace("]", "").
				replace(" ", "").
				replace(",", "");
				
				
	}
	
	
	

}
