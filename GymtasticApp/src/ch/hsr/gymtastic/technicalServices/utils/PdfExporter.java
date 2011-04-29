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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfExporter {
	private GymCup gymCup = null;
	private String path = "";
	private Document document = null;
	private int competitionNr = 0;

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

	public void createCompetitionRankingList(int competitionNr)
			throws FileNotFoundException, DocumentException {
		this.competitionNr = competitionNr;
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
			writeTotalTitle();
			int rank = 1;
			List<Athlete> rankingList = getSortedList(competition);
			for (Athlete athlete : rankingList) {
				document.add(new Paragraph(rank
						+ " "
						+ athlete.getFirstName()
						+ " "
						+ athlete.getLastName()
						+ " "
						+ athlete.getYearOfBirth()
						+ " "
						+ athlete.getAssociation()
						+ " "
						+ athlete.getMarks().get(DeviceType.FLOOR_EXCERCISE)
								.getFinalMark()
						+ " "
						+ athlete.getMarks().get(DeviceType.POMMEL_HORSE)
								.getFinalMark()
						+ " "
						+ athlete.getMarks().get(DeviceType.STILL_RINGS)
								.getFinalMark()
						+ " "
						+ athlete.getMarks().get(DeviceType.VAULT)
								.getFinalMark()
						+ " "
						+ athlete.getMarks().get(DeviceType.PARALLEL_BARS)
								.getFinalMark()
						+ " "
						+ athlete.getMarks().get(DeviceType.HIGH_BAR)
								.getFinalMark() + " "
						+ athlete.getSumOfEndMarks()));
				rank++;
			}

		}

	}

	private void writeCompetitionContent() throws DocumentException {
		writeCompetitionTitle();
		Competition competition = gymCup.getCompetitions().get(competitionNr);
		int rank = 1;
		List<Athlete> rankingList = getSortedList(competition);
		for (Athlete athlete : rankingList) {
			document.add(new Paragraph(rank
					+ " "
					+ athlete.getFirstName()
					+ " "
					+ athlete.getLastName()
					+ " "
					+ athlete.getYearOfBirth()
					+ " "
					+ athlete.getAssociation()
					+ " "
					+ athlete.getMarks().get(DeviceType.FLOOR_EXCERCISE)
							.getFinalMark()
					+ " "
					+ athlete.getMarks().get(DeviceType.POMMEL_HORSE)
							.getFinalMark()
					+ " "
					+ athlete.getMarks().get(DeviceType.STILL_RINGS)
							.getFinalMark()
					+ " "
					+ athlete.getMarks().get(DeviceType.VAULT).getFinalMark()
					+ " "
					+ athlete.getMarks().get(DeviceType.PARALLEL_BARS)
							.getFinalMark()
					+ " "
					+ athlete.getMarks().get(DeviceType.HIGH_BAR)
							.getFinalMark() + " " + athlete.getSumOfEndMarks()));
			rank++;
		}

	}

	private void writeTotalTitle() throws DocumentException {
		Paragraph title = new Paragraph("Rangliste vom " + gymCup.getName()
				+ " in " + gymCup.getLocation() + " vom "
				+ gymCup.getStartDate() + " bis " + gymCup.getEndDate());
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);
	}

	private void writeCompetitionTitle() throws DocumentException {
		Paragraph title = new Paragraph("Rangliste vom "
				+ gymCup.getName()
				+ " in "
				+ gymCup.getLocation()
				+ " vom "
				+ gymCup.getStartDate()
				+ " bis "
				+ gymCup.getEndDate()
				+ gymCup.getCompetitions().get(competitionNr).getDescription()
						.toUpperCase());
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);
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
