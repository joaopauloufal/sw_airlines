package sistema_esp.controller;

import sistema_esp.view.TelaEditRegra;

public class TelaEditRegraController {
	
	private TelaEditRegra view;
	
	public TelaEditRegraController(TelaEditRegra view) {
		setView(view);
	}

	public TelaEditRegra getView() {
		return view;
	}

	public void setView(TelaEditRegra view) {
		this.view = view;
	}
	
	

}
