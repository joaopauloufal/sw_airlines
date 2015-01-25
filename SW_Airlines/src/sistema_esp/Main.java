package sistema_esp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sistema_esp.view.TelaInicio;

public class Main extends Application {
	
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		scene = new Scene(new TelaInicio(this), 800, 600, Color.SILVER);
		primaryStage.setScene(getScene());
		primaryStage.setTitle("SW Airlines 1.6 | Sistema Especialista");
		primaryStage.show();
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
}




//import sistema_esp.model.Conclusao;
//import sistema_esp.model.Premissa;
//import sistema_esp.model.Regra;
//import sistema_esp.model.Variavel;
//
//public class Main {
//	public static void main(String[] args) {
//		Variavel tempo = new Variavel("Frio");
//		Variavel clima = new Variavel("Temperado");
//		Variavel conclusaoV = new Variavel("EUA");
//		
//		Premissa premissa1 = new Premissa(tempo);
//		premissa1.setSimbolo("^");
//		Premissa premissa2 = new Premissa(clima);
//		
//		Conclusao conclusao = new Conclusao(conclusaoV);
//		
//		Regra regra = new Regra(conclusao);
//		regra.adicionarPremissa(premissa1);
//		regra.adicionarPremissa(premissa2);
//		
//		String[] regras = regra.toString().split(" ");
//		Conclusao c = new Conclusao(conclusaoV);
//		
//		Regra r = new Regra(c);
//		Premissa p = null;
///*		
//		for (int i = 0; i < regras.length-2; i++){
//			if (!regras[i].equals("^") || !regras[i].equals("|") || !regras.equals("->")){
//				Variavel v = new Variavel(regras[i]);
//				p = new Premissa(v);
//				r.adicionarPremissa(p);
//				
//			} else {
//				p.setSimbolo(regras[i]);
//			}
//		}
//*/
//		Variavel v = new Variavel(regras[0]);
//		System.out.println(v.getValor());
//		p = new Premissa(v);
//		r.adicionarPremissa(p);
//
//		System.out.println(r);		
//	}
//
//}

