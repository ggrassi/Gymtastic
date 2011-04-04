package application;

import importer.ImportStartList;

import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Map;

import com.itextpdf.text.DocumentException;

import control.SquadCreator;
import domain.Competition;
import domain.Gymcup;
import domain.RoundAllocation;
import domain.Squad;
import exporter.PdfExport;

public class GymtasticApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println("******** Welcome to Gymtastic *************");
    }

    public static void emulateCup() {


	/* create a cup */
	System.out.println("******** Cup *************");
	Gymcup cup = new Gymcup("HSR Cup", "Rapperswil");

	/* Import the starter file */
	ImportStartList importList = new ImportStartList("src/importer/Startliste_Bsp.txt");
	System.out.println("******** Import *************");
	importList.readImport();
	importList.toString();

	/* generate Squads with importlist */
	System.out.println("******** Squad Generator *************");
	SquadCreator squadCreator = new SquadCreator(importList);
	Map<Integer, Squad> squads = squadCreator.createSquads();

	/* create a competition in the cup */
	System.out.println("******** Competition *************");
	Competition competition1 = new Competition("Wettkampf1", new GregorianCalendar(2011, 04, 04, 16, 0, 0),
		"Wettkampf Programm 1");
	cup.addCompetition(competition1);

	/* Riegeneinteilung - die ersten 6 Riegen zum Wettkampf1 hinzufügen */
	System.out.println("******** Add squads to Competition *************");
	competition1.addSquad(squads.get(1));
	competition1.addSquad(squads.get(2));
	competition1.addSquad(squads.get(3));
	competition1.addSquad(squads.get(4));
	competition1.addSquad(squads.get(5));
	competition1.addSquad(squads.get(6));

	/* create the round allocation for the competition */
	System.out.println("******** Round Allocation Generator *************");
	RoundAllocation ra = new RoundAllocation(squads);
	System.out.println("Riege vor der Rotation");
	System.out.println(ra.getRoundAllocation(0));
	System.out.println("Riege nach der Rotation");
	System.out.println(ra.roundChange(ra.getRoundAllocation(0)));
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
