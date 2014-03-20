package swairlines.view;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name TelaTabelaVendas
 */

import javax.swing.JOptionPane;

import swairlines.Main;
import swairlines.dao.VendaDAO;
import swairlines.model.Funcionario;
import swairlines.model.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TelaTabelaVendas extends BorderPane {
	
	private TableView<Venda> tableView = new TableView<Venda>();
	private ObservableList<Venda> dados;
	
	@SuppressWarnings("unchecked")
	public TelaTabelaVendas(final Funcionario f) {
		
		HBox hbox = new HBox(20);
		
		dados = FXCollections.observableArrayList();
		VendaDAO vendaDao = new VendaDAO();
		dados.addAll(vendaDao.buscaVendas());
		
		tableView.setEditable(true);
		
		TableColumn<Venda, Integer> vooColuna = new TableColumn<>("Voo Nº");
		vooColuna.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("idVoo"));
		vooColuna.setMinWidth(120);
		
		TableColumn<Venda, String> origemVooColuna = new TableColumn<>("Origem");
		origemVooColuna.setCellValueFactory(new PropertyValueFactory<Venda, String>("origemVoo"));
		origemVooColuna.setMinWidth(140);
		
		TableColumn<Venda, String> destinoVooColuna = new TableColumn<>("Destino");
		destinoVooColuna.setCellValueFactory(new PropertyValueFactory<Venda, String>("destinoVoo"));
		destinoVooColuna.setMinWidth(140);
		
		TableColumn<Venda, String> tipoVendaColuna = new TableColumn<>("Tipo de Venda");
		tipoVendaColuna.setCellValueFactory(new PropertyValueFactory<Venda, String>("tipoVenda"));
		tipoVendaColuna.setMinWidth(140);
		
		TableColumn<Venda, Double> valorVooColuna = new TableColumn<>("Preço Voo");
		valorVooColuna.setCellValueFactory(new PropertyValueFactory<Venda, Double>("valorVoo"));
		valorVooColuna.setMinWidth(140);
		
		TableColumn<Venda, String> dataHoraVenda = new TableColumn<>("Data e Hora da Venda");
		dataHoraVenda.setCellValueFactory(new PropertyValueFactory<Venda, String>("dataVenda"));
		dataHoraVenda.setMinWidth(170);
		
		TableColumn<Venda, String> cpfCnpjClienteColuna = new TableColumn<>("CPF/CNPJ Cliente");
		cpfCnpjClienteColuna.setCellValueFactory(new PropertyValueFactory<Venda, String>("cpfCnpjCliente"));
		cpfCnpjClienteColuna.setMinWidth(140);
		
		TableColumn<Venda, String> nomeClienteColuna = new TableColumn<>("Nome Cliente");
		nomeClienteColuna.setCellValueFactory(new PropertyValueFactory<Venda, String>("nomeCliente"));
		nomeClienteColuna.setMinWidth(140);
		
		TableColumn<Venda, String> cartaoClienteColuna = new TableColumn<>("Cartão de Crédito Cliente");
		cartaoClienteColuna.setCellValueFactory(new PropertyValueFactory<Venda, String>("cartaoCreditoCliente"));
		cartaoClienteColuna.setMinWidth(200);
		
		TableColumn<Venda, Integer> parcelasColuna = new TableColumn<>("Parcelas");
		parcelasColuna.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("parcelas"));
		parcelasColuna.setMinWidth(140);
		
		TableColumn<Venda, Double> valorParcelasColuna = new TableColumn<>("Valor Parcela");
		valorParcelasColuna.setCellValueFactory(new PropertyValueFactory<Venda, Double>("valorParcela"));
		valorParcelasColuna.setMinWidth(140);
		
		/** Remove venda do banco*/
		
		Button btnRemoverVenda = new Button("Remover Venda");
		btnRemoverVenda.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza de que quer excuir a Venda selecionada?", "Confirmação de Exclusão de Venda", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						VendaDAO vendaDao = new VendaDAO();
						vendaDao.excluiVenda(tableView.getSelectionModel().getSelectedItem());
						JOptionPane.showMessageDialog(null, "Venda excluida.", "Exclusão de Venda", JOptionPane.INFORMATION_MESSAGE);
						Main.alterarTela(new TelaTabelaVendas(f));
					}
				}
				
			}
		});
		
		/** Altera venda do banco*/
		
		Button btnAlterarVenda = new Button("Alterar Venda...");
		btnAlterarVenda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					Venda venda = dados.get(tableView.getSelectionModel().getSelectedIndex());
					if (venda.getCartaoCreditoCliente() == null) {
						JOptionPane.showMessageDialog(null, "Não é possível alterar o tipo da venda. Cliente não possui cartão de crédito", "Alteração de Vendas", JOptionPane.WARNING_MESSAGE);
					} else {
						TelaEditVenda tela = new TelaEditVenda(venda);
						tela.setTitle("Alterar Venda");
					}
					
				}
				
			}
			
		});
		
		/**Atualiza dados*/
		
		Button btnAtualizarValores = new Button("Atualizar Valores");
		btnAtualizarValores.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaVendas(f));
				
			}
			
		});
		
		tableView.getColumns().addAll(vooColuna, origemVooColuna, destinoVooColuna, tipoVendaColuna, valorVooColuna, dataHoraVenda, cpfCnpjClienteColuna, nomeClienteColuna, cartaoClienteColuna, parcelasColuna, valorParcelasColuna);
		tableView.setFocusTraversable(false);
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Lista de Vendas");
		titulo.setFont(new Font(30));
		hbox.getChildren().addAll(btnAlterarVenda, btnRemoverVenda, btnAtualizarValores);
		hbox.setAlignment(Pos.BASELINE_CENTER);
		boxTop.setAlignment(Pos.CENTER);
		VBox vboxTabela = new VBox();
		vboxTabela.setPadding(new Insets(0, 10, 0, 10));
		vboxTabela.getChildren().add(tableView);
		boxTop.getChildren().addAll(new TelaPrincipal(f), titulo, vboxTabela, hbox);
		setTop(boxTop);
		
		tableView.setItems(dados);
		
	}

}