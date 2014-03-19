package swairlines.view;

import swairlines.model.Voo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaAtrasoVoo extends Stage {
	
	private TextField txtNovaHoraInicio;
	
	public TelaAtrasoVoo(Voo voo) {
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		Scene scene = new Scene(gPane, 500, 500, Color.SILVER);
		setScene(scene);
		
		HBox hbox1 = new HBox(20);
		HBox hbox2 = new HBox(20);
		VBox vbox = new VBox();
		
		Label lblNovaHoraIncio = new Label("Nova Hora de Inicio Voo:");
		txtNovaHoraInicio = new TextField();
		txtNovaHoraInicio.setPrefColumnCount(10);
		hbox1.getChildren().addAll(lblNovaHoraIncio, txtNovaHoraInicio);
		
		Button btnAtrasarVoo = new Button("Atrasar Voo");
		Button btnCancelar = new Button("Cancelar");
		hbox2.getChildren().addAll(btnAtrasarVoo, btnCancelar);
		hbox2.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(hbox1, hbox2);
		
		GridPane.setConstraints(vbox, 9, 4);
		
		gPane.getChildren().add(vbox);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}

}
