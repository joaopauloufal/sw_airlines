package swairlines.view;

import swairlines.dao.ContaDeUsuarioDAO;
import swairlines.model.ContaDeUsuario;
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

public class TelaTabelaContasDeUsuario extends BorderPane {
	
	private TableView<ContaDeUsuario> tableView = new TableView<ContaDeUsuario>();
	private ObservableList<ContaDeUsuario> dados;
	
	@SuppressWarnings("unchecked")
	public TelaTabelaContasDeUsuario(Funcionario f) {
		
		dados = FXCollections.observableArrayList();
		ContaDeUsuarioDAO contaDao = new ContaDeUsuarioDAO();
		
		dados.addAll(contaDao.buscaContasDeUsuario());
		tableView.setEditable(true);
		
		TableColumn<ContaDeUsuario, String> cpfFuncColuna = new TableColumn<>("CPF Funcionário");
		cpfFuncColuna.setCellValueFactory(new PropertyValueFactory<ContaDeUsuario, String>("cpfFuncionario"));
		cpfFuncColuna.setMinWidth(140);
		
		TableColumn<ContaDeUsuario, String> loginColuna = new TableColumn<>("Login");
		loginColuna.setCellValueFactory(new PropertyValueFactory<ContaDeUsuario, String>("login"));
		loginColuna.setMinWidth(140);
		
		TableColumn<ContaDeUsuario, String> senhaColuna = new TableColumn<>("Senha");
		senhaColuna.setCellValueFactory(new PropertyValueFactory<ContaDeUsuario, String>("senha"));
		senhaColuna.setMinWidth(140);
		
		TableColumn<ContaDeUsuario, String> tipoContaColuna = new TableColumn<>("Tipo de Conta");
		tipoContaColuna.setCellValueFactory(new PropertyValueFactory<ContaDeUsuario, String>("tipoConta"));
		tipoContaColuna.setMinWidth(205);
		
		tableView.getColumns().addAll(cpfFuncColuna, loginColuna, senhaColuna, tipoContaColuna);
		tableView.setFocusTraversable(false);
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Listas de Contas Cadastradas");
		titulo.setFont(new Font(30));
		boxTop.setAlignment(Pos.CENTER);
		VBox vboxTabela = new VBox();
		vboxTabela.setPadding(new Insets(0, 200, 0, 200));
		vboxTabela.getChildren().add(tableView);
		boxTop.getChildren().addAll(new TelaPrincipal(f), titulo, vboxTabela);
		setTop(boxTop);
		
		tableView.setItems(dados);
		
	}

}
