package pages;
import application.SpeedFormat;
import calculations.ChemicalMath;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Equations extends Page{
	// Instance variables for the Equations Class
	private Pane equationsPage = new Pane();
    private Scene equations = new Scene(equationsPage, 1280, 720);
    
    // Method to setup equations page
	public void setUpEquationsPage() {
        // Setup for °C to K equation module
        Rectangle kcBox = new Rectangle();
        SpeedFormat.box(kcBox, equationsPage, 40, 80, 90, 225);
        Text convertKtoC = new Text();
        SpeedFormat.text(convertKtoC, equationsPage, 50, 100, "Convert K & °C", "-fx-font-weight: bold");
        TextField kcInput = new TextField();
        SpeedFormat.textField(kcInput, equationsPage, 50, 115, 30, 150, "Input ___ °C or K");
        Text kcOutput = new Text();
        SpeedFormat.text(kcOutput, equationsPage, 50, 160, "Temperature: ", "");
        Button kcGo = new Button();
        SpeedFormat.button(kcGo, equationsPage, 205, 115, 50, 30, "Go", "");
        kcGo.setOnAction(event -> {
            if (kcInput.getText().isEmpty() == false) {
                kcOutput.setText("Temperature: " + ChemicalMath.convertCK(kcInput.getText()));
            }
        });

        // Setup for pressure conversion of pressure units
        Rectangle pcBox = new Rectangle();
        SpeedFormat.box(pcBox, equationsPage, 300, 80, 90, 480);
        Text convertP = new Text();
        SpeedFormat.text(convertP, equationsPage, 310, 100, "Convert Pressure Units", "-fx-font-weight: bold");
        TextField pInput = new TextField();
        SpeedFormat.textField(pInput, equationsPage, 310, 115, 30, 200, "Input ___ atm, mmHg, kPa, or psi");
        Text pOutput = new Text();
        SpeedFormat.text(pOutput, equationsPage, 310, 160, "Pressure: ", "");
        Button pATM = new Button();
        SpeedFormat.button(pATM, equationsPage, 515, 115, 50, 30, "atm", "");
        pATM.setOnAction(event -> {
            if (pInput.getText().isEmpty() == false) {
                pOutput.setText("Pressure: " + ChemicalMath.convertPressure(pInput.getText(), "atm"));
            }
        });
        Button pMMHG = new Button();
        SpeedFormat.button(pMMHG, equationsPage, 570, 115, 70, 30, "mmHg", "");
        pMMHG.setOnAction(event -> {
            if (pInput.getText().isEmpty() == false) {
                pOutput.setText("Pressure: " + ChemicalMath.convertPressure(pInput.getText(), "mmHg"));
            }
        });
        Button pKPA = new Button();
        SpeedFormat.button(pKPA, equationsPage, 645, 115, 70, 30, "kPa", "");
        pKPA.setOnAction(event -> {
            if (pInput.getText().isEmpty() == false) {
                pOutput.setText("Pressure: " + ChemicalMath.convertPressure(pInput.getText(), "kPa"));
            }
        });
        Button pPSI = new Button();
        SpeedFormat.button(pPSI, equationsPage, 720, 115, 50, 30, "psi", "");
        pPSI.setOnAction(event -> {
            if (pInput.getText().isEmpty() == false) {
                pOutput.setText("Pressure: " + ChemicalMath.convertPressure(pInput.getText(), "psi"));
            }
        });

        // Setup for Beer Lambert Law equation
        Rectangle blBox = new Rectangle();
        SpeedFormat.box(blBox, equationsPage, 40, 200, 120, 740);
        Text bl = new Text();
        SpeedFormat.text(bl, equationsPage, 50, 220, "Beer Lambert Law", "-fx-font-weight: bold");
        Text aorT = new Text();
        SpeedFormat.text(aorT, equationsPage, 50, 245, "A or %T", "");
        TextField taInput = new TextField();
        SpeedFormat.textField(taInput, equationsPage, 50, 250, 30, 150, "Input ___ (no units) or %");
        Text l = new Text();
        SpeedFormat.text(l, equationsPage, 230, 245, "l Path Length", "");
        Text aorTequal = new Text();
        SpeedFormat.text(aorTequal, equationsPage, 210, 270, "=", "");
        TextField lInput = new TextField();
        SpeedFormat.textField(lInput, equationsPage, 230, 250, 30, 150, "Input ___ cm or m");
        Text c = new Text();
        SpeedFormat.text(c, equationsPage, 385, 245, "c Concentration", "");
        TextField cInput = new TextField();
        SpeedFormat.textField(cInput, equationsPage, 385, 250, 30, 175, "Input ___ mol/L");
        Text e = new Text();
        SpeedFormat.text(e, equationsPage, 565, 245, "e value", "");
        TextField eInput = new TextField();
        SpeedFormat.textField(eInput, equationsPage, 565, 250, 30, 150, "Input ___ (no units)");
        Text blOut = new Text();
        SpeedFormat.text(blOut, equationsPage, 50, 295, "Leave on variable blank \nUnknown Variable Value:", "");
        Button blGo = new Button();
        SpeedFormat.button(blGo, equationsPage, 720, 250, 50, 30, "Go", "");
        blGo.setOnAction(event -> {
            blOut.setText("Leave on variable blank \nUnknown Variable Value: " + ChemicalMath
                    .beerLambertLaw(taInput.getText(), lInput.getText(), cInput.getText(), eInput.getText()));
        });

        // Setup for C1V1 = C2V2 equation
        Rectangle cBox = new Rectangle();
        SpeedFormat.box(cBox, equationsPage, 40, 350, 120, 740);
        Text cv = new Text();
        SpeedFormat.text(cv, equationsPage, 50, 370, "C1V1 = C2V2", "-fx-font-weight: bold");
        Text c1T = new Text();
        SpeedFormat.text(c1T, equationsPage, 50, 395, "C1", "");
        TextField c1Input = new TextField();
        SpeedFormat.textField(c1Input, equationsPage, 50, 400, 30, 150, "Input ___ mol/L or g/L");
        Text v1T = new Text();
        SpeedFormat.text(v1T, equationsPage, 205, 395, "V1", "");
        TextField v1Input = new TextField();
        SpeedFormat.textField(v1Input, equationsPage, 205, 400, 30, 150, "Input ___ L or mL");
        Text cvequal = new Text();
        SpeedFormat.text(cvequal, equationsPage, 378, 420, "=", "");

        Text c2T = new Text();
        SpeedFormat.text(c2T, equationsPage, 410, 395, "C2", "");
        TextField c2Input = new TextField();
        SpeedFormat.textField(c2Input, equationsPage, 410, 400, 30, 150, "Input ___ mol/L or g/L");
        Text v2T = new Text();
        SpeedFormat.text(v2T, equationsPage, 565, 395, "V2", "");
        TextField v2Input = new TextField();
        SpeedFormat.textField(v2Input, equationsPage, 565, 400, 30, 150, "Input ___ L or mL");

        Text cvOut = new Text();
        SpeedFormat.text(cvOut, equationsPage, 50, 445, "Leave on variable blank \nUnknown Variable Value:", "");
        Button cvGo = new Button();
        SpeedFormat.button(cvGo, equationsPage, 720, 400, 50, 30, "Go", "");
        cvGo.setOnAction(event -> {
            cvOut.setText("Leave on variable blank \nUnknown Variable Value: " + ChemicalMath.findVolumeOrConentration(
                    c1Input.getText(), v1Input.getText(), c2Input.getText(), v2Input.getText()));
        });
    }
	
	// Pane getter for the Equations Class -- Overridden from the Page abstract class
	@Override
	public Pane getPage() {
    		return equationsPage;
    }
	
	// Scene getter for the Equations Class -- Overridden from the Page abstract class
	@Override
    public Scene getScene() {
    		return equations;
    }
	
	// Scene changer for the Equations Class -- Overridden from the Page abstract class
	@Override
	public void changePage() {
		primaryStage.setScene(equations);
	}
}
