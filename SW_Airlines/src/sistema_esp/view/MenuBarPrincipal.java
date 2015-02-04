package sistema_esp.view;

import sistema_esp.controller.TelaCadRegraController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarPrincipal extends MenuBar {
	
	public MenuBarPrincipal() {
		Menu menuSistema = new Menu("Sistema");
		Menu menuCadastro = new Menu("Cadastro");
		Menu menuListar = new Menu("Listar");		
		Menu menuAjuda = new Menu("Ajuda");
		
		MenuItem itemCadastrarRegra = new MenuItem("Cadastrar Regra...");
		itemCadastrarRegra.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				TelaCadRegraController telaController = new TelaCadRegraController(new TelaCadRegra());
				telaController.getView();
				
			}
		});
		
		menuCadastro.getItems().addAll(itemCadastrarRegra);
		
		getMenus().addAll(menuSistema, menuCadastro, menuListar, menuAjuda);
	}

}
