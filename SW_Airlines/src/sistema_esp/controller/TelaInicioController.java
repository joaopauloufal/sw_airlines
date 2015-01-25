package sistema_esp.controller;

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
}
