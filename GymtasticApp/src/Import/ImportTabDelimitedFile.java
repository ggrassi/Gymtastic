package Import;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportTabDelimitedFile {

    /**
     * @param args
     * @throws IOException
     */
    private String startNr;
    private String squad;
    private String programClass;
    private String firstName;
    private String surName;
    private String address;
    private String birth;
    private String vereinsname;
    private String vereinsort;

    public ImportTabDelimitedFile(String[] persInfo) {
	startNr = persInfo[0];
	squad = persInfo[1];
	programClass = persInfo[2];
	firstName = persInfo[3];
	surName = persInfo[4];
	address = persInfo[5];
	birth = persInfo[6];
	vereinsname = persInfo[7];
	vereinsort = persInfo[8];
    }

    public static void main(String[] args) {
	BufferedReader readbuffer = null;
	try {
	    readbuffer = new BufferedReader(new FileReader("src/Import/Startliste_Bsp.txt"));
	    String line;
	    List<ImportTabDelimitedFile> importList = new ArrayList<ImportTabDelimitedFile>();
	    while ((line = readbuffer.readLine()) != null) {
		String splitArray[] = line.split("\t");
		importList.add(new ImportTabDelimitedFile(splitArray));
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	} finally{
	    try {
		readbuffer.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

}
