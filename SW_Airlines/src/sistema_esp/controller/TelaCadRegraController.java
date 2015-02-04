package sistema_esp.controller;

import sistema_esp.view.TelaCadRegra;

public class TelaCadRegraController {
	
	private TelaCadRegra view;
	
	public TelaCadRegraController(TelaCadRegra view) {
		setView(view);
	}

	public TelaCadRegra getView() {
		return view;
	}

	public void setView(TelaCadRegra view) {
		this.view = view;
	}
	
	

}
