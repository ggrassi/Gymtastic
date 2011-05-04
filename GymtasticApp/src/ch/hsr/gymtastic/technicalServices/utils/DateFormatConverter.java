package ch.hsr.gymtastic.technicalServices.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateFormatConverter {

    public static GregorianCalendar convertStringToDate(String s) throws ParseException {
	if (!s.equals("")) {
	    DateFormat df = new SimpleDateFormat("dd.mm.yy");
	    Date d = df.parse(s);
	    GregorianCalendar date = new GregorianCalendar();
	    date.setTime(d);
	    return date;
	}
	return new GregorianCalendar();
    }

    public static String convertDateToString(GregorianCalendar date) {
	if (date != null) {
	    DateFormat f = SimpleDateFormat.getDateInstance();
	    return f.format(date.getTime());
	}
	return "";
    }
}
