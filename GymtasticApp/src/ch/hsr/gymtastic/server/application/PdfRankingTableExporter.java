package ch.hsr.gymtastic.server.application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.technicalServices.utils.DateFormatConverter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * The Class PdfRankingTableExporter defines how the Ranking PDF is formatted
 * and generated.
 */
public class PdfRankingTableExporter extends PdfExporter {

	private String programClassName = "";
	private DecimalFormat finalMarkFormat = new DecimalFormat("#0.00");

	/**
	 * Instantiates a new pdf ranking table exporter.
	 * 
	 * @param gymCup
	 *            the gym cup
	 * @param path
	 *            the path
	 */
	public PdfRankingTableExporter(GymCup gymCup, String path) {
		super(gymCup, path);
	}

	/**
	 * Creates the total ranking list which contains the ranking of the Athletes
	 * 
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws DocumentException
	 *             the document exception
	 */
	public void createTotalRankingList() throws FileNotFoundException,
			DocumentException {
		createFile();
		writeTotalContent();
		closeFile();
	}

	/**
	 * Creates the program class ranking list per Programm Class
	 * 
	 * @param programClass
	 *            the program class
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws DocumentException
	 *             the document exception
	 */
	public void createProgramClassRankingList(String programClass)
			throws FileNotFoundException, DocumentException {
		this.programClassName = programClass;
		createFile();
		writeProgramClassContent();
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
		document = new Document(PageSize.LETTER.rotate());
		PdfWriter.getInstance(document, new FileOutputStream(path));
		document.open();

	}

	/**
	 * Write the Content for the PDF File
	 * 
	 * @throws DocumentException
	 *             the document exception
	 */
	private void writeTotalContent() throws DocumentException {
		writeTotalTitle();

		for (String programClass : gymCup.getProgramClasses()) {
			writeProgramClass(programClass);
		}

	}

	/**
	 * Write the content of the program class for the PDF File
	 * 
	 * @throws DocumentException
	 *             the document exception
	 */
	private void writeProgramClassContent() throws DocumentException {
		String programClass = getProgramClass();
		writeTotalTitle();
		writeProgramClass(programClass);
	}

	/**
	 * Write the detailed content from the Athletes to the PDF File
	 * 
	 * @param programClass
	 *            the program class
	 * @throws DocumentException
	 *             the document exception
	 */
	private void writeProgramClass(String programClass)
			throws DocumentException {

		writeProgramClassTitle(programClass);
		table = createTable();
		int rank = 1;

		for (Athlete athlete : getSortedList(programClass)) {

			addAthleteInformation(rank, athlete);
			for (DeviceType deviceType : DeviceType.values()) {
				table.addCell(finalMarkFormat.format(athlete.getMarks()
						.get(deviceType).getFinalMark()));
			}
			table.addCell(finalMarkFormat.format(athlete.getSumOfEndMarks()));

			rank++;
		}
		document.add(table);
		document.newPage();

	}

	private void addAthleteInformation(int rank, Athlete athlete) {
		table.addCell(rank + "");
		table.addCell(athlete.getFirstName());
		table.addCell(athlete.getLastName());
		table.addCell(athlete.getYearOfBirth() + "");
		table.addCell(athlete.getAssociation() + "");
	}

	/**
	 * Creates the table in which the details of the Athletes appear
	 * 
	 * @return the pdf p table
	 */
	private PdfPTable createTable() {
		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(100);

		int[] widths = { 60, 120, 120, 120, 200, 70, 70, 70, 70, 70, 70, 80 };

		try {
			table.setWidths(widths);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		addTableHeader(table);
		return table;
	}

	private void addTableHeader(PdfPTable table) {
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
	}

	/**
	 * Write the title of the total column
	 * 
	 * @throws DocumentException
	 *             the document exception
	 */
	private void writeTotalTitle() throws DocumentException {

		Paragraph title = new Paragraph("Rangliste vom " + gymCup.getName() + " in " + gymCup.getLocation() + " vom "
				+ DateFormatConverter.convertDateToString(gymCup.getStartDate())
				+ " bis "
				+ DateFormatConverter.convertDateToString(gymCup.getEndDate()),
				totalRankingTitleFont);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		title.setSpacingAfter((float) 10.0);
		document.add(title);
	}

	/**
	 * Write the program class title.
	 * 
	 * @param programClass
	 *            the program class
	 * @throws DocumentException
	 *             the document exception
	 */
	private void writeProgramClassTitle(String programClass)
			throws DocumentException {
		Paragraph title = new Paragraph("Programm Klasse " + programClass,
				programClassRankingTitleFont);
		title.setAlignment(Paragraph.ALIGN_LEFT);
		title.setSpacingAfter((float) 2.0);
		document.add(title);

	}

	private String getProgramClass() {
		for (String programClass : gymCup.getProgramClasses()) {
			if (programClass.equals(programClassName)) {
				return programClass;
			}
		}
		return null;
	}

	/**
	 * Gets a sorted list of Athletes per ProgramClass
	 * 
	 * @param programClass
	 *            the program class
	 * @return the sorted list
	 */
	private List<Athlete> getSortedList(String programClass) {
		List<Athlete> list = new ArrayList<Athlete>();

		for (Athlete athlete : gymCup.getAllAthletes()) {
			if (athlete.getPrgClass().equals(programClass)) {
				list.add(athlete);
			}
		}

		java.util.Collections.sort(list, comperator);
		return list;
	}

	/** The comperator. */
	protected Comparator<Athlete> comperator = new Comparator<Athlete>() {
		@Override
		public int compare(Athlete athlete1, Athlete athlete2) {
			if (athlete1.getSumOfEndMarks() < athlete2.getSumOfEndMarks()) {
				return 1;
			} else if (athlete1.getSumOfEndMarks() > athlete2
					.getSumOfEndMarks()) {
				return 0;
			} else {
				return 0;
			}
		}
	};
	private PdfPTable table;

}
