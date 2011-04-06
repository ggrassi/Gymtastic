package exporter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import domain.Athlet;
import domain.Squad;

public class PdfExport {

    private Map<Integer, Squad> squads;

    public PdfExport(Map<Integer, Squad> squads) {
	this.squads = squads;
    }

    public void createStartList(String path) throws FileNotFoundException, DocumentException {
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream(path));
	document.open();
	Paragraph title = new Paragraph("Startliste\n");
	title.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(title);
	for (Squad squad : squads.values()) {
	    document.add(new Paragraph("\nRiege " + squad.getId()));
	    for (int i = 0; i < squad.getSquadSize(); i++) {
		Athlet athlet = squad.getAthlet(i);
		document.add(new Paragraph(athlet.getAthletId() + " " + athlet.getVorname() + " " + athlet.getNachname()));
	    }
	}
	document.close();
    }
}
