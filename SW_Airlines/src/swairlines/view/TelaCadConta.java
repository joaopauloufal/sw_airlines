package swairlines.view;

import javax.swing.JOptionPane;

import swairlines.dao.ContaDeUsuarioDAO;
import swairlines.dao.FuncionarioDAO;
import swairlines.model.ContaDeUsuario;
import swairlines.model.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaCadConta extends Stage {

	private TextField txtUsuario;
	private PasswordField txtSenha;
	private PasswordField txtSenhaConfirmacao;
	private ComboBox<String> listTipoConta;
	private ComboBox<String> listCpfFuncionarios;
	
	public TelaCadConta() {
		
		ObservableList<String> funcionariosCpf;
		funcionariosCpf = FXCollections.observableArrayList();		
		
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
		VBox vbox1 = new VBox(15);
		
		Scene scene = new Scene(gPane, 450, 270, Color.SILVER);
		setScene(scene);
		
		Label lblTipoConta = new Label("Tipo de Conta:");
		listTipoConta = new ComboBox<String>();
		listTipoConta.getItems().addAll("Administrador", "Operador");
		hbox1.getChildren().addAll(lblTipoConta, listTipoConta);
		
		Label lblUsuario = new Label("Usuário:");
		txtUsuario = new TextField();
		txtUsuario.setPrefColumnCount(15);
		hbox2.getChildren().addAll(lblUsuario, txtUsuario);
		
		Label lblSenha = new Label("Senha:");
		txtSenha = new PasswordField();
		hbox3.getChildren().addAll(lblSenha, txtSenha);
		
		Label lblSenhaConfirmacao = new Label("Confirme a senha:");
		txtSenhaConfirmacao = new PasswordField();
		hbox4.getChildren().addAll(lblSenhaConfirmacao, txtSenhaConfirmacao);
		
		Label lblCpfFuncionario = new Label("CPF do Funcionário:");
		listCpfFuncionarios = new ComboBox<String>();
		hbox6.getChildren().addAll(lblCpfFuncionario, listCpfFuncionarios);
		
		FuncionarioDAO funcionarioDao = new FuncionarioDAO();
		
		for (Funcionario f : funcionarioDao.buscaFuncionarios()) {
			funcionariosCpf.add(f.getCpf());
		}
		
		listCpfFuncionarios.getItems().addAll(funcionariosCpf);
		
				
		Button btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ContaDeUsuarioDAO contaDao = new ContaDeUsuarioDAO();
				ContaDeUsuario c1 = new ContaDeUsuario(txtUsuario.getText(), txtSenha.getText(), listTipoConta.getValue(), listCpfFuncionarios.getValue());
				if (c1.getSenha().equals(txtSenhaConfirmacao.getText())) {
					if (contaDao.insereContaDeUsuario(c1)) {
						JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!");
						hide();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao inserir!", "Erro", JOptionPane.ERROR_MESSAGE);
						hide();
					}					
					
				} else {
					JOptionPane.showMessageDialog(null, "A senha digitada não confere com a confirmação!", "Erro de Confirmação", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		Button btnCancelar = new Button("Cancelar");
		btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				hide();
				
			}
			
		});
		hbox5.getChildren().addAll(btnCadastrar, btnCancelar);
		hbox5.setAlignment(Pos.CENTER);
		
		vbox1.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox6, hbox5);
		
		GridPane.setConstraints(vbox1, 9, 4);		
		
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}
	
}
