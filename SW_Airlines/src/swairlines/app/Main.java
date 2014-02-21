package swairlines.app;

import swairlines.gui.TelaLogin;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Scene SCENE;
	
	public static void alterarTela(Parent root) {
		SCENE.setRoot(root);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Main.SCENE = new Scene(new TelaLogin(), 1024, 700, Color.SILVER);
			primaryStage.setScene(Main.SCENE);
			primaryStage.setTitle("SW Airlines");
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
	
	

}
