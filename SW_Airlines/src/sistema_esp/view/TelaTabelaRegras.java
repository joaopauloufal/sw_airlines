package sistema_esp.view;

import sistema_esp.dao.RegraDAO;
import sistema_esp.model.Conclusao;
import sistema_esp.model.Premissa;
import sistema_esp.model.Regra;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
		
		TableColumn<Regra, Double> fatorConfiancaColuna = new TableColumn<>("Fator de Confiança (%)");
		fatorConfiancaColuna.setCellValueFactory(new PropertyValueFactory<Regra, Double>("fatorDeConfianca"));
		fatorConfiancaColuna.setMinWidth(410);
		
		tableView.getColumns().addAll(nomeColuna, premissasColuna, conclusaoColuna, fatorConfiancaColuna);
		
		tableView.setFocusTraversable(false);
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Lista de Regras");
		titulo.setFont(new Font(30));
		hbox.setAlignment(Pos.BASELINE_CENTER);
		boxTop.setAlignment(Pos.CENTER);
		VBox boxTable = new VBox();
		boxTable.setPadding(new Insets(0, 10, 0, 10));
		boxTable.getChildren().add(tableView);
		boxTop.getChildren().addAll(new TelaInicial(),titulo, boxTable, hbox);
		setTop(boxTop);
		tableView.setItems(dados);
		
	}

}
