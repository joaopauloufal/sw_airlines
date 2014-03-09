package swairlines.view;

import swairlines.dao.FuncionarioDAO;
import swairlines.model.Endereco;
import swairlines.model.Funcionario;
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

public class TelaTabelaFuncionarios extends BorderPane {
	
	private TableView<Funcionario> tableView = new TableView<Funcionario>();
	private ObservableList<Funcionario> dados;
	
	@SuppressWarnings("unchecked")
	public TelaTabelaFuncionarios(Funcionario f) {
		
		dados = FXCollections.observableArrayList();
		FuncionarioDAO funcDao = new FuncionarioDAO();
		dados.addAll(funcDao.buscaFuncionarios());
		
		tableView.setEditable(true);
		
		TableColumn<Funcionario, String> nomeColuna = new TableColumn<>("Nome");
		nomeColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));
		nomeColuna.setMinWidth(140);
		
		TableColumn<Funcionario, String> sexoColuna = new TableColumn<>("Sexo");
		sexoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("sexo"));
		sexoColuna.setMinWidth(140);
		
		TableColumn<Funcionario, String> cpfColuna = new TableColumn<>("CPF");
		cpfColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cpf"));
		cpfColuna.setMinWidth(140);
		
		TableColumn<Funcionario, String> rgColuna = new TableColumn<>("RG");
		rgColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("rg"));
		rgColuna.setMinWidth(140);
		
		TableColumn<Funcionario, String> dataDeNascimentoColuna = new TableColumn<>("Data de Nascimento");
		dataDeNascimentoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("dataDeNascimento"));
		dataDeNascimentoColuna.setMinWidth(160);
		
		TableColumn<Funcionario, String> telefoneCelularColuna = new TableColumn<>("Telefone Celular");
		telefoneCelularColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("telefoneCelular"));
		telefoneCelularColuna.setMinWidth(140);
		
		TableColumn<Funcionario, String> telefoneResidencialColuna = new TableColumn<>("Telefone Residencial");
		telefoneResidencialColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("telefoneResidencial"));
		telefoneResidencialColuna.setMinWidth(160);
		
		TableColumn<Funcionario, String> nacionalidadeColuna = new TableColumn<>("Nacionalidade");
		nacionalidadeColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nacionalidade"));
		nacionalidadeColuna.setMinWidth(140);
		
		TableColumn<Funcionario, String> estadoCivilColuna = new TableColumn<>("Estado Civil");
		estadoCivilColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("estadoCivil"));
		estadoCivilColuna.setMinWidth(140);
		
		TableColumn<Funcionario, String> cargoColuna = new TableColumn<>("Cargo");
		cargoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cargo"));
		cargoColuna.setMinWidth(140);
		
		TableColumn<Funcionario, Endereco> enderecoColuna = new TableColumn<>("Endereço");
		enderecoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, Endereco>("endereco"));
		enderecoColuna.setMinWidth(140);
		
		tableView.getColumns().addAll(nomeColuna, sexoColuna, cpfColuna, rgColuna, dataDeNascimentoColuna, telefoneCelularColuna, telefoneResidencialColuna, nacionalidadeColuna, estadoCivilColuna, cargoColuna, enderecoColuna);
		tableView.setFocusTraversable(false);
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Funcionários Cadastrados");
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
