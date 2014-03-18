package swairlines.view;

import javax.swing.JOptionPane;

import swairlines.Main;
import swairlines.model.ContaDeUsuario;
import swairlines.model.Funcionario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarPrincipal extends MenuBar {
	
	public MenuBarPrincipal(final Funcionario f) {
		Menu menuArquivo = new Menu("Arquivo");
		Menu menuCadastro = new Menu("Cadastro");
		Menu menuEditar = new Menu("Editar");
		Menu menuListar = new Menu("Listar");
		Menu menuVoo = new Menu("Voo");
		Menu menuCompra = new Menu("Compra");
		Menu menuSobre = new Menu("Sobre");		
		
		MenuItem itemSair = new MenuItem("Sair");
		
		itemSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema?", "Confirmação de Saida", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
				if (resposta == JOptionPane.YES_OPTION) {
					System.exit(0);
				}				
				
			}			
			
		});
		
		MenuItem itemLogout = new MenuItem("Fazer Logout...");
		
		itemLogout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				int resposta = JOptionPane.showConfirmDialog(null, "Você deseja realmente fazer Logout?", "Confirmação de Logout", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
				if (resposta == JOptionPane.YES_OPTION) {
					Main.alterarTela(new TelaLogin());
				}
								
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
		itemChecarDisponibilidade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaVoos(f));
				
			}
			
		});
		MenuItem itemAtrasarVoo = new MenuItem("Atrasar Voo..");
		
		MenuItem menuRealizarCompra = new MenuItem("Realizar Compra Passagem...");
		menuRealizarCompra.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TelaVenda tela = new TelaVenda();
				tela.setTitle("Venda de Passagens");
				
			}

		
		});
		menuCompra.getItems().addAll(menuRealizarCompra);
		
		MenuItem itemEditarVoo = new MenuItem("Editar Voo...");
		
		itemEditarVoo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// Para fazer				
				
			}
			
		});				
		
		menuVoo.getItems().addAll(itemChecarDisponibilidade, itemAtrasarVoo, itemEditarVoo);
		
		MenuItem itemListarContasDeUsuario = new MenuItem("Listar Contas de Usuário");
		
		itemListarContasDeUsuario.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaContasDeUsuario(f));
				
			}
			
		});
		MenuItem itemListarFuncionarios = new MenuItem("Listar Funcionários");
		
		itemListarFuncionarios.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaFuncionarios(f));
				
			}
			
		});
		MenuItem itemListarClientes = new MenuItem("Listar Clientes");
		
		itemListarClientes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaClientes(f));
				
			}
			
		});
		MenuItem listarVendas = new MenuItem("Listar Vendas");
		listarVendas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaVendas(f));
				
			}
			
		});
		
		
		menuListar.getItems().addAll(itemListarContasDeUsuario, itemListarClientes, itemListarFuncionarios, listarVendas);
		
		getMenus().addAll(menuArquivo, menuCadastro, menuEditar, menuListar, menuCompra, menuVoo, menuSobre);
		
		if (f.getConta().getTipoConta().equals(ContaDeUsuario.TIPO_CONTA_OPERADOR)) {
			itemCadastrarFuncionario.setVisible(false);
			itemCadastrarVoo.setVisible(false);
			itemEditarVoo.setVisible(false);
			itemCadastroConta.setVisible(false);
			itemAtrasarVoo.setVisible(false);
			itemListarFuncionarios.setVisible(false);
			itemCadastroConta.setVisible(false);
			itemListarContasDeUsuario.setVisible(false);
			
		}
	}

}