package swairlines.view;

/**
 * @author João Paulo, Danilo Victor, Pedro Victor
 * @since 2014
 * @name TelaTabelaContasDeUsuario
 */

import javax.swing.JOptionPane;

import swairlines.Main;
import swairlines.dao.ContaDeUsuarioDAO;
import swairlines.model.ContaDeUsuario;
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

public class TelaTabelaContasDeUsuario extends BorderPane {
	
	private TableView<ContaDeUsuario> tableView = new TableView<ContaDeUsuario>();
	private ObservableList<ContaDeUsuario> dados;
	
	@SuppressWarnings("unchecked")
	public TelaTabelaContasDeUsuario(final Funcionario f) {
		
		HBox hbox = new HBox(20);
		
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
		
		/**Edita conta do banco*/
		
		Button btnEditarConta = new Button("Editar Conta");
		btnEditarConta.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					ContaDeUsuario conta = dados.get(tableView.getSelectionModel().getSelectedIndex());
					TelaEditContaDeUsuario tela = new TelaEditContaDeUsuario(conta);
					tela.setTitle("Editar Conta de Usuário");
				}
				
			}
		});
		
		/** Atualiza dados*/
		Button btnAtualizarValores = new Button("Atualizar Valores");
		btnAtualizarValores.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main.alterarTela(new TelaTabelaContasDeUsuario(f));
				
			}
			
		});
		
		/**Exclui conta do banco */
		
		Button btnExcuirConta = new Button("Excluir Conta");
		btnExcuirConta.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (tableView.getSelectionModel().getSelectedIndex() != -1) {
					int resposta = JOptionPane.showConfirmDialog(null, "Você tem certeza de que quer excluir a conta selecionada?", "Confirmação de Exclusão Conta De Usuário", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE);
					if (resposta == JOptionPane.YES_OPTION) {
						ContaDeUsuarioDAO contaDao = new ContaDeUsuarioDAO();
						contaDao.excluiContaDeUsuario((tableView.getSelectionModel().getSelectedItem()));
						JOptionPane.showMessageDialog(null, "Conta de usuário excluida.", "Exclusão de Conta de Usuário", JOptionPane.INFORMATION_MESSAGE);
						Main.alterarTela(new TelaTabelaContasDeUsuario(f));
					}
				}
				
			}
			
		});
		
		tableView.getColumns().addAll(cpfFuncColuna, loginColuna, senhaColuna, tipoContaColuna);
		tableView.setFocusTraversable(false);
		
		VBox boxTop = new VBox(20);
		Label titulo = new Label("Listas de Contas Cadastradas");
		titulo.setFont(new Font(30));
		boxTop.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(btnEditarConta, btnAtualizarValores, btnExcuirConta);
		hbox.setAlignment(Pos.BASELINE_CENTER);
		VBox vboxTabela = new VBox();
		vboxTabela.setPadding(new Insets(0, 200, 0, 200));
		vboxTabela.getChildren().add(tableView);
		boxTop.getChildren().addAll(new TelaPrincipal(f), titulo, vboxTabela, hbox);
		setTop(boxTop);
		
		tableView.setItems(dados);
		
	}

}
