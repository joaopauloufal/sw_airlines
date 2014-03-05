package swairlines.gui;

import swairlines.modelo.Funcionario;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TelaPrincipal extends BorderPane {
	
	public TelaPrincipal(Funcionario f) {		
		VBox menuTopo = new VBox(30);				
		MenuBarPrincipal menuBarPrincipal = new MenuBarPrincipal(f);
		menuTopo.getChildren().add(menuBarPrincipal);				
		setTop(menuTopo);
		
	}

}
