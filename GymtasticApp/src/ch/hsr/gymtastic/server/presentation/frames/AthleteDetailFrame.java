package ch.hsr.gymtastic.server.presentation.frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class AthleteDetailFrame {

    private JFrame frame;
    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;

    /**
     * Launch the application.
     */
    public static void open() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    AthleteDetailFrame window = new AthleteDetailFrame();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public AthleteDetailFrame() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 632, 456);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{280, 0, 0};
	gridBagLayout.rowHeights = new int[]{0, 170, 0, 0};
	gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
	frame.getContentPane().setLayout(gridBagLayout);
	
	JPanel panelPersonBorder = new JPanel();
	panelPersonBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Person", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelPersonBorder = new GridBagConstraints();
	gbc_panelPersonBorder.anchor = GridBagConstraints.NORTH;
	gbc_panelPersonBorder.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelPersonBorder.gridheight = 2;
	gbc_panelPersonBorder.insets = new Insets(0, 0, 5, 5);
	gbc_panelPersonBorder.gridx = 0;
	gbc_panelPersonBorder.gridy = 0;
	frame.getContentPane().add(panelPersonBorder, gbc_panelPersonBorder);
	GridBagLayout gbl_panelPersonBorder = new GridBagLayout();
	gbl_panelPersonBorder.columnWidths = new int[]{0, 0};
	gbl_panelPersonBorder.rowHeights = new int[]{0, 0, 0};
	gbl_panelPersonBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_panelPersonBorder.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
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
	gbl_panelPerson.columnWidths = new int[]{0, 0, 0};
	gbl_panelPerson.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
	gbl_panelPerson.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	gbl_panelPerson.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	panelPerson.setLayout(gbl_panelPerson);
	
	JLabel lblVorname = new JLabel("Vorname: ");
	GridBagConstraints gbc_lblVorname = new GridBagConstraints();
	gbc_lblVorname.anchor = GridBagConstraints.WEST;
	gbc_lblVorname.insets = new Insets(0, 0, 5, 5);
	gbc_lblVorname.gridx = 0;
	gbc_lblVorname.gridy = 0;
	panelPerson.add(lblVorname, gbc_lblVorname);
	
	textField = new JTextField();
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.insets = new Insets(0, 0, 5, 0);
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.gridx = 1;
	gbc_textField.gridy = 0;
	panelPerson.add(textField, gbc_textField);
	textField.setColumns(10);
	
	JLabel lblNachname = new JLabel("Nachname: ");
	GridBagConstraints gbc_lblNachname = new GridBagConstraints();
	gbc_lblNachname.anchor = GridBagConstraints.WEST;
	gbc_lblNachname.insets = new Insets(0, 0, 5, 5);
	gbc_lblNachname.gridx = 0;
	gbc_lblNachname.gridy = 1;
	panelPerson.add(lblNachname, gbc_lblNachname);
	
	textField_1 = new JTextField();
	GridBagConstraints gbc_textField_1 = new GridBagConstraints();
	gbc_textField_1.insets = new Insets(0, 0, 5, 0);
	gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_1.gridx = 1;
	gbc_textField_1.gridy = 1;
	panelPerson.add(textField_1, gbc_textField_1);
	textField_1.setColumns(10);
	
	JLabel lblVerein = new JLabel("Verein: ");
	GridBagConstraints gbc_lblVerein = new GridBagConstraints();
	gbc_lblVerein.anchor = GridBagConstraints.WEST;
	gbc_lblVerein.insets = new Insets(0, 0, 5, 5);
	gbc_lblVerein.gridx = 0;
	gbc_lblVerein.gridy = 2;
	panelPerson.add(lblVerein, gbc_lblVerein);
	
	textField_2 = new JTextField();
	GridBagConstraints gbc_textField_2 = new GridBagConstraints();
	gbc_textField_2.insets = new Insets(0, 0, 5, 0);
	gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_2.gridx = 1;
	gbc_textField_2.gridy = 2;
	panelPerson.add(textField_2, gbc_textField_2);
	textField_2.setColumns(10);
	
	JLabel lblJahrgang = new JLabel("Adresse: ");
	GridBagConstraints gbc_lblJahrgang = new GridBagConstraints();
	gbc_lblJahrgang.anchor = GridBagConstraints.WEST;
	gbc_lblJahrgang.insets = new Insets(0, 0, 5, 5);
	gbc_lblJahrgang.gridx = 0;
	gbc_lblJahrgang.gridy = 3;
	panelPerson.add(lblJahrgang, gbc_lblJahrgang);
	
	textField_3 = new JTextField();
	GridBagConstraints gbc_textField_3 = new GridBagConstraints();
	gbc_textField_3.insets = new Insets(0, 0, 5, 0);
	gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_3.gridx = 1;
	gbc_textField_3.gridy = 3;
	panelPerson.add(textField_3, gbc_textField_3);
	textField_3.setColumns(10);
	
	JLabel lblJahrgang_1 = new JLabel("Jahrgang: ");
	GridBagConstraints gbc_lblJahrgang_1 = new GridBagConstraints();
	gbc_lblJahrgang_1.anchor = GridBagConstraints.WEST;
	gbc_lblJahrgang_1.insets = new Insets(0, 0, 0, 5);
	gbc_lblJahrgang_1.gridx = 0;
	gbc_lblJahrgang_1.gridy = 4;
	panelPerson.add(lblJahrgang_1, gbc_lblJahrgang_1);
	
	textField_4 = new JTextField();
	GridBagConstraints gbc_textField_4 = new GridBagConstraints();
	gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_4.gridx = 1;
	gbc_textField_4.gridy = 4;
	panelPerson.add(textField_4, gbc_textField_4);
	textField_4.setColumns(10);
	
	JPanel panelCompetitionInfoSaveCancel = new JPanel();
	GridBagConstraints gbc_panelCompetitionInfoSaveCancel = new GridBagConstraints();
	gbc_panelCompetitionInfoSaveCancel.anchor = GridBagConstraints.NORTH;
	gbc_panelCompetitionInfoSaveCancel.gridheight = 2;
	gbc_panelCompetitionInfoSaveCancel.insets = new Insets(0, 0, 5, 0);
	gbc_panelCompetitionInfoSaveCancel.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelCompetitionInfoSaveCancel.gridx = 1;
	gbc_panelCompetitionInfoSaveCancel.gridy = 0;
	frame.getContentPane().add(panelCompetitionInfoSaveCancel, gbc_panelCompetitionInfoSaveCancel);
	GridBagLayout gbl_panelCompetitionInfoSaveCancel = new GridBagLayout();
	gbl_panelCompetitionInfoSaveCancel.columnWidths = new int[]{0, 0};
	gbl_panelCompetitionInfoSaveCancel.rowHeights = new int[]{0, 0, 0};
	gbl_panelCompetitionInfoSaveCancel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_panelCompetitionInfoSaveCancel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	panelCompetitionInfoSaveCancel.setLayout(gbl_panelCompetitionInfoSaveCancel);
	
	JPanel panelCompetitionInfoBorder = new JPanel();
	panelCompetitionInfoBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Wettkampf Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelCompetitionInfoBorder = new GridBagConstraints();
	gbc_panelCompetitionInfoBorder.anchor = GridBagConstraints.NORTH;
	gbc_panelCompetitionInfoBorder.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelCompetitionInfoBorder.insets = new Insets(0, 0, 5, 0);
	gbc_panelCompetitionInfoBorder.gridx = 0;
	gbc_panelCompetitionInfoBorder.gridy = 0;
	panelCompetitionInfoSaveCancel.add(panelCompetitionInfoBorder, gbc_panelCompetitionInfoBorder);
	GridBagLayout gbl_panelCompetitionInfoBorder = new GridBagLayout();
	gbl_panelCompetitionInfoBorder.columnWidths = new int[]{315, 8, 0};
	gbl_panelCompetitionInfoBorder.rowHeights = new int[]{0, 0};
	gbl_panelCompetitionInfoBorder.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
	gbl_panelCompetitionInfoBorder.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panelCompetitionInfoBorder.setLayout(gbl_panelCompetitionInfoBorder);
	
	JPanel panelCompetitionInfo = new JPanel();
	GridBagConstraints gbc_panelCompetitionInfo = new GridBagConstraints();
	gbc_panelCompetitionInfo.fill = GridBagConstraints.BOTH;
	gbc_panelCompetitionInfo.gridwidth = 2;
	gbc_panelCompetitionInfo.insets = new Insets(0, 0, 0, 5);
	gbc_panelCompetitionInfo.gridx = 0;
	gbc_panelCompetitionInfo.gridy = 0;
	panelCompetitionInfoBorder.add(panelCompetitionInfo, gbc_panelCompetitionInfo);
	GridBagLayout gbl_panelCompetitionInfo = new GridBagLayout();
	gbl_panelCompetitionInfo.columnWidths = new int[]{0, 0, 0};
	gbl_panelCompetitionInfo.rowHeights = new int[]{0, 0, 0, 0, 0};
	gbl_panelCompetitionInfo.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	gbl_panelCompetitionInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	panelCompetitionInfo.setLayout(gbl_panelCompetitionInfo);
	
	JLabel lblStartnr = new JLabel("StartNr: ");
	GridBagConstraints gbc_lblStartnr = new GridBagConstraints();
	gbc_lblStartnr.anchor = GridBagConstraints.WEST;
	gbc_lblStartnr.insets = new Insets(0, 0, 5, 5);
	gbc_lblStartnr.gridx = 0;
	gbc_lblStartnr.gridy = 0;
	panelCompetitionInfo.add(lblStartnr, gbc_lblStartnr);
	
	textField_5 = new JTextField();
	GridBagConstraints gbc_textField_5 = new GridBagConstraints();
	gbc_textField_5.insets = new Insets(0, 0, 5, 0);
	gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_5.gridx = 1;
	gbc_textField_5.gridy = 0;
	panelCompetitionInfo.add(textField_5, gbc_textField_5);
	textField_5.setColumns(10);
	
	JLabel lblProgrammklasse = new JLabel("Programmklasse: ");
	GridBagConstraints gbc_lblProgrammklasse = new GridBagConstraints();
	gbc_lblProgrammklasse.anchor = GridBagConstraints.WEST;
	gbc_lblProgrammklasse.insets = new Insets(0, 0, 5, 5);
	gbc_lblProgrammklasse.gridx = 0;
	gbc_lblProgrammklasse.gridy = 1;
	panelCompetitionInfo.add(lblProgrammklasse, gbc_lblProgrammklasse);
	
	textField_6 = new JTextField();
	GridBagConstraints gbc_textField_6 = new GridBagConstraints();
	gbc_textField_6.insets = new Insets(0, 0, 5, 0);
	gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_6.gridx = 1;
	gbc_textField_6.gridy = 1;
	panelCompetitionInfo.add(textField_6, gbc_textField_6);
	textField_6.setColumns(10);
	
	JLabel lblRiege = new JLabel("Riege: ");
	GridBagConstraints gbc_lblRiege = new GridBagConstraints();
	gbc_lblRiege.anchor = GridBagConstraints.WEST;
	gbc_lblRiege.insets = new Insets(0, 0, 5, 5);
	gbc_lblRiege.gridx = 0;
	gbc_lblRiege.gridy = 2;
	panelCompetitionInfo.add(lblRiege, gbc_lblRiege);
	
	textField_7 = new JTextField();
	GridBagConstraints gbc_textField_7 = new GridBagConstraints();
	gbc_textField_7.insets = new Insets(0, 0, 5, 0);
	gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_7.gridx = 1;
	gbc_textField_7.gridy = 2;
	panelCompetitionInfo.add(textField_7, gbc_textField_7);
	textField_7.setColumns(10);
	
	JLabel lblRang = new JLabel("Rang: ");
	GridBagConstraints gbc_lblRang = new GridBagConstraints();
	gbc_lblRang.anchor = GridBagConstraints.WEST;
	gbc_lblRang.insets = new Insets(0, 0, 0, 5);
	gbc_lblRang.gridx = 0;
	gbc_lblRang.gridy = 3;
	panelCompetitionInfo.add(lblRang, gbc_lblRang);
	
	textField_8 = new JTextField();
	GridBagConstraints gbc_textField_8 = new GridBagConstraints();
	gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField_8.gridx = 1;
	gbc_textField_8.gridy = 3;
	panelCompetitionInfo.add(textField_8, gbc_textField_8);
	textField_8.setColumns(10);
	
	JPanel panelSaveCancel = new JPanel();
	GridBagConstraints gbc_panelSaveCancel = new GridBagConstraints();
	gbc_panelSaveCancel.anchor = GridBagConstraints.NORTH;
	gbc_panelSaveCancel.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelSaveCancel.gridx = 0;
	gbc_panelSaveCancel.gridy = 1;
	panelCompetitionInfoSaveCancel.add(panelSaveCancel, gbc_panelSaveCancel);
	GridBagLayout gbl_panelSaveCancel = new GridBagLayout();
	gbl_panelSaveCancel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
	gbl_panelSaveCancel.rowHeights = new int[]{0, 0};
	gbl_panelSaveCancel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panelSaveCancel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panelSaveCancel.setLayout(gbl_panelSaveCancel);
	
	JButton btnCancel = new JButton("Abbrechen");
	GridBagConstraints gbc_btnCancel = new GridBagConstraints();
	gbc_btnCancel.anchor = GridBagConstraints.EAST;
	gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
	gbc_btnCancel.gridx = 3;
	gbc_btnCancel.gridy = 0;
	panelSaveCancel.add(btnCancel, gbc_btnCancel);
	
	JButton btnSave = new JButton("Speichern");
	GridBagConstraints gbc_btnSave = new GridBagConstraints();
	gbc_btnSave.insets = new Insets(0, 0, 0, 5);
	gbc_btnSave.anchor = GridBagConstraints.EAST;
	gbc_btnSave.gridx = 4;
	gbc_btnSave.gridy = 0;
	panelSaveCancel.add(btnSave, gbc_btnSave);
	
	JPanel panelMarks = new JPanel();
	panelMarks.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bewertungen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelMarks = new GridBagConstraints();
	gbc_panelMarks.fill = GridBagConstraints.BOTH;
	gbc_panelMarks.gridwidth = 2;
	gbc_panelMarks.gridx = 0;
	gbc_panelMarks.gridy = 2;
	frame.getContentPane().add(panelMarks, gbc_panelMarks);
	GridBagLayout gbl_panelMarks = new GridBagLayout();
	gbl_panelMarks.columnWidths = new int[]{0, 0, 0};
	gbl_panelMarks.rowHeights = new int[]{0, 0};
	gbl_panelMarks.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	gbl_panelMarks.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	panelMarks.setLayout(gbl_panelMarks);
	
	JScrollPane scrollPane = new JScrollPane();
	GridBagConstraints gbc_scrollPane = new GridBagConstraints();
	gbc_scrollPane.gridwidth = 2;
	gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
	gbc_scrollPane.fill = GridBagConstraints.BOTH;
	gbc_scrollPane.gridx = 0;
	gbc_scrollPane.gridy = 0;
	panelMarks.add(scrollPane, gbc_scrollPane);
	
	table = new JTable();
	table.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"New column", "New column", "New column", "New column", "New column", "New column", "New column"
		}
	));
	scrollPane.setViewportView(table);
    }

}
