package ch.hsr.gymtastic.technicalServices.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileExtensionFilter extends FileFilter{

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = FileExtension.getExtension(f);
		if (extension != null && !FileExtension.extensions.isEmpty()) {
			for (String ext : FileExtension.extensions) {
				System.out.println("accept: "+ext);
				if(extension.equals(ext)){
					return true;
				}else{
					System.out.println("Falsches Format");
					return false;
				}
			}
		}

		return false;
	}

	public void addExtension(String string) {
		FileExtension.addExtension(string);
		
	}

	@Override
	public String getDescription() {
		
		return null;
	}
}
