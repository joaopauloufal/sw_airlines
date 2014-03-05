package swairlines.gui;

import swairlines.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarOperador extends MenuBar {
	
	public MenuBarOperador() {
		Menu menuArquivo = new Menu("Arquivo");
		Menu menuEditar = new Menu("Editar");
		Menu menuVoo = new Menu("Voo");
		Menu menuCompra = new Menu("Compra");
		Menu menuSobre = new Menu("Sobre");
		
		MenuItem itemSair = new MenuItem("Sair");
		
		itemSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
				
			}			
			
		});
		
		MenuItem itemLogout = new MenuItem("Logout...");
		
		itemLogout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Main.alterarTela(new TelaLogin());
				
			}
			
		});	
		
		menuArquivo.getItems().addAll(itemLogout, itemSair);
		
		MenuItem menuRealizarCompra = new MenuItem("Realizar Compra Passagem");
		menuCompra.getItems().addAll(menuRealizarCompra);
		
		MenuItem menuListarVoos = new MenuItem("Listar Voos");
		menuVoo.getItems().addAll(menuListarVoos);
		
		menuListarVoos.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaVoos());
				
			}
			
		});
		
		getMenus().addAll(menuArquivo, menuEditar, menuVoo, menuCompra, menuSobre);
	}

}
