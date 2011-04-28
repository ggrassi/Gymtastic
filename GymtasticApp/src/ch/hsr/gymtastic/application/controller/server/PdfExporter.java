package ch.hsr.gymtastic.application.controller.server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.Squad;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfExporter {
	private GymCup gymCup = null;
	private String path = "";
	private Document document = null;

	public PdfExporter(GymCup gymCup, String path) {
		this.gymCup = gymCup;
		this.path = path;
	}

	public void createRankingList() throws FileNotFoundException,
			DocumentException {
		createFile();
		writeContent();
		closeFile();
	}

	private void createFile() throws FileNotFoundException, DocumentException {
		document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(path));
		document.open();
	}

	private void writeContent() throws DocumentException {
		Paragraph title = new Paragraph("Rangliste vom " + gymCup.getName()
				+ " in " + gymCup.getLocation() + " vom "
				+ gymCup.getStartDate() + " bis " + gymCup.getEndDate());
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);

		List rankingList = getSortedList();
		
		

	}

	private List<Athlete> getSortedList() {
		List<Athlete> list = new ArrayList<Athlete>();
		for (Competition competition : gymCup.getCompetitions()) {
			for (Squad squad : competition.getSquads()) {
				for (Athlete athlete : squad.getAthlets()) {
					list.add(athlete);
				}
			}
		}
		
		java.util.Collections.sort(list, comperator);

		return list;
	}

	private void closeFile() {
		document.close();

	}

	Comparator<Athlete> comperator = new Comparator<Athlete>() {

		@Override
		public int compare(Athlete athlete1, Athlete athlete2) {
			if (athlete1.getSumOfEndMarks() > athlete2.getSumOfEndMarks()) {
				return 1;
			} else if (athlete1.getSumOfEndMarks() < athlete2
					.getSumOfEndMarks()) {
				return 0;
			} else {
				return 0;
			}
		}
	};

}
