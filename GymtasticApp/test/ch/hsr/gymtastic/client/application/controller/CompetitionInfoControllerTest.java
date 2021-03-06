package ch.hsr.gymtastic.client.application.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.domain.CompetitionInfo;

public class CompetitionInfoControllerTest {

    private CompetitionInfoController competitionInfoController;
    private CompetitionInfo competitionInfo;

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
