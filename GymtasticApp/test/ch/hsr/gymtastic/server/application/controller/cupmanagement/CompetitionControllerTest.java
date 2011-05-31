package ch.hsr.gymtastic.server.application.controller.cupmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.controller.cupmanagement.CompetitionController;
import ch.hsr.gymtastic.server.application.controller.cupmanagement.GymCupController;

public class CompetitionControllerTest {

    private CompetitionController competitionController;
    public static GymCupController gymCupController = new GymCupController();
    private Competition competition;
    private List<Squad> squads = new ArrayList<Squad>();
    private int roundNr;

    @Before
    public void setUp() throws ConnectException {
	competitionController = gymCupController.getCompetitionController();
	competition = new Competition("TestCompetition", new GregorianCalendar(), "08:00", "09:00",
		"Test Programmklasse");
	for (int i = 0; i < 6; i++) {
	    Squad squad = new Squad(i);
	    for (int j = 0; j < 5; j++) {
		Athlete athlete = new Athlete(i, 0, "Test Programm", "FirstName ", "LastName ", "Test address ", 2000,
			"Association");
		squad.addAthlet(athlete);
	    }
	    squads.add(squad);
	}
	competition.setSquads(squads);
	competitionController.setCompetition(competition);
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
