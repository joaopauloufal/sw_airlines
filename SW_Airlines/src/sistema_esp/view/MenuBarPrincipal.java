package sistema_esp.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuBarPrincipal extends MenuBar {
	
	public MenuBarPrincipal() {
		Menu menuSistema = new Menu("Sistema");
		Menu menuCadastro = new Menu("Cadastro");
		Menu menuListar = new Menu("Listar");		
		Menu menuAjuda = new Menu("Ajuda");
		
		getMenus().addAll(menuSistema, menuCadastro, menuListar, menuAjuda);
	}

}
