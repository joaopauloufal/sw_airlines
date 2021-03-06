package sistema_esp.view;

import sistema_esp.dao.RegraDAO;
import sistema_esp.model.Conclusao;
import sistema_esp.model.ExpressaoMalFormatadaException;
import sistema_esp.model.Premissa;
import sistema_esp.model.Regra;
import sistema_esp.model.SemVoosCadastradosException;
import sistema_esp.model.Variavel;
import swairlines.dao.VooDAO;
import swairlines.model.Voo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaCadRegra extends Stage {
	
	private ObservableList<Premissa> dados;
	private ObservableList<String> conclusoes;
	private TextField txtNomeRegra;
	
	public TelaCadRegra() throws SemVoosCadastradosException {
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		HBox hbox0 = new HBox(20);
		HBox hbox1 = new HBox(20);
		HBox hbox2 = new HBox(20);
		HBox hbox3 = new HBox(20);
		HBox hbox4 = new HBox(20);
		VBox vbox1 = new VBox(20);
		
		Scene scene = new Scene(gPane, 600, 500, Color.SILVER);
		setScene(scene);
		
		dados = FXCollections.observableArrayList();
		
		ObservableList<String> variaveis = FXCollections.observableArrayList("Culturas diferentes", "Praias", "Florestas", "Climas Temperados", "Climas Tropicais", "Ambientes Frios", "Ambientes Quentes");
		ObservableList<String> simbolos = FXCollections.observableArrayList("","e", "ou");
		conclusoes = FXCollections.observableArrayList();
		VooDAO vd = new VooDAO();
		if (!vd.buscaVoos().isEmpty()){
			for (Voo v : vd.buscaVoos()){
				conclusoes.add(v.getDestino());
			}
		} else {
			throw new SemVoosCadastradosException("Não há voos cadastrados!");
		}
		
		ObservableList<String> negacoes = FXCollections.observableArrayList("","Não");
		ObservableList<Float> fatoresCerteza = FXCollections.observableArrayList(10.0f, 20.0f, 30.0f, 40.0f, 50.0f, 60.0f, 70.0f, 80.0f, 90.0f, 100.0f);
		
		Button btnAdicionar = new Button("Add");
		Button btnCancelar = new Button("Cancelar");
		Label lblPremissa = new Label("Premissa:");
		Label lblSe = new Label("Se:");
		
		
		Label lblConclusao = new Label("Então:");
		Label lblFatorCerteza = new Label("Fator de Certeza (%):");
		final ComboBox<Float> comboBoxFatoresCerteza = new ComboBox<Float>(fatoresCerteza);
		comboBoxFatoresCerteza.getSelectionModel().selectLast();
		
		Label lblNomeRegra = new Label("Nome da Regra:");
		txtNomeRegra = new TextField();
		txtNomeRegra.setPrefColumnCount(20);
		hbox0.getChildren().addAll(lblNomeRegra, txtNomeRegra);
		
		final ComboBox<String> comboBox = new ComboBox<String>(variaveis);
		comboBox.getSelectionModel().selectFirst();
		final ComboBox<String> comboBoxSimbolos = new ComboBox<String>(simbolos);
		comboBoxSimbolos.getSelectionModel().selectFirst();
		final ListView<Premissa> lista = new ListView<Premissa>();
		final ComboBox<String> comboBoxConclusao = new ComboBox<String>(conclusoes);
		comboBoxConclusao.getSelectionModel().selectFirst();
		final ComboBox<String> comboBoxNegacao = new ComboBox<String>(negacoes);
		comboBoxNegacao.getSelectionModel().selectFirst();
		
		Button btnCadastrar = new Button("Cadastrar");
		Button btnLimparTodasPremissas = new Button("Limpar tudo");
		
		lista.setPrefSize(300, 200);
		
		hbox1.getChildren().addAll(lblPremissa, comboBoxNegacao,comboBox, comboBoxSimbolos, btnAdicionar);
		hbox2.getChildren().addAll(lblSe, lista, btnLimparTodasPremissas);
		hbox3.getChildren().addAll(lblConclusao, comboBoxConclusao, lblFatorCerteza, comboBoxFatoresCerteza);
		hbox4.getChildren().addAll(btnCadastrar, btnCancelar);
		hbox4.setAlignment(Pos.CENTER);
		
		btnAdicionar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Variavel v = new Variavel(comboBox.getSelectionModel().getSelectedItem());
				Premissa p = new Premissa(v);
				if (comboBoxSimbolos.getSelectionModel().getSelectedItem().equals("e")){
					p.setSimbolo("^");
				}
				if (comboBoxSimbolos.getSelectionModel().getSelectedItem().equals("ou")){
					p.setSimbolo("|");
				}
				if (comboBoxSimbolos.getSelectionModel().getSelectedItem().equals("")){
					p.setSimbolo("");
				}
				
				if (comboBoxNegacao.getSelectionModel().getSelectedItem().equals("Não")){
					p.setEstaNegada(true);
				}
				
				if (dados.size() >= 1 && dados.get(0).getSimbolo().equals("")){
					try {
						throw new ExpressaoMalFormatadaException("Expressão mal formatada!");
					} catch (ExpressaoMalFormatadaException e) {
						Alert alertConf = new Alert(AlertType.ERROR);
						alertConf.setTitle("Expressão Mal Formatada");
						alertConf.setHeaderText("Erro.");
						alertConf.setContentText(e.getMessage());
						alertConf.showAndWait();
					}
				} else {
					dados.add(p);
					lista.setItems(dados);
				}
				
			}
			
		});
		
		btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				RegraDAO rDao = new RegraDAO();
				Variavel vConc = new Variavel(comboBoxConclusao.getSelectionModel().getSelectedItem());
				Conclusao c = new Conclusao(vConc);
				Regra r = new Regra(txtNomeRegra.getText(), c, comboBoxFatoresCerteza.getSelectionModel().getSelectedItem());
				r.setPremissas(dados);
				rDao.insereRegra(r);
				Alert alertConf = new Alert(AlertType.INFORMATION);
				alertConf.setTitle("Cadastro de Regra");
				alertConf.setHeaderText("Sucesso.");
				alertConf.setContentText("Regra Cadastrada com sucesso!");
				alertConf.showAndWait();
				hide();
				
			}
			
		});
		
		btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				hide();
				
			}
			
		});
		
		btnLimparTodasPremissas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				dados = FXCollections.observableArrayList();
				lista.setItems(dados);
				
			}
			
		});
		
		
		
		vbox1.getChildren().addAll(hbox0, hbox1, hbox2, hbox3, hbox4);
		this.setTitle("Cadastrar Regra");
		
		GridPane.setConstraints(vbox1, 9, 4);
		
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}

	public ObservableList<String> getConclusoes() {
		return conclusoes;
	}

	public void setConclusoes(ObservableList<String> conclusoes) {
		this.conclusoes = conclusoes;
	}
	
	

}
