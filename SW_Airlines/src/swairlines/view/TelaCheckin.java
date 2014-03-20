package swairlines.view;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name TelaCheckin
 */

import javax.swing.JOptionPane;

import swairlines.dao.BagagemDAO;
import swairlines.dao.ClienteDAO;
import swairlines.dao.VooDAO;
import swairlines.model.Bagagem;
import swairlines.model.Cliente;
import swairlines.model.Voo;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaCheckin extends Stage {
	
	private Label lblValorOrigemVoo;
	private Label lblValorDestinoVoo;
	private Label lblNomeClienteValor;
	private TextField txtPesoBagagem;
	private ComboBox<String> cpfCnpjClientes;
	private ComboBox<String> cVoosIds;
	private VooDAO vooDao;
	private Voo voo;
	private ClienteDAO clienteDao;
	
	public TelaCheckin() {
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		Scene scene = new Scene(gPane, 500, 400, Color.SILVER);
		setScene(scene);
		
		HBox hbox1 = new HBox(20);
		HBox hbox2 = new HBox(20);
		HBox hbox3 = new HBox(20);
		HBox hbox4 = new HBox(20);
		HBox hbox5 = new HBox(20);
		HBox hbox6 = new HBox(20);
		HBox hbox8 = new HBox(20);
		VBox vbox = new VBox(20);
		
		ObservableList<String> voosIds;
		voosIds = FXCollections.observableArrayList();
		ObservableList<String> clientesIds;
		clientesIds = FXCollections.observableArrayList();
		
		Label lblCpfClientes = new Label("CPF Clientes:");
		cpfCnpjClientes = new ComboBox<String>();
		hbox1.getChildren().addAll(lblCpfClientes, cpfCnpjClientes);
		
		Label lblVoosIds = new Label("Voo Nº:");
		cVoosIds = new ComboBox<String>();
		hbox2.getChildren().addAll(lblVoosIds, cVoosIds);
		
		Label lblNomeCliente = new Label("Nome Cliente:");
		lblNomeClienteValor = new Label();
		hbox3.getChildren().addAll(lblNomeCliente, lblNomeClienteValor);
		
		Label lblOrigemVoo = new Label("Origem Voo:");
		lblValorOrigemVoo = new Label();
		hbox4.getChildren().addAll(lblOrigemVoo, lblValorOrigemVoo);
		
		Label lblDestinoVoo = new Label("Destino Voo:");
		lblValorDestinoVoo = new Label();
		hbox5.getChildren().addAll(lblDestinoVoo, lblValorDestinoVoo);
		
		Label lblPesoBagagem = new Label("Peso Bagagem Kg:");
		txtPesoBagagem = new TextField();
		txtPesoBagagem.setPrefColumnCount(5);
		hbox6.getChildren().addAll(lblPesoBagagem, txtPesoBagagem);
		
		/** Realiza o Check-in*/
		
		Button btnSalvarBagagem = new Button("Finalizar");
		btnSalvarBagagem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (lblValorOrigemVoo.getText().isEmpty() || lblNomeClienteValor.getText().isEmpty() || txtPesoBagagem.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Selecione um cliente e/ou um Voo",
							"Atenção!",JOptionPane.ERROR_MESSAGE);				
				}else{
				BagagemDAO bDao = new BagagemDAO();
				Bagagem bagagem = new Bagagem(cpfCnpjClientes.getValue(), lblNomeClienteValor.getText(), Integer.parseInt(cVoosIds.getValue()), lblValorOrigemVoo.getText(), lblValorDestinoVoo.getText(), Double.parseDouble(txtPesoBagagem.getText()));
				if (Double.parseDouble(txtPesoBagagem.getText()) > 70) {
					JOptionPane.showMessageDialog(null, "Você execedeu o limite máximo de peso da bagagem. (Limite - 70 Kg).", "Limite de Peso Bagagem Excedido", JOptionPane.ERROR_MESSAGE);
				} else {					
					if (Double.parseDouble(txtPesoBagagem.getText()) > 15) {
						double pesoUltrapassado = Double.parseDouble(txtPesoBagagem.getText()) - 15;
						double taxa = pesoUltrapassado * 0.5;
						bagagem.setTaxa(taxa);
						if (bDao.insereBagagem(bagagem) && vooDao.inserirPassageiro(voo)) {
							JOptionPane.showMessageDialog(null, "Check-in feito com aumento aumento de " + taxa + "% do valor da venda.", "Check-in com ultrapassagem de Peso Permitido", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Passageiro já realizou a verificaçao para este voo.", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						if (bDao.insereBagagem(bagagem) && vooDao.inserirPassageiro(voo)) {
							JOptionPane.showMessageDialog(null, "Check-in feito.", "Check-in", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Passageiro já realizou a verificaçao para este voo.", "Erro", JOptionPane.ERROR_MESSAGE);
						}
					}
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
		hbox8.getChildren().addAll(btnSalvarBagagem, btnCancelar);
		hbox8.setAlignment(Pos.CENTER);
		
		clienteDao = new ClienteDAO();
		for (Cliente c : clienteDao.buscaClientes()) {
			clientesIds.add(c.getCpfCnpj());
		}
		
		cpfCnpjClientes.getItems().addAll(clientesIds);
		
		vooDao = new VooDAO();		
		for (Voo v : vooDao.buscaVoos()) {
			voo = v;
			voosIds.add(v.getId() +"");
		}
		
		cVoosIds.getItems().addAll(voosIds);
		
		cpfCnpjClientes.setOnHidden(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				Cliente c = clienteDao.buscaClientePorCpf(cpfCnpjClientes.getValue());
				if (cpfCnpjClientes.getSelectionModel().getSelectedIndex() != -1) {
					lblNomeClienteValor.setText(c.getNome());
				}
			}
		});
		
		cVoosIds.setOnHidden(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				if (cVoosIds.getSelectionModel().getSelectedIndex() != -1) {
					Voo v = vooDao.buscaVooPorId(Integer.parseInt(cVoosIds.getValue()));
					lblValorOrigemVoo.setText(v.getOrigem());
					lblValorDestinoVoo.setText(v.getDestino());
				}
				
			}
			
		});
		
		vbox.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox8);
		GridPane.setConstraints(vbox, 9, 4);
		gPane.getChildren().add(vbox);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
		
		
	}
}
