package ch.hsr.gymtastic.server.application.controller.persistence;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Mark;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.controller.persistence.SquadCreator;

public class SquadCreatorTest {

	private SquadCreatorStub squadCreator;
	private Map<Integer, Squad> squadMapOriginal;
	private Map<Integer, Squad> squadMapUnderTest;
	private List<List<String>> importList = new ArrayList<List<String>>();

	private class SquadCreatorStub extends SquadCreator {

		public SquadCreatorStub() {

			List<String> squadOneWithOneAthlete = new ArrayList<String>();
			squadMapOriginal = new TreeMap<Integer, Squad>();
			Squad firstSquad = new Squad(1);
			firstSquad.addAthlet(new Athlete(1, 1, "P1", "Luca11",
					"Schweiwiler", "Test Strasse 1", 2000, "TZR sg"));
			squadMapOriginal.put(1, firstSquad);

			squadOneWithOneAthlete.add("1");
			squadOneWithOneAthlete.add("1");
			squadOneWithOneAthlete.add("P1");
			squadOneWithOneAthlete.add("Luca11");
			squadOneWithOneAthlete.add("Scheiwiler");
			squadOneWithOneAthlete.add("Test Strasse 1");
			squadOneWithOneAthlete.add("2000");
			squadOneWithOneAthlete.add("TZR");
			squadOneWithOneAthlete.add("sg");
			importList.add(squadOneWithOneAthlete);

			List<String> squadTwoWithFirstAthlete = new ArrayList<String>();
			Squad secondSquad = new Squad(2);
			secondSquad.addAthlet(new Athlete(2, 2, "P3", "Luca21",
					"Schweiwiler", "Test Strasse 1", 2000, "TZR sg"));
			squadTwoWithFirstAthlete.add("2");
			squadTwoWithFirstAthlete.add("2");
			squadTwoWithFirstAthlete.add("P3");
			squadTwoWithFirstAthlete.add("Luca21");
			squadTwoWithFirstAthlete.add("Scheiwiler");
			squadTwoWithFirstAthlete.add("Test Strasse 1");
			squadTwoWithFirstAthlete.add("2000");
			squadTwoWithFirstAthlete.add("TZR");
			squadTwoWithFirstAthlete.add("sg");
			importList.add(squadTwoWithFirstAthlete);

			List<String> squadTwoWithSecondAthlete = new ArrayList<String>();
			secondSquad.addAthlet(new Athlete(2, 3, "P3", "Luca22",
					"Schweiwiler", "Test Strasse 1", 2000, "TZR sg"));
			squadMapOriginal.put(2, secondSquad);
			squadTwoWithSecondAthlete.add("2");
			squadTwoWithSecondAthlete.add("3");
			squadTwoWithSecondAthlete.add("P3");
			squadTwoWithSecondAthlete.add("Luca22");
			squadTwoWithSecondAthlete.add("Scheiwiler");
			squadTwoWithSecondAthlete.add("Test Strasse 1");
			squadTwoWithSecondAthlete.add("2000");
			squadTwoWithSecondAthlete.add("TZR");
			squadTwoWithSecondAthlete.add("sg");
			importList.add(squadTwoWithSecondAthlete);
		}

		@Override
		public Map<Integer, Squad> createSquads() {
			Set<Integer> squadsNrList = findSquadNumbers(importList);

			Map<Integer, Squad> squadMap = new TreeMap<Integer, Squad>();
			for (Integer squadNr : squadsNrList) {
				squadMap.put(squadNr, new Squad(squadNr));
			}

			for (List<String> line : importList) {
				Athlete tmpAthlete = getAthleteFrom(line);
				for (DeviceType dt : DeviceType.values()) {
					tmpAthlete.addMark(dt, new Mark(0.0, 0.0, 0.0, 0.0, 0.0,
							0.0));
				}
				squadMap.get(Integer.parseInt(line.get(squadPositionImport)))
						.addAthlet(tmpAthlete);
			}
			return squadMap;

		}
	}

	@Before
	public void setUp() {
		squadCreator = new SquadCreatorStub();
		squadMapUnderTest = squadCreator.createSquads();
	}

	@After
	public void tearDown() {
		squadMapUnderTest.clear();
		squadCreator = null;
	}

	@Test
	public void testFindSquadNumbers() {
		int first = 0;
		String firstAthleteSquadNumber = "1";
		assertEquals(firstAthleteSquadNumber, importList.get(first).get(0));
	}

	@Test
	public void testCreateSquads() {
		assertEquals(squadMapOriginal.size(), squadMapUnderTest.size());
	}

}
