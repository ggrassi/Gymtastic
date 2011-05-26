package ch.hsr.gymtastic.client.application.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.client.application.controller.CompetitionInfoController;
import ch.hsr.gymtastic.domain.CompetitionInfo;

public class CompetitionInfoControllerTest {

	CompetitionInfoController competitionInfoController;
	CompetitionInfo competitionInfo;

	@Before
	public void setUp() {
		competitionInfoController = new CompetitionInfoController();
		competitionInfo = new CompetitionInfo("Test Competition");

	}

	@Test
	public void testSetCompetitionInfo() {
		competitionInfoController.setComeptitionInfo(competitionInfo);
		assertEquals(competitionInfo, competitionInfoController.getCompetitionInfo());
	}

}
