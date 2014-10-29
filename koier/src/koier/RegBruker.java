package koier;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class RegBruker extends Application {

	public static void main(String[] args) {
		Application.launch(RegBruker.class);
	}
	
	public void start(Stage primaryStage) {
		TextField brukerId = new TextField("Studentnummer");
		TextField forNavn = new TextField("Foravn");
		TextField etterNavn = new TextField("EtterNavn");
		TextField email = new TextField("Email");
		TextField mobilNr = new TextField("Mobilnummer");
		TextField brukerNavn = new TextField("Brukernavn");
		PasswordField passord = new PasswordField();
		passord.setPromptText("Passord");
		PasswordField passordRep = new PasswordField();
		passordRep.setPromptText("Gjenta passord");
		
		Button avbryt = new Button("Avbryt");
		Button regButt = new Button("Registrer");
		FlowPane test = new FlowPane();
		Scene testScene = new Scene(test,500,500);
		test.getChildren().addAll(brukerId,forNavn,etterNavn,email,mobilNr,brukerNavn,passord,passordRep,avbryt,regButt);
		primaryStage.setScene(testScene);
		primaryStage.show();
		
		regButt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				boolean altOk = false;
				if()
				if(passord.getText() != passordRep.getText())
				
				if(altOk){
					try {
						new Login().start(new Stage());
					} catch (Exception e) {
						e.printStackTrace();
					}
					primaryStage.close();
				}
			}
		});
		
		avbryt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				try {
					new Login().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				primaryStage.close();
			}
		});
		
	}
	/*@Override
	public void start(Stage primaryStage) throws Exception {
		// denne må være her av en eller annen grunn :S
		
	}*/
}