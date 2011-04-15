package ch.hsr.gymtastic.presentation.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class CupManagementPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFieldPlace;
	private JTextField txtFieldEndDate;
	private JTextField txtFieldStartDate;
	private JTextField txtFieldName;
	private GridBagLayout gridBagLayout;
	private JPanel panelGeneralInfoBorder;
	private JPanel panelGeneralInfo;
	private JLabel lblName;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JLabel lblPlace;
	private JLabel lblDescription;
	private JTextArea txtAreaDescr;
	private JLabel lblSponsers;
	private JTextArea txtAreaSponsers;
	private JPanel panelImportBorder;
	private JPanel panelImport;
	private JLabel lblChoseCup;
	private JLabel lblStatusCup;
	private JButton btnOpenCup;
	private JLabel lblChoseImport;
	private JLabel lblStatusImport;
	private JButton btnImportStartList;
	private JPanel panelLogoBorder;
	private JPanel panelLogo;
	private JPanel panelLogoDescr;
	private JButton btnOpenPic;
	private JLabel lblChoseLogo;
	private JLabel lblLogo;
	private JPanel panelSaveCancel;
	private JButton btnCancel;
	private JButton btnSave;

	public CupManagementPanel() {

		initGUI();
		initListeners();

	}

	private void initListeners() {
		btnOpenPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

	}

	private void initGUI() {
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		panelGeneralInfoBorder = new JPanel();
		panelGeneralInfoBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Allgemeine Infos",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelGeneralInfoBorder = new GridBagConstraints();
		gbc_panelGeneralInfoBorder.fill = GridBagConstraints.BOTH;
		gbc_panelGeneralInfoBorder.gridheight = 2;
		gbc_panelGeneralInfoBorder.insets = new Insets(0, 0, 5, 5);
		gbc_panelGeneralInfoBorder.gridx = 0;
		gbc_panelGeneralInfoBorder.gridy = 0;
		add(panelGeneralInfoBorder, gbc_panelGeneralInfoBorder);
		GridBagLayout gbl_panelGeneralInfoBorder = new GridBagLayout();
		gbl_panelGeneralInfoBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelGeneralInfoBorder.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelGeneralInfoBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelGeneralInfoBorder.rowWeights = new double[] { 1.0, 1.0,
				Double.MIN_VALUE };
		panelGeneralInfoBorder.setLayout(gbl_panelGeneralInfoBorder);

		panelGeneralInfo = new JPanel();
		GridBagConstraints gbc_panelGeneralInfo = new GridBagConstraints();
		gbc_panelGeneralInfo.fill = GridBagConstraints.BOTH;
		gbc_panelGeneralInfo.gridheight = 2;
		gbc_panelGeneralInfo.insets = new Insets(0, 0, 5, 0);
		gbc_panelGeneralInfo.gridx = 0;
		gbc_panelGeneralInfo.gridy = 0;
		panelGeneralInfoBorder.add(panelGeneralInfo, gbc_panelGeneralInfo);
		GridBagLayout gbl_panelGeneralInfo = new GridBagLayout();
		gbl_panelGeneralInfo.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelGeneralInfo.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelGeneralInfo.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelGeneralInfo.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				1.0, 1.0, Double.MIN_VALUE };
		panelGeneralInfo.setLayout(gbl_panelGeneralInfo);

		lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		panelGeneralInfo.add(lblName, gbc_lblName);

		txtFieldName = new JTextField();
		GridBagConstraints gbc_txtFieldName = new GridBagConstraints();
		gbc_txtFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldName.gridx = 1;
		gbc_txtFieldName.gridy = 0;
		panelGeneralInfo.add(txtFieldName, gbc_txtFieldName);
		txtFieldName.setColumns(10);

		lblStartDate = new JLabel("Startdatum:");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.anchor = GridBagConstraints.WEST;
		gbc_lblStartDate.gridx = 0;
		gbc_lblStartDate.gridy = 1;
		panelGeneralInfo.add(lblStartDate, gbc_lblStartDate);

		txtFieldStartDate = new JTextField();
		GridBagConstraints gbc_txtFieldStartDate = new GridBagConstraints();
		gbc_txtFieldStartDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldStartDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldStartDate.gridx = 1;
		gbc_txtFieldStartDate.gridy = 1;
		panelGeneralInfo.add(txtFieldStartDate, gbc_txtFieldStartDate);
		txtFieldStartDate.setColumns(10);

		lblEndDate = new JLabel("Enddatum:");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.anchor = GridBagConstraints.WEST;
		gbc_lblEndDate.gridx = 0;
		gbc_lblEndDate.gridy = 2;
		panelGeneralInfo.add(lblEndDate, gbc_lblEndDate);

		txtFieldEndDate = new JTextField();
		GridBagConstraints gbc_txtFieldEndDate = new GridBagConstraints();
		gbc_txtFieldEndDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldEndDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldEndDate.gridx = 1;
		gbc_txtFieldEndDate.gridy = 2;
		panelGeneralInfo.add(txtFieldEndDate, gbc_txtFieldEndDate);
		txtFieldEndDate.setColumns(10);

		lblPlace = new JLabel("Ort:");
		GridBagConstraints gbc_lblPlace = new GridBagConstraints();
		gbc_lblPlace.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlace.anchor = GridBagConstraints.WEST;
		gbc_lblPlace.gridx = 0;
		gbc_lblPlace.gridy = 3;
		panelGeneralInfo.add(lblPlace, gbc_lblPlace);

		txtFieldPlace = new JTextField();
		GridBagConstraints gbc_txtFieldPlace = new GridBagConstraints();
		gbc_txtFieldPlace.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldPlace.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldPlace.gridx = 1;
		gbc_txtFieldPlace.gridy = 3;
		panelGeneralInfo.add(txtFieldPlace, gbc_txtFieldPlace);
		txtFieldPlace.setColumns(10);

		lblDescription = new JLabel("Beschreibung:");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 4;
		panelGeneralInfo.add(lblDescription, gbc_lblDescription);

		txtAreaDescr = new JTextArea();
		GridBagConstraints gbc_txtAreaDescr = new GridBagConstraints();
		gbc_txtAreaDescr.insets = new Insets(0, 0, 5, 0);
		gbc_txtAreaDescr.fill = GridBagConstraints.BOTH;
		gbc_txtAreaDescr.gridx = 1;
		gbc_txtAreaDescr.gridy = 4;
		panelGeneralInfo.add(txtAreaDescr, gbc_txtAreaDescr);

		lblSponsers = new JLabel("Sponsoren:");
		GridBagConstraints gbc_lblSponsers = new GridBagConstraints();
		gbc_lblSponsers.insets = new Insets(0, 0, 0, 5);
		gbc_lblSponsers.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblSponsers.gridx = 0;
		gbc_lblSponsers.gridy = 5;
		panelGeneralInfo.add(lblSponsers, gbc_lblSponsers);

		txtAreaSponsers = new JTextArea();
		GridBagConstraints gbc_txtAreaSponsers = new GridBagConstraints();
		gbc_txtAreaSponsers.fill = GridBagConstraints.BOTH;
		gbc_txtAreaSponsers.gridx = 1;
		gbc_txtAreaSponsers.gridy = 5;
		panelGeneralInfo.add(txtAreaSponsers, gbc_txtAreaSponsers);

		panelImportBorder = new JPanel();
		panelImportBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Startlisten Import",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelImportBorder = new GridBagConstraints();
		gbc_panelImportBorder.fill = GridBagConstraints.BOTH;
		gbc_panelImportBorder.insets = new Insets(0, 0, 5, 0);
		gbc_panelImportBorder.gridx = 1;
		gbc_panelImportBorder.gridy = 0;
		add(panelImportBorder, gbc_panelImportBorder);
		GridBagLayout gbl_panelImportBorder = new GridBagLayout();
		gbl_panelImportBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelImportBorder.rowHeights = new int[] { 0, 0 };
		gbl_panelImportBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelImportBorder.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelImportBorder.setLayout(gbl_panelImportBorder);

		panelImport = new JPanel();
		GridBagConstraints gbc_panelImport = new GridBagConstraints();
		gbc_panelImport.fill = GridBagConstraints.BOTH;
		gbc_panelImport.gridx = 0;
		gbc_panelImport.gridy = 0;
		panelImportBorder.add(panelImport, gbc_panelImport);
		GridBagLayout gbl_panelImport = new GridBagLayout();
		gbl_panelImport.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelImport.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelImport.columnWeights = new double[] { 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelImport.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelImport.setLayout(gbl_panelImport);

		lblChoseCup = new JLabel("Datei auswählen");
		GridBagConstraints gbc_lblChoseCup = new GridBagConstraints();
		gbc_lblChoseCup.insets = new Insets(0, 0, 5, 5);
		gbc_lblChoseCup.gridx = 0;
		gbc_lblChoseCup.gridy = 0;
		panelImport.add(lblChoseCup, gbc_lblChoseCup);

		lblStatusCup = new JLabel("status");
		GridBagConstraints gbc_lblStatusCup = new GridBagConstraints();
		gbc_lblStatusCup.anchor = GridBagConstraints.EAST;
		gbc_lblStatusCup.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatusCup.gridx = 1;
		gbc_lblStatusCup.gridy = 0;
		panelImport.add(lblStatusCup, gbc_lblStatusCup);

		btnOpenCup = new JButton("Cup Öffnen...");
		GridBagConstraints gbc_btnOpenCup = new GridBagConstraints();
		gbc_btnOpenCup.anchor = GridBagConstraints.EAST;
		gbc_btnOpenCup.insets = new Insets(0, 0, 5, 0);
		gbc_btnOpenCup.gridx = 2;
		gbc_btnOpenCup.gridy = 0;
		panelImport.add(btnOpenCup, gbc_btnOpenCup);

		lblChoseImport = new JLabel("Datei auswählen");
		GridBagConstraints gbc_lblChoseImport = new GridBagConstraints();
		gbc_lblChoseImport.insets = new Insets(0, 0, 0, 5);
		gbc_lblChoseImport.gridx = 0;
		gbc_lblChoseImport.gridy = 1;
		panelImport.add(lblChoseImport, gbc_lblChoseImport);

		lblStatusImport = new JLabel("status");
		GridBagConstraints gbc_lblStatusImport = new GridBagConstraints();
		gbc_lblStatusImport.anchor = GridBagConstraints.EAST;
		gbc_lblStatusImport.insets = new Insets(0, 0, 0, 5);
		gbc_lblStatusImport.gridx = 1;
		gbc_lblStatusImport.gridy = 1;
		panelImport.add(lblStatusImport, gbc_lblStatusImport);

		btnImportStartList = new JButton("Importieren...");
		GridBagConstraints gbc_btnImportStartList = new GridBagConstraints();
		gbc_btnImportStartList.anchor = GridBagConstraints.EAST;
		gbc_btnImportStartList.gridx = 2;
		gbc_btnImportStartList.gridy = 1;
		panelImport.add(btnImportStartList, gbc_btnImportStartList);

		panelLogoBorder = new JPanel();
		panelLogoBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Logo",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelLogoBorder = new GridBagConstraints();
		gbc_panelLogoBorder.insets = new Insets(0, 0, 5, 0);
		gbc_panelLogoBorder.fill = GridBagConstraints.BOTH;
		gbc_panelLogoBorder.gridx = 1;
		gbc_panelLogoBorder.gridy = 1;
		add(panelLogoBorder, gbc_panelLogoBorder);
		GridBagLayout gbl_panelLogoBorder = new GridBagLayout();
		gbl_panelLogoBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelLogoBorder.rowHeights = new int[] { 0, 0 };
		gbl_panelLogoBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelLogoBorder.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelLogoBorder.setLayout(gbl_panelLogoBorder);

		panelLogo = new JPanel();
		GridBagConstraints gbc_panelLogo = new GridBagConstraints();
		gbc_panelLogo.fill = GridBagConstraints.BOTH;
		gbc_panelLogo.gridx = 0;
		gbc_panelLogo.gridy = 0;
		panelLogoBorder.add(panelLogo, gbc_panelLogo);
		panelLogo.setLayout(new BorderLayout(0, 0));

		panelLogoDescr = new JPanel();
		panelLogo.add(panelLogoDescr, BorderLayout.NORTH);
		panelLogoDescr.setLayout(new BorderLayout(0, 0));

		btnOpenPic = new JButton("Bild Öffnen...");

		panelLogoDescr.add(btnOpenPic, BorderLayout.EAST);

		lblChoseLogo = new JLabel("Logo auswählen");
		panelLogoDescr.add(lblChoseLogo, BorderLayout.WEST);

		lblLogo = new JLabel("Logo");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		panelLogo.add(lblLogo, BorderLayout.CENTER);

		panelSaveCancel = new JPanel();
		GridBagConstraints gbc_panelSaveCancel = new GridBagConstraints();
		gbc_panelSaveCancel.insets = new Insets(0, 0, 0, 5);
		gbc_panelSaveCancel.fill = GridBagConstraints.BOTH;
		gbc_panelSaveCancel.gridx = 0;
		gbc_panelSaveCancel.gridy = 2;
		add(panelSaveCancel, gbc_panelSaveCancel);
		GridBagLayout gbl_panelSaveCancel = new GridBagLayout();
		gbl_panelSaveCancel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelSaveCancel.rowHeights = new int[] { 0, 0 };
		gbl_panelSaveCancel.columnWeights = new double[] { 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelSaveCancel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelSaveCancel.setLayout(gbl_panelSaveCancel);

		btnCancel = new JButton("Abbrechen");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 0;
		panelSaveCancel.add(btnCancel, gbc_btnCancel);

		btnSave = new JButton("Speichern");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		panelSaveCancel.add(btnSave, gbc_btnSave);

	}

}
