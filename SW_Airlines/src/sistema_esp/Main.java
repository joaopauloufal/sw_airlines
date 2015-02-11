package sistema_esp;

import sistema_esp.view.TelaInicial;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	private static Scene scene;
	
	public static void alterarTela(Parent root) {
		scene.setRoot(root);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		scene = new Scene(new TelaInicial(), 800, 600, Color.SILVER);
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
	
	public static void main(String[] args) {
		launch(args);
	}

	

}


