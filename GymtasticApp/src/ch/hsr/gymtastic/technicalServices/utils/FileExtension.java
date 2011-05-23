package ch.hsr.gymtastic.technicalServices.utils;

import java.io.File;
import java.util.ArrayList;

/**
 * The Class FileExtension holds an Array over all file extensions
 * which are desired
 */
public class FileExtension {

	public static ArrayList<String> extensions = new ArrayList<String>();

	/**
	 * Gets the extension of a file.
	 *
	 * @param f the f
	 * @return the extension
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	/**
	 * Adds the extension into the Array
	 *
	 * @param string the string
	 */
	public static void addExtension(String string) {
		extensions.add(string);

	}
}