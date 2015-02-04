package sistema_esp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaCadRegra extends Stage {
	
	@SuppressWarnings("unchecked")
	public TelaCadRegra() {
		
		GridPane gPane = new GridPane();
		gPane.setPadding(new Insets(10, 10, 10, 10));
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		HBox hbox1 = new HBox(20);
		HBox hbox2 = new HBox(20);
		VBox vbox1 = new VBox(10);
		
		Scene scene = new Scene(gPane, 600, 700, Color.SILVER);
		setScene(scene);
		
		ObservableList<String> variaveis = FXCollections.observableArrayList("Influência em Inglês","Culturas diferentes","Praia", "Frio", "Temperado", "Calor");
		ObservableList<String> simbolos = FXCollections.observableArrayList("","e", "ou");
		
		Label lblSe = new Label("Se");
		final ComboBox comboBox = new ComboBox(variaveis);
		comboBox.getSelectionModel().selectFirst();
		final ComboBox comboBoxSimbolos = new ComboBox(simbolos);
		comboBoxSimbolos.getSelectionModel().selectFirst();
		hbox1.getChildren().addAll(lblSe, comboBox, comboBoxSimbolos);
		
		comboBoxSimbolos.setOnHidden(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				if (comboBoxSimbolos.getValue().equals("e")){
					
				}
				
			}
			
		});
		
		vbox1.getChildren().addAll(hbox1, hbox2);
		this.setTitle("Cadastrar Regra");
		
		GridPane.setConstraints(vbox1, 9, 4);
		
		gPane.getChildren().add(vbox1);
		initModality(Modality.APPLICATION_MODAL);
		show();
		
	}

}
