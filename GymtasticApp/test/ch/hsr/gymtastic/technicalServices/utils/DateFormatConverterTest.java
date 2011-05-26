package ch.hsr.gymtastic.technicalServices.utils;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class DateFormatConverterTest {
    private GregorianCalendar dateGC;
    private String dateS;

    @Before
    public void setUp() {
	dateGC = new GregorianCalendar(2011, 4, 17);
	dateS = "17.04.2011";
    }

    @Test
    public void testStringToDate() throws ParseException {
	assertEquals(dateS, DateFormatConverter.convertDateToString(DateFormatConverter.convertStringToDate(dateS)));
    }

    @Test
    public void testDateToString() throws ParseException {
	assertEquals(dateGC, DateFormatConverter.convertStringToDate(DateFormatConverter.convertDateToString(dateGC)));
    }
}
