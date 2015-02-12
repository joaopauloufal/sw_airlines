package sistema_esp.controller;

import sistema_esp.view.TelaPergunta;

public class TelaPerguntaController {
	
	private TelaPergunta view;
	
	public TelaPerguntaController(TelaPergunta view) {
		this.setView(view);
	}

	public TelaPergunta getView() {
		return view;
	}

	public void setView(TelaPergunta view) {
		this.view = view;
	}
	
	

}
