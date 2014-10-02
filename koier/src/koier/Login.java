package koier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Login extends Application {
	
	private int logFail = 0;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Reservasjon");
		TextField userText = new TextField();
		PasswordField passwordText = new PasswordField();
		Label userLabel = new Label("Brukernavn:");
		userLabel.setPadding(new Insets(10));
		userLabel.setAlignment(Pos.CENTER);
		Label passwordLabel = new Label("Passord:");
		passwordLabel.setPadding(new Insets(10));
		passwordLabel.setAlignment(Pos.CENTER);
		Button logButt = new Button("Logg inn");
		Button quitButt = new Button("Avbryt");
		final Label loggInnLabel = new Label("Logg inn");
		final Label failedLabel = new Label("");
		failedLabel.setTextFill(Color.RED);
		loggInnLabel.setFont(Font.font(20));
		
		FlowPane nesteSkjerm = new FlowPane();
		Scene nesteScene = new Scene(nesteSkjerm,500,500);
		
		VBox hovedLog = new VBox();
		hovedLog.setSpacing(0.6f);
		hovedLog.setPadding(new Insets(10,10,10,10));
		
		final BorderPane topp = new BorderPane();
		topp.setCenter(loggInnLabel);
		topp.setBottom(failedLabel);
		BorderPane.setAlignment(failedLabel,Pos.BOTTOM_CENTER);
		
		final HBox brukerBox = new HBox();
		brukerBox.setPadding(new Insets(10,10,10,10));
		brukerBox.setSpacing(0.5f);
		final HBox passordBox = new HBox();
		passordBox.setPadding(new Insets(10,10,10,10));
		passordBox.setSpacing(0.5f);
		brukerBox.getChildren().addAll(userLabel,userText);
		passordBox.getChildren().addAll(passwordLabel,new Label("     "),passwordText);
		
		final HBox knappBox = new HBox();
		knappBox.getChildren().addAll(quitButt,new Label("		"),logButt);
		knappBox.setAlignment(Pos.CENTER);
		
		hovedLog.getChildren().addAll(topp,brukerBox,passordBox,knappBox);
		Scene loginScene = new Scene(hovedLog,300,300);
		primaryStage.setScene(loginScene);
		primaryStage.resizableProperty().set(false);
		primaryStage.show();
		
		logButt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if((userText.getText().contains("Haha"))&&(passwordText.getText().contains("Hehe"))){ //Sjekker om brukernavn og passord stemmer og bytter skjermbilde.
					primaryStage.setScene(nesteScene);
					primaryStage.resizableProperty().set(true);
					logFail = 0;
				}
				else{	// Gir melding om at brukernavn og passord er feil
					if(logFail == 0){
						failedLabel.setText("Feil brukernavn eller passord!");
						//sp2.setBottom(failed);
						//BorderPane.setAlignment(failed, Pos.BOTTOM_CENTER);
						logFail = 1;
					}
				}
			}
		});
		// Setter enter som actionevent i tekstfeltene til brukernavn og passord
		userText.setOnAction((event) ->{
			logButt.fire();
		});
		passwordText.setOnAction((event) ->{
			logButt.fire();
		});
		quitButt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				System.exit(0);
			}
		});
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
