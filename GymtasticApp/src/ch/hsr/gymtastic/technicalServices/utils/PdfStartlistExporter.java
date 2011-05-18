package ch.hsr.gymtastic.technicalServices.utils;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.Squad;

public class PdfStartlistExporter extends PdfExporter {

	public PdfStartlistExporter(GymCup gymCup, String path) {
		super(gymCup, path);
	}

	public void createTotalStartlist() throws FileNotFoundException,
			DocumentException {
		createFile();
		writeTotalContent();
		closeFile();
	}

	public void createCompetitionStartlist(String competitionName)
			throws FileNotFoundException, DocumentException {
		this.competitionName = competitionName;
		createFile();
		writeCompetitionContent();
		closeFile();
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
		
		
		for (Squad squad : competition.getSquads()) {
			writeSquadTitle(squad);
			for (Athlete athlete : squad.getAthlets()) {
				Paragraph athleteParagraph = new Paragraph(athlete.getFirstName() + " " + athlete.getLastName() + " " + athlete.getYearOfBirth() + " " + athlete.getAssociation().getName());
				document.add(athleteParagraph);
			}
			document.add(new Paragraph(" "));
		}
		
		document.newPage();

	}

	private void writeSquadTitle(Squad squad) throws DocumentException {
		Paragraph title = new Paragraph("Riege " + squad.getSquadId() + " (" + squad.getAthlets().size() +" Athleten)");
//		title.setAlignment(Paragraph.ALIGN_CENTER);
//		title.setSpacingAfter((float) 10.0);
//		Font titleFont = new Font();
//		titleFont.setColor(20, 50, 10);
//		title.setFont(titleFont);
		document.add(title);		
	}

	

	private void writeTotalTitle() throws DocumentException {

		Paragraph title = new Paragraph(
				"Startliste vom "
						+ gymCup.getName()
						+ " in "
						+ gymCup.getLocation()
						+ " vom "
						+ DateFormatConverter.convertDateToString(gymCup
								.getStartDate())
						+ " bis "
						+ DateFormatConverter.convertDateToString(gymCup
								.getEndDate()));
		title.setAlignment(Paragraph.ALIGN_CENTER);
		title.setSpacingAfter((float) 10.0);
		Font titleFont = new Font();
		titleFont.setColor(20, 50, 10);
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
}
