package control;

import importer.ImportStartList;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import domain.Association;
import domain.Athlet;
import domain.Squad;

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

    
    private int nonsense;
    private ImportStartList startList;
    private DBConnection db;

    public SquadCreator(ImportStartList importStartList) {
	this.startList = importStartList;
    }

    public Map<Integer, Squad> createSquads() {
	List<List<String>> importList = startList.getImportList();

	Set<Integer> squadsNrList = findSquadNumbers(importList);

	Map<Integer, Squad> squadMap = new TreeMap<Integer, Squad>();
	for (Integer squadNr : squadsNrList) {
	    squadMap.put(squadNr, new Squad(squadNr));
	}

	for (List<String> line : importList) {
	    squadMap.get(Integer.parseInt(line.get(squadPositionImport))).addAthlet(
		    new Athlet(Integer.parseInt(line.get(squadPositionImport)), Integer.parseInt(line
			    .get(startNrPositionImport)), line.get(progClassPositionImport), line
			    .get(firstNamePositionImport), line.get(lastNamePositionImport), line
			    .get(addressPositionImport), Integer.parseInt(line.get(yearPositionImport)),
			    new Association(line.get(associationNamePositionImport), line
				    .get(associationPlacePositionImport))));
	}

	return squadMap;

    }

    private Set<Integer> findSquadNumbers(List<List<String>> importList) {
	Set<Integer> squadsNrList = new TreeSet<Integer>();
	for (List<String> line : importList) {
	    squadsNrList.add(Integer.parseInt(line.get(squadPositionImport)));
	}
	return squadsNrList;
    }

    public void insertImportToDB() {
	db = new DBConnection();
	List<List<String>> importList = startList.getImportList();
	Set<Integer> squadsNrList = findSquadNumbers(importList);
	for (Integer squadNr : squadsNrList) {
	    db.persist(new Squad(squadNr));
	}
	db.commit();
	db.closeConnection();
	db = new DBConnection();
	for (List<String> line : importList) {
	    // db.begin();
	    Squad temp = new Squad(Integer.parseInt(line.get(squadPositionImport)));
	    temp = db.getEm().find(Squad.class, temp.getSquadId());

	    Athlet atemp = new Athlet(Integer.parseInt(line.get(squadPositionImport)), Integer.parseInt(line
		    .get(startNrPositionImport)), line.get(progClassPositionImport), line.get(firstNamePositionImport),
		    line.get(lastNamePositionImport), line.get(addressPositionImport), Integer.parseInt(line
			    .get(yearPositionImport)), new Association(line.get(associationNamePositionImport), line
			    .get(associationPlacePositionImport)));
	    db.persist(atemp);
	    temp.addAthlet(atemp);
	    db.persist(temp);

	}
	db.commit();

	db.closeConnection();
    }

}
