package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private GameController back;
	
	@Override
	public void start(Stage primaryStage) {
		back = new GameController();
			
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			BorderPane root = (BorderPane) loader.load();
			SampleController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
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
