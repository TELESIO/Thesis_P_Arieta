package utils;

public class NumberUtils {

    public NumberUtils() {
    }

    public double doubleParser(String number) {
	Double toReturn = null;
	try {
	    toReturn = Double.parseDouble(number);
	} catch (NumberFormatException nfe) {
	}

	return toReturn;
    }

    public double integerParser(String number) {
	Integer toReturn = null;
	try {
	    toReturn = Integer.parseInt(number);
	} catch (NumberFormatException nfe) {
	}

	return toReturn;
    }

    @SuppressWarnings("unused")
    public boolean isDoubleNumber(String number) {
	try {
	    double d = Double.parseDouble(number);
	} catch (NumberFormatException nfe) {
	    return false;
	}
	return true;

    }

    @SuppressWarnings("unused")
    public boolean isIntegerNumber(String number) {
	try {
	    int i = Integer.parseInt(number);
	} catch (NumberFormatException nfe) {
	    return false;
	}
	return true;

    }
}
