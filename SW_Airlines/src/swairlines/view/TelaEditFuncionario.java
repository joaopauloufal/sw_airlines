package swairlines.view;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name TelaEditFuncionario
 */

import javax.swing.JOptionPane;

import swairlines.dao.FuncionarioDAO;
import swairlines.model.Endereco;
import swairlines.model.Funcionario;
import swairlines.model.Operador;
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

public class TelaEditFuncionario extends Stage {
	
	private TextField txtNome;
	private Label lblCpfValor;
	private TextField txtRg;
	private ComboBox<String> listCargo;
	private ComboBox<String> listSexo;
	private TextField txtDataDeNascimento;
	private ComboBox<String> listEstadoCivil;
	private TextField txtTelefoneCelular;
	private TextField txtTelefoneResidencial;
	private TextField txtNacionalidade;
	private TextField txtRua;
	private TextField txtCidade;
	private TextField txtEstado;
	private TextField txtBairro;
	private TextField txtNumero;
	
	public TelaEditFuncionario(Funcionario funcionario) {
		

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
		
		
		Scene scene = new Scene(gPane, 600, 670, Color.SILVER);
		setScene(scene);
		
		
		Label lblNome = new Label("Nome:");
		txtNome = new TextField();
		txtNome.setText(funcionario.getNome());
		txtNome.setPrefColumnCount(30);
		hbox1.getChildren().addAll(lblNome, txtNome);
		
		Label lblCpf = new Label("CPF:");
		lblCpfValor = new Label(funcionario.getCpf());
		hbox2.getChildren().addAll(lblCpf, lblCpfValor);
		
		Label lblRg = new Label("RG:");
		txtRg = new TextField();
		txtRg.setText(funcionario.getRg());
		hbox3.getChildren().addAll(lblRg, txtRg);
		
		Label lblCargo = new Label("Cargo:");
		listCargo = new ComboBox<String>();
		listCargo.setValue(funcionario.getCargo());
		listCargo.getItems().addAll("Gerente", "Operador");
		hbox4.getChildren().addAll(lblCargo, listCargo);
		
		Label lblSexo = new Label("Sexo:");
		listSexo = new ComboBox<String>();
		listSexo.setValue(funcionario.getSexo());
		listSexo.getItems().addAll("Masculino", "Feminino");
		hbox5.getChildren().addAll(lblSexo, listSexo);
		
		Label lblDataDeNascimento = new Label("Data de Nascimento:");
		txtDataDeNascimento = new TextField();
		txtDataDeNascimento.setText(funcionario.getDataDeNascimento());
		hbox6.getChildren().addAll(lblDataDeNascimento, txtDataDeNascimento);
		
		Label lblEstadoCivil = new Label("Estado Civil:");
		listEstadoCivil = new ComboBox<String>();
		listEstadoCivil.setValue(funcionario.getEstadoCivil());
		listEstadoCivil.getItems().addAll("Solteiro(a)", "Casado(a)", "Divorciado(a)", "Viuvo(a)");
		hbox7.getChildren().addAll(lblEstadoCivil, listEstadoCivil);
		
		Label lblTelefoneCelular = new Label("Telefone Celular:");
		txtTelefoneCelular = new TextField();
		txtTelefoneCelular.setText(funcionario.getTelefoneCelular());
		txtTelefoneCelular.setPrefColumnCount(16);
		hbox8.getChildren().addAll(lblTelefoneCelular, txtTelefoneCelular);
		
		Label lblTelefoneResidencial = new Label("Telefone Residencial:");
		txtTelefoneResidencial = new TextField();
		txtTelefoneResidencial.setText(funcionario.getTelefoneResidencial());
		txtTelefoneResidencial.setPrefColumnCount(16);
		hbox9.getChildren().addAll(lblTelefoneResidencial, txtTelefoneResidencial);
		
		Label lblNacionalidade = new Label("Nacionalidade:");
		txtNacionalidade = new TextField();
		txtNacionalidade.setText(funcionario.getNacionalidade());
		txtNacionalidade.setPrefColumnCount(20);
		hbox10.getChildren().addAll(lblNacionalidade, txtNacionalidade);
		
		Label lblRua = new Label("Rua:");
		txtRua = new TextField();
		txtRua.setText(funcionario.getEndereco().getRua());
		txtRua.setPrefColumnCount(30);
		hbox11.getChildren().addAll(lblRua, txtRua);
		
		Label lblBairro = new Label("Bairro:");
		txtBairro = new TextField();
		txtBairro.setText(funcionario.getEndereco().getBairro());
		txtBairro.setPrefColumnCount(25);
		hbox12.getChildren().addAll(lblBairro, txtBairro);
		
		Label lblNumero = new Label("Número:");
		txtNumero = new TextField();
		txtNumero.setText(funcionario.getEndereco().getNumero());
		hbox13.getChildren().addAll(lblNumero, txtNumero);
		
		Label lblCidade = new Label("Cidade:");
		txtCidade = new TextField();
		txtCidade.setText(funcionario.getEndereco().getCidade());
		txtCidade.setPrefColumnCount(25);
		hbox14.getChildren().addAll(lblCidade, txtCidade);
		
		Label lblEstado = new Label("Estado:");
		txtEstado = new TextField();
		txtEstado.setText(funcionario.getEndereco().getEstado());
		txtEstado.setPrefColumnCount(25);
		hbox15.getChildren().addAll(lblEstado, txtEstado);
		
		/**Atualiza os dados*/
		
		Button btnAtualizar = new Button("Atualizar");
		btnAtualizar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				String  padrao  =  ("(0[1-9]|[12][0-9]|3[01])[-  /.](0[1-9]|[0-9]|1[012])[-  /.]((19|20)\\d\\d)");

				if(txtRua.getText().isEmpty()||txtCidade.getText().isEmpty()||txtBairro.getText().isEmpty()||txtNumero.getText().isEmpty()||txtEstado.getText().isEmpty()||
						txtNome.getText().isEmpty()|| listSexo.getValue().isEmpty()|| txtRg.getText().isEmpty()|| txtDataDeNascimento.getText().isEmpty()|| 
						txtTelefoneCelular.getText().isEmpty()||listCargo.getValue().isEmpty()||txtTelefoneResidencial.getText().isEmpty()||txtNacionalidade.getText().isEmpty()||listEstadoCivil.getValue().isEmpty()){
					JOptionPane.showMessageDialog(null, "Campo(s) vazio(s)!","Alerta!",JOptionPane.ERROR_MESSAGE);
				}
				else{
				if (txtDataDeNascimento.getText().matches(padrao) == false) {
					System.out.println("invalido");
					JOptionPane.showMessageDialog(null, "Error, formato da data deve ser 00/00/0000", "Error, formato da data", JOptionPane.ERROR_MESSAGE);					
					
				} else {
					System.out.println("valido");
					FuncionarioDAO funcDao = new FuncionarioDAO();
					Endereco endereco = new Endereco(txtRua.getText(), txtCidade.getText(), txtBairro.getText(), txtNumero.getText(), txtEstado.getText());
					Funcionario f1 = new Operador(txtNome.getText(), listSexo.getValue(), lblCpfValor.getText(), txtRg.getText(), txtDataDeNascimento.getText(), txtTelefoneCelular.getText(), listCargo.getValue(), txtTelefoneResidencial.getText(), txtNacionalidade.getText(), listEstadoCivil.getValue(), endereco);
					if (funcDao.alteraFuncionario(f1)) {
						JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!", "Atualização Funcionário", JOptionPane.INFORMATION_MESSAGE);
						hide();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar.", "Erro", JOptionPane.ERROR_MESSAGE);
						hide();
					}
					
				}
				
				}

			}
		});
		Button btnCancelar = new Button("Cancelar");
		hbox16.getChildren().addAll(btnAtualizar, btnCancelar);
		hbox16.setAlignment(Pos.CENTER);
		
		btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				hide();
				
			}
			
		});
		
		vbox1.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10, hbox11, hbox12, hbox13, hbox14, hbox15, hbox16);
		
		GridPane.setConstraints(vbox1, 9, 4);
	
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}

}
