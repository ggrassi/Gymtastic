package ch.hsr.gymtastic.server.presentation.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.domain.Mark;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.PdfRankingTableExporter;
import ch.hsr.gymtastic.server.application.PdfStartlistExporter;
import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.presentation.frames.ProgramClassComboBoxModel;
import ch.hsr.gymtastic.server.presentation.models.CompetitionComboBoxModel;

import com.itextpdf.text.DocumentException;

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
	private JButton btnPreview;
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
					if (rdbtnRankingList.isSelected()) {
						PdfRankingTableExporter pdfRankingTableExporter = new PdfRankingTableExporter(
								gymCupController.getGymCup(), chooser
										.getSelectedFile().getAbsolutePath());

						if (comboBoxRankingModel.getSelectedItem().toString()
								.equalsIgnoreCase("Alle")) {

							try {
								pdfRankingTableExporter
										.createTotalRankingList();
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (DocumentException e1) {
								e1.printStackTrace();
							}
						} else {
							try {
								pdfRankingTableExporter
										.createProgramClassRankingList(comboBoxRankingModel
												.getSelectedItem().toString());
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (DocumentException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						PdfStartlistExporter pdfStartlistExporter = new PdfStartlistExporter(
								gymCupController.getGymCup(), chooser
										.getSelectedFile().getAbsolutePath());
						if (comboBoxStartlistModel.getSelectedItem().toString()
								.equalsIgnoreCase("Alle")) {

							try {
								pdfStartlistExporter.createTotalStartlist();
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (DocumentException e1) {
								e1.printStackTrace();
							}
						} else {
							try {
								pdfStartlistExporter
										.createCompetitionStartlist(comboBoxStartlistModel
												.getSelectedItem().toString());
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (DocumentException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});

		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		panelControl.setBorder(new TitledBorder(null, "Document Verwaltung",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelControl = new GridBagConstraints();
		gbc_panelControl.insets = new Insets(0, 0, 5, 0);
		gbc_panelControl.fill = GridBagConstraints.BOTH;
		gbc_panelControl.gridx = 2;
		gbc_panelControl.gridy = 0;
		panel.add(panelControl, gbc_panelControl);
		GridBagLayout gbl_panelControl = new GridBagLayout();
		gbl_panelControl.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelControl.rowHeights = new int[] { 0, 0 };
		gbl_panelControl.columnWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelControl.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelControl.setLayout(gbl_panelControl);

		btnPreview = new JButton("Vorschau");

		GridBagConstraints gbc_btnPreview = new GridBagConstraints();
		gbc_btnPreview.insets = new Insets(0, 0, 0, 5);
		gbc_btnPreview.gridx = 0;
		gbc_btnPreview.gridy = 0;
		panelControl.add(btnPreview, gbc_btnPreview);

		btnSave = new JButton("Speichern");

		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		panelControl.add(btnSave, gbc_btnSave);
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

	/*
	 * TODO: DELETE!
	 */

	/**
	 * Creates the gym cup.
	 * 
	 * @return the gym cup
	 */
	@SuppressWarnings("unused")
	private GymCup createGymCup() {
		GymCup gymCup = new GymCup("Faessi Cup", "Schellenberg");
		Competition competition1 = new Competition("Wettkampf P1",
				new GregorianCalendar(), "13:20", "19:32", "P1");
		Competition competition2 = new Competition("Wettkampf P2",
				new GregorianCalendar(), "13:20", "19:32", "P2");
		Competition competition3 = new Competition("Wettkampf P3",
				new GregorianCalendar(), "13:20", "19:32", "P3");
		Competition competition4 = new Competition("Wettkampf P4",
				new GregorianCalendar(), "13:20", "19:32", "P4");
		Competition competition5 = new Competition("Wettkampf P5",
				new GregorianCalendar(), "13:20", "19:32", "P5");
		Competition competition6 = new Competition("Wettkampf P6",
				new GregorianCalendar(), "13:20", "19:32", "P6");
		Squad squad1 = new Squad(1);
		Squad squad2 = new Squad(2);
		Squad squad3 = new Squad(3);
		Squad squad4 = new Squad(4);
		Squad squad5 = new Squad(5);
		Squad squad6 = new Squad(6);
		Squad squad7 = new Squad(7);
		Squad squad8 = new Squad(8);
		Squad squad9 = new Squad(9);
		Squad squad10 = new Squad(10);
		Squad squad11 = new Squad(11);
		Squad squad12 = new Squad(12);
		Athlete athlete1 = new Athlete("Mathias", "Fasser", "Gutacker 13");
		Athlete athlete2 = new Athlete("Maroc", "Pfiffner", "Gutacker 13");
		Athlete athlete3 = new Athlete("Jules", "Weder", "Gutacker 13");
		Athlete athlete4 = new Athlete("Giuliano", "Grassi", "Gutacker 13");
		Mark mark1 = new Mark(4.0, 5.0, 3.0, 4.0, 1.0, 2.0);
		Mark mark2 = new Mark(10.0, 5.0, 3.0, 4.0, 1.0, 2.0);
		Mark mark3 = new Mark(12.0, 5.0, 3.0, 4.0, 1.0, 2.0);
		Mark mark4 = new Mark(15.0, 5.0, 3.0, 4.0, 1.0, 2.0);
		Map<DeviceType, Mark> marks = new TreeMap<DeviceType, Mark>();
		marks.put(DeviceType.FLOOR_EXCERCISE, mark1);
		marks.put(DeviceType.HIGH_BAR, mark1);
		marks.put(DeviceType.PARALLEL_BARS, mark2);
		marks.put(DeviceType.POMMEL_HORSE, mark2);
		marks.put(DeviceType.STILL_RINGS, mark3);
		marks.put(DeviceType.VAULT, mark4);

		Map<DeviceType, Mark> marks2 = new TreeMap<DeviceType, Mark>();
		marks2.put(DeviceType.FLOOR_EXCERCISE, mark4);
		marks2.put(DeviceType.HIGH_BAR, mark4);
		marks2.put(DeviceType.PARALLEL_BARS, mark4);
		marks2.put(DeviceType.POMMEL_HORSE, mark4);
		marks2.put(DeviceType.STILL_RINGS, mark4);
		marks2.put(DeviceType.VAULT, mark4);

		athlete1.setMarks(marks);
		athlete2.setMarks(marks2);
		athlete3.setMarks(marks);
		athlete4.setMarks(marks);

		squad1.addAthlet(athlete1);
		squad1.addAthlet(athlete2);
		squad1.addAthlet(athlete3);
		squad1.addAthlet(athlete4);
		squad2.addAthlet(athlete1);
		squad2.addAthlet(athlete2);
		squad2.addAthlet(athlete3);
		squad2.addAthlet(athlete4);
		squad3.addAthlet(athlete1);
		squad3.addAthlet(athlete2);
		squad3.addAthlet(athlete3);
		squad3.addAthlet(athlete4);
		squad4.addAthlet(athlete1);
		squad4.addAthlet(athlete2);
		squad4.addAthlet(athlete3);
		squad4.addAthlet(athlete4);
		squad5.addAthlet(athlete1);
		squad5.addAthlet(athlete2);
		squad5.addAthlet(athlete3);
		squad5.addAthlet(athlete4);
		squad6.addAthlet(athlete1);
		squad6.addAthlet(athlete2);
		squad6.addAthlet(athlete3);
		squad6.addAthlet(athlete4);
		squad7.addAthlet(athlete1);
		squad7.addAthlet(athlete2);
		squad7.addAthlet(athlete3);
		squad7.addAthlet(athlete4);
		squad8.addAthlet(athlete1);
		squad8.addAthlet(athlete2);
		squad8.addAthlet(athlete3);
		squad8.addAthlet(athlete4);
		squad9.addAthlet(athlete1);
		squad9.addAthlet(athlete2);
		squad9.addAthlet(athlete3);
		squad9.addAthlet(athlete4);
		squad10.addAthlet(athlete1);
		squad10.addAthlet(athlete2);
		squad10.addAthlet(athlete3);
		squad10.addAthlet(athlete4);
		squad11.addAthlet(athlete1);
		squad11.addAthlet(athlete2);
		squad11.addAthlet(athlete3);
		squad11.addAthlet(athlete4);
		squad12.addAthlet(athlete1);
		squad12.addAthlet(athlete2);
		squad12.addAthlet(athlete3);
		squad12.addAthlet(athlete4);

		competition1.addSquad(squad1);
		competition1.addSquad(squad2);
		competition2.addSquad(squad3);
		competition2.addSquad(squad4);
		competition3.addSquad(squad5);
		competition3.addSquad(squad6);
		competition4.addSquad(squad7);
		competition4.addSquad(squad8);
		competition5.addSquad(squad9);
		competition5.addSquad(squad10);
		competition6.addSquad(squad11);
		competition6.addSquad(squad12);
		gymCup.addCompetition(competition1);
		gymCup.addCompetition(competition2);
		gymCup.addCompetition(competition3);
		gymCup.addCompetition(competition4);
		gymCup.addCompetition(competition5);
		gymCup.addCompetition(competition6);
		return gymCup;

	}

}