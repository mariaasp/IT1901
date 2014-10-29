package koier;

import java.awt.Button;
import java.awt.Label;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;


public class Meny extends Application {

	public static void main(String[] args) {
		Application.launch(Meny.class);
	}

	public void start(Stage primaryStage) {

		Pane pane = new Pane();
		pane.setPrefHeight(400);
		pane.setPrefWidth(600);
		Scene scene = new Scene(pane, 700, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Meny");
//		Bruker bruker = new Bruker();
		
		
		Label velkommen = new Label("Velkommen, ");
		velkommen.setLayoutX(65.0);
	    velkommen.setLayoutY(30);
	    velkommen.setPrefHeight(52.0);
	    velkommen.setPrefWidth(310.0);
	    velkommen.setAlignment(Pos.CENTER_LEFT);
	    velkommen.setFont(new Font("System", 27)); 
		
		
		Button reservasjon = new Button();
		reservasjon.setLayoutX(65.0);
		reservasjon.setLayoutY(132.0);
		reservasjon.setPrefHeight(69.0);
		reservasjon.setPrefWidth(145.0);
		reservasjon.setText("Reservasjon");
		reservasjon.setFont(new Font("System", 27));
		
		Button rapport = new Button();
		rapport.setLayoutX(65.0);
		rapport.setLayoutY(270.0);
		rapport.setPrefHeight(69.0);
		rapport.setPrefWidth(145.0);
		rapport.setText("Rapport");
		rapport.setFont(new Font("System", 27));
		
		Button loggUt = new Button();
		loggUt.setLayoutX(347.0);
		loggUt.setLayoutY(270.0);
		loggUt.setPrefHeight(69.0);
		loggUt.setPrefWidth(145.0);
		loggUt.setText("Rapport");
		loggUt.setFont(new Font("System", 27));
		
		
		pane.getChildren().addAll(reservasjon, velkommen, rapport, loggUt);
		primaryStage.show();
		
	}

}
