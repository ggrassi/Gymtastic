package ch.hsr.gymtastic.application.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.persistence.TypedQuery;
import javax.swing.table.AbstractTableModel;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;

public class AthleteDataTableModel extends AbstractTableModel implements
		Observer {

	private AthleteModel athleteModel = null;
	private static final long serialVersionUID = 1L;
	private String[] columns = { "Vorname", "Nachname", "Jahrgang",
			"Leistungsklasse"};
	private List<Athlete> athletes = new ArrayList<Athlete>();
	
	

	public AthleteDataTableModel(AthleteModel athleteModel) {
		this.athleteModel = athleteModel;
		this.athleteModel.addObserver(this);
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
		if (athleteModel.getModelController().getGymCup() != null) {
			return athletes.size();
		} else {
			return 0;
		}
	}

	private void getAthletes() {
		DBConnection db = new DBConnection();
		System.out.println(DBConnection.getPath());
		TypedQuery<Athlete> query = db.getEm().createQuery("SELECT p FROM Athlete p", Athlete.class);
		List<Athlete> result = query.getResultList();
		for (Athlete athlete : result) {
			athletes.add(athlete);
		}
		db.commit();
		db.closeConnection();	
		
		
//		for (Competition competition : athleteModel.getModelController().getGymCup().getCompetitions()) {
//			for (Squad squad : competition.getSquads()) {
//				for (Athlete athlete : squad.getAthlets()) {
//					athletes.add(athlete);
//					System.out.println("getAthletes in athleteDataTableModel");
//				}
//			}
//		}
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
		System.out.println("athleteDataTableUpdate");
		fireTableDataChanged();
	}

}
