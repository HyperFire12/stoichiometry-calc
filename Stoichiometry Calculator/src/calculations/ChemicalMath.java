package calculations;
public class ChemicalMath {
    // The class' purpose is to act as a Math. class for the applications of chemistry calculations
	
    // Method converts °C to Kelvin
    public static String convertCK(String TEMP) {
        // Checks to make sure input has only a valid number and valid units
        if (inputValidationUnits(TEMP)) {
            return "INVALID UNITS";
        }
        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(TEMP, "K", "°C")) {
            return "INVALID UNITS";
        }
        double output = getNumbers(TEMP);
        String outputUnits = "";
        if (TEMP.contains("°C")) {
            outputUnits = "K";
            output += 273;
        } else {
            outputUnits = "°C";
            output -= 273;
        }
        return output + " " + outputUnits;
    }

    // Method finds unknown variable in Beer Lambert Law equations
    public static String beerLambertLaw(String TA, String L, String C, String E) {
        // Checks to make sure only one variable is left to solve for
        if (!(onlyOneLeft(TA, L, C, E))) {
            return "Only one varible can be empty";
        }
        // Checks to make sure input has only a valid number and valid units
        if (inputValidationUnits(L) || inputValidationUnits(C) || inputValidationNumber(E)) {
            return "INVALID UNITS";
        }
        /* If solving for TA which can either be a percent or a number value, check
 		valid depending on while to isolate */

        // If % check for valid units
        if (TA.contains("%")) {
            if (inputValidationUnits(TA)) {
                return "INVALID UNITS";
            }
        }
        // If a number check input is only valid number
        else {
            if (inputValidationNumber(TA)) {
                return "INVALID UNITS";
            }
        }
        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(L, "m", "cm") && !(L.length() == 0)) {
            return "INVALID UNITS";
        }
        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(C, "mol/L") && !(C.length() == 0)) {
            return "INVALID UNITS";
        }
        double ta = getNumbers(TA), l = getNumbers(L), c = getNumbers(C), e = getNumbers(E);
        String output, outputUnits;
        if (TA.contains("%")) {
            ta = Math.log10(ta / 100);
            ta *= -1;
        }
        if (L.contains("cm")) {

        } else if (L.contains("m")) {
            l *= 100;
        }
        if (TA.length() == 0) {
            ta = l * c * e;
            output = "" + ta;
            outputUnits = "Absorbance";
        } else if (L.length() == 0) {
            l = ta / (c * e);
            output = "" + l;
            outputUnits = "cm";
        } else if (C.length() == 0) {
            c = ta / (l * e);
            output = "" + c;
            outputUnits = "mol/L";
        } else {
            e = ta / (l * c);
            output = "" + e;
            outputUnits = "L*mol*L^-1*cm^-1";
        }
        return Math.round(Double.parseDouble(output) * 10000d) / 10000d + " " + outputUnits;
    }

    // Method finds unknown variable in C1V1 = C2V2 calculation
    public static String findVolumeOrConentration(String C1, String V1, String C2, String V2) {
        // Checks to make sure only one variable is left to solve for
        if (!(onlyOneLeft(C1, C2, V2, V1))) {
            return "Only one varible can be empty";
        }

        // Checks to make sure input has only a valid number and valid units
        if (inputValidationUnits(C1) || inputValidationUnits(C2) || inputValidationUnits(V1)
                || inputValidationUnits(V2)) {
            return "INVALID UNITS";
        }

        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(C1, "mol/L", "g/L") && !(C1.length() == 0)) {
            return "INVALID UNITS";
        }

        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(C2, "mol/L", "g/L") && !(C2.length() == 0)) {
            return "INVALID UNITS";
        }

        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(V1, "L", "mL") && !(V1.length() == 0)) {
            return "INVALID UNITS";
        }

        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(V2, "L", "mL") && !(V2.length() == 0)) {
            return "INVALID UNITS";
        }

        // Checks to make sure that units are consistant
        if ((C1.contains("mol/L") && C2.contains("g/L")) || (C2.contains("mol/L") && C1.contains("g/L"))) {
            return "INPUT MISMATCH";
        }
        double c1 = getNumbers(C1), v1 = getNumbers(V1), c2 = getNumbers(C2), v2 = getNumbers(V2);
        String cUnits = "mol/L", output, outputUnits = "mol/L";
        if (C1.contains("g/L") || C2.contains("g/L")) {
            cUnits = "g/L";
        }
        if (V1.contains("mL") || V2.contains("mL")) {
            v1 *= 0.001;
            v2 *= 0.001;
        }
        if (C1.length() == 0) {
            c1 = (c2 * v2) / (v1);
            output = "" + c1;
            outputUnits = cUnits;
        } else if (V1.length() == 0) {
            outputUnits = "L";
            v1 = (c2 * v2) / (c1);
            output = "" + v1;
        } else if (C2.length() == 0) {
            c2 = (c1 * v1) / (v2);
            output = "" + c2;
            outputUnits = cUnits;
        } else {
            outputUnits = "L";
            v2 = (c1 * v1) / (c2);
            output = "" + v2;
        }
        return Math.round(Double.parseDouble(output) * 10000d) / 10000d + " " + outputUnits;
    }

    // Method finds unknown variable in PV = nRT equation
    public static String findPVNRT(String P, String V, String N, String T) {
        // Checks to make sure only one variable is left to solve for
        if (!(onlyOneLeft(P, V, N, T))) {
            return "Only one varible can be empty";
        }

        // Checks to make sure input has only a valid number and valid units
        if (inputValidationUnits(P) || inputValidationUnits(T) || inputValidationUnits(V)) {
            return "INVALID UNITS";
        }

        // If moles have units mol/L input check if valid number and units
        if (N.contains("mol/L")) {
            if (inputValidationUnits(N)) {
                return "INVALID UNITS";
            }
        }

        // Since mols are only valid unit mol/L not required, check if valid number
        else {
            if (inputValidationNumber(N)) {
                return "INVALID UNITS";
            }
        }

        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(P, "atm", "mmHg", "kPa") && !(P.length() == 0)) {
            return "INVALID UNITS";
        }

        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(V, "mL", "L") && !(V.length() == 0)) {
            return "INVALID UNITS";
        }

        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(T, "°C", "K") && !(T.length() == 0)) {
            return "INVALID UNITS";
        }

        double p = getNumbers(P), v = getNumbers(V), n = getNumbers(N), t = getNumbers(T), r = -1;
        String pUnits, output, outputUnits;
        if (P.contains("atm")) {
            pUnits = "atm";
            r = 0.0821;
        } else if (P.contains("mmHg")) {
            pUnits = "mmHg";
            r = 62.4;
        } else {
            pUnits = "kPa";
            r = 8.314;
        }
        if (V.contains("mL")) {
            v *= 0.001;
        }
        if (T.contains("°C")) {
            t += 273;
        }
        if (P.length() == 0) {
            p = (n * r * t) / v;
            output = "" + p;
            outputUnits = pUnits;

        } else if (V.length() == 0) {
            v = (n * r * t) / p;
            output = "" + v;
            outputUnits = "L";

        } else if (N.length() == 0) {
            n = (p * v) / (r * t);
            output = "" + n;
            outputUnits = "mol";
        } else {
            t = (p * v) / (r * n);
            output = "" + t;
            outputUnits = "K";
        }
        return Math.round(Double.parseDouble(output) * 10000d) / 10000d + " " + outputUnits;
    }

    // Method converts given pressure to specified pressure units
    public static String convertPressure(String PRESSURE, String convertTo) {
        // Checks to make sure input has only a valid number and valid units
        if (inputValidationUnits(PRESSURE)) {
            return "INVALID UNITS";
        }

        // Checks to make sure input has only valid units for given equation
        if (checkUnitsInvalid(PRESSURE, "atm", "mmHg", "kPa", "psi")) {
            return "INVALID UNITS";
        }
        double pressure = getNumbers(PRESSURE);
        String output;
        double conversion1 = -1, conversion2 = -1;
        if (PRESSURE.contains("atm")) {
            conversion1 = 1.000;
        } else if (PRESSURE.contains("kPa")) {
            conversion1 = 101.3;
        } else if (PRESSURE.contains("mmHg")) {
            conversion1 = 760;
        } else if (PRESSURE.contains("psi")) {
            conversion1 = 14.7;
        }
        if (convertTo.equals("atm")) {
            conversion2 = 1.000;
        } else if (convertTo.equals("kPa")) {
            conversion2 = 101.3;
        } else if (convertTo.equals("mmHg")) {
            conversion2 = 760;
        } else if (convertTo.contains("psi")) {
            conversion2 = 14.7;
        }
        output = Math.round(pressure * (conversion2 / conversion1) * 10000d) / 10000d + "";
        return output + " " + convertTo;
    }

    // Method finds number in string input
    private static double getNumbers(String var) {
        if (var.length() == 0) {
            return -1.0;
        }
        String value = "";
        for (int i = 0; i < var.length(); i++) {
            if ((var.charAt(i) > 47 && var.charAt(i) < 58) || var.charAt(i) == '.') {
                value += var.charAt(i);
            } else {
                break;
            }
        }
        return Double.parseDouble(value);
    }

    // Checks if input has the required input and also only has one period
    private static boolean checkUnitsInvalid(String check, String requiredUnits) {
        int periods = 0;
        for (int i = 0; i < check.length(); i++) {
            if (check.charAt(i) == '.') {
                periods++;
            }
            if (periods > 1) {
                return true;
            }
        }
        if (!(check.contains(requiredUnits))) {
            return true;
        }
        return false;
    }

    // Overloaded methods to check if input has valid units for multiple different allowable units
    private static boolean checkUnitsInvalid(String check, String units1, String units2) {
        if (checkUnitsInvalid(check, units1)) {
            return checkUnitsInvalid(check, units2);
        }
        return false;
    }

    private static boolean checkUnitsInvalid(String check, String units1, String units2, String units3) {
        if (checkUnitsInvalid(check, units1)) {
            if (checkUnitsInvalid(check, units2)) {
                return checkUnitsInvalid(check, units3);
            }
        }
        return false;
    }

    private static boolean checkUnitsInvalid(String check, String units1, String units2, String units3, String units4) {
        if (checkUnitsInvalid(check, units1)) {
            if (checkUnitsInvalid(check, units2)) {
                if (checkUnitsInvalid(check, units3)) {
                    return checkUnitsInvalid(check, units4);
                }
            }
        }
        return false;
    }

    /* Checks if input has only numbers and periods and all characters make up some
    valid units */
    private static boolean inputValidationUnits(String check) {
        if (check.length() == 0) {
            return false;
        }
        // Loops through string to check input for having units
        for (int i = 0; i < check.length(); i++) {
            /* If the character isnt a digit check if either a period or space, if not the
            rest must be some valid units */
            if (!(Character.isDigit(check.charAt(i)))) {
                if (check.charAt(i) != '.' || check.charAt(i) != ' ') {
                    if (check.substring(i).equals("mol/L") || check.substring(i).equals("K")
                            || check.substring(i).equals("°C") || check.substring(i).equals("m")
                            || check.substring(i).equals("cm") || check.substring(i).equals("%")
                            || check.substring(i).equals("L") || check.substring(i).equals("mL")
                            || check.substring(i).equals("g/L") || check.substring(i).equals("atm")
                            || check.substring(i).equals("mmHg") || check.substring(i).equals("kPa")
                            || check.substring(i).equals("psi")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Checks to make sure given input is only made up of numerical characters
    private static boolean inputValidationNumber(String check) {
        if (check.length() == 0) {
            return false;
        }
        for (int i = 0; i < check.length(); i++) {
            if (!(Character.isDigit(check.charAt(i)))) {
                if (check.charAt(i) != '.') {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to ensure only one variable is left to isolate
    private static boolean onlyOneLeft(String one, String two, String three, String four) {
        int total = 0;
        if (one.length() == 0) {
            total++;
        }
        if (two.length() == 0) {
            total++;
        }
        if (three.length() == 0) {
            total++;
        }
        if (four.length() == 0) {
            total++;
        }
        if (total == 1) {
            return true;
        } else {
            return false;
        }
    }
}