package koier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginTest extends Application {
	private TextField usrName;
	private PasswordField pswrd;
	private Label respo;
	private int te = 0;
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Reservasjon");
		Label userNam = new Label("Username:");
		Label passward = new Label("Password:");
		usrName = new TextField("hahaha");
		pswrd = new PasswordField();
		usrName.setAlignment(Pos.CENTER);
		pswrd.setAlignment(Pos.CENTER);
		Button logButt = new Button("Login");
		FlowPane rootLog = new FlowPane(10,5);
		FlowPane rootBru = new FlowPane();
		FlowPane rootAdm = new FlowPane();

		final Scene login = new Scene(rootLog,250,250);
		final Scene brukjar = new Scene(rootBru, 500,500);
		final Scene Adman = new Scene(rootAdm,500,500);
		logButt.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				if((usrName.getText().contains("Haha"))&&(pswrd.getText().contains("Hehe"))){
					primaryStage.setScene(brukjar);
					te = 0;
				}
				else{
					if(te == 0){
						Label respo = new Label("Feil brukernavn eller passord!");
						respo.setAlignment(Pos.BASELINE_LEFT);
						rootLog.getChildren().add(respo);
						te = 1;
					}
				}
			}
		});

		rootLog.getChildren().addAll(userNam,usrName,passward, pswrd, logButt);
		rootLog.setAlignment(Pos.CENTER);
		primaryStage.setScene(login);
		primaryStage.show();
		
		//rootLog.setAlignment(Pos.CENTER);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
