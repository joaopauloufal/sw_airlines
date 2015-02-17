package sistema_esp.view;

import sistema_esp.Main;
import sistema_esp.controller.TelaCadRegraController;
import sistema_esp.model.SemVoosCadastradosException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;

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
				try {
					TelaCadRegraController telaController = new TelaCadRegraController(new TelaCadRegra());
					telaController.getView();
				} catch (SemVoosCadastradosException e){
					Alert alertConf = new Alert(AlertType.ERROR);
					alertConf.setTitle("Sem Voos");
					alertConf.setHeaderText("Erro.");
					alertConf.setContentText(e.getMessage());
					alertConf.showAndWait();
				}
				
			}
		});
		
		MenuItem itemListarRegras = new MenuItem("Listar Regras...");
		itemListarRegras.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaRegras());
				
			}
			
		});
		
		MenuItem itemInicio = new MenuItem("Inicio");
		itemInicio.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaInicial());
				
			}
			
		});
		
		
		
		menuCadastro.getItems().addAll(itemCadastrarRegra);
		menuSistema.getItems().addAll(itemInicio);
		menuListar.getItems().addAll(itemListarRegras);
		
		
		getMenus().addAll(menuSistema, menuCadastro, menuListar, menuAjuda);
	}

}
