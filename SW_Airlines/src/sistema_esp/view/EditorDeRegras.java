package sistema_esp.view;

import java.util.ArrayList;

import sistema_esp.Main;
import sistema_esp.controller.EditorDeRegrasController;
import sistema_esp.model.Variavel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EditorDeRegras extends BorderPane {
	
	private Main mainInstance;
	private EditorDeRegrasController controller;
	
	private GridPane grid;
	// TODO Revisar Array de regras (Interface)
	private ArrayList<ModelViewRegra> regras;
	
	class ModelViewRegra{
		public Label label;
		public ComboBox<Variavel> cbVariavel;
		
		public ModelViewRegra () {
			// TODO definir componentes de interface para a tela principal
		}
	}
	
	public EditorDeRegras (Main instance) {
		setMainInstance(instance);
		setController(new EditorDeRegrasController(this));
		configuraTela();
		eventos();
	}
	
	private void configuraTela () {
		grid = new GridPane();
	}
	
	private void eventos () {
		// TODO Eventos
	}

	public Main getMainInstance() {
		return mainInstance;
	}

	public void setMainInstance(Main mainInstance) {
		this.mainInstance = mainInstance;
	}

	public EditorDeRegrasController getController() {
		return controller;
	}

	public void setController(EditorDeRegrasController controller) {
		this.controller = controller;
	}
}
