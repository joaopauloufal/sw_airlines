package sistema_esp.controller;

import sistema_esp.view.TelaTabelaRegras;

public class TelaTabelaRegrasController {
	
	private TelaTabelaRegras view;
	
	public TelaTabelaRegrasController(TelaTabelaRegras view) {
		setView(view);
	}

	public TelaTabelaRegras getView() {
		return view;
	}

	public void setView(TelaTabelaRegras view) {
		this.view = view;
	}
	
	

}
