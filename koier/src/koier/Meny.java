package koier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;



public class Meny extends Application {

	public static void main(String[] args) {
		Application.launch(Meny.class);
	}

	public void start(Stage primaryStage, Bruker bruker) {

		Pane pane = new Pane();
		pane.setPrefHeight(400);
		pane.setPrefWidth(600);
		Scene scene = new Scene(pane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Meny");
//		Bruker bruker = new Bruker();
		
		
		Label velkommen = new Label("Velkommen, " + bruker.fornavn + " " + bruker.etternavn); // "velkommen bruker"
		velkommen.setLayoutX(65.0);
	    velkommen.setLayoutY(30);
	    velkommen.setPrefHeight(52.0);
	    velkommen.setPrefWidth(650.0);
	    velkommen.setAlignment(Pos.CENTER_LEFT);
	    velkommen.setFont(new Font("System", 27)); 
		
		
		Button reservasjon = new Button();
		reservasjon.setLayoutX(65.0);
		reservasjon.setLayoutY(132.0);
		reservasjon.setPrefHeight(69.0);
		reservasjon.setPrefWidth(145.0);
		reservasjon.setText("Reservasjon");
		reservasjon.setFont(new Font("System", 18));
		
		Button rapport = new Button();
		rapport.setLayoutX(65.0);
		rapport.setLayoutY(270.0);
		rapport.setPrefHeight(69.0);
		rapport.setPrefWidth(145.0);
		rapport.setText("Rapport");
		rapport.setFont(new Font("System", 18));
		
		Button loggUt = new Button();
		loggUt.setLayoutX(347.0);
		loggUt.setLayoutY(299.0);
		loggUt.setPrefHeight(40.0);
		loggUt.setPrefWidth(145.0);
		loggUt.setText("Logg ut");
		loggUt.setFont(new Font("System", 18));
		
		Button admRap = new Button();
		admRap.setLayoutX(347.0);
		admRap.setLayoutY(132);
		admRap.setPrefHeight(40.0);
		admRap.setPrefWidth(145.0);
		admRap.setText("Adminrapport");
		admRap.setFont(new Font("System", 18));
		
		
		pane.getChildren().addAll(velkommen, reservasjon, rapport, loggUt, admRap);
		primaryStage.show();
		
		reservasjon.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				try {
					new ReserverKoie().start(new Stage(), bruker);
				} catch (Exception e) {
					e.printStackTrace();
				}
				primaryStage.close();
				
			}
		});
		
		rapport.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				try {
					new RapportKlasse().start(new Stage(), bruker);
				} catch (Exception e) {
					e.printStackTrace();
				}
				primaryStage.close();
				
			}
		});
		
		loggUt.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			try {
				new Login().start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			primaryStage.close();
			
		}
	});
		admRap.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			try {
				new AdmRap().start(new Stage(), bruker);
			} catch (Exception e) {
				e.printStackTrace();
			}
			primaryStage.close();
			
		}
	});
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
