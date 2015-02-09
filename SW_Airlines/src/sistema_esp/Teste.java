package sistema_esp;

import sistema_esp.dao.RegraDAO;
import sistema_esp.model.BaseDeRegras;
import sistema_esp.model.Conclusao;
import sistema_esp.model.MemoriaDeFatos;
import sistema_esp.model.MotorDeInferencia;
import sistema_esp.model.Premissa;
import sistema_esp.model.Regra;
import sistema_esp.model.Variavel;

public class Teste {
	public static void main(String[] args) {
	Variavel tempo = new Variavel("Frio");
	Variavel clima = new Variavel("Temperado");
	Variavel clima2 = new Variavel("Tropical");
	Variavel conclusaoV = new Variavel("EUA");
	
	Premissa premissa1 = new Premissa(tempo);
	premissa1.setSimbolo("^");
	Premissa premissa2 = new Premissa(clima);
	premissa2.setSimbolo("|");
	Premissa premissa3 = new Premissa(clima2);
	premissa3.setSimbolo("");
	
	Premissa p4 = new Premissa(clima);
	p4.setSimbolo("^");
	Premissa p5 = new Premissa(clima2);
	
	
	Conclusao conclusao = new Conclusao(conclusaoV);
	conclusao.setSimbolo("");
	
	Conclusao conclusao2 = new Conclusao(conclusaoV);
	conclusao2.setSimbolo("");
	
	Regra regra = new Regra("EUA", conclusao, 90);
	regra.adicionarPremissa(premissa1);
	regra.adicionarPremissa(premissa2);
	regra.adicionarPremissa(premissa3);
	
	Regra r2 = new Regra("EUA", conclusao2, 100);
	r2.adicionarPremissa(p4);
	r2.adicionarPremissa(p5);
	
	MotorDeInferencia m = new MotorDeInferencia();
	MemoriaDeFatos mem = new MemoriaDeFatos();
	BaseDeRegras base = new BaseDeRegras();
	base.adicionarRegra(regra);
	base.adicionarRegra(r2);
	m.setMemoriaDeFatos(mem);
	m.setBaseDeRegras(base);
	System.out.println(m.inferir(conclusao));
	System.out.println(m.inferir(conclusao2));
	System.out.println(mem.toString());
	System.out.println(base.toString());
	
	//RegraDAO r = new RegraDAO();
	//r.insereRegra(regra);
	//System.out.println(r.retornaTodasAsRegrasDoBanco());
	
	}

}

