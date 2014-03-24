package swairlines.view;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name TelaPrincipal
 */

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import swairlines.model.Funcionario;

public class TelaPrincipal extends BorderPane {
	
	public TelaPrincipal(Funcionario f) {		
		VBox menuTopo = new VBox(30);				
		MenuBarPrincipal menuBarPrincipal = new MenuBarPrincipal(f);
		menuTopo.getChildren().add(menuBarPrincipal);				
		setTop(menuTopo);
		
		
	}

}
