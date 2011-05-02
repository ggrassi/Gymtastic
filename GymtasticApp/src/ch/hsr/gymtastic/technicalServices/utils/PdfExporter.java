package ch.hsr.gymtastic.technicalServices.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.Squad;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfExporter {
	private GymCup gymCup = null;
	private String path = "";
	private Document document = null;
	private String competitionName = "";

	public PdfExporter(GymCup gymCup, String path) {
		this.gymCup = gymCup;
		this.path = path;
	}

	public void createTotalRankingList() throws FileNotFoundException,
			DocumentException {
		createFile();
		writeTotalContent();
		closeFile();
	}

	public void createCompetitionRankingList(String competitionName)
			throws FileNotFoundException, DocumentException {
		this.competitionName = competitionName;
		createFile();
		writeCompetitionContent();
		closeFile();
	}

	private void createFile() throws FileNotFoundException, DocumentException {
		document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(path));
		document.open();

	}

	private void writeTotalContent() throws DocumentException {
		writeTotalTitle();

		for (Competition competition : gymCup.getCompetitions()) {
			writeCompetition(competition);
		}

	}

	private void writeCompetitionContent() throws DocumentException {
		Competition competition = getCompetition();
		writeCompetition(competition);
	}

	private void writeCompetition(Competition competition)
			throws DocumentException {
		writeCompetitionTitle(competition);

		PdfPTable table = new PdfPTable(12);

		table.addCell("Rang");
		table.addCell("Vorname");
		table.addCell("Nachname");
		table.addCell("Geburtsdatum");
		table.addCell("Verein");
		table.addCell("Boden");
		table.addCell("Pferd");
		table.addCell("Ringe");
		table.addCell("Sprung");
		table.addCell("Barren");
		table.addCell("Reck");
		table.addCell("Endnote");

		int rank = 1;
		List<Athlete> rankingList = getSortedList(competition);
		for (Athlete athlete : rankingList) {

			table.addCell(rank + "");
			table.addCell(athlete.getFirstName());
			table.addCell(athlete.getLastName());
			table.addCell(athlete.getYearOfBirth() + "");
			table.addCell(athlete.getAssociation()+"");
			table.addCell(athlete.getMarks().get(DeviceType.FLOOR_EXCERCISE)
					.getFinalMark()
					+ "");
			table.addCell(athlete.getMarks().get(DeviceType.POMMEL_HORSE)
					.getFinalMark()
					+ "");
			table.addCell(athlete.getMarks().get(DeviceType.STILL_RINGS)
					.getFinalMark()
					+ "");
			table.addCell(athlete.getMarks().get(DeviceType.VAULT)
					.getFinalMark()
					+ "");
			table.addCell(athlete.getMarks().get(DeviceType.PARALLEL_BARS)
					.getFinalMark()
					+ "");
			table.addCell(athlete.getMarks().get(DeviceType.HIGH_BAR)
					.getFinalMark()
					+ "");
			table.addCell(athlete.getSumOfEndMarks() + "");

			rank++;
		}
		document.add(table);
		document.newPage();

	}

	public static PdfPTable createFirstTable() {
		PdfPTable table = new PdfPTable(12);
		
		table.addCell("Rang");
		table.addCell("Vorname");
		table.addCell("Nachname");
		table.addCell("Geburtsdatum");
		table.addCell("Verein");
		table.addCell("Boden");
		table.addCell("Pferd");
		table.addCell("Ringe");
		table.addCell("Sprung");
		table.addCell("Barren");
		table.addCell("Reck");
		table.addCell("Endnote");
		return table;
	}

	private void writeTotalTitle() throws DocumentException {

		Paragraph title = new Paragraph("Rangliste vom " + gymCup.getName()
				+ " in " + gymCup.getLocation() + " vom "
				+ gymCup.getStartDate() + " bis " + gymCup.getEndDate());
		title.setAlignment(Paragraph.ALIGN_CENTER);
		title.setSpacingAfter((float) 10.0);
		Font titleFont = new Font();
		titleFont.setColor(20, 50, 10);
		// titleFont.setSize((float) 50.0);
		title.setFont(titleFont);
		document.add(title);
	}

	private void writeCompetitionTitle(Competition competition)
			throws DocumentException {
		Paragraph title = new Paragraph("Wettkampf "
				+ competition.getDescription());
		title.setAlignment(Paragraph.ALIGN_LEFT);
		title.setSpacingAfter((float) 2.0);
		Font titleFont = new Font();
		title.setFont(titleFont);
		document.add(title);

	}

	private Competition getCompetition() {
		for (Competition competition : gymCup.getCompetitions()) {
			if (competition.getDescription().equals(competitionName))
				return competition;
		}
		return null;
	}

	private List<Athlete> getSortedList(Competition competition) {
		List<Athlete> list = new ArrayList<Athlete>();

		for (Squad squad : competition.getSquads()) {
			for (Athlete athlete : squad.getAthlets()) {
				list.add(athlete);
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
