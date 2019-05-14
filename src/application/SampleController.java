package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class SampleController implements Initializable{

	ProjektDAO project = new ProjektDAODBImpl();
	MitarbeiterDAO mitarbeiter = new MitarbeiterDAODBImpl();
	ObservableList<Mitarbeiter> mitarbeiterliste;
	ObservableList<Projekt> projektliste;

	@FXML
	private TextField eintragID;
	@FXML
	private TextField eintragVorname;
	@FXML
	private TextField eintragNachname;
	@FXML
	private TextField eintragBezeichnung;
	@FXML
	private Button buttonLeeren;
	@FXML
	private Button buttonLoeschen;
	@FXML
	private Button buttonSpeichern;
	@FXML
	private Button buttonExport;
	@FXML
	private Button buttonAbmelden;
	@FXML
	private TableView<Mitarbeiter> mitarbeiterview;
	@FXML
	private TableView<Projekt> projektview;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) {	
    	TableColumn idcol = new TableColumn("ID");
    	TableColumn vorcol = new TableColumn("Vorname");
		TableColumn nachcol = new TableColumn("Nachname");
		mitarbeiterview.getColumns().addAll(idcol, vorcol, nachcol);
		
		idcol.setCellValueFactory(new PropertyValueFactory<Mitarbeiter, Integer>("id"));
    	vorcol.setCellValueFactory(new PropertyValueFactory<Mitarbeiter, String>("vorname"));
    	nachcol.setCellValueFactory(new PropertyValueFactory<Mitarbeiter, String>("nachname"));
    	
    	mitarbeiterliste = FXCollections.observableArrayList(mitarbeiter.getAllMitarbeiter());
    	
    	idcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.2));
    	vorcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.4));
    	nachcol.prefWidthProperty().bind(mitarbeiterview.widthProperty().multiply(0.4));
		
    	mitarbeiterview.setItems(mitarbeiterliste);
    	}

	@FXML
    public void buttonSpeichernClicked() {
		 String vorname = String.valueOf(eintragVorname.getText());
		 String nachname = String.valueOf(eintragNachname.getText());
		 if(eintragID.getLength() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Bestätigung");
            String s ="Mitarbeiter wurde erfolgreich hinzugefügt";
            alert.setContentText(s);
            alert.show();
    		mitarbeiter.addMitarbeiter(vorname, nachname);
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Bestätigung");
            String s ="Mitarbeiter wurde erfolgreich aktualisiert";
            alert.setContentText(s);
            alert.show();
            int id = Integer.valueOf(eintragID.getText());
        	mitarbeiter.updateMitarbeiter(id, vorname, nachname);
        }
        mitarbeiterliste = FXCollections.observableArrayList(mitarbeiter.getAllMitarbeiter());
    	mitarbeiterview.setItems(mitarbeiterliste);
	}
	
	@FXML
    public void buttonLeerenClicked() {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Entleeren");
        String s ="Eingabefelder wurden geleert";
        alert.setContentText(s);
        alert.show();
        eintragID.setText("");
		eintragVorname.setText("");
		eintragNachname.setText("");
	}
	
	@FXML
    public void buttonLoeschenClicked() {
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
	
//	@FXML
//	public void buttonAnmeldenClicked() {
//		nichtangemeldeteplayers = FXCollections.observableArrayList();
//		angemeldeteplayers = FXCollections.observableArrayList();
//		ungezahlt = FXCollections.observableArrayList();
//		for(Spieler s : spielerliste) {
//			if(s.isAngemeldet() == false) {
//				nichtangemeldeteplayers.add(s);
//			}else {
//				angemeldeteplayers.add(s);
//				if(s.isPaid() == false) {
//					ungezahlt.add(s);
//				}
//			}
//		}
//		ChoiceDialog<Spieler> dialog = new ChoiceDialog<>(nichtangemeldeteplayers.get(0),nichtangemeldeteplayers);
//		dialog.setTitle("Auswahl");
//		dialog.setHeaderText("Bitte wählen sie den anzumeldenden Spieler aus");
//		dialog.setContentText("Spieler auswählen:");
//
//		// Traditional way to get the response value.
//		Optional<Spieler> result = dialog.showAndWait();
//		if (result.isPresent()){
//			result.get().setAngemeldet(true);
//			angemeldeteplayers.add(result.get());
//			nichtangemeldeteplayers.remove(result.get());
//		    Turnier selectedItem = turnierview.getSelectionModel().getSelectedItem();
//			tournament.updateTurnier(selectedItem.getId(), selectedItem.getName(), selectedItem.getStart(), selectedItem.getEnde(), angemeldeteplayers.size(), selectedItem.getNenngeld());
//			turnierliste = FXCollections.observableArrayList(tournament.getAllTurniere());
//	    	turnierview.setItems(turnierliste);
//	    	int teilnehmer = 0;
//	    	for(Spieler s : ungezahlt) {
//	    		teilnehmer++;
//	    	}
//	    	double nenngeld = selectedItem.getNenngeld();
//	    	eintragNenngeld.setText(String.valueOf(teilnehmer*nenngeld));
//	    	spielerview.refresh();
//		}
//		System.out.println("Alle bereits angemeldete Spieler");
//		System.out.println(angemeldeteplayers);
//	}
//	
//	@FXML
//	public void buttonAbmeldenClicked() {
//		nichtangemeldeteplayers = FXCollections.observableArrayList();
//		angemeldeteplayers = FXCollections.observableArrayList();
//		ungezahlt = FXCollections.observableArrayList();
//		for(Spieler s : spielerliste) {
//			if(s.isAngemeldet() == true) {
//				angemeldeteplayers.add(s);
//				if(s.isPaid() == false) {
//					ungezahlt.add(s);
//				}
//			}else {
//				nichtangemeldeteplayers.add(s);
//			}
//		}
//		
//		ChoiceDialog<Spieler> dialog = new ChoiceDialog<>(angemeldeteplayers.get(0), angemeldeteplayers);
//		dialog.setTitle("Auswahl");
//		dialog.setHeaderText("Bitte wählen sie den abzumeldenden Spieler aus");
//		dialog.setContentText("Spieler auswählen:");
//
//		// Traditional way to get the response value.
//		Optional<Spieler> result = dialog.showAndWait();
//		if (result.isPresent()){
//			result.get().setAngemeldet(false);
//			result.get().setPaid(false);
//			nichtangemeldeteplayers.add(result.get());
//			angemeldeteplayers.remove(result.get());
//			ungezahlt.remove(result.get());
//		    Turnier selectedItem = turnierview.getSelectionModel().getSelectedItem();
//			tournament.updateTurnier(selectedItem.getId(), selectedItem.getName(), selectedItem.getStart(), selectedItem.getEnde(), angemeldeteplayers.size(), selectedItem.getNenngeld());
//			turnierliste = FXCollections.observableArrayList(tournament.getAllTurniere());
//	    	turnierview.setItems(turnierliste);
//	    	int teilnehmer = 0;
//	    	for(Spieler s : ungezahlt) {
//	    		teilnehmer++;
//	    	}
//	    	double nenngeld = selectedItem.getNenngeld();
//	    	eintragNenngeld.setText(String.valueOf(teilnehmer*nenngeld));
//	    	spielerview.refresh();
//		}
//		System.out.println("Alle noch nicht angemeldeten Spieler");
//		System.out.println(nichtangemeldeteplayers);
//	}
	
//	public void speichereAlsCSV(String pfad) {
//
//        FileWriter fileWriter = null;
//        try {
//            new File(pfad).createNewFile();
//            fileWriter = new FileWriter(pfad);
//            fileWriter.append("Alle Turniere");
//            fileWriter.append("\n");
//            for (Turnier t : turnierliste) {
//                fileWriter.append(t.toCSV());
//                fileWriter.append("\n");
//                for(Spieler s : angemeldeteplayers) {
//                	fileWriter.append(s.toCSVAngemeldet());
//                    fileWriter.append("\n");
//                }
//            }
//            
//            fileWriter.append("\n");
//            fileWriter.append("Alle Spieler");
//            fileWriter.append("\n");
//            for(Spieler s : spielerliste) {
//            	fileWriter.append(s.toCSV());
//            	fileWriter.append("\n");
//            }
//            System.out.println("Erfolgreich exportiert!");
//
//        } catch (IOException e) {
//            System.out.println("Fehler beim Schreiben.");
//            e.printStackTrace();
//        } finally{
//            try{
//                fileWriter.flush();
//                fileWriter.close();
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//	}
//	
//	@FXML
//	public void buttonExportClicked() {
//		speichereAlsCSV("D:\\export.csv");
//	}
	
	@FXML
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) //Checking double click
	    {
			Mitarbeiter abk = mitarbeiterview.getSelectionModel().getSelectedItem();
	        eintragVorname.setText(abk.getVorname());
	        eintragNachname.setText(abk.getNachname());
	    }
	}
}
