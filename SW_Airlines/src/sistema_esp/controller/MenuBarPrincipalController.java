package sistema_esp.controller;

import sistema_esp.view.MenuBarPrincipal;

public class MenuBarPrincipalController {
	
	private MenuBarPrincipal view;
	
	public MenuBarPrincipalController(MenuBarPrincipal view) {
		setView(view);
	}

	public MenuBarPrincipal getView() {
		return view;
	}

	public void setView(MenuBarPrincipal view) {
		this.view = view;
	}
	
	

}
