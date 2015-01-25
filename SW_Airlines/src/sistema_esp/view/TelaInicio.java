package sistema_esp.view;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sistema_esp.Main;
import sistema_esp.controller.TelaInicioController;

public class TelaInicio extends BorderPane {
	private Main mainInstance;
	private TelaInicioController controller;
	
	private Label lblEscolhaBanco;
	private ComboBox<String> cbEscolhaBanco;
	
	private Button btAvancar;
	
	public TelaInicio(Main main) {
		setMainInstance(main);
		setController(new TelaInicioController(this));
		configuraTela();
		eventos();
	}
	
	private void eventos() {
		getBtAvancar().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.actionBtAvancar();
			}
		});
	}

	private void configuraTela (){
		VBox vboxPrincipal = new VBox(10);
		
		lblEscolhaBanco = new Label("Escolha o banco de dados");
		try {
			cbEscolhaBanco = new ComboBox<String>(controller.retornaBancosDeDados());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBtAvancar(new Button("Avançar"));
		
		vboxPrincipal.getChildren().addAll(
				lblEscolhaBanco,
				cbEscolhaBanco,
				getBtAvancar()
				);
		vboxPrincipal.setAlignment(Pos.CENTER);
		
		setCenter(vboxPrincipal);
	}

	public Main getMainInstance() {
		return mainInstance;
	}

	public void setMainInstance(Main instance) {
		this.mainInstance = instance;
	}

	public ComboBox<String> getCbEscolhaBanco() {
		return cbEscolhaBanco;
	}

	public void setCbEscolhaBanco(ComboBox<String> cbEscolhaBanco) {
		this.cbEscolhaBanco = cbEscolhaBanco;
	}

	public TelaInicioController getController() {
		return controller;
	}

	public void setController(TelaInicioController controller) {
		this.controller = controller;
	}

	public Button getBtAvancar() {
		return btAvancar;
	}

	public void setBtAvancar(Button btAvancar) {
		this.btAvancar = btAvancar;
	}
}
