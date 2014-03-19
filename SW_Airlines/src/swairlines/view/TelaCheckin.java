package swairlines.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
	private Label lblCpfCnpJCliente;
	private Label lblNomeClienteValor;
	private Label lblPrecoTotalBagagemValor;
	private TextField txtPesoBagagem;
	private ComboBox<String> cpfCnpjClientes;
	private ComboBox<Integer> voosIds;
	
	public TelaCheckin() {
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		Scene scene = new Scene(gPane, 500, 400, Color.SILVER);
		setScene(scene);
		
		HBox hbox1 = new HBox(20);
		VBox vbox = new VBox();
		
		ObservableList<Integer> voosIds;
		voosIds = FXCollections.observableArrayList();
		ObservableList<String> clientesIds;
		
		Label lblCpfClientes = new Label("CPF Clientes:");
		cpfCnpjClientes = new ComboBox<String>();
		hbox1.getChildren().addAll(lblCpfClientes, cpfCnpjClientes);
		
		vbox.getChildren().addAll(hbox1);
		GridPane.setConstraints(vbox, 9, 4);
		gPane.getChildren().add(vbox);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
		
		
	}
}
