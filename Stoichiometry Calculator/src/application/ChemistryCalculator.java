package application;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pages.Balancer;
import pages.Equations;
import pages.Instructions;
import pages.Page;
import pages.Table;

public class ChemistryCalculator extends Application {
	// TODO: By Aidan Maunder & Chris Leung
    @Override
    public void start(Stage primaryStage) {
    		Page.setStage(primaryStage);
    		// Creates new instances of the different page classes we created
    		Table table = new Table();
    		table.setUpTablePage();
    		Balancer balancer = new Balancer();
    		balancer.setUpBalancerPage();
    		Equations equations = new Equations();
    		equations.setUpEquationsPage();
    		Instructions instructions = new Instructions();
    		instructions.setUpInstructionsPage();
    		
    		// Runs the javafx to the stage
        primaryStage.show();
        primaryStage.setTitle("Chemistry Calculator");
        primaryStage.setResizable(false);

        // Creates the different page buttons
        Button molarMassPageButton = new Button();
        Button balancePageButton = new Button();
        Button equationPageButton = new Button();
        Button instructionsPageButton = new Button();

        // Sets up the molar mass page toggle button
        SpeedFormat.button(molarMassPageButton, table.getPage(), 0, 0, 320, 50, "Molar Mass Calculator",
                "-fx-background-color: #355C7D; -fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-background-radius: 0em;");
        molarMassPageButton.setOnAction(event -> {
            if (primaryStage.getScene() != table.getScene()) {
                table.changePage();
                table.getPage().getChildren().add(molarMassPageButton);
                table.getPage().getChildren().add(balancePageButton);
                table.getPage().getChildren().add(equationPageButton);
                table.getPage().getChildren().add(instructionsPageButton);
            }
        });

        // Sets up the stoichiometry calculator toggle button
        SpeedFormat.button(balancePageButton, table.getPage(), 320, 0, 320, 50, "Stoichiometry Calculator",
                "-fx-background-color: #6C5B7B; -fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-background-radius: 0em;");
        balancePageButton.setOnAction(event -> {
            if (primaryStage.getScene() != balancer.getScene()) {
                balancer.changePage();
                balancer.getPage().getChildren().add(molarMassPageButton);
                balancer.getPage().getChildren().add(balancePageButton);
                balancer.getPage().getChildren().add(equationPageButton);
                balancer.getPage().getChildren().add(instructionsPageButton);
            }
        });

        // Sets up the equation page toggle button
        SpeedFormat.button(equationPageButton, table.getPage(), 640, 0, 320, 50, "Equations",
                "-fx-background-color: #C06C84; -fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-background-radius: 0em;");
        equationPageButton.setOnAction(event -> {
            if (primaryStage.getScene() != equations.getScene()) {
            	    equations.changePage();
                equations.getPage().getChildren().add(molarMassPageButton);
                equations.getPage().getChildren().add(balancePageButton);
                equations.getPage().getChildren().add(equationPageButton);
                equations.getPage().getChildren().add(instructionsPageButton);
            }
        });

        // Sets up the instructions page toggle button
        SpeedFormat.button(instructionsPageButton, table.getPage(), 960, 0, 320, 50, "Instructions",
                "-fx-background-color: #F67280; -fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-background-radius: 0em;");
        instructionsPageButton.setOnAction(event -> {
            if (primaryStage.getScene() != instructions.getScene()) {
                instructions.changePage();
                instructions.getPage().getChildren().add(molarMassPageButton);
                instructions.getPage().getChildren().add(balancePageButton);
                instructions.getPage().getChildren().add(equationPageButton);
                instructions.getPage().getChildren().add(instructionsPageButton);
            }
        });
    }

    public static void main(final String[] args) {
        launch(args);
    }
}