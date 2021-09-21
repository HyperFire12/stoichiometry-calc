package pages;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import application.SpeedFormat;
import calculations.Chemical;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Table extends Page {
	// Instance variables for the Table Class
    private Pane tablePage = new Pane();
    private Scene table = new Scene(tablePage, 1280, 720);
    private Text tableChemicalFormulaOutput = new Text("Molar Mass: ");
    private int[] countsNum = new int[110];
    private int x = 100, y = 75;
    private double mm = 0;
    // Memory stored in ArrayLists
    private ArrayList<Button> tableButton = new ArrayList<>();
    private ArrayList<Button> plusMinus = new ArrayList<>();
    private ArrayList<Text> counts = new ArrayList<>();
    private ArrayList<Text> past = new ArrayList<>();
    private ArrayList<Chemical> chemicals = new ArrayList<>();

    // Method to setup periodic table on molar mass page
    public void setUpTablePage() {
        setUpTable();
        manualAdd();

        Rectangle back = new Rectangle();
        SpeedFormat.rectangle(back, tablePage, 1100, 0, 1280, 200, Color.LIGHTGREY);

        Rectangle chemicalFormulaMod = new Rectangle();
        SpeedFormat.box(chemicalFormulaMod, tablePage, x + 275, y + 525, 100, 290);

        Rectangle tableMod = new Rectangle();
        SpeedFormat.box(tableMod, tablePage, x, y + 525, 100, chemicalFormulaMod.getTranslateX() - x - 25);

        Text chemicalFormulaOutput = new Text("Molar Mass: ");
        SpeedFormat.text(chemicalFormulaOutput, tablePage, chemicalFormulaMod.getTranslateX() + 15,
                chemicalFormulaMod.getTranslateY() + 82, "Molar Mass: ", "");

        Text tableChemicalFormulaInputTitle = new Text();
        SpeedFormat.text(tableChemicalFormulaInputTitle, tablePage, tableMod.getTranslateX() + 15,
                tableMod.getTranslateY() + 25, "Input Chemical Formula In Table", "-fx-font-weight: bold");
        Text previousTitle = new Text();
        SpeedFormat.text(previousTitle, tablePage, 1115, 80, "Previous Molar Masses", "-fx-font-weight: bold");

        Text chemicalFormulaInputTitle = new Text();
        SpeedFormat.text(chemicalFormulaInputTitle, tablePage, chemicalFormulaMod.getTranslateX() + 15,
                chemicalFormulaMod.getTranslateY() + 25, "Input Chemical Formula", "-fx-font-weight: bold");

        TextField chemicalFormulaInput = new TextField();
        SpeedFormat.textField(chemicalFormulaInput, tablePage, chemicalFormulaMod.getTranslateX() + 15,
                chemicalFormulaMod.getTranslateY() + 35, 30, 200);

        SpeedFormat.text(tableChemicalFormulaOutput, tablePage, tableMod.getTranslateX() + 15,
                chemicalFormulaOutput.getTranslateY(), "Molar Mass: ", "");

        Button enter = new Button();
        SpeedFormat.button(enter, tablePage, chemicalFormulaMod.getTranslateX() + 225,
                chemicalFormulaMod.getTranslateY() + 35, 50, 30, "Go", null);

        // When button is pressed molar mass of new chemical input
        enter.setOnAction(event -> {
            // If there is no input don't run
            if (chemicalFormulaInput.getText().isEmpty() == false) {
                // Add new chemical to arraylist of chemicals
                chemicals.add(new Chemical(chemicalFormulaInput.getText()));
                // If the chemical is a valid chemical run output
                if (chemicals.get(chemicals.size() - 1).checkIfValid()) {
                    // Add new chemical to past chemicals list
                    addPast(chemicals.get(chemicals.size() - 1).getFormula(),
                            chemicals.get(chemicals.size() - 1).getMolarMass());
                    // Set molar mass to the output
                    chemicalFormulaOutput.setText("Molar Mass: "
                            + Double.toString(chemicals.get(chemicals.size() - 1).getMolarMass())
                                    .substring(0, Math.min(Double.toString(chemicals.get(chemicals.size() - 1)
                                    		.getMolarMass()).length(), 9))
                            + " g/mol");
                    back.toBack();
                }
                // If the formula is invalid
                else {
                    chemicalFormulaOutput.setText("Molar Mass: INVALID INPUT");
                    // Remove the chemical from the arraylist
                    chemicals.remove(chemicals.size() - 1);
                    back.toBack();
                }
            }
        });

        Button clear = new Button();
        SpeedFormat.button(clear, tablePage, tableMod.getTranslateX() + 15, chemicalFormulaInput.getTranslateY(),
                tableMod.getTranslateX() - x + tableMod.getWidth() - 30, 30, "Clear Table & Record", null);

        // When the button is pressed find the formula and molar mass of chemical from periodic table
        clear.setOnAction(event -> {
            if (mm != 0) {
                addPast(getFormula(), mm);
                for (int i = 1; i < 110; i++) {
                    countsNum[i] = 0;
                    mm = 0;
                    counts.get(i).setText("0");
                    tableChemicalFormulaOutput.setText("Molar Mass: ");
                }
                back.toBack();
            }
        });

        changePage();
    }

    // Sets up periodic table of buttons
    private void setUpTable() {
        // Adds new unused button so arraylist numbers are chemical numbers
        tableButton.add(new Button());
        int x2 = x + 150, x3 = x + 150, xOriginal = x, yOriginal = y;
        // Adds all the elements to the arraylist of numbers
        for (int j = 1; j < 110; j++) {
            tableButton.add(new Button());
            tablePage.getChildren().add(tableButton.get(j));
            // Disable buttons since they have no function other than visual
            tableButton.get(j).setDisable(true);
            tableButton.get(j).setOpacity(1);
        }
        // Sets all the elements names
        setElements();
        // Loop sets all style setting for buttons
        for (int i = 1; i < 110; i++) {
            tableButton.get(i).setPrefHeight(50);
            tableButton.get(i).setPrefWidth(50);
            tableButton.get(i).setFont(Font.font("Lucida Grande"));
            /* If statements set placement of elements in table adjusting for table
            placement since table isnt all the same */
            if (i == 5 || i == 13) {
                x += 500;
            }
            if (i == 2) {
                tableButton.get(i).setTranslateX(x + 50 * 16);
                tableButton.get(i).setTranslateY(y);
            } else if (i >= 58 && i <= 71) {
                tableButton.get(i).setTranslateX(x2);
                x2 += 50;
                tableButton.get(i).setTranslateY(y + 150);
            } else if (i >= 90 && i <= 103) {
                tableButton.get(i).setTranslateX(x3);
                x3 += 50;
                tableButton.get(i).setTranslateY(y + 150);
            } else {
                tableButton.get(i).setTranslateX(x);
                x += 50;
                tableButton.get(i).setTranslateY(y);
            }
            if (i == 2 || i == 10 || i == 18 || i == 36 || i == 54 || i == 86) {
                y += 50;
                x = xOriginal;
            }
        }
        x = xOriginal;
        y = yOriginal;
    }

    // Method sets name of elements and also sets colour based on element family
    private void setElements() {
        String line = "";
        int i = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("periodicIndex.csv"))) {
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] contents = line.split(",");
                /* Sets array values as comma separated values, sets chemical name to csv file
                string */
                tableButton.get(i).setText(contents[0]);
                // Checks family of chemical to set colour
                String ref = contents[2];
                switch (ref) {
                    case "alkaliMetal":
                        tableButton.get(i).setStyle(
                                "-fx-background-color: #9A2E94; -fx-border-color: #000000; -fx-border-width: 1px; "
                                + "-fx-background-radius: 0; -fx-text-fill: #000000; -fx-font-weight: bold");
                        break;
                    case "alkalineEarthMetal":
                        tableButton.get(i).setStyle(
                                "-fx-background-color: #C53656; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                                + "-fx-text-fill: #000000; -fx-font-weight: bold");
                        break;
                    case "lanthanide":
                        tableButton.get(i).setStyle(
                                "-fx-background-color: #B83928; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                                + "-fx-text-fill: #000000; -fx-font-weight: bold");
                        break;
                    case "actinide":
                        tableButton.get(i).setStyle(
                                "-fx-background-color: #B75621; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                                + "-fx-text-fill: #000000; -fx-font-weight: bold");
                        break;
                    case "transitionMetal":
                        tableButton.get(i).setStyle(
                                "-fx-background-color: #F5BC44; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                                + "-fx-text-fill: #000000; -fx-font-weight: bold");
                        break;
                    case "post-transitionMetal":
                        tableButton.get(i).setStyle(
                                "-fx-background-color: #3B8249; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                                + "-fx-text-fill: #000000; -fx-font-weight: bold");
                        break;
                    case "metalloid":
                        tableButton.get(i).setStyle(
                                "-fx-background-color: #377E7A; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                                + "-fx-text-fill: #000000; -fx-font-weight: bold");
                        break;
                    case "otherNonMetal":
                        tableButton.get(i).setStyle(
                                "-fx-background-color: #3173CF; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                                + "-fx-text-fill: #000000; -fx-font-weight: bold");
                        break;
                    case "nobleGas":
                        tableButton.get(i).setStyle(
                                "-fx-background-color: #ff0000; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                                + "-fx-text-fill: #000000; -fx-font-weight: bold");
                        break;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Setup for plus and minus buttons on elements to manually add molar mass
    private void manualAdd() {
        // Adds blank button so chemical numbers are same as array numbers
        plusMinus.add(new Button());
        counts.add(new Text());
        // Loop adds all + buttons to elements based on elements positions
        for (int i = 1; i < 110; i++) {
            plusMinus.add(new Button());
            counts.add(new Text());
            countsNum[i] = 0;
            tablePage.getChildren().add(plusMinus.get(i));
            tablePage.getChildren().add(counts.get(i));
            plusMinus.get(i).setText("+");
            counts.get(i).setText("" + countsNum[i]);
            counts.get(i).setFont(Font.font("Lucida Grande"));
            plusMinus.get(i).setPrefHeight(15);
            plusMinus.get(i).setPrefWidth(15);
            plusMinus.get(i).setPadding(new Insets(0, 0, 0, 0));
            plusMinus.get(i).setTranslateX(tableButton.get(i).getTranslateX());
            plusMinus.get(i).setTranslateY(tableButton.get(i).getTranslateY() + 33);
            counts.get(i).setTranslateY(tableButton.get(i).getTranslateY() + 45);
            counts.get(i).setTranslateX(tableButton.get(i).getTranslateX() + 21);
            String text = tableButton.get(i).getText();
            int num = i;
            // When button is pressed increase molar mass by said elements molar mass
            plusMinus.get(i).setOnAction(event -> {
                mm += Chemical.findElement(text);
                tableChemicalFormulaOutput.setText("Molar Mass: "
                        + Double.toString(mm).substring(0, Math.min(Double.toString(mm).length(), 9)) + " g/mol");
                countsNum[num] += 1;
                counts.get(num).setText("" + countsNum[num]);
                if (countsNum[num] > 9) {
                    counts.get(num).setTranslateX(tableButton.get(num).getTranslateX() + 17);
                } else {
                    counts.get(num).setTranslateX(tableButton.get(num).getTranslateX() + 21);
                }

            });
            plusMinus.get(i).setStyle("-fx-font-size: 12px; ");
            plusMinus.get(i).setStyle(
                    "-fx-font-size: 12px; -fx-background-color: #F6F6F6; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                    + "-fx-text-fill: #000000");
        }

        // Loop adds all - buttons to elements based on elements positions
        for (int j = 1; j < 110; j++) {
            plusMinus.add(new Button());
            // 109 offset placed since all - buttons are after +
            tablePage.getChildren().add(plusMinus.get(109 + j));
            plusMinus.get(109 + j).setText("-");
            plusMinus.get(109 + j).setPrefHeight(15);
            plusMinus.get(109 + j).setPrefWidth(15);
            plusMinus.get(109 + j).setPadding(new Insets(0, 0, 0, 0));
            plusMinus.get(109 + j).setTranslateX(tableButton.get(j).getTranslateX() + 35);
            plusMinus.get(109 + j).setTranslateY(tableButton.get(j).getTranslateY() + 33);
            String text = tableButton.get(j).getText();
            int num = j;

            // when button is pressed decrease molar mass by said elements molar mass
            plusMinus.get(109 + j).setOnAction(event -> {
                if (countsNum[num] != 0) {
                    countsNum[num] -= 1;
                    mm -= Chemical.findElement(text);
                    if (mm <= 0.1) {
                        mm = 0;
                    }
                }
                tableChemicalFormulaOutput.setText("Molar Mass: "
                        + Double.toString(mm).substring(0, Math.min(Double.toString(mm).length(), 9)) + " g/mol");
                counts.get(num).setText("" + countsNum[num]);
                if (countsNum[num] > 9) {
                    counts.get(num).setTranslateX(tableButton.get(num).getTranslateX() + 17);
                } else {
                    counts.get(num).setTranslateX(tableButton.get(num).getTranslateX() + 21);
                }
            });
            plusMinus.get(109 + j).setStyle(
                    "-fx-font-size: 12px; -fx-background-color: #656E72; -fx-border-color: #000000; -fx-border-width: 1px; -fx-background-radius: 0; "
                    + "-fx-text-fill: #000000");
        }

    }

    // Method gets formula made by manual molar mass input
    private String getFormula() {
        String formula = "";
        String letter = "";
        String number = "";

        for (int i = 1; i < 110; i++) {
            letter = tableButton.get(i).getText();
            // Adds number of certain element to array of elements to make up formula
            number = "" + countsNum[i];
            /* If element is present in compound but only has 1 atom, add element to formula
            but do not add 1 for formatting */
            if (countsNum[i] != 0 && countsNum[i] == 1) {
                formula = formula + letter;
            }
            // If element is present in compound add element to formula plus its multiplier
            else if (countsNum[i] != 0) {
                formula = formula + letter + number;
            }
        }
        return formula;
    }

    // Method when called adds chemical formula and its molar mass to previous table
    private void addPast(String name, double num) {
        past.add(new Text(name + "\n" + Double.toString(num).substring(0, Math.min(Double.toString(num).length(), 9))
                + " g/mol"));
        // If there is more than one previous calculation add new calculation
        if (past.size() > 1) {
            tablePage.getChildren().add(past.get(past.size() - 1));
            past.get(past.size() - 1).setTranslateX(190 + +925);
            past.get(past.size() - 1).setFont(Font.font("Lucida Grande", 12));
            // Format previous calculations in order of newest to olders
            for (int i = past.size() - 1; i >= 0; i--) {
                past.get(i).setTranslateY(125 + (past.size() - (i + 1)) * 50);
            }
        }
        // If there are no previos calculations add calculation to table
        else {
            past.get(0).setFont(Font.font("Lucida Grande", 12));
            past.get(0).setTranslateX(190 + 925);
            past.get(0).setTranslateY(125);
            tablePage.getChildren().add(past.get(0));
        }
    }

    // Pane getter for the Table Class -- Overridden from the Page abstract class
    @Override
    public Pane getPage() {
        return tablePage;
    }

    // Scene getter for the Table Class -- Overridden from the Page abstract class
    @Override
    public Scene getScene() {
        return table;
    }

    // Scene changer for the Table Class -- Overridden from the Page abstract class
    @Override
    public void changePage() {
        primaryStage.setScene(table);
    }

}
