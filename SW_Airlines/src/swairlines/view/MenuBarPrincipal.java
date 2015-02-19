package swairlines.view;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name MenuBarPrincipal
 */

import java.text.DecimalFormat;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javax.swing.JOptionPane;
import sistema_esp.dao.RegraDAO;
import sistema_esp.model.BaseDeRegras;
import sistema_esp.model.Conclusao;
import sistema_esp.model.MemoriaDeFatos;
import sistema_esp.model.MotorDeInferencia;
import swairlines.Main;
import swairlines.dao.VooDAO;
import swairlines.model.ContaDeUsuario;
import swairlines.model.Funcionario;
import swairlines.model.Voo;

public class MenuBarPrincipal extends MenuBar {
	
	public MenuBarPrincipal(final Funcionario f) {
		Menu menuSistema = new Menu("Sistema");
		Menu menuCadastro = new Menu("Cadastro");
		Menu menuListar = new Menu("Listar");
		Menu menuVoo = new Menu("Voo");
		Menu menuCompra = new Menu("Compra");
		Menu menuAjuda = new Menu("Ajuda");		
		
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
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente fazer Logout?", "Confirmação de Logout", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
				if (resposta == JOptionPane.YES_OPTION) {
					Main.alterarTela(new TelaLogin());
				}
								
			}			
		});
		
		MenuItem itemIncio = new MenuItem("Inicio");
		itemIncio.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaInicial(f));
				
			}
			
		});
		
		menuSistema.getItems().addAll(itemIncio,itemLogout, itemSair);

		
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
		
		MenuItem menuRealizarCompra = new MenuItem("Realizar Compra Passagem...");
		menuRealizarCompra.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TelaVenda tela = new TelaVenda();
				tela.setTitle("Venda de Passagens");
				
			}

		
		});
		
		MenuItem menuRealizarCompraGuiada = new MenuItem("Realizar Compra Passagem Guiada...");
		menuRealizarCompraGuiada.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				RegraDAO rDao = new RegraDAO();
				BaseDeRegras base = new BaseDeRegras();
				base.setRegras(rDao.retornaTodasAsRegrasDoBanco());
				MemoriaDeFatos mem = new MemoriaDeFatos();
				MotorDeInferencia motor = new MotorDeInferencia();
				motor.setMemoriaDeFatos(mem);
				motor.setBaseDeRegras(base);
				for (int i = 0; i < base.getRegras().size(); i++){
					Conclusao c = rDao.retornaTodasAsRegrasDoBanco().get(i).getConclusao();
					
					if(motor.inferir(c)){
						
						Alert alertConf = new Alert(AlertType.CONFIRMATION);
						DecimalFormat df = new DecimalFormat("0.0");
						alertConf.setTitle("Venda Passagem Guiada");
						alertConf.setHeaderText("Concluido!");
						alertConf.setContentText("Seu destino é ir para o/a " + c.getVariavel().getValor() + ".\nGrau de Confiança: " + df.format(c.getFatorCerteza()*100)+"%.\n" +"Você gostaria de comprar a passagem?");
						
						Optional<ButtonType> resposta = alertConf.showAndWait();
						
						if (resposta.get() == ButtonType.OK){
							VooDAO vd = new VooDAO();
							Voo vooDestino = new Voo();
							for (Voo v : vd.buscaVoos()){
								if (v.getDestino().equals(c.getVariavel().getValor())){
									vooDestino = v;
								}
							}
							TelaVenda telaVenda = new TelaVenda();
							telaVenda.setTitle("Venda de Passagens");
							telaVenda.getListVoos().getSelectionModel().select(String.valueOf((vooDestino.getId())));
							telaVenda.getLblOrigemVooValor().setText(vooDestino.getOrigem());
							telaVenda.getLblDestinoVooValor().setText(vooDestino.getDestino());
							telaVenda.getLblValorVooPreco().setText(String.valueOf(vooDestino.getValor()));
							
						}
						break;
			
					} else {
						if (i == base.getRegras().size()-1){
						Alert alertConf = new Alert(AlertType.INFORMATION);
						alertConf.setTitle("Venda Passagem Guiada");
						alertConf.setHeaderText("Concluido.");
						alertConf.setContentText("Não consegui encontrar um destino.");
						alertConf.showAndWait();
						break;
						}
					}
				}
				
				System.out.println(base.toString());
				System.out.println(mem.toString());
				
			}
			
		});
		
		MenuItem menuRealizarCheckin = new MenuItem("Realizar Check-in...");
		menuRealizarCheckin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				TelaCheckin tela = new TelaCheckin();
				tela.setTitle("Realizar Check-In");
				
			}
			
		});
		menuCompra.getItems().addAll(menuRealizarCompra, menuRealizarCompraGuiada, menuRealizarCheckin);			
		
		menuVoo.getItems().addAll(itemChecarDisponibilidade);
		
		MenuItem itemListarContasDeUsuario = new MenuItem("Listar Contas de Usuário...");
		
		itemListarContasDeUsuario.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaContasDeUsuario(f));
				
			}
			
		});
		MenuItem itemListarFuncionarios = new MenuItem("Listar Funcionários...");
		
		itemListarFuncionarios.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaFuncionarios(f));
				
			}
			
		});
		MenuItem itemListarClientes = new MenuItem("Listar Clientes...");
		
		itemListarClientes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaClientes(f));
				
			}
			
		});
		MenuItem listarVendas = new MenuItem("Listar Vendas...");
		listarVendas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaVendas(f));
				
			}
			
		});
		
		MenuItem listarBagagens = new MenuItem("Listar Bagagens...");
		listarBagagens.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaBagagens(f));
				
			}
			
		});
		
		
		menuListar.getItems().addAll(itemListarContasDeUsuario, itemListarClientes, itemListarFuncionarios, listarVendas, listarBagagens);
		
		MenuItem itemAjuda = new MenuItem("Sobre SW Airlines...");
		itemAjuda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "Versão 1.6", "SW Airlines", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuAjuda.getItems().addAll(itemAjuda);
		
		getMenus().addAll(menuSistema, menuCadastro, menuListar, menuCompra, menuVoo, menuAjuda);
		
		/**Verifica se a conta de usuário é operador e gerente, para assim alterar a visualização da janela*/
		if (f.getConta().getTipoConta().equals(ContaDeUsuario.TIPO_CONTA_OPERADOR)) {
			itemCadastrarFuncionario.setVisible(false);
			itemCadastrarVoo.setVisible(false);
			itemCadastroConta.setVisible(false);
			itemListarFuncionarios.setVisible(false);
			itemCadastroConta.setVisible(false);
			itemListarContasDeUsuario.setVisible(false);
			
		}
	}

}