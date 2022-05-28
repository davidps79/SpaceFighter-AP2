package application;
	
import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private GameController back;
	private Stage currentStage;
	
	@Override
	public void start(Stage primaryStage) {
		back = new GameController();
			
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MenuViewController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root, 1200, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			currentStage = primaryStage;
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openGame() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			BorderPane root = (BorderPane) loader.load();
			SampleController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root, 1200, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			currentStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
			BorderPane root = (BorderPane) loader.load();
			SampleController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root, 1200, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			currentStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public GameController getBack() {
		return back;
	}
}
