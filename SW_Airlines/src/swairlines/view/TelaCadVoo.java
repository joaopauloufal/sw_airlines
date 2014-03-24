package swairlines.view;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name TelaCadVoo
 */

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

import javax.swing.JOptionPane;

import swairlines.dao.VooDAO;
import swairlines.model.Voo;

public class TelaCadVoo extends Stage{
	
	private TextField txtOrigem;
	private TextField txtDestino;
	private TextField txtRota;
	private TextField txtHoraPartida;
	private TextField txtDataPartida;
	private TextField txtHoraChegada;
	private ComboBox<String> listTipoVoo;
	private TextField txtDataChegada;
	private TextField txtValor;
	private TextField txtAeronave;
	
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
		HBox hbox10 = new HBox(20);
		HBox hbox11 = new HBox(20);
		VBox vbox1 = new VBox(20);		
		
		Scene scene = new Scene(gPane, 620, 540, Color.SILVER);
		setScene(scene);
		
		Label lblAeronave = new Label("Aeronave Nº:");
		txtAeronave = new TextField();
		txtAeronave.setPrefColumnCount(10);
		hbox11.getChildren().addAll(lblAeronave, txtAeronave);
		
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
		
		Label lblValor = new Label("Valor R$:");
		txtValor = new TextField();
		hbox10.getChildren().addAll(lblValor, txtValor);
		
		
		/**Cadastra um voo*/
		Button btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event){
				if(txtAeronave.getText().isEmpty() ||txtOrigem.getText().isEmpty()||txtDestino.getText().isEmpty()||
						txtRota.getText().isEmpty()|| txtHoraPartida.getText().isEmpty()|| txtHoraChegada.getText().isEmpty()||
						txtDataPartida.getText().isEmpty()|| txtDataChegada.getText().isEmpty()|| listTipoVoo.getValue().isEmpty()||
						txtValor.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Algum campo está nulo!","Campo Nulo!",JOptionPane.ERROR_MESSAGE);
				}
				else{
					String  padraoData  =  ("(0[1-9]|[12][0-9]|3[01])[-  /.](0[1-9]|[0-9]|1[012])[-  /.]((19|20)\\d\\d)");
					String  padraoHora  =  ("(0[0-9]|1[0-9]|2[0-3])[-  :.]([0-5][0-9])");

					if ((txtDataPartida.getText().matches(padraoData) == false) && (txtDataChegada.getText().matches(padraoData) == false) && (txtHoraChegada.getText().matches(padraoHora) == false) && (txtHoraPartida.getText().matches(padraoHora) == false) ) {
						System.out.println("invalido");
						JOptionPane.showMessageDialog(null, "Error, formato da data deve ser 00/00/0000 e o da hora deve ser 00:00", "Error, formato da data ou da hora inválido", JOptionPane.ERROR_MESSAGE);
					}
				 else {
					System.out.println("valido");
					
					VooDAO vooDao = new VooDAO();
					Voo v1 = new Voo(txtAeronave.getText(),txtOrigem.getText(), txtDestino.getText(), txtRota.getText(), txtHoraPartida.getText(), txtHoraChegada.getText(), txtDataPartida.getText(), txtDataChegada.getText(), listTipoVoo.getValue(), Double.parseDouble(txtValor.getText()));
					if(vooDao.insereVoo(v1)){
						JOptionPane.showMessageDialog(null, "Voo cadastrado com sucesso!", "Cadastro Voo", JOptionPane.INFORMATION_MESSAGE);
						hide();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar!", "Erro", JOptionPane.ERROR_MESSAGE);
						hide();
					}
				}
	
			}}
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
		
		vbox1.getChildren().addAll(hbox11, hbox1, hbox2, hbox3, hbox4, hbox6, hbox5, hbox8, hbox9, hbox10, hbox7);
		
		GridPane.setConstraints(vbox1, 9, 4);
		
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}

}
