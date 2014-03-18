package swairlines.view;

import swairlines.model.Venda;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TelaEditVenda extends Stage {
	
	private TextField txtParcelaValor;
	private Label lblVooIdValor;
	private Label lblNomeClienteValor;
	private Label lblCartaoCredClienteValor;
	private Label lblOrigemVooValor;
	private Label lblDestinoVooValor;
	private Label lblValorVoo;
	private Label lblValorVooPreco;
	private ToggleGroup buttonGroup;
	private RadioButton aVista;
	private RadioButton cartao;
	private Button btnCalcular;
	private Label lblCpfClientesValor;
	
	public TelaEditVenda(Venda venda) {
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		Scene scene = new Scene(gPane, 600, 670, Color.SILVER);
		setScene(scene);
		
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
		
		Label lblCpfCnpjCliente = new Label("CPF/CNPJ Cliente");
		lblCpfClientesValor = new Label();
		lblCpfClientesValor.setText(venda.getCpfCnpjCliente());
		hbox1.getChildren().addAll(lblCpfCnpjCliente, lblCpfClientesValor);
		
		Label lblNomeCliente = new Label("Nome Cliente");
		lblNomeClienteValor = new Label();
		lblNomeClienteValor.setText(venda.getNomeCliente());
		hbox2.getChildren().addAll(lblNomeCliente, lblNomeClienteValor);
		
		Label lblVooId = new Label("Voo Nº");
		lblVooIdValor = new Label();
		lblVooIdValor.setText(String.valueOf(venda.getIdVoo()));
		hbox3.getChildren().addAll(lblVooId, lblVooIdValor);
		
	}

}
