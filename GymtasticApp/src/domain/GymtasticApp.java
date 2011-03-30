package domain;

import importer.ImportStartList;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GymtasticApp {

    private static final int squadPositionImport = 0;

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println("******** Welcome to Gymtastic *************");

	/* Import the starter file */
	ImportStartList startList = new ImportStartList("src/importer/Startliste_Bsp.txt");
	System.out.println("******** Import *************");
	startList.readImport();
	startList.toString();

	/* generate Squads with importlist */
	System.out.println("******** Squad Generator *************");
	Map<Integer, Squad> squadMap = createSquads(startList);
	

    }

    private static Map<Integer, Squad> createSquads(ImportStartList startList) {
	List<List<String>> importList = startList.getImportList();

	Set<Integer> squadsNrList = new TreeSet<Integer>();
	for (List<String> line : importList) {
	    squadsNrList.add(Integer.parseInt(line.get(squadPositionImport)));
	}

	Map<Integer, Squad> squadMap = new TreeMap<Integer, Squad>();
	for (Integer squadNr : squadsNrList) {
	    squadMap.put(squadNr, new Squad(squadNr));
	}

	for (List<String> line : importList) {
	    squadMap.get(Integer.parseInt(line.get(0))).addAthlete(
		    new Athlet(Integer.parseInt(line.get(0)), Integer.parseInt(line.get(1)), line.get(2), line.get(3),
			    line.get(4), line.get(5), Integer.parseInt(line.get(6)), new Verein(line.get(7), line
				    .get(8))));
	}
	
	return squadMap;

    }
}
