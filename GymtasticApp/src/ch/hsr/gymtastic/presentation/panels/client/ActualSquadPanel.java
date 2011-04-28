package ch.hsr.gymtastic.presentation.panels.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ch.hsr.gymtastic.application.controller.client.SquadController;
import ch.hsr.gymtastic.application.models.AthleteOverviewTableModel;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Squad;

public class ActualSquadPanel extends JPanel {
	/**
     * 
     */
	private static final long serialVersionUID = 9138092871453323277L;

	private JTable tableOverview;
	private JPanel panelAthletesBorder;
	private JPanel panelAthletes;
	private JScrollPane scrollPane;
	private JPanel panelAthleteInfoBorder;
	private JPanel panelAthleteInfo;
	private SquadController squadController;

	private AthleteOverviewTableModel tableOverviewModel;

	public ActualSquadPanel(SquadController squadController) {
		this.squadController = squadController;
		initGUI();
		initListeners();
	}

	private void initGUI() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		panelAthletesBorder = new JPanel();
		panelAthletesBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Athleten",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelAthletesBorder = new GridBagConstraints();
		gbc_panelAthletesBorder.fill = GridBagConstraints.BOTH;
		gbc_panelAthletesBorder.insets = new Insets(0, 0, 5, 0);
		gbc_panelAthletesBorder.gridx = 0;
		gbc_panelAthletesBorder.gridy = 0;
		add(panelAthletesBorder, gbc_panelAthletesBorder);
		GridBagLayout gbl_panelAthletesBorder = new GridBagLayout();
		gbl_panelAthletesBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelAthletesBorder.rowHeights = new int[] { 0, 0 };
		gbl_panelAthletesBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelAthletesBorder.rowWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		panelAthletesBorder.setLayout(gbl_panelAthletesBorder);

		panelAthletes = new JPanel();
		GridBagConstraints gbc_panelAthletes = new GridBagConstraints();
		gbc_panelAthletes.fill = GridBagConstraints.BOTH;
		gbc_panelAthletes.gridx = 0;
		gbc_panelAthletes.gridy = 0;
		panelAthletesBorder.add(panelAthletes, gbc_panelAthletes);
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
		tableOverviewModel = new AthleteOverviewTableModel(squadController);
		tableOverview.setModel(tableOverviewModel);
		scrollPane.setViewportView(tableOverview);

		panelAthleteInfoBorder = new JPanel();
		panelAthleteInfoBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Information",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelAthleteInfoBorder = new GridBagConstraints();
		gbc_panelAthleteInfoBorder.fill = GridBagConstraints.BOTH;
		gbc_panelAthleteInfoBorder.gridx = 0;
		gbc_panelAthleteInfoBorder.gridy = 1;
		add(panelAthleteInfoBorder, gbc_panelAthleteInfoBorder);
		GridBagLayout gbl_panelAthleteInfoBorder = new GridBagLayout();
		gbl_panelAthleteInfoBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelAthleteInfoBorder.rowHeights = new int[] { 0, 0 };
		gbl_panelAthleteInfoBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelAthleteInfoBorder.rowWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		panelAthleteInfoBorder.setLayout(gbl_panelAthleteInfoBorder);

		panelAthleteInfo = new JPanel();
		GridBagConstraints gbc_panelAthleteInfo = new GridBagConstraints();
		gbc_panelAthleteInfo.fill = GridBagConstraints.BOTH;
		gbc_panelAthleteInfo.gridx = 0;
		gbc_panelAthleteInfo.gridy = 0;
		panelAthleteInfoBorder.add(panelAthleteInfo, gbc_panelAthleteInfo);
	}

	private void initListeners() {
	}
	
	

}
