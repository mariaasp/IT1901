package koier;

import javax.swing.JComboBox;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ReserverKoie extends Application {
	Label hent;
	public static void main(String[] args) {
		Application.launch(ReserverKoie.class);
	}
	public String[] lagListe(){
		int lengde = 10;
		if(hent.getText() == "FlÃ¥koia (Antall sengeplasser: 11)"){
			lengde = 11;
		}else if(hent.getText() == "Fosenkoia (Antall sengeplasser: 10)"){
			lengde = 10;
		}else if(hent.getText() == "Heinfjordstua (Antall sengeplasser: 25)"){
			lengde = 25;
		}else if(hent.getText() == "Hognabu (Antall sengeplasser: 6)"){
			lengde = 6;
		}else if(hent.getText() == "HolmsÃ¸koia (Antall sengeplasser: 20)"){
			lengde = 20;
		}else if(hent.getText() == "Holvassgamma (Antall sengeplasser: 8)"){
			lengde = 8;
		}else if(hent.getText() == "Iglbu (Antall sengeplasser: 8)"){
			lengde = 8;
		}else if(hent.getText() == "KamtjÃ¸nnkoia (Antall sengeplasser: 6)"){
			lengde = 6;
		}else if(hent.getText() == "KrÃ¥klikÃ¥ten (Antall sengeplasser: 4)"){
			lengde = 4;
		}else if(hent.getText() == "Kvernmovollen (Antall sengeplasser: 8)"){
			lengde = 8;
		}else if(hent.getText() == "KÃ¥sen (Antall sengeplasser: 8)"){
			lengde = 8;
		}else if(hent.getText() == "LynhÃ¥gen (Antall sengeplasser: 4)"){
			lengde = 4;
		}else if(hent.getText() == "MortenskÃ¥ten (Antall sengeplasser: 2)"){
			lengde = 2;
		}else if(hent.getText() == "Nicokoia (Antall sengeplasser: 8)"){
			lengde = 8;
		}else if(hent.getText() == "RindalslÃ¸a (Antall sengeplasser: 4)"){
			lengde = 4;
		}else if(hent.getText() == "SelbukÃ¥ten (Antall sengeplasser: 2)"){
			lengde = 2;
		}else if(hent.getText() == "Sonvasskoia (Antall sengeplasser: 8)"){
			lengde = 8;
		}else if(hent.getText() == "Stabburet (Antall sengeplasser: 2)"){
			lengde = 2;
		}else if(hent.getText() == "Stakkslettbua (Antall sengeplasser: 11)"){
			lengde = 11;
		}else if(hent.getText() == "Stakkslettbua (Antall sengeplasser: 11)"){
			lengde = 11;
		}else if(hent.getText() == "Telin (Antall sengeplasser: 9)"){
			lengde = 9;
		}else if(hent.getText() == "Taagaabu (Antall sengeplasser: 6)"){
			lengde = 6;
		}else if(hent.getText() == "VekvessÃ¦tra (Antall sengeplasser: 20)"){
			lengde = 20;
		}else if(hent.getText() == "Ã˜vensenget (Antall sengeplasser: 8)"){
			lengde = 8;
			
		}
		
		String[] retur = new String[lengde+2];
		for(int i=0;i< lengde+2;i++){
			if(i == 0){
				retur[i] = "Antall plasser å reservere";
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

	public void start(Stage primaryStage, Bruker user) {
		//henter og setter bruker
		final Bruker bruker = user;

		Pane pane = new StackPane();
		pane.setPrefWidth(753.0);
		pane.setPrefHeight(430.0);
		Scene scene = new Scene(pane, 800, 450);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Reservasjon");
		
		final String[] koieliste = new String[] { "Velg koie", "Flåkoia (Antall sengeplasser: 11)",
				"Fosenkoia (Antall sengeplasser: 10)", "Heinfjordstua (Antall sengeplasser: 25)", "Hognabu (Antall sengeplasser: 6)", "Holmsøkoia (Antall sengeplasser: 20)",
				"Holvassgamma (Antall sengeplasser: 8)", "Iglbu (Antall sengeplasser: 8)", "Kamtjønnkoia (Antall sengeplasser: 6)", "Kråklikåten (Antall sengeplasser: 4)",
				"Kvernmovollen (Antall sengeplasser: 8)", "Kåsen (Antall sengeplasser: 8)", "Lynhågen (Antall sengeplasser: 4)", "Mortenskåten (Antall sengeplasser: 2)",
				"Nicokoia (Antall sengeplasser: 8)", "Rindalsløa (Antall sengeplasser: 4)", "Selbukåten (Antall sengeplasser: 2)", "Sonvasskoia (Antall sengeplasser: 8)",
				"Stabburet (Antall sengeplasser: 2)", "Stakkslettbua (Antall sengeplasser: 11)", "Telin (Antall sengeplasser: 9)", "Taagaabu (Antall sengeplasser: 6)",
				"Vekvessætra (Antall sengeplasser: 20)", "Øvensenget (Antall sengeplasser: 8)" };

		ChoiceBox<String> koier = new ChoiceBox<String>(FXCollections.observableArrayList(koieliste));
		koier.getSelectionModel().select(0);
		koier.setPrefHeight(26.0);
		koier.setPrefWidth(200.0);
		koier.setPadding(new Insets(5));
		
		hent = new Label(koier.getValue());
		hent.textProperty().bind(koier.getSelectionModel().selectedItemProperty());
		
		//koier.addEventHandler(, eventHandler);
		/*
		koier.setOnMouseClicked(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				lagListe();
			}
		});*/
	
		
		koier.getSelectionModel().select(0);
		koier.setPrefHeight(26.0);
		koier.setPrefWidth(200.0);
		koier.setPadding(new Insets(5));

		final TextField turleder = new TextField();
		turleder.setPrefHeight(26.0);
		turleder.setPrefWidth(202.0);
		turleder.setPadding(new Insets(5));
		turleder.setPromptText("Navn på turleder");

		final Label turlederlabel = new Label("Turleder: ");
		turlederlabel.setLayoutX(250.0);
		turlederlabel.setLayoutY(200.0);
		turlederlabel.setAlignment(Pos.CENTER);
		final CheckBox turledercheck = new CheckBox(
				"Sett meg selv som turleder");
		
		
		
//		final List options = koieliste.getItems();
//		koieliste.getSelectionModel().selectedIndexProperty().addListener(
//				new ChangeListener()
//				@override public void changed(observableValue of, Number))
		
			
			
			
			

		final VBox vbox = new VBox();
		vbox.setPadding(new Insets(25, 0, 0, 265));
		vbox.setSpacing(7.5);
		//vbox.setAlignment(Pos.); //
		
		ChoiceBox<String> antPlasser = new ChoiceBox<String>(FXCollections.observableArrayList("Antall plasser å reservere", "1", "2","3","4","5","6","7","8",
				"9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25", "Reserver hele koien"));
		//ChoiceBox<String> antPlasser = new ChoiceBox<String>(FXCollections.observableArrayList(lagListe()));
		// antall sengeplasser man kan reservere må kanskje være avhengig av
		// hvilken koie som man vil reservere plasser på. (varierende antall
		// plasser på hver koie)
		
		
		//koier.setOnMouseClicked((event) ->{
		//hent.setOnInputMethodTextChanged((event) ->{
		antPlasser.setOnMouseClicked((event) ->{
			antPlasser.getItems().clear();
			antPlasser.getItems().addAll(lagListe());
			//ChoiceBox<String> antPlasser = new ChoiceBox<String>(FXCollections.observableArrayList(lagListe()));
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
		

		vbox.getChildren().addAll(koier, dateFrom, dateTo, antPlasser, turlederlabel, turleder,
				turledercheck,hent);

		final HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 10, 10, 10));
		hbox.setSpacing(0.8f);
		//hbox.setAlignment(Pos.BASELINE_CENTER); //center alt
		//hbox.relocate(20, 20);
		
		hbox.getChildren().addAll(vbox);

		

		pane.getChildren().addAll(hbox);
		primaryStage.show();
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// denne må være her av en eller annen grunn :S
		
	}
}
