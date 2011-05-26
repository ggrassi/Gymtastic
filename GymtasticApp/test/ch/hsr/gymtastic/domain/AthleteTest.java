package ch.hsr.gymtastic.domain;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AthleteTest {

    private Athlete athlete;
    private Athlete emptyAthlete;
    private Athlete athleteAllInformations;
    private Association association;
    private Mark mark1;
    private Mark mark2;

    @Before
    public void setUp() throws Exception {
	athlete = new Athlete("Chuck", "Norris", "Roundhousekick Ave.");
	association = new Association("Rheintal TV", "Rheintal");
	athleteAllInformations = new Athlete(1, 33, "E1", "Jolly", "Jumper", "Desertstreet 12", 1980, association);
	mark1 = new Mark(4.5, 4.3, 3.4, 4.5, 0.2, 0.5);
	mark2 = new Mark(6.656, 3.232, 4.433, 5.544, 0.445, 1.255);
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
    public void testFinalMarks() {
	assertEquals(mark1.getFinalMark(), athleteAllInformations.getMark(DeviceType.FLOOR_EXCERCISE).getFinalMark());
	assertEquals(mark2.getFinalMark(), athleteAllInformations.getMark(DeviceType.HIGH_BAR).getFinalMark());
	assertEquals(mark1.getFinalMark(), athleteAllInformations.getMark(DeviceType.PARALLEL_BARS).getFinalMark());
	assertEquals(mark2.getFinalMark(), athleteAllInformations.getMark(DeviceType.POMMEL_HORSE).getFinalMark());
	assertEquals(mark1.getFinalMark(), athleteAllInformations.getMark(DeviceType.STILL_RINGS).getFinalMark());
	assertEquals(mark2.getFinalMark(), athleteAllInformations.getMark(DeviceType.VAULT).getFinalMark());

    }

    @Test
    public void testSumOfEndMarks() {
	assertEquals(3 * mark1.getFinalMark() + 3 * mark2.getFinalMark(), athleteAllInformations.getSumOfEndMarks());

    }

    private void addMarks() {
	athleteAllInformations.addMark(DeviceType.FLOOR_EXCERCISE, mark1);
	athleteAllInformations.addMark(DeviceType.HIGH_BAR, mark2);
	athleteAllInformations.addMark(DeviceType.PARALLEL_BARS, mark1);
	athleteAllInformations.addMark(DeviceType.POMMEL_HORSE, mark2);
	athleteAllInformations.addMark(DeviceType.STILL_RINGS, mark1);
	athleteAllInformations.addMark(DeviceType.VAULT, mark2);
    }

}
