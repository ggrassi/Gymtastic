package ch.hsr.gymtastic.technicalServices.utils;

import java.io.File;
import java.util.ArrayList;

/**
 * The Class FileExtension holds an Array over all file extensions which are
 * desired
 */
public final class FileExtension {

	private static ArrayList<String> extensions = new ArrayList<String>();

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
	 * @param string
	 *            the string
	 */
	public static void addExtension(String string) {
		getExtensions().add(string);

	}

	/**
	 * Returns the field extensions
	 * 
	 * @return extensions
	 */
	public static ArrayList<String> getExtensions() {
		return extensions;
	}
}
