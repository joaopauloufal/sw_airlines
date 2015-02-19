package sistema_esp.view;

import java.util.Optional;

import sistema_esp.model.Premissa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaPergunta extends Stage {
	
	private boolean valorLogicoFato;
	private float fatorDeCertezaFato;
	private boolean cancelaConsulta;
	

	public TelaPergunta() {
		
	}
	
	public TelaPergunta(final Premissa fato) {
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		Scene scene = new Scene(gPane, 500, 300, Color.SILVER);
		setScene(scene);
		
		VBox vbox = new VBox(20);
		HBox hbox1 = new HBox(20);
		HBox hbox2 = new HBox(20);
		HBox hbox3 = new HBox(20);
		HBox hbox4 = new HBox(20);
		HBox hbox5 = new HBox(20);
		
		ObservableList<Float> fatoresCerteza = FXCollections.observableArrayList(10.0f, 20.0f, 30.0f, 40.0f, 50.0f, 60.0f, 70.0f, 80.0f, 90.0f, 100.0f);
		
		Label lblPergunta = new Label("Você gosta de " + fato.getVariavel() +"?");
		Label lblFatorCerteza = new Label("Fator de Certeza (%):");
		final ComboBox<Float> comboBoxFatoresCerteza = new ComboBox<Float>(fatoresCerteza);
		comboBoxFatoresCerteza.getSelectionModel().selectFirst();
		hbox1.getChildren().addAll(lblPergunta);
		
		final ToggleGroup grupo = new ToggleGroup();
		
		RadioButton rbSim = new RadioButton("Sim");
		rbSim.setToggleGroup(grupo);
		
		RadioButton rbNao = new RadioButton("Não");
		rbNao.setToggleGroup(grupo);
		grupo.selectToggle(rbSim);
		
		hbox2.getChildren().addAll(rbSim);
		hbox3.getChildren().addAll(rbNao);
		hbox4.getChildren().addAll(lblFatorCerteza, comboBoxFatoresCerteza);		
		
		
		Button btnOk = new Button("Ok");
		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (grupo.getToggles().get(0).isSelected()){
					fato.setValorLogico(true);
					valorLogicoFato = true;
				} else {
					fato.setValorLogico(false);
					valorLogicoFato = false;
				}
				fato.setFatorCerteza(comboBoxFatoresCerteza.getSelectionModel().getSelectedItem());
				fatorDeCertezaFato = comboBoxFatoresCerteza.getSelectionModel().getSelectedItem();
				hide();
			}
			
			
			
		});
		
		Button btnCancelar = new Button("Cancelar");
		
		btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Alert alertConf = new Alert(AlertType.CONFIRMATION);
				alertConf.setTitle("Venda Passagem Guiada");
				alertConf.setHeaderText("Confirmação de Cancelamento.");
				alertConf.setContentText("Você tem certeza de que quer cancelar a compra de passagem guiada?");
				
				Optional<ButtonType> resposta = alertConf.showAndWait();
				
				if (resposta.get() == ButtonType.OK){
					cancelaConsulta = true;
					hide();
				}
				
			}
			
		});
		
	
		
		hbox5.getChildren().addAll(btnOk, btnCancelar);
		hbox5.setAlignment(Pos.CENTER);
		
		
		
		vbox.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5);
		
		GridPane.setConstraints(vbox, 9, 4);		
		
		gPane.getChildren().add(vbox);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("Venda Passagem Guiada");
		showAndWait();
		
		
		
		
		
	}
	
	public boolean isCancelaConsulta() {
		return cancelaConsulta;
	}

	public void setCancelaConsulta(boolean cancelaConsulta) {
		this.cancelaConsulta = cancelaConsulta;
	}

	public boolean isValorLogicoFato() {
		return valorLogicoFato;
	}

	public void setValorLogicoFato(boolean valorLogicoFato) {
		this.valorLogicoFato = valorLogicoFato;
	}

	public float getFatorDeCertezaFato() {
		return fatorDeCertezaFato;
	}

	public void setFatorDeCertezaFato(float fatorDeCertezaFato) {
		this.fatorDeCertezaFato = fatorDeCertezaFato;
	}
	
	

}
