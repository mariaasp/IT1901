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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegBruker extends Application {

	Label galtPass = new Label();
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
		
		galtPass.setTextFill(Color.RED);
		
		Button avbryt = new Button("Avbryt");
		Button regButt = new Button("Registrer");
		FlowPane test = new FlowPane();
		Scene testScene = new Scene(test,500,500);
		
		regButt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				boolean altOk = true;
				if(passord.getText().length() < 6){
					galtPass.setText("Passordet er ugyldig. MÂ vÊre lengre en 8 tegn.");
					altOk = false;
				}
				else if(!(passord.getText().equals(passordRep.getText()))){
					galtPass.setText("Passordene mÂ vÊre like");
					altOk = false;
				}
				//if()
				
				if(altOk == true){
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
		
		test.getChildren().addAll(brukerId,forNavn,etterNavn,email,mobilNr,brukerNavn,passord,passordRep,avbryt,regButt,galtPass);
		primaryStage.setScene(testScene);
		primaryStage.show();
		
	}
	/*@Override
	public void start(Stage primaryStage) throws Exception {
		// denne m√• v√¶re her av en eller annen grunn :S
		
	}*/
}