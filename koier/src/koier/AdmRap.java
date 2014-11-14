package koier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.application.Application;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;


// TODO: Auto-generated Javadoc
/**
 * The Class AdmRap.
 */
public class AdmRap extends Application {
	
	/** The item. */
	Record item;
	
	/** The temp. */
	Bruker temp;
	
	/** The text field_fornavn. */
	TextField textField_fornavn;
	
	/** The text field_etternavn. */
	TextField textField_etternavn;
	
	/** The text field_mobilnr. */
	TextField textField_mobilnr;
	
	/** The text field_email. */
	TextField textField_email;
	
	/** The skade nr send. */
	int skadeNrSend;
	
	/** The test. */
	TextArea test;
	
	/**
	 * Class that contains information about damage reports from the database
	 * 
	 * 
	 * Contain skadeId, koieNr, skadeDato, skade, brukerId, repDato, adminId.
	 *
	 * @author Eirik
	 */
	public static class Record {

		/** The skade id. */
		private final SimpleIntegerProperty skadeId;
		
		/** The koie nr. */
		private final SimpleIntegerProperty koieNr;
		
		/** The skade dato. */
		private final SimpleStringProperty skadeDato;
		
		/** The skade. */
		private final SimpleStringProperty skade;
		
		/** The bruker id. */
		private final SimpleIntegerProperty brukerId;
		
		/** The rep dato. */
		private final SimpleStringProperty repDato;
		
		/** The admin id. */
		private final SimpleIntegerProperty adminId;

		/**
		 * Constructor  for class Record.
		 *
		 * @param skadeId the skade id
		 * @param koieNr the koie nr
		 * @param skadeDato the skade dato
		 * @param skade the skade
		 * @param brukerId the bruker id
		 * @param repDato the rep dato
		 * @param adminId the admin id
		 */
		private Record(final int skadeId, final int koieNr, final String skadeDato, final String skade, final int brukerId, final String repDato, final int adminId){ //,final boolean booly) {
			
			this.skadeId = new SimpleIntegerProperty(skadeId);
			this.koieNr = new SimpleIntegerProperty(koieNr);
			this.skadeDato = new SimpleStringProperty(skadeDato);
			this.skade = new SimpleStringProperty(skade);
			this.brukerId = new SimpleIntegerProperty(brukerId);
			this.repDato = new SimpleStringProperty(repDato);
			this.adminId = new SimpleIntegerProperty(adminId);
		}
		
		/**
		 * Gets the skade dato.
		 *
		 * @return the skade dato
		 */
		public String getSkadeDato(){
			return this.skadeDato.get();
		}
		
		/**
		 * Sets the skade dato.
		 *
		 * @param date the new skade dato
		 */
		public void setSkadeDato(final String date){
			this.skadeDato.set(date);
		}
		
		/**
		 * Gets the skade.
		 *
		 * @return the skade
		 */
		public String getSkade(){
			return this.skade.get();
		}
		
		/**
		 * Sets the skade.
		 *
		 * @param skade the new skade
		 */
		public void setSkade(final String skade){
			this.skade.set(skade);
		}
		
		/**
		 * Gets the rep dato.
		 *
		 * @return the rep dato
		 */
		public String getRepDato(){
			return this.repDato.get();
		}
		
		/**
		 * Sets the rep dato.
		 *
		 * @param date the new rep dato
		 */
		public void setRepDato(final String date){
			this.repDato.set(date);
		}
		
		/**
		 * Gets the skade id.
		 *
		 * @return the skade id
		 */
		public int getSkadeId(){
			return this.skadeId.get();
		}
		
		/**
		 * Sets the skade id.
		 *
		 * @param skadeId the new skade id
		 */
		public void setSkadeId(final int skadeId){
			this.skadeId.set(skadeId);
		}
		
		/**
		 * Gets the koie nr.
		 *
		 * @return the koie nr
		 */
		public int getKoieNr(){
			return this.koieNr.get();
		}
		
		/**
		 * Sets the koie nr.
		 *
		 * @param koieNr the new koie nr
		 */
		public void setKoieNr(final int koieNr){
			this.koieNr.set(koieNr);
		}
		
		/**
		 * Gets the bruker id.
		 *
		 * @return the bruker id
		 */
		public int getBrukerId(){
			return this.brukerId.get();
		}
		
		/**
		 * Sets the bruker id.
		 *
		 * @param brukerId the new bruker id
		 */
		public void setBrukerId(final int brukerId){
			this.brukerId.set(brukerId);
		}
		
		/**
		 * Gets the admin id.
		 *
		 * @return the admin id
		 */
		public int getAdminId(){
			return this.adminId.get();
		}
		
		/**
		 * Sets the admin id.
		 *
		 * @param skadeId the new admin id
		 */
		public void setAdminId(final int skadeId){
			this.adminId.set(skadeId);
		}
	}
	
	/** The table view. */
	private final TableView<Record> tableView = new TableView<>();
	
	/** The rapport list. */
	private final ObservableList<Record> rapportList = FXCollections.observableArrayList();

	/**
	 * Function that registers the date and who repaired the damage.
	 *
	 * @param skadeId the skade id
	 * @param dato the dato
	 * @param adminID the admin id
	 * @throws SQLException the SQL exception
	 */
	private void regReperasjon(int skadeId, String dato, int adminID) throws SQLException{
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
		
		try{
			PreparedStatement statement = con.prepareStatement ("UPDATE skaderapport SET reperasjonsdato = "+"'"+dato+"'"+","+"adminID = "+adminID+" WHERE skadeID = "+skadeId);
			statement.executeUpdate();
			hentRapport();
			con.close();
		} catch(Exception e){
	
		}
		
	}
	
	/**
	 * Function that creates a list of damage reports using the Record class.
	 *
	 * @throws SQLException the SQL exception
	 */
	private void hentRapport() throws SQLException {
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
		rapportList.clear();
		
		try {
			PreparedStatement statement = con.prepareStatement ("select * from skaderapport");
			ResultSet results = statement.executeQuery();
			
			for(int i = 0; i < 50; i++){
				results.next();
				rapportList.add(new Record(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),results.getInt(5),results.getString(6),results.getInt(7))); //,results.getBoolean(8)));
			}
			con.close();
		} catch (Exception e) {	
			System.out.println(e);
		}
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override 
	public void start(final Stage primaryStage) throws Exception {
		
	}

	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @param bruker the bruker
	 * @throws SQLException the SQL exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(final Stage primaryStage, Bruker bruker) throws SQLException {
		final Scene scene = new Scene(new Group());
		scene.setFill(Color.LIGHTGREY);
		temp = bruker;
		primaryStage.setTitle("Rapport");
		primaryStage.setWidth(1000);
		primaryStage.setHeight(1000);		

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

		TableColumn colRepDato = new TableColumn("Reperasjonsdato");
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
		dato.setPromptText("Reperasjonsdato");
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
		Button cancel = new Button("Tilbake");
		
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

		Label turleder = new Label("Turleder");
		Label fornavn = new Label("Fornavn	");
		Label etternavn = new Label("Etternavn	");
		Label mobil = new Label("Mobilnr	");
		Label email = new Label("Email		");
		textField_fornavn = new TextField();
		textField_etternavn = new TextField();
		textField_mobilnr = new TextField();
		textField_email = new TextField();
		textField_fornavn.editableProperty().set(false);
		textField_etternavn.editableProperty().set(false);
		textField_mobilnr.editableProperty().set(false);
		textField_email.editableProperty().set(false);
		HBox hBox_fornavn = new HBox();
		hBox_fornavn.setSpacing(10);
		hBox_fornavn.getChildren().addAll(fornavn, textField_fornavn);
		HBox hBox_etternavn = new HBox();
		hBox_etternavn.setSpacing(10);
		hBox_etternavn.getChildren().addAll(etternavn, textField_etternavn);
		HBox hBox_mobil = new HBox();
		hBox_mobil.setSpacing(10);
		hBox_mobil.getChildren().addAll(mobil, textField_mobilnr);
		//textField_ved = new TextField();
		//textField_ved.editableProperty().set(false);
		HBox hBox_email = new HBox();
		hBox_email.setSpacing(10);
		hBox_email.getChildren().addAll(email,textField_email);
		/*Label ved = new Label("Nok ved");
		HBox hBox_ved = new HBox();
		hBox_ved.setSpacing(10);
		hBox_ved.getChildren().addAll(ved,textField_ved);*/
		
		
		VBox vBox_person = new VBox();
		vBox_person.setSpacing(10);
		vBox_person.getChildren().addAll(turleder,hBox_fornavn,hBox_etternavn,hBox_mobil,hBox_email);//, hBox_ved);
		test = new TextArea();
		test.editableProperty().set(false);
		Label label_beskrivelse = new Label("Skadebeskrivelse");
		HBox hBox_besk = new HBox();
		hBox_besk.setSpacing(10);
		hBox_besk.getChildren().addAll(label_beskrivelse, test);
		HBox hBox_alt = new HBox();
		hBox_alt.setSpacing(10);
		hBox_alt.getChildren().addAll(vBox_person,hBox_besk);
		
		HBox hBox_registrering = new HBox();
		hBox_registrering.setSpacing(10);
		hBox_registrering.getChildren().addAll(hBox_dato, regButt);
		
		
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(hBox_alt, tableView, hBox_registrering, cancel);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		primaryStage.setScene(scene);
		primaryStage.show();
		}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	
	/**
	 * The Class MyIntegerTableCell.
	 */
	class MyIntegerTableCell extends TableCell<Record, Integer> {

		/* (non-Javadoc)
		 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
		 */
		@Override
		public void updateItem(final Integer item, final boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : getString());
			setGraphic(null);
		}

		/**
		 * Gets the string.
		 *
		 * @return the string
		 */
		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}


	/**
	 * The Class MyStringTableCell.
	 */
	class MyStringTableCell extends TableCell<Record, String> {

		/* (non-Javadoc)
		 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
		 */
		@Override
		public void updateItem(final String item, final boolean empty) {
			super.updateItem(item, empty);
			setText(empty ? null : getString());
			setGraphic(null);
		}

		/**
		 * Gets the string.
		 *
		 * @return the string
		 */
		private String getString() {
			return getItem() == null ? "" : getItem().toString();
		}
	}

	/**
	 * The Class MyEventHandler.
	 */
	class MyEventHandler implements EventHandler<MouseEvent>{

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@SuppressWarnings("rawtypes")
		@Override
		public void handle(final MouseEvent t) {
			final TableCell c = (TableCell) t.getSource();
			final int index = c.getIndex();

			try {
				item = rapportList.get(index);
				
				final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
				PreparedStatement statement = con.prepareStatement ("SELECT * from bruker WHERE brukerID = "+item.getBrukerId());
				ResultSet results = statement.executeQuery();
				results.next();
				skadeNrSend = item.getSkadeId();
				test.setText(item.getSkade());
				textField_fornavn.setText(results.getString(2));
				textField_etternavn.setText(results.getString(3));
				textField_email.setText(results.getString(4));
				StringBuilder sb = new StringBuilder();
				sb.append("");
				sb.append(results.getInt(5));
				String mob = sb.toString();
				textField_mobilnr.setText(mob);
				con.close();
				
			} catch (IndexOutOfBoundsException exception) {
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}