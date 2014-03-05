package swairlines.gui;

import swairlines.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarAdmin extends MenuBar {
	
	public MenuBarAdmin() {
		Menu menuArquivo = new Menu("Arquivo");
		Menu menuCadastro = new Menu("Cadastro");
		Menu menuEditar = new Menu("Editar");
		Menu menuVoo = new Menu("Voo");
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

		
		MenuItem itemCadastrarFuncionario = new MenuItem("Cadastrar Funcionário...");
		
		itemCadastrarFuncionario.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				TelaCadFuncionario tela = new TelaCadFuncionario();
				tela.setTitle("Cadastrar Funcionário");
				
			}
		});
		
		MenuItem itemCadastrarCliente = new MenuItem("Cadastrar Cliente...");
		
		itemCadastrarCliente.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				TelaCadCliente tela = new TelaCadCliente();
				tela.setTitle("Cadastrar Cliente");				
				
			}			
		});
		
		MenuItem itemCadastrarVoo = new MenuItem("Cadastrar Voo...");
		
		itemCadastrarVoo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {				
				TelaCadVoo tela = new TelaCadVoo();
				tela.setTitle("Cadastrar Voo");				
				
			}
			
		});
		
		MenuItem itemCadastroConta = new MenuItem("Cadastrar Conta...");
		
		menuCadastro.getItems().addAll(itemCadastrarFuncionario, itemCadastrarCliente, itemCadastrarVoo, itemCadastroConta);
		
		itemCadastroConta.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				TelaCadConta tela = new TelaCadConta();
				tela.setTitle("Cadastrar Conta");
				
			}
			
			
		});
		
		MenuItem itemChecarDisponibilidade = new MenuItem("Checar Disponibilidade...");
		MenuItem itemAtrasarVoo = new MenuItem("Atrasar Voo..");
		MenuItem listarVoos = new MenuItem("Listar Voos");
		
		listarVoos.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {			
					Main.alterarTela(new TelaTabelaVoos());				
				
			}
			
		});
		
		MenuItem itemEditarVoo = new MenuItem("Editar Voo...");
		
		itemEditarVoo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// Para fazer
				
				
			}
			
		});
		
		menuVoo.getItems().addAll(listarVoos, itemChecarDisponibilidade, itemAtrasarVoo, itemEditarVoo);
		
		getMenus().addAll(menuArquivo, menuCadastro, menuEditar, menuVoo, menuSobre);
	}

}
