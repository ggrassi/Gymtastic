package ch.hsr.gymtastic.client.application.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.domain.Association;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.controller.CompetitionController;
import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.application.controller.RoundAllocator;

public class CompetitionControllerTest {

    private CompetitionController competitionController;
    private RoundAllocator roundAllocator;
    private static GymCupController gymCupController = new GymCupController();
    private Competition competition;
    private List<Squad> squads = new ArrayList<Squad>();
    private int roundNr;

    @Before
    public void setUp() {
	competitionController = new CompetitionController(gymCupController.getNetworkController());
	competition = new Competition("TestCompetition", new GregorianCalendar(), "08:00", "09:00",
		"Test Programmklasse");
	for (int i = 0; i < 6; i++) {
	    Squad squad = new Squad(i);
	    for (int j = 0; j < 5; j++) {
		Athlete athlete = new Athlete(i, 0, "Test Programm", "FirstName ", "LastName ", "Test address ", 2000,
			new Association("Association ", "Testlocation"));
		squad.addAthlet(athlete);
	    }
	    squads.add(squad);
	}
	competition.setSquads(squads);
	competitionController.setCompetition(competition);
	roundAllocator = competitionController.getRoundAllocator();
	roundNr = 1;
    }

    @After
    public void tearDown() {
	competitionController.setCompetition(competition);
    }

    @Test
    public void hasCompetition() {
	assertEquals(competition, competitionController.getActualCompetition());
    }

    @Test
    public void hasRoundAllocator() {
	assertTrue(competitionController.getRoundAllocator() != null);
    }

    @Test
    public void testActualRoundNr() {
	competitionController.setActualRoundNr(roundNr);
	assertEquals(roundNr, competitionController.getActualRoundNr());
    }

}
