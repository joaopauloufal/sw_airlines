package swairlines.view;

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

import javax.swing.JOptionPane;

import swairlines.dao.ClienteDAO;
import swairlines.dao.ContaDeUsuarioDAO;
import swairlines.dao.VooDAO;
import swairlines.model.Cliente;
import swairlines.model.ContaDeUsuario;
import swairlines.model.Voo;

public class TelaCompra extends Stage{
	private TextField txtCliente;
	private PasswordField txtSenha;
	private PasswordField txtSenhaConfirmacao;
	private ComboBox<String> listVoos;
	private ComboBox<String> listClientes;
	private VooDAO vooDao;
	private Voo voo;
	
	public TelaCompra() {
		
		ObservableList<String> voos;
		voos = FXCollections.observableArrayList();		
		ObservableList<String> clientes;
		clientes = FXCollections.observableArrayList();
		
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
		
		Label lblCpfClientes = new Label("Clientes:");
		listClientes = new ComboBox<String>();
		hbox1.getChildren().addAll(lblCpfClientes, listClientes);
		
		ClienteDAO clienteDao = new ClienteDAO();
		
		for (Cliente c : clienteDao.buscaClientes()) {
			clientes.add(c.getCpfCnpj());
		}
		
		listClientes.getItems().addAll(clientes);
		
		Label lblVoos = new Label("Destinos:");
		listVoos = new ComboBox<String>();
		hbox1.getChildren().addAll(lblVoos, listVoos);
		
		vooDao = new VooDAO();
		
		for (Voo v : vooDao.buscaVoos()) {
			voo = v;
			voos.add(v.getDestino());
		}
		
		listVoos.getItems().addAll(voos);
		
//		Label lblNomeCliente = new Label("Nome do cliente:");
//		txtCliente = new TextField();
//		txtCliente.setPrefColumnCount(15);
//		hbox2.getChildren().addAll(lblNomeCliente, txtCliente);
		
//		Label lblUsuario = new Label("Usuário:");
//		txtUsuario = new TextField();
//		txtUsuario.setPrefColumnCount(15);
//		hbox2.getChildren().addAll(lblUsuario, txtUsuario);
//		
//		Label lblSenha = new Label("Senha:");
//		txtSenha = new PasswordField();
//		hbox3.getChildren().addAll(lblSenha, txtSenha);
		
//		Label lblSenhaConfirmacao = new Label("Confirme a senha:");
//		txtSenhaConfirmacao = new PasswordField();
//		hbox4.getChildren().addAll(lblSenhaConfirmacao, txtSenhaConfirmacao);
		

		
				
		Button btnComprar = new Button("Comprar");
		btnComprar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				if (true) {
					if (vooDao.quantPass(voo)) {
						JOptionPane.showMessageDialog(null, "Compra cadastrada com sucesso!");
						hide();
					} else {
						JOptionPane.showMessageDialog(null, "Erro na compra", "Erro", JOptionPane.ERROR_MESSAGE);
						hide();
					}					
					
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
		hbox5.getChildren().addAll(btnComprar, btnCancelar);
		hbox5.setAlignment(Pos.CENTER);
		
		vbox1.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox6, hbox5);
		
		GridPane.setConstraints(vbox1, 9, 4);		
		
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}
}
