package sistema_esp;

import sistema_esp.view.TelaInicial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		scene = new Scene(new TelaInicial(this), 800, 600, Color.SILVER);
		primaryStage.setScene(getScene());
		primaryStage.setTitle("SW Airlines - Sistema Especialista");
		primaryStage.show();
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	

	

}


