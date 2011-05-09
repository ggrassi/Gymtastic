package ch.hsr.gymtastic.server.presentation.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

import javax.swing.Box;
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

import ch.hsr.gymtastic.domain.GymCup;
import ch.hsr.gymtastic.server.application.controller.DBController;
import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.application.controller.SquadCreator;
import ch.hsr.gymtastic.server.presentation.frames.panels.ImagePanel;
import ch.hsr.gymtastic.technicalServices.utils.DateFormatConverter;
import ch.hsr.gymtastic.technicalServices.utils.FileExtensionFilter;
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
	private JButton btnOpenCup;
	private JLabel lblChoseImport;
	private JButton btnImportStartList;
	private JPanel panelLogoBorder;
	private ImagePanel panelLogo = new ImagePanel();
	private JPanel panelLogoDescr;
	private JButton btnOpenPic;
	private JLabel lblChoseLogo;
	private JLabel lblLogo;
	private JPanel panelSaveCancel;
	private JButton btnCancel;
	private JButton btnSave;
	protected JFileChooser chooser;
	protected boolean isNewCup = true;
	protected boolean isNewImportList = false;
	private JPanel panelNorth;
	private String pathCup;
	private String pathImport;
	private Component verticalStrutMarginNoth;
	private Component verticalStrutMarginSouth;
	private GymCupController gymCupController;
	private boolean isNewImage = false;

	public CupManagementPanel(GymCupController gymCupController) {
		this.gymCupController = gymCupController;
		gymCupController.addObserver(this);
		/*
		 * TODO: Observers
		 */
		initGUI();
		initListeners();
	}

	private void initGUI() {
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		panelNorth = new JPanel();
		GridBagConstraints gbc_panelNorth = new GridBagConstraints();
		gbc_panelNorth.gridwidth = 2;
		gbc_panelNorth.insets = new Insets(0, 0, 5, 5);
		gbc_panelNorth.fill = GridBagConstraints.BOTH;
		gbc_panelNorth.gridx = 0;
		gbc_panelNorth.gridy = 0;
		add(panelNorth, gbc_panelNorth);
		panelNorth.setLayout(new BorderLayout(0, 0));

		btnOpenCup = new JButton("Cup \u00f6ffnen...");
		panelNorth.add(btnOpenCup, BorderLayout.WEST);

		verticalStrutMarginNoth = Box.createVerticalStrut(10);
		panelNorth.add(verticalStrutMarginNoth, BorderLayout.NORTH);

		verticalStrutMarginSouth = Box.createVerticalStrut(10);
		panelNorth.add(verticalStrutMarginSouth, BorderLayout.SOUTH);

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
		gbc_panelGeneralInfoBorder.gridy = 1;
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
					date = DateFormatConverter
							.convertStringToDate(txtFieldStartDate.getText());
				} catch (ParseException e1) {
					if (date != new GregorianCalendar()) {
						txtFieldStartDate
								.setToolTipText("Bitte Format richtig eingeben: '01.02.2011'");
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

		txtFieldEndDate = new JFormattedTextField(new DateFormatter(
				DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN)));
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
					date = DateFormatConverter
							.convertStringToDate(txtFieldStartDate.getText());
				} catch (ParseException e1) {
					if (date != new GregorianCalendar()) {
						txtFieldStartDate
								.setToolTipText("Bitte Format richtig eingeben: '01.02.2011'");
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
		panelImportBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Startlisten Import",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelImportBorder = new GridBagConstraints();
		gbc_panelImportBorder.fill = GridBagConstraints.BOTH;
		gbc_panelImportBorder.insets = new Insets(0, 0, 5, 0);
		gbc_panelImportBorder.gridx = 1;
		gbc_panelImportBorder.gridy = 1;
		add(panelImportBorder, gbc_panelImportBorder);
		GridBagLayout gbl_panelImportBorder = new GridBagLayout();
		gbl_panelImportBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelImportBorder.rowHeights = new int[] { 26, 0 };
		gbl_panelImportBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelImportBorder.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelImportBorder.setLayout(gbl_panelImportBorder);

		panelImport = new JPanel();
		GridBagConstraints gbc_panelImport = new GridBagConstraints();
		gbc_panelImport.anchor = GridBagConstraints.NORTH;
		gbc_panelImport.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelImport.gridx = 0;
		gbc_panelImport.gridy = 0;
		panelImportBorder.add(panelImport, gbc_panelImport);
		GridBagLayout gbl_panelImport = new GridBagLayout();
		gbl_panelImport.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelImport.rowHeights = new int[] { 0, 0 };
		gbl_panelImport.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelImport.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelImport.setLayout(gbl_panelImport);

		lblChoseImport = new JLabel("Importliste ausw\u00e4hlen:");
		lblChoseImport.setSize(panelImport.getWidth(), panelImport.getHeight());
		GridBagConstraints gbc_lblChoseImport = new GridBagConstraints();
		gbc_lblChoseImport.anchor = GridBagConstraints.WEST;
		gbc_lblChoseImport.insets = new Insets(0, 0, 0, 5);
		gbc_lblChoseImport.gridx = 0;
		gbc_lblChoseImport.gridy = 0;
		panelImport.add(lblChoseImport, gbc_lblChoseImport);

		btnImportStartList = new JButton("Importieren...");
		GridBagConstraints gbc_btnImportStartList = new GridBagConstraints();
		gbc_btnImportStartList.anchor = GridBagConstraints.EAST;
		gbc_btnImportStartList.gridx = 1;
		gbc_btnImportStartList.gridy = 0;
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
		gbc_panelLogoBorder.gridy = 2;
		add(panelLogoBorder, gbc_panelLogoBorder);
		GridBagLayout gbl_panelLogoBorder = new GridBagLayout();
		gbl_panelLogoBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelLogoBorder.rowHeights = new int[] { 0, 0 };
		gbl_panelLogoBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
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

		btnOpenPic = new JButton("Bild \u00f6ffnen...");

		panelLogoDescr.add(btnOpenPic, BorderLayout.EAST);

		lblChoseLogo = new JLabel("Logo ausw\u00e4hlen:");
		panelLogoDescr.add(lblChoseLogo, BorderLayout.WEST);

		lblLogo = new JLabel("Logo");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		panelLogo.add(lblLogo, BorderLayout.CENTER);

		panelSaveCancel = new JPanel();
		GridBagConstraints gbc_panelSaveCancel = new GridBagConstraints();
		gbc_panelSaveCancel.gridwidth = 2;
		gbc_panelSaveCancel.insets = new Insets(0, 0, 0, 5);
		gbc_panelSaveCancel.fill = GridBagConstraints.BOTH;
		gbc_panelSaveCancel.gridx = 0;
		gbc_panelSaveCancel.gridy = 3;
		add(panelSaveCancel, gbc_panelSaveCancel);
		GridBagLayout gbl_panelSaveCancel = new GridBagLayout();
		gbl_panelSaveCancel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelSaveCancel.rowHeights = new int[] { 0, 0 };
		gbl_panelSaveCancel.columnWeights = new double[] { 1.0, 0.0,
				Double.MIN_VALUE };
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

		btnSave = new JButton("Speichern");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		panelSaveCancel.add(btnSave, gbc_btnSave);

	}

	private void initListeners() {
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
					pathCup = path;
					isNewCup = false;
					// gymCupController.setExistingGymcup(pathImport);
					System.out.println(path);
					DBController.setPath(pathCup);
					gymCupController.setExistingGymcup();
					btnImportStartList.setEnabled(false);
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
					pathImport = path;
					isNewImportList = true;
					isNewCup = true;
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
					isNewImage = true;

				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isNewCup) {
					GymCup gymCup = new GymCup(txtFieldName.getText(),
							txtFieldLocation.getText());
					gymCup.setName(txtFieldName.getText());
					gymCup.setLocation(txtFieldLocation.getText());
					gymCup.setSponsors(txtAreaSponsors.getText());
					gymCup.setDescription(txtAreaDescr.getText());
					try {
						gymCup.setStartDate(DateFormatConverter
								.convertStringToDate(txtFieldStartDate
										.getText()));
						gymCup.setEndDate(DateFormatConverter
								.convertStringToDate(txtFieldEndDate.getText()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if (panelLogo.isGenerated()) {
						gymCup.setLogoImagePath(panelLogo.getPath());
						lblLogo.setText("");
					}

					if (isNewImportList) {
						DBController.importGymCupToDB(gymCup);
						try {
							gymCup.setStartDate(DateFormatConverter
									.convertStringToDate(txtFieldStartDate
											.getText()));
							gymCup.setEndDate(DateFormatConverter
									.convertStringToDate(txtFieldEndDate
											.getText()));
						} catch (ParseException e) {
						}
						gymCupController.setGymCup(gymCup);
						ImportStartList startList = new ImportStartList(
								pathImport);
						startList.readImport();
						SquadCreator squadCreator = new SquadCreator(startList);
						squadCreator.insertImportToDB();
						DBController.importAllSquads(gymCupController
								.getGymCup());
						gymCupController.getGymCup().setSquads(
								squadCreator.createSquads());
						isNewCup = false;
					}

				} else {
					// TODO
					// änderungen am cup abspeichern

				}
			}
		});

	}

	private void setEnabledAllComponents(Boolean bool) {
		txtFieldName.setEnabled(bool);
		txtFieldLocation.setEnabled(bool);
		txtAreaDescr.setEnabled(bool);
		txtAreaSponsors.setEnabled(bool);
		txtFieldEndDate.setEnabled(bool);
		txtFieldStartDate.setEnabled(bool);
		btnOpenCup.setEnabled(bool);
		btnOpenPic.setEnabled(bool);
		btnImportStartList.setEnabled(bool);
	}

	private void changesCupInformation() {
		if (gymCupController.getGymCup() != null) {
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
		return txtFieldName.getText().equals(
				gymCupController.getGymCup().getName())
				&& txtAreaDescr.getText().equals(
						gymCupController.getGymCup().getDescription())
				&& txtAreaSponsors.getText().equals(
						gymCupController.getGymCup().getSponsors())
				&& (txtFieldStartDate.getText().equals(DateFormatConverter
						.convertDateToString(gymCupController.getGymCup()
								.getStartDate())))
				&& txtFieldEndDate.getText().equals(
						DateFormatConverter
								.convertDateToString(gymCupController
										.getGymCup().getEndDate()))
				&& txtFieldLocation.getText().equals(
						gymCupController.getGymCup().getLocation());
	}

	private void updateAfterCancel() {
		txtFieldName.setText(gymCupController.getGymCup().getName());
		txtAreaDescr.setText(gymCupController.getGymCup().getDescription());
		txtAreaSponsors.setText(gymCupController.getGymCup().getSponsors());
		txtFieldEndDate
				.setText(DateFormatConverter
						.convertDateToString(gymCupController.getGymCup()
								.getEndDate()));
		txtFieldStartDate.setText(DateFormatConverter
				.convertDateToString(gymCupController.getGymCup()
						.getStartDate()));
		txtFieldLocation.setText(gymCupController.getGymCup().getLocation());
		btnSave.setEnabled(false);
		btnCancel.setEnabled(false);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateContent();
		System.out.println("update from cupmgmt panel");
	}

	private void updateContent() {

		txtFieldName.setText(gymCupController.getGymCup().getName());
		txtFieldLocation.setText(gymCupController.getGymCup().getLocation());
		txtAreaDescr.setText(gymCupController.getGymCup().getDescription());
		txtAreaSponsors.setText(gymCupController.getGymCup().getSponsors());
		txtFieldStartDate.setText(DateFormatConverter
				.convertDateToString(gymCupController.getGymCup()
						.getStartDate()));
		txtFieldEndDate
				.setText(DateFormatConverter
						.convertDateToString(gymCupController.getGymCup()
								.getEndDate()));

		if (!isNewCup && !isNewImage) {
			panelLogo.setPath(gymCupController.getGymCup().getLogoImagePath());
		}
		lblLogo.setText("");

	}

}
