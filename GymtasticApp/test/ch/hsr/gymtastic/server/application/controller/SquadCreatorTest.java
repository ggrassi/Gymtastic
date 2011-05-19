package ch.hsr.gymtastic.server.application.controller;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SquadCreatorTest {

	private ArrayList<String> tmp;

	@Before
	public void setUp() throws Exception {
		tmp.add("1");
		tmp.add("1");
		tmp.add("P1");
		tmp.add("Luca");
		tmp.add("Scheiwiler");
		tmp.add("Test");
		tmp.add("Strasse 1");
		tmp.add("2000");
		tmp.add("TZR");
		tmp.add("sg");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSquadCreator() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateSquads() {
		fail("Not yet implemented");
	}

}
