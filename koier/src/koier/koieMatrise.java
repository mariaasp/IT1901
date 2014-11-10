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
import javafx.stage.Stage;
import javafx.util.Callback;


public class koieMatrise extends Application {
	
	Record item;
	Bruker temp;
	TextField textField_KoieNavn;
	TextField sengeplasser;
	TextField bordplasser;
	TextField gitar;
	TextField vaffeljern;
	TextField spesialiteter;
	TextField  textField_ved;
	CheckBox sjekkVed = new CheckBox();
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
		if(temp.permission == 1){
			Label koieNavn = new Label("Koienavn");
			textField_KoieNavn = new TextField();
			Label LabSengeplasser = new Label("Sengeplasser");
			sengeplasser = new TextField();
			Label LabBordplasser = new Label("Bordplasser");
			bordplasser = new TextField();
			Label LabGitar = new Label("Gitar");
			gitar = new TextField();
			Label LabVaffeljern = new Label("Vaffeljern");
			vaffeljern = new TextField();
			Label LabSpes = new Label("Spesialiteter");
			spesialiteter = new TextField();
			halve1.getChildren().addAll(koieNavn, textField_KoieNavn, LabSengeplasser, sengeplasser, LabBordplasser, bordplasser);
			halve2.getChildren().addAll(LabGitar, gitar, LabVaffeljern, vaffeljern, LabSpes, spesialiteter, regEnd);
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