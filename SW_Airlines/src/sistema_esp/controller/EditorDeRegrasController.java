package sistema_esp.controller;

import sistema_esp.view.EditorDeRegras;

public class EditorDeRegrasController {
	
	private EditorDeRegras view;
	
	public EditorDeRegrasController (EditorDeRegras view) {
		setView(view);
	}

	public EditorDeRegras getView() {
		return view;
	}

	public void setView(EditorDeRegras view) {
		this.view = view;
	}
}
