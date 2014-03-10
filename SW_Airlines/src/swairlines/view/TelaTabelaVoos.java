package swairlines.view;

import javax.swing.JOptionPane;

import swairlines.Main;
import swairlines.dao.VooDAO;
import swairlines.model.ContaDeUsuario;
import swairlines.model.Funcionario;
import swairlines.model.Voo;
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

public class TelaTabelaVoos extends BorderPane {	

	private TableView<Voo> tableView = new TableView<Voo>();
	private ObservableList<Voo> dados;
	
	@SuppressWarnings("unchecked")
	public TelaTabelaVoos(final Funcionario f) {
		
		
		dados = FXCollections.observableArrayList();
		VooDAO vooDao = new VooDAO();
		
		dados.addAll(vooDao.buscaVoos());
		
		tableView.setEditable(true);		
		
		HBox hbox = new HBox(20);
		
		TableColumn<Voo, Integer> idColuna = new TableColumn<>("Voo Nº");
		idColuna.setCellValueFactory(new PropertyValueFactory<Voo, Integer>("id"));
		idColuna.setMinWidth(60);
		
		TableColumn<Voo, String> origemColuna = new TableColumn<>("Origem");
		origemColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("origem"));
		origemColuna.setMinWidth(140);
		
		TableColumn<Voo, String> destinoColuna = new TableColumn<>("Destino");
		destinoColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("destino"));
		destinoColuna.setMinWidth(140);
		
		TableColumn<Voo, String> statusColuna = new TableColumn<>("Status");
		statusColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("status"));
		statusColuna.setMinWidth(140);
		
		TableColumn<Voo, Integer> qntPassageirosColuna = new TableColumn<>("Quantidade de Passageiros");
		qntPassageirosColuna.setCellValueFactory(new PropertyValueFactory<Voo, Integer>("quantidadeDePassageiros"));
		qntPassageirosColuna.setMinWidth(210);
		
		TableColumn<Voo, String> rotaColuna = new TableColumn<>("Rota");
		rotaColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("rota"));
		rotaColuna.setMinWidth(160);
		
		TableColumn<Voo, String> horaPartidaColuna = new TableColumn<>("Hora de Partida");
		horaPartidaColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("horaPartida"));
		horaPartidaColuna.setMinWidth(120);
		
		TableColumn<Voo, String> horaChegadaColuna = new TableColumn<>("Hora de Chegada");
		horaChegadaColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("horaChegada"));
		horaChegadaColuna.setMinWidth(140);
		
		TableColumn<Voo, String> dataPartidaColuna = new TableColumn<>("Data de Partida");
		dataPartidaColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("dataPartida"));
		dataPartidaColuna.setMinWidth(150);
		
		TableColumn<Voo, String> dataChegadaColuna = new TableColumn<>("Data de Chegada");
		dataChegadaColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("dataChegada"));
		dataChegadaColuna.setMinWidth(140);
		
		TableColumn<Voo, String> tipoVooColuna = new TableColumn<>("Tipo de Voo");
		tipoVooColuna.setCellValueFactory(new PropertyValueFactory<Voo, String>("tipoVoo"));
		tipoVooColuna.setMinWidth(140);		
		
		Button btnEditarVoo = new Button("Editar Voo");
		btnEditarVoo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {	
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					Voo v1 = dados.get(tableView.getSelectionModel().getSelectedIndex());
					TelaEditVoo tela = new TelaEditVoo(v1);
					tela.setTitle("Editar Voo");
					
				}				
			}
		});
		
		Button btnAtualizarValores = new Button("Atualizar Valores");
		
		btnAtualizarValores.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaVoos(f));
				
			}
			
		});
		
		Button btnExcluirVoo = new Button("Excluir Voo");
		
		btnExcluirVoo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza de que quer excuir o Voo selecionado?", "Confirmação de Exclusão Voo", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						VooDAO vooDao = new VooDAO();
						vooDao.excluiVoo(tableView.getSelectionModel().getSelectedItem());
						JOptionPane.showMessageDialog(null, "Voo excluido.", "Exclusão de Voo", JOptionPane.INFORMATION_MESSAGE);
						Main.alterarTela(new TelaTabelaVoos(f));
					}
					
				}
				
			}
			
		});
		
		Button btnCancelarVoo = new Button("Cancelar Voo");
		btnCancelarVoo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					if (dados.get(tableView.getSelectionModel().getSelectedIndex()).getStatus().equals("Cancelado")) {
						JOptionPane.showMessageDialog(null, "Este voo já foi cancelado.", "Cancelamento Voo", JOptionPane.INFORMATION_MESSAGE);
					} else {
						int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza de que quer cancelar o Voo selecionado?", "Confirmação de Cancelamento Voo", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
						if (resposta == JOptionPane.YES_OPTION) {
							Voo v1 = dados.get(tableView.getSelectionModel().getSelectedIndex());
							VooDAO vd = new VooDAO();
							vd.cancelarVoo(v1);
							Main.alterarTela(new TelaTabelaVoos(f));
						}
					}
					
				}
				
			}
			
		});
		
		tableView.getColumns().addAll(idColuna, origemColuna, destinoColuna, statusColuna, qntPassageirosColuna, rotaColuna, horaPartidaColuna, horaChegadaColuna, dataPartidaColuna, dataChegadaColuna, tipoVooColuna);
		tableView.setFocusTraversable(false);	
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Relação de Voos");
		titulo.setFont(new Font(30));
		hbox.getChildren().addAll(btnExcluirVoo, btnAtualizarValores, btnEditarVoo, btnCancelarVoo);
		hbox.setAlignment(Pos.BASELINE_CENTER);
		boxTop.setAlignment(Pos.CENTER);
		VBox boxTable = new VBox();
		boxTable.setPadding(new Insets(0, 10, 0, 10));
		boxTable.getChildren().add(tableView);
		boxTop.getChildren().addAll(new TelaPrincipal(f), titulo, boxTable, hbox);
		setTop(boxTop);
		
		tableView.setItems(dados);
		
		if (f.getConta().getTipoConta().equals(ContaDeUsuario.TIPO_CONTA_OPERADOR)) {
			btnEditarVoo.setVisible(false);
			btnExcluirVoo.setVisible(false);
			btnCancelarVoo.setVisible(false);
		}
		
		
	}
	
}
