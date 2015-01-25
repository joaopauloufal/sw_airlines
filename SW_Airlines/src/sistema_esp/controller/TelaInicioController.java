package sistema_esp.controller;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import sistema_esp.dao.ListaBancosDAO;
import sistema_esp.view.TelaInicio;

public class TelaInicioController {
	
	private TelaInicio view;
	
	public TelaInicioController (TelaInicio view) {
		setView(view);
	}

	public TelaInicio getView() {
		return view;
	}

	public void setView(TelaInicio view) {
		this.view = view;
	}

	public void actionBtAvancar() {
		System.out.println("Ação do botão avançar");
	}
	
	public ObservableList<String> retornaBancosDeDados () throws SQLException{
		ListaBancosDAO listaDAO = new ListaBancosDAO(); 
		return listaDAO.consultaBanco();
	}
}
