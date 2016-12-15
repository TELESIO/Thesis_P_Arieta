package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private SimpleDateFormat formatter;

    public DateUtils() {
    }

    public boolean isValidDate(String dateString) {
	formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	try {
	    formatter.parse(dateString);
	    return true;
	} catch (ParseException e) {
	    return false;
	}
    }

    public Date parseDate(String text) {
	formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

	Date date = null;
	try {
	    date = formatter.parse(text);
	} catch (ParseException e) {

	}

	return date;
    }
}
