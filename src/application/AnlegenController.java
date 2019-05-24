package application;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.Date;
import java.util.*;
import java.io.*;
import java.net.URL;

public class AnlegenController implements Initializable{

	ProjektDAO project = new ProjektDAODBImpl();
	MitarbeiterDAO mitarbeiter = new MitarbeiterDAODBImpl();
	ObservableList<Mitarbeiter> mitarbeiterliste;
	ObservableList<Projekt> projektliste;

	@FXML
	private TextField eintragID;
	@FXML
	private TextField eintragProID;
	@FXML
	private TextField eintragVorname;
	@FXML
	private TextField eintragNachname;
	@FXML
	private TextField eintragBezeichnung;
	@FXML
	private TextField eintragAuftraggeber;
	@FXML
	private ChoiceBox<String> choiceTyp;
	@FXML
	private Button buttonLeeren;
	@FXML
	private Button buttonLoeschen;
	@FXML
	private Button buttonSpeichern;
	@FXML
	private Button buttonAbmelden;
	@FXML
	private TableColumn idcol;
	@FXML
	private TableColumn vorcol;
	@FXML
	private TableColumn nachcol;
	@FXML
	private TableColumn typcol;
	@FXML
	private TableColumn projidcol;
	@FXML
	private TableColumn bezcol;
	@FXML
	private TableColumn auftragcol;
	@FXML
	private Button mitarSpeichern;
	@FXML
	private Button mitarLeeren;
	@FXML
	private Button mitarLoeschen;
	@FXML
	private Button projSpeichern;
	@FXML
	private Button projLeeren;
	@FXML
	private Button projLoeschen;
	@FXML
	private TableView<Mitarbeiter> mitarbeiterview;
	@FXML
	private TableView<Projekt> projektview;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
		
		choiceTyp.getItems().add("Admin");
		choiceTyp.getItems().add("Buchhaltung");
		choiceTyp.getItems().add("Einkauf/Verkauf");
		choiceTyp.getItems().add("Lagerhaltung");
		
		idcol.setCellValueFactory(new PropertyValueFactory<Mitarbeiter, Integer>("id"));
    	vorcol.setCellValueFactory(new PropertyValueFactory<Mitarbeiter, String>("vorname"));
    	nachcol.setCellValueFactory(new PropertyValueFactory<Mitarbeiter, String>("nachname"));
    	typcol.setCellValueFactory(new PropertyValueFactory<Mitarbeiter, String>("typ"));
    	projidcol.setCellValueFactory(new PropertyValueFactory<Projekt, Integer>("id"));
    	bezcol.setCellValueFactory(new PropertyValueFactory<Projekt, String>("name"));
    	auftragcol.setCellValueFactory(new PropertyValueFactory<Projekt, String>("auftraggeber"));
    	
    	mitarbeiterliste = FXCollections.observableArrayList(mitarbeiter.getAllMitarbeiter());
    	projektliste = FXCollections.observableArrayList(project.getAllProjekt());
    	
    	idcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.09));
    	vorcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.275));
    	nachcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.275));
    	typcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.35));
    	projidcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.09));
    	bezcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.45));
    	auftragcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.45));
		
    	mitarbeiterview.setItems(mitarbeiterliste);
    	projektview.setItems(projektliste);
    	}

	@FXML
    public void mitarSpeichernClicked() {
		 String vorname = String.valueOf(eintragVorname.getText());
		 String nachname = String.valueOf(eintragNachname.getText());
		 String typ = String.valueOf(choiceTyp.getValue());
		 if(eintragID.getLength() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Bestätigung");
            String s ="Mitarbeiter wurde erfolgreich hinzugefügt";
            alert.setContentText(s);
            alert.show();
    		mitarbeiter.addMitarbeiter(vorname, nachname, typ);
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Bestätigung");
            String s ="Mitarbeiter wurde erfolgreich aktualisiert";
            alert.setContentText(s);
            alert.show();
            int id = Integer.valueOf(eintragID.getText());
        	mitarbeiter.updateMitarbeiter(id, vorname, nachname, typ);
        }
        mitarbeiterliste = FXCollections.observableArrayList(mitarbeiter.getAllMitarbeiter());
    	mitarbeiterview.setItems(mitarbeiterliste);
    	mitarLeerenSpeichern();
	}
	
	@FXML
    public void mitarLeerenClicked() {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Entleeren");
        String s ="Eingabefelder wurden geleert";
        alert.setContentText(s);
        alert.show();
        eintragID.setText("");
		eintragVorname.setText("");
		eintragNachname.setText("");
		choiceTyp.getSelectionModel().clearSelection();
		choiceTyp.setValue(null);
	}
	
	@FXML
    public void mitarLoeschenClicked() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Information");
		String s = "Bitte Löschen bestätigen";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			Mitarbeiter selectedItem = mitarbeiterview.getSelectionModel().getSelectedItem();
			mitarbeiter.deleteMitarbeiter(selectedItem.getId());
		    mitarbeiterview.getItems().remove(selectedItem);
		}
	}
	
	@FXML
	public void mitarLeerenSpeichern() {
		eintragID.setText("");
		eintragVorname.setText("");
		eintragNachname.setText("");
		choiceTyp.getSelectionModel().clearSelection();
		choiceTyp.setValue(null);
	}
	
	@FXML
	public void projLeerenSpeichern() {
		eintragProID.setText("");
		eintragBezeichnung.setText("");
		eintragAuftraggeber.setText("");
	}
	
	@FXML
	public void projSpeichernClicked() {
		String bezeichnung = String.valueOf(eintragBezeichnung.getText());
		String auftrag = String.valueOf(eintragAuftraggeber.getText());
		 if(eintragProID.getLength() == 0) {
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Information");
           alert.setHeaderText("Bestätigung");
           String s ="Projekt wurde erfolgreich hinzugefügt";
           alert.setContentText(s);
           alert.show();
   		project.addProjekt(bezeichnung, auftrag);
       }else {
       	Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Information");
           alert.setHeaderText("Bestätigung");
           String s ="Projekt wurde erfolgreich aktualisiert";
           alert.setContentText(s);
           alert.show();
           int id = Integer.valueOf(eintragProID.getText());
       	project.updateProjekt(id, bezeichnung, auftrag);
       }
       projektliste = FXCollections.observableArrayList(project.getAllProjekt());
   	projektview.setItems(projektliste);
   	projLeerenSpeichern();
	}
	
	@FXML
	public void projLeerenClicked() {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Entleeren");
        String s ="Eingabefelder wurden geleert";
        alert.setContentText(s);
        alert.show();
        eintragProID.setText("");
		eintragBezeichnung.setText("");
		eintragAuftraggeber.setText("");
	}
	
	@FXML
	public void projLoeschenClicked() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Information");
		String s = "Bitte Löschen bestätigen";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			Projekt selectedItem = projektview.getSelectionModel().getSelectedItem();
			project.deleteProjekt(selectedItem.getId());
		    projektview.getItems().remove(selectedItem);
		}
	}
	
	@FXML
	public void mitarbeiterviewClicked(MouseEvent event) {
		if (event.getClickCount() == 2) //Checking double click
	    {
			Mitarbeiter abk = mitarbeiterview.getSelectionModel().getSelectedItem();
			eintragID.setText(""+abk.getId());
	        eintragVorname.setText(abk.getVorname());
	        eintragNachname.setText(abk.getNachname());
	        choiceTyp.setValue(abk.getTyp());
	    }
	}
	
	@FXML
	public void projectviewClicked(MouseEvent event) {
		if (event.getClickCount() == 2) //Checking double click
	    {
			Projekt abk = projektview.getSelectionModel().getSelectedItem();
			eintragProID.setText(""+abk.getId());
			eintragBezeichnung.setText(abk.getName());
			eintragAuftraggeber.setText(abk.getAuftraggeber());
	    }
	}
}
