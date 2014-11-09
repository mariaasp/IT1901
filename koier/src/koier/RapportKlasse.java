package koier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class RapportKlasse extends Application {

	public static void main(String[] args) {
		Application.launch(RapportKlasse.class);
	}
	
	
	public void start(Stage primaryStage, Bruker bruker) throws SQLException {
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
		Pane pane  = new Pane();
		pane.setPrefWidth(663.0);
		pane.setPrefHeight(491.0);
		pane.setStyle("-fx-background-color: lightgrey;");
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setContrast(0.1);
		pane.setEffect(colorAdjust);
		
		Scene scene = new Scene(pane, 700, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Skaderapport");
		primaryStage.resizableProperty().set(false);
		
	    Label header = new Label();
	    header.setLayoutX(31.0);
	    header.setLayoutY(26);
	    header.setPrefHeight(61.0);
	    header.setPrefWidth(477.0);
	    header.setText("Meld inn rapport etter koietur: ");
	    header.setAlignment(Pos.CENTER_LEFT);
	    header.setFont(new Font("System", 31)); 
	    
	    final Label failedLabel = new Label("Venligst fyll inn manglende felt");
		failedLabel.setLayoutY(480);
		failedLabel.setLayoutX(450);
		failedLabel.setTextFill(Color.RED);
		failedLabel.setVisible(false);
	    
	    
	    
		
		
		final String[] koieliste = new String[] { "Velg koie: ","Flåkoia",
				"Fosenkoia", "Heinfjordstua", "Hognabu", "Holmsøkoia",
				"Holvassgamma", "Iglbu", "Kamtjønnkoia", "Kråklikåten",
				"Kvernmovollen", "Kåsen", "Lynhøgen", "Mortenskåten",
				"Nicokoia", "Rindalsløa", "Selbukåten", "Sonvasskoia",
				"Stabburet", "Stakkslettbua", "Telin", "Tågåbu",
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
		skadedato.setPromptText("Koieturens dato");
		
		TextArea skadebeskrivelse = new TextArea();
		skadebeskrivelse.setLayoutX(15.0);
		skadebeskrivelse.setLayoutY(269.0);
		skadebeskrivelse.setPrefHeight(200.0);
		skadebeskrivelse.setPrefWidth(324.0);
		skadebeskrivelse.setPromptText("Beskrivelse av skade, gjenglemte ting, evt. andre hendelser...");
		
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

		
		pane.getChildren().addAll(header, skadedato, velgKoie, skadebeskrivelse, vedstatus, sendRapport, avbryt, turleder, failedLabel);
		
		primaryStage.show();
		
		avbryt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				
				try {
					new Meny().start(new Stage(), bruker);
				} catch (Exception e) {
					e.printStackTrace();
				}
				primaryStage.close();
				
			}
		});
		
		
		
		sendRapport.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				
				if (!velgKoie.getValue().toString().equals("Velg koie: ") && !turleder.getText().isEmpty()) {
					String selectedKoie = velgKoie.getValue().toString();
					String selectedLeader = turleder.getText();
					String date = "";
					try {
						date += skadedato.getValue().toString();
					} catch (Exception e) {
						
					}
					String SQLdate = "";
					if (!date.toString().isEmpty() && date != null) SQLdate = date.replaceAll("-", "");
					String skade = "";
					boolean selectedVedstatus = false;
					if (selectedKoie.contains("�") || selectedKoie.contains("�") || selectedKoie.contains("�")) {
						selectedKoie = selectedKoie.replaceAll("�", "aa");
						selectedKoie = selectedKoie.replaceAll("�", "oe");
						selectedKoie = selectedKoie.replaceAll("�", "ae");
					}
					if (!skadebeskrivelse.getText().isEmpty()) skade = skadebeskrivelse.getText();
					if (vedstatus.isSelected()) selectedVedstatus = true;
					int koienr = 0;
					
					try {
						PreparedStatement statement = con.prepareStatement("select * from koie where koie = " + "'" + selectedKoie + "'");
						ResultSet results = statement.executeQuery();
						results.next();
						koienr = Integer.parseInt(results.getString(1));
					} catch(Exception e) {
						System.out.println(e);
					}
					
					if (koienr != 0 && SQLdate.length() == 8) {
						try {
							PreparedStatement statementAdd = 
con.prepareStatement("INSERT INTO skaderapport (koienr, skadeDato, skade, brukerID, vedstatus) VALUES ("+koienr+","+ "'" +SQLdate+ "'" +","+ "'" + skade + "'" +","+selectedLeader+","+ selectedVedstatus +")");
							statementAdd.executeUpdate();
							
							try {
								new Meny().start(new Stage(), bruker);
							} catch (Exception e) {
								e.printStackTrace();
							}
							primaryStage.close();
						}catch (Exception e) {
						}
						
					}
					else {
						failedLabel.setVisible(true);
					}
							
				}
				else {
					failedLabel.setVisible(true);
				}
			}
		});
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
	}

}
