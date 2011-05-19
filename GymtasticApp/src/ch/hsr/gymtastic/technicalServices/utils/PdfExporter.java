package ch.hsr.gymtastic.technicalServices.utils;

import ch.hsr.gymtastic.domain.GymCup;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

public class PdfExporter {
	protected GymCup gymCup = null;
	protected String path = "";
	protected Document document = null;
	protected String competitionName = "";
	protected Font totalRankingTitleFont = new Font(FontFamily.HELVETICA, 21, Font.BOLD,
			new BaseColor(0, 0, 0));
	protected Font competitionRankingTitleFont = new Font(FontFamily.HELVETICA, 18, Font.BOLDITALIC,
			new BaseColor(0, 0, 0));
	protected Font totalStartlistTitleFont = new Font(FontFamily.HELVETICA, 16, Font.BOLD,
			new BaseColor(0, 0, 0));
	protected Font competitionStartlistTitleFont = new Font(FontFamily.HELVETICA, 14, Font.BOLDITALIC,
			new BaseColor(0, 0, 0));
	protected Font squadStartlistTitleFont = new Font(FontFamily.HELVETICA, 12, Font.BOLDITALIC,
			new BaseColor(0, 0, 0));

	public PdfExporter(GymCup gymCup, String path) {
		this.gymCup = gymCup;
		this.path = path;
	}

	public void closeFile() {
		document.close();

	}

}
