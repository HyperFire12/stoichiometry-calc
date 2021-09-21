package application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SpeedFormat {
	/*
	 * The purpose of speed format class is to be a collection of methods to format
	 * javafx elements with common commands to reduce code clutter, as well as to
	 * standardize things such as projects font and font size
	 */

	// Button speed format with preference width and height
	public static Button button(Button toSetup, Pane pane, double posX, double posY, double prefWidth,
			double prefHeight, String setText, String styleTag) {

		toSetup.setText(setText);

		pane.getChildren().add(toSetup);

		toSetup.setTranslateY(posY);
		toSetup.setTranslateX(posX);

		toSetup.setPrefHeight(prefHeight);
		toSetup.setPrefWidth(prefWidth);

		toSetup.setFont(Font.font("Lucida Grande", 12));
		toSetup.setStyle(styleTag);

		return toSetup;
	}

	// Button speed format with no preference width and height
	public static Button button(Button toSetup, Pane pane, double posX, double posY, String setText, String styleTag) {

		toSetup.setText(setText);

		pane.getChildren().add(toSetup);

		toSetup.setTranslateY(posY);
		toSetup.setTranslateX(posX);

		toSetup.setFont(Font.font("Lucida Grande", 12));
		toSetup.setStyle(styleTag);

		return toSetup;
	}

	// Text speed format
	public static Text text(Text toSetup, Pane pane, double posX, double posY, String setText, String styleTag) {

		toSetup.setText(setText);

		pane.getChildren().add(toSetup);

		toSetup.setTranslateY(posY);
		toSetup.setTranslateX(posX);

		toSetup.setFont(Font.font("Lucida Grande", 12));
		toSetup.setStyle(styleTag);

		return toSetup;
	}

	// Textfield speed format with NO text prompt
	public static TextField textField(TextField toSetup, Pane pane, double posX, double posY, double prefHeight,
			double prefWidth) {

		pane.getChildren().add(toSetup);

		toSetup.setTranslateY(posY);
		toSetup.setTranslateX(posX);

		toSetup.setPrefHeight(prefHeight);
		toSetup.setPrefWidth(prefWidth);

		return toSetup;
	}

	// Textfield speed format with text prompt with no pref height
	public static TextField textField(TextField toSetup, Pane pane, double posX, double posY, double prefWidth,
			String promptText) {

		pane.getChildren().add(toSetup);

		toSetup.setTranslateY(posY);
		toSetup.setTranslateX(posX);

		toSetup.setPrefWidth(prefWidth);

		toSetup.setPromptText(promptText);

		return toSetup;
	}

	// Textfield speed format with text prompt with pref height
	public static TextField textField(TextField toSetup, Pane pane, double posX, double posY, double prefHeight,
			double prefWidth, String promptText) {

		pane.getChildren().add(toSetup);

		toSetup.setTranslateY(posY);
		toSetup.setTranslateX(posX);

		toSetup.setPrefHeight(prefHeight);
		toSetup.setPrefWidth(prefWidth);

		toSetup.setPromptText(promptText);

		return toSetup;
	}

	// Rectangle speed format
	public static Rectangle rectangle(Rectangle toSetup, Pane pane, double posX, double posY, double height,
			double width, Color fill) {

		pane.getChildren().add(toSetup);

		toSetup.setTranslateY(posY);
		toSetup.setTranslateX(posX);

		toSetup.setHeight(height);
		toSetup.setWidth(width);

		toSetup.setFill(fill);

		return toSetup;
	}

	// Rectangle speed format in moduele style used on molar mass calculator and equations page
	public static Rectangle box(Rectangle toSetup, Pane pane, double posX, double posY, double height, double width) {

		pane.getChildren().add(toSetup);

		toSetup.setHeight(height);
		toSetup.setWidth(width);

		toSetup.setTranslateX(posX);
		toSetup.setTranslateY(posY);

		toSetup.setStyle("-fx-fill: transparent; -fx-stroke: #656E72; -fx-stroke-width: 2");

		toSetup.setArcWidth(20.0);
		toSetup.setArcHeight(20.0);

		return toSetup;
	}
}