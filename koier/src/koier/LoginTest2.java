package koier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class LoginTest2 extends Application {
	private TextField usrName;
	private PasswordField password;
	private int logFail = 0;	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Reservasjon");
		usrName = new TextField();
		password = new PasswordField();
		Label brukerNavn = new Label("Brukernavn:");
		Label passord = new Label("Passord:");
		Button logButt = new Button("Logg inn");
		Button avslutt = new Button("Avbryt");
		
		FlowPane lala= new FlowPane();
		
		Scene brukjar = new Scene(lala,500,500);
		
		//SplitPane logPane = new SplitPane();
		VBox logPane = new VBox();
		//logPane.setOrientation(Orientation.VERTICAL);
		
		final SplitPane sp1 = new SplitPane();
		sp1.setDividerPositions(0.5f);
		sp1.setOrientation(Orientation.HORIZONTAL);
		
		final BorderPane sp2 = new BorderPane();
		final FlowPane sp3 = new FlowPane(10,10);
		final FlowPane sp4 = new FlowPane(10,10);
		final HBox sp5 = new HBox();
		sp1.getItems().addAll(sp3,sp4);
		
		final Label loggInn = new Label("Logg inn");
		final Label failed = new Label("");
		failed.setTextFill(Color.RED);
		loggInn.setFont(Font.font(20));
		
		logButt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if((usrName.getText().contains("Haha"))&&(password.getText().contains("Hehe"))){ //Sjekker om brukernavn og passord stemmer og bytter skjermbilde.
					primaryStage.setScene(brukjar);
					logFail = 0;
				}
				else{	// Gir melding om at brukernavn og passord er feil
					if(logFail == 0){
						failed.setText("Feil brukernavn eller passord!");
						//sp2.setBottom(failed);
						//BorderPane.setAlignment(failed, Pos.BOTTOM_CENTER);
						logFail = 1;
					}
				}
			}
		});
		// Setter enter som actionevent i tekstfeltene til brukernavn og passord
		usrName.setOnAction((event) ->{
			logButt.fire();
		});
		password.setOnAction((event) ->{
			logButt.fire();
		});
		
		sp4.getChildren().addAll(usrName,password);
		sp3.getChildren().addAll(brukerNavn,passord);
		
		sp5.getChildren().addAll(avslutt,new Label("	"),logButt);
		sp5.setAlignment(Pos.CENTER);
		//sp5.setCenter(logButt);
		//sp5.setBottom(avslutt);
		//BorderPane.setAlignment(logButt, Pos.CENTER);
		//BorderPane.setAlignment(avslutt, Pos.CENTER);
		
		sp2.setCenter(loggInn);
		sp2.setBottom(failed);
		BorderPane.setAlignment(failed, Pos.BOTTOM_CENTER);
		
		logPane.getChildren().addAll(sp2,sp1,sp5);
		//logPane.getItems().addAll(sp2, sp1,sp5);
		//logPane.setDividerPositions(0.3f, 0.7f);
		Scene login = new Scene(logPane,300,250);
		primaryStage.setScene(login);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
