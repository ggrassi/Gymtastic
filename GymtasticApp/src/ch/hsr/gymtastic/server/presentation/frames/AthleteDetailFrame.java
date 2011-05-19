package ch.hsr.gymtastic.server.presentation.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ch.hsr.gymtastic.domain.Association;
import ch.hsr.gymtastic.domain.Athlete;
import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.presentation.models.AthleteDetailTableModel;

public class AthleteDetailFrame {

    private JFrame frmAthletDetailansicht;
    private JTable tableMarks;
    private JTextField txtFieldFirstName;
    private JTextField txtFieldLastName;
    private JTextField txtFieldAssocation;
    private JTextField txtFieldAddress;
    private JTextField txtFieldYearOfBirth;
    private JTextField txtFieldStartNr;
    private JTextField txtFieldProgramClass;
    private JTextField txtFieldSquad;
    private JTextField txtFieldRank;
    private Athlete athlete;
    private JButton btnCancel;
    private JButton btnSave;
    private JPanel panelMarks;
    private JScrollPane scrollPaneMarks;
    private JPanel panelSaveCancel;
    private JPanel panelCompetitionInfo;
    private JPanel panelCompetitionInfoBorder;
    private JPanel panelCompetitionInfoSaveCancel;
    private GymCupController gymCupController;
    private AthleteDetailTableModel athleteDetailTableModel;

    /**
     * Launch the application.
     * 
     * @param athlete
     * @param gymCupController
     */
    public static void open(final Athlete athlete, final GymCupController gymCupController) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    AthleteDetailFrame window = new AthleteDetailFrame(athlete, gymCupController);
		    window.frmAthletDetailansicht.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     * 
     * @param gymCupController
     */

    /*
     * TODO: muss nur observer sein wenn man zB will dass bei offenem
     * Detail-Fenster der aktuelle Rang des Athleten geupdated wird
     */
    public AthleteDetailFrame(Athlete athlete, GymCupController gymCupController) {
	this.athlete = athlete;
	this.gymCupController = gymCupController;
	// this.gymCupController.addObserver(this);
	initGUI();
	initListeners();
	inizializeFields();
    }

    private void initListeners() {
	btnCancel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		updateAfterCancel();
	    }
	});

	txtFieldFirstName.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
		changesAthleteInformation();
	    }
	});

	txtFieldYearOfBirth.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent e) {
		changesAthleteInformation();
	    }
	});

	txtFieldLastName.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent e) {
		changesAthleteInformation();
	    }
	});

	txtFieldAssocation.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent e) {
		changesAthleteInformation();
	    }
	});

	txtFieldAddress.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent e) {
		changesAthleteInformation();
	    }
	});

	txtFieldStartNr.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent e) {
		changesAthleteInformation();
	    }
	});

	txtFieldProgramClass.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent e) {
		changesAthleteInformation();
	    }
	});

	txtFieldSquad.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent e) {
		changesAthleteInformation();
	    }
	});

	btnSave.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		saveAthleteInfos();
	    }
	});
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initGUI() {
	frmAthletDetailansicht = new JFrame();
	frmAthletDetailansicht.setTitle("Athlet Detailansicht");
	frmAthletDetailansicht.setResizable(false);
	frmAthletDetailansicht.setBounds(100, 100, 632, 350);
	frmAthletDetailansicht.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 280, 0, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 170, 0, 0 };
	gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
	frmAthletDetailansicht.getContentPane().setLayout(gridBagLayout);

	JPanel panelPersonBorder = new JPanel();
	panelPersonBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Person",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelPersonBorder = new GridBagConstraints();
	gbc_panelPersonBorder.anchor = GridBagConstraints.NORTH;
	gbc_panelPersonBorder.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelPersonBorder.gridheight = 2;
	gbc_panelPersonBorder.insets = new Insets(0, 0, 5, 5);
	gbc_panelPersonBorder.gridx = 0;
	gbc_panelPersonBorder.gridy = 0;
	frmAthletDetailansicht.getContentPane().add(panelPersonBorder, gbc_panelPersonBorder);
	GridBagLayout gbl_panelPersonBorder = new GridBagLayout();
	gbl_panelPersonBorder.columnWidths = new int[] { 0, 0 };
	gbl_panelPersonBorder.rowHeights = new int[] { 0, 0, 0 };
	gbl_panelPersonBorder.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panelPersonBorder.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	panelPersonBorder.setLayout(gbl_panelPersonBorder);

	JPanel panelPerson = new JPanel();
	GridBagConstraints gbc_panelPerson = new GridBagConstraints();
	gbc_panelPerson.anchor = GridBagConstraints.NORTH;
	gbc_panelPerson.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelPerson.gridheight = 2;
	gbc_panelPerson.insets = new Insets(0, 0, 5, 0);
	gbc_panelPerson.gridx = 0;
	gbc_panelPerson.gridy = 0;
	panelPersonBorder.add(panelPerson, gbc_panelPerson);
	GridBagLayout gbl_panelPerson = new GridBagLayout();
	gbl_panelPerson.columnWidths = new int[] { 0, 0, 0 };
	gbl_panelPerson.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
	gbl_panelPerson.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	gbl_panelPerson.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	panelPerson.setLayout(gbl_panelPerson);

	JLabel lblFirstName = new JLabel("Vorname: ");
	GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
	gbc_lblFirstName.anchor = GridBagConstraints.WEST;
	gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
	gbc_lblFirstName.gridx = 0;
	gbc_lblFirstName.gridy = 0;
	panelPerson.add(lblFirstName, gbc_lblFirstName);

	txtFieldFirstName = new JTextField();
	GridBagConstraints gbc_txtFieldFirstName = new GridBagConstraints();
	gbc_txtFieldFirstName.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldFirstName.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldFirstName.gridx = 1;
	gbc_txtFieldFirstName.gridy = 0;
	panelPerson.add(txtFieldFirstName, gbc_txtFieldFirstName);
	txtFieldFirstName.setColumns(10);

	JLabel lblLastName = new JLabel("Nachname: ");
	GridBagConstraints gbc_lblLastName = new GridBagConstraints();
	gbc_lblLastName.anchor = GridBagConstraints.WEST;
	gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
	gbc_lblLastName.gridx = 0;
	gbc_lblLastName.gridy = 1;
	panelPerson.add(lblLastName, gbc_lblLastName);

	txtFieldLastName = new JTextField();
	GridBagConstraints gbc_txtFieldLastName = new GridBagConstraints();
	gbc_txtFieldLastName.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldLastName.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldLastName.gridx = 1;
	gbc_txtFieldLastName.gridy = 1;
	panelPerson.add(txtFieldLastName, gbc_txtFieldLastName);
	txtFieldLastName.setColumns(10);

	JLabel lblAssociation = new JLabel("Verein: ");
	GridBagConstraints gbc_lblAssociation = new GridBagConstraints();
	gbc_lblAssociation.anchor = GridBagConstraints.WEST;
	gbc_lblAssociation.insets = new Insets(0, 0, 5, 5);
	gbc_lblAssociation.gridx = 0;
	gbc_lblAssociation.gridy = 2;
	panelPerson.add(lblAssociation, gbc_lblAssociation);

	txtFieldAssocation = new JTextField();
	GridBagConstraints gbc_txtFieldAssocation = new GridBagConstraints();
	gbc_txtFieldAssocation.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldAssocation.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldAssocation.gridx = 1;
	gbc_txtFieldAssocation.gridy = 2;
	panelPerson.add(txtFieldAssocation, gbc_txtFieldAssocation);
	txtFieldAssocation.setColumns(10);

	JLabel lblAddress = new JLabel("Adresse: ");
	GridBagConstraints gbc_lblAddress = new GridBagConstraints();
	gbc_lblAddress.anchor = GridBagConstraints.WEST;
	gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
	gbc_lblAddress.gridx = 0;
	gbc_lblAddress.gridy = 3;
	panelPerson.add(lblAddress, gbc_lblAddress);

	txtFieldAddress = new JTextField();
	GridBagConstraints gbc_txtFieldAddress = new GridBagConstraints();
	gbc_txtFieldAddress.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldAddress.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldAddress.gridx = 1;
	gbc_txtFieldAddress.gridy = 3;
	panelPerson.add(txtFieldAddress, gbc_txtFieldAddress);
	txtFieldAddress.setColumns(10);

	JLabel lblYearOfBirth = new JLabel("Jahrgang: ");
	GridBagConstraints gbc_lblYearOfBirth = new GridBagConstraints();
	gbc_lblYearOfBirth.anchor = GridBagConstraints.WEST;
	gbc_lblYearOfBirth.insets = new Insets(0, 0, 0, 5);
	gbc_lblYearOfBirth.gridx = 0;
	gbc_lblYearOfBirth.gridy = 4;
	panelPerson.add(lblYearOfBirth, gbc_lblYearOfBirth);

	txtFieldYearOfBirth = new JTextField();
	GridBagConstraints gbc_txtFieldYearOfBirth = new GridBagConstraints();
	gbc_txtFieldYearOfBirth.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldYearOfBirth.gridx = 1;
	gbc_txtFieldYearOfBirth.gridy = 4;
	panelPerson.add(txtFieldYearOfBirth, gbc_txtFieldYearOfBirth);
	txtFieldYearOfBirth.setColumns(10);

	panelCompetitionInfoSaveCancel = new JPanel();
	GridBagConstraints gbc_panelCompetitionInfoSaveCancel = new GridBagConstraints();
	gbc_panelCompetitionInfoSaveCancel.anchor = GridBagConstraints.NORTH;
	gbc_panelCompetitionInfoSaveCancel.gridheight = 2;
	gbc_panelCompetitionInfoSaveCancel.insets = new Insets(0, 0, 5, 0);
	gbc_panelCompetitionInfoSaveCancel.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelCompetitionInfoSaveCancel.gridx = 1;
	gbc_panelCompetitionInfoSaveCancel.gridy = 0;
	frmAthletDetailansicht.getContentPane().add(panelCompetitionInfoSaveCancel, gbc_panelCompetitionInfoSaveCancel);
	GridBagLayout gbl_panelCompetitionInfoSaveCancel = new GridBagLayout();
	gbl_panelCompetitionInfoSaveCancel.columnWidths = new int[] { 0, 0 };
	gbl_panelCompetitionInfoSaveCancel.rowHeights = new int[] { 0, 0, 0 };
	gbl_panelCompetitionInfoSaveCancel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panelCompetitionInfoSaveCancel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
	panelCompetitionInfoSaveCancel.setLayout(gbl_panelCompetitionInfoSaveCancel);

	panelCompetitionInfoBorder = new JPanel();
	panelCompetitionInfoBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"Wettkampf Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelCompetitionInfoBorder = new GridBagConstraints();
	gbc_panelCompetitionInfoBorder.anchor = GridBagConstraints.NORTH;
	gbc_panelCompetitionInfoBorder.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelCompetitionInfoBorder.insets = new Insets(0, 0, 5, 0);
	gbc_panelCompetitionInfoBorder.gridx = 0;
	gbc_panelCompetitionInfoBorder.gridy = 0;
	panelCompetitionInfoSaveCancel.add(panelCompetitionInfoBorder, gbc_panelCompetitionInfoBorder);
	GridBagLayout gbl_panelCompetitionInfoBorder = new GridBagLayout();
	gbl_panelCompetitionInfoBorder.columnWidths = new int[] { 315, 8, 0 };
	gbl_panelCompetitionInfoBorder.rowHeights = new int[] { 0, 0 };
	gbl_panelCompetitionInfoBorder.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
	gbl_panelCompetitionInfoBorder.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panelCompetitionInfoBorder.setLayout(gbl_panelCompetitionInfoBorder);

	panelCompetitionInfo = new JPanel();
	GridBagConstraints gbc_panelCompetitionInfo = new GridBagConstraints();
	gbc_panelCompetitionInfo.fill = GridBagConstraints.BOTH;
	gbc_panelCompetitionInfo.gridwidth = 2;
	gbc_panelCompetitionInfo.insets = new Insets(0, 0, 0, 5);
	gbc_panelCompetitionInfo.gridx = 0;
	gbc_panelCompetitionInfo.gridy = 0;
	panelCompetitionInfoBorder.add(panelCompetitionInfo, gbc_panelCompetitionInfo);
	GridBagLayout gbl_panelCompetitionInfo = new GridBagLayout();
	gbl_panelCompetitionInfo.columnWidths = new int[] { 0, 0, 0 };
	gbl_panelCompetitionInfo.rowHeights = new int[] { 0, 0, 0, 0, 0 };
	gbl_panelCompetitionInfo.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	gbl_panelCompetitionInfo.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	panelCompetitionInfo.setLayout(gbl_panelCompetitionInfo);

	JLabel lblStartNr = new JLabel("StartNr: ");
	GridBagConstraints gbc_lblStartNr = new GridBagConstraints();
	gbc_lblStartNr.anchor = GridBagConstraints.WEST;
	gbc_lblStartNr.insets = new Insets(0, 0, 5, 5);
	gbc_lblStartNr.gridx = 0;
	gbc_lblStartNr.gridy = 0;
	panelCompetitionInfo.add(lblStartNr, gbc_lblStartNr);

	txtFieldStartNr = new JTextField();
	GridBagConstraints gbc_txtFieldStartNr = new GridBagConstraints();
	gbc_txtFieldStartNr.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldStartNr.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldStartNr.gridx = 1;
	gbc_txtFieldStartNr.gridy = 0;
	panelCompetitionInfo.add(txtFieldStartNr, gbc_txtFieldStartNr);
	txtFieldStartNr.setColumns(10);
	txtFieldStartNr.setEnabled(false);

	JLabel lblProgramClass = new JLabel("Programmklasse: ");
	GridBagConstraints gbc_lblProgramClass = new GridBagConstraints();
	gbc_lblProgramClass.anchor = GridBagConstraints.WEST;
	gbc_lblProgramClass.insets = new Insets(0, 0, 5, 5);
	gbc_lblProgramClass.gridx = 0;
	gbc_lblProgramClass.gridy = 1;
	panelCompetitionInfo.add(lblProgramClass, gbc_lblProgramClass);

	txtFieldProgramClass = new JTextField();
	GridBagConstraints gbc_txtFieldProgramClass = new GridBagConstraints();
	gbc_txtFieldProgramClass.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldProgramClass.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldProgramClass.gridx = 1;
	gbc_txtFieldProgramClass.gridy = 1;
	panelCompetitionInfo.add(txtFieldProgramClass, gbc_txtFieldProgramClass);
	txtFieldProgramClass.setColumns(10);

	JLabel lblSquad = new JLabel("Riege: ");
	GridBagConstraints gbc_lblSquad = new GridBagConstraints();
	gbc_lblSquad.anchor = GridBagConstraints.WEST;
	gbc_lblSquad.insets = new Insets(0, 0, 5, 5);
	gbc_lblSquad.gridx = 0;
	gbc_lblSquad.gridy = 2;
	panelCompetitionInfo.add(lblSquad, gbc_lblSquad);

	txtFieldSquad = new JTextField();
	GridBagConstraints gbc_txtFieldSquad = new GridBagConstraints();
	gbc_txtFieldSquad.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldSquad.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldSquad.gridx = 1;
	gbc_txtFieldSquad.gridy = 2;
	panelCompetitionInfo.add(txtFieldSquad, gbc_txtFieldSquad);
	txtFieldSquad.setColumns(10);
	txtFieldSquad.setEnabled(false);

	JLabel lblRank = new JLabel("Rang: ");
	GridBagConstraints gbc_lblRank = new GridBagConstraints();
	gbc_lblRank.anchor = GridBagConstraints.WEST;
	gbc_lblRank.insets = new Insets(0, 0, 0, 5);
	gbc_lblRank.gridx = 0;
	gbc_lblRank.gridy = 3;
	panelCompetitionInfo.add(lblRank, gbc_lblRank);

	txtFieldRank = new JTextField();
	GridBagConstraints gbc_txtFieldRank = new GridBagConstraints();
	gbc_txtFieldRank.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldRank.gridx = 1;
	gbc_txtFieldRank.gridy = 3;
	panelCompetitionInfo.add(txtFieldRank, gbc_txtFieldRank);
	txtFieldRank.setColumns(10);
	txtFieldRank.setEnabled(false);

	panelSaveCancel = new JPanel();
	GridBagConstraints gbc_panelSaveCancel = new GridBagConstraints();
	gbc_panelSaveCancel.anchor = GridBagConstraints.NORTH;
	gbc_panelSaveCancel.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelSaveCancel.gridx = 0;
	gbc_panelSaveCancel.gridy = 1;
	panelCompetitionInfoSaveCancel.add(panelSaveCancel, gbc_panelSaveCancel);
	GridBagLayout gbl_panelSaveCancel = new GridBagLayout();
	gbl_panelSaveCancel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	gbl_panelSaveCancel.rowHeights = new int[] { 0, 0 };
	gbl_panelSaveCancel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
	gbl_panelSaveCancel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panelSaveCancel.setLayout(gbl_panelSaveCancel);

	btnCancel = new JButton("Abbrechen");
	GridBagConstraints gbc_btnCancel = new GridBagConstraints();
	gbc_btnCancel.anchor = GridBagConstraints.EAST;
	gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
	gbc_btnCancel.gridx = 3;
	gbc_btnCancel.gridy = 0;
	panelSaveCancel.add(btnCancel, gbc_btnCancel);
	btnCancel.setEnabled(false);

	btnSave = new JButton("Speichern");
	GridBagConstraints gbc_btnSave = new GridBagConstraints();
	gbc_btnSave.insets = new Insets(0, 0, 0, 5);
	gbc_btnSave.anchor = GridBagConstraints.EAST;
	gbc_btnSave.gridx = 4;
	gbc_btnSave.gridy = 0;
	panelSaveCancel.add(btnSave, gbc_btnSave);
	btnSave.setEnabled(false);

	panelMarks = new JPanel();
	panelMarks.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bewertungen",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelMarks = new GridBagConstraints();
	gbc_panelMarks.fill = GridBagConstraints.BOTH;
	gbc_panelMarks.gridwidth = 2;
	gbc_panelMarks.gridx = 0;
	gbc_panelMarks.gridy = 2;
	frmAthletDetailansicht.getContentPane().add(panelMarks, gbc_panelMarks);
	GridBagLayout gbl_panelMarks = new GridBagLayout();
	gbl_panelMarks.columnWidths = new int[] { 0, 0, 0 };
	gbl_panelMarks.rowHeights = new int[] { 0, 0 };
	gbl_panelMarks.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
	gbl_panelMarks.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	panelMarks.setLayout(gbl_panelMarks);

	scrollPaneMarks = new JScrollPane();
	GridBagConstraints gbc_scrollPaneMarks = new GridBagConstraints();
	gbc_scrollPaneMarks.gridwidth = 2;
	gbc_scrollPaneMarks.insets = new Insets(0, 0, 0, 5);
	gbc_scrollPaneMarks.fill = GridBagConstraints.BOTH;
	gbc_scrollPaneMarks.gridx = 0;
	gbc_scrollPaneMarks.gridy = 0;
	panelMarks.add(scrollPaneMarks, gbc_scrollPaneMarks);

	tableMarks = new JTable();
	athleteDetailTableModel = new AthleteDetailTableModel(athlete, gymCupController);
	tableMarks.setModel(athleteDetailTableModel);
	scrollPaneMarks.setViewportView(tableMarks);

    }

    private void inizializeFields() {
	if (athlete != null) {
	    txtFieldAddress.setText(athlete.getAddress());
	    txtFieldAssocation.setText(athlete.getAssociation().getName());
	    txtFieldFirstName.setText(athlete.getFirstName());
	    txtFieldLastName.setText(athlete.getLastName());
	    txtFieldProgramClass.setText(athlete.getPrgClass());
	    txtFieldSquad.setText("" + athlete.getSquadId());
	    txtFieldStartNr.setText("" + athlete.getStartNr());
	    txtFieldYearOfBirth.setText("" + athlete.getYearOfBirth());
	} else {
	    txtFieldSquad.setEnabled(true);
	}
    }

    private void changesAthleteInformation() {
	if (nothingChanged()) {
	    btnCancel.setEnabled(false);
	    btnSave.setEnabled(false);
	} else {
	    btnCancel.setEnabled(true);
	    btnSave.setEnabled(true);
	}
    }

    private boolean nothingChanged() {
	if (athlete != null) {
	    return txtFieldAddress.getText().equals(athlete.getAddress())
		    && txtFieldAssocation.getText().equals(athlete.getAssociation().getName())
		    && txtFieldFirstName.getText().equals(athlete.getFirstName())
		    && txtFieldLastName.getText().equals(athlete.getLastName())
		    && txtFieldProgramClass.getText().equals(athlete.getPrgClass())
		    && txtFieldSquad.getText().equals(athlete.getSquadId() + "")
		    && txtFieldStartNr.getText().equals(athlete.getStartNr() + "")
		    && txtFieldYearOfBirth.getText().equals(athlete.getYearOfBirth() + "");
	} else {
	    return txtFieldAddress.getText().isEmpty() && txtFieldAssocation.getText().isEmpty()
		    && txtFieldFirstName.getText().isEmpty() && txtFieldLastName.getText().isEmpty()
		    && txtFieldProgramClass.getText().isEmpty() && txtFieldSquad.getText().isEmpty()
		    && txtFieldStartNr.getText().isEmpty() && txtFieldYearOfBirth.getText().isEmpty();
	}
    }

    private void updateAfterCancel() {
	if (athlete != null) {
	    txtFieldAddress.setText(athlete.getAddress());
	    txtFieldAssocation.setText(athlete.getAssociation().getName());
	    txtFieldFirstName.setText(athlete.getFirstName());
	    txtFieldLastName.setText(athlete.getLastName());
	    txtFieldProgramClass.setText(athlete.getPrgClass());
	    txtFieldSquad.setText(athlete.getSquadId() + "");
	    txtFieldStartNr.setText(athlete.getStartNr() + "");
	    txtFieldYearOfBirth.setText(athlete.getYearOfBirth() + "");

	} else {
	    txtFieldAddress.setText("");
	    txtFieldAssocation.setText("");
	    txtFieldFirstName.setText("");
	    txtFieldLastName.setText("");
	    txtFieldProgramClass.setText("");
	    txtFieldSquad.setText("");
	    txtFieldStartNr.setText("");
	    txtFieldYearOfBirth.setText("");
	}
	btnSave.setEnabled(false);
	btnCancel.setEnabled(false);
    }

    private void saveAthleteInfos() {
	if (athlete != null) {
	    athlete.setAddress(txtFieldAddress.getText());
	    athlete
		    .setAssociation(new Association(txtFieldAssocation.getText(), athlete.getAssociation()
			    .getLocation()));
	    athlete.setFirstName(txtFieldFirstName.getText());
	    athlete.setLastName(txtFieldLastName.getText());
	    athlete.setPrgClass(txtFieldProgramClass.getText());
	    athlete.setSquadId(Integer.parseInt(txtFieldSquad.getText()));
	    athlete.setStartNr(Integer.parseInt(txtFieldStartNr.getText()));
	    athlete.setYearOfBirth(Integer.parseInt(txtFieldYearOfBirth.getText()));

	} else {
	    athlete = new Athlete(Integer.parseInt(txtFieldSquad.getText()), gymCupController.getGymCup()
		    .getAllAthletes().size(), txtFieldProgramClass.getText(), txtFieldFirstName.getText(),
		    txtFieldLastName.getText(), txtFieldAddress.getText(), Integer.parseInt(txtFieldYearOfBirth
			    .getText()), new Association(txtFieldAssocation.getText(), ""));
	    gymCupController.getGymCup().addAthleteToSquad(Integer.parseInt(txtFieldSquad.getText()), athlete);
	    athleteDetailTableModel.setAthlete(athlete);
	    updateAfterCancel();
	}
	btnSave.setEnabled(false);
	btnCancel.setEnabled(false);
	gymCupController.getGymCup().athleteChanged();
    }

}
