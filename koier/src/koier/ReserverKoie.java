package koier;

import javax.swing.JComboBox;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ReserverKoie extends Application {

	public static void main(String[] args) {
		Application.launch(ReserverKoie.class);
	}

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new StackPane();
		pane.setPrefWidth(753.0);
		pane.setPrefHeight(430.0);
		Scene scene = new Scene(pane, 800, 450);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Reservasjon");
		
		final String[] koieliste = new String[] { "Velg koie", "Flåkoia (Antall sengeplasser: 11)",
				"Fosenkoia (Antall sengeplasser: 10)", "Heinfjordstua (Antall sengeplasser: 25)", "Hognabu (Antall sengeplasser: 6)", "Holmsåkoia (Antall sengeplasser: 20)",
				"Holvassgamma (Antall sengeplasser: 8)", "Iglbu (Antall sengeplasser: 8)", "Kamtjønnkoia (Antall sengeplasser: 6)", "Kråklikåten (Antall sengeplasser: 4)",
				"Kvernmovollen (Antall sengeplasser: 8)", "Kåsen (Antall sengeplasser: 8)", "Lynhøgen (Antall sengeplasser: 4)", "Mortenskåten (Antall sengeplasser: 2)",
				"Nicokoia (Antall sengeplasser: 8)", "Rindalsløa (Antall sengeplasser: 4)", "Selbukåten (Antall sengeplasser: 2)", "Sonvasskoia (Antall sengeplasser: 8)",
				"Stabburet (Antall sengeplasser: 2)", "Stakkslettbua (Antall sengeplasser: 11)", "Telin (Antall sengeplasser: 9)", "Taagaabu (Antall sengeplasser: 6)",
				"Vekvessætra (Antall sengeplasser: 20)", "Øvensenget (Antall sengeplasser: 8)" };

		ChoiceBox<String> koier = new ChoiceBox<String>(FXCollections.observableArrayList(koieliste));
		koier.getSelectionModel().select(0);
		koier.setPrefHeight(26.0);
		koier.setPrefWidth(200.0);
		koier.setPadding(new Insets(5));
		
		
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
		// antall sengeplasser man kan reservere mÃ¥ kanskje vÃ¦re avhengig av
		// hvilken koie som man vil reservere plasser pÃ¥. (varierende antall
		// plasser pÃ¥ hver koie)

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
				turledercheck);

		final HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 10, 10, 10));
		hbox.setSpacing(0.8f);
		//hbox.setAlignment(Pos.BASELINE_CENTER); //center alt
		//hbox.relocate(20, 20);
		
		hbox.getChildren().addAll(vbox);

		

		pane.getChildren().addAll(hbox);
		primaryStage.show();
	}
}
