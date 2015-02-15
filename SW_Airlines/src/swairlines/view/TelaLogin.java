package swairlines.view;
/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name TelaLogin
 */

import javax.swing.JOptionPane;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import swairlines.Main;
import swairlines.model.ContaDeUsuario;
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

@SuppressWarnings("deprecation")
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
		
		/**Verifica os usuário e senha*/
		
		Button btnLogin = new Button("Login");
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {					
				ContaDeUsuario conta = new ContaDeUsuario(txtUsuario.getText(), txtSenha.getText());
				if (conta.autenticar(conta)) {
					Main.alterarTela(new TelaInicial(conta.getFuncionario()));
				} else {
					JOptionPane.showMessageDialog(null, "Usuário Inexistente.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
				}
			}			
			
		});
		
		Button btnSair = new Button("Sair");
		btnSair.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Action resposta = Dialogs.create()
				.title("Confirmação")
				.masthead("Confirmação de Saída")
				.message("Deseja realmente sair do sistema?")
				.styleClass(Dialog.STYLE_CLASS_NATIVE)
				
				.showConfirm();
				
				if (resposta == Dialog.ACTION_YES){
					System.exit(0);
				} 
				
				
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
