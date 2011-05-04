package ch.hsr.gymtastic.presentation.imports;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileExtensionFilter extends FileFilter{

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = Utils.getExtension(f);
		if (extension != null && !Utils.extensions.isEmpty()) {
			for (String ext : Utils.extensions) {
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
		Utils.addExtension(string);
		
	}

	@Override
	public String getDescription() {
		
		return null;
	}
}
