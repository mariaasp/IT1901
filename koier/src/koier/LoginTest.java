package koier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginTest extends Application {
	private TextField usrName; // Inneholder brukernav
	private PasswordField pswrd; //Inneholder passord fra bruker
	private Label respo; //melding om feil brukernav eller passord
	private int te = 0;
	@Override
	public void start(Stage primaryStage) {
		Label welcome = new Label("		Velkommen!		"); // Jalla fix på velkommen 
		welcome.setFont(Font.font(20));
		primaryStage.setTitle("Reservasjon"); // Setter tittel på vinduet
		Label userNam = new Label("Username:"); //  Sette navn ved brukernavnboks
		Label passward = new Label("Password:"); // Setter navn ved passordboks
		usrName = new TextField(); // initialiserer usrname
		pswrd = new PasswordField(); // initialiserer pswrd
		//usrName.setLayoutX(10);
		//usrName.setLayoutY(10);
		//usrName.setAlignment(Pos.CENTER);
		//pswrd.setAlignment(Pos.CENTER);
		Button logButt = new Button("Login"); // Lager loginknappen
		FlowPane rootLog = new FlowPane(10,5); //Lager root for loginscreen
		FlowPane rootBru = new FlowPane(); //lager root for nesteskjerm
		FlowPane rootAdm = new FlowPane(); //lager root for ubrukt skjerm
		//Lager de forskjellige scenene
		final Scene login = new Scene(rootLog,250,250); 
		final Scene brukjar = new Scene(rootBru, 500,500);
		final Scene Adman = new Scene(rootAdm,500,500);
		// Funksjon for hva som skal skje når loginknappen funker.
		//logButt.defaultButtonProperty().bind(logButt.focusedProperty());
		logButt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if((usrName.getText().contains("Haha"))&&(pswrd.getText().contains("Hehe"))){ //Sjekker om brukernavn og passord stemmer og bytter skjermbilde.
					primaryStage.setScene(brukjar);
					te = 0;
				}
				else{	// Gir melding om at brukernavn og passord er feil
					if(te == 0){
						Label respo = new Label("Feil brukernavn eller passord!");
						respo.setTextFill(Color.RED);
						respo.setAlignment(Pos.BASELINE_LEFT);
						rootLog.getChildren().add(respo);
						te = 1;
					}
				}
			}
		});
		// Setter enter som actionevent i tekstfeltene til brukernavn og passord
		usrName.setOnAction((event) ->{
			logButt.fire();
		});
		pswrd.setOnAction((event) ->{
			logButt.fire();
		});

		rootLog.getChildren().addAll(welcome,userNam,usrName,passward, pswrd, logButt);	// Gir ut alle barna? BLÆR
		rootLog.setAlignment(Pos.CENTER); //Setter pos til rootLog
		primaryStage.setScene(login); // Bestemmer hvilken scene som skal vises på stage
		primaryStage.show(); //Vister Stage
	}

	public static void main(String[] args) {
		launch(args);
	}
}
