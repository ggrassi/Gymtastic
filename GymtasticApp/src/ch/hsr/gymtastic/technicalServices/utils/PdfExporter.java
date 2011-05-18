package ch.hsr.gymtastic.technicalServices.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Comparator;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.GymCup;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfExporter {
	protected GymCup gymCup = null;
	private String path = "";
	protected Document document = null;
	protected String competitionName = "";

	public PdfExporter(GymCup gymCup, String path) {
		this.gymCup = gymCup;
		this.path = path;
	}

	protected void createFile() throws FileNotFoundException, DocumentException {
		document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(path));
		document.open();

	}

	public void closeFile() {
		document.close();

	}

	

}
