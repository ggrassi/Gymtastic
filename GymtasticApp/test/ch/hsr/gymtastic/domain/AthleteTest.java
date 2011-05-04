package ch.hsr.gymtastic.domain;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AthleteTest {

	Athlete athlete;
	Athlete emptyAthlete;
	Athlete athleteAllInformations;
	Association association;
	Mark mark;

	@Before
	public void setUp() throws Exception {
		athlete = new Athlete("Chuck", "Norris", "Roundhousekick Ave.");
		association = new Association("Rheintal TV", "Rheintal");
		athleteAllInformations = new Athlete(1, 33, "E1", "Jolly", "Jumper",
				"Desertstreet 12", 1980, association);
		mark = new Mark(4.5, 4.3, 3.4, 4.5, 0.2, 0.5);
		addMarks();
		emptyAthlete = new Athlete();
	}

	@Test
	public void testGetAthleteInformations() {
		assertEquals("Chuck", athlete.getFirstName());
		assertEquals("Norris", athlete.getLastName());
		assertEquals("Roundhousekick Ave.", athlete.getAddress());
	}

	@Test
	public void testEmptyAthlete() {
		assertEquals(null, emptyAthlete.getFirstName());
		assertEquals(null, emptyAthlete.getLastName());
		assertEquals(null, emptyAthlete.getAddress());
	}

	@Test
	public void testAthleteAllInformations() {
		assertEquals(33, athleteAllInformations.getStartNr());
		assertEquals("Jolly", athleteAllInformations.getFirstName());
		assertEquals("Jumper", athleteAllInformations.getLastName());
		assertEquals("Desertstreet 12", athleteAllInformations.getAddress());
		assertEquals("E1", athleteAllInformations.getPrgClass());
		assertEquals(1, athleteAllInformations.getSquadId());
		assertEquals(1980, athleteAllInformations.getYearOfBirth());
		assertEquals(association, athleteAllInformations.getAssociation());
	}

	@Test
	public void testSumOfEndMarks() {
		assertEquals(6 * mark.getFinalMark(), athleteAllInformations
				.getSumOfEndMarks());

	}

	private void addMarks() {
		athleteAllInformations.addMark(DeviceType.FLOOR_EXCERCISE, mark);
		athleteAllInformations.addMark(DeviceType.HIGH_BAR, mark);
		athleteAllInformations.addMark(DeviceType.PARALLEL_BARS, mark);
		athleteAllInformations.addMark(DeviceType.POMMEL_HORSE, mark);
		athleteAllInformations.addMark(DeviceType.STILL_RINGS, mark);
		athleteAllInformations.addMark(DeviceType.VAULT, mark);
	}

}
