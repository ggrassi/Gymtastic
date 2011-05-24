package ch.hsr.gymtastic.server.presentation.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import ch.hsr.gymtastic.server.application.PdfRankingTableExporter;
import ch.hsr.gymtastic.server.application.PdfStartlistExporter;
import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.presentation.frames.ProgramClassComboBoxModel;
import ch.hsr.gymtastic.server.presentation.models.CompetitionComboBoxModel;

import com.itextpdf.text.DocumentException;
import java.awt.BorderLayout;

/**
 * At the Class RankingPanel we can create a PDF wich shows the actuall Ranking
 * of the Athletes.
 */
public class RankingPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panelStartlist;
	private JRadioButton rdbtnStartlist;
	private JComboBox comboBoxStartlist;
	private JPanel panelRankingTable;
	private JRadioButton rdbtnRankingList;
	private JComboBox comboBoxRankingTable;
	private JPanel panelControl;
	private JButton btnSave;
	private JFileChooser chooser;
	private ButtonGroup buttonGroupParticipation;
	private CompetitionComboBoxModel comboBoxStartlistModel;
	private ProgramClassComboBoxModel comboBoxRankingModel;
	private GymCupController gymCupController;

	/**
	 * Instantiates a new ranking panel.
	 * 
	 * @param gymCupController
	 *            the gym cup controller
	 */
	public RankingPanel(GymCupController gymCupController) {
		this.gymCupController = gymCupController;
		this.gymCupController.getGymCup().addObserver(this);
		initGUI();
		initListeners();
	}

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(false);
				int result = chooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {

					try {
						if (rdbtnRankingList.isSelected()) {
							PdfRankingTableExporter pdfRankingTableExporter = new PdfRankingTableExporter(
									gymCupController.getGymCup(), chooser
											.getSelectedFile()
											.getAbsolutePath());

							if (comboBoxRankingModel.getSelectedItem()
									.toString().equalsIgnoreCase("Alle")) {

								pdfRankingTableExporter
										.createTotalRankingList();
							} else {
								pdfRankingTableExporter
										.createProgramClassRankingList(comboBoxRankingModel
												.getSelectedItem().toString());
							}
						} else {
							PdfStartlistExporter pdfStartlistExporter = new PdfStartlistExporter(
									gymCupController.getGymCup(), chooser
											.getSelectedFile()
											.getAbsolutePath());
							if (comboBoxStartlistModel.getSelectedItem()
									.toString().equalsIgnoreCase("Alle")) {

								pdfStartlistExporter.createTotalStartlist();
							} else {
								pdfStartlistExporter
										.createCompetitionStartlist(comboBoxStartlistModel
												.getSelectedItem().toString());

							}
						}

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (DocumentException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		rdbtnStartlist.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				comboBoxStartlist.setEnabled(true);
				comboBoxRankingTable.setEnabled(false);
			}
		});

		rdbtnRankingList.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				comboBoxStartlist.setEnabled(false);
				comboBoxRankingTable.setEnabled(true);
			}

		});

	}

	/**
	 * Inits the contents of the GUI.
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		panel = new JPanel();

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		panelStartlist = new JPanel();
		panelStartlist.setBorder(new TitledBorder(null, "Startliste",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelStartlist = new GridBagConstraints();
		gbc_panelStartlist.insets = new Insets(0, 0, 5, 5);
		gbc_panelStartlist.fill = GridBagConstraints.BOTH;
		gbc_panelStartlist.gridx = 0;
		gbc_panelStartlist.gridy = 0;
		panel.add(panelStartlist, gbc_panelStartlist);
		GridBagLayout gbl_panelStartlist = new GridBagLayout();
		gbl_panelStartlist.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelStartlist.rowHeights = new int[] { 0, 0 };
		gbl_panelStartlist.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelStartlist.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelStartlist.setLayout(gbl_panelStartlist);

		rdbtnStartlist = new JRadioButton("Startliste");

		GridBagConstraints gbc_rdbtnStartlist = new GridBagConstraints();
		gbc_rdbtnStartlist.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnStartlist.gridx = 0;
		gbc_rdbtnStartlist.gridy = 0;
		panelStartlist.add(rdbtnStartlist, gbc_rdbtnStartlist);

		comboBoxStartlist = new JComboBox();
		comboBoxStartlistModel = new CompetitionComboBoxModel();
		comboBoxStartlist.setModel(comboBoxStartlistModel);
		GridBagConstraints gbc_comboBoxStartlist = new GridBagConstraints();
		gbc_comboBoxStartlist.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStartlist.gridx = 1;
		gbc_comboBoxStartlist.gridy = 0;
		panelStartlist.add(comboBoxStartlist, gbc_comboBoxStartlist);

		panelRankingTable = new JPanel();
		panelRankingTable.setBorder(new TitledBorder(null, "Rangliste",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelRankingTable = new GridBagConstraints();
		gbc_panelRankingTable.insets = new Insets(0, 0, 5, 5);
		gbc_panelRankingTable.fill = GridBagConstraints.BOTH;
		gbc_panelRankingTable.gridx = 1;
		gbc_panelRankingTable.gridy = 0;
		panel.add(panelRankingTable, gbc_panelRankingTable);
		GridBagLayout gbl_panelRankingTable = new GridBagLayout();
		gbl_panelRankingTable.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelRankingTable.rowHeights = new int[] { 0, 0 };
		gbl_panelRankingTable.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelRankingTable.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelRankingTable.setLayout(gbl_panelRankingTable);

		rdbtnRankingList = new JRadioButton("Rangliste");
		GridBagConstraints gbc_rdbtnRankingList = new GridBagConstraints();
		gbc_rdbtnRankingList.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnRankingList.gridx = 0;
		gbc_rdbtnRankingList.gridy = 0;
		panelRankingTable.add(rdbtnRankingList, gbc_rdbtnRankingList);

		comboBoxRankingTable = new JComboBox();
		comboBoxRankingModel = new ProgramClassComboBoxModel();
		comboBoxRankingTable.setModel(comboBoxRankingModel);
		GridBagConstraints gbc_comboBoxRankingTable = new GridBagConstraints();
		gbc_comboBoxRankingTable.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRankingTable.gridx = 1;
		gbc_comboBoxRankingTable.gridy = 0;
		panelRankingTable.add(comboBoxRankingTable, gbc_comboBoxRankingTable);

		buttonGroupParticipation = new ButtonGroup();
		buttonGroupParticipation.add(rdbtnStartlist);
		buttonGroupParticipation.add(rdbtnRankingList);
		rdbtnStartlist.setSelected(true);

		comboBoxStartlist.setEnabled(true);
		comboBoxRankingTable.setEnabled(false);

		panelControl = new JPanel();
		GridBagConstraints gbc_panelControl = new GridBagConstraints();
		gbc_panelControl.insets = new Insets(0, 0, 5, 0);
		gbc_panelControl.fill = GridBagConstraints.BOTH;
		gbc_panelControl.gridx = 2;
		gbc_panelControl.gridy = 0;
		panel.add(panelControl, gbc_panelControl);
		panelControl.setLayout(new BorderLayout(0, 0));

		btnSave = new JButton("Speichern");
		panelControl.add(btnSave, BorderLayout.EAST);
		updateComboBox();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		updateComboBox();
	}

	/**
	 * Updates the ComboBoxes
	 */
	private void updateComboBox() {
		comboBoxStartlistModel = new CompetitionComboBoxModel(gymCupController
				.getGymCup().getCompetitions());
		comboBoxStartlist.setModel(comboBoxStartlistModel);
		comboBoxRankingModel = new ProgramClassComboBoxModel(gymCupController
				.getGymCup().getProgramClasses());
		comboBoxRankingTable.setModel(comboBoxRankingModel);
	}
}