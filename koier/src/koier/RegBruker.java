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
	Label feilTlf = new Label();
	Label galtFornavn = new Label();
	Label galtEtternavn = new Label();
	Label galtStud = new Label();
	Label galMail = new Label();
	Label galtBruker = new Label();
	
	boolean altOk = true;
	
	int studNr;
	int mobil;
	String passordLag;
	String passordLagRep;
	String forNavnLag;
	String etterNavnLag;
	String postAdresse;
	String brukerNavnLag;

	public static void main(String[] args) {
		Application.launch(RegBruker.class);
	}
	
	public void start(Stage primaryStage) throws SQLException {
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
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
		feilTlf.setTextFill(Color.RED);
		galtFornavn.setTextFill(Color.RED);
		galtEtternavn.setTextFill(Color.RED);
		galtStud.setTextFill(Color.RED);
		galMail.setTextFill(Color.RED);
		galtBruker.setTextFill(Color.RED);
		
		Button avbryt = new Button("Avbryt");
		Button regButt = new Button("Registrer");
		FlowPane test = new FlowPane();
		Scene testScene = new Scene(test,500,500);
		
		regButt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				boolean altOk = true;
				
				passordLag = passord.getText();
				passordLagRep = passordRep.getText();
				forNavnLag = forNavn.getText();
				etterNavnLag = etterNavn.getText();
				postAdresse = email.getText();
				brukerNavnLag = brukerNavn.getText();
				
				galtPass.setText("");
				feilTlf.setText("");
				galtFornavn.setText("");
				galtEtternavn.setText("");
				galtStud.setText("");
				galMail.setText("");
				galtBruker.setText("");
				
				if(forNavnLag.length()<2){
					galtFornavn.setText("Fornavn må være minimum 2 bokstaver");
					altOk  = false;
				}
				else if(forNavnLag.matches(".*\\d.*")){
					   // contains a number
					galtFornavn.setText("Fornavn kan ikke inneholde tall");
					altOk = false;
				} 
				if(etterNavnLag.length()<2){
					galtEtternavn.setText("Etternavn må være minimum 2 bokstaver");
					altOk  = false;
				}
				else if(etterNavnLag.matches(".*\\d.*")){
					   // contains a number
					galtEtternavn.setText("Etternavn kan ikke inneholde tall");
					altOk = false;
				} 
				try{
					mobil = Integer.parseInt(mobilNr.getText());
				} catch(Exception e){
					altOk = false;
					feilTlf.setText("Ugyldig telefonnummer");
				}
				try{
					studNr = Integer.parseInt(brukerId.getText());
				} catch(Exception e){
					altOk = false;
					galtStud.setText("Ugyldig studentnummer");
				}
				if(!(postAdresse.contains("@ntnu.no"))){
					galMail.setText("Ugyldig mail. Må være @ntnu.no");
					altOk = false;
				}
				
				if(passordLag.length() < 6){
					galtPass.setText("Passordet er ugyldig. Må være lengre en 6 tegn.");
					altOk = false;
				}
				else if(!(passordLag.equals(passordLagRep))){
					galtPass.setText("Passordene må være like");
					altOk = false;
				}

				try {
					PreparedStatement statement = con.prepareStatement ("select * from bruker where brukernavn = " + "'" + brukerNavnLag + "'");
					ResultSet results = statement.executeQuery();
					results.next();
					String mild = results.getString(8);
					altOk = false;
					galtBruker.setText("Brukernavnet finnes allerede");
				} catch (Exception e) {
				}
				try {
					PreparedStatement statement = con.prepareStatement ("select * from bruker where mail = " + "'" + postAdresse + "'");
					ResultSet results = statement.executeQuery();
					results.next();
					String mild = results.getString(8);
					altOk = false;
					galMail.setText("Emailadressen finnes allerede");
				} catch (Exception e) {
				}
				try {
					PreparedStatement statement = con.prepareStatement ("select * from bruker where brukerID = " + "'" + studNr + "'");
					ResultSet results = statement.executeQuery();
					results.next();
					String mild = results.getString(8);
					altOk = false;
					galtStud.setText("Studentnummeret finnes allerede");
				} catch (Exception e) {
				}
				try {
					PreparedStatement statement = con.prepareStatement ("select * from bruker where mobilnr = " + "'" + mobil + "'");
					ResultSet results = statement.executeQuery();
					results.next();
					String mild = results.getString(8);
					altOk = false;
					feilTlf.setText("Tlfnummer er registrert på annen bruker");
				} catch (Exception e) {
				}


				
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
		
		test.getChildren().addAll(brukerId,forNavn,etterNavn,email,mobilNr,brukerNavn,passord,passordRep,avbryt,regButt,galtPass,feilTlf, galtFornavn,galtEtternavn, galtStud,galMail,galtBruker);
		primaryStage.setScene(testScene);
		primaryStage.show();
		
	}
}