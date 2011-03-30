package Import;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    private String Vereinsort;

    public ImportTabDelimitedFile(String[] persInfo) {
	startNr = persInfo[0];
	squad = persInfo[1];
	programClass = persInfo[2];
	firstName = persInfo[3];
	surName = persInfo[4];
	address = persInfo[5];
	birth = persInfo[6];
	vereinsname = persInfo[7];
	Vereinsort = persInfo[8];
    }

    public static void main(String[] args) {
	BufferedReader readbuffer = null;
	try {
	    readbuffer = new BufferedReader(new FileReader("src/Import/Startliste_Bsp.txt"));

	    String strRead;
	    List<ImportTabDelimitedFile> importList = new ArrayList<ImportTabDelimitedFile>();
	    while ((strRead = readbuffer.readLine()) != null) {
		String splitArray[] = strRead.split("\t");
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

    public void setStartNr(String startNr) {
	this.startNr = startNr;
    }

    public String getStartNr() {
	return startNr;
    }

    public void setSquad(String squad) {
	this.squad = squad;
    }

    public String getSquad() {
	return squad;
    }

    public void setProgramClass(String programClass) {
	this.programClass = programClass;
    }

    public String getProgramClass() {
	return programClass;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setSurName(String surName) {
	this.surName = surName;
    }

    public String getSurName() {
	return surName;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getAddress() {
	return address;
    }

    public void setBirth(String birth) {
	this.birth = birth;
    }

    public String getBirth() {
	return birth;
    }

    public void setVereinsname(String vereinsname) {
	this.vereinsname = vereinsname;
    }

    public String getVereinsname() {
	return vereinsname;
    }

    public void setVereinsort(String vereinsort) {
	Vereinsort = vereinsort;
    }

    public String getVereinsort() {
	return Vereinsort;
    }

}
