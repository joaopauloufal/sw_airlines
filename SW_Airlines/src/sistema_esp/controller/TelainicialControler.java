package sistema_esp.controller;

import sistema_esp.view.TelaInicial;

public class TelainicialControler {
	 private TelaInicial view;
	 
	 public TelainicialControler(TelaInicial view) {
		setView(view);
	}

	public TelaInicial getView() {
		return view;
	}

	public void setView(TelaInicial view) {
		this.view = view;
	}
	 
	

}
