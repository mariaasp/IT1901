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
import javafx.scene.control.CheckBox;
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
import javafx.stage.Stage;
import javafx.util.Callback;


public class AdmRap extends Application {
	
	Bruker temp;
	TextField textField_fornavn;
	TextField textField_etternavn;
	TextField textField_mobilnr;
	int skadeNrSend;
	TextArea test;
	TextField  textField_ved;
	
	
	public static class Record {

		private final SimpleIntegerProperty skadeId;
		private final SimpleIntegerProperty koieNr;
		private final SimpleStringProperty skadeDato;
		private final SimpleStringProperty skade;
		private final SimpleIntegerProperty brukerId;
		private final SimpleStringProperty repDato;
		private final SimpleIntegerProperty adminId;
		private final SimpleStringProperty ved;

		private Record(final int skadeId, final int koieNr, final String skadeDato, final String skade, final int brukerId, final String repDato, final int adminId,final boolean booly) {
			
			this.skadeId = new SimpleIntegerProperty(skadeId);
			this.koieNr = new SimpleIntegerProperty(koieNr);
			this.skadeDato = new SimpleStringProperty(skadeDato);
			this.skade = new SimpleStringProperty(skade);
			this.brukerId = new SimpleIntegerProperty(brukerId);
			this.repDato = new SimpleStringProperty(repDato);
			this.adminId = new SimpleIntegerProperty(adminId);
			if(booly){
				this.ved = new SimpleStringProperty("Ja");
			}
			else{
				this.ved = new SimpleStringProperty("Nei");
			}
			
		}
		public String getVed(){
			return this.ved.get();
		}
		public void setVed(final boolean bool){
			if(bool){
				this.ved.set("Ja");
			}
			else{
				this.ved.set("Nei");
			}
			
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
		public void setSkade(final String skade){
			this.skade.set(skade);
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
			PreparedStatement statement = con.prepareStatement ("UPDATE skaderapport SET reperasjonsdato = "+"'"+dato+"'"+","+"adminID = "+adminID+" WHERE skadeID = "+skadeId);
			//statement.setString(1,"'"+dato+"'");
			//statement.setInt(2,adminID);
			//statement.setInt(3,skadeId);
			statement.executeUpdate();
			hentRapport();
			con.close();
		} catch(Exception e){
	
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
				rapportList.add(new Record(results.getInt(1),results.getInt(2),results.getString(3),results.getString(4),results.getInt(5),results.getString(6),results.getInt(7),true));
			}
			con.close();
		} catch (Exception e) {	

		}
	}

	@Override 
	public void start(final Stage primaryStage) throws Exception {
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(final Stage primaryStage, Bruker bruker) throws SQLException {
		final Scene scene = new Scene(new Group());
		temp = bruker;
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
		
		TableColumn colVed = new TableColumn("Vedstatus");
		colVed.setCellValueFactory(new PropertyValueFactory<Record, String>("ved"));
		colVed.setCellFactory(stringCellFactory);
		
		tableView.setItems(rapportList);
		tableView.getColumns().addAll(colId, colKoieNr, colSkadeDato, colSkade, colBrukerId, colRepDato, colAdminId, colVed);

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
		Label fornavn = new Label("Fornavn");
		Label etternavn = new Label("Etternavn");
		Label mobil = new Label("Mobilnr");
		textField_fornavn = new TextField();
		textField_etternavn = new TextField();
		textField_mobilnr = new TextField();
		textField_fornavn.editableProperty().set(false);
		textField_etternavn.editableProperty().set(false);
		textField_mobilnr.editableProperty().set(false);
		HBox hBox_fornavn = new HBox();
		hBox_fornavn.setSpacing(10);
		hBox_fornavn.getChildren().addAll(fornavn, textField_fornavn);
		HBox hBox_etternavn = new HBox();
		hBox_etternavn.setSpacing(10);
		hBox_etternavn.getChildren().addAll(etternavn, textField_etternavn);
		HBox hBox_mobil = new HBox();
		hBox_mobil.setSpacing(10);
		hBox_mobil.getChildren().addAll(mobil, textField_mobilnr);
		textField_ved = new TextField();
		textField_ved.editableProperty().set(false);
		Label ved = new Label("Nok ved");
		HBox hBox_ved = new HBox();
		hBox_ved.setSpacing(10);
		hBox_ved.getChildren().addAll(ved,textField_ved);
		
		VBox vBox_person = new VBox();
		vBox_person.setSpacing(10);
		vBox_person.getChildren().addAll(turleder,hBox_fornavn,hBox_etternavn,hBox_mobil,hBox_ved);
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
		Label merVed = new  Label("Mer ved lagt til");
		CheckBox sjekkVed = new CheckBox();
		Button regVed = new Button("Registrer ved");
		hBox_registrering.getChildren().addAll(hBox_dato, regButt, merVed,sjekkVed,regVed);
		
		
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(hBox_alt, tableView, hBox_registrering, cancel);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		primaryStage.setScene(scene);
		primaryStage.show();
		
		regVed.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				System.out.println(sjekkVed.isSelected());
			}
		});}

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
				System.out.println("Vedstatus = " + item.getVed());
				test.setText(item.getSkade());
				textField_fornavn.setText(temp.getFornavn());
				textField_etternavn.setText(temp.getEtternavn());
				StringBuilder sb = new StringBuilder();
				sb.append("");
				sb.append(temp.getMobilnr());
				String mob = sb.toString();
				textField_mobilnr.setText(mob);
				textField_ved.setText(item.getVed());
				
			} catch (IndexOutOfBoundsException exception) {
			}

		}
	}
}