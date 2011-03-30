package domain;

import java.util.List;

import importer.ImportStartList;

public class GymtasticApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println("******** Welcome to Gymtastic *************");

	/* Import the starter file */
	ImportStartList startList = new ImportStartList("src/importer/Startliste_Bsp.txt");
	startList.readImport();
	startList.toString();

    }

}
