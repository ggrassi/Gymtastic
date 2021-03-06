package ch.hsr.gymtastic.server.presentation.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.controller.cupmanagement.GymCupController;
import ch.hsr.gymtastic.server.application.controller.persistence.DBController;
import ch.hsr.gymtastic.server.presentation.SearchTextField;
import ch.hsr.gymtastic.server.presentation.frames.AthleteDetailFrame;
import ch.hsr.gymtastic.server.presentation.models.AthleteDataTableModel;

/**
 * The Class AthletePanel contains all Athletes of a Cup
 */
public class AthletePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private JTextField txtFieldSearchAthlete;
	private JTable tableAthletes;
	private JPanel panelAthletesSearch;
	private JPanel panelAthletes;
	private JPanel panelAthletesBorder;
	private JLabel lblSquadsAmount;
	private JLabel lblSquads;
	private JLabel lblAthletes;
	private JPanel panelStatisticsBorder;
	private JPanel panelStatistics;
	private JLabel lblAthletesAmount;
	private JButton btnShowAthlete;
	private JButton btnAddAthlete;
	private JButton btnRemoveAthlete;
	private JScrollPane scrollPaneAthletes;
	private GymCupController gymCupController;
	private AthleteDataTableModel athleteDataTableModel;
	private TableRowSorter<AthleteDataTableModel> tableSorter;

	/**
	 * Instantiates a new athlete panel.
	 * 
	 * @param gymCupController
	 *            the gym cup controller
	 */
	public AthletePanel(GymCupController gymCupController) {
		this.gymCupController = gymCupController;
		this.gymCupController.getGymCup().addObserver(this);
		initGUI();
		initListeners();
		updateStatistics();
	}

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		btnShowAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableAthletes.getSelectedRow();
				row = tableAthletes.convertRowIndexToModel(row);
				AthleteDetailFrame.open(athleteDataTableModel.getAthlete(row),
						gymCupController);
			}
		});

		btnAddAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AthleteDetailFrame.open(null, gymCupController);
			}
		});

		btnRemoveAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableAthletes.getSelectedRow();
				row = tableAthletes.convertRowIndexToModel(row);
				Athlete removableAthlete = athleteDataTableModel
						.getAthlete(row);
				gymCupController.getGymCup().removeAthleteFromSquad(
						removableAthlete);
				Squad tmpSquad = gymCupController.getGymCup().getSquad(
						removableAthlete.getSquadId());
				DBController.removeAthleteFromSquad(removableAthlete, tmpSquad);
			}
		});

		txtFieldSearchAthlete.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						searchAthletes();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						searchAthletes();
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						searchAthletes();
					}
				});

		tableAthletes.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						if (tableAthletes.getSelectedRowCount() > 0) {
							updateButtonsShowRemove(true);
						} else {
							updateButtonsShowRemove(false);

						}
					}
				});
	}

	/**
	 * Inits the contents of the GUI
	 */
	private void initGUI() {
		athleteDataTableModel = new AthleteDataTableModel(gymCupController);
		tableSorter = new TableRowSorter<AthleteDataTableModel>(
				athleteDataTableModel);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		panelStatisticsBorder = new JPanel();
		panelStatisticsBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Statistik",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelStatisticsBorder = new GridBagConstraints();
		gbc_panelStatisticsBorder.fill = GridBagConstraints.BOTH;
		gbc_panelStatisticsBorder.insets = new Insets(0, 0, 5, 0);
		gbc_panelStatisticsBorder.gridx = 0;
		gbc_panelStatisticsBorder.gridy = 0;
		add(panelStatisticsBorder, gbc_panelStatisticsBorder);
		GridBagLayout gbl_panelStatisticsBorder = new GridBagLayout();
		gbl_panelStatisticsBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelStatisticsBorder.rowHeights = new int[] { 0, 0 };
		gbl_panelStatisticsBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelStatisticsBorder.rowWeights = new double[] { 0.0,
				Double.MIN_VALUE };
		panelStatisticsBorder.setLayout(gbl_panelStatisticsBorder);

		panelStatistics = new JPanel();
		GridBagConstraints gbc_panelStatistics = new GridBagConstraints();
		gbc_panelStatistics.fill = GridBagConstraints.BOTH;
		gbc_panelStatistics.gridx = 0;
		gbc_panelStatistics.gridy = 0;
		panelStatisticsBorder.add(panelStatistics, gbc_panelStatistics);
		GridBagLayout gbl_panelStatistics = new GridBagLayout();
		gbl_panelStatistics.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelStatistics.rowHeights = new int[] { 0, 0 };
		gbl_panelStatistics.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_panelStatistics.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelStatistics.setLayout(gbl_panelStatistics);

		lblAthletes = new JLabel("Athleten: ");
		GridBagConstraints gbc_lblAthletes = new GridBagConstraints();
		gbc_lblAthletes.insets = new Insets(0, 0, 0, 5);
		gbc_lblAthletes.gridx = 0;
		gbc_lblAthletes.gridy = 0;
		panelStatistics.add(lblAthletes, gbc_lblAthletes);

		lblAthletesAmount = new JLabel("0");
		GridBagConstraints gbc_lblAthletesAmount = new GridBagConstraints();
		gbc_lblAthletesAmount.insets = new Insets(0, 0, 0, 5);
		gbc_lblAthletesAmount.gridx = 1;
		gbc_lblAthletesAmount.gridy = 0;
		panelStatistics.add(lblAthletesAmount, gbc_lblAthletesAmount);

		lblSquads = new JLabel("Riegen: ");
		GridBagConstraints gbc_lblSquads = new GridBagConstraints();
		gbc_lblSquads.insets = new Insets(0, 0, 0, 5);
		gbc_lblSquads.gridx = 3;
		gbc_lblSquads.gridy = 0;
		panelStatistics.add(lblSquads, gbc_lblSquads);

		lblSquadsAmount = new JLabel("0");
		GridBagConstraints gbc_lblSquadsAmount = new GridBagConstraints();
		gbc_lblSquadsAmount.gridx = 4;
		gbc_lblSquadsAmount.gridy = 0;
		panelStatistics.add(lblSquadsAmount, gbc_lblSquadsAmount);

		panelAthletesBorder = new JPanel();
		panelAthletesBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Athleten",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelAthletesBorder = new GridBagConstraints();
		gbc_panelAthletesBorder.fill = GridBagConstraints.BOTH;
		gbc_panelAthletesBorder.gridx = 0;
		gbc_panelAthletesBorder.gridy = 1;
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
		gbl_panelAthletes.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelAthletes.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelAthletes.columnWeights = new double[] { 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelAthletes.rowWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		panelAthletes.setLayout(gbl_panelAthletes);

		panelAthletesSearch = new JPanel();
		GridBagConstraints gbc_panelAthletesSearch = new GridBagConstraints();
		gbc_panelAthletesSearch.gridwidth = 2;
		gbc_panelAthletesSearch.insets = new Insets(0, 0, 5, 0);
		gbc_panelAthletesSearch.fill = GridBagConstraints.BOTH;
		gbc_panelAthletesSearch.gridx = 0;
		gbc_panelAthletesSearch.gridy = 0;
		panelAthletes.add(panelAthletesSearch, gbc_panelAthletesSearch);
		GridBagLayout gbl_panelAthletesSearch = new GridBagLayout();
		gbl_panelAthletesSearch.columnWidths = new int[] { 179, 0, 0, 0, 0, 0 };
		gbl_panelAthletesSearch.rowHeights = new int[] { 0, 0 };
		gbl_panelAthletesSearch.columnWeights = new double[] { 1.0, 0.0, 1.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_panelAthletesSearch.rowWeights = new double[] { 0.0,
				Double.MIN_VALUE };
		panelAthletesSearch.setLayout(gbl_panelAthletesSearch);

		txtFieldSearchAthlete = new SearchTextField();
		GridBagConstraints gbc_txtFieldSearchAthlete = new GridBagConstraints();
		gbc_txtFieldSearchAthlete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldSearchAthlete.insets = new Insets(0, 0, 0, 5);
		gbc_txtFieldSearchAthlete.gridx = 0;
		gbc_txtFieldSearchAthlete.gridy = 0;
		panelAthletesSearch.add(txtFieldSearchAthlete,
				gbc_txtFieldSearchAthlete);
		txtFieldSearchAthlete.setColumns(10);

		btnShowAthlete = new JButton("Anzeigen...");
		GridBagConstraints gbc_btnShowAthlete = new GridBagConstraints();
		gbc_btnShowAthlete.anchor = GridBagConstraints.EAST;
		gbc_btnShowAthlete.insets = new Insets(0, 0, 0, 5);
		gbc_btnShowAthlete.gridx = 2;
		gbc_btnShowAthlete.gridy = 0;
		panelAthletesSearch.add(btnShowAthlete, gbc_btnShowAthlete);
		btnShowAthlete.setEnabled(false);

		btnAddAthlete = new JButton("Hinzuf\u00fcgen...");
		GridBagConstraints gbc_btnAddAthlete = new GridBagConstraints();
		gbc_btnAddAthlete.anchor = GridBagConstraints.EAST;
		gbc_btnAddAthlete.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddAthlete.gridx = 3;
		gbc_btnAddAthlete.gridy = 0;
		panelAthletesSearch.add(btnAddAthlete, gbc_btnAddAthlete);

		btnRemoveAthlete = new JButton("Entfernen");
		GridBagConstraints gbc_btnRemoveAthlete = new GridBagConstraints();
		gbc_btnRemoveAthlete.anchor = GridBagConstraints.EAST;
		gbc_btnRemoveAthlete.gridx = 4;
		gbc_btnRemoveAthlete.gridy = 0;
		panelAthletesSearch.add(btnRemoveAthlete, gbc_btnRemoveAthlete);
		btnRemoveAthlete.setEnabled(false);

		scrollPaneAthletes = new JScrollPane();
		GridBagConstraints gbc_scrollPaneAthletes = new GridBagConstraints();
		gbc_scrollPaneAthletes.gridwidth = 2;
		gbc_scrollPaneAthletes.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneAthletes.gridx = 0;
		gbc_scrollPaneAthletes.gridy = 1;
		panelAthletes.add(scrollPaneAthletes, gbc_scrollPaneAthletes);

		tableAthletes = new JTable();
		tableAthletes.setModel(athleteDataTableModel);
		tableAthletes.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPaneAthletes.setViewportView(tableAthletes);

	}

	/**
	 * Search through the list of Athletes and the results in the JTable
	 */
	private void searchAthletes() {
		RowFilter<AthleteDataTableModel, Object> rowFilter = null;
		if (!txtFieldSearchAthlete.getText().equals("Suchen...")) {
			rowFilter = RowFilter.regexFilter("(?i)"
					+ Pattern.quote(txtFieldSearchAthlete.getText()));
		}
		tableSorter.setRowFilter(rowFilter);
		tableAthletes.setRowSorter(tableSorter);
	}

	/**
	 * Updates the statistic fields in the panel.
	 */
	private void updateStatistics() {
		lblSquadsAmount.setText(""
				+ gymCupController.getGymCup().getSquads().size());
		lblAthletesAmount.setText(""
				+ gymCupController.getGymCup().getAllAthletes().size());
	}
	/**
	 * Enable the Buttons removeAthlete and showAthlete
	 */
	private void updateButtonsShowRemove(Boolean enable) {
		btnShowAthlete.setEnabled(enable);
		btnRemoveAthlete.setEnabled(enable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */

	@Override
	public void update(Observable arg0, Object arg1) {
		updateStatistics();
	}

}
