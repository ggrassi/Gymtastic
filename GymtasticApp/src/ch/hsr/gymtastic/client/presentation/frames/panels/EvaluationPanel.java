package ch.hsr.gymtastic.client.presentation.frames.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ch.hsr.gymtastic.client.application.controller.SquadController;
import ch.hsr.gymtastic.client.presentation.frames.ClientFrame;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Mark;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EvaluationPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private JTextField txtFieldFinalMark;
	private JTextField txtFieldBonus;
	private JTextField txtFieldPenalty;
	private JTextField txtFieldEMark3;
	private JTextField txtFieldEMark2;
	private JTextField txtFieldEMark1;
	private JTextField txtFieldDMark;
	private JButton btnNext;
	private JPanel panelSouth;
	private JButton btnPrevious;
	private JPanel panelSouthCenter;
	private JPanel panelCenter;
	private JPanel panelAthleteInfoBorder;
	private JPanel panelAthleteInfo;
	private JLabel lblFirstName;
	private JLabel lblFirstNameField;
	private JLabel lblLastName;
	private JLabel lblLastNameField;
	private JLabel lblSquad;
	private JLabel lblSquadField;
	private JLabel lblPrgClass;
	private JLabel lblPrgClassField;
	private JLabel lblStartNr;
	private JLabel lblStartNrField;
	private JPanel panelMarksBorder;
	private JPanel panelMarks;
	private JLabel lblDMark;
	private JLabel lblEMark1;
	private JLabel lblEMark2;
	private JLabel lblEMark3;
	private JLabel lblPenalty;
	private JLabel lblBonus;
	private JLabel lblFinalMark;
	private SquadController squadController;
	private Athlete athlete;
	private DeviceType deviceType;
	private JButton btnOverview;
	private ClientFrame frameClient;
	private JButton btnEndEvaluation;
	private JPanel panelRightBtn;
	private JPanel panelLeftBtn;
	private double finalMark = 0.0;

	public EvaluationPanel(final SquadController squadController,
			DeviceType deviceType, ClientFrame frameClient) {
		this.squadController = squadController;
		this.deviceType = deviceType;
		this.frameClient = frameClient;
		initGUI();
		initListeners();

	}
	
	private void initListeners() {
		btnNext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setMark();
				getNextAthlete();
				loadAthleteFields();
				checkButtons();
				calculateFinalMarkField();
				setFocusOnFirstMark();

			}

		});
		btnPrevious.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setMark();
				getPreviousAthlete();
				loadAthleteFields();
				checkButtons();
				calculateFinalMarkField();
				setFocusOnFirstMark();

			}

		});
		btnEndEvaluation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setMark();
				showOverviewPanel();
			}
		});

		btnOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMark();
				showOverviewPanel();
			}
		});

	}

	private void initGUI() {
		setLayout(new BorderLayout(0, 0));

		panelSouth = new JPanel();
		add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BorderLayout(0, 0));

		panelSouthCenter = new JPanel();
		panelSouth.add(panelSouthCenter, BorderLayout.CENTER);
		panelSouthCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnOverview = new JButton("Zur aktuellen Riege");
		panelSouthCenter.add(btnOverview);

		panelRightBtn = new JPanel();
		panelSouth.add(panelRightBtn, BorderLayout.EAST);

		btnEndEvaluation = new JButton("Bewertung abschliessen");
		panelRightBtn.add(btnEndEvaluation);

		btnNext = new JButton("N\u00e4chster Athlet");

		panelRightBtn.add(btnNext);

		panelLeftBtn = new JPanel();
		panelSouth.add(panelLeftBtn, BorderLayout.WEST);

		btnPrevious = new JButton("Vorheriger Athlet");
		panelLeftBtn.add(btnPrevious);
		btnPrevious.setEnabled(false);
		btnEndEvaluation.setVisible(false);

		panelCenter = new JPanel();
		add(panelCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelCenter = new GridBagLayout();
		gbl_panelCenter.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelCenter.rowHeights = new int[] { 0, 0 };
		gbl_panelCenter.columnWeights = new double[] { 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelCenter.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelCenter.setLayout(gbl_panelCenter);

		panelAthleteInfoBorder = new JPanel();
		panelAthleteInfoBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Athlet",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelAthleteInfoBorder = new GridBagConstraints();
		gbc_panelAthleteInfoBorder.fill = GridBagConstraints.BOTH;
		gbc_panelAthleteInfoBorder.insets = new Insets(0, 0, 0, 5);
		gbc_panelAthleteInfoBorder.gridx = 0;
		gbc_panelAthleteInfoBorder.gridy = 0;
		panelCenter.add(panelAthleteInfoBorder, gbc_panelAthleteInfoBorder);
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
		GridBagLayout gbl_panelAthleteInfo = new GridBagLayout();
		gbl_panelAthleteInfo.columnWidths = new int[] { 121, 0, 0 };
		gbl_panelAthleteInfo.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gbl_panelAthleteInfo.columnWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelAthleteInfo.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelAthleteInfo.setLayout(gbl_panelAthleteInfo);

		lblFirstName = new JLabel("Vorname:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 0;
		panelAthleteInfo.add(lblFirstName, gbc_lblFirstName);

		lblLastName = new JLabel("Nachname:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 1;
		panelAthleteInfo.add(lblLastName, gbc_lblLastName);

		lblSquad = new JLabel("Riege:");
		GridBagConstraints gbc_lblSquad = new GridBagConstraints();
		gbc_lblSquad.anchor = GridBagConstraints.WEST;
		gbc_lblSquad.insets = new Insets(0, 0, 5, 5);
		gbc_lblSquad.gridx = 0;
		gbc_lblSquad.gridy = 2;
		panelAthleteInfo.add(lblSquad, gbc_lblSquad);

		lblPrgClass = new JLabel("Programmklasse:");
		GridBagConstraints gbc_lblPrgClass = new GridBagConstraints();
		gbc_lblPrgClass.anchor = GridBagConstraints.WEST;
		gbc_lblPrgClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrgClass.gridx = 0;
		gbc_lblPrgClass.gridy = 3;
		panelAthleteInfo.add(lblPrgClass, gbc_lblPrgClass);

		lblStartNr = new JLabel("Startnummer:");
		GridBagConstraints gbc_lblStartNr = new GridBagConstraints();
		gbc_lblStartNr.anchor = GridBagConstraints.WEST;
		gbc_lblStartNr.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartNr.gridx = 0;
		gbc_lblStartNr.gridy = 4;
		panelAthleteInfo.add(lblStartNr, gbc_lblStartNr);

		lblFirstNameField = new JLabel("");
		GridBagConstraints gbc_lblFirstNameField = new GridBagConstraints();
		gbc_lblFirstNameField.anchor = GridBagConstraints.WEST;
		gbc_lblFirstNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirstNameField.gridx = 1;
		gbc_lblFirstNameField.gridy = 0;
		panelAthleteInfo.add(lblFirstNameField, gbc_lblFirstNameField);

		lblLastNameField = new JLabel("");
		GridBagConstraints gbc_lblLastNameField = new GridBagConstraints();
		gbc_lblLastNameField.anchor = GridBagConstraints.WEST;
		gbc_lblLastNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lblLastNameField.gridx = 1;
		gbc_lblLastNameField.gridy = 1;
		panelAthleteInfo.add(lblLastNameField, gbc_lblLastNameField);

		lblSquadField = new JLabel("");
		GridBagConstraints gbc_lblSquadField = new GridBagConstraints();
		gbc_lblSquadField.anchor = GridBagConstraints.WEST;
		gbc_lblSquadField.insets = new Insets(0, 0, 5, 0);
		gbc_lblSquadField.gridx = 1;
		gbc_lblSquadField.gridy = 2;
		panelAthleteInfo.add(lblSquadField, gbc_lblSquadField);

		lblPrgClassField = new JLabel("");
		GridBagConstraints gbc_lblPrgClassField = new GridBagConstraints();
		gbc_lblPrgClassField.anchor = GridBagConstraints.WEST;
		gbc_lblPrgClassField.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrgClassField.gridx = 1;
		gbc_lblPrgClassField.gridy = 3;
		panelAthleteInfo.add(lblPrgClassField, gbc_lblPrgClassField);

		lblStartNrField = new JLabel("");
		GridBagConstraints gbc_lblStartNrField = new GridBagConstraints();
		gbc_lblStartNrField.anchor = GridBagConstraints.WEST;
		gbc_lblStartNrField.gridx = 1;
		gbc_lblStartNrField.gridy = 4;
		panelAthleteInfo.add(lblStartNrField, gbc_lblStartNrField);

		panelMarksBorder = new JPanel();
		panelMarksBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Bewertung",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelMarksBorder = new GridBagConstraints();
		gbc_panelMarksBorder.fill = GridBagConstraints.BOTH;
		gbc_panelMarksBorder.gridx = 1;
		gbc_panelMarksBorder.gridy = 0;
		panelCenter.add(panelMarksBorder, gbc_panelMarksBorder);
		GridBagLayout gbl_panelMarksBorder = new GridBagLayout();
		gbl_panelMarksBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelMarksBorder.rowHeights = new int[] { 0, 0 };
		gbl_panelMarksBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelMarksBorder.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelMarksBorder.setLayout(gbl_panelMarksBorder);

		panelMarks = new JPanel();
		GridBagConstraints gbc_panelMarks = new GridBagConstraints();
		gbc_panelMarks.fill = GridBagConstraints.BOTH;
		gbc_panelMarks.gridx = 0;
		gbc_panelMarks.gridy = 0;
		panelMarksBorder.add(panelMarks, gbc_panelMarks);
		GridBagLayout gbl_panelMarks = new GridBagLayout();
		gbl_panelMarks.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelMarks.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelMarks.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelMarks.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		panelMarks.setLayout(gbl_panelMarks);

		lblDMark = new JLabel("D-Note:");
		GridBagConstraints gbc_lblDMark = new GridBagConstraints();
		gbc_lblDMark.insets = new Insets(0, 0, 5, 5);
		gbc_lblDMark.anchor = GridBagConstraints.WEST;
		gbc_lblDMark.gridx = 0;
		gbc_lblDMark.gridy = 0;
		panelMarks.add(lblDMark, gbc_lblDMark);

		txtFieldDMark = new JTextField();

		txtFieldDMark.addFocusListener(new FocusAdapterMark(txtFieldDMark));
		GridBagConstraints gbc_txtFieldDMark = new GridBagConstraints();
		gbc_txtFieldDMark.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldDMark.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldDMark.gridx = 1;
		gbc_txtFieldDMark.gridy = 0;
		panelMarks.add(txtFieldDMark, gbc_txtFieldDMark);
		txtFieldDMark.setColumns(10);

		lblEMark1 = new JLabel("E-Note 1:");
		GridBagConstraints gbc_lblEMark1 = new GridBagConstraints();
		gbc_lblEMark1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEMark1.anchor = GridBagConstraints.WEST;
		gbc_lblEMark1.gridx = 0;
		gbc_lblEMark1.gridy = 1;
		panelMarks.add(lblEMark1, gbc_lblEMark1);

		txtFieldEMark1 = new JTextField();

		txtFieldEMark1.addFocusListener(new FocusAdapterMark(txtFieldEMark1));
		GridBagConstraints gbc_txtFieldEMark1 = new GridBagConstraints();
		gbc_txtFieldEMark1.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldEMark1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldEMark1.gridx = 1;
		gbc_txtFieldEMark1.gridy = 1;
		panelMarks.add(txtFieldEMark1, gbc_txtFieldEMark1);
		txtFieldEMark1.setColumns(10);

		lblEMark2 = new JLabel("E-Note 2:");
		GridBagConstraints gbc_lblEMark2 = new GridBagConstraints();
		gbc_lblEMark2.insets = new Insets(0, 0, 5, 5);
		gbc_lblEMark2.anchor = GridBagConstraints.WEST;
		gbc_lblEMark2.gridx = 0;
		gbc_lblEMark2.gridy = 2;
		panelMarks.add(lblEMark2, gbc_lblEMark2);

		txtFieldEMark2 = new JTextField();
		txtFieldEMark2.addFocusListener(new FocusAdapterMark(txtFieldEMark2));
		GridBagConstraints gbc_txtFieldEMark2 = new GridBagConstraints();
		gbc_txtFieldEMark2.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldEMark2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldEMark2.gridx = 1;
		gbc_txtFieldEMark2.gridy = 2;
		panelMarks.add(txtFieldEMark2, gbc_txtFieldEMark2);
		txtFieldEMark2.setColumns(10);

		lblEMark3 = new JLabel("E-Note 3:");
		GridBagConstraints gbc_lblEMark3 = new GridBagConstraints();
		gbc_lblEMark3.insets = new Insets(0, 0, 5, 5);
		gbc_lblEMark3.anchor = GridBagConstraints.WEST;
		gbc_lblEMark3.gridx = 0;
		gbc_lblEMark3.gridy = 3;
		panelMarks.add(lblEMark3, gbc_lblEMark3);

		txtFieldEMark3 = new JTextField();
		txtFieldEMark3.addFocusListener(new FocusAdapterMark(txtFieldEMark3));
		GridBagConstraints gbc_txtFieldEMark3 = new GridBagConstraints();
		gbc_txtFieldEMark3.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldEMark3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldEMark3.gridx = 1;
		gbc_txtFieldEMark3.gridy = 3;
		panelMarks.add(txtFieldEMark3, gbc_txtFieldEMark3);
		txtFieldEMark3.setColumns(10);

		lblPenalty = new JLabel("Penalty:");
		GridBagConstraints gbc_lblPenalty = new GridBagConstraints();
		gbc_lblPenalty.insets = new Insets(0, 0, 5, 5);
		gbc_lblPenalty.anchor = GridBagConstraints.WEST;
		gbc_lblPenalty.gridx = 0;
		gbc_lblPenalty.gridy = 4;
		panelMarks.add(lblPenalty, gbc_lblPenalty);

		txtFieldPenalty = new JTextField();
		txtFieldPenalty.addFocusListener(new FocusAdapterMark(txtFieldPenalty));
		txtFieldPenalty.setBackground(Color.RED);
		txtFieldPenalty.setForeground(Color.WHITE);
		GridBagConstraints gbc_txtFieldPenalty = new GridBagConstraints();
		gbc_txtFieldPenalty.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldPenalty.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldPenalty.gridx = 1;
		gbc_txtFieldPenalty.gridy = 4;
		panelMarks.add(txtFieldPenalty, gbc_txtFieldPenalty);
		txtFieldPenalty.setColumns(10);

		lblBonus = new JLabel("Bonus:");
		GridBagConstraints gbc_lblBonus = new GridBagConstraints();
		gbc_lblBonus.insets = new Insets(0, 0, 5, 5);
		gbc_lblBonus.anchor = GridBagConstraints.WEST;
		gbc_lblBonus.gridx = 0;
		gbc_lblBonus.gridy = 5;
		panelMarks.add(lblBonus, gbc_lblBonus);

		txtFieldBonus = new JTextField();
		txtFieldBonus.addFocusListener(new FocusAdapterMark(txtFieldBonus));
		txtFieldBonus.setBackground(Color.YELLOW);
		GridBagConstraints gbc_txtFieldBonus = new GridBagConstraints();
		gbc_txtFieldBonus.anchor = GridBagConstraints.NORTH;
		gbc_txtFieldBonus.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldBonus.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldBonus.gridx = 1;
		gbc_txtFieldBonus.gridy = 5;
		panelMarks.add(txtFieldBonus, gbc_txtFieldBonus);
		txtFieldBonus.setColumns(10);

		lblFinalMark = new JLabel("Endnote:");
		GridBagConstraints gbc_lblFinalMark = new GridBagConstraints();
		gbc_lblFinalMark.insets = new Insets(0, 0, 0, 5);
		gbc_lblFinalMark.anchor = GridBagConstraints.WEST;
		gbc_lblFinalMark.gridx = 0;
		gbc_lblFinalMark.gridy = 6;
		panelMarks.add(lblFinalMark, gbc_lblFinalMark);

		txtFieldFinalMark = new JTextField();
		txtFieldFinalMark.setForeground(Color.WHITE);
		txtFieldFinalMark.setEditable(false);
		txtFieldFinalMark.setBackground(Color.BLUE);
		GridBagConstraints gbc_txtFieldFinalMark = new GridBagConstraints();
		gbc_txtFieldFinalMark.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldFinalMark.gridx = 1;
		gbc_txtFieldFinalMark.gridy = 6;
		panelMarks.add(txtFieldFinalMark, gbc_txtFieldFinalMark);
		txtFieldFinalMark.setColumns(10);
		
		getNextAthlete();
		loadAthleteFields();
		checkButtons();
		setFocusOnFirstMark();

		setKeyTypedListener();
		txtFieldFinalMark.setText("" + finalMark);
	}

	private void setMark() {

		athlete.addMark(deviceType, getMarkFromInput());

	}

	private Mark getMarkFromInput() {
		try {
			double dMark = Double.valueOf(txtFieldDMark.getText());
			double eMark1 = Double.valueOf(txtFieldEMark1.getText());
			double eMark2 = Double.valueOf(txtFieldEMark2.getText());
			double eMark3 = Double.valueOf(txtFieldEMark3.getText());
			double penalty = Double.valueOf(txtFieldPenalty.getText());
			double bonus = Double.valueOf(txtFieldBonus.getText());
			return new Mark(dMark, eMark1, eMark2, eMark3, penalty, bonus);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frameClient.getFrame(),
					"Es d\u00fcrfen keine Notenfelder leer gelassen werden.",
					"Eingabefehler", JOptionPane.ERROR_MESSAGE);
		}
		return null;

	}

	private void loadAthleteFields() {
		if (athlete != null) {
			lblFirstNameField.setText(athlete.getFirstName());
			lblLastNameField.setText(athlete.getLastName());
			lblPrgClassField.setText(athlete.getPrgClass());
			lblSquadField.setText("" + athlete.getSquadId());
			lblStartNrField.setText("" + athlete.getStartNr());
			loadAhtleteMarks();
		}
	}

	private void loadAhtleteMarks() {
		if (athlete.getMark(deviceType) != null) {
			txtFieldDMark.setText("" + athlete.getMark(deviceType).getdMark());
			txtFieldEMark1.setText(""
					+ athlete.getMark(deviceType).geteMarkOne());
			txtFieldEMark2.setText(""
					+ athlete.getMark(deviceType).getEmarkTwo());
			txtFieldEMark3.setText(""
					+ athlete.getMark(deviceType).geteMarkThree());
			txtFieldBonus.setText("" + athlete.getMark(deviceType).getBonus());
			txtFieldPenalty.setText(""
					+ athlete.getMark(deviceType).getPenalty());
		} else {
			initMarkFields();
		}
	}

	private void initMarkFields() {
		txtFieldDMark.setText("");
		txtFieldEMark1.setText("");
		txtFieldEMark2.setText("");
		txtFieldEMark3.setText("");
		txtFieldBonus.setText("");
		txtFieldPenalty.setText("");
	}

	private void checkButtons() {
		if (squadController.hasNextAthlete()) {
			btnNext.setEnabled(true);
			showNextButton();
		} else {
			btnNext.setEnabled(false);
			showEndButton();
		}
		if (squadController.hasPreviousAthlete()) {
			btnPrevious.setEnabled(true);
		} else {
			btnPrevious.setEnabled(false);
		}
	}

	private void showEndButton() {
		btnNext.setVisible(false);
		btnEndEvaluation.setVisible(true);
	}

	private void showNextButton() {
		btnNext.setVisible(true);
		btnEndEvaluation.setVisible(false);
	}

	private void getNextAthlete() {
		athlete = squadController.getNextAthlete();
	}

	private void getPreviousAthlete() {
		athlete = squadController.getPreviousAthlete();
	}

	private void showOverviewPanel() {
		frameClient.setFocusOnPanel(2);
	}

	private void setFocusOnFirstMark() {
		txtFieldDMark.requestFocusInWindow();
	}

	private void setKeyTypedListener() {
		txtFieldDMark.addKeyListener(calculateFinalMarkAdapter);
		txtFieldEMark1.addKeyListener(calculateFinalMarkAdapter);
		txtFieldEMark2.addKeyListener(calculateFinalMarkAdapter);
		txtFieldEMark3.addKeyListener(calculateFinalMarkAdapter);
		txtFieldBonus.addKeyListener(calculateFinalMarkAdapter);
		txtFieldPenalty.addKeyListener(calculateFinalMarkAdapter);
	}

	private void calculateFinalMarkField() {
		try {

			finalMark = ((Double.parseDouble(txtFieldEMark1.getText())
					+ Double.parseDouble(txtFieldEMark2.getText()) + Double
					.parseDouble(txtFieldEMark3.getText())) / 3)
					+ Double.parseDouble(txtFieldDMark.getText())
					+ Double.parseDouble(txtFieldBonus.getText())
					- Double.parseDouble(txtFieldPenalty.getText());
			txtFieldFinalMark.setText("" + finalMark);

			btnNext.setEnabled(true);
			btnPrevious.setEnabled(true);
			btnEndEvaluation.setEnabled(true);
			btnOverview.setEnabled(true);
			btnNext.setToolTipText(null);
			btnPrevious.setToolTipText(null);
			btnEndEvaluation.setToolTipText(null);
			btnOverview.setToolTipText(null);

			frameClient.setOverviewAndActualSquadPanelsEnabled();

			checkButtons();

		} catch (NumberFormatException nfe) {
			txtFieldFinalMark.setText("Endnote kann nicht berechnet werden.");

			frameClient.setOverviewAndActualSquadPanelsDisabled();

			btnNext.setEnabled(false);
			btnPrevious.setEnabled(false);
			btnEndEvaluation.setEnabled(false);
			btnOverview.setEnabled(false);
			btnNext.setToolTipText("Der Athlet muss zuerst korrekt bewertet werden.");
			btnPrevious
					.setToolTipText("Der Athlet muss zuerst korrekt bewertet werden.");
			btnEndEvaluation
					.setToolTipText("Der Athlet muss zuerst korrekt bewertet werden.");
			btnOverview
					.setToolTipText("Der Athlet muss zuerst korrekt bewertet werden.");
		}
	}
	
	private class FocusAdapterMark extends FocusAdapter {
		private final JTextField txtField;

		public FocusAdapterMark(JTextField txtField) {
			this.txtField = txtField;
		}

		public void focusLost(FocusEvent e) {
			try {
				Double.valueOf(txtField.getText());
			} catch (NumberFormatException nfe) {
				txtField.setText("");
			}
		}
	};

	private KeyAdapter calculateFinalMarkAdapter = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			calculateFinalMarkField();
		}

	};

	@Override
	public void update(Observable arg0, Object arg1) {
		loadAthleteFields();

	}

}
