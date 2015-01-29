package sistema_esp;

import sistema_esp.model.BaseDeRegras;
import sistema_esp.model.Conclusao;
import sistema_esp.model.MemoriaDeFatos;
import sistema_esp.model.MotorDeInferencia;
import sistema_esp.model.Premissa;
import sistema_esp.model.Regra;
import sistema_esp.model.Variavel;

public class Main {

	public static void main(String[] args) {
		Variavel a = new Variavel("A");
		Variavel b = new Variavel("B");
		Variavel c = new Variavel("C");
		Variavel d = new Variavel("D");
		Variavel e = new Variavel("E");
		Variavel f = new Variavel("F");
		Variavel g = new Variavel("G");
		Variavel h = new Variavel("H");
		
		Premissa premissa1 = new Premissa(a);
		premissa1.setSimbolo("");
		Premissa premissa2 = new Premissa(b);
		premissa2.setSimbolo("^");
		Premissa premissa3 = new Premissa(c);
		premissa3.setSimbolo("");
		Premissa premissa4 = new Premissa(d);
		premissa4.setSimbolo("|");
		Premissa premissa5 = new Premissa(e);
		premissa5.setSimbolo("");
		Premissa premissa6 = new Premissa(f);
		premissa6.setSimbolo("");
		Premissa premissa7 = new Premissa(g);
		
		Conclusao cB = new Conclusao(b);
		cB.setSimbolo("");
		
		Conclusao cC = new Conclusao(c);
		cC.setSimbolo("");
		
		Conclusao cD = new Conclusao(d);
		cD.setSimbolo("");
		
		Conclusao cE = new Conclusao(e);
		cE.setSimbolo("");
		
		Conclusao cF = new Conclusao(f);
		cF.setSimbolo("");

		Conclusao cH = new Conclusao(h);
		cF.setSimbolo("");
		cH.setValorLogico(false);
		
		Regra r1 = new Regra(cB);
		r1.adicionarPremissa(premissa1);
		
		Regra r2 = new Regra(cC);
		r2.adicionarPremissa(premissa1);
		
		Regra r3 = new Regra(cD);
		r3.adicionarPremissa(premissa2);
		r3.adicionarPremissa(premissa3);
		
		Regra r4 = new Regra(cE);
		r4.adicionarPremissa(premissa4);
		r4.adicionarPremissa(premissa1);
		
		Regra r5 = new Regra(cF);
		r5.adicionarPremissa(premissa2);
		
		Regra r7 = new Regra(cH);
		r7.adicionarPremissa(premissa7);
		
		
		
		
	
//		String[] regras = regra.toString().split("#");
//		Conclusao c = new Conclusao(conclusaoV);
//		
//		Regra r = new Regra(c);
//		Premissa p = null;
/*		
		for (int i = 0; i < regras.length-2; i++){
			if (!regras[i].equals("^") || !regras[i].equals("|") || !regras.equals("->")){
				Variavel v = new Variavel(regras[i]);
				p = new Premissa(v);
				r.adicionarPremissa(p);
				
			} else {
				p.setSimbolo(regras[i]);
				
				
				
				
			}
		}
*/
	
		MemoriaDeFatos mem = new MemoriaDeFatos();
		mem.adicionarFato(cH);
		BaseDeRegras base = new BaseDeRegras();
		base.adicionarRegra(r1);
		base.adicionarRegra(r2);
		base.adicionarRegra(r3);
		base.adicionarRegra(r4);
		base.adicionarRegra(r5);
		base.adicionarRegra(r7);
		MotorDeInferencia m = new MotorDeInferencia(mem, base);
		System.out.println(m.inferir(cD));
		
		
		
		
		
		
	}

}


