package koier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class JabaFX extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tittel");
		
		Group root1 = new Group();
		Group root2 = new Group();
		Group root3 = new Group();
		
		final Scene scene1 = new Scene(root1,300,250);
		final Scene scene2 = new Scene(root2,300,250);
		final Scene scene3 = new Scene(root3,300,250);
		
		Button go1 = new Button();
		go1.setLayoutX(100);
		go1.setLayoutY(80);
		go1.setText("Gå til scene2");
		go1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaryStage.setScene(scene2);
			}
		});
		root1.getChildren().addAll(new Label("Scene 1"), go1);
		
		Button go2 = new Button();
		go2.setLayoutX(100);
		go2.setLayoutY(80);
		go2.setText("Gå til scene3");
		go2.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaryStage.setScene(scene3);
			}
		});
		root2.getChildren().addAll(new TextField(), go2);
		
		Button go3 = new Button();
		go3.setLayoutX(100);
		go3.setLayoutY(80);
		go3.setText("Tilbake til scene1");
		go3.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaryStage.setScene(scene1);
			}
		});
		root3.getChildren().addAll(new TextArea(),go3);
		
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
