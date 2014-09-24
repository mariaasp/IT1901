package koier;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Stage;

public class JabaFX extends Application {
	public static final ObservableList<String> koier = FXCollections.observableArrayList();
	public static final ObservableList<String> data = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tittel");
		
		final ListView<String> listView = new ListView<String>(data);
		listView.setPrefSize(200, 250);
		listView.setEditable(true);
		
		koier.addAll("Koie1","Koie2","Koie3","Koie3","Koie4","Koie5","Koie6","Koie7","Koie8","Koie9","Koie10","Koie11","Koie12","Koie13","Koie14","Koie15","Koie16");
		for(int i = 0;i < 18; i++){
			data.add("anonym");
		}	
		listView.setItems(data);
		listView.setCellFactory(ComboBoxListCell.forListView(koier));
		Group root1 = new Group();
		Group root2 = new Group();
		Group root3 = new Group();
		Group root4 = new Group();
		
		final Scene scene1 = new Scene(root1,300,250);
		final Scene scene2 = new Scene(root2,300,250);
		final Scene scene3 = new Scene(root3,300,250);
		final Scene scene4 = new Scene(root4,300,250);
		
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
		go2.setLayoutX(210);
		go2.setLayoutY(80);
		go2.setText("Gå til scene3");
		go2.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaryStage.setScene(scene3);
			}
		});
		root2.getChildren().addAll(new TextField(), go2,listView);
		Button go3 = new Button();
		go3.setLayoutX(100);
		go3.setLayoutY(200);
		go3.setText("Tilbake til scene4");
		go3.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaryStage.setScene(scene4);
			}
		});
		root3.getChildren().addAll(new TextArea(),go3);
		Button go4 = new Button();
		go4.setLayoutX(100);
		go4.setLayoutY(200);
		go4.setText("Tilbake til scene1");
		go4.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				primaryStage.setScene(scene1);
			}
		});
		root4.getChildren().addAll(go4);
		
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
