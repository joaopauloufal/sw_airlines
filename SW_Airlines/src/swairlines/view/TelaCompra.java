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

public class TelaCompra extends Stage {
	
	private Label lblValorParcela;
	private TextField txtParcela;
	private ComboBox<String> listVoos;
	private ComboBox<String> listClientes;
	private VooDAO vooDao;
	private Voo voo;
	private ClienteDAO clienteDao;
	private Label lblNomeCliente;
	private Label lblNomeClienteValor;
	private Label lblCartaoCredCliente;
	private Label lblCartaoCredClienteValor;
	private Label lblOrigemVoo;
	private Label lblOrigemVooValor;
	private Label lblDestinoVoo;
	private Label lblDestinoVooValor;
	private Label lblValorVoo;
	private Label lblValorVooPreco;
	private Label lblParcelasCartao;
	private ToggleGroup buttonGroup;
	private RadioButton aVista;
	private RadioButton cartao;
	private Label lblParcela;
	private Button btnCalcular;

	
	public TelaCompra() {
		
		ObservableList<String> voos;
		voos = FXCollections.observableArrayList();		
		ObservableList<String> clientes;
		clientes = FXCollections.observableArrayList();
		clienteDao = new ClienteDAO();
		lblNomeCliente = new Label("Nome: ");
		lblNomeClienteValor = new Label();
		lblCartaoCredCliente = new Label("N° Cartão de Crédito: ");
		lblCartaoCredClienteValor = new Label();
		lblOrigemVoo = new Label("Origem: ");
		lblOrigemVooValor = new Label();
		lblDestinoVoo = new Label("Destino: ");
		lblDestinoVooValor = new Label();
		lblValorVoo = new Label("Valor: ");
		lblValorVooPreco = new Label();
		
	
		
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
		HBox hbox5 = new HBox(6);
		HBox hbox6 = new HBox(20);
		HBox hbox7 = new HBox(20);
		HBox hbox8 = new HBox(20);
		HBox hbox9 = new HBox(20);
		
		
		Scene scene = new Scene(gPane, 500, 400, Color.SILVER);
		setScene(scene);
		
		Label lblRgClientes = new Label("RG Clientes:");
		listClientes = new ComboBox<String>();
		hbox1.getChildren().addAll(lblRgClientes, listClientes);
		
		btnCalcular = new Button("Calcular");
		btnCalcular.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					float resultado = Float.parseFloat(lblValorVooPreco.getText()) / Integer.parseInt(txtParcela.getText());
					lblValorParcela.setText(String.valueOf(resultado));
					
				} catch (NumberFormatException n){
					JOptionPane.showMessageDialog(null, "Verifique se existe algum campo em branco.", "Erro ao calcular", JOptionPane.ERROR_MESSAGE);
					
				}
				
				
			}
			
		});
		
		for (Cliente c : clienteDao.buscaClientes()) {
			clientes.add(c.getRg());
		}
		
		listClientes.getItems().addAll(clientes);
		
		Label lblVoos = new Label("Voos ID's:");
		listVoos = new ComboBox<String>();
		hbox1.getChildren().addAll(lblVoos, listVoos);
		
		vooDao = new VooDAO();
		
		for (Voo v : vooDao.buscaVoos()) {
			voo = v;
			voos.add(v.getId()+"");
		}
		
		listVoos.getItems().addAll(voos);
		
		listClientes.setOnHidden(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				Cliente c = clienteDao.buscaClientePorRg(listClientes.getValue());
				if (listClientes.getSelectionModel().getSelectedIndex() != -1) {
					lblNomeClienteValor.setText(c.getNome());	
					lblCartaoCredClienteValor.setText(c.getCartaoDeCredito());					
					
				}				
				
				
			}
			
		});
		
		hbox5.getChildren().addAll(lblCartaoCredCliente, lblCartaoCredClienteValor);
		hbox7.getChildren().addAll(lblNomeCliente, lblNomeClienteValor);

		vbox.getChildren().addAll(hbox7, hbox5);
		
		listVoos.setOnHidden(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (listVoos.getSelectionModel().getSelectedIndex() != -1) {
					int id = Integer.parseInt(listVoos.getValue());
					Voo v = vooDao.buscaVooPorId(id);
					lblOrigemVooValor.setText(v.getOrigem());
					lblDestinoVooValor.setText(v.getDestino());	
					lblValorVooPreco.setText(String.valueOf(v.getValor()));					
					
					
				}
				
			}
			
		});
		
		hbox6.getChildren().addAll(lblValorVoo, lblValorVooPreco);
		hbox8.getChildren().addAll(lblOrigemVoo, lblOrigemVooValor);
		hbox9.getChildren().addAll(lblDestinoVoo, lblDestinoVooValor);

		vbox.getChildren().addAll(hbox8, hbox9, hbox6);
		
		buttonGroup = new ToggleGroup();
		aVista = new RadioButton("A Vista");
		cartao = new RadioButton("Cartão");
		
		aVista.setToggleGroup(buttonGroup);
		cartao.setToggleGroup(buttonGroup);
		
		aVista.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				lblParcelasCartao.setVisible(false);
				txtParcela.setVisible(false);
				lblParcela.setVisible(false);
				lblValorParcela.setVisible(false);
				btnCalcular.setVisible(false);
			}
		});
		
		cartao.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				lblParcelasCartao.setVisible(true);
				txtParcela.setVisible(true);
				lblParcela.setVisible(true);
				lblValorParcela.setVisible(true);
				btnCalcular.setVisible(true);
				
			}
		});
		
		aVista.setSelected(true);
		
		lblParcelasCartao = new Label("Parcelas: ");
		lblParcelasCartao.setVisible(false);
		txtParcela = new TextField();
		txtParcela.setPrefColumnCount(5);
		txtParcela.setVisible(false);
		
		
		hbox4.getChildren().addAll(aVista, cartao, lblParcelasCartao, txtParcela);

		
		
		lblParcela = new Label("Valor Parcela R$:");
		lblParcela.setVisible(false);
		lblValorParcela = new Label();
		lblValorParcela.setVisible(false);
		btnCalcular.setVisible(false);
		hbox2.getChildren().addAll(lblParcela, lblValorParcela, btnCalcular);
		
				
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
					
				} else if (buttonGroup.getSelectedToggle().equals(cartao)){
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
