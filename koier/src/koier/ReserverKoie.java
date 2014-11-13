package koier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ReserverKoie extends Application {
	Label hent;
	public static void main(String[] args) {
		Application.launch(ReserverKoie.class);
	}
	public String[] lagListe(String a){
		//Setter 10 som standard lengde
		int lengde = 10;
		
		//Setter lengde som int paa slutten av valgt element
		String currentKoie = hent.getText().toString();
		if (currentKoie.equals("Velg koie")) {
			
		}
		else {
			String lengdeString = currentKoie.substring(currentKoie.indexOf(":")+2, currentKoie.indexOf(")"));
			lengde = Integer.parseInt(lengdeString) - 1;
		}

		String[] retur = new String[lengde+2];
		for(int i=0;i< lengde+2;i++){
			if(i == 0){
				retur[i] = "Antall plasser aa reservere";
			}
			else if(i == lengde+1){
				retur[i] = "Reserver hele koien";
			}
			else{
				retur[i] = Integer.toString(i);
			}
			
		}
		//String[] retur = {"hei","ha","hu"};
		return retur;
	}
	
	public int getKoieSpots (String reservertKoie) {
		String lengdeString = reservertKoie.substring(reservertKoie.indexOf(":")+2, reservertKoie.indexOf(")"));
		int spots = Integer.parseInt(lengdeString);
		return spots;
		
	}
	
	public String getKoieName (String reservertKoie) {
		String koienavn = "";
		for (int i = 0; i < reservertKoie.length(); i++) {
			if (reservertKoie.charAt(i) == ' ' && reservertKoie.charAt(i+1) == '(') {
				break;
			}
			else {
				koienavn += reservertKoie.charAt(i);
			}
		}
		return koienavn;
	}

	public void start(Stage primaryStage, Bruker user) throws SQLException {
		//henter og setter bruker
		final Bruker bruker = user;
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
		primaryStage.resizableProperty().set(false);
		
		Pane pane = new StackPane();
		pane.setPrefWidth(753.0);
		pane.setPrefHeight(430.0);

		Scene scene = new Scene(pane, 800, 450);
		scene.setFill(Color.LIGHTGREY);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Reservasjon");
		
		final Label failedLabel = new Label("Venligst fyll inn manglende felt, og se til at dato er riktig");
		final Label failedLabel2 = new Label("Turleder finnes ikke i systemet, eller noe har gaatt galt");
		pane.getChildren().addAll(failedLabel);
		pane.getChildren().addAll(failedLabel2);
		pane.setStyle("-fx-background-color: lightgrey;");
		((StackPane) pane).setAlignment(failedLabel,Pos.BOTTOM_CENTER);
		((StackPane) pane).setAlignment(failedLabel2,Pos.BOTTOM_CENTER);
		failedLabel.setTextFill(Color.RED);
		failedLabel.setVisible(false);
		failedLabel2.setTextFill(Color.RED);
		failedLabel2.setVisible(false);
		
		
		final String[] koieliste = new String[] { "Velg koie", "Flaakoia (Antall sengeplasser: 11)",
				"Fosenkoia (Antall sengeplasser: 10)", "Heinfjordstua (Antall sengeplasser: 25)", "Hognabu (Antall sengeplasser: 6)", "Holmsoekoia (Antall sengeplasser: 20)",
				"Holvassgamma (Antall sengeplasser: 8)", "Iglbu (Antall sengeplasser: 8)", "Kamtjoennkoia (Antall sengeplasser: 6)", "Kraaklikaaten (Antall sengeplasser: 4)",
				"Kvernmovollen (Antall sengeplasser: 8)", "Kaasen (Antall sengeplasser: 8)", "Lynhoegen (Antall sengeplasser: 4)", "Mortenskaaten (Antall sengeplasser: 2)",
				"Nicokoia (Antall sengeplasser: 8)", "Rindalsloea (Antall sengeplasser: 4)", "Selbukaaten (Antall sengeplasser: 2)", "Sonvasskoia (Antall sengeplasser: 8)",
				"Stabburet (Antall sengeplasser: 2)", "Stakkslettbua (Antall sengeplasser: 11)", "Telin (Antall sengeplasser: 9)", "Taagaabu (Antall sengeplasser: 6)",
				"Vekvessaetra (Antall sengeplasser: 20)", "Oevensenget (Antall sengeplasser: 8)" };

		ChoiceBox<String> koier = new ChoiceBox<String>(FXCollections.observableArrayList(koieliste));
		koier.getSelectionModel().select(0);	
		koier.setPrefHeight(26.0);
		koier.setPrefWidth(200.0);
		koier.setPadding(new Insets(5));
		
		hent = new Label(koier.getValue());
		hent.textProperty().bind(koier.getSelectionModel().selectedItemProperty());
		
	
		
		koier.getSelectionModel().select(0);
		koier.setPrefHeight(26.0);
		koier.setPrefWidth(200.0);
		koier.setPadding(new Insets(5));

		final TextField turleder = new TextField();
		turleder.setPrefHeight(26.0);
		turleder.setPrefWidth(202.0);
		turleder.setPadding(new Insets(5));
		turleder.setPromptText("Studentnr til turleder");

		final Label turlederlabel = new Label("Turleder: ");
		turlederlabel.setLayoutX(250.0);
		turlederlabel.setLayoutY(200.0);
		turlederlabel.setAlignment(Pos.CENTER);
		final CheckBox turledercheck = new CheckBox(
				"Sett meg selv som turleder");
		
		turledercheck.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if (turledercheck.isSelected()) turleder.setVisible(false);
				else turleder.setVisible(true);
			}
		});
		
		
//		final List options = koieliste.getItems();
//		koieliste.getSelectionModel().selectedIndexProperty().addListener(
//				new ChangeListener()
//				@override public void changed(observableValue of, Number))
		
			
			
			
			

		final VBox vbox = new VBox();
		vbox.setPadding(new Insets(25, 0, 0, 265));
		vbox.setSpacing(7.5);
		ChoiceBox<String> antPlasser = new ChoiceBox<String>(FXCollections.observableArrayList(lagListe(hent.getText())));
		// antall sengeplasser man kan reservere maa kanskje vaare avhengig av
		// hvilken koie som man vil reservere plasser paa. (varierende antall
		// plasser paa hver koie)
		
		antPlasser.setOnMouseClicked((event) ->{
			antPlasser.getItems().clear();
			antPlasser.getItems().addAll(lagListe(hent.getText()));
		});

		antPlasser.getSelectionModel().select(0);
		antPlasser.setPrefHeight(26.0);
		antPlasser.setPrefWidth(200.0);
		antPlasser.setPadding(new Insets(5));
		
		
		DatePicker dateFrom = new DatePicker();
		//vbox.getChildren().add(dateFrom); //endret hbox til vbox
		dateFrom.setLayoutX(251.0);
		dateFrom.setLayoutY(125.0);
		
		dateFrom.setPadding(new Insets(5));
		dateFrom.setPromptText("Reserver fra");

		DatePicker dateTo = new DatePicker();
		//vbox.getChildren().add(dateTo); //endret hbox til xbox
		dateTo.setLayoutX(251.0);
		dateTo.setLayoutY(125.0);
		dateTo.setPadding(new Insets(5));
		dateTo.setPromptText("Reserver til");
		Button submit = new Button("Reserver Koie");
		Button cancel = new Button("Avbryt");
		

		vbox.getChildren().addAll(koier, dateFrom, dateTo, antPlasser, turlederlabel, turleder,
				turledercheck,hent,submit, cancel);

		final HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 10, 10, 10));
		hbox.setSpacing(0.8f);
		//hbox.setAlignment(Pos.BASELINE_CENTER); //center alt
		//hbox.relocate(20, 20);
		
		cancel.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				try {
					new Meny().start(new Stage(), bruker);
				} catch (Exception e) {
					e.printStackTrace();
				}
				primaryStage.close();
			}
		});
	
		
		hbox.getChildren().addAll(vbox);


		pane.getChildren().addAll(hbox);
		primaryStage.show();
		
		
		
		
		submit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				String reservertKoie = getKoieName(koier.getValue().toString());
				int koieSpots = getKoieSpots(koier.getValue().toString());
				String sqlDateFrom = "";
				String sqlDateTo = "";
				
				if (reservertKoie.contains("æ") || reservertKoie.contains("ø") || reservertKoie.contains("å")) {
					reservertKoie = reservertKoie.replaceAll("å", "aa");
					reservertKoie = reservertKoie.replaceAll("ø", "oe");
					reservertKoie = reservertKoie.replaceAll("æ", "ae");
				}
				LocalDate $dateFrom = null;
				LocalDate $dateTo = null;
				//SETTER DATO VALGT TIL DET SOM ER VALGT OG SJEKKER OM INGENTING ER VALGT
				try {
					$dateFrom = dateFrom.getValue();
					$dateTo = dateTo.getValue();
					sqlDateFrom = $dateFrom.toString().replaceAll("-", "");
					sqlDateTo = $dateTo.toString().replaceAll("-", "");
				}catch(Exception e) {
					//SENDER FEILMELDING OM AT DATO IKKE ER VALGT
				}
				
				int selectedLeader = 0;
				int selectedSpots = 0;
				
				
				Boolean koieSelected = false;
				Boolean validDate = false;
				Boolean validSpots = false;
				Boolean validLeader = false;
				
				
				if (!reservertKoie.equals("Velg koie")) koieSelected = true;
				if ($dateFrom != null && $dateTo != null && $dateFrom.isBefore($dateTo)) validDate = true;
				if (!antPlasser.getValue().equals("Antall plasser aa reservere")) validSpots = true;
				
				if (turledercheck.isSelected()) {
					//Å VELGE DEG SELV SOM TURLEDER TRUMFER ANNET VALG
					selectedLeader = bruker.brukerID;
					validLeader = true;
				}
				else if (turleder.getText().isEmpty() && !turledercheck.isSelected()) {
					//MÅ HA EN TURLEDER
					failedLabel2.setVisible(false);
				}
				else{
					int idnr = 0;
					try {
						idnr = Integer.parseInt(turleder.getText());
					}catch (Exception e) {
						System.out.println(e);
					}
					if (idnr <= 0) validLeader = false;
					else {
						selectedLeader = idnr;
						validLeader = true;
					}
				}
				
				if (koieSelected && validDate && validSpots && validLeader) {
					
					int koienr = 0;
					
					if (antPlasser.getValue().toString().equals("Reserver hele koien")) {
						selectedSpots = koieSpots;
					}
					else {
						selectedSpots = Integer.parseInt(antPlasser.getValue().toString());
					}
					
					try {
						PreparedStatement statement = con.prepareStatement("select * from koie where koie = " + "'" + reservertKoie + "'");
						ResultSet results = statement.executeQuery();
						results.next();
						koienr = Integer.parseInt(results.getString(1));
					} catch(Exception e) {
						System.out.println(e);
					}
					
					if (koienr != 0) {
						try {
							PreparedStatement statementAdd = con.prepareStatement 
							("INSERT INTO reservasjon VALUES ("+koienr+","+selectedSpots+","+bruker.brukerID +","+selectedLeader+","+"'"+sqlDateFrom+"'" +","+"'"+$dateTo+"'"+")");
							statementAdd.executeUpdate();
							
							try {
								new Meny().start(new Stage(), bruker);
							} catch (Exception e) {
								e.printStackTrace();
							}
							primaryStage.close();
						}catch (Exception e) {
							failedLabel.setVisible(false);
							failedLabel2.setVisible(true);
							//System.out.println(e);
						}	
					}
					
				}
				else{
					//KOMMER VI HIT ER NOE AV DET BRUKER SKREV INN IKKE FYLT INN ELLER FEIL (eks: dato)
					failedLabel.setVisible(true);
				}
				
			}
		});
		
		
		
		
		
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// denne maa vaare her av en eller annen grunn :S
		
	}
}
