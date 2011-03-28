/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author ggrassi
 */
public class CsvImporter {

	private String path;
	private FileReader csvFile = null;
	private BufferedReader br = null;
	private final List<String> attributesLine = new ArrayList<String>();
	private ArrayList<String[]> valuesArray;
	public CsvImporter(String path) {
		this.path = path;
	}

	public void readFile() {
		try {
			csvFile = new FileReader(path);
			br = new BufferedReader(csvFile);
			String line;
			while ((line = br.readLine()) != null) {
				attributesLine.add(line);
				int cnt = 0;
				for (final String line1 : attributesLine) {
					//generates one String[]-attrubutes of one athlet per line
					valuesArray.add(cnt++, line1.split(","));
				}
			}
		} catch (IOException e) {
			System.err.println("Error2 :" + e);
		} finally {
			try {
				br.close();
				csvFile.close();
			} catch (IOException e) {
				System.err.println("Error2 :" + e);
			}
		}

		

	}

}