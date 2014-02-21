package swairlines.gui;

import swairlines.bd.ClienteBD;
import swairlines.modelo.Cliente;
import swairlines.modelo.Endereco;
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

public class TelaCadCliente extends Stage {
	
	private TextField txtNome;
	private TextField txtCpfCnpj;
	private TextField txtRg;
	private ComboBox<String> listSexo;
	private TextField txtNacionalidade;
	private TextField txtDataDeNascimento;
	private TextField txtTelefoneCelular;
	private TextField txtTelefoneResidencial;
	private ComboBox<String> listEstadoCivil;
	private TextField txtCartaoDeCredito;
	private TextField txtRua;
	private TextField txtNumero;
	private TextField txtBairro;
	private TextField txtCidade;
	private TextField txtEstado;
	
	
	public TelaCadCliente() {
		
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
		HBox hbox13 = new HBox(20);
		HBox hbox14 = new HBox(20);
		HBox hbox15 = new HBox(20);
		HBox hbox16 = new HBox(20);
		VBox vbox1 = new VBox(15);
		
		
		Scene scene = new Scene(gPane, 600, 680, Color.SILVER);
		setScene(scene);
		
		Label lblNome = new Label("Nome:");
		txtNome = new TextField();
		txtNome.setPrefColumnCount(30);
		hbox1.getChildren().addAll(lblNome, txtNome);		
		
		Label lblCpfCnpj = new Label("CPF/CNPJ:");
		txtCpfCnpj = new TextField();
		hbox2.getChildren().addAll(lblCpfCnpj, txtCpfCnpj);		
		
		Label lblRg = new Label("RG:");
		txtRg = new TextField();
		hbox3.getChildren().addAll(lblRg, txtRg);
		
		Label lblSexo = new Label("Sexo:");
		listSexo = new ComboBox<String>();
		listSexo.getItems().addAll("Masculino", "Feminino");
		hbox4.getChildren().addAll(lblSexo, listSexo);
		
		Label lblNacionalidade = new Label("Nacionalidade:");
		txtNacionalidade = new TextField();
		txtNacionalidade.setPrefColumnCount(20);
		hbox5.getChildren().addAll(lblNacionalidade, txtNacionalidade);
		
		Label lblDataDeNascimento = new Label("Data de Nascimento:");
		txtDataDeNascimento = new TextField();
		hbox6.getChildren().addAll(lblDataDeNascimento, txtDataDeNascimento);
		
		Label lblTelefoneCelular = new Label("Telefone Celular:");
		txtTelefoneCelular = new TextField();
		txtTelefoneCelular.setPrefColumnCount(16);
		hbox7.getChildren().addAll(lblTelefoneCelular, txtTelefoneCelular);
		
		Label lblTelefoneResidencial = new Label("Telefone Residencial:");
		txtTelefoneResidencial = new TextField();
		txtTelefoneResidencial.setPrefColumnCount(16);
		hbox8.getChildren().addAll(lblTelefoneResidencial, txtTelefoneResidencial);
		
		Label lblEstadoCivil = new Label("Estado Civil:");
		listEstadoCivil = new ComboBox<String>();
		listEstadoCivil.getItems().addAll("Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viuvo(a)");
		hbox9.getChildren().addAll(lblEstadoCivil, listEstadoCivil);
		
		Label lblCartaoDeCredito = new Label("Cartão de Crédito:");
		txtCartaoDeCredito = new TextField();
		txtCartaoDeCredito.setPrefColumnCount(16);
		hbox10.getChildren().addAll(lblCartaoDeCredito, txtCartaoDeCredito);
		
		Label lblRua = new Label("Rua:");
		txtRua = new TextField();
		txtRua.setPrefColumnCount(30);
		hbox11.getChildren().addAll(lblRua, txtRua);
		
		Label lblNumero = new Label("Número:");
		txtNumero = new TextField();
		hbox12.getChildren().addAll(lblNumero, txtNumero);
		
		Label lblBairro = new Label("Bairro:");
		txtBairro = new TextField();
		txtBairro.setPrefColumnCount(25);
		hbox13.getChildren().addAll(lblBairro, txtBairro);
		
		Label lblCidade = new Label("Cidade:");
		txtCidade = new TextField();
		txtCidade.setPrefColumnCount(25);
		hbox14.getChildren().addAll(lblCidade, txtCidade);
		
		Label lblEstado = new Label("Estado:");
		txtEstado = new TextField();
		txtEstado.setPrefColumnCount(25);
		hbox15.getChildren().addAll(lblEstado, txtEstado);
		
		Button btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Cliente c1  = new Cliente(txtNome.getText(), txtCpfCnpj.getText(), listSexo.getValue(), txtRg.getText(), txtDataDeNascimento.getText(), listEstadoCivil.getValue(), txtNacionalidade.getText(), txtTelefoneCelular.getText(), txtTelefoneResidencial.getText(), txtCartaoDeCredito.getText());
				Endereco e1 = new Endereco(txtRua.getText(), txtCidade.getText(), txtBairro.getText(), txtNumero.getText(), txtEstado.getText());
				ClienteBD cb1 = new ClienteBD();
				cb1.insere(c1, e1);
				
			}
		});
		
		Button btnCancelar = new Button("Cancelar");
		hbox16.getChildren().addAll(btnCadastrar, btnCancelar);
		hbox16.setAlignment(Pos.CENTER);
		
		vbox1.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10, hbox11, hbox12, hbox13, hbox14, hbox15, hbox16);
		
		GridPane.setConstraints(vbox1, 9, 4);
		
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
	}
	

}
