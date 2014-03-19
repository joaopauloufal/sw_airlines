package swairlines.view;

import javax.swing.JOptionPane;

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
import swairlines.dao.VooDAO;
import swairlines.model.Voo;

public class TelaEditVoo extends Stage {
	
	protected TextField txtOrigem;
	private TextField txtDestino;
	private TextField txtRota;
	private TextField txtHoraPartida;
	private TextField txtDataPartida;
	private TextField txtHoraChegada;
	private ComboBox<String> listTipoVoo;
	private TextField txtDataChegada;
	private TextField txtValor;
	private Label lblValorId;
	private TextField txtAeronave;
	
	public TelaEditVoo(Voo v1) {
		
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
		HBox hbox10 = new HBox(20);
		HBox hbox11 = new HBox(20);
		HBox hbox12 = new HBox(20);
		VBox vbox1 = new VBox(20);		
		
		Scene scene = new Scene(gPane, 620, 550, Color.SILVER);
		setScene(scene);
		
		Label lblId = new Label("Id:");
		lblValorId = new Label(String.valueOf(v1.getId()));
		hbox10.getChildren().addAll(lblId, lblValorId);
		
		Label lblAeronave = new Label("Aeronave Nº:");
		txtAeronave = new TextField();
		txtAeronave.setText(v1.getAeronaveNumero());
		txtAeronave.setPrefColumnCount(10);
		hbox12.getChildren().addAll(lblAeronave, txtAeronave);
		
		Label lblOrigem = new Label("Origem:");
		txtOrigem = new TextField();
		txtOrigem.setPrefColumnCount(25);
		txtOrigem.setText(v1.getOrigem());
		hbox1.getChildren().addAll(lblOrigem, txtOrigem);
		
		Label lblDestino = new Label("Destino:");
		txtDestino = new TextField();
		txtDestino.setPrefColumnCount(25);
		txtDestino.setText(v1.getDestino());
		hbox2.getChildren().addAll(lblDestino, txtDestino);
		
		Label lblRota = new Label("Rota:");
		txtRota = new TextField();
		txtRota.setPrefColumnCount(30);
		txtRota.setText(v1.getRota());
		hbox3.getChildren().addAll(lblRota, txtRota);
		
		Label lblTipoVoo = new Label("Tipo de Voo:");
		listTipoVoo = new ComboBox<String>();
		listTipoVoo.getItems().addAll("Nacional", "Internacional");
		listTipoVoo.setValue(v1.getTipoVoo());
		hbox4.getChildren().addAll(lblTipoVoo, listTipoVoo);
		
		Label lblHoraChegada = new Label("Hora de Chegada:");
		txtHoraChegada = new TextField();
		txtHoraChegada.setText(v1.getHoraChegada());
		hbox5.getChildren().addAll(lblHoraChegada, txtHoraChegada);
		
		Label lblHoraPartida = new Label("Hora de Partida:");
		txtHoraPartida = new TextField();
		txtHoraPartida.setText(v1.getHoraPartida());
		hbox6.getChildren().addAll(lblHoraPartida, txtHoraPartida);
		
		Label lblDataPartida = new Label("Data de Partida:");
		txtDataPartida = new TextField();
		txtDataPartida.setText(v1.getDataPartida());
		hbox8.getChildren().addAll(lblDataPartida, txtDataPartida);
		
		Label lblDataChegada = new Label("Data de Chegada:");
		txtDataChegada = new TextField();
		txtDataChegada.setText(v1.getDataChegada());
		hbox9.getChildren().addAll(lblDataChegada, txtDataChegada);
		
		Label lblValor = new Label("Valor:");
		txtValor = new TextField();
		txtValor.setText(v1.getValor() + "");
		hbox11.getChildren().addAll(lblValor, txtValor);
		
		
		Button btnAtualizar = new Button("Atualizar");
		btnAtualizar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {		
				
				String  padrao  =  ("(0[1-9]|[12][0-9]|3[01])[-  /.](0[1-9]|[0-9]|1[012])[-  /.]((19|20)\\d\\d)");

				if ((txtDataPartida.getText().matches(padrao) == false) && (txtDataChegada.getText().matches(padrao) == false)) {
					System.out.println("invalido");
					JOptionPane.showMessageDialog(null, "Error, formato da data deve ser 00/00/0000", "Error, formato da data", JOptionPane.ERROR_MESSAGE);					
					
				} else {
					System.out.println("valido");
					
					Voo v1 = new Voo(txtAeronave.getText(), txtOrigem.getText(), txtDestino.getText(), txtRota.getText(), txtHoraPartida.getText(), txtHoraChegada.getText(), txtDataPartida.getText(), txtDataChegada.getText(), listTipoVoo.getValue(), Double.parseDouble(txtValor.getText()));
					v1.setId(Integer.parseInt(lblValorId.getText()));
					VooDAO vooDao = new VooDAO();
					if (vooDao.alteraVoo(v1)){
						JOptionPane.showMessageDialog(null, "Voo atualizado com sucesso!");
						hide();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar!","Erro", JOptionPane.ERROR_MESSAGE);
						hide();
					}
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
		hbox7.getChildren().addAll(btnAtualizar, btnCancelar);
		hbox7.setAlignment(Pos.CENTER);
		
		vbox1.getChildren().addAll(hbox10, hbox12, hbox1, hbox2, hbox3, hbox4, hbox6, hbox5, hbox8, hbox9, hbox11, hbox7);
		
		GridPane.setConstraints(vbox1, 9, 4);
		
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}

}
