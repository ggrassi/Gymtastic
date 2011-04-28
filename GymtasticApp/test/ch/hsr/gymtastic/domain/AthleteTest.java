package ch.hsr.gymtastic.domain;


import static junit.framework.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AthleteTest {
    
    Athlete athlete;
    Athlete emptyAthlete;
    Athlete athleteAllInformations;
    Association association;

    @Before
    public void setUp() throws Exception {
	athlete = new Athlete("Chuck", "Norris", "Roundhousekick Ave.");
	 association = new Association("Rheintal TV", "Rheintal");
	athleteAllInformations = new Athlete(1, 33, "E1", "Jolly", "Jumper", "Desertstreet 12", 1980, association);
	emptyAthlete = new Athlete();
    }

    @Test
    public final void testGetAthleteInformations() {
	assertEquals("Chuck", athlete.getFirstName());
	assertEquals("Norris", athlete.getLastName());
	assertEquals("Roundhousekick Ave.", athlete.getAddress());
    }
    
    @Test
    public final void testEmptyAthlete() {
	assertEquals(null, emptyAthlete.getFirstName());
	assertEquals(null, emptyAthlete.getLastName());
	assertEquals(null, emptyAthlete.getAddress());
    }
    
    @Test
    public final void testAthleteAllInformations() {
	assertEquals(33, athleteAllInformations.getStartNr());
	assertEquals("Jolly", athleteAllInformations.getFirstName());
	assertEquals("Jumper", athleteAllInformations.getLastName());
	assertEquals("Desertstreet 12", athleteAllInformations.getAddress());
	assertEquals("E1", athleteAllInformations.getPrgClass());
	assertEquals(1, athleteAllInformations.getSquadId());
	assertEquals(1980, athleteAllInformations.getYearOfBirth());
	assertEquals(association, athleteAllInformations.getAssociation());
    }
    
    
   

}
