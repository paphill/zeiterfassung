package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane rootcreate = (Pane)FXMLLoader.load(getClass().getResource("Anlegen.fxml"));
			Pane rootrecord = (Pane)FXMLLoader.load(getClass().getResource("Taetigkeiterfassung.fxml"));
			Pane rootevalutation = (Pane)FXMLLoader.load(getClass().getResource("Auswertung.fxml"));
			TabPane tabpane = new TabPane();
			Scene scene = new Scene(tabpane);
			Tab erstellen = new Tab("Create");
			Tab erfassen = new Tab("Record");
			Tab auswertung = new Tab("Evalutation");
			erstellen.setContent(rootcreate);
			erfassen.setContent(rootrecord);
			auswertung.setContent(rootevalutation);
			tabpane.getTabs().add(erstellen);
			tabpane.getTabs().add(erfassen);
			tabpane.getTabs().add(auswertung);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Zeiterfassung");
			primaryStage.show();
			primaryStage.centerOnScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
