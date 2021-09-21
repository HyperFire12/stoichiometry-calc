package calculations;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Chemical {
	// Instance variables for the Chemical Class
    private double molarMass = -1.0;
    private String chemicalFormula;

    // Object constructor
    public Chemical(String formula) {
        chemicalFormula = formula;
    }

    // Method returns private chemical's molar mass
    public double getMolarMass() {
        /* Checks if molar mass has been calculated or not to ensure the code doesn't
		run and unnessacary times */
        if (molarMass == -1.0) {
            molarMass = molarMassCalc(chemicalFormula);
            return molarMass;
        }
        // Returns molar mass if previously calculated
        else {
            return molarMass;
        }
    }

    // Method returns string of private chemical's formula
    public String getFormula() {
        return chemicalFormula;
    }

    // Find elements multiplier afrer the element
    private int findNumber(String find) {
        String val = "";
        // Checks if each for numbers in string and concatenates them to string val
        for (int i = 0; i < find.length(); i++) {
            if (Character.isDigit(find.charAt(i))) {
                val += find.charAt(i);
            }
            // When a non number is found break the loop
            else {
                break;
            }
        }
        // Returns interger value of string val since it represents and index
        return Integer.parseInt(val);
    }

    // Method reads elements from csv file and retrieves their molar mass
    // https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
    public static double findElement(String element) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader("periodicIndex.csv"))) {
            while ((line = br.readLine()) != null) {
                /* Use comma as separator, if comma is found add new element to array after
				comma */
                String[] contents = line.split(",");
                // Checks if element in csv file is element being searched for
                if (element.equals(contents[0])) {
                    // Returns corresponding elements molar mass as double
                    return Double.parseDouble(contents[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // If no corresponding element is found return 0
        return 0.0;
    }

    // Find the location of closing bracket
    private int findBracket(String find) {
        /* State variable ensures that the corresponding closing bracket is found not
    		the first found closing bracket */
        int state = 0;
        for (int i = 0; i < find.length(); i++) {
            if (find.charAt(i) == '(') {
                state++;
            } else if (find.charAt(i) == ')' && state == 0) {
                return i + 1;
            } else if (find.charAt(i) == ')' && state != 0) {
                state--;
            }
        }
        return 1;
    }

    // Recursive molar mass calcaulation method
    private double molarMassCalc(String formula) {
        /* Base case for if the entire formula has been checked, therefor length = 0, or
    		if a bracket is found */
        if (formula.length() == 0 || formula.charAt(0) == ')') {
            return 0.0;
        }
        /* If there is a bracket find the molar mass inside of the bracket and the
        multiply it by its multiplyier */
        else if (formula.charAt(0) == '(') {
            int multiplier = findNumber(formula.substring(findBracket(formula.substring(1)) + 1));
            int indexBracket = findBracket(formula.substring(1));
            int indexString = indexBracket + String.valueOf(findNumber(formula.substring(indexBracket + 1))).length()
                    + 1;
            /* Returns molar mass of inside of bracket * its multiplier and then adds what
            is outside the bracket to the molar mass */
            return molarMassCalc(formula.substring(1)) * multiplier + molarMassCalc(formula.substring(indexString));

        }
        // Add mm of single letter element no multiplier
        else if (formula.length() == 1 || formula.charAt(1) == ')' || formula.charAt(1) == '('
                || (formula.charAt(1) < 96 && formula.charAt(1) > 58)) {
            return findElement(formula.substring(0, 1)) + molarMassCalc(formula.substring(1));
        }

        // Add mm of single letter element with multiplier
        else if (formula.charAt(1) < 96 && formula.charAt(1) < 58) {
            return findElement(formula.substring(0, 1)) * findNumber(formula.substring(1))
                    + molarMassCalc(formula.substring(1 + String.valueOf(findNumber(formula.substring(1))).length()));
        }
        // Add mm of double letter element no multiplier
        else if (formula.length() == 2 || formula.charAt(2) == ')' || formula.charAt(2) == '('
                || (formula.charAt(1) > 96 && formula.charAt(2) > 58)) {
            return findElement(formula.substring(0, 2)) + molarMassCalc(formula.substring(2));
        }

        // Add mm of double letter element with multiplier
        else if (formula.charAt(1) > 96 && formula.charAt(2) < 58) {
            return findElement(formula.substring(0, 2)) * findNumber(formula.substring(2))
                    + molarMassCalc(formula.substring(2 + String.valueOf(findNumber(formula.substring(2))).length()));
        }
        /* Returns 0 as final ensurance program runs, however
        checkIfValid() ensures that the input is a valid formula */
        else {
            return 0.0;
        }

    }

    /* method to check if inputed chemical formula contains only valid chemcical
    formula characters and proper formatting */
    public boolean checkIfValid() {
        int leftBracketQ = 0;
        int rightBracketQ = 0;

        for (int i = 0; i < chemicalFormula.length(); i++) {
            // Counts the amount of opening brackets
            if (chemicalFormula.charAt(i) == '(') {
                leftBracketQ++;
            }
            // Counts the amount of closing brackets
            if (chemicalFormula.charAt(i) == ')') {
                rightBracketQ++;
                // checks if the closing bracket is end of formula meaning no multiplier
                if (i + 1 >= chemicalFormula.length()) {
                    return false;
                }
                // Checks if the closing bracket has a multiplier after it
                if (!(chemicalFormula.charAt(i + 1) > 47 && chemicalFormula.charAt(i + 1) < 58)) {
                    return false;
                }
            }
            // For loop moves through the check if all characters are valid
            if ((chemicalFormula.charAt(i) < 40) || (chemicalFormula.charAt(i) < 48 && chemicalFormula.charAt(i) > 41)
                    || (chemicalFormula.charAt(i) < 65 && chemicalFormula.charAt(i) > 57)
                    || (chemicalFormula.charAt(i) < 97 && chemicalFormula.charAt(i) > 90)
                    || (chemicalFormula.charAt(i) > 122)) {
                return false;
            }
        }
        /* If the amount of opening and closing brackets arent equal return false since
        the formula is invalid */
        if (leftBracketQ != rightBracketQ) {
            return false;
        }
        // Checks if all elements are valid elements
        for (int i = 0; i < chemicalFormula.length(); i++) {
            // If lowercase letter found alone return false
            if (chemicalFormula.charAt(i) > 96 && chemicalFormula.charAt(i) < 123) {
                return false;
            }
            // Skip if valid character and not element
            if (chemicalFormula.charAt(i) == ')' || chemicalFormula.charAt(i) == '('
                    || chemicalFormula.charAt(i) > 47 && chemicalFormula.charAt(i) < 58)
                ;
            /* If capital letter, check if one or two letter element, check if element is
            valid */
            else if (chemicalFormula.charAt(i) > 64 && chemicalFormula.charAt(i) < 91) {
                // Check if check is not at end of formula
                if (chemicalFormula.length() > i + 1) {
                    if (chemicalFormula.charAt(i + 1) > 96 && chemicalFormula.charAt(i + 1) < 123) {
                        /* If no molar mass is found for two letter element ouput of findElement() is 0
                        so return false */
                        if (Chemical.findElement(chemicalFormula.substring(i, i + 2)) == 0.0) {
                            return false;
                        }
                        ++i;
                        /* If no molar mass is found for one letter element ouput of findElement() is 0
                        so return false */
                    } else if (Chemical.findElement(chemicalFormula.substring(i, i + 1)) == 0.0) {
                        return false;
                    }
                }
                // Check if final character is a valid element
                else {
                    if (Chemical.findElement(chemicalFormula.substring(i, i + 1)) == 0.0) {
                        return false;
                    }
                }
            }
        }
        // If first character is a number return false
        if (chemicalFormula.charAt(0) > 47 && chemicalFormula.charAt(0) < 58) {
            return false;
        }
        return true;
    }
}