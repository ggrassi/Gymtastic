package ch.hsr.gymtastic.technicalServices.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.Squad;

public class PdfRankingTableExporter extends PdfExporter {

	public PdfRankingTableExporter(GymCup gymCup, String path) {
		super(gymCup, path);
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
	
	private void createFile() throws FileNotFoundException,
	DocumentException {
		document = new Document(PageSize.LETTER.rotate());
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
		writeTotalTitle();
		writeCompetition(competition);
	}

	private void writeCompetition(Competition competition)
			throws DocumentException {
		
		writeCompetitionTitle(competition);
		PdfPTable table = createTable();

		int rank = 1;
		List<Athlete> rankingList = getSortedList(competition);
		for (Athlete athlete : rankingList) {

			table.addCell(rank + "");
			table.addCell(athlete.getFirstName());
			table.addCell(athlete.getLastName());
			table.addCell(athlete.getYearOfBirth() + "");
			table.addCell(athlete.getAssociation() + "");
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

	private static PdfPTable createTable() {
		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(100);
		
		int[] widths = new int[12];
		widths[0] = 60;
		widths[1] = 120;
		widths[2] = 120;
		widths[3] = 120;
		widths[4] = 200;
		widths[5] = 70;
		widths[6] = 70;
		widths[7] = 70;
		widths[8] = 70;
		widths[9] = 70;
		widths[10] = 70;
		widths[11] = 80;

		try {
			table.setWidths(widths);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		table.addCell("Rang");
		table.addCell("Vorname");
		table.addCell("Nachname");
		table.addCell("Jahrgang");
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

		
		Paragraph title = new Paragraph(
				"Rangliste vom "
						+ gymCup.getName()
						+ " in "
						+ gymCup.getLocation()
						+ " vom "
						+ DateFormatConverter.convertDateToString(gymCup
								.getStartDate())
						+ " bis "
						+ DateFormatConverter.convertDateToString(gymCup
								.getEndDate()), totalRankingTitleFont);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		title.setSpacingAfter((float) 10.0);
		document.add(title);
	}

	private void writeCompetitionTitle(Competition competition)
			throws DocumentException {
		Paragraph title = new Paragraph("Wettkampf "
				+ competition.getDescription(),competitionRankingTitleFont);
		title.setAlignment(Paragraph.ALIGN_LEFT);
		title.setSpacingAfter((float) 2.0);
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

	

	protected Comparator<Athlete> comperator = new Comparator<Athlete>() {
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
