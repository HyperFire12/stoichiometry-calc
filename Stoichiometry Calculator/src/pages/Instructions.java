package pages;
import application.SpeedFormat;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Instructions extends Page {
	// Instance variables for the Instructions Class
	private Pane instructionsPage = new Pane();
	private Scene instructions = new Scene(instructionsPage, 1280, 720);

	// Runs instructions page setup
	public void setUpInstructionsPage() {
		Text molarMassTitle = new Text();
		SpeedFormat.text(molarMassTitle, instructionsPage, 50, 100, "Molar Mass Calculator Instructions",
				"-fx-font-weight: bold");
		Text molarMassText = new Text();
		SpeedFormat.text(molarMassText, instructionsPage, 50, 115,
				"The Molar Mass page functions as a way find the molar mass of a given chemical from its chemical equation or a manually inputed chemical\n\n"
				+ "For a manual molar mass calculation using the periodic table the molar mass output is accompained by a sample formula for said eqaution\n   "
				+ "- this however does NOT make the actual chemical formula for the inputed molar mass\n\n"
				+ "Inputing a chemical formula text into the chemical formula box calculates the molar mass based on the given chemical. "
				+ "The calculator will only output a molar mass for a chemical that\n   - uses all real elements no spelling mistakes\n   "
				+ "- all sets of brackets have an accompanying multiplier\n   - all numbers have no decimal\n   "
				+ "- all brackets are members of a mathcing set", "");

		Text stoichTitle = new Text();
		SpeedFormat.text(stoichTitle, instructionsPage, 50, 275, "Stoichiometry Calculator Instructions",
				"-fx-font-weight: bold");
		Text stoichText = new Text();
		SpeedFormat.text(stoichText, instructionsPage, 50, 290,
				"The Stoichiometry Calculator page functions as a way find the quantity of moles and grams of said chemical in a chemcial equation\n"
				+ "The calculator does not balance moles and requires a balanced chemical equation with the quantity of moles of one chemical\n"
				+ "If there are two or more inputted quantity of moles for different chemicals, "
				+ "the program will find the limiting reagent when making its calculation\n\nThe calculator will only output data when\n   "
				+ "- all chemicals are input and have valid formula (see molar mass instructions for more details)\n   "
				+ "- at least one quantity of moles has been inputed", "");

		Text equationsTitle = new Text();
		SpeedFormat.text(equationsTitle, instructionsPage, 50, 410, "Stoichiometry Calculator Instructions",
				"-fx-font-weight: bold");
		Text equationsText = new Text();
		SpeedFormat.text(equationsText, instructionsPage, 50, 425,
				"The Equations page has many different functions\nFor all calculations to function in the page they require the units specified "
				+ "in their respective textfields\n\nFor the calculator to give an output, there are\n   - valid units in input\n   "
				+ "- no spaces after units\n   - only decimal numbers (no scientific notation or brackets)","");

		Text disclaimerTitle = new Text();
		SpeedFormat.text(disclaimerTitle, instructionsPage, 50, 610, "**Disclaimer", "-fx-font-weight: bold");
		Text disclaimerText = new Text();
		SpeedFormat.text(disclaimerText, instructionsPage, 50, 625,
				"All calculations in this calculator are purely math based and do not reflect the possibilies or impossibilites of physical chemistry","");

		Text names = new Text();
		SpeedFormat.text(names, instructionsPage, 50, 700,
				"Calculator by: Aidan Maunder & Chris Leung","");
	}

	// Pane getter for the Instructions Class -- Overridden from the Page abstract class
	@Override
	public Pane getPage() {
		return instructionsPage;
	}

	// Scene getter for the Instructions Class -- Overridden from the Page abstract class
	@Override
	public Scene getScene() {
		return instructions;
	}

	// Scene changer for the Instructions Class -- Overridden from the Page abstract class
	@Override
	public void changePage() {
		primaryStage.setScene(instructions);
	}
}
