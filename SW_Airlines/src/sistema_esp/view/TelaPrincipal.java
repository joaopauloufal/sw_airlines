package sistema_esp.view;
import sistema_esp.view.MenuBarPrincipal;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TelaPrincipal extends BorderPane {
	
	public TelaPrincipal() {		
		VBox menuTopo = new VBox(30);				
		MenuBarPrincipal menuBarPrincipal = new MenuBarPrincipal();
		menuTopo.getChildren().add(menuBarPrincipal);				
		setTop(menuTopo);
		
		
	}

}
