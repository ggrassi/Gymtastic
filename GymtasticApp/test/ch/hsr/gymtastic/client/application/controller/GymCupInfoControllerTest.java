package ch.hsr.gymtastic.client.application.controller;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCupClientInfo;
import ch.hsr.gymtastic.technicalServices.utils.DateFormatConverter;

public class GymCupInfoControllerTest {
	private GymCupInfoController gymCupInfoController;
	private GymCupClientInfo gymCupInfo;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private DeviceType deviceType;
	private String cupName;
	private String location;

	@Before
	public void setUp() throws ParseException {
		gymCupInfoController = new GymCupInfoController();
		startDate = DateFormatConverter.convertStringToDate("12.01.2011");
		endDate = DateFormatConverter.convertStringToDate("13.01.2011");
		cupName = "Test Cup";
		location = "Rapperswil";

		gymCupInfo = new GymCupClientInfo(cupName, location, startDate,
				endDate, deviceType);
	}

	@Test
	public void testSetAndGet() {
		gymCupInfoController.setGymCupInfo(gymCupInfo);
		assertEquals(gymCupInfo, gymCupInfoController.getGymCupClientInfo());
	}

}
