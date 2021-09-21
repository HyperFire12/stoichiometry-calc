package pages;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Page {
	// Static Class variable that will be inherited by the different page classes
	static Stage primaryStage;
	 // Abstract getter that require a Pane object to be returned and must be overriden by all subclasses
	public abstract Pane getPage();
	 // Abstract getter that require a Scene object to be returned and must be overriden by all subclasses
	public abstract Scene getScene();
	 // Abstract method that will change the scene and must be overriden by all subclasses
	public abstract void changePage();
	
	//Method that sets the Static variable to the stage given
	public static void setStage(Stage stage) {
		primaryStage = stage;
	}
}
