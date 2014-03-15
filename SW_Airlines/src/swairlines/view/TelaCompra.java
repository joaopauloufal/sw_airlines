package swairlines.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import swairlines.dao.ClienteDAO;
import swairlines.dao.VooDAO;
import swairlines.model.Cliente;
import swairlines.model.Voo;

public class TelaCompra extends Stage{
	
	private TextField txtValor;
	private TextField txtParcela;
	private ComboBox<String> listVoos;
	private ComboBox<String> listClientes;
	private VooDAO vooDao;
	private Voo voo;
	private ClienteDAO clienteDao;
	private Label lblNomeCliente;
	private Label lblCartaoCredCliente;
	private Label lblOrigemVoo;
	private Label lblDestinoVoo;
	private Label lblValorVoo;
	private Label lblParcelasCartao;


	
	public TelaCompra() {
		
		ObservableList<String> voos;
		voos = FXCollections.observableArrayList();		
		ObservableList<String> clientes;
		clientes = FXCollections.observableArrayList();
		clienteDao = new ClienteDAO();
		lblNomeCliente = new Label("Nome: ");
		lblCartaoCredCliente = new Label("N° Cartão de Crédito: ");
		lblOrigemVoo = new Label("Origem: ");
		lblDestinoVoo = new Label("Origem: ");	
		lblValorVoo = new Label("Valor: ");
		
	
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		VBox vbox = new VBox(20);
		VBox vbox1 = new VBox(20);
		VBox vbox0 = new VBox(15);
		
		HBox hbox1 = new HBox(20);
		HBox hbox2 = new HBox(20);
		HBox hbox3 = new HBox(20);
		HBox hbox4 = new HBox(20);
		
		
		Scene scene = new Scene(gPane, 450, 350, Color.SILVER);
		setScene(scene);
		
		Label lblCpfClientes = new Label("Clientes:");
		listClientes = new ComboBox<String>();
		hbox1.getChildren().addAll(lblCpfClientes, listClientes);
		
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
			voos.add(v.getId()+"");
		}
		
		listVoos.getItems().addAll(voos);
		listClientes.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Cliente c = clienteDao.buscaCliente(listClientes.getValue());
				lblNomeCliente.setText("Nome: " + c.getNome());	
				lblCartaoCredCliente.setText("N° Cartão de Crédito: " + c.getCartaoDeCredito());
			}
		});
		listClientes.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Cliente c = clienteDao.buscaCliente(listClientes.getValue());
				lblNomeCliente.setText("Nome: " + c.getNome());	
				lblCartaoCredCliente.setText("N° Cartão de Crédito: " + c.getCartaoDeCredito());				
			}
		});

		vbox.getChildren().addAll(lblNomeCliente, lblCartaoCredCliente);
		
		listVoos.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				int id = Integer.parseInt(listVoos.getValue());
				Voo v = vooDao.buscaVoo(id);
				lblOrigemVoo.setText("Origem: " + v.getOrigem());
				lblDestinoVoo.setText("Destino: " + v.getDestino());	
				lblValorVoo.setText("Valor: R$" + v.getValor());
			}
		});
		
		listVoos.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				int id = Integer.parseInt(listVoos.getValue());
				Voo v = vooDao.buscaVoo(id);
				lblOrigemVoo.setText("Origem: " + v.getOrigem());
				lblDestinoVoo.setText("Destino: " + v.getDestino());	
				lblValorVoo.setText("Valor: R$" + v.getValor());				
			}
		});

		vbox.getChildren().addAll(lblOrigemVoo, lblDestinoVoo, lblValorVoo);
		
		final ToggleGroup buttonGroup = new ToggleGroup();
		final RadioButton aVista = new RadioButton("A Vista");
		final RadioButton cartao = new RadioButton("Cartão");
		
		aVista.setToggleGroup(buttonGroup);
		cartao.setToggleGroup(buttonGroup);
		
		aVista.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				lblParcelasCartao.setVisible(false);
				txtParcela.setVisible(false);
			}
		});
		
		cartao.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				lblParcelasCartao.setVisible(true);
				txtParcela.setVisible(true);				
			}
		});
		
		aVista.setSelected(true);
		
		lblParcelasCartao = new Label("Parcelas: ");
		lblParcelasCartao.setVisible(false);
		txtParcela = new TextField();
		txtParcela.setPrefColumnCount(5);
		txtParcela.setVisible(false);
		hbox4.getChildren().addAll(aVista, cartao, lblParcelasCartao, txtParcela);

		
		
		Label lblValorPago = new Label("R$:");
		txtValor = new TextField();
		txtValor.setPrefColumnCount(15);
		hbox2.getChildren().addAll(lblValorPago, txtValor);
		
				
		Button btnComprar = new Button("Comprar");
		btnComprar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if (buttonGroup.getSelectedToggle().equals(aVista)) {
					if (vooDao.quantPass(voo)) {
						JOptionPane.showMessageDialog(null, "A vista - Venda realizada com sucesso!");
						hide();
					} else {
						JOptionPane.showMessageDialog(null, "Erro na compra", "Erro", JOptionPane.ERROR_MESSAGE);
						hide();
					}					
					
				}else if(buttonGroup.getSelectedToggle().equals(cartao)){
					if (vooDao.quantPass(voo)) {
						JOptionPane.showMessageDialog(null, "No cartão - Venda realizada com sucesso!");
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
		hbox3.getChildren().addAll(btnComprar, btnCancelar);
		hbox3.setAlignment(Pos.CENTER);
		
		vbox0.getChildren().addAll(hbox1, vbox, vbox1, hbox4, hbox2, hbox3);
		
		GridPane.setConstraints(vbox0, 9, 4);		
		
		gPane.getChildren().add(vbox0);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}
}
