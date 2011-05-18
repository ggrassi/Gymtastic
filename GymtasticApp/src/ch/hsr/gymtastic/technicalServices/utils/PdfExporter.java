package ch.hsr.gymtastic.technicalServices.utils;


import ch.hsr.gymtastic.domain.GymCup;

import com.itextpdf.text.Document;

public class PdfExporter {
	protected GymCup gymCup = null;
	protected String path = "";
	protected Document document = null;
	protected String competitionName = "";

	public PdfExporter(GymCup gymCup, String path) {
		this.gymCup = gymCup;
		this.path = path;
	}

	public void closeFile() {
		document.close();

	}
	

}
