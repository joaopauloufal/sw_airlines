package sistema_esp.view;

import java.util.Optional;

import sistema_esp.Main;
import sistema_esp.controller.TelaEditRegraController;
import sistema_esp.dao.RegraDAO;
import sistema_esp.model.Conclusao;
import sistema_esp.model.Premissa;
import sistema_esp.model.Regra;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TelaTabelaRegras extends BorderPane {
	
	private TableView<Regra> tableView = new TableView<Regra>();
	private ObservableList<Regra> dados;
	
	@SuppressWarnings("unchecked")
	public TelaTabelaRegras() {
		
		dados = FXCollections.observableArrayList();
		RegraDAO regraDao = new RegraDAO();
		
		dados.addAll(regraDao.retornaTodasAsRegrasDoBanco());
		
		tableView.setEditable(true);
		
		HBox hbox = new HBox(20);
		
		TableColumn<Regra, String> nomeColuna = new TableColumn<>("Nome da Regra");
		nomeColuna.setCellValueFactory(new PropertyValueFactory<Regra, String>("nome"));
		nomeColuna.setMinWidth(60);
		
		TableColumn<Regra, Premissa> premissasColuna = new TableColumn<>("Premissas");
		premissasColuna.setCellValueFactory(new PropertyValueFactory<Regra, Premissa>("premissas"));
		premissasColuna.setMinWidth(60);
		
		TableColumn<Regra, Conclusao> conclusaoColuna = new TableColumn<>("Conclusão");
		conclusaoColuna.setCellValueFactory(new PropertyValueFactory<Regra, Conclusao>("conclusao"));
		conclusaoColuna.setMinWidth(60);
		
		TableColumn<Regra, Float> fatorConfiancaColuna = new TableColumn<>("Fator de Confiança (%)");
		fatorConfiancaColuna.setCellValueFactory(new PropertyValueFactory<Regra, Float>("fatorDeConfianca"));
		fatorConfiancaColuna.setMinWidth(410);
		
		tableView.getColumns().addAll(nomeColuna, premissasColuna, conclusaoColuna, fatorConfiancaColuna);
		
		tableView.setFocusTraversable(false);
		
		Button btnExcluirRegra = new Button("Excluir Regra");
		btnExcluirRegra.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1){
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Exclusão de Regra");
					alert.setHeaderText("Confirmação de Exclusão de Regra");
					alert.setContentText("Você quer realmente excluir a regra selecionada?");
					
					Optional<ButtonType> resposta = alert.showAndWait();
					
					if (resposta.get() == ButtonType.OK){
						RegraDAO rDao = new RegraDAO();
						rDao.excluirRegra(tableView.getSelectionModel().getSelectedItem());
						Alert alertConf = new Alert(AlertType.INFORMATION);
						alertConf.setTitle("Exclusão de Regra");
						alertConf.setHeaderText("Sucesso.");
						alertConf.setContentText("Regra Excluida.");
						alertConf.showAndWait();
						Main.alterarTela(new TelaTabelaRegras());
					}
				}
				
			}
		});
		
		Button btnEditarRegra = new Button("Editar Regra");
		btnEditarRegra.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1){
					TelaEditRegraController telaControl = new TelaEditRegraController(new TelaEditRegra(tableView.getSelectionModel().getSelectedItem()));
					telaControl.getView();
				}
			}
			
		});
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Lista de Regras");
		titulo.setFont(new Font(30));
		hbox.setAlignment(Pos.BASELINE_CENTER);
		hbox.getChildren().addAll(btnExcluirRegra, btnEditarRegra);
		boxTop.setAlignment(Pos.CENTER);
		VBox boxTable = new VBox();
		boxTable.setPadding(new Insets(0, 10, 0, 10));
		boxTable.getChildren().add(tableView);
		boxTop.getChildren().addAll(new TelaPrincipal(),titulo, boxTable, hbox);
		setTop(boxTop);
		tableView.setItems(dados);
		
	}

}
