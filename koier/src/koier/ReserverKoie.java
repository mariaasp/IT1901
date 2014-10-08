package koier;

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

		final String[] koieliste = new String[] { "Velg koie", "Flåkoia",
				"Fosenkoia", "Heinfjordstua", "Hognabu", "Holmsåkoia",
				"Holvassgamma", "Iglbu", "Kamtjønnkoia", "Kråklikåten",
				"Kvernmovollen", "Kåsen", "Lynhøgen", "Mortenskåten",
				"Nicokoia", "Rindalsløa", "Selbukåten", "Sonvasskoia",
				"Stabburet", "Stakkslettbua", "Telin", "Taagaabu",
				"Vekvessætra", "Øvensenget" };

		ChoiceBox<String> koier = new ChoiceBox<String>(FXCollections.observableArrayList(koieliste));
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

		final VBox vbox = new VBox();
		vbox.setPadding(new Insets(0, 0, 0, 0));
		vbox.setSpacing(0.8f);
		ChoiceBox<String> antPlasser = new ChoiceBox<String>(FXCollections.observableArrayList("Antall plasser å reservere", "1", "2"));
		// antall sengeplasser man kan reservere må kanskje være avhengig av
		// hvilken koie som man vil reservere plasser på. (varierende antall
		// plasser på hver koie)

		antPlasser.getSelectionModel().select(0);
		antPlasser.setPrefHeight(26.0);
		antPlasser.setPrefWidth(200.0);
		antPlasser.setPadding(new Insets(5));

		vbox.getChildren().addAll(koier, antPlasser, turlederlabel, turleder,
				turledercheck);

		final HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setSpacing(0.8f);
		hbox.getChildren().addAll(vbox);

		DatePicker dateFrom = new DatePicker();
		hbox.getChildren().add(dateFrom);
		dateFrom.setLayoutX(251.0);
		dateFrom.setLayoutY(125.0);
		dateFrom.setPadding(new Insets(5));
		dateFrom.setPromptText("Reserver fra");

		DatePicker dateTo = new DatePicker();
		hbox.getChildren().add(dateTo);
		dateTo.setLayoutX(251.0);
		dateTo.setLayoutY(125.0);
		dateTo.setPadding(new Insets(5));
		dateTo.setPromptText("Reserver til");

		pane.getChildren().addAll(hbox);
		primaryStage.show();
	}
}
