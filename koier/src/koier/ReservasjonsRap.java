package koier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import koier.AdmRap.MyEventHandler;
import koier.AdmRap.MyIntegerTableCell;
import koier.AdmRap.MyStringTableCell;
import koier.AdmRap.Record;


public class ReservasjonsRap extends Application {

	
	TextField textField_name;
	TextField textField_lastname;
	TextField textField_email;
	int skadeNrSend;
	boolean nyDato = false;
	DatePicker dato;
	
	public static class Record {

		private final SimpleIntegerProperty koieNr;
		private final SimpleIntegerProperty reservertePlasser;
		private final SimpleIntegerProperty brukerId;
		private final SimpleIntegerProperty turlederId;
		private final SimpleStringProperty fraDato;
		private final SimpleStringProperty tilDato;

		private Record(final int koieNr, final int reservertePlasser, final int brukerId, final int turlederId, final String fraDato, final String tilDato){

			this.koieNr = new SimpleIntegerProperty(koieNr);
			this.reservertePlasser = new SimpleIntegerProperty(reservertePlasser);
			this.brukerId = new SimpleIntegerProperty(brukerId);
			this.turlederId = new SimpleIntegerProperty(turlederId);
			this.fraDato = new SimpleStringProperty(fraDato);
			this.tilDato = new SimpleStringProperty(tilDato);
		}
		public String getTilDato(){
			return this.tilDato.get();
		}
		public void setTilDato(final String tilDato){
			this.tilDato.set(tilDato);
		}
		public String getFraDato(){
			return this.fraDato.get();
		}
		public void setFraDato(final String fraDato){
			this.fraDato.set(fraDato);
		}
		public int getKoieNr(){
			return this.koieNr.get();
		}
		public void setKoieNr(final int koieNr){
			this.koieNr.set(koieNr);
		}
		public int getReservertePlasser(){
			return this.reservertePlasser.get();
		}
		public void setReservertePlasser(final int reservertePlasser){
			this.reservertePlasser.set(reservertePlasser);
		}
		public int getBrukerId(){
			return this.brukerId.get();
		}
		public void setBrukerId(final int brukerId){
			this.brukerId.set(brukerId);
		}
		public int getTurlederId(){
			return this.turlederId.get();
		}
		public void setTurlederId(final int turlederId){
			this.turlederId.set(turlederId);
		}
	}
	private final TableView<Record> tableView = new TableView<>();
	private final ObservableList<Record> rapportList = FXCollections.observableArrayList();

	private void regReperasjon(int skadeId, String dato, int adminID) throws SQLException{
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
		
		try{
			PreparedStatement statement = con.prepareStatement ("UPDATE skaderapport SET reperasjonsdato = "+"'"+dato+"'"+","+"adminID = "+adminID+" WHERE skadeID = "+skadeId);
			statement.executeUpdate();
			hentRapport(nyDato);
			con.close();
		} catch(Exception e){
		}
		
	}
	private void hentRapport(final boolean hentNyDato) throws SQLException {
		rapportList.clear();
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");

		try {
			
			PreparedStatement statement = con.prepareStatement ("select * from reservasjon");
			ResultSet results = statement.executeQuery();
			if(hentNyDato){
				LocalDate date = dato.getValue();
				for(int i = 0; i < 50;i++){
					results.next();
					if(date.toString().compareTo(results.getString(5))<=0){
						rapportList.add(new Record(results.getInt(1),results.getInt(2),results.getInt(3),results.getInt(4),results.getString(5),results.getString(6)));
					}
				}
			}
			else{
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(int i = 0; i < 50; i++){
					results.next();
					if(sdf.format(date).compareTo(results.getString(5))<=0){
						rapportList.add(new Record(results.getInt(1),results.getInt(2),results.getInt(3),results.getInt(4),results.getString(5),results.getString(6)));
					}
				}
			}
			
		}catch (Exception e) {	
			con.close();
		}
	}

	@Override 
	public void start(final Stage primaryStage) throws Exception {
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(final Stage primaryStage, Bruker bruker) throws SQLException {
		final Scene scene = new Scene(new Group());
		primaryStage.setTitle("Rapport");
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);

		hentRapport(nyDato);

		//tableView.setEditable(false);

		final Callback<TableColumn, TableCell> integerCellFactory =
				new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(final TableColumn p) {
				final MyIntegerTableCell cell = new MyIntegerTableCell();
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
				return cell;
			}
		};

		Callback<TableColumn, TableCell> stringCellFactory =
				new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn p) {
				MyStringTableCell cell = new MyStringTableCell();
				cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
				return cell;
			}
		};
		
		TableColumn colKoieNr = new TableColumn("Koienummer");
		colKoieNr.setCellValueFactory(new PropertyValueFactory<Record, String>("koieNr"));
		colKoieNr.setCellFactory(integerCellFactory);

		TableColumn colReservertePlasser = new TableColumn("Reserverte plasser");
		colReservertePlasser.setCellValueFactory(new PropertyValueFactory<Record, String>("reservertePlasser"));
		colReservertePlasser.setCellFactory(integerCellFactory);

		TableColumn colBrukerId = new TableColumn("BrukerID");
		colBrukerId.setCellValueFactory(new PropertyValueFactory<Record, String>("brukerId"));
		colBrukerId.setCellFactory(integerCellFactory);

		TableColumn colTurlederId = new TableColumn("Turleder");
		colTurlederId.setCellValueFactory(new PropertyValueFactory<Record, String>("turlederId"));
		colTurlederId.setCellFactory(integerCellFactory);
		
		TableColumn colFraDato = new TableColumn("fra dato");
		colFraDato.setCellValueFactory(new PropertyValueFactory<Record, String>("fraDato"));
		colFraDato.setCellFactory(stringCellFactory);

		TableColumn colTilDato = new TableColumn("til dato");
		colTilDato.setCellValueFactory(new PropertyValueFactory<Record, String>("tilDato"));
		colTilDato.setCellFactory(stringCellFactory);

		
		tableView.setItems(rapportList);
		tableView.getColumns().addAll(colKoieNr, colReservertePlasser, colBrukerId, colTurlederId, colFraDato, colTilDato);
		tableView.setPrefSize(600, 600);
		
		final VBox vbox = new VBox();
		
		Label label_dato = new Label("Reperasjonsdato");
		dato = new DatePicker();
		dato.setOnAction((event) ->{
			nyDato = true;
			try {
				hentRapport(nyDato);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		dato.setPromptText("Reservasjonsdato");
		HBox hBox_dato = new HBox();
		hBox_dato.setSpacing(10);
		hBox_dato.getChildren().addAll(label_dato, dato);

		Button regButt = new Button("Registrer");
		regButt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				LocalDate test = dato.getValue();
				try {
					regReperasjon(skadeNrSend,test.toString().replaceAll("-",""),bruker.getBrukerID());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button cancel = new Button("Avbryt");
		
		cancel.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				try {
					new Meny().start(new Stage(), bruker);
				} catch (Exception e) {
					e.printStackTrace();
				}
				primaryStage.close();
			}
		});
		
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(hBox_dato,regButt, tableView, cancel);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(final String[] args) {
		launch(args);
	}

	class MyIntegerTableCell extends TableCell<Record, Integer> {

		@Override
		public void updateItem(final Integer item, final boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : getString());
			setGraphic(null);
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}

	class MyStringTableCell extends TableCell<Record, String> {

		@Override
		public void updateItem(final String item, final boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : getString());
			setGraphic(null);
		}

		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}

	class MyEventHandler implements EventHandler<MouseEvent> {

		@SuppressWarnings("rawtypes")
		@Override
		public void handle(final MouseEvent t) {
			/*final T	ableCell c = (TableCell) t.getSource();
			final int index = c.getIndex();

			try {
				Record item = rapportList.get(index);
				skadeNrSend = item.getSkadeId();
				System.out.println(skadeNrSend);
				System.out.println("ID = " + item.getSkadeId());
				System.out.println("Koienummer = " + item.getKoieNr());
				System.out.println("Skadedato = " + item.getSkadeDato());
				System.out.println("Skade = " + item.getSkade());
				System.out.println("BrukerID = " + item.getBrukerId());
				System.out.println("Reperasjonsdato = " + item.getRepDato());
				System.out.println("AdminID = " + item.getAdminId());
			} catch (IndexOutOfBoundsException exception) {
				System.out.println("Feil");
			}
*/
		}
	}
}
