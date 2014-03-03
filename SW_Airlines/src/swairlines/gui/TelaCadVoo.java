package swairlines.gui;

import javax.swing.JOptionPane;

import swairlines.modelo.Gerente;
import swairlines.modelo.Voo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaCadVoo extends Stage {
	
	private TextField txtOrigem;
	private TextField txtDestino;
	private TextField txtRota;
	private TextField txtHoraPartida;
	private TextField txtDataPartida;
	private TextField txtHoraChegada;
	private ComboBox<String> listTipoVoo;
	private TextField txtDataChegada;
	
	public TelaCadVoo() {
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		HBox hbox1 = new HBox(20);
		HBox hbox2 = new HBox(20);
		HBox hbox3 = new HBox(20);
		HBox hbox4 = new HBox(20);
		HBox hbox5 = new HBox(20);
		HBox hbox6 = new HBox(20);
		HBox hbox7 = new HBox(20);
		HBox hbox8 = new HBox(20);
		HBox hbox9 = new HBox(20);
		VBox vbox1 = new VBox(20);		
		
		Scene scene = new Scene(gPane, 620, 480, Color.SILVER);
		setScene(scene);
		
		Label lblOrigem = new Label("Origem:");
		txtOrigem = new TextField();
		txtOrigem.setPrefColumnCount(25);
		hbox1.getChildren().addAll(lblOrigem, txtOrigem);
		
		Label lblDestino = new Label("Destino:");
		txtDestino = new TextField();
		txtDestino.setPrefColumnCount(25);
		hbox2.getChildren().addAll(lblDestino, txtDestino);
		
		Label lblRota = new Label("Rota:");
		txtRota = new TextField();
		txtRota.setPrefColumnCount(30);
		hbox3.getChildren().addAll(lblRota, txtRota);
		
		Label lblTipoVoo = new Label("Tipo de Voo:");
		listTipoVoo = new ComboBox<String>();
		listTipoVoo.getItems().addAll("Nacional", "Internacional");
		hbox4.getChildren().addAll(lblTipoVoo, listTipoVoo);
		
		Label lblHoraChegada = new Label("Hora de Chegada:");
		txtHoraChegada = new TextField();
		hbox5.getChildren().addAll(lblHoraChegada, txtHoraChegada);
		
		Label lblHoraPartida = new Label("Hora de Partida:");
		txtHoraPartida = new TextField();
		hbox6.getChildren().addAll(lblHoraPartida, txtHoraPartida);
		
		Label lblDataPartida = new Label("Data de Partida:");
		txtDataPartida = new TextField();
		hbox8.getChildren().addAll(lblDataPartida, txtDataPartida);
		
		Label lblDataChegada = new Label("Data de Chegada:");
		txtDataChegada = new TextField();
		hbox9.getChildren().addAll(lblDataChegada, txtDataChegada);
		
		
		Button btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
					Gerente gerente = new Gerente();
					Voo v1 = new Voo(txtOrigem.getText(), txtDestino.getText(), txtRota.getText(), txtHoraPartida.getText(), txtHoraChegada.getText(), txtDataPartida.getText(), txtDataChegada.getText(), listTipoVoo.getValue());
					gerente.insereVoo(v1);
					if(gerente.insereVoo(v1)){
						JOptionPane.showMessageDialog(null, "Voo cadastrado com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar!","Erro", 0);
					}
				
			}
		});
		
		Button btnCancelar = new Button("Cancelar");
		btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				close();
				
			}
			
		});
		hbox7.getChildren().addAll(btnCadastrar, btnCancelar);
		hbox7.setAlignment(Pos.CENTER);
		
		vbox1.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox6, hbox5, hbox8, hbox9, hbox7);
		
		GridPane.setConstraints(vbox1, 9, 4);
		
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}

}
