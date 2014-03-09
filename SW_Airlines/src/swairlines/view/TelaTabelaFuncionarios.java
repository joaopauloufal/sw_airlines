package swairlines.view;

import javax.swing.JOptionPane;

import swairlines.Main;
import swairlines.dao.FuncionarioDAO;
import swairlines.model.Endereco;
import swairlines.model.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TelaTabelaFuncionarios extends BorderPane {
	
	private TableView<Funcionario> tableView = new TableView<Funcionario>();
	private ObservableList<Funcionario> dados;
	
	@SuppressWarnings("unchecked")
	public TelaTabelaFuncionarios(final Funcionario f) {
		
		HBox hbox = new HBox(20);
		
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
		
		Button btnAtualizarValores = new Button("Atualizar Valores");
		btnAtualizarValores.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaFuncionarios(f));
				
			}
			
		});
		Button btnEditarFuncionario = new Button("Editar Funcionário");
		btnEditarFuncionario.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					Funcionario funcionario = dados.get(tableView.getSelectionModel().getSelectedIndex());
					TelaEditFuncionario tela = new TelaEditFuncionario(funcionario);
					tela.setTitle("Editar Funcionário");
					
				}
				
			}
		});
		
		Button btnExcluirFuncionario = new Button("Excluir Funcionário");
		btnExcluirFuncionario.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza de que quer excuir o Funcionário selecionado?", "Confirmação de Exclusão Funcionário", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						FuncionarioDAO funcDao = new FuncionarioDAO();
						funcDao.excluiFuncionario(tableView.getSelectionModel().getSelectedItem());
						JOptionPane.showMessageDialog(null, "Funcionário excluido.", "Exclusão de Funcionário", JOptionPane.INFORMATION_MESSAGE);
						Main.alterarTela(new TelaTabelaFuncionarios(f));
					}
				}
				
			}
			
		});
		
		tableView.getColumns().addAll(nomeColuna, sexoColuna, cpfColuna, rgColuna, dataDeNascimentoColuna, telefoneCelularColuna, telefoneResidencialColuna, nacionalidadeColuna, estadoCivilColuna, cargoColuna, enderecoColuna);
		tableView.setFocusTraversable(false);
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Funcionários Cadastrados");
		titulo.setFont(new Font(30));
		hbox.getChildren().addAll(btnAtualizarValores, btnEditarFuncionario, btnExcluirFuncionario);
		hbox.setAlignment(Pos.BASELINE_CENTER);
		boxTop.setAlignment(Pos.CENTER);
		VBox vboxTabela = new VBox();
		vboxTabela.setPadding(new Insets(0, 10, 0, 10));
		vboxTabela.getChildren().add(tableView);
		boxTop.getChildren().addAll(new TelaPrincipal(f), titulo, vboxTabela, hbox);
		setTop(boxTop);
		
		tableView.setItems(dados);
		
	}

}
