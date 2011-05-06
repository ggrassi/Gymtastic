package ch.hsr.gymtastic.client.application.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.client.application.controller.SquadController;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.RoundInfo;
import ch.hsr.gymtastic.domain.Squad;

public class SquadControllerTest {
	Squad squad;
	SquadController squadController;
	Athlete mp;
	Athlete gg;
	Athlete mf;
	Athlete jw;
	private RoundInfo roundInfo;
	private int roundNr;

	@Before
	public void setUp() {
		squad = new Squad();
		squadController = new SquadController();
		mp = new Athlete("Marco", "Pfiffner", "Vilters");
		squad.addAthlet(mp);
		gg = new Athlete("Giuliano", "Grassi", "Savognin");
		squad.addAthlet(gg);
		mf = new Athlete("Mathias", "Fasser", "Schellenberg");
		squad.addAthlet(mf);
		jw = new Athlete("Jules", "Weder", "Au");
		squad.addAthlet(jw);
		squadController.setSquad(squad);
		roundNr = 2;
		roundInfo = new RoundInfo(squad, roundNr);
	}

	@After
	public void tearDown() {
		squadController.reset();
	}

	@Test
	public void testHasNextAthlete() {
		assertTrue(squadController.hasNextAthlete());
		doForwardSteps(4);
		assertFalse(squadController.hasNextAthlete());
	}

	@Test
	public void testHasPreviousAthlete() {
		assertFalse(squadController.hasPreviousAthlete());
		doForwardSteps(2);
		assertTrue(squadController.hasPreviousAthlete());
	}

	@Test
	public void testNextAthlete() {
		assertEquals(mp, squadController.getNextAthlete());
		doForwardSteps(2);
		assertEquals(jw, squadController.getNextAthlete());
	}

	@Test
	public void testPreviousAthlete() {
		doForwardSteps(2);
		assertEquals(mp, squadController.getPreviousAthlete());
	}

	@Test
	public void testPreviousAthlete2() {
		doForwardSteps(4);
		assertEquals(mf, squadController.getPreviousAthlete());
	}

	@Test
	public void testGetNumberOfAthlets() {
		assertEquals(4, squadController.getNumberOfAthletes());
	}

	@Test
	public void testGetSquad() {

		assertEquals(squad, squadController.getSquad());
	}
	@Test
	public void testSetRoundInfo(){
		squadController.setRoundInfo(roundInfo);
		assertEquals(roundNr, squadController.getRoundNr());
		assertEquals(squad, squadController.getSquad());
	}

	private void doForwardSteps(int steps) {
		for (int i = 1; i <= steps; i++)
			squadController.getNextAthlete();
	}

}
