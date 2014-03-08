package swairlines.view;

import swairlines.model.ContaDeUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class TelaTabelaContasDeUsuario extends BorderPane {
	
	private TableView<ContaDeUsuario> tableView = new TableView<ContaDeUsuario>();
	private ObservableList<ContaDeUsuario> dados;
	
	public TelaTabelaContasDeUsuario() {
		
		dados = FXCollections.observableArrayList();
		ContaDeUsuario conta = new ContaDeUsuario();
		dados.addAll(conta.buscaContasDeUsuario());
		
	}

}
