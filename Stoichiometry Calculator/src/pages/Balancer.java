package pages;
import java.text.DecimalFormat;
import java.util.ArrayList;

import application.SpeedFormat;
import calculations.Chemical;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Balancer extends Page{
	// Instance variables for the Balancer Class
	private Pane balancerPage = new Pane();
    private Scene balancer = new Scene(balancerPage, 1280, 720);
    // Memory stored in ArrayLists
    private ArrayList<TextField> reactantTextAmt = new ArrayList<>();
	private ArrayList<TextField> productTextAmt = new ArrayList<>();
	private ArrayList<TextField> reactant = new ArrayList<>();
	private ArrayList<TextField> product = new ArrayList<>();
	private ArrayList<TextField> reactantMol = new ArrayList<>();
	private ArrayList<TextField> productMol = new ArrayList<>();
	private ArrayList<Text> plusReactant = new ArrayList<>();
	private ArrayList<Text> plusProduct = new ArrayList<>();
	// Memory stored in Arrays
	private int[] reactantAmt = {0,0,0,0,0};
	private int[] productAmt = {0,0,0,0,0};
	private double[] reactantMM = {0,0,0,0,0};
	private double[] productMM = {0,0,0,0,0};
	private double[] reactantMoles = {0,0,0,0,0};
	private double[] productMoles = {0,0,0,0,0};
	private double[] finalMolReactant = {0,0,0,0,0};
	private double[] finalMolProduct = {0,0,0,0,0};
	private Text[] rMol = new Text[5];
	private Text[] rGrams = new Text[5];
	private Text[] pMol = new Text[5];
	private Text[] pGrams = new Text[5];
	// Changes with the amount of sets of textfields
	private int intReactant = 1;
	private int intProduct = 1;
	// Formats to a certain amount of decimals
	private DecimalFormat numberFormat = new DecimalFormat("#0.0000");
	
	// Resets all the stored memory to the initial state
	private void reset() {
		for(int i = 0; i < 5; i++) {
			reactantAmt[i] = 0;
			productAmt[i] = 0;
			reactantMM[i] = 0;
			productMM[i] = 0;
			reactantMoles[i] = 0;
			productMoles[i] = 0;
			finalMolReactant[i] = 0;
			finalMolProduct[i] = 0;
			remove();
		}
	}
    
	// Sets all of the text from the calculated text to nothing to remove any text from past calculations
	private void remove() {
    		for(int i = 0; i < 5; i++) {
    			rMol[i].setText("");
    			rGrams[i].setText("");
    			pMol[i].setText("");
    			pGrams[i].setText("");
    		}
    }
	
	// Returns the int in a given textfield even if there is non-numerical characters
	private String intChecker(String s) {
		String newS = "";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) > 47 && s.charAt(i) < 58) {
				newS += s.charAt(i);
			}
		}
		return newS;
	}
	
	// Returns the double in a given textfield even if there is non-numerical characters
	private String doubleChecker(String s) {
		String newS = "";
		for(int i = 0; i < s.length(); i++) {
			if((s.charAt(i) > 47 && s.charAt(i) < 58) || s.charAt(i) == '.') {
				newS += s.charAt(i);
			}
		}
		return newS;
	}
	
	// Calculates the limiting moles amount
	private void molCalculator() {
		double finalMolRMultiplier = 100000000;
		int finalMolRIndex = 0;
		double finalMolPMultiplier = 100000000;
		int finalMolPIndex = 0;
		double finalMol = 0;
		int finalMolAmt = 0;

		/* Finds the limiting moles on the reactant side and sets the # textfield to its assumed value 
		in case of non-numerical values */
		for (int i = 0; i < intReactant; i++) {
			if (reactantMM[i] > 0) {
				if (reactantAmt[i] == 0) {
					reactantTextAmt.get(i).setText("1");
					reactantAmt[i] = 1;
				}
				reactantTextAmt.get(i).setText(reactantAmt[i] + "");
				if (!(reactantMoles[i] == 0)) {
					double temp = reactantMoles[i] / reactantAmt[i];
					if (finalMolRMultiplier > temp) {
						finalMolRMultiplier = temp;
						finalMolRIndex = i;
					}
				}
			}
		}

		/* Finds the limiting moles on the product side and sets the # textfield to its assumed value 
		in case of non-numerical values */
		for (int i = 0; i < intProduct; i++) {
			if (productMM[i] > 0) {
				if (productAmt[i] == 0) {
					productTextAmt.get(i).setText("1");
					productAmt[i] = 1;
				}
				productTextAmt.get(i).setText(productAmt[i] + "");
				if (!(productMoles[i] == 0)) {
					double temp = productMoles[i] / productAmt[i];
					if (finalMolPMultiplier > temp) {
						finalMolPMultiplier = temp;
						finalMolPIndex = i;
					}
				}
			}
		}

		// Finds the final limiting moles
		if (finalMolRMultiplier > finalMolPMultiplier) {
			finalMol = productMoles[finalMolPIndex];
			finalMolAmt = productAmt[finalMolPIndex];
		} else {
			finalMol = reactantMoles[finalMolRIndex];
			finalMolAmt = reactantAmt[finalMolRIndex];
		}

		// Stores the final limiting moles of each reactant
		for (int i = 0; i < intReactant; i++) {
			if (reactantMM[i] > 0) {
				// Tries to find the final mol on the reactant side but if the calculation is NaN, don't divide
				try {
					finalMolReactant[i] = Double.parseDouble(numberFormat.format((reactantAmt[i] * finalMol) / finalMolAmt));
				} catch(Exception e) {
					finalMolAmt = 1;
					finalMolReactant[i] = Double.parseDouble(numberFormat.format(reactantAmt[i] * finalMol));
				}
			}
		}

		// Stores the final limiting moles of each product
		for (int i = 0; i < intProduct; i++) {
			if (productMM[i] > 0) {
				// Tries to find the final mol on the product side but if the calculation is NaN, don't divide
				try {
					finalMolProduct[i] = Double.parseDouble(numberFormat.format((productAmt[i] * finalMol) / finalMolAmt));
				} catch(Exception e) {
					finalMolProduct[i] = Double.parseDouble(numberFormat.format(productAmt[i] * finalMol));
				}
			}
		}
	}
	
	// Sets the calculated text to the amount of moles and grams a given formula has 
	private void end() {
		for(int i = 0; i < intReactant; i++) {
			if(reactantMM[i] > 0) {
				rMol[i].setText(finalMolReactant[i] + "mol");
				rGrams[i].setText(numberFormat.format(reactantMM[i] * finalMolReactant[i]) + "g");
			}
		}
		for(int i = 0; i < intProduct; i++) {
			if(productMM[i] > 0) {
				pMol[i].setText(finalMolProduct[i] + "mol");
				pGrams[i].setText(numberFormat.format(productMM[i] * finalMolProduct[i]) + "g");
			}
		}
	}
	
	// Creates the javafx for the balancer page 
	public void setUpBalancerPage() {
		// Reactant Side
		/* Creates the initial set of textfields that allows for the user to input the data required 
		for the reactant side */
		reactantTextAmt.add(SpeedFormat.textField(new TextField(), balancerPage, 10, 150, 0, 35, "#"));
		reactant.add(SpeedFormat.textField(new TextField(), balancerPage, 50, 150, 0, 100, "Formula"));
		reactantMol.add(SpeedFormat.textField(new TextField(), balancerPage, 10, 185, 0, 140, "Moles"));		

		Button addReactant = SpeedFormat.button(new Button(), balancerPage, 10, 80, "Add", "");
		
		/* Creates an add button that adds more sets of textfields that allow for 
		the user to input the data required for the reactant side */
		addReactant.setOnAction(action -> {
			if (intReactant < 5) {
				reactantTextAmt.add(SpeedFormat.textField(new TextField(), balancerPage, 10 + (intReactant * 165), 150, 35, "#"));
				reactant.add(SpeedFormat.textField(new TextField(), balancerPage, 50 + (intReactant * 165), 150, 100, "Formula"));
				reactantMol.add(SpeedFormat.textField(new TextField(), balancerPage, 10 + (intReactant * 165), 185, 140, "Moles"));

				plusReactant.add(SpeedFormat.text(new Text(), balancerPage, 155 + ((intReactant - 1) * 165), 170, "+", ""));
				plusReactant.get(plusReactant.size() - 1).setFont(Font.font("verdana", FontPosture.REGULAR, 20));

				intReactant++;
			}
		});

		// Creates a remove button that removes the sets of textfields from the reactant side
		Button removeReactant = SpeedFormat.button(new Button(), balancerPage, 60, 80, "Remove", "");

		removeReactant.setOnAction(action -> {
			if (intReactant > 1) {
				remove();
				balancerPage.getChildren().removeAll(reactantTextAmt.get(reactantTextAmt.size() - 1), reactant.get(reactant.size() - 1), 
						reactantMol.get(reactantMol.size() - 1), plusReactant.get(plusReactant.size() - 1));
				reactantTextAmt.remove(reactantTextAmt.size() - 1);
				reactant.remove(reactant.size() - 1);
				reactantMol.remove(reactantMol.size() - 1);
				plusReactant.remove(plusReactant.size() - 1);
				intReactant--;
			}
		});

		// Creates the Equation Arrow Text
		Text equationArrow = SpeedFormat.text(new Text(), balancerPage, 50, 290, "↓", "");
		equationArrow.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));

		// Product Side
		/* Creates the initial set of textfields that allows for the user to input the data required 
		for the product side */
		productTextAmt.add(SpeedFormat.textField(new TextField(), balancerPage, 10, 400, 35, "#"));
		product.add(SpeedFormat.textField(new TextField(), balancerPage, 50, 400, 100, "Formula"));
		productMol.add(SpeedFormat.textField(new TextField(), balancerPage, 10, 435, 140, "Moles"));

		/* Creates an add button that adds more sets of textfields that allow for 
		the user to input the data required for the product side */
		Button addProduct = SpeedFormat.button(new Button(), balancerPage, 10, 330, "Add", "");

		addProduct.setOnAction(action -> {
			if (intProduct < 5) {
				productTextAmt.add(SpeedFormat.textField(new TextField(), balancerPage, 10 + (intProduct * 165), 400, 35, "#"));
				product.add(SpeedFormat.textField(new TextField(), balancerPage, 50 + (intProduct * 165), 400, 100, "Formula"));
				productMol.add(SpeedFormat.textField(new TextField(), balancerPage, 10 + (intProduct * 165), 435, 140, "Moles"));

				plusProduct.add(SpeedFormat.text(new Text(), balancerPage, 155 + ((intProduct - 1) * 165), 420, "+", ""));
				plusProduct.get(plusProduct.size() - 1).setFont(Font.font("verdana", FontPosture.REGULAR, 20));

				intProduct++;
			}
		});

		// Creates a remove button that removes the sets of textfields from the product side
		Button removeProduct = SpeedFormat.button(new Button(), balancerPage, 60, 330, "Remove", "");

		removeProduct.setOnAction(action -> {
			if (intProduct > 1) {
				remove();
				balancerPage.getChildren().removeAll(productTextAmt.get(productTextAmt.size() - 1), product.get(product.size() - 1), 
						productMol.get(productMol.size() - 1), plusProduct.get(plusProduct.size() - 1));
				productTextAmt.remove(productTextAmt.size() - 1);
				product.remove(product.size() - 1);
				productMol.remove(productMol.size() - 1);
				plusProduct.remove(product.size() - 1);
				intProduct--;
			}
		});
		
		// Creates the calculated texts objects
		for(int i = 0; i < 5; i++) {
			rMol[i] = SpeedFormat.text(new Text(), balancerPage, 50 + (i * 165), 230, "", "");
			rGrams[i] = SpeedFormat.text(new Text(), balancerPage, 50 + (i * 165), 250, "", "");
			pMol[i] = SpeedFormat.text(new Text(), balancerPage, 50 + (i * 165), 480, "", "");
			pGrams[i] = SpeedFormat.text(new Text(), balancerPage, 50 + (i * 165), 500, "", "");
		}
		
		/* Creates a button that will calculate the limiting moles and sets the end text 
		to the moles and grams of a given formula */
		Button go = SpeedFormat.button(new Button(), balancerPage, 10, 520, "Go", "");	
		
		go.setOnAction(action -> {
			reset();
			for(int i = 0; i < intReactant; i++) {
				if(!intChecker(reactantTextAmt.get(i).getText()).equals("")) {
					reactantAmt[i] = Integer.parseInt(intChecker(reactantTextAmt.get(i).getText()));
				}
			}
			
			for(int i = 0; i < intReactant; i++) {
				Chemical chemR = new Chemical(reactant.get(i).getText());
				reactantMM[i] = chemR.getMolarMass();
			}
			
			for(int i = 0; i < intReactant; i++) {
				if(!doubleChecker(reactantMol.get(i).getText()).equals("")) {
					reactantMoles[i] = Double.parseDouble(doubleChecker(reactantMol.get(i).getText()));
				}
			}
			
			for(int j = 0; j < intProduct; j++) {
				if(!intChecker(productTextAmt.get(j).getText()).equals("")) {
					productAmt[j] = Integer.parseInt(intChecker(productTextAmt.get(j).getText()));
				}
			}
			
			for(int j = 0; j < intProduct; j++) {
				Chemical chemP = new Chemical(product.get(j).getText());
				productMM[j] = chemP.getMolarMass();
			}
			
			for(int j = 0; j < intProduct; j++) {
				if(!doubleChecker(productMol.get(j).getText()).equals("")) {
					productMoles[j] = Double.parseDouble(doubleChecker(productMol.get(j).getText()));	
				}
			}
			
			molCalculator();
			end();
		});
		
		// Creates a button that will clear all of the textfields
		Button clear = SpeedFormat.button(new Button(), balancerPage, 50, 520, "Clear", "");	
		
		clear.setOnAction(action -> {
			reset();
			for(int i = 0; i < intReactant; i++) {
				reactantTextAmt.get(i).clear();
				reactant.get(i).clear();
				reactantMol.get(i).clear();
			}
			
			for(int i = 0; i < intProduct; i++) {
				productTextAmt.get(i).clear();
				product.get(i).clear();
				productMol.get(i).clear();
			}
		});
	}
	
	// Pane getter for the Balancer Class -- Overridden from the Page abstract class
	@Override
    public Pane getPage() {
    		return balancerPage;
    }
	
	// Scene getter for the Balancer Class -- Overridden from the Page abstract class
	@Override
    public Scene getScene() {
    		return balancer;
    }
	
	// Scene changer for the Balancer Class -- Overridden from the Page abstract class
	@Override
	public void changePage() {
		primaryStage.setScene(balancer);
	}
}
