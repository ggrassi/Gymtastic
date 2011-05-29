package ch.hsr.gymtastic.technicalServices.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ImportStartList
 */
public class ImportStartList {

	private String fileName;
	protected List<List<String>> importList = new ArrayList<List<String>>();

	/**
	 * Instantiates a new import start list.
	 */
	public ImportStartList() {
		super();
	
	}
	/**
	 * Instantiates a new import start list.
	 * 
	 * @param filePath
	 *            the file name
	 */
	public ImportStartList(String filePath) {
		this.fileName = filePath;
	}


	/**
	 * Gets the import list.
	 * 
	 * @return the import list
	 */
	public List<List<String>> getImportList() {
		return importList;
	}


	/**
	 * Reads the import of a appropriate CSV File and inserts line per line into
	 * the importList List
	 */
	public void readImport() {
		BufferedReader readbuffer = null;
		try {

			readbuffer = new BufferedReader(new FileReader(fileName));
			readLines(readbuffer);
			removeFirstLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeReadBuffer(readbuffer);
		}
	}

	private String readLines(BufferedReader readbuffer) throws IOException {
		String line;
		while ((line = readbuffer.readLine()) != null) {
			String[] splitArray = line.split("\t");
			List<String> importLine = new ArrayList<String>();
			for (int i = 0; i < splitArray.length; i++) {
				importLine.add(splitArray[i]);
			}
			importList.add(importLine);
		}
		return line;
	}

	private void closeReadBuffer(BufferedReader readbuffer) {
		try {
			readbuffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes the first line because its the header of the file.
	 */
	protected void removeFirstLine() {
		importList.remove(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
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
