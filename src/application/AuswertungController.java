package application;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.net.URL;

public class AuswertungController implements Initializable{

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
	private DatePicker eintragDate;
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
    public void taetSpeichernClicked() {
		 String mitar = String.valueOf(choiceMitar.getValue());
		 String proj = String.valueOf(choiceProj.getValue());
		 int zeit = Integer.valueOf(eintragZeit.getText());
		 String beschreibung = String.valueOf(eintragBeschreibung.getText());
		 Date datum = java.sql.Date.valueOf(eintragDate.getValue());
		 if(eintragTaetID.getLength() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Best�tigung");
            String s ="Taetigkeit wurde erfolgreich hinzugef�gt";
            alert.setContentText(s);
            alert.show();
    		taetigkeit.addTaetigkeit(mitar, proj, zeit, beschreibung, datum);
        }else {
        	Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Best�tigung");
            String s ="Taetigkeit wurde erfolgreich aktualisiert";
            alert.setContentText(s);
            alert.show();
            int id = Integer.valueOf(eintragTaetID.getText());
        	taetigkeit.updateTaetigkeit(id, mitar, proj, zeit, beschreibung, datum);
        }
        taetigkeitliste = FXCollections.observableArrayList(taetigkeit.getAllTaetigkeit());
    	taetview.setItems(taetigkeitliste);
    	taetLeerenSpeichern();
	}
	
	public void choiceAktualisieren() {
		choiceMitarAktualisieren();
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
	
	@FXML
	public void choiceMitarClicked() {
		choiceMitarAktualisieren();
	}
	
	@FXML
	public void choiceProjClicked() {
		choiceProjAktualisieren();
	}
	
	@FXML
	public void taetLeerenSpeichern() {
		eintragTaetID.setText("");
		choiceMitar.getSelectionModel().clearSelection();
		choiceMitar.setValue(null);
		choiceProj.getSelectionModel().clearSelection();
		choiceProj.setValue(null);
		eintragZeit.setText("");
		eintragBeschreibung.setText("");
		eintragDate.setValue(null);
	}
	
	@FXML
    public void taetLeerenClicked() {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Entleeren");
        String s ="Eingabefelder wurden geleert";
        alert.setContentText(s);
        alert.show();
        eintragTaetID.setText("");
		choiceMitar.getSelectionModel().clearSelection();
		choiceMitar.setValue(null);
		choiceProj.getSelectionModel().clearSelection();
		choiceProj.setValue(null);
		eintragZeit.setText("");
		eintragBeschreibung.setText("");
		eintragDate.setValue(null);
	}
	
	@FXML
    public void taetLoeschenClicked() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Information");
		String s = "Bitte L�schen best�tigen";
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
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Auswahl der Exportm�glichkeit");
		alert.setHeaderText("Auswahl Exportm�glichkeit");
		alert.setContentText("Bitte w�hlen Sie die Exportm�glichkeit aus:");

		ButtonType buttonTypeOne = new ButtonType("Projekte");
		ButtonType buttonTypeTwo = new ButtonType("Mitarbeiter");
		ButtonType buttonTypeThree = new ButtonType("Allgemein");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
		    auswertungProjekte("D:\\export.csv");
		} else if (result.get() == buttonTypeTwo) {
		    auswertungMitarbeiter("D:\\export.csv");
		} else if (result.get() == buttonTypeThree){
		    auswertungAllgemein("D:\\export.csv");
		}
	}
	
	public void auswertungAllgemein(String pfad){
        FileWriter fileWriter = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd;HH:mm");
		LocalDateTime now = LocalDateTime.now();
        try {
            new File(pfad).createNewFile();
            fileWriter = new FileWriter(pfad);
            String date = dtf.format(now);
            fileWriter.append(date+";");
            fileWriter.append("\n");
            fileWriter.append("Alle Taetigkeiten der Mitarbeiter");
            fileWriter.append("\n");
        	fileWriter.append("ID;Vorname;Nachname;");
        	fileWriter.append("\n");
            for (Mitarbeiter m : mitarbeiterliste) {
                fileWriter.append(m.toCSV());
                fileWriter.append("\n");
            	fileWriter.append(";ID;Projekt;Dauer;Beschreibung;Datum;");
            	fileWriter.append("\n");
                for(Taetigkeit t : taetigkeitliste) {
                	if(m.getId() == Integer.parseInt(t.getMitarId())) {
                    	fileWriter.append(t.toCSV());
                    	fileWriter.append("\n");
                    }
                }
            }
            fileWriter.append("\n");
            fileWriter.append("Alle Mitarbeiter");
            fileWriter.append("\n");
            for (Mitarbeiter m : mitarbeiterliste) {
                fileWriter.append(m.toCSV());
                fileWriter.append("\n");
            }
            
            fileWriter.append("\n");
            fileWriter.append("Alle Projekte");
            fileWriter.append("\n");
            for(Projekt p : projektliste) {
            	fileWriter.append(p.toCSV());
            	fileWriter.append("\n");
            }
            System.out.println("Erfolgreich exportiert!");

        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben.");
            e.printStackTrace();
        } finally{
            try{
                fileWriter.flush();
                fileWriter.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
	}
	
	public void auswertungMitarbeiter(String pfad){
		FileWriter fileWriter = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd;HH:mm");
		LocalDateTime now = LocalDateTime.now();
        try {
            new File(pfad).createNewFile();
            fileWriter = new FileWriter(pfad);
            String date = dtf.format(now);
            fileWriter.append(date+";");
            fileWriter.append("\n");
            fileWriter.append("Alle Taetigkeiten der Mitarbeiter");
            fileWriter.append("\n");
        	fileWriter.append("ID;Vorname;Nachname;");
        	fileWriter.append("\n");
            for (Mitarbeiter m : mitarbeiterliste) {
                fileWriter.append(m.toCSV());
                fileWriter.append("\n");
            	fileWriter.append(";ID;Projekt;Dauer;Beschreibung;Datum;");
            	fileWriter.append("\n");
                for(Taetigkeit t : taetigkeitliste) {
                	if(m.getId() == Integer.parseInt(t.getMitarId())) {
                    	fileWriter.append(t.toCSVMitar());
                    	fileWriter.append("\n");
                    }
                }
            }
            System.out.println("Erfolgreich exportiert!");

        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben.");
            e.printStackTrace();
        } finally{
            try{
                fileWriter.flush();
                fileWriter.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
	}
	
	public void auswertungProjekte(String pfad){
		FileWriter fileWriter = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd;HH:mm");
		LocalDateTime now = LocalDateTime.now();

        try {
            new File(pfad).createNewFile();
            fileWriter = new FileWriter(pfad);
            String date = dtf.format(now);
            fileWriter.append(date+";");
            fileWriter.append("\n");
            fileWriter.append("Alle Bearbeitungen der Projekte");
            fileWriter.append("\n");
        	fileWriter.append("ID;Bezeichnung;Auftraggeber;");
        	fileWriter.append("\n");
        	for(Projekt p : projektliste) {
            	fileWriter.append(p.toCSV());
            	fileWriter.append("\n");
            	fileWriter.append(";ID;Mitarbeiter;Dauer;Beschreibung;Datum;");
            	fileWriter.append("\n");
                for(Taetigkeit t : taetigkeitliste) {
                	if(p.getId() == Integer.parseInt(t.getProjId())) {
                    	fileWriter.append(t.toCSVProj());
                    	fileWriter.append("\n");
                    }
                }
            }
            System.out.println("Erfolgreich exportiert!");

        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben.");
            e.printStackTrace();
        } finally{
            try{
                fileWriter.flush();
                fileWriter.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
	}
	
	@FXML
	public void choiceClicked(MouseEvent even) {
		zwischenmitar.clear();
		zwischenproj.clear();
		choiceAktualisieren();
	}
	
	@FXML
	public void taetClicked(MouseEvent event) {
		if (event.getClickCount() == 2) //Checking double click
	    {
			Taetigkeit abk = taetview.getSelectionModel().getSelectedItem();
			eintragTaetID.setText(""+abk.getId());
			choiceMitar.setValue(abk.getMitar());
			choiceProj.setValue(abk.getProj());
			eintragZeit.setText(""+abk.getZeit());
			eintragBeschreibung.setText(abk.getBeschreibung());
			eintragDate.setValue(abk.getDatum().toLocalDate());
	        
	    }
	}
}
