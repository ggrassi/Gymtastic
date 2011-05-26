package ch.hsr.gymtastic.technicalServices.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * The Class FileExtensionFilter filters the allowed Files from the not allowed
 * files, which have the wrong file extension
 */
public class FileExtensionFilter extends FileFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = FileExtension.getExtension(f);
		if (extension != null && !FileExtension.extensions.isEmpty()) {
			for (String ext : FileExtension.extensions) {
				if (extension.equals(ext)) {
					return true;
				} else {
					return false;
				}
			}
		}

		return false;
	}

	/**
	 * Adds an extension to a FileExtension object
	 * 
	 * @param string
	 *            the string
	 */
	public void addExtension(String string) {
		FileExtension.addExtension(string);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {

		return null;
	}
}
