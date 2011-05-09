package ch.hsr.gymtastic.client.presentation.frames.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ch.hsr.gymtastic.client.presentation.models.AthleteOverviewTableModel;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ActualSquadPanel extends JPanel {
	/**
     * 
     */
	private static final long serialVersionUID = 9138092871453323277L;

	private JTable tableOverview;
	private JPanel panelAthletesTable;
	private JPanel panelAthletes;
	private JScrollPane scrollPane;
	private AthleteOverviewTableModel tableOverviewModel;
	private Squad actualSquad;
	private DeviceType deviceType;
	private JPanel panelAthleteInformation;
	private JPanel panelControl;
	private JButton btnFinishEvaluation;
	private JLabel lblStatusInfo;
	private JPanel panelAthleteControl;
	private JButton btnEvaluateAthlete;

	public ActualSquadPanel(Squad actualSquad, DeviceType deviceType) {
		this.actualSquad = actualSquad;
		this.deviceType = deviceType;
		initGUI();
		initListeners();
	}

	private void initGUI() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		tableOverviewModel = new AthleteOverviewTableModel(actualSquad, deviceType);
				
				panelAthleteInformation = new JPanel();
				panelAthleteInformation.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_panelAthleteInformation = new GridBagConstraints();
				gbc_panelAthleteInformation.insets = new Insets(0, 0, 5, 0);
				gbc_panelAthleteInformation.fill = GridBagConstraints.BOTH;
				gbc_panelAthleteInformation.gridx = 0;
				gbc_panelAthleteInformation.gridy = 0;
				add(panelAthleteInformation, gbc_panelAthleteInformation);
				panelAthleteInformation.setLayout(new BorderLayout(0, 0));
				
				panelControl = new JPanel();
				panelAthleteInformation.add(panelControl, BorderLayout.SOUTH);
				panelControl.setLayout(new BorderLayout(0, 0));
				
				btnFinishEvaluation = new JButton("Bewertung abschliessen");
				panelControl.add(btnFinishEvaluation, BorderLayout.EAST);
				
				lblStatusInfo = new JLabel("Sie haben 5 von 7 Athleten bewertet.");
				panelAthleteInformation.add(lblStatusInfo, BorderLayout.CENTER);
		
				panelAthletesTable = new JPanel();
				panelAthletesTable.setBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"), "Athleten",
						TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 0)));
				GridBagConstraints gbc_panelAthletesTable = new GridBagConstraints();
				gbc_panelAthletesTable.fill = GridBagConstraints.BOTH;
				gbc_panelAthletesTable.gridx = 0;
				gbc_panelAthletesTable.gridy = 1;
				add(panelAthletesTable, gbc_panelAthletesTable);
						panelAthletesTable.setLayout(new BorderLayout(0, 0));
				
						panelAthletes = new JPanel();
						panelAthletesTable.add(panelAthletes);
						GridBagLayout gbl_panelAthletes = new GridBagLayout();
						gbl_panelAthletes.columnWidths = new int[] { 0, 0 };
						gbl_panelAthletes.rowHeights = new int[] { 0, 0 };
						gbl_panelAthletes.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
						gbl_panelAthletes.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
						panelAthletes.setLayout(gbl_panelAthletes);
						
								scrollPane = new JScrollPane();
								GridBagConstraints gbc_scrollPane = new GridBagConstraints();
								gbc_scrollPane.fill = GridBagConstraints.BOTH;
								gbc_scrollPane.gridx = 0;
								gbc_scrollPane.gridy = 0;
								panelAthletes.add(scrollPane, gbc_scrollPane);
								
										tableOverview = new JTable();
										tableOverview.setModel(tableOverviewModel);
										scrollPane.setViewportView(tableOverview);
										
										panelAthleteControl = new JPanel();
										panelAthletesTable.add(panelAthleteControl, BorderLayout.SOUTH);
										panelAthleteControl.setLayout(new BorderLayout(0, 0));
										
										btnEvaluateAthlete = new JButton("Athlet bewerten");
										panelAthleteControl.add(btnEvaluateAthlete, BorderLayout.EAST);
	}

	private void initListeners() {
	}
	
	

}