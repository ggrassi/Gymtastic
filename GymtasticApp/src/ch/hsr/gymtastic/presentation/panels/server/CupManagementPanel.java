package ch.hsr.gymtastic.presentation.panels.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

import ch.hsr.gymtastic.application.controller.server.SquadCreator;
import ch.hsr.gymtastic.application.models.CupManagementModel;
import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.presentation.imports.FileExtensionFilter;
import ch.hsr.gymtastic.technicalServices.database.DBConnection;
import ch.hsr.gymtastic.technicalServices.utils.DateFormatConverter;
import ch.hsr.gymtastic.technicalServices.utils.ImportStartList;

public class CupManagementPanel extends JPanel implements Observer {
    /**
	 * 
	 */

    private static final long serialVersionUID = 1L;
    private JTextField txtFieldLocation;
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
    private JTextArea txtAreaSponsors;
    private JPanel panelImportBorder;
    private JPanel panelImport;
    private JTextField txtChoseCup;
    private JLabel lblStatusCup;
    private JButton btnOpenCup;
    private JTextField txtChoseImport;
    private JLabel txtStatusImport;
    private JButton btnImportStartList;
    private JPanel panelLogoBorder;
    private ImagePanel panelLogo;
    private JPanel panelLogoDescr;
    private JButton btnOpenPic;
    private JLabel lblChoseLogo;
    private JLabel lblLogo;
    private JPanel panelSaveCancel;
    private JButton btnCancel;
    private JButton btnSave;
    protected JFileChooser chooser;
    private CupManagementModel cupManagementModel;

    public CupManagementPanel(CupManagementModel cupManagementModel) {
	this.cupManagementModel = cupManagementModel;
	this.cupManagementModel.addObserver(this);
	initGUI();
	initListeners();
    }

    private void initGUI() {
	gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
	gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
	setLayout(gridBagLayout);

	panelGeneralInfoBorder = new JPanel();
	panelGeneralInfoBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"Allgemeine Infos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
	gbl_panelGeneralInfoBorder.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panelGeneralInfoBorder.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
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
	gbl_panelGeneralInfo.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	gbl_panelGeneralInfo.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
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
	txtFieldStartDate.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
		changesCupInformation();
	    }
	});
	txtFieldStartDate.addFocusListener(new FocusAdapter() {
	    public void focusLost(FocusEvent e) {
		GregorianCalendar date = null;
		try {
		    date = DateFormatConverter.convertStringToDate(txtFieldStartDate.getText());
		} catch (ParseException e1) {
		    if (date != new GregorianCalendar()) {
			txtFieldStartDate.setToolTipText("Bitte Format richtig eingeben: '01.02.2011'");
			txtFieldStartDate.setText("");
		    }
		}
	    }
	});
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

	txtFieldEndDate = new JFormattedTextField(new DateFormatter(DateFormat.getDateInstance(DateFormat.SHORT,
		Locale.GERMAN)));
	txtFieldEndDate.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
		changesCupInformation();
	    }
	});
	txtFieldEndDate.addFocusListener(new FocusAdapter() {
	    public void focusLost(FocusEvent e) {
		GregorianCalendar date = null;
		try {
		    date = DateFormatConverter.convertStringToDate(txtFieldStartDate.getText());
		} catch (ParseException e1) {
		    if (date != new GregorianCalendar()) {
			txtFieldStartDate.setToolTipText("Bitte Format richtig eingeben: '01.02.2011'");
			txtFieldStartDate.setText("");
		    }
		}
	    }
	});
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

	txtFieldLocation = new JTextField();
	txtFieldLocation.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
		changesCupInformation();
	    }
	});
	GridBagConstraints gbc_txtFieldLocation = new GridBagConstraints();
	gbc_txtFieldLocation.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldLocation.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldLocation.gridx = 1;
	gbc_txtFieldLocation.gridy = 3;
	panelGeneralInfo.add(txtFieldLocation, gbc_txtFieldLocation);
	txtFieldLocation.setColumns(10);

	lblDescription = new JLabel("Beschreibung:");
	GridBagConstraints gbc_lblDescription = new GridBagConstraints();
	gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
	gbc_lblDescription.anchor = GridBagConstraints.NORTHWEST;
	gbc_lblDescription.gridx = 0;
	gbc_lblDescription.gridy = 4;
	panelGeneralInfo.add(lblDescription, gbc_lblDescription);

	txtAreaDescr = new JTextArea();
	txtAreaDescr.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
		changesCupInformation();
	    }
	});
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

	txtAreaSponsors = new JTextArea();
	txtAreaSponsors.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
		changesCupInformation();
	    }
	});
	GridBagConstraints gbc_txtAreaSponsers = new GridBagConstraints();
	gbc_txtAreaSponsers.fill = GridBagConstraints.BOTH;
	gbc_txtAreaSponsers.gridx = 1;
	gbc_txtAreaSponsers.gridy = 5;
	panelGeneralInfo.add(txtAreaSponsors, gbc_txtAreaSponsers);

	panelImportBorder = new JPanel();
	panelImportBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Startlisten Import",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelImportBorder = new GridBagConstraints();
	gbc_panelImportBorder.fill = GridBagConstraints.BOTH;
	gbc_panelImportBorder.insets = new Insets(0, 0, 5, 0);
	gbc_panelImportBorder.gridx = 1;
	gbc_panelImportBorder.gridy = 0;
	add(panelImportBorder, gbc_panelImportBorder);
	GridBagLayout gbl_panelImportBorder = new GridBagLayout();
	gbl_panelImportBorder.columnWidths = new int[] { 0, 0 };
	gbl_panelImportBorder.rowHeights = new int[] { 0, 0 };
	gbl_panelImportBorder.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
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
	gbl_panelImport.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
	gbl_panelImport.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
	panelImport.setLayout(gbl_panelImport);

	txtChoseCup = new JTextField("Dateipfad auswählen");
	GridBagConstraints gbc_lblChoseCup = new GridBagConstraints();
	gbc_lblChoseCup.insets = new Insets(0, 0, 5, 5);
	gbc_lblChoseCup.gridx = 0;
	gbc_lblChoseCup.gridy = 0;
	panelImport.add(txtChoseCup, gbc_lblChoseCup);

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

	txtChoseImport = new JTextField("Dateipfad auswählen");
	txtChoseImport.setMaximumSize(new Dimension(25, 1));
	GridBagConstraints gbc_lblChoseImport = new GridBagConstraints();
	gbc_lblChoseImport.insets = new Insets(0, 0, 0, 5);
	gbc_lblChoseImport.gridx = 0;
	gbc_lblChoseImport.gridy = 1;
	panelImport.add(txtChoseImport, gbc_lblChoseImport);

	txtStatusImport = new JLabel("status");
	txtStatusImport.setSize(panelImport.getWidth(), panelImport.getHeight());
	GridBagConstraints gbc_lblStatusImport = new GridBagConstraints();
	gbc_lblStatusImport.anchor = GridBagConstraints.EAST;
	gbc_lblStatusImport.insets = new Insets(0, 0, 0, 5);
	gbc_lblStatusImport.gridx = 1;
	gbc_lblStatusImport.gridy = 1;
	panelImport.add(txtStatusImport, gbc_lblStatusImport);

	btnImportStartList = new JButton("Importieren...");
	GridBagConstraints gbc_btnImportStartList = new GridBagConstraints();
	gbc_btnImportStartList.anchor = GridBagConstraints.EAST;
	gbc_btnImportStartList.gridx = 2;
	gbc_btnImportStartList.gridy = 1;
	panelImport.add(btnImportStartList, gbc_btnImportStartList);

	panelLogoBorder = new JPanel();
	panelLogoBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Logo",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelLogoBorder = new GridBagConstraints();
	gbc_panelLogoBorder.insets = new Insets(0, 0, 5, 0);
	gbc_panelLogoBorder.fill = GridBagConstraints.BOTH;
	gbc_panelLogoBorder.gridx = 1;
	gbc_panelLogoBorder.gridy = 1;
	add(panelLogoBorder, gbc_panelLogoBorder);
	GridBagLayout gbl_panelLogoBorder = new GridBagLayout();
	gbl_panelLogoBorder.columnWidths = new int[] { 0, 0 };
	gbl_panelLogoBorder.rowHeights = new int[] { 0, 0 };
	gbl_panelLogoBorder.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panelLogoBorder.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	panelLogoBorder.setLayout(gbl_panelLogoBorder);

	panelLogo = new ImagePanel();
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
	gbl_panelSaveCancel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
	gbl_panelSaveCancel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panelSaveCancel.setLayout(gbl_panelSaveCancel);

	btnCancel = new JButton("Abbrechen");
	btnCancel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		updateAfterCancel();
	    }

	});
	GridBagConstraints gbc_btnCancel = new GridBagConstraints();
	gbc_btnCancel.anchor = GridBagConstraints.EAST;
	gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
	gbc_btnCancel.gridx = 0;
	gbc_btnCancel.gridy = 0;
	panelSaveCancel.add(btnCancel, gbc_btnCancel);
	btnCancel.setEnabled(false);

	btnSave = new JButton("Speichern");
	GridBagConstraints gbc_btnSave = new GridBagConstraints();
	gbc_btnSave.anchor = GridBagConstraints.EAST;
	gbc_btnSave.gridx = 1;
	gbc_btnSave.gridy = 0;
	panelSaveCancel.add(btnSave, gbc_btnSave);

    }

    private void initListeners() {
	btnOpenPic.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    }
	});

	txtFieldName.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
		changesCupInformation();
	    }
	});

	btnOpenCup.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		chooser = new JFileChooser();
		FileExtensionFilter filter = new FileExtensionFilter();
		filter.addExtension("odb");
		chooser.addChoosableFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(true);
		int returnVal = chooser.showOpenDialog(panelImport);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    String path = chooser.getSelectedFile().getAbsolutePath();
		    DBConnection.setPath(path);
		    cupManagementModel.setExistingGymcup();
		   
		    System.out.println(path);
		    txtChoseCup.setText(path);
		    txtChoseCup.setEnabled(false);
		    btnOpenCup.setEnabled(false);
		}
	    }
	});

	btnImportStartList.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		chooser = new JFileChooser();
		FileExtensionFilter filter = new FileExtensionFilter();
		filter.addExtension("txt");
		chooser.addChoosableFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(true);
		int returnVal = chooser.showOpenDialog(panelImport);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    String path = chooser.getSelectedFile().getAbsolutePath();
		    System.out.println(path);
		    txtChoseImport.setText(path);
		}

	    }
	});

	btnOpenPic.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		chooser = new JFileChooser();
		FileExtensionFilter filter = new FileExtensionFilter();
		filter.addExtension("jpeg");
		chooser.addChoosableFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(true);
		int returnVal = chooser.showOpenDialog(panelImport);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    String path = chooser.getSelectedFile().getAbsolutePath();
		    System.out.println(path);
		    panelLogo.generateImage(path);

		}
	    }
	});

	btnSave.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
	    	
		GymCup gymCup = new GymCup(txtFieldName.getText(), txtFieldLocation.getText());
		gymCup.setName(txtFieldName.getText());
		gymCup.setLocation(txtFieldLocation.getText());
		gymCup.setSponsors(txtAreaSponsors.getText());
		gymCup.setDescription(txtAreaDescr.getText());
		if(panelLogo.isGenerated()){
			gymCup.setLogoImagePath(panelLogo.getPath());
		}
		gymCup.importGymCupToDB();
		gymCup.setStartDateStr(txtFieldStartDate.getText());
		gymCup.setEndDateStr(txtFieldEndDate.getText());
		cupManagementModel.setGymcup(gymCup);
		
		ImportStartList startList = new ImportStartList(txtChoseImport.getText());
		startList.readImport();
		startList.toString();
		SquadCreator squadCreator = new SquadCreator(startList, cupManagementModel.getGymCup());
		squadCreator.insertImportToDB();
		cupManagementModel.getGymCup().importAllSquads();
		cupManagementModel.getGymCup().setSquads(squadCreator.createSquads());
	    }
	});

    }

    private void changesCupInformation() {
	if (cupManagementModel.getGymCup() != null) {
	    if (nothingChanged()) {
		btnCancel.setEnabled(false);
		btnSave.setEnabled(false);
	    } else {
		btnCancel.setEnabled(true);
		btnSave.setEnabled(true);
	    }
	}
    }

    private boolean nothingChanged() {
	return txtFieldName.getText().equals(cupManagementModel.getGymCup().getName())
		&& txtAreaDescr.getText().equals(cupManagementModel.getGymCup().getDescription())
		&& txtAreaSponsors.getText().equals(cupManagementModel.getGymCup().getSponsors())
		&& (txtFieldStartDate.getText().equals(DateFormatConverter.convertDateToString(cupManagementModel
			.getGymCup().getStartDate())))
		&& txtFieldEndDate.getText().equals(
			DateFormatConverter.convertDateToString(cupManagementModel.getGymCup().getEndDate()))
		&& txtFieldLocation.getText().equals(cupManagementModel.getGymCup().getLocation());
    }

    private void updateAfterCancel() {
	txtFieldName.setText(cupManagementModel.getGymCup().getName());
	txtAreaDescr.setText(cupManagementModel.getGymCup().getDescription());
	txtAreaSponsors.setText(cupManagementModel.getGymCup().getSponsors());
	txtFieldEndDate.setText(DateFormatConverter.convertDateToString(cupManagementModel.getGymCup().getEndDate()));
	txtFieldStartDate.setText(DateFormatConverter
		.convertDateToString(cupManagementModel.getGymCup().getStartDate()));
	txtFieldLocation.setText(cupManagementModel.getGymCup().getLocation());
	btnSave.setEnabled(false);
	btnCancel.setEnabled(false);

    }

    @Override
    public void update(Observable arg0, Object arg1) {
	System.out.println("cupmngmtpanel update");
    }

}