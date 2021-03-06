/*
 * 
 */
package koier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


// TODO: Auto-generated Javadoc
/**
 * The Class Login.
 */
public class Login extends Application {
	
	/** The naa bruker. */
	Bruker naaBruker;
	
	/** The log fail. */
	private int logFail = 0;
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws SQLException {
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
		primaryStage.setTitle("Reservasjon");	// Setter tittel
		TextField userText = new TextField();	// Lager tekstfelt for brukernavn
		PasswordField passwordText = new PasswordField();	// lager tekstfelt for passord
		Label userLabel = new Label("Brukernavn:");	// Label for brukernavn
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
				
		//For å registerer ny bruker
		Label registrerBruker = new Label("Registerer ny bruker");
		registrerBruker.setPadding(new Insets(10,10,10,10));
		registrerBruker.setUnderline(true);
		registrerBruker.setOnMouseClicked((event) ->{
			registrerBruker.setTextFill(Color.RED);
			try {
				new RegBruker().start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			primaryStage.close();
		});
		Label kontakt = new Label("Ved feil, kontakt oss paa mail: hjelp@hjelp.reg.no");
		kontakt.setPadding(new Insets(10,10,10,10));
		kontakt.setAlignment(Pos.CENTER);
		
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
		

		final FlowPane regis = new FlowPane();
		regis.getChildren().add(registrerBruker);
		regis.setAlignment(Pos.CENTER);

		hovedLog.getChildren().addAll(topp,brukerBox,passordBox,knappBox,regis, kontakt);
		Scene loginScene = new Scene(hovedLog,400,300);
		loginScene.setFill(Color.LIGHTGREY);
		primaryStage.setScene(loginScene);
		primaryStage.resizableProperty().set(false);
		primaryStage.show();
		topp.setStyle("-fx-background-color: lightgrey;");
		brukerBox.setStyle("-fx-background-color: lightgrey;");
		passordBox.setStyle("-fx-background-color: lightgrey;");
		knappBox.setStyle("-fx-background-color: lightgrey;");
		regis.setStyle("-fx-background-color: lightgrey;");
		kontakt.setStyle("-fx-background-color: lightgrey;");
		hovedLog.setStyle("-fx-background-color: lightgrey;");
		
		logButt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				String correctPassword = "";
				ResultSet resultSet = null;
				
				try {
					PreparedStatement statement = con.prepareStatement ("select * from bruker where brukernavn = " + "'" + userText.getText().toString() + "'");
					ResultSet results = statement.executeQuery();
					results.next();
					resultSet = results;
					correctPassword += results.getString(8);
					
					
					
					
				} catch (Exception e) {
					System.out.println(e);
				}

					if(!passwordText.getText().isEmpty() && passwordText.getText().equals(correctPassword)){ //Sjekker om brukernavn og passord stemmer og bytter skjermbilde.
						
						primaryStage.resizableProperty().set(true);
						logFail = 0;
						
						//sender data videre til ReserverKoie og lukker dette vinduet
						try {
							new Meny().start(new Stage(), createUser(resultSet));
							con.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						primaryStage.close();
						
						
					}
				else{	// Gir melding om at brukernavn og passord er feil
					if(logFail == 0){
						failedLabel.setText("Feil brukernavn eller passord!");
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
				Platform.exit();
			}
		});
	}
	
	//lager bruker nÃ¥r blir kalt
	/**
	 * Constructs a new Object Bruker. 
	 *
	 * @param results the results
	 * @return the bruker
	 * @throws SQLException the SQL exception
	 */
	public Bruker createUser (ResultSet results) throws SQLException {
		Bruker user = new Bruker(results.getInt(1), results.getString(2), results.getString(3), 
				  results.getString(4), results.getInt(5), results.getInt(6), results.getString(7), results.getString(8));
		return user;
	}
}
