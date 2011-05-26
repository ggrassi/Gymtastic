package ch.hsr.gymtastic.server.application.controller;


import static junit.framework.Assert.assertEquals;
import java.util.GregorianCalendar;
import org.junit.Test;
import ch.hsr.gymtastic.domain.GymCup;

public class GymCupControllerTest {

	private static GymCupController gymCupController  = new GymCupController();
	private GymCup gymCupEmpty = new GymCup();
	private GymCup gymCupFilled = new GymCup("TestCup", "Rapperswil");
	private GregorianCalendar startDate = new GregorianCalendar(2011, 5, 22);
	private GregorianCalendar endDate = new GregorianCalendar(2011, 5, 22);
	
	@Test
	public void testGetEmptyGymCup() {
		gymCupController.setGymCup(gymCupEmpty);
		assertEquals(gymCupEmpty, gymCupController.getGymCup());
	}
	
	@Test
	public void testGetFilledGymCup() {
		gymCupFilled.setStartDate(startDate);
		gymCupFilled.setEndDate(endDate);
		gymCupController.setGymCup(gymCupFilled);
		assertEquals(gymCupFilled, gymCupController.getGymCup());
	}
	

	

}
