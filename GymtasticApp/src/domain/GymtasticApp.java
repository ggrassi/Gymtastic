package domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import importer.ImportStartList;

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
	createSquad(startList);
	
	System.out.println(DeviceType.FLOOR_EXCERCISE);
	
	Evaluation eval = new Evaluation();
	eval.insertMark(new Mark(5,10,5,10,5,10,5));
	System.out.println(eval.getAverageMark());
	
	
    }

    private static void createSquad(ImportStartList startList) {
	
	//Set mit Riegen erstellen
	Set<Integer> squadsList = new TreeSet<Integer>();
	List<List<String>> importList = startList.getImportList();
	for (List<String> line : importList) {
	    squadsList.add(Integer.parseInt(line.get(squadPositionImport)));
	}
	
	// Ausgabe aller Riegennummern
	for (Integer squadNr : squadsList) {
	    System.out.println(squadNr);
	}
	
	// Anhand der Riegennummern die Riegen erstellen
	
    }

}
