package swairlines.view;

import javax.swing.JOptionPane;

import swairlines.dao.VendaDAO;
import swairlines.model.Venda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class TelaEditVenda extends Stage {
	
	private TextField txtParcelaValor;
	private Label lblVooIdValor;
	private Label lblNomeClienteValor;
	private Label lblCartaoCredClienteValor;
	private Label lblOrigemVooValor;
	private Label lblDestinoVooValor;
	private Label lblParcelaValor;
	private Label lblValorVooPreco;
	private ToggleGroup buttonGroup;
	private RadioButton aVista;
	private RadioButton cartao;
	private Button btnCalcular;
	private Label lblCpfClientesValor;
	private HBox hbox7;
	private HBox hbox9;
	
	public TelaEditVenda(Venda venda) {
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		Scene scene = new Scene(gPane, 400, 500, Color.SILVER);
		setScene(scene);
		
		HBox hbox1 = new HBox(20);
		HBox hbox2 = new HBox(20);
		HBox hbox3 = new HBox(20);
		HBox hbox4 = new HBox(20);
		HBox hbox5 = new HBox(20);
		HBox hbox6 = new HBox(20);
		hbox7 = new HBox(20);
		HBox hbox8 = new HBox(20);
		hbox9 = new HBox(20);
		HBox hbox10 = new HBox(20);
		HBox hbox11 = new HBox(20);
		VBox vbox = new VBox(20);
		
		Label lblCpfCnpjCliente = new Label("CPF/CNPJ Cliente:");
		lblCpfClientesValor = new Label();
		lblCpfClientesValor.setText(venda.getCpfCnpjCliente());
		hbox1.getChildren().addAll(lblCpfCnpjCliente, lblCpfClientesValor);
		
		Label lblNomeCliente = new Label("Nome Cliente:");
		lblNomeClienteValor = new Label();
		lblNomeClienteValor.setText(venda.getNomeCliente());
		hbox2.getChildren().addAll(lblNomeCliente, lblNomeClienteValor);
		
		Label lblCartaoCliente = new Label("Cartão de Crédito:");
		lblCartaoCredClienteValor = new Label();
		lblCartaoCredClienteValor.setText(venda.getCartaoCreditoCliente());
		hbox6.getChildren().addAll(lblCartaoCliente, lblCartaoCredClienteValor);
		
		Label lblVooId = new Label("Voo Nº:");
		lblVooIdValor = new Label();
		lblVooIdValor.setText(String.valueOf(venda.getIdVoo()));
		hbox3.getChildren().addAll(lblVooId, lblVooIdValor);
		
		Label lblOrigemVoo = new Label("Origem:");
		lblOrigemVooValor = new Label();
		lblOrigemVooValor.setText(venda.getOrigemVoo());
		hbox4.getChildren().addAll(lblOrigemVoo, lblOrigemVooValor);
		
		Label lblDestinoVoo = new Label("Destino:");
		lblDestinoVooValor = new Label();
		lblDestinoVooValor.setText(venda.getDestinoVoo());
		hbox5.getChildren().addAll(lblDestinoVoo, lblDestinoVooValor);
		
		buttonGroup = new ToggleGroup();
		aVista = new RadioButton("À Vista");
		cartao = new RadioButton("Cartão");
		buttonGroup.getToggles().addAll(aVista, cartao);
		if (venda.getCartaoCreditoCliente().equals("")) {
			cartao.setVisible(false);
		}
				
		hbox11.getChildren().addAll(aVista, cartao);	
		
		Label lblValorVoo = new Label("Valor R$:");
		lblValorVooPreco = new Label();
		lblValorVooPreco.setText(String.valueOf(venda.getValorVoo()));
		hbox8.getChildren().addAll(lblValorVoo, lblValorVooPreco);
		
		Label lblParcelasCartao = new Label("Nº de Parcelas:");		
		txtParcelaValor = new TextField();
		txtParcelaValor.setPrefColumnCount(5);
		txtParcelaValor.setText(String.valueOf(venda.getParcelas()));	
		hbox7.getChildren().addAll(lblParcelasCartao, txtParcelaValor);	
		hbox7.setVisible(false);
		
		Label lblParcela = new Label("Valor Parcela R$:");
		lblParcelaValor = new Label();
		lblParcelaValor.setText(String.valueOf(venda.getValorParcela()));
		btnCalcular = new Button("Calcular");
		hbox9.getChildren().addAll(lblParcela, lblParcelaValor, btnCalcular);
		hbox9.setVisible(false);
		
		aVista.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				hbox7.setVisible(false);
				hbox9.setVisible(false);
				
			}
		});
		
		cartao.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				hbox7.setVisible(true);
				hbox9.setVisible(true);
				
			}
			
		});
		
		if (venda.getTipoVenda().equals("Cartão")) {
			cartao.setSelected(true);
			hbox7.setVisible(true);
			hbox9.setVisible(true);
		} else {
			aVista.setSelected(true);
		}		
		
		
		btnCalcular.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					float resultado = Float.parseFloat(lblValorVooPreco.getText()) / Integer.parseInt(txtParcelaValor.getText());
					
					if (Float.isInfinite(resultado)) {
						JOptionPane.showMessageDialog(null, "Número de Parcelas não pode ser 0", "Erro no Campo Parcelas", JOptionPane.WARNING_MESSAGE);		
					
					} else {
						lblParcelaValor.setText(String.valueOf(resultado));
					}
					
					
				} catch (NumberFormatException n){
					JOptionPane.showMessageDialog(null, "Verifique se existe algum campo em branco.", "Erro ao calcular", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
			
		});
		
		Button btnAtualizarVenda = new Button("Atualizar Venda");
		btnAtualizarVenda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				VendaDAO vendaDao = new VendaDAO();
				
				if (buttonGroup.getSelectedToggle().equals(aVista)) {
					Venda venda = new Venda("À Vista", Integer.parseInt(lblVooIdValor.getText()), lblOrigemVooValor.getText(), lblDestinoVooValor.getText(), Double.parseDouble(lblValorVooPreco.getText()), lblNomeClienteValor.getText(), lblCpfClientesValor.getText(), lblCartaoCredClienteValor.getText());
					if (vendaDao.alteraVenda(venda)) {
						JOptionPane.showMessageDialog(null, "A vista - Venda atualizada com sucesso!");
						hide();
					} else {						
						JOptionPane.showMessageDialog(null, "Erro na compra", "Erro", JOptionPane.ERROR_MESSAGE);
						hide();
					}
				} else if (buttonGroup.getSelectedToggle().equals(cartao)) {
					Venda venda = new Venda("Cartão", Integer.parseInt(lblVooIdValor.getText()), lblOrigemVooValor.getText(), lblDestinoVooValor.getText(), lblNomeClienteValor.getText(), lblCpfClientesValor.getText(), Integer.parseInt(txtParcelaValor.getText()), Double.parseDouble(lblParcelaValor.getText()), Double.parseDouble(lblValorVooPreco.getText()), lblCartaoCredClienteValor.getText());
					if (lblParcelaValor.getText().equals("0.0")) {
						JOptionPane.showMessageDialog(null, "Faltou calcular as parcelas.", "Cálculo de parcelas", JOptionPane.WARNING_MESSAGE);
					} else {
						if (vendaDao.alteraVenda(venda)) {
							JOptionPane.showMessageDialog(null, "No cartão - Venda atualizada com sucesso!");
							hide();
						} else {
							JOptionPane.showMessageDialog(null, "Erro na compra", "Erro", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					
				}
				
			}
			
		});
		Button btnCancelar = new Button("Cancelar");
		btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				hide();
				
			}
			
		});
		hbox10.getChildren().addAll(btnAtualizarVenda, btnCancelar);
		hbox10.setAlignment(Pos.CENTER);
		
		
		vbox.getChildren().addAll(hbox1, hbox2, hbox6, hbox3, hbox4, hbox5, hbox11, hbox8, hbox7, hbox9, hbox10);
		
		GridPane.setConstraints(vbox, 9, 4);
		gPane.getChildren().add(vbox);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
		
	}

}