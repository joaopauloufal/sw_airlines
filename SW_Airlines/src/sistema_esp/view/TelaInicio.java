package sistema_esp.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sistema_esp.Main;

public class TelaInicio extends BorderPane {
	private Main mainInstance;
	
	private Label lblEscolhaBanco;
	private ComboBox<String> cbEscolhaBanco;
	
	private Button btAvancar;
	
	public TelaInicio(Main main) {
		setInstance(main);
		configuraTela();
		eventos();
	}
	
	private void eventos() {
		btAvancar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Avançando para a próxima página");
			}
		});
	}

	private void configuraTela (){
		VBox vboxPrincipal = new VBox(10);
		
		lblEscolhaBanco = new Label("Escolha o banco de dados");
		cbEscolhaBanco = new ComboBox<String>();
		
		btAvancar = new Button("Avançar");
		
		vboxPrincipal.getChildren().addAll(
				lblEscolhaBanco,
				cbEscolhaBanco,
				btAvancar
				);
		vboxPrincipal.setAlignment(Pos.CENTER);
		
		setCenter(vboxPrincipal);
	}

	public Main getInstance() {
		return mainInstance;
	}

	public void setInstance(Main instance) {
		this.mainInstance = instance;
	}
}
