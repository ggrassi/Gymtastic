package ch.hsr.gymtastic.presentation.panels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import ch.hsr.gymtastic.application.controller.SquadController;
import ch.hsr.gymtastic.domain.Athlete;

public class EvaluationPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFieldFinalMark;
	private JTextField txtFieldBonus;
	private JTextField txtFieldPenalty;
	private JTextField txtFieldEMark3;
	private JTextField txtFieldEMark2;
	private JTextField txtFieldEMark1;
	private JTextField txtFieldDMark;
	private SquadController presentationController;
	private int actualAthleteIndex = 0;
	private Athlete actualAthlete;
	private JButton btnNext;
	private JPanel panelSouth;
	private JButton btnPrevious;
	private JPanel panelSouthCenter;
	private JButton btnFinishRound;
	private JPanel panelCenter;
	private JPanel panelAthleteInfoBorder;
	private JPanel panelAthleteInfo;
	private JRadioButton rdbtnValid;
	private JRadioButton rdbtnInvalid;
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

	public EvaluationPanel(
			final SquadController presentationController) {
		this.presentationController = presentationController;
		initGUI();
		initListeners();
	}

	private void initListeners() {
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

	}

	private void initGUI() {
		setLayout(new BorderLayout(0, 0));

		panelSouth = new JPanel();
		add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BorderLayout(0, 0));

		btnPrevious = new JButton("Vorheriger Athlet");
		panelSouth.add(btnPrevious, BorderLayout.WEST);

		btnNext = new JButton("Nächster Athlet");

		panelSouth.add(btnNext, BorderLayout.EAST);

		panelSouthCenter = new JPanel();
		panelSouth.add(panelSouthCenter, BorderLayout.CENTER);
		GridBagLayout gbl_panelSouthCenter = new GridBagLayout();
		gbl_panelSouthCenter.columnWidths = new int[] { 0, 0 };
		gbl_panelSouthCenter.rowHeights = new int[] { 0, 0 };
		gbl_panelSouthCenter.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelSouthCenter.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelSouthCenter.setLayout(gbl_panelSouthCenter);

		btnFinishRound = new JButton("Durchgang Abschliessen");
		GridBagConstraints gbc_btnFinishRound = new GridBagConstraints();
		gbc_btnFinishRound.gridx = 0;
		gbc_btnFinishRound.gridy = 0;
		panelSouthCenter.add(btnFinishRound, gbc_btnFinishRound);

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
		gbl_panelAthleteInfo.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelAthleteInfo.columnWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelAthleteInfo.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelAthleteInfo.setLayout(gbl_panelAthleteInfo);

		rdbtnValid = new JRadioButton("tritt an");
		GridBagConstraints gbc_rdbtnValid = new GridBagConstraints();
		gbc_rdbtnValid.anchor = GridBagConstraints.WEST;
		gbc_rdbtnValid.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnValid.gridx = 0;
		gbc_rdbtnValid.gridy = 0;
		panelAthleteInfo.add(rdbtnValid, gbc_rdbtnValid);

		rdbtnInvalid = new JRadioButton("tritt nicht an");
		GridBagConstraints gbc_rdbtnInvalid = new GridBagConstraints();
		gbc_rdbtnInvalid.anchor = GridBagConstraints.WEST;
		gbc_rdbtnInvalid.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnInvalid.gridx = 0;
		gbc_rdbtnInvalid.gridy = 1;
		panelAthleteInfo.add(rdbtnInvalid, gbc_rdbtnInvalid);

		lblFirstName = new JLabel("Vorname:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 2;
		panelAthleteInfo.add(lblFirstName, gbc_lblFirstName);

		lblFirstNameField = new JLabel("New label");
		GridBagConstraints gbc_lblFirstNameField = new GridBagConstraints();
		gbc_lblFirstNameField.anchor = GridBagConstraints.WEST;
		gbc_lblFirstNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirstNameField.gridx = 1;
		gbc_lblFirstNameField.gridy = 2;
		panelAthleteInfo.add(lblFirstNameField, gbc_lblFirstNameField);

		lblLastName = new JLabel("Nachname:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 3;
		panelAthleteInfo.add(lblLastName, gbc_lblLastName);

		lblLastNameField = new JLabel("New label");
		GridBagConstraints gbc_lblLastNameField = new GridBagConstraints();
		gbc_lblLastNameField.anchor = GridBagConstraints.WEST;
		gbc_lblLastNameField.insets = new Insets(0, 0, 5, 0);
		gbc_lblLastNameField.gridx = 1;
		gbc_lblLastNameField.gridy = 3;
		panelAthleteInfo.add(lblLastNameField, gbc_lblLastNameField);

		lblSquad = new JLabel("Riege:");
		GridBagConstraints gbc_lblSquad = new GridBagConstraints();
		gbc_lblSquad.anchor = GridBagConstraints.WEST;
		gbc_lblSquad.insets = new Insets(0, 0, 5, 5);
		gbc_lblSquad.gridx = 0;
		gbc_lblSquad.gridy = 4;
		panelAthleteInfo.add(lblSquad, gbc_lblSquad);

		lblSquadField = new JLabel("New label");
		GridBagConstraints gbc_lblSquadField = new GridBagConstraints();
		gbc_lblSquadField.anchor = GridBagConstraints.WEST;
		gbc_lblSquadField.insets = new Insets(0, 0, 5, 0);
		gbc_lblSquadField.gridx = 1;
		gbc_lblSquadField.gridy = 4;
		panelAthleteInfo.add(lblSquadField, gbc_lblSquadField);

		lblPrgClass = new JLabel("Programmklasse:");
		GridBagConstraints gbc_lblPrgClass = new GridBagConstraints();
		gbc_lblPrgClass.anchor = GridBagConstraints.WEST;
		gbc_lblPrgClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrgClass.gridx = 0;
		gbc_lblPrgClass.gridy = 5;
		panelAthleteInfo.add(lblPrgClass, gbc_lblPrgClass);

		lblPrgClassField = new JLabel("New label");
		GridBagConstraints gbc_lblPrgClassField = new GridBagConstraints();
		gbc_lblPrgClassField.anchor = GridBagConstraints.WEST;
		gbc_lblPrgClassField.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrgClassField.gridx = 1;
		gbc_lblPrgClassField.gridy = 5;
		panelAthleteInfo.add(lblPrgClassField, gbc_lblPrgClassField);

		lblStartNr = new JLabel("Startnummer:");
		GridBagConstraints gbc_lblStartNr = new GridBagConstraints();
		gbc_lblStartNr.anchor = GridBagConstraints.WEST;
		gbc_lblStartNr.insets = new Insets(0, 0, 0, 5);
		gbc_lblStartNr.gridx = 0;
		gbc_lblStartNr.gridy = 6;
		panelAthleteInfo.add(lblStartNr, gbc_lblStartNr);

		lblStartNrField = new JLabel("New label");
		GridBagConstraints gbc_lblStartNrField = new GridBagConstraints();
		gbc_lblStartNrField.anchor = GridBagConstraints.WEST;
		gbc_lblStartNrField.gridx = 1;
		gbc_lblStartNrField.gridy = 6;
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
	}

}
