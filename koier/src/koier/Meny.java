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
		primaryStage.resizableProperty().set(false);
		
		
		Label velkommen = new Label("Velkommen, " + bruker.fornavn + " " + bruker.etternavn); // "velkommen bruker"
		velkommen.setLayoutX(65.0);
	    velkommen.setLayoutY(30);
	    velkommen.setPrefHeight(52.0);
	    velkommen.setPrefWidth(650.0);
	    velkommen.setAlignment(Pos.CENTER_LEFT);
	    velkommen.setFont(new Font("System", 27)); 
		
		
		Button reservasjon = new Button();
		reservasjon.setLayoutX(75.0);
		reservasjon.setLayoutY(137.0);
		reservasjon.setPrefHeight(59.0);
		reservasjon.setPrefWidth(169.0);
		reservasjon.setText("Reserver koie");
		reservasjon.setFont(new Font("System", 18));
		
		Button rapport = new Button();
		rapport.setLayoutX(75.0);
		rapport.setLayoutY(230.0);
		rapport.setPrefHeight(59.0);
		rapport.setPrefWidth(169.0);
		rapport.setText("Skriv rapport");
		rapport.setFont(new Font("System", 18));
		
		Button loggUt = new Button();
		loggUt.setLayoutX(220.0);
		loggUt.setLayoutY(330.0);
		loggUt.setPrefHeight(40.0);
		loggUt.setPrefWidth(137.0);
		loggUt.setText("Logg ut");
		loggUt.setFont(new Font("System", 18));
		
		Label adminLabel = new Label(" For Administrator: ");
		adminLabel.setPrefHeight(40);
		adminLabel.setPrefWidth(137);
		adminLabel.setLayoutX(343);
		adminLabel.setLayoutY(100);
		
		Button admRap = new Button();
		admRap.setLayoutX(339.0);
		admRap.setLayoutY(137.0);
		admRap.setPrefHeight(59.0);
		admRap.setPrefWidth(169.0);
		admRap.setText("Rapporter");
		admRap.setFont(new Font("System", 18));
		
		Button resRap = new Button();
		resRap.setLayoutX(339.0);
		resRap.setLayoutY(230.0);
		resRap.setPrefHeight(59.0);
		resRap.setPrefWidth(169.0);
		resRap.setText("Reservasjoner");
		resRap.setFont(new Font("System", 18));
		
		pane.getChildren().addAll(velkommen, reservasjon, rapport, loggUt, admRap,resRap, adminLabel);
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
		resRap.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent event){
			try {
				new ReservasjonsRap().start(new Stage(), bruker);
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
