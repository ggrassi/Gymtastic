package domain;

import importer.ImportStartList;

import java.io.FileNotFoundException;
import java.util.Map;

import com.itextpdf.text.DocumentException;

import control.SquadCreator;
import exporter.PdfExport;

public class GymtasticApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println("******** Welcome to Gymtastic *************");

	/* Import the starter file */
	ImportStartList importList = new ImportStartList("src/importer/Startliste_Bsp.txt");
	System.out.println("******** Import *************");
	importList.readImport();
	importList.toString();

	/* generate Squads with importlist */
	System.out.println("******** Squad Generator *************");
	SquadCreator squadCreator = new SquadCreator(importList);
	Map<Integer, Squad> squads = squadCreator.createSquads();
	
	/* create a cup */
	
	/* create a competition in the cup */
	
	/* create the round allocation for the competition */
	System.out.println("******** Round Allocation Generator *************");
	RoundAllocation ra = new RoundAllocation(squads);
	System.out.println("Riege vor der Rotation");
	System.out.println(ra.getMap(0));
	System.out.println("Riege nach der Rotation");
	System.out.println(ra.roundChange(ra.getMap(0)));
	//
	
	/* create a startlist pdf */
	PdfExport export = new PdfExport(squads);
	try {
	    export.createStartList("src/exporter/Startliste.pdf");
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
	
	System.out.println("******** Good Bye *************");

    }

}
