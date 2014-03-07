package swairlines.gui;

import swairlines.modelo.Cliente;
import swairlines.modelo.Funcionario;
import swairlines.modelo.Gerente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TelaTabelaClientes extends BorderPane {
	
	private TableView<Cliente> tableView = new TableView<Cliente>();
	private ObservableList<Cliente> dados;
	
	@SuppressWarnings("unchecked")
	public TelaTabelaClientes(Funcionario f) {
		
		dados = FXCollections.observableArrayList();
		Gerente gerente = new Gerente();
		dados.addAll(gerente.buscaClientes());
		
		tableView.setEditable(true);
		
		TableColumn<Cliente, String> rgColuna = new TableColumn<>("RG");
		rgColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("rg"));
		rgColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> cpfColuna = new TableColumn<>("CPF/CNPJ");
		cpfColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpfCnpj"));
		cpfColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> nomeColuna = new TableColumn<>("Nome");
		nomeColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		nomeColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> sexoColuna = new TableColumn<>("Sexo");
		sexoColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("sexo"));
		sexoColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> dataDeNascimentoColuna = new TableColumn<>("Data de Nascimento");
		dataDeNascimentoColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dataDeNascimento"));
		dataDeNascimentoColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> estadoCivilColuna = new TableColumn<>("Estado Civil");
		estadoCivilColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("estadoCivil"));
		estadoCivilColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> nacionalidadeColuna = new TableColumn<>("Nacionalidade");
		nacionalidadeColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nacionalidade"));
		nacionalidadeColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> telefoneCelularColuna = new TableColumn<>("Telefone Celular");
		telefoneCelularColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefoneCelular"));
		telefoneCelularColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> telefoneResidencialColuna = new TableColumn<>("Telefone Residencial");
		telefoneResidencialColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefoneResidencial"));
		telefoneResidencialColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> cartaoDeCreditoColuna = new TableColumn<>("Cartão de Crédito");
		cartaoDeCreditoColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cartaoDeCredito"));
		cartaoDeCreditoColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> ruaColuna = new TableColumn<>("Rua");
		ruaColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("rua"));
		ruaColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> cidadeColuna = new TableColumn<>("Cidade");
		cidadeColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cidade"));
		cidadeColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> bairroColuna = new TableColumn<>("Bairro");
		bairroColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("bairro"));
		bairroColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> numeroColuna = new TableColumn<>("Número");
		numeroColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("numero"));
		numeroColuna.setMinWidth(140);
		
		TableColumn<Cliente, String> estadoColuna = new TableColumn<>("Estado");
		estadoColuna.setCellValueFactory(new PropertyValueFactory<Cliente, String>("estado"));
		estadoColuna.setMinWidth(140);
		
		tableView.getColumns().addAll(rgColuna, cpfColuna, nomeColuna, sexoColuna, dataDeNascimentoColuna, estadoCivilColuna, nacionalidadeColuna, telefoneCelularColuna, telefoneResidencialColuna, cartaoDeCreditoColuna, ruaColuna, cidadeColuna, bairroColuna, numeroColuna, estadoColuna);
		tableView.setFocusTraversable(false);
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Lista de Clientes");
		titulo.setFont(new Font(30));
		boxTop.setAlignment(Pos.CENTER);
		VBox vboxTabela = new VBox();
		vboxTabela.setPadding(new Insets(0, 10, 0, 10));
		vboxTabela.getChildren().add(tableView);
		boxTop.getChildren().addAll(new TelaPrincipal(f), titulo, vboxTabela);
		setTop(boxTop);
		
		tableView.setItems(dados);
		
		
		
	}

}
