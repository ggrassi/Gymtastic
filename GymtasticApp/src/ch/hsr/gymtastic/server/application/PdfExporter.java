package ch.hsr.gymtastic.server.application;

import ch.hsr.gymtastic.domain.GymCup;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

/**
 * The Class PdfExporter creates a PDF
 */
public class PdfExporter {

	protected GymCup gymCup = null;
	protected String path = "";
	protected Document document = null;
	protected Font totalRankingTitleFont = new Font(FontFamily.HELVETICA, 21,
			Font.BOLD, new BaseColor(0, 0, 0));
	protected Font programClassRankingTitleFont = new Font(
			FontFamily.HELVETICA, 18, Font.BOLDITALIC, new BaseColor(0, 0, 0));
	protected Font totalStartlistTitleFont = new Font(FontFamily.HELVETICA, 16,
			Font.BOLD, new BaseColor(0, 0, 0));
	protected Font competitionStartlistTitleFont = new Font(
			FontFamily.HELVETICA, 14, Font.BOLDITALIC, new BaseColor(0, 0, 0));
	protected Font squadStartlistTitleFont = new Font(FontFamily.HELVETICA, 12,
			Font.BOLDITALIC, new BaseColor(0, 0, 0));

	/**
	 * Instantiates a new pdf exporter.
	 * 
	 * @param gymCup
	 *            the gym cup
	 * @param path
	 *            the path
	 */
	public PdfExporter(GymCup gymCup, String path) {
		this.gymCup = gymCup;
		this.path = path;
	}

	/**
	 * Close file.
	 */
	public void closeFile() {
		document.close();

	}

}
