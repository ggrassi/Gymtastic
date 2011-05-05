package ch.hsr.gymtastic.application.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.application.controller.server.GymCupController;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.Squad;

public class AthleteDataTableModel extends AbstractTableModel implements
		Observer {

	private static final long serialVersionUID = 1L;
	private String[] columns = { "Vorname", "Nachname", "Jahrgang",
			"Leistungsklasse"};
	private List<Athlete> athletes = new ArrayList<Athlete>();
	private final GymCupController gymCupController;

	
	public AthleteDataTableModel(GymCupController gymCupController) {
		this.gymCupController = gymCupController;
		this.gymCupController.getGymCup().addObserver(this);
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		if (gymCupController.getGymCup() != null) {
			return athletes.size();
		} else {
			return 0;
		}
	}

	private void getAthletes() {
		for (Competition competition : gymCupController.getGymCup().getCompetitions()) {
			for (Squad squad : competition.getSquads()) {
				for (Athlete athlete : squad.getAthlets()) {
					athletes.add(athlete);
					System.out.println("getAthletes in athleteDataTableModel");
				}
			}
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Athlete athlete = athletes.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return athlete.getFirstName();
		case 1:
			return athlete.getLastName();
		case 2:
			return athlete.getYearOfBirth();
		case 3:
			return athlete.getPrgClass();
		
		}
		return "";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		getAthletes();
		fireTableDataChanged();
	}

}
