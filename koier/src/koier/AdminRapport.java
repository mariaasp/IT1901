package koier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import koier.DBConnectFlogger; 

public class AdminRapport extends Application {
	String[][] tabell;
	private ObservableList<ObservableList> data;
	private TableView<String> tableview;
	
	public static void main(String[] args) {
		Application.launch(AdminRapport.class);
		
	}
	
	
	public void start(Stage primaryStage) throws SQLException {
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
		
		
		try {
			PreparedStatement statement = con.prepareStatement ("select * from skaderapport");
			ResultSet results = statement.executeQuery();
			statement = con.prepareStatement("SELECT COUNT(*) FROM skaderapport");
			//ResultSet teller = statement.executeQuery();
			tabell = new String[2][7];//teller.getInt(1)][8];
			for(int i = 0; i < 50; i++){
				results.next();
				for(int j = 0; j < 7;j++){
					tabell[i][j] = results.getString(j+1);
					System.out.println(tabell[i][j]);
				}
			}
		} catch (Exception e) {	
			System.out.println("Feil");
		}

		
		Label data = new Label();
		//data.setText();
		data.setAlignment(Pos.CENTER);
		
		
		
		
		Pane pane = new Pane();
		pane.setPrefWidth(100);
		pane.setPrefHeight(100);
		Scene scene = new Scene(pane, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Admin rapp");
		
		
		
		Label head = new Label();
		head.setText("Admin rapp");
		head.setAlignment(Pos.CENTER_LEFT);
		//head.setLayoutX(25);
		//head.setLayoutY(25);
		//head.setPrefHeight(25);
		//head.setPrefWidth(25);
		
		
		Label list = new Label();
		list.setText("blaaa");
		list.setAlignment(Pos.CENTER_LEFT);
		//head.setLayoutX(25);
		head.setLayoutY(100);
		//head.setPrefHeight(25);
		//head.setPrefWidth(25);
		
		
		DatePicker repdato = new DatePicker();
		repdato.setLayoutX(14.0);
		repdato.setLayoutY(220.0);
		repdato.setPrefHeight(26.0);
		repdato.setPrefWidth(324.0);
		repdato.setPromptText("Reparasjonsdato");
		
		
		ToggleGroup radio = new ToggleGroup();
		RadioButton button1 = new RadioButton("First");
		button1.setToggleGroup(radio);
		button1.setLayoutX(363.0);
		button1.setLayoutY(335.0);
		button1.setPrefHeight(34.0);
		button1.setPrefWidth(118.0);
		RadioButton button2 = new RadioButton("Second");
		button2.setToggleGroup(radio);
		button2.setToggleGroup(radio);
		button2.setLayoutX(363.0);
		button2.setLayoutY(365.0);
		button2.setPrefHeight(34.0);
		button2.setPrefWidth(118.0);
		//RadioButton button3 = new RadioButton("Third")
		
		
		
		
//	    private final StringProperty skadeID;
//	    private final StringProperty koienr;
//	    private final ObjectProperty<LocalDate> skadeDato;
//	    private final StringProperty skade;
//	    private final StringProperty brukerID;
//	    private final ObjectProperty<LocalDate> reparasjonsdato;
//	    private final StringProperty adminID;
//	    
//	    
//	    TableView<Skad>
//	    
//	    public void init() {
//	    	table = new TableView<Skad>
//	    }
	    
		
		
		
		
		
		
		
		
		
		
		
//		TableView<AdminRapport> tabell = new TableView<AdminRapport>();
//		
//		ObservableList<AdminRapport> tabellData = gettabellData();
//		 tabell.setItems(tabellData);
//		 
//		 TableColumn<AdminRapport,String> firstNameCol = new TableColumn<AdminRapport,String>("First Name");
//		 firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
//		 TableColumn<AdminRapport,String> lastNameCol = new TableColumn<AdminRapport,String>("Last Name");
//		 lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
//		 
//		 tabell.getColumns().setAll(firstNameCol, lastNameCol);
		
		
		
		
		pane.getChildren().addAll(head, button1, button2, repdato, list, data);
		
		primaryStage.show();
		
	}

}


/*class DBconnection {
	private static Connection conn;
	private static String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier";
	private static String user = "nilsad";//Username of database
	private static String pass = "passord1212";//password of the database
	public static Connection connect() throws SQLException {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(ClassNotFoundException cnfe){
			System.err.println("Error: "+cnfe.getMessage());
			}catch(InstantiationException ie){
				System.err.println("Error: "+ ie.getMessage());
			}catch(IllegalAccessException iae){  
			       System.err.println("Error: "+iae.getMessage());  
		     }  
		conn = DriverManager.getConnection(url,user,pass);  
	     return conn;  
	   }  
	   public static Connection getConnection() throws SQLException, ClassNotFoundException{  
	     if(conn !=null && !conn.isClosed())  
	       return conn;  
	     connect();  
	     return conn; 
		}
	}
	
			
class DisplayDatabase{
	//tableview
	private static ObservableList<ObservableList> data;
	//conn DB
	public static void buildData(TableView tableView){
		Connection c;
		data = FXCollections.observableArrayList();
		try{
			c = DBConnectFlogger.connect();
			String SQL = "SELECT * FROM skaderapport;";
			//rs
			ResultSet rs = c.createStatement().executeQuery(SQL);
			
			//Adde tables og columns
			
			for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){  
			final int j = i;
			//System.out.println("Hei");
			TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
			col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>,ObservableValue<String>>(){
				public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
					return new SimpleStringProperty(param.getValue().get(j).toString());
				}
			});
			tableView.getColumns().addAll(col);
			
		}
			
			//adde data til observable list
			
			while(rs.next()){//iterere row
				ObservableList<String> row = FXCollections.observableArrayList();
				for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
					//iterere col
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added "+ row);
				data.add(row);
				}
			//finally
			tableView.setItems(data);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error on building data");
			}
			
	}
}


class FXDatabaseDisplay extends Application{
	@Override
	public void start(Stage stage) throws Exception{
		stage.setFullScreen(true);
		TableView tableView;
		tableView = new TableView();
		DisplayDatabase.buildData(tableView);
		//add grid pane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		//main scene
		Scene scene = new Scene(tableView);
		stage.setScene(scene);
		stage.show();
	}
	public static void main (String args[]){
		Application.launch(null);
	}
}*/


