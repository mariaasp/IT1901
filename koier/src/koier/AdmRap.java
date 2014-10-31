package koier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


public class AdmRap extends Application {
	
	TextField textField_name;
	TextField textField_lastname;
	TextField textField_email;
	int skadeNrSend;
	
	public static class Record {

		private final SimpleIntegerProperty skadeId;
		private final SimpleIntegerProperty koieNr;
		private final SimpleStringProperty skadeDato;
		private final SimpleStringProperty skade;
		private final SimpleIntegerProperty brukerId;
		private final SimpleStringProperty repDato;
		private final SimpleIntegerProperty adminId;

		private Record(final int skadeId, final int koieNr, final String skadeDato, final String skade, final int brukerId, final String repDato, final int adminId) {

			System.out.println("Platform.isFxApplicationThread(): "
					+ Platform.isFxApplicationThread());
			this.skadeId = new SimpleIntegerProperty(skadeId);
			this.koieNr = new SimpleIntegerProperty(koieNr);
			this.skadeDato = new SimpleStringProperty(skadeDato);
			this.skade = new SimpleStringProperty(skade);
			this.brukerId = new SimpleIntegerProperty(brukerId);
			this.repDato = new SimpleStringProperty(repDato);
			this.adminId = new SimpleIntegerProperty(adminId);
		}
		public String getSkadeDato(){
			return this.skadeDato.get();
		}
		public void setSkadeDato(final String date){
			this.skadeDato.set(date);
		}
		public String getSkade(){
			return this.skade.get();
		}
		public void setSkade(final String date){
			this.skade.set(date);
		}
		public String getRepDato(){
			return this.repDato.get();
		}
		public void setRepDato(final String date){
			this.repDato.set(date);
		}
		public int getSkadeId(){
			return this.skadeId.get();
		}
		public void setSkadeId(final int skadeId){
			this.skadeId.set(skadeId);
		}
		public int getKoieNr(){
			return this.koieNr.get();
		}
		public void setKoieNr(final int skadeId){
			this.koieNr.set(skadeId);
		}
		public int getBrukerId(){
			return this.brukerId.get();
		}
		public void setBrukerId(final int skadeId){
			this.brukerId.set(skadeId);
		}
		public int getAdminId(){
			return this.adminId.get();
		}
		public void setAdminId(final int skadeId){
			this.adminId.set(skadeId);
		}
	}
	private final TableView<Record> tableView = new TableView<>();
	private final ObservableList<Record> rapportList = FXCollections.observableArrayList();

	private void regReperasjon(int skadeId, String dato, int adminID) throws SQLException{
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
		
		try{
			PreparedStatement statement = con.prepareStatement ("UPDATE skaderapport SET(reperasjonsdato = ?, adminID = ?) WHERE skadeID = ?");
			statement.setString(1,"'"+dato+"'");
			statement.setInt(2,adminID);
			statement.setInt(3,skadeId);
			statement.executeUpdate();
			con.close();
		} catch(Exception e){
			System.out.println("AAARh");
		}
		
	}
	private void hentRapport() throws SQLException {
		rapportList.clear();
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");

		try {
			PreparedStatement statement = con.prepareStatement ("select * from skaderapport");
			ResultSet results = statement.executeQuery();
			for(int i = 0; i < 50; i++){
				results.next();
				rapportList.add(new Record(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),results.getInt(5),results.getString(6),results.getInt(7)));
			}
			con.close();
		} catch (Exception e) {	
			System.out.println("Feil");
		}
	}

	@Override 
	public void start(final Stage primaryStage) throws Exception {
		
	}

	public void start(final Stage primaryStage, Bruker bruker) throws SQLException {
		final Scene scene = new Scene(new Group());
		primaryStage.setTitle("Rapport");
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);

		hentRapport();

		tableView.setEditable(false);

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

		TableColumn colId = new TableColumn("ID");
		colId.setCellValueFactory(new PropertyValueFactory<Record, String>("skadeId"));
		colId.setCellFactory(integerCellFactory);

		TableColumn colKoieNr = new TableColumn("Koienummer");
		colKoieNr.setCellValueFactory(new PropertyValueFactory<Record, String>("koieNr"));
		colKoieNr.setCellFactory(integerCellFactory);

		TableColumn colSkadeDato = new TableColumn("Dato skadet");
		colSkadeDato.setCellValueFactory(new PropertyValueFactory<Record, String>("skadeDato"));
		colSkadeDato.setCellFactory(stringCellFactory);

		TableColumn colSkade = new TableColumn("Skade");
		colSkade.setCellValueFactory(new PropertyValueFactory<Record, String>("skade"));
		colSkade.setCellFactory(stringCellFactory);
		
		TableColumn colBrukerId = new TableColumn("BrukerID");
		colBrukerId.setCellValueFactory(new PropertyValueFactory<Record, String>("brukerId"));
		colBrukerId.setCellFactory(integerCellFactory);

		TableColumn colRepDato = new TableColumn("Reperasjons dato");
		colRepDato.setCellValueFactory(new PropertyValueFactory<Record, String>("repDato"));
		colRepDato.setCellFactory(stringCellFactory);

		TableColumn colAdminId = new TableColumn("AdminID");
		colAdminId.setCellValueFactory(new PropertyValueFactory<Record, String>("adminId"));
		colAdminId.setCellFactory(integerCellFactory);
		
		tableView.setItems(rapportList);
		tableView.getColumns().addAll(colId, colKoieNr, colSkadeDato, colSkade, colBrukerId, colRepDato, colAdminId);

		final VBox vbox = new VBox();
		
		Label label_dato = new Label("Reperasjonsdato");
		DatePicker dato = new DatePicker();
		dato.setPromptText("Reservasjonsdato");
		HBox hBox_dato = new HBox();
		hBox_dato.setSpacing(10);
		hBox_dato.getChildren().addAll(label_dato, dato);

		Button regButt = new Button("Registrer");
		regButt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				try {
					regReperasjon(skadeNrSend,dato.toString(),bruker.getBrukerID());
					hentRapport();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(hBox_dato,regButt, tableView);

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

		@Override
		public void handle(final MouseEvent t) {
			final TableCell c = (TableCell) t.getSource();
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

		}
	}
}