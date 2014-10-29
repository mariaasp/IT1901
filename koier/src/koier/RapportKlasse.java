package koier;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class RapportKlasse extends Application {

	public static void main(String[] args) {
		Application.launch(RapportKlasse.class);
	}
	
	
	public void start(Stage primaryStage) {
		Pane pane  = new Pane();
		pane.setPrefWidth(663.0);
		pane.setPrefHeight(491.0);
		Scene scene = new Scene(pane, 700, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Skaderapport");
		
	    Label header = new Label();
	    header.setLayoutX(31.0);
	    header.setLayoutY(26);
	    header.setPrefHeight(61.0);
	    header.setPrefWidth(477.0);
	    header.setText("Meld inn rapport etter koietur: ");
	    header.setAlignment(Pos.CENTER_LEFT);
	    header.setFont(new Font("System", 31)); 
	    
	    
	    
		
		
		final String[] koieliste = new String[] { "Velg koie: ","Flåkoia",
				"Fosenkoia", "Heinfjordstua", "Hognabu", "Holmsåkoia",
				"Holvassgamma", "Iglbu", "Kamtjønnkoia", "Kråklikåten",
				"Kvernmovollen", "Kåsen", "Lynhøgen", "Mortenskåten",
				"Nicokoia", "Rindalsløa", "Selbukåten", "Sonvasskoia",
				"Stabburet", "Stakkslettbua", "Telin", "Taagaabu",
				"Vekvessætra", "Øvensenget" };
		
		Label koie = new Label("Velg koie: ");
		koie.setLayoutX(15.0);
		koie.setLayoutY(133.0);
		koie.setPrefHeight(26.0);
		koie.setPrefWidth(324);
		
		ChoiceBox<String> velgKoie = new ChoiceBox<String>(FXCollections.observableArrayList(koieliste));
		velgKoie.getSelectionModel().select(0);
		velgKoie.setPrefHeight(26.0);
		velgKoie.setPrefWidth(324.0);
		velgKoie.setLayoutX(14.0);
		velgKoie.setLayoutY(159.0);
		velgKoie.setPadding(new Insets(5));
		
		DatePicker skadedato = new DatePicker();
		skadedato.setLayoutX(14.0);
		skadedato.setLayoutY(220.0);
		skadedato.setPrefHeight(26.0);
		skadedato.setPrefWidth(324.0);
		skadedato.setPromptText("Skadedato");
		
		TextArea skadebeskrivelse = new TextArea();
		skadebeskrivelse.setLayoutX(15.0);
		skadebeskrivelse.setLayoutY(269.0);
		skadebeskrivelse.setPrefHeight(200.0);
		skadebeskrivelse.setPrefWidth(324.0);
		skadebeskrivelse.setPromptText("Beskrivelse av skade, evt. andre hendelser...");
		
		CheckBox vedstatus = new CheckBox();
		vedstatus.setLayoutX(363.0);
		vedstatus.setLayoutY(280.0);
		vedstatus.setPrefHeight(34.0);
		vedstatus.setPrefWidth(204.0);
		vedstatus.setText("Kryss av for nok ved");
		
		Button avbryt = new Button();
		avbryt.setLayoutX(363.0);
		avbryt.setLayoutY(435.0);
		avbryt.setPrefHeight(34.0);
		avbryt.setPrefWidth(118.0);
		avbryt.setText("Avbryt");
		
		
		Button sendRapport = new Button();
		sendRapport.setLayoutX(501.0);
		sendRapport.setLayoutY(435);
		sendRapport.setPrefHeight(34.0);
		sendRapport.setPrefWidth(137.0);
		sendRapport.setText("Send inn rapport");
		
	    TextField turleder = new TextField();
	    turleder.setLayoutX(363.0);
	    turleder.setLayoutY(335.0);
	    turleder.setPrefHeight(34.0);
	    turleder.setPrefWidth(204.0);
	    turleder.setPromptText("Turleders studentnr.");

		
		pane.getChildren(). addAll(header, skadedato, velgKoie, skadebeskrivelse, vedstatus, sendRapport, avbryt, turleder);
		primaryStage.show();
		
	}

}