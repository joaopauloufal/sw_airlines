package swairlines.view;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name TelaCadCliente
 */

import javax.swing.JOptionPane;

import swairlines.dao.ClienteDAO;
import swairlines.model.Cliente;
import swairlines.model.Endereco;
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
	private TextField txtPassaporte;
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
		HBox hbox17 = new HBox(20);
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
		
		Label lblPassaporte = new Label("Passaporte Nº:");
		txtPassaporte = new TextField();
		hbox17.getChildren().addAll(lblPassaporte, txtPassaporte);
		/** Cadastra um Cliente*/
		Button btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(txtRua.getText().isEmpty()||txtCidade.getText().isEmpty()||txtBairro.getText().isEmpty()||txtNumero.getText().isEmpty()||txtEstado.getText().isEmpty()||
						txtNome.getText().isEmpty()||txtCpfCnpj.getText().isEmpty()|| listSexo.getValue().isEmpty()||txtRg.getText().isEmpty()||txtDataDeNascimento.getText().isEmpty()||
						listEstadoCivil.getValue().isEmpty()||txtNacionalidade.getText().isEmpty()||txtTelefoneCelular.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Campo(s) vazio(s).","Atenção!",JOptionPane.ERROR_MESSAGE);
				}
				else{
				String  padrao  =  ("(0[1-9]|[12][0-9]|3[01])[-  /.](0[1-9]|[0-9]|1[012])[-  /.]((19|20)\\d\\d)");

				if (txtDataDeNascimento.getText().matches(padrao) == false) {
					System.out.println("invalido");
					JOptionPane.showMessageDialog(null, "Error, formato da data deve ser 00/00/0000", "Error, formato da data", JOptionPane.ERROR_MESSAGE);					
					
				} else {
					System.out.println("valido");
					
					ClienteDAO clienteDao = new ClienteDAO();
					Endereco endereco = new Endereco(txtRua.getText(), txtCidade.getText(), txtBairro.getText(), txtNumero.getText(),txtEstado.getText());
					Cliente cliente = new Cliente(txtNome.getText(), txtCpfCnpj.getText(), listSexo.getValue(), txtRg.getText(),txtDataDeNascimento.getText(), listEstadoCivil.getValue(), txtNacionalidade.getText(),txtTelefoneCelular.getText(), txtTelefoneResidencial.getText(), txtCartaoDeCredito.getText(),	txtPassaporte.getText(), endereco);
					if (clienteDao.insereCliente(cliente)) {
						JOptionPane.showMessageDialog(null,	"Cliente cadastrado com sucesso!","Cadastro Cliente",JOptionPane.INFORMATION_MESSAGE);
						hide();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao inserir.",	"Erro", JOptionPane.ERROR_MESSAGE);
						hide();
					}
				}
			}}
		});
		
		Button btnCancelar = new Button("Cancelar");
		hbox16.getChildren().addAll(btnCadastrar, btnCancelar);
		hbox16.setAlignment(Pos.CENTER);
		
		btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				hide();
				
			}
			
		});
		
		vbox1.getChildren().addAll(hbox1, hbox2, hbox3, hbox17, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10, hbox11, hbox12, hbox13, hbox14, hbox15, hbox16);
		
		GridPane.setConstraints(vbox1, 9, 4);
		
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
	}
	

}
