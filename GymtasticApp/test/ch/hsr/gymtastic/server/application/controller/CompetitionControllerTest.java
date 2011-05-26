package ch.hsr.gymtastic.server.application.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.domain.Association;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.Squad;

public class CompetitionControllerTest {

    private CompetitionController competitionController;
    private RoundAllocator roundAllocator;
    public static GymCupController gymCupController = new GymCupController();
    private Competition competition;
    private List<Squad> squads = new ArrayList<Squad>();
    private int roundNr;
    private NetworkServerController networkcontroller;

    @Before
    public void setUp() throws ConnectException {
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
