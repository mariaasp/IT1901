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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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


public class koieMatrise extends Application {
	
	Record item;
	Bruker temp;
	TextField textField_fornavn;
	TextField textField_etternavn;
	TextField textField_mobilnr;
	TextField textField_email;
	TextArea test;
	TextField  textField_ved;
	ChoiceBox<String> koier;
	
	
	public static class Record {

		private final SimpleIntegerProperty koienr;	
		private final SimpleStringProperty koieNavn;
		private final SimpleIntegerProperty sengeplasser;
		private final SimpleIntegerProperty bordplasser;
		private final SimpleIntegerProperty aar;
		private final SimpleStringProperty terreng;
		private final SimpleIntegerProperty sykkel;
		private final SimpleIntegerProperty topptur;
		private final SimpleIntegerProperty jakt;
		private final SimpleIntegerProperty fiske;
		private final SimpleIntegerProperty gitar;
		private final SimpleIntegerProperty	vaffeljern;
		private final SimpleStringProperty spesialiteter;
		private final SimpleStringProperty vedStatus;
	

		private Record(final int koienr, final String koieNavn, final int sengeplasser, final int bordplasser, final int aar, final String terreng, final int sykkel, final int topptur, final int jakt, final int fiske, final int gitar, final int vaffeljern, final String spesialiteter, final boolean booly) {
			
			this.koienr = new SimpleIntegerProperty(koienr);
			this.koieNavn = new SimpleStringProperty(koieNavn);
			this.sengeplasser = new SimpleIntegerProperty(sengeplasser);
			this.bordplasser= new SimpleIntegerProperty(bordplasser);
			this.aar = new SimpleIntegerProperty(aar);
			this.terreng = new SimpleStringProperty(terreng);
			this.sykkel = new SimpleIntegerProperty(sykkel);
			this.topptur = new SimpleIntegerProperty(topptur);
			this.jakt = new SimpleIntegerProperty(jakt);
			this.fiske = new SimpleIntegerProperty(fiske);
			this.gitar = new SimpleIntegerProperty(gitar);
			this.vaffeljern = new SimpleIntegerProperty(vaffeljern);
			this.spesialiteter = new SimpleStringProperty(spesialiteter);
			if(booly){
				this.vedStatus = new SimpleStringProperty("Ja");
			}
			else{
				this.vedStatus = new SimpleStringProperty("Nei");
			}
			
			
			
		}
		public String getVedStatus(){
			return this.vedStatus.get();
		}
		public void setVedStatus(final boolean booly){
			if(booly){
				this.vedStatus.set("Ja");
			}
			else{
				this.vedStatus.set("Nei");
			}
			
		}
		public String getKoieNavn(){
			return this.koieNavn.get();
		}
		public void setKoieNavn(final String koieNavn){
			this.koieNavn.set(koieNavn);
		}
		public String getTerreng(){
			return this.terreng.get();
		}
		public void setTerreng(final String terreng){
			this.terreng.set(terreng);
		}
		public String getSpesialiteter(){
			return this.spesialiteter.get();
		}
		public void setSpesialiteter(final String spesialiteter){
			this.spesialiteter.set(spesialiteter);
		}
		public int getKoienr(){
			return this.koienr.get();
		}
		public void setKoieNr(final int koieNr){
			this.koienr.set(koieNr);
		}
		public int getSengeplasser(){
			return this.sengeplasser.get();
		}
		public void setSengeplasser(final int sengeplasser){
			this.sengeplasser.set(sengeplasser);
		}
		public int getBordplasser(){
			return this.bordplasser.get();
		}
		public void setBordplasser(final int bordplasser){
			this.bordplasser.set(bordplasser);
		}
		public int getAar(){
			return this.aar.get();
		}
		public void setAar(final int aar){
			this.aar.set(aar);
		}
		public int getSykkel(){
			return this.sykkel.get();
		}
		public void setSykkel(final int sykkel){
			this.sykkel.set(sykkel);
		}
		public int getTopptur(){
			return this.topptur.get();
		}
		public void setTopptur(final int topptur){
			this.topptur.set(topptur);
		}
		public int getJakt(){
			return this.jakt.get();
		}
		public void setJakt(final int jakt){
			this.jakt.set(jakt);
		}
		public int getFiske(){
			return this.fiske.get();
		}
		public void setFiske(final int jakt){
			this.fiske.set(jakt);
		}
		public int getGitar(){
			return this.gitar.get();
		}
		public void setGitar(final int gitar){
			this.gitar.set(gitar);
		}
		public int getVaffeljern(){
			return this.vaffeljern.get();
		}
		public void setVaffeljern(final int vaffeljern){
			this.vaffeljern.set(vaffeljern);
		}
	}
	private final TableView<Record> tableView = new TableView<>();
	private final ObservableList<Record> rapportList = FXCollections.observableArrayList();

	private void hentRapport() throws SQLException {
		final Connection con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
		rapportList.clear();
		try {
			PreparedStatement statement = con.prepareStatement ("select * from koie");
			ResultSet results = statement.executeQuery();
			if(koier.getValue().equals("Se alle")){
				for(int i = 0; i < 23; i++){
					results.next();
					rapportList.add(new Record(i+1, results.getString(2), results.getInt(3), results.getInt(4), results.getInt(5), results.getString(6), results.getInt(7), results.getInt(8), results.getInt(9), results.getInt(10), results.getInt(11), results.getInt(12),results.getString(13), results.getBoolean(14)));
				}
			}
			else{
				for(int i = 0; i < 23; i++){
					results.next();
					if (koier.getValue().equals(results.getString(2))){
						rapportList.add(new Record(i+1, results.getString(2), results.getInt(3), results.getInt(4), results.getInt(5), results.getString(6), results.getInt(7), results.getInt(8), results.getInt(9), results.getInt(10), results.getInt(11), results.getInt(12),results.getString(13), results.getBoolean(14)));
					}
				}
			}
			con.close();
		} catch (Exception e) {	
			System.out.println(e);
		}
	}

	@Override 
	public void start(final Stage primaryStage) throws Exception {
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(final Stage primaryStage, Bruker bruker) throws SQLException {
		final Scene scene = new Scene(new Group());
		temp = bruker;
		primaryStage.setTitle("Koiematrise");
		primaryStage.setWidth(1200);
		primaryStage.setHeight(1000);
		primaryStage.resizableProperty().set(false);

		koier = new ChoiceBox();
		koier.getItems().addAll(
				"Flaakoia",
				"Fosenkoia",
				"Heinfjordstua",
				"Hognabu",
				"Holmsaakoia",
				"Holvassgamma",
				"Iglbu",
				"Kamtjoennkoia",
				"Kraaklikaaten",
				"Kvernmovollen",
				"Kaasen",
				"Lynhoegen",
				"Mortenskaaten",
				"Nickokoia",
				"Rindalsloea",
				"Selbukaaten",
				"Sonvasskoia",
				"Stabburet",
				"Stakkslettbua",
				"Telin",
				"Taagaabu",
				"Vekvessaetra",
				"Oevensenget",
				"Se alle"
		);
		koier.setValue("Se alle");
			
		tableView.setEditable(true);

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

		
		TableColumn colKoieNr= new TableColumn("Koienummer");
		colKoieNr.setCellValueFactory(new PropertyValueFactory<Record, String>("koienr"));
		colKoieNr.setCellFactory(integerCellFactory);

		TableColumn colKoieNavn = new TableColumn("Koienavn");
		colKoieNavn.setCellValueFactory(new PropertyValueFactory<Record, String>("koieNavn"));
		colKoieNavn.setCellFactory(stringCellFactory);

		TableColumn colSengeplasser = new TableColumn("Sengeplasser");
		colSengeplasser.setCellValueFactory(new PropertyValueFactory<Record, String>("sengeplasser"));
		colSengeplasser.setCellFactory(integerCellFactory);
		
		TableColumn colBordplasser = new TableColumn("Bordplasser");
		colBordplasser.setCellValueFactory(new PropertyValueFactory<Record, String>("bordplasser"));
		colBordplasser.setCellFactory(integerCellFactory);
		
		TableColumn colAar = new TableColumn("Bygge�r");
		colAar.setCellValueFactory(new PropertyValueFactory<Record, String>("aar"));
		colAar.setCellFactory(integerCellFactory);

		TableColumn colTerreng = new TableColumn("Terreng");
		colTerreng.setCellValueFactory(new PropertyValueFactory<Record, String>("terreng"));
		colTerreng.setCellFactory(stringCellFactory);
		
		TableColumn colSykkel = new TableColumn("Sykle");
		colSykkel.setCellValueFactory(new PropertyValueFactory<Record, String>("sykkel"));
		colSykkel.setCellFactory(integerCellFactory);
		
		TableColumn colTopptur = new TableColumn("Topptur");
		colTopptur.setCellValueFactory(new PropertyValueFactory<Record, String>("topptur"));
		colTopptur.setCellFactory(integerCellFactory);
		
		TableColumn colJakt = new TableColumn("Jakt");
		colJakt.setCellValueFactory(new PropertyValueFactory<Record, String>("jakt"));
		colJakt.setCellFactory(integerCellFactory);
		
		TableColumn colFiske= new TableColumn("Fiske");
		colFiske.setCellValueFactory(new PropertyValueFactory<Record, String>("fiske"));
		colFiske.setCellFactory(integerCellFactory);
		
		TableColumn colGitar= new TableColumn("Gitar");
		colGitar.setCellValueFactory(new PropertyValueFactory<Record, String>("gitar"));
		colGitar.setCellFactory(integerCellFactory);
		
		TableColumn colVaffeljern= new TableColumn("Vaffeljern");
		colVaffeljern.setCellValueFactory(new PropertyValueFactory<Record, String>("vaffeljern"));
		colVaffeljern.setCellFactory(integerCellFactory);
		
		TableColumn colSpesialiteter = new TableColumn("Spesialiteter");
		colSpesialiteter.setCellValueFactory(new PropertyValueFactory<Record, String>("spesialiteter"));
		colSpesialiteter.setCellFactory(stringCellFactory);
		
		TableColumn colVedStatus = new TableColumn("Nok ved:");
		colVedStatus.setCellValueFactory(new PropertyValueFactory<Record, String>("vedStatus"));
		colVedStatus.setCellFactory(stringCellFactory);
		
		hentRapport();

		tableView.setItems(rapportList);
		tableView.getColumns().addAll(colKoieNr, colKoieNavn, colSengeplasser, colBordplasser, colAar, colTerreng, colSykkel, colTopptur, colJakt, colFiske, colGitar, colVaffeljern, colSpesialiteter, colVedStatus);

		
		final VBox vbox = new VBox();
				
		Button refButt = new Button("Oppdater");
		refButt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				try {
					hentRapport();
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
		Label email = new Label("Email");
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
		textField_ved = new TextField();
		textField_ved.editableProperty().set(false);
		HBox hBox_email = new HBox();
		hBox_email.setSpacing(10);
		hBox_email.getChildren().addAll(email,textField_email);
		Label ved = new Label("Nok ved");
		HBox hBox_ved = new HBox();
		hBox_ved.setSpacing(10);
		hBox_ved.getChildren().addAll(ved,textField_ved);
		
		
		VBox vBox_person = new VBox();
		vBox_person.setSpacing(10);
		vBox_person.getChildren().addAll(turleder,hBox_fornavn,hBox_etternavn,hBox_mobil,hBox_email, hBox_ved);
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
		hBox_registrering.getChildren().addAll(refButt, merVed,sjekkVed,regVed);
		
		
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(hBox_alt, tableView, hBox_registrering, cancel, koier);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		primaryStage.setScene(scene);
		primaryStage.show();
		
		regVed.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				Connection con;
				try {
					con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
					PreparedStatement statement = con.prepareStatement ("UPDATE koie SET vedstatus = " + sjekkVed.isSelected() +" WHERE koienr = "+item.getKoienr());
					statement.executeUpdate();
					hentRapport();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
				}
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

	class MyEventHandler implements EventHandler<MouseEvent>{

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
				textField_ved.setText(item.getVed());
				con.close();
				
			} catch (IndexOutOfBoundsException exception) {
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}