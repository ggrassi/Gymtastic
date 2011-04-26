package ch.hsr.gymtastic.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class JudgeDetailFrame {

    private JFrame frame;
    private JTextField txtFieldFirstName;
    private JTextField txtFieldLastName;
    private JTextField txtFieldAddress;
    private JTextField txtFieldDeviceTypeAlloc;
    private JTextField txtFieldMarkType;

    /**
     * Launch the application.
     */
    public static void open() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    JudgeDetailFrame window = new JudgeDetailFrame();
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
    public JudgeDetailFrame() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 550, 237);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{0, 0, 0};
	gridBagLayout.rowHeights = new int[]{0, 0, 0};
	gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
	frame.getContentPane().setLayout(gridBagLayout);
	
	JPanel panelPersonBorder = new JPanel();
	panelPersonBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Person", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelPersonBorder = new GridBagConstraints();
	gbc_panelPersonBorder.fill = GridBagConstraints.BOTH;
	gbc_panelPersonBorder.gridheight = 2;
	gbc_panelPersonBorder.insets = new Insets(0, 0, 0, 5);
	gbc_panelPersonBorder.gridx = 0;
	gbc_panelPersonBorder.gridy = 0;
	frame.getContentPane().add(panelPersonBorder, gbc_panelPersonBorder);
	GridBagLayout gbl_panelPersonBorder = new GridBagLayout();
	gbl_panelPersonBorder.columnWidths = new int[]{0, 0};
	gbl_panelPersonBorder.rowHeights = new int[]{0, 0, 0, 0, 0};
	gbl_panelPersonBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_panelPersonBorder.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
	panelPersonBorder.setLayout(gbl_panelPersonBorder);
	
	JPanel panelPerson = new JPanel();
	GridBagConstraints gbc_panelPerson = new GridBagConstraints();
	gbc_panelPerson.fill = GridBagConstraints.BOTH;
	gbc_panelPerson.gridheight = 4;
	gbc_panelPerson.insets = new Insets(0, 0, 5, 0);
	gbc_panelPerson.gridx = 0;
	gbc_panelPerson.gridy = 0;
	panelPersonBorder.add(panelPerson, gbc_panelPerson);
	GridBagLayout gbl_panelPerson = new GridBagLayout();
	gbl_panelPerson.columnWidths = new int[]{0, 0, 0};
	gbl_panelPerson.rowHeights = new int[]{0, 0, 0, 0, 0};
	gbl_panelPerson.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	gbl_panelPerson.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	panelPerson.setLayout(gbl_panelPerson);
	
	JLabel lblFirstName = new JLabel("Vorname: ");
	GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
	gbc_lblFirstName.anchor = GridBagConstraints.NORTHWEST;
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
	gbc_lblLastName.anchor = GridBagConstraints.NORTHWEST;
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
	
	JLabel lblAddress = new JLabel("Adresse: ");
	GridBagConstraints gbc_lblAddress = new GridBagConstraints();
	gbc_lblAddress.anchor = GridBagConstraints.NORTHWEST;
	gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
	gbc_lblAddress.gridx = 0;
	gbc_lblAddress.gridy = 2;
	panelPerson.add(lblAddress, gbc_lblAddress);
	
	txtFieldAddress = new JTextField();
	GridBagConstraints gbc_txtFieldAddress = new GridBagConstraints();
	gbc_txtFieldAddress.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldAddress.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldAddress.gridx = 1;
	gbc_txtFieldAddress.gridy = 2;
	panelPerson.add(txtFieldAddress, gbc_txtFieldAddress);
	txtFieldAddress.setColumns(10);
	
	JLabel lblQualifications = new JLabel("Qualifikation: ");
	GridBagConstraints gbc_lblQualifications = new GridBagConstraints();
	gbc_lblQualifications.anchor = GridBagConstraints.NORTHWEST;
	gbc_lblQualifications.insets = new Insets(0, 0, 0, 5);
	gbc_lblQualifications.gridx = 0;
	gbc_lblQualifications.gridy = 3;
	panelPerson.add(lblQualifications, gbc_lblQualifications);
	
	JTextArea txtAreaQualifications = new JTextArea();
	GridBagConstraints gbc_txtAreaQualifications = new GridBagConstraints();
	gbc_txtAreaQualifications.fill = GridBagConstraints.BOTH;
	gbc_txtAreaQualifications.gridx = 1;
	gbc_txtAreaQualifications.gridy = 3;
	panelPerson.add(txtAreaQualifications, gbc_txtAreaQualifications);
	
	JPanel panelCompetitionInfoBorder = new JPanel();
	panelCompetitionInfoBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Wettkampf Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelCompetitionInfoBorder = new GridBagConstraints();
	gbc_panelCompetitionInfoBorder.fill = GridBagConstraints.BOTH;
	gbc_panelCompetitionInfoBorder.insets = new Insets(0, 0, 5, 0);
	gbc_panelCompetitionInfoBorder.gridx = 1;
	gbc_panelCompetitionInfoBorder.gridy = 0;
	frame.getContentPane().add(panelCompetitionInfoBorder, gbc_panelCompetitionInfoBorder);
	GridBagLayout gbl_panelCompetitionInfoBorder = new GridBagLayout();
	gbl_panelCompetitionInfoBorder.columnWidths = new int[]{0, 0};
	gbl_panelCompetitionInfoBorder.rowHeights = new int[]{0, 0};
	gbl_panelCompetitionInfoBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_panelCompetitionInfoBorder.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panelCompetitionInfoBorder.setLayout(gbl_panelCompetitionInfoBorder);
	
	JPanel panelCompetitionInfo = new JPanel();
	GridBagConstraints gbc_panelCompetitionInfo = new GridBagConstraints();
	gbc_panelCompetitionInfo.fill = GridBagConstraints.BOTH;
	gbc_panelCompetitionInfo.gridx = 0;
	gbc_panelCompetitionInfo.gridy = 0;
	panelCompetitionInfoBorder.add(panelCompetitionInfo, gbc_panelCompetitionInfo);
	GridBagLayout gbl_panelCompetitionInfo = new GridBagLayout();
	gbl_panelCompetitionInfo.columnWidths = new int[]{0, 0, 0};
	gbl_panelCompetitionInfo.rowHeights = new int[]{0, 0, 0};
	gbl_panelCompetitionInfo.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	gbl_panelCompetitionInfo.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	panelCompetitionInfo.setLayout(gbl_panelCompetitionInfo);
	
	JLabel lblDeviceTypeAlloc = new JLabel("Gerätezuteilung: ");
	GridBagConstraints gbc_lblDeviceTypeAlloc = new GridBagConstraints();
	gbc_lblDeviceTypeAlloc.anchor = GridBagConstraints.WEST;
	gbc_lblDeviceTypeAlloc.insets = new Insets(0, 0, 5, 5);
	gbc_lblDeviceTypeAlloc.gridx = 0;
	gbc_lblDeviceTypeAlloc.gridy = 0;
	panelCompetitionInfo.add(lblDeviceTypeAlloc, gbc_lblDeviceTypeAlloc);
	
	txtFieldDeviceTypeAlloc = new JTextField();
	GridBagConstraints gbc_txtFieldDeviceTypeAlloc = new GridBagConstraints();
	gbc_txtFieldDeviceTypeAlloc.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldDeviceTypeAlloc.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldDeviceTypeAlloc.gridx = 1;
	gbc_txtFieldDeviceTypeAlloc.gridy = 0;
	panelCompetitionInfo.add(txtFieldDeviceTypeAlloc, gbc_txtFieldDeviceTypeAlloc);
	txtFieldDeviceTypeAlloc.setColumns(10);
	
	JLabel lblMarkType = new JLabel("Notentyp: ");
	GridBagConstraints gbc_lblMarkType = new GridBagConstraints();
	gbc_lblMarkType.anchor = GridBagConstraints.WEST;
	gbc_lblMarkType.insets = new Insets(0, 0, 0, 5);
	gbc_lblMarkType.gridx = 0;
	gbc_lblMarkType.gridy = 1;
	panelCompetitionInfo.add(lblMarkType, gbc_lblMarkType);
	
	txtFieldMarkType = new JTextField();
	GridBagConstraints gbc_txtFieldMarkType = new GridBagConstraints();
	gbc_txtFieldMarkType.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldMarkType.gridx = 1;
	gbc_txtFieldMarkType.gridy = 1;
	panelCompetitionInfo.add(txtFieldMarkType, gbc_txtFieldMarkType);
	txtFieldMarkType.setColumns(10);
	
	JPanel panel = new JPanel();
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.anchor = GridBagConstraints.SOUTH;
	gbc_panel.fill = GridBagConstraints.HORIZONTAL;
	gbc_panel.gridx = 1;
	gbc_panel.gridy = 1;
	frame.getContentPane().add(panel, gbc_panel);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
	gbl_panel.rowHeights = new int[]{0, 0};
	gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panel.setLayout(gbl_panel);
	
	JButton btnCancel = new JButton("Abbrechen");
	GridBagConstraints gbc_btnCancel = new GridBagConstraints();
	gbc_btnCancel.anchor = GridBagConstraints.EAST;
	gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
	gbc_btnCancel.gridx = 1;
	gbc_btnCancel.gridy = 0;
	panel.add(btnCancel, gbc_btnCancel);
	
	JButton btnSave = new JButton("Speichern");
	GridBagConstraints gbc_btnSave = new GridBagConstraints();
	gbc_btnSave.anchor = GridBagConstraints.EAST;
	gbc_btnSave.insets = new Insets(0, 0, 0, 5);
	gbc_btnSave.gridx = 2;
	gbc_btnSave.gridy = 0;
	panel.add(btnSave, gbc_btnSave);
    }

}
