package ch.hsr.gymtastic.application.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.application.controller.client.SquadController;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Squad;

public class SquadControllerTest {
	Squad squad;
	SquadController squadController;
	Athlete mp;
	Athlete gg;
	Athlete mf;
	Athlete jw;
	
	@Before
	public void setUp(){
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
	}
	@After
	public void initSquadController(){
		squadController.initialize();
	}
	
	@Test
	public void testHasNextAthlete(){
		assertTrue(squadController.hasNextAthlete());		
		doForwardSteps(4);
		assertFalse(squadController.hasNextAthlete());
	}
	
	@Test
	public void testHasPreviousAthlete(){
		assertFalse(squadController.hasPreviousAthlete());
		doForwardSteps(2);
		assertTrue(squadController.hasPreviousAthlete());
	}
	
	@Test
	public void getNextAthlete(){
		assertEquals(mp, squadController.getNextAthlete());
		doForwardSteps(2);
		assertEquals(jw, squadController.getNextAthlete());
	}
	
	@Test
	public void getPreviousAthlete(){
		doForwardSteps(2);
		assertEquals(mp, squadController.getPreviousAthlete());
	}
	@Test
	public void getPreviousAthlete2(){
		doForwardSteps(4);
		assertEquals(mf, squadController.getPreviousAthlete());
	}
	
	private void doForwardSteps(int steps) {
		for(int i = 1; i <= steps; i++)
			squadController.getNextAthlete();
	}

}
