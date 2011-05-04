package ch.hsr.gymtastic.technicalServices.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportStartList {

    private String fileName;
    private List<List<String>> importList = new ArrayList<List<String>>();

    public List<List<String>> getImportList() {
	return importList;
    }

    public ImportStartList(String fileName) {
	this.fileName = fileName;
    }

    public void readImport() {
	BufferedReader readbuffer = null;
	try {
		
	    readbuffer = new BufferedReader(new FileReader(fileName));
	    String line;

	    while ((line = readbuffer.readLine()) != null) {
		String splitArray[] = line.split("\t");
		List<String> importLine = new ArrayList<String>();
		for (int i = 0; i < splitArray.length; i++) {
		    importLine.add(splitArray[i]);
		}
		importList.add(importLine);
	    }
	    removeFirstLine();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		readbuffer.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    private void removeFirstLine() {
	importList.remove(0);
    }

    @Override
    public String toString() {
	for (List<String> list : importList) {
	    for (String item : list) {
		System.out.print(item + "\t");
	    }
	    System.out.println("");
	}
	return "";
    }
}
