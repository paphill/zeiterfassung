package application;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AuswertungController implements Initializable{

	ProjektDAO project = new ProjektDAODBImpl();
	MitarbeiterDAO mitarbeiter = new MitarbeiterDAODBImpl();
	TaetigkeitDAO taetigkeit = new TaetigkeitDAODBImpl();
	ObservableList<Taetigkeit> taetigkeitliste;
	ObservableList<Taetigkeit> zwischentaetigkeitliste;
	ObservableList<Mitarbeiter> mitarbeiterliste;
	ObservableList<Projekt> projektliste;
	private ObservableList<String> zwischenmitar;
	private ObservableList<String> zwischenproj;
	
	@FXML
	private ChoiceBox<String> choiceMitar;
	@FXML
	private ChoiceBox<String> choiceProj;
	@FXML
	private DatePicker choiceDatum;
	@FXML
	private TableColumn taetidcol;
	@FXML
	private TableColumn taetmitarcol;
	@FXML
	private TableColumn taetprojcol;
	@FXML
	private TableColumn zeitcol;
	@FXML
	private TableColumn beschcol;
	@FXML
	private TableColumn datecol;
	@FXML
	private Button buttonAuswerten;
	@FXML
	private TableView<Taetigkeit> taetview;
	
	@SuppressWarnings("unchecked")
	public void initialize(URL url, ResourceBundle rb) {
		choiceAktualisieren();
		
		taetidcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, Integer>("id"));
    	taetmitarcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, String>("mitar"));
    	taetprojcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, String>("proj"));
    	zeitcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, Integer>("zeit"));
    	beschcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, String>("beschreibung"));
    	datecol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, Date>("datum"));
    	
    	taetigkeitliste = FXCollections.observableArrayList(taetigkeit.getAllTaetigkeit());
    	
    	taetidcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.095));
    	taetmitarcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.18));
    	taetprojcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.20));
    	zeitcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.07));
    	beschcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.30));
    	datecol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.15));
		
    	taetview.setItems(taetigkeitliste);
	}
	
	@FXML
	public void auswertenClicked() {
		
		if(choiceMitar.getValue() != null && choiceProj.getValue() != null && choiceDatum.getValue() != null) {
			specificAuswerten(choiceProj.getValue(), choiceMitar.getValue(), choiceDatum.getValue().toString());
		}else if(choiceMitar.getValue() != null && choiceProj.getValue() == null && choiceDatum.getValue() == null) {
			mitarAuswerten(choiceMitar.getValue());
		}else if(choiceMitar.getValue() == null && choiceProj.getValue() != null && choiceDatum.getValue() == null) {
			projAuswerten(choiceProj.getValue());
		}else if(choiceMitar.getValue() == null && choiceProj.getValue() == null && choiceDatum.getValue() != null) {
			dateAuswerten(choiceDatum.getValue().toString());
		}else if (choiceMitar.getValue() != null && choiceProj.getValue() != null && choiceDatum.getValue() == null) {
			mitarProj(choiceProj.getValue(), choiceMitar.getValue());
		}else if (choiceMitar.getValue() == null && choiceProj.getValue() != null && choiceDatum.getValue() != null) {
			projDate(choiceProj.getValue(), choiceDatum.getValue().toString());
		}else if (choiceMitar.getValue() != null && choiceProj.getValue() == null && choiceDatum.getValue() != null) {
			mitarDate(choiceMitar.getValue(), choiceDatum.getValue().toString());
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warnung");
			alert.setHeaderText("Warnung, keine Felder ausgefüllt!");
			alert.setContentText("Bitte nach Mitarbeiter oder Projekt filtern");
			alert.showAndWait();
		}
		choiceMitar.setValue(null);
		choiceProj.setValue(null);
		choiceDatum.setValue(null);
	}
	
	public void projAuswerten(String auswahl) {
		taetigkeitliste = FXCollections.observableArrayList(taetigkeit.getAllProjTaetigkeit("'" + auswahl + "'"));
		taetview.setItems(taetigkeitliste);
	}
	
	public void mitarAuswerten(String auswahl) {
		taetigkeitliste = FXCollections.observableArrayList(taetigkeit.getAllMitarTaetigkeit("'" + auswahl + "'"));
		taetview.setItems(taetigkeitliste);
	}
	
	public void dateAuswerten(String auswahl) {
		taetigkeitliste = FXCollections.observableArrayList(taetigkeit.getAllDateTaetigkeit("'" + auswahl + "'"));
		taetview.setItems(taetigkeitliste);
	}
	
	public void mitarProj(String auswahl, String choose) {
		taetigkeitliste = FXCollections.observableArrayList(taetigkeit.mitarProj("'" + auswahl + "'", "'" + choose + "'"));
		taetview.setItems(taetigkeitliste);
	}
	
	public void projDate(String auswahl, String choose) {
		taetigkeitliste = FXCollections.observableArrayList(taetigkeit.projDate("'" + auswahl + "'", "'" + choose + "'"));
		taetview.setItems(taetigkeitliste);
	}
	
	public void mitarDate(String auswahl, String choose) {
		taetigkeitliste = FXCollections.observableArrayList(taetigkeit.mitarDate("'" + auswahl + "'", "'" + choose + "'"));
		taetview.setItems(taetigkeitliste);
	}
	
	public void specificAuswerten(String projid, String mitarid, String datum) {
		taetigkeitliste = FXCollections.observableArrayList(taetigkeit.getAllSpecificTaetigkeit("'" + projid + "'", "'" + mitarid + "'", "'" + datum + "'"));
		taetview.setItems(taetigkeitliste);
	}
	
	@FXML
	public void choiceAktualisieren() {
		choiceMitarAktualisieren();
		choiceProjAktualisieren();
	}
	
	@FXML
	public void choiceMitarClicked() {
		choiceMitarAktualisieren();
	}
	
	@FXML
	public void choiceProjClicked() {
		choiceProjAktualisieren();
	}
	
	public void choiceMitarAktualisieren() {
		mitarbeiterliste = FXCollections.observableArrayList(mitarbeiter.getAllMitarbeiter());
		zwischenmitar = FXCollections.observableArrayList();
		
		for(Mitarbeiter m : mitarbeiterliste) {
			zwischenmitar.add(m.toString());
		}
	
		choiceMitar.setItems(zwischenmitar);
	}
	
	public void choiceProjAktualisieren() {
		projektliste = FXCollections.observableArrayList(project.getAllProjekt());
		zwischenproj = FXCollections.observableArrayList();
		
		for(Projekt p : projektliste) {
			zwischenproj.add(p.toString());
		}

		choiceProj.setItems(zwischenproj);;
	}
	
	
}
