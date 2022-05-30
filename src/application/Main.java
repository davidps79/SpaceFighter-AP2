package application;
	
import java.io.File;
import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameController;
import model.Registry;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private GameController back;
	private MediaPlayer mediaPlayer;
	private Stage currentStage;
	private Registry registry;
	
	@Override
	public void start(Stage primaryStage) {	
		this.registry = new Registry();
		
		try {
			playBackgroundMusic("MenuMusic.wav", 1f);
			currentStage = primaryStage;
			currentStage.setTitle("Space Adventure");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StartupView.fxml"));
			BorderPane root = (BorderPane) loader.load();
			StartupController controller = loader.getController();
			controller.setMain(this);			
			Scene scene = new Scene(root, 1200, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openGame() {
		try {
			back = new GameController(this);
			mediaPlayer.stop(); 
			playBackgroundMusic("BattleMusic.mp3", 0.3f);
			
			Runnable onEnd = new Runnable() {
	            @Override
	            public void run() {
	            	Media sound = new Media(new File("files/sounds/" + "BattleMusic.mp3").toURI().toString());
	            	mediaPlayer.dispose();
	            	mediaPlayer = new MediaPlayer(sound);
	            	mediaPlayer.setVolume(0.3f);
	            	mediaPlayer.play();
	            	mediaPlayer.setOnEndOfMedia(this);
	            }
	        };
	        mediaPlayer.setOnEndOfMedia(onEnd);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameView.fxml"));
			BorderPane root = (BorderPane) loader.load();
			GameViewController controller = loader.getController();
			back.setGameView(controller);
			controller.setMain(this);
			Scene scene = new Scene(root, 1200, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			currentStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openMenu() {
		Platform.runLater(()->{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
				BorderPane root = (BorderPane) loader.load();
				MenuViewController controller = loader.getController();
				controller.setMain(this);
				Scene scene = new Scene(root, 1200, 900);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				currentStage.setScene(scene);
			} catch(Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public GameController getBack() {
		return back;
	}
	
	private void playBackgroundMusic(String file, float volume){
		Media sound = new Media(new File("files/sounds/" + file).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(volume);
		mediaPlayer.setVolume(0.8f);
		mediaPlayer.setVolume(0.45);
		mediaPlayer.play();
	}

	public Registry getRegistry() {
		return registry;
	}

	public void openTopFive() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TopFiveView.fxml"));
			BorderPane root = (BorderPane) loader.load();
			TopFiveController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root, 1200, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			currentStage.setScene(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void endGame() {
		back.getFighter().stop();
		back = null;
		mediaPlayer.stop();
		playBackgroundMusic("MenuMusic.wav", 1f);
		openMenu();
	}
}
