package swairlines.view;


import swairlines.model.Funcionario;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TelaInicial extends BorderPane {
	
	public TelaInicial(final Funcionario f) {		
		VBox menuTopo = new VBox(30);				
		MenuBarPrincipal menuBarPrincipal = new MenuBarPrincipal(f);
		menuTopo.getChildren().add(menuBarPrincipal);				
		setTop(menuTopo);
		
		VBox boximage = new VBox();
		Image image = new Image("./images/aviao.png");
		Label titulo = new Label("SW Airlines");
		titulo.setFont(new Font(30));
		final ImageView imageview = new ImageView(image);
		
        boximage.getChildren().addAll(imageview,titulo);
        boximage.setAlignment(Pos.CENTER);
        setCenter(boximage);

	}
	
}
