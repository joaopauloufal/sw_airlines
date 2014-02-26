package swairlines.gui;

import javax.swing.JOptionPane;

import swairlines.Main;
import swairlines.bd.UsuarioBD;
import swairlines.modelo.ContaDeUsuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaLogin extends BorderPane {
	private TextField txtUsuario;
	private PasswordField txtSenha;
	
	public TelaLogin() {		
		
		HBox hbox1 = new HBox(25);
		HBox hbox2 = new HBox(30);
		HBox hbox3 = new HBox(30);
		VBox vbox = new VBox(20);
		
		txtUsuario = new TextField();
		txtSenha = new PasswordField();
		
		Label lblUsuario = new Label("Usuário:");
		Label lblSenha = new Label("Senha:");				
		
		Button btnLogin = new Button("Login");
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ContaDeUsuario conta = new ContaDeUsuario(txtUsuario.getText(), txtSenha.getText());
				UsuarioBD ubd = new UsuarioBD();
				if (ubd.validarUsuarioBanco(conta)){
					Main.alterarTela(new TelaPrincipal());
				} else {
					JOptionPane.showMessageDialog(null, "Usuário inexistente!", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		Button btnSair = new Button("Sair");
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
				
			}
			
		});
		
		
		hbox1.getChildren().addAll(lblUsuario, txtUsuario);
		hbox2.getChildren().addAll(lblSenha, txtSenha);
		hbox3.getChildren().addAll(btnLogin, btnSair);		
		
		vbox.getChildren().addAll(hbox1, hbox2, hbox3);
		vbox.setAlignment(Pos.CENTER);
		hbox1.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		hbox3.setAlignment(Pos.CENTER);
		setCenter(vbox);
		
		
	}	
	

}
