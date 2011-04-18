package ch.hsr.gymtastic.presentation.imports;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;

public class Utils {

	public static ArrayList<String> extensions = new ArrayList<String>();

	/*
	 * Get the extension of a file.
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

	public static void addExtension(String string) {
		extensions.add(string);

	}
}