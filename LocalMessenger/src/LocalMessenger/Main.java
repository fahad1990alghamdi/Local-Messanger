package LocalMessenger;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		 
		 Messenger messenger = new Messenger(primaryStage);
	     messenger.load();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
