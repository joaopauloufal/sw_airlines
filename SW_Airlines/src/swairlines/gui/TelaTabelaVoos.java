package swairlines.gui;

import swairlines.modelo.ContaDeUsuario;
import swairlines.modelo.Funcionario;
import swairlines.modelo.Gerente;
import swairlines.modelo.Voo;
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
	public TelaTabelaVoos(Funcionario f) {
		
		dados = FXCollections.observableArrayList();
		Gerente gerente = new Gerente();
		dados.addAll(gerente.buscaVoos());
		
		tableView.setEditable(true);		
		
		HBox hbox = new HBox(20);
		
		TableColumn<Voo, Integer> idColuna = new TableColumn<>("Id");
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
		
		tableView.getColumns().addAll(idColuna, origemColuna, destinoColuna, statusColuna, qntPassageirosColuna, rotaColuna, horaPartidaColuna, horaChegadaColuna, dataPartidaColuna, dataChegadaColuna, tipoVooColuna);
		tableView.setFocusTraversable(false);	
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Relação de Voos");
		titulo.setFont(new Font(30));
		hbox.getChildren().addAll(btnEditarVoo);
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
		}
		
		
	}
	
}
