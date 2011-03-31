package domain;

import importer.ImportStartList;
import java.util.Map;

import control.DBConnection;
import control.SquadCreator;

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
	
	System.out.println("******** DB *************");
	System.out.println("DB Connection");
	DBConnection db = new DBConnection();
	
	db.insert(squads);
	db.closeConnection();
	//db.getAllSquads();
	
	
	
	RoundAllocation ra = new RoundAllocation(squads);
	System.out.println(ra.getRoundAllocation(0));
	System.out.println(ra.roundChange(ra.getRoundAllocation(0)));
	System.out.println(DeviceType.FLOOR_EXCERCISE.getIndex());
	
	System.out.println("******** Good Bye *************");

    }

}
