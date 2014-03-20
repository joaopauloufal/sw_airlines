package swairlines.view;

import javax.swing.JOptionPane;

import swairlines.Main;
import swairlines.dao.BagagemDAO;
import swairlines.model.Bagagem;
import swairlines.model.Funcionario;
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

public class TelaTabelaBagagens extends BorderPane {
	
	private TableView<Bagagem> tableView = new TableView<Bagagem>();
	private ObservableList<Bagagem> dados;
	
	@SuppressWarnings("unchecked")
	public TelaTabelaBagagens(final Funcionario f) {
		
		HBox hbox = new HBox(20);
		
		dados = FXCollections.observableArrayList();
		BagagemDAO bDao = new BagagemDAO();
		dados.addAll(bDao.buscaBagagens());
		
		tableView.setEditable(true);
		
		TableColumn<Bagagem, String> cpfClienteColuna = new TableColumn<>("CPF Cliente");
		cpfClienteColuna.setCellValueFactory(new PropertyValueFactory<Bagagem, String>("cpfCnpjCliente"));
		cpfClienteColuna.setMinWidth(140);
		
		TableColumn<Bagagem, String> nomeClienteColuna = new TableColumn<>("Nome Cliente");
		nomeClienteColuna.setCellValueFactory(new PropertyValueFactory<Bagagem, String>("nomeCliente"));
		nomeClienteColuna.setMinWidth(140);
		
		TableColumn<Bagagem, Integer> idVooColuna = new TableColumn<>("Voo Nº");
		idVooColuna.setCellValueFactory(new PropertyValueFactory<Bagagem, Integer>("vooId"));
		idVooColuna.setMinWidth(140);
		
		TableColumn<Bagagem, String> origemColuna = new TableColumn<>("Origem Voo");
		origemColuna.setCellValueFactory(new PropertyValueFactory<Bagagem, String>("origemVoo"));
		origemColuna.setMinWidth(140);
		
		TableColumn<Bagagem, String> destinoColuna = new TableColumn<>("Destino Voo");
		destinoColuna.setCellValueFactory(new PropertyValueFactory<Bagagem, String>("destinoVoo"));
		destinoColuna.setMinWidth(140);
		
		TableColumn<Bagagem, Double> pesoBagagemColuna = new TableColumn<>("Peso Kg");
		pesoBagagemColuna.setCellValueFactory(new PropertyValueFactory<Bagagem, Double>("pesoBagagem"));
		pesoBagagemColuna.setMinWidth(140);
		
		tableView.getColumns().addAll(cpfClienteColuna, nomeClienteColuna, idVooColuna, origemColuna, destinoColuna, pesoBagagemColuna);
		tableView.setFocusTraversable(false);
		
		Button btnAtualizar = new Button("Atualizar Valores");
		btnAtualizar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaBagagens(f));
				
			}
		});
		Button btnExcluirBagagem = new Button("Excluir Bagagem");
		btnExcluirBagagem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza de que quer excluir a bagagem selecionada?", "Confirmação de Exclusão Bagagem", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						BagagemDAO bDao = new BagagemDAO();
						bDao.removeBagagem(tableView.getSelectionModel().getSelectedItem());
						JOptionPane.showMessageDialog(null, "Bagagem excluida.", "Exclusão de Bagagem", JOptionPane.INFORMATION_MESSAGE);
						Main.alterarTela(new TelaTabelaBagagens(f));
					}
				}
				
			}
			
		});
		
		
		
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Lista de Bagagens");
		titulo.setFont(new Font(30));
		boxTop.setAlignment(Pos.CENTER);
		VBox vboxTabela = new VBox();
		hbox.getChildren().addAll(btnAtualizar, btnExcluirBagagem);
		hbox.setAlignment(Pos.BASELINE_CENTER);
		vboxTabela.setPadding(new Insets(0, 90, 0, 90));
		vboxTabela.getChildren().add(tableView);
		boxTop.getChildren().addAll(new TelaPrincipal(f), titulo, vboxTabela, hbox);
		setTop(boxTop);
		
		tableView.setItems(dados);
	}

}
