/*
 * 
 */
package koier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
 * This is a class that contains information about cabins. 
 * 
 * It contains koienr, koieNavn, sengeplasser, bordplasser, aar, terreng, sykkel, topptur, jakt, fiske, g itar, vaffeljern, spesialiteter and vedstatus
 * @author Eirik
 *
 */
public class koieMatrise extends Application {
	
	/** The item. */
	Record item;
	
	/** The temp. */
	Bruker temp;
	
	/** The text field_ koie navn. */
	TextField textField_KoieNavn;
	
	/** The sengeplasser. */
	TextField sengeplasser;
	
	/** The bordplasser. */
	TextField bordplasser;
	
	/** The gitar. */
	TextField gitar;
	
	/** The vaffeljern. */
	TextField vaffeljern;
	
	/** The spesialiteter. */
	TextField spesialiteter;
	
	/** The text field_ved. */
	TextField  textField_ved;
	
	/** The sjekk ved. */
	CheckBox sjekkVed = new CheckBox();
	
	/** The koier. */
	ChoiceBox<String> koier;
	
	
	/**
	 * The Class Record.
	 */
	public static class Record {

		/** The koienr. */
		private final SimpleIntegerProperty koienr;	
		
		/** The koie navn. */
		private final SimpleStringProperty koieNavn;
		
		/** The sengeplasser. */
		private final SimpleIntegerProperty sengeplasser;
		
		/** The bordplasser. */
		private final SimpleIntegerProperty bordplasser;
		
		/** The aar. */
		private final SimpleIntegerProperty aar;
		
		/** The terreng. */
		private final SimpleStringProperty terreng;
		
		/** The sykkel. */
		private final SimpleIntegerProperty sykkel;
		
		/** The topptur. */
		private final SimpleIntegerProperty topptur;
		
		/** The jakt. */
		private final SimpleIntegerProperty jakt;
		
		/** The fiske. */
		private final SimpleIntegerProperty fiske;
		
		/** The gitar. */
		private final SimpleIntegerProperty gitar;
		
		/** The vaffeljern. */
		private final SimpleIntegerProperty	vaffeljern;
		
		/** The spesialiteter. */
		private final SimpleStringProperty spesialiteter;
		
		/** The ved status. */
		private final SimpleStringProperty vedStatus;
	

		/**
		 * Constructor for class Record.
		 *
		 * @param koienr the koienr
		 * @param koieNavn the koie navn
		 * @param sengeplasser the sengeplasser
		 * @param bordplasser the bordplasser
		 * @param aar the aar
		 * @param terreng the terreng
		 * @param sykkel the sykkel
		 * @param topptur the topptur
		 * @param jakt the jakt
		 * @param fiske the fiske
		 * @param gitar the gitar
		 * @param vaffeljern the vaffeljern
		 * @param spesialiteter the spesialiteter
		 * @param booly the booly
		 */
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
		
		/**
		 * Gets the ved status.
		 *
		 * @return the ved status
		 */
		public String getVedStatus(){
			return this.vedStatus.get();
		}
		
		/**
		 * Sets the ved status.
		 *
		 * @param booly the new ved status
		 */
		public void setVedStatus(final boolean booly){
			if(booly){
				this.vedStatus.set("Ja");
			}
			else{
				this.vedStatus.set("Nei");
			}
			
		}
		
		/**
		 * Gets the koie navn.
		 *
		 * @return the koie navn
		 */
		public String getKoieNavn(){
			return this.koieNavn.get();
		}
		
		/**
		 * Sets the koie navn.
		 *
		 * @param koieNavn the new koie navn
		 */
		public void setKoieNavn(final String koieNavn){
			this.koieNavn.set(koieNavn);
		}
		
		/**
		 * Gets the terreng.
		 *
		 * @return the terreng
		 */
		public String getTerreng(){
			return this.terreng.get();
		}
		
		/**
		 * Sets the terreng.
		 *
		 * @param terreng the new terreng
		 */
		public void setTerreng(final String terreng){
			this.terreng.set(terreng);
		}
		
		/**
		 * Gets the spesialiteter.
		 *
		 * @return the spesialiteter
		 */
		public String getSpesialiteter(){
			return this.spesialiteter.get();
		}
		
		/**
		 * Sets the spesialiteter.
		 *
		 * @param spesialiteter the new spesialiteter
		 */
		public void setSpesialiteter(final String spesialiteter){
			this.spesialiteter.set(spesialiteter);
		}
		
		/**
		 * Gets the koienr.
		 *
		 * @return the koienr
		 */
		public int getKoienr(){
			return this.koienr.get();
		}
		
		/**
		 * Sets the koie nr.
		 *
		 * @param koieNr the new koie nr
		 */
		public void setKoieNr(final int koieNr){
			this.koienr.set(koieNr);
		}
		
		/**
		 * Gets the sengeplasser.
		 *
		 * @return the sengeplasser
		 */
		public int getSengeplasser(){
			return this.sengeplasser.get();
		}
		
		/**
		 * Sets the sengeplasser.
		 *
		 * @param sengeplasser the new sengeplasser
		 */
		public void setSengeplasser(final int sengeplasser){
			this.sengeplasser.set(sengeplasser);
		}
		
		/**
		 * Gets the bordplasser.
		 *
		 * @return the bordplasser
		 */
		public int getBordplasser(){
			return this.bordplasser.get();
		}
		
		/**
		 * Sets the bordplasser.
		 *
		 * @param bordplasser the new bordplasser
		 */
		public void setBordplasser(final int bordplasser){
			this.bordplasser.set(bordplasser);
		}
		
		/**
		 * Gets the aar.
		 *
		 * @return the aar
		 */
		public int getAar(){
			return this.aar.get();
		}
		
		/**
		 * Sets the aar.
		 *
		 * @param aar the new aar
		 */
		public void setAar(final int aar){
			this.aar.set(aar);
		}
		
		/**
		 * Gets the sykkel.
		 *
		 * @return the sykkel
		 */
		public int getSykkel(){
			return this.sykkel.get();
		}
		
		/**
		 * Sets the sykkel.
		 *
		 * @param sykkel the new sykkel
		 */
		public void setSykkel(final int sykkel){
			this.sykkel.set(sykkel);
		}
		
		/**
		 * Gets the topptur.
		 *
		 * @return the topptur
		 */
		public int getTopptur(){
			return this.topptur.get();
		}
		
		/**
		 * Sets the topptur.
		 *
		 * @param topptur the new topptur
		 */
		public void setTopptur(final int topptur){
			this.topptur.set(topptur);
		}
		
		/**
		 * Gets the jakt.
		 *
		 * @return the jakt
		 */
		public int getJakt(){
			return this.jakt.get();
		}
		
		/**
		 * Sets the jakt.
		 *
		 * @param jakt the new jakt
		 */
		public void setJakt(final int jakt){
			this.jakt.set(jakt);
		}
		
		/**
		 * Gets the fiske.
		 *
		 * @return the fiske
		 */
		public int getFiske(){
			return this.fiske.get();
		}
		
		/**
		 * Sets the fiske.
		 *
		 * @param jakt the new fiske
		 */
		public void setFiske(final int jakt){
			this.fiske.set(jakt);
		}
		
		/**
		 * Gets the gitar.
		 *
		 * @return the gitar
		 */
		public int getGitar(){
			return this.gitar.get();
		}
		
		/**
		 * Sets the gitar.
		 *
		 * @param gitar the new gitar
		 */
		public void setGitar(final int gitar){
			this.gitar.set(gitar);
		}
		
		/**
		 * Gets the vaffeljern.
		 *
		 * @return the vaffeljern
		 */
		public int getVaffeljern(){
			return this.vaffeljern.get();
		}
		
		/**
		 * Sets the vaffeljern.
		 *
		 * @param vaffeljern the new vaffeljern
		 */
		public void setVaffeljern(final int vaffeljern){
			this.vaffeljern.set(vaffeljern);
		}
	}
	
	/** The table view. */
	private final TableView<Record> tableView = new TableView<>();
	
	/** The rapport list. */
	private final ObservableList<Record> rapportList = FXCollections.observableArrayList();

	/**
	 * A function that fetches information about cabins from the database .
	 *
	 * @throws SQLException the SQL exception
	 */
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
		primaryStage.setTitle("Koiematrise");
		primaryStage.setWidth(1200);
		primaryStage.setHeight(800);
		//primaryStage.resizableProperty().set(false);

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
		
		TableColumn colAar = new TableColumn("Byggeår");
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
		
		Button regVed = new Button("Registrer ved");
		HBox hBox_registrering = new HBox();
		Button regEnd = new Button("Registrer endringer");
		HBox halve1 = new HBox();
		HBox halve2 = new HBox();
		halve1.setSpacing(10);
		halve2.setSpacing(10);
		hBox_registrering.setSpacing(10);
		
		Label koieNavn = new Label("Koienavn		");
		koieNavn.alignmentProperty().set(Pos.CENTER);
		textField_KoieNavn = new TextField();
		//textField_KoieNavn.alignmentProperty().set(Pos.CENTER);
		Label LabSengeplasser = new Label("Sengeplasser	");
		LabSengeplasser.alignmentProperty().set(Pos.CENTER);
		sengeplasser = new TextField();
		//sengeplasser.alignmentProperty().set(Pos.CENTER);
		Label LabBordplasser = new Label("Bordplasser	");
		LabBordplasser.alignmentProperty().set(Pos.CENTER);
		bordplasser = new TextField();
		//bordplasser.alignmentProperty().set(Pos.CENTER);
		Label LabGitar = new Label("Gitar			");
		LabGitar.alignmentProperty().set(Pos.CENTER);
		gitar = new TextField();
		//gitar.alignmentProperty().set(Pos.CENTER);
		gitar.setLayoutX(100);
		Label LabVaffeljern = new Label("Vaffeljern		");
		LabVaffeljern.alignmentProperty().set(Pos.CENTER);
		vaffeljern = new TextField();
		//vaffeljern.alignmentProperty().set(Pos.CENTER);
		Label LabSpes = new Label("Spesialiteter	");
		LabSpes.alignmentProperty().set(Pos.CENTER);
		spesialiteter = new TextField();
		//spesialiteter.alignmentProperty().set(Pos.CENTER);
		halve1.getChildren().addAll(koieNavn, textField_KoieNavn, LabSengeplasser, sengeplasser, LabBordplasser, bordplasser);
		halve2.getChildren().addAll(LabGitar, gitar, LabVaffeljern, vaffeljern, LabSpes, spesialiteter);
		textField_KoieNavn.editableProperty().set(false);
		sengeplasser.editableProperty().set(false);
		bordplasser.editableProperty().set(false);
		gitar.editableProperty().set(false);
		vaffeljern.editableProperty().set(false);
		spesialiteter.editableProperty().set(false);
		if(temp.permission == 1){
			halve2.getChildren().add(regEnd);
			textField_KoieNavn.editableProperty().set(true);
			sengeplasser.editableProperty().set(true);
			bordplasser.editableProperty().set(true);
			gitar.editableProperty().set(true);
			vaffeljern.editableProperty().set(true);
			spesialiteter.editableProperty().set(true);
			Label merVed = new  Label("Mer ved lagt til");
			sjekkVed = new CheckBox();
			hBox_registrering.getChildren().addAll(merVed,sjekkVed,regVed);
			
		}
		Label feilSenger = new Label("");
		Label feilBord= new Label("");
		Label feilGitar = new Label("");
		Label feilVaffeljern = new Label("");
		
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(tableView, halve1, halve2 ,  hBox_registrering, koier, refButt, cancel, feilSenger, feilBord, feilGitar, feilVaffeljern);
		
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
		});
	regEnd.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event){
			Connection con;
			Boolean altOk = true;
			String koienavn = textField_KoieNavn.getText();
			String spesial = spesialiteter.getText();
			int senger = 0;
			int bord = 0;
			int erGitar = 0;
			int erVaffeljern = 0;
			try{
				 senger = Integer.parseInt(sengeplasser.getText());

			} catch(Exception e){
				altOk = false;
				feilSenger.setText("Ugyldig sengeplasser");
			}
			try{
				 bord = Integer.parseInt(bordplasser.getText());
			} catch(Exception e){
				altOk = false;
				feilBord.setText("Ugyldig bordplasser");
			}
			try{
				 erGitar = Integer.parseInt(gitar.getText());
				 if((erGitar != 0) && (erGitar != 1)){
					 //Sett feilmelding
					 feilGitar.setText("Gitar må være 1 eller 0");
					 altOk = false;
			 }
			} catch(Exception e){
				altOk = false;
				feilGitar.setText("Gitar må være 1 eller 0");
			}
			try{
				 erVaffeljern = Integer.parseInt(vaffeljern.getText());
				 if((erVaffeljern != 0) && (erVaffeljern!= 1)){
					 //Sett feilmelding
					 feilVaffeljern.setText("Vaffeljern må være 1 eller 0");
					 altOk = false;
			 }
			} catch(Exception e){
				altOk = false;
				feilVaffeljern.setText("Vaffeljern må være 1 eller 0");
			}

			if(altOk){
				try {
					con = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/nilsad_koier", "nilsad" , "passord1212");
					PreparedStatement statement = con.prepareStatement ("UPDATE koie SET koie = "+"'"+koienavn+"'"+", sengeplasser = " + senger +", bordplasser = "+ bord +", gitar = "+ erGitar+ ", vaffeljern = "+ erVaffeljern + ", spesialiteter = " + "'" + spesial + "'" + " WHERE koienr = "+item.getKoienr());
					statement.executeUpdate();
					hentRapport();
					con.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				}
			}
		}
	});}

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
			item = rapportList.get(index);
			textField_KoieNavn.setText(item.getKoieNavn());
			StringBuilder sb = new StringBuilder();
			sb.append("");
			sb.append(item.getSengeplasser());
			sengeplasser.setText(sb.toString());
			StringBuilder sb2 = new StringBuilder();
			sb2.append("");
			sb2.append(item.getBordplasser());
			bordplasser.setText(sb2.toString());
			StringBuilder sb3 = new StringBuilder();
			sb3.append("");
			sb3.append(item.getGitar());
			gitar.setText(sb3.toString());
			StringBuilder sb4 = new StringBuilder();
			sb4.append("");
			sb4.append(item.getVaffeljern());
			vaffeljern.setText(sb4.toString());
			spesialiteter.setText(item.getSpesialiteter());


		}
	}
}