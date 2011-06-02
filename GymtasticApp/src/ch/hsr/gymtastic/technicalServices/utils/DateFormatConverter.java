package ch.hsr.gymtastic.technicalServices.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The Class DateFormatConverter converts a GregorianDate into a string and
 * viceversa.
 */
public final class DateFormatConverter {

	/**
	 * Instantiates a new DateFormatConverter
	 */
	private DateFormatConverter() {
	}

	/**
	 * Convert string to date.
	 * 
	 * @param s
	 *            the s
	 * @return the gregorian calendar
	 * @throws ParseException
	 *             the parse exception
	 */
	public static GregorianCalendar convertStringToDate(String s)
			throws ParseException {
		if (!s.equals("")) {
			DateFormat df = new SimpleDateFormat("dd.MM.yy");
			Date d = df.parse(s);
			GregorianCalendar date = new GregorianCalendar();
			date.setTime(d);
			return date;
		}
		return new GregorianCalendar();
	}

	/**
	 * Convert date to string.
	 * 
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String convertDateToString(GregorianCalendar date) {
		if (date != null) {
			DateFormat f = SimpleDateFormat.getDateInstance();
			return f.format(date.getTime());
		}
		return "";
	}
}
