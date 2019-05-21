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

public class AuswertungController implements Initializable {

	ProjektDAO project = new ProjektDAODBImpl();
	MitarbeiterDAO mitarbeiter = new MitarbeiterDAODBImpl();
	TaetigkeitDAO taetigkeit = new TaetigkeitDAODBImpl();
	ObservableList<Taetigkeit> taetigkeitliste;
	ObservableList<Mitarbeiter> mitarbeiterliste;
	ObservableList<Projekt> projektliste;
	private ObservableList<String> zwischenmitar;
	private ObservableList<String> zwischenproj;

	@FXML
	private TextField eintragZeit;
	@FXML
	private TextField eintragBeschreibung;
	@FXML
	private TextField eintragTaetID;
	@FXML
	private ChoiceBox<String> choiceMitar;
	@FXML
	private ChoiceBox<String> choiceProj;
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
	private Button taetSpeichern;
	@FXML
	private Button taetLeeren;
	@FXML
	private Button taetLoeschen;
	@FXML
	private Button buttonExport;
	@FXML
	private TableView<Taetigkeit> taetview;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		choiceAktualisieren();

		taetidcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, Integer>("id"));
		taetmitarcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, String>("mitar"));
		taetprojcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, String>("proj"));
		zeitcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, Integer>("zeit"));
		beschcol.setCellValueFactory(new PropertyValueFactory<Taetigkeit, String>("beschreibung"));

		taetigkeitliste = FXCollections.observableArrayList(taetigkeit.getAllTaetigkeit());

		taetidcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.09));
		taetmitarcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.2));
		taetprojcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.2));
		zeitcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.15));
		beschcol.prefWidthProperty().bind(taetview.widthProperty().multiply(0.35));

		taetview.setItems(taetigkeitliste);
	}

	@FXML
	public void taetSpeichernClicked() {
		String mitar = String.valueOf(choiceMitar.getValue());
		String proj = String.valueOf(choiceProj.getValue());
		int zeit = Integer.valueOf(eintragZeit.getText());
		String beschreibung = String.valueOf(eintragBeschreibung.getText());
		if (eintragTaetID.getLength() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Bestätigung");
			String s = "Taetigkeit wurde erfolgreich hinzugefügt";
			alert.setContentText(s);
			alert.show();
			taetigkeit.addTaetigkeit(mitar, proj, zeit, beschreibung);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Bestätigung");
			String s = "Taetigkeit wurde erfolgreich aktualisiert";
			alert.setContentText(s);
			alert.show();
			int id = Integer.valueOf(eintragTaetID.getText());
			taetigkeit.updateTaetigkeit(id, mitar, proj, zeit, beschreibung);
		}
		taetigkeitliste = FXCollections.observableArrayList(taetigkeit.getAllTaetigkeit());
		taetview.setItems(taetigkeitliste);
	}

	public void choiceAktualisieren() {
		mitarbeiterliste = FXCollections.observableArrayList(mitarbeiter.getAllMitarbeiter());
		projektliste = FXCollections.observableArrayList(project.getAllProjekt());
		zwischenmitar = FXCollections.observableArrayList();
		zwischenproj = FXCollections.observableArrayList();

		for (Mitarbeiter m : mitarbeiterliste) {
			zwischenmitar.add(m.toString());
		}

		for (Projekt p : projektliste) {
			zwischenproj.add(p.toString());
		}

		choiceMitar.setItems(zwischenmitar);
		choiceProj.setItems(zwischenproj);
		;
	}

	@FXML
	public void taetLeerenClicked() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Entleeren");
		String s = "Eingabefelder wurden geleert";
		alert.setContentText(s);
		alert.show();
		eintragTaetID.setText("");
		choiceMitar.getSelectionModel().clearSelection();
		choiceMitar.setValue(null);
		choiceProj.getSelectionModel().clearSelection();
		choiceProj.setValue(null);
		eintragZeit.setText("");
		eintragBeschreibung.setText("");
	}

	@FXML
	public void taetLoeschenClicked() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Information");
		String s = "Bitte Löschen bestätigen";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			Taetigkeit selectedItem = taetview.getSelectionModel().getSelectedItem();
			taetigkeit.deleteTaetigkeit(selectedItem.getId());
			taetview.getItems().remove(selectedItem);
		}
	}

	@FXML
	public void ExportClicked() {

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

	public void speichereAlsCSV(String pfad) {

		FileWriter fileWriter = null;
		try {
			new File(pfad).createNewFile();
			fileWriter = new FileWriter(pfad);
			fileWriter.append("Alle Projekte");
			fileWriter.append("\n");
			for (Projekt t : projektliste) {
				fileWriter.append(t.toCSV());
				fileWriter.append("\n");
			}
			
			System.out.println("Eexportiert!");

		} catch (IOException e) {
			System.out.println("Fehler beim Schreiben.");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//	
//	@FXML
//	public void ExportClicked() {
//		speichereAlsCSV("D:\\export.csv");
//	}

	@FXML
	public void choiceClicked(MouseEvent even) {
		zwischenmitar.clear();
		zwischenproj.clear();
		choiceAktualisieren();
	}

	@FXML
	public void taetClicked(MouseEvent event) {
		if (event.getClickCount() == 2) // Checking double click
		{
			Taetigkeit abk = taetview.getSelectionModel().getSelectedItem();
			eintragTaetID.setText("" + abk.getId());
			choiceMitar.setValue(abk.getMitar());
			choiceProj.setValue(abk.getProj());
			eintragZeit.setText("" + abk.getZeit());
			eintragBeschreibung.setText(abk.getBeschreibung());

		}
	}
}
