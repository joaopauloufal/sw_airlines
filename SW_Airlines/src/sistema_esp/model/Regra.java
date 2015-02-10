package sistema_esp.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sistema_esp.model.Conclusao;
import sistema_esp.model.Premissa;

public class Regra {
	
	private String nome;
	private ObservableList<Premissa> premissas;
	private Conclusao conclusao;
	private double fatorDeConfianca;
	private int Id;

	public Regra(String nome, Conclusao conclusao, double fatorDeConfianca) {
		this.premissas = FXCollections.observableArrayList();
		this.conclusao = conclusao;
		this.nome = nome;
		this.fatorDeConfianca = fatorDeConfianca;
		this.conclusao.setFatorCerteza(this.fatorDeConfianca);
	}
	
	public Regra(){
		this.premissas = FXCollections.observableArrayList();
	}
	
	public double getFatorDeConfianca() {
		return fatorDeConfianca;
	}

	public void setFatorDeConfianca(double fatorDeConfianca) {
		this.fatorDeConfianca = fatorDeConfianca;
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

	public ObservableList<Premissa> getPremissas() {
		return premissas;
	}

	public void setPremissas(ObservableList<Premissa> premissas) {
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
				replace(", ", "");
				
				
	}
	
	
	

}
