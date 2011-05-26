package ch.hsr.gymtastic.server.application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.utils.DateFormatConverter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * The Class PdfStartlistExporter defines how the Ranking PDF is formatted and
 * generated.
 */
public class PdfStartlistExporter extends PdfExporter {

    private String competitionName = "";

    /**
     * Instantiates a new pdf startlist exporter.
     * 
     * @param gymCup
     *            the gym cup
     * @param path
     *            the path
     */
    public PdfStartlistExporter(GymCup gymCup, String path) {
	super(gymCup, path);
    }

    /**
     * Creates the total startlist which contains the startlist for the Athletes
     * 
     * @throws FileNotFoundException
     *             the file not found exception
     * @throws DocumentException
     *             the document exception
     */
    public void createTotalStartlist() throws FileNotFoundException, DocumentException {
	createFile();
	writeTotalContent();
	closeFile();
    }

    /**
     * Creates the competition startlist which contains the startlist for the
     * Athletes
     * 
     * @param competitionName
     *            the competition name
     * @throws FileNotFoundException
     *             the file not found exception
     * @throws DocumentException
     *             the document exception
     */
    public void createCompetitionStartlist(String competitionName) throws FileNotFoundException, DocumentException {
	this.competitionName = competitionName;
	createFile();
	writeCompetitionContent();
	closeFile();
    }

    /**
     * Creates the PDF file.
     * 
     * @throws FileNotFoundException
     *             the file not found exception
     * @throws DocumentException
     *             the document exception
     */
    private void createFile() throws FileNotFoundException, DocumentException {
	document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(path));
	document.open();
    }

    /**
     * Write the total content for the PDF file
     * 
     * @throws DocumentException
     *             the document exception
     */
    private void writeTotalContent() throws DocumentException {
	writeTotalTitle();

	for (Competition competition : gymCup.getCompetitions()) {
	    writeCompetition(competition);
	}
    }

    /**
     * Write the competition content into the PDF
     * 
     * @throws DocumentException
     *             the document exception
     */
    private void writeCompetitionContent() throws DocumentException {
	Competition competition = getCompetition();
	writeTotalTitle();
	writeCompetition(competition);
    }

    /**
     * Writes the formats for the competition into the PDF
     * 
     * @param competition
     *            the competition
     * @throws DocumentException
     *             the document exception
     */
    private void writeCompetition(Competition competition) throws DocumentException {

	writeCompetitionTitle(competition);

	for (Squad squad : competition.getSquads()) {
	    writeSquadTitle(squad);
	    for (Athlete athlete : squad.getAthlets()) {
		Paragraph athleteParagraph = new Paragraph(athlete.getFirstName() + " " + athlete.getLastName() + " "
			+ athlete.getYearOfBirth() + " " + athlete.getAssociation().getName());
		document.add(athleteParagraph);
	    }
	    document.add(new Paragraph(" "));
	}
	document.newPage();
    }

    /**
     * Write the squad title into the PDF
     * 
     * @param squad
     *            the squad
     * @throws DocumentException
     *             the document exception
     */
    private void writeSquadTitle(Squad squad) throws DocumentException {
	Paragraph title = new Paragraph(
		"Riege " + squad.getSquadId() + " (" + squad.getAthlets().size() + " Athleten)",
		squadStartlistTitleFont);
	document.add(title);
    }

    /**
     * Write the total title into the PDF
     * 
     * @throws DocumentException
     *             the document exception
     */
    private void writeTotalTitle() throws DocumentException {

	Paragraph title = new Paragraph("Startliste vom " + gymCup.getName() + " in " + gymCup.getLocation() + " vom "
		+ DateFormatConverter.convertDateToString(gymCup.getStartDate()) + " bis "
		+ DateFormatConverter.convertDateToString(gymCup.getEndDate()), totalStartlistTitleFont);
	title.setAlignment(Paragraph.ALIGN_CENTER);
	title.setSpacingAfter((float) 10.0);
	document.add(title);
    }

    /**
     * Write the competition title into the PDF
     * 
     * @param competition
     *            the competition
     * @throws DocumentException
     *             the document exception
     */
    private void writeCompetitionTitle(Competition competition) throws DocumentException {
	Paragraph title = new Paragraph("Wettkampf " + competition.getDescription(), competitionStartlistTitleFont);
	title.setAlignment(Paragraph.ALIGN_LEFT);
	title.setSpacingAfter((float) 2.0);

	document.add(title);

    }

    /**
     * Gets the competition.
     * 
     * @return the competition
     */
    private Competition getCompetition() {
	for (Competition competition : gymCup.getCompetitions()) {
	    if (competition.getDescription().equals(competitionName)) {
		return competition;
	    }
	}
	return null;
    }
}
