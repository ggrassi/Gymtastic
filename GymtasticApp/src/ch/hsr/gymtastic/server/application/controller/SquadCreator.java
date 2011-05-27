package ch.hsr.gymtastic.server.application.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Mark;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;
import ch.hsr.gymtastic.technicalServices.utils.ImportStartList;

/**
 * The Class SquadCreator
 */
public class SquadCreator {

	private static final int associationPlacePositionImport = 8;
	private static final int associationNamePositionImport = 7;
	private static final int yearPositionImport = 6;
	private static final int addressPositionImport = 5;
	private static final int lastNamePositionImport = 4;
	private static final int firstNamePositionImport = 3;
	private static final int progClassPositionImport = 2;
	private static final int startNrPositionImport = 1;
	private static final int squadPositionImport = 0;
	private ImportStartList startList;
	private DBConnection db;

	/**
	 * Instantiates a new squad creator.
	 * 
	 * @param importStartList
	 *            the import start list
	 */
	public SquadCreator(ImportStartList importStartList) {
		this.startList = importStartList;
	}

	/**
	 * Creates a map of squads and fills it with the Athletes
	 * 
	 * @return the map
	 */
	public Map<Integer, Squad> createSquads() {
		Set<Integer> squadsNrList = findSquadNumbers(startList.getImportList());
		Map<Integer, Squad> squadMap = new TreeMap<Integer, Squad>();
		for (Integer squadNr : squadsNrList) {
			squadMap.put(squadNr, new Squad(squadNr));
		}
		addAthletes(startList.getImportList(), squadMap);
		return squadMap;

	}

	private void addAthletes(List<List<String>> importList,
			Map<Integer, Squad> squadMap) {
		for (List<String> line : importList) {
			Athlete tmpAthlete = getAthleteFrom(line);
			for (DeviceType dt : DeviceType.values()) {
				tmpAthlete.addMark(dt, new Mark(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			}
			squadMap.get(Integer.parseInt(line.get(squadPositionImport)))
					.addAthlet(tmpAthlete);
		}
	}

	/**
	 * Finding the index of the Squad and fill it up to a set.
	 * 
	 * @param importList
	 *            the import list
	 * @return the sets the
	 */
	private Set<Integer> findSquadNumbers(List<List<String>> importList) {
		Set<Integer> squadsNrList = new TreeSet<Integer>();
		for (List<String> line : importList) {
			squadsNrList.add(Integer.parseInt(line.get(squadPositionImport)));
		}
		return squadsNrList;
	}

	/**
	 * Inserts the imported List to the DB
	 */
	public void insertImportToDB() {
		List<List<String>> importList = getImportList();
		insertAthletesToDB(importList);
	}

	/**
	 * manages to persist the Athletes to the DB.
	 * 
	 * @param importList
	 *            the import list
	 */
	private void insertAthletesToDB(List<List<String>> importList) {
		db = new DBConnection();
		for (List<String> line : importList) {
			Squad tempSquad = new Squad(Integer.parseInt(line
					.get(squadPositionImport)));
			tempSquad = db.getEm().find(Squad.class, tempSquad.getSquadId());
			Athlete tempAthlete = getAthleteFrom(line);
			db.persist(tempAthlete);
			addEmptyMarksTo(tempAthlete);
			tempSquad.addAthlet(tempAthlete);
			db.persist(tempSquad);

		}
		db.commit();

		db.closeConnection();
	}

	/**
	 * Gets the import list.
	 * 
	 * @return the import list
	 */
	private List<List<String>> getImportList() {
		db = new DBConnection();
		List<List<String>> importList = startList.getImportList();
		Set<Integer> squadsNrList = findSquadNumbers(importList);
		for (Integer squadNr : squadsNrList) {
			Squad tempSquad = new Squad(squadNr);
			db.persist(tempSquad);
		}
		db.commit();
		db.closeConnection();
		return importList;
	}

	/**
	 * Adds the empty marks to an Athlete and persists it to the DB
	 * 
	 * @param atemp
	 *            the atemp
	 */
	private void addEmptyMarksTo(Athlete atemp) {

		for (DeviceType dt : DeviceType.values()) {
			Mark mtemp = new Mark(0, 0, 0, 0, 0, 0);
			db.persist(mtemp);
			atemp.addMark(dt, mtemp);
		}
		db.persist(atemp);
	}

	/**
	 * Gets the athlete out of the imported String line
	 * 
	 * @param line
	 *            the line
	 * @return the athlete from
	 */
	private Athlete getAthleteFrom(List<String> line) {
		Athlete atemp = new Athlete(Integer.parseInt(line
				.get(squadPositionImport)), Integer.parseInt(line
				.get(startNrPositionImport)),
				line.get(progClassPositionImport),
				line.get(firstNamePositionImport),
				line.get(lastNamePositionImport),
				line.get(addressPositionImport), Integer.parseInt(line
						.get(yearPositionImport)),
				line.get(associationNamePositionImport) + " "
						+ line.get(associationPlacePositionImport));
		return atemp;
	}

}
