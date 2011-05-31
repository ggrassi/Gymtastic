package ch.hsr.gymtastic.server.presentation.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DateFormatter;

import ch.hsr.gymtastic.domain.Competition;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.server.application.controller.DBController;
import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.presentation.frames.SquadsSelectionFrame;
import ch.hsr.gymtastic.server.presentation.models.CompetitionOverviewTableModel;
import ch.hsr.gymtastic.server.presentation.models.SquadsInCompetitionTableModel;
import ch.hsr.gymtastic.technicalServices.utils.DateFormatConverter;

/**
 * The Class CompetitionPanel contains an input mask to manage the CRUD on the
 * Competitions
 * 
 */

public class CompetitionPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtFieldProgramClass;
    private JTextField txtFieldEndTime;
    private JTextField txtFieldStartTime;
    private JTextField txtFieldDate;
    private JTextField txtFieldDescription;
    private JTable tableCompetitionsOverview;
    private JTable tableSquadsInCompetition;
    private CompetitionOverviewTableModel competitionOverviewTableModel;
    private SquadsInCompetitionTableModel squadsInCompetitionTableModel;
    private GymCupController gymCupController;
    private Competition actualCompetition;
    private JButton btnAddCompetition;
    private JButton btnAddSquad;
    private JButton btnSaveCompetition;
    private JButton btnCancel;
    private JButton btnDeleteCompetition;
    private JButton btnRemoveSquad;
    private Squad actualSquad;
    private JPanel panel;

    /**
     * Instantiates a new competition panel.
     * 
     * @param gymCupController
     *            the gym cup controller
     */
    public CompetitionPanel(GymCupController gymCupController) {
	this.gymCupController = gymCupController;
	initGUI();
	initListeners();
    }

    /**
     * Inits the listeners.
     */
    private void initListeners() {

	btnCancel.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (isActualCompetitionChanged()) {
		    updateCompetitionTextFields();
		}
		competitionOverviewTableModel.fireTableDataChanged();
		updateSaveCanelButtons(false);
	    }
	});

	btnDeleteCompetition.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (gymCupController.getGymCup().getCompetitions().remove(actualCompetition)) {
		    competitionOverviewTableModel.fireTableDataChanged();
		    DBController.deleteCompetitionFromGymCup(actualCompetition, gymCupController.getGymCup());
		    setActualCompetition(null);
		}
	    }
	});

	btnSaveCompetition.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (actualCompetition != null) {
		    if (isActualCompetitionChanged()) {
			try {
			    Competition newComp = new Competition(txtFieldDescription.getText(), DateFormatConverter
				    .convertStringToDate(txtFieldDate.getText()), txtFieldStartTime.getText(),
				    txtFieldEndTime.getText(), txtFieldProgramClass.getText());
			    DBController.updateCompetition(newComp, actualCompetition);
			    updateActualCompetition(newComp);

			    competitionOverviewTableModel.fireTableDataChanged();
			    setActualCompetition(null);

			} catch (ParseException e1) {
			}
		    }
		} else {
		    addCompetition();
		    competitionOverviewTableModel.fireTableDataChanged();
		    cleanCompetitionInfos();
		}

		btnAddCompetition.setEnabled(true);
	    }
	});

	btnAddCompetition.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		cleanCompetitionInfos();
		tableCompetitionsOverview.getSelectionModel().clearSelection();
		txtFieldDescription.requestFocus();
		btnAddCompetition.setEnabled(false);
	    }
	});

	btnRemoveSquad.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (actualCompetition != null) {
		    removeActualSquadFromCompetition(actualCompetition, actualSquad);
		}

	    }
	});

	tableSquadsInCompetition.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent event) {
		if (tableSquadsInCompetition.getSelectedRowCount() > 0) {
		    updateSelectedSquad();
		    btnRemoveSquad.setEnabled(true);
		} else {
		    btnRemoveSquad.setEnabled(false);
		}
	    }
	});

	tableCompetitionsOverview.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent event) {
		if (tableCompetitionsOverview.getSelectedRowCount() > 0) {
		    updateCompetitionInfos();
		    updateButtons(true);
		    updateSaveCanelButtons(false);
		} else {
		    cleanCompetitionInfos();
		    updateButtons(false);
		}
	    }
	});

	btnAddSquad.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	SquadsSelectionFrame squadSelectionFrame = new SquadsSelectionFrame(gymCupController, actualCompetition);
			squadSelectionFrame.addObserver(squadsInCompetitionTableModel);
	    }
	});

    }

    /**
     * Update selected Button enables or disables the JButtons depending on its
     * state
     */
    private void updateButtons(boolean enabled) {
	btnSaveCompetition.setEnabled(enabled);
	btnCancel.setEnabled(enabled);
	btnDeleteCompetition.setEnabled(enabled);
	btnAddSquad.setEnabled(enabled);
    }

    /**
     * Updateselectedsquad renews the actual selected Squad from the table.
     */
    protected void updateSelectedSquad() {
	if (squadsInCompetitionTableModel == null) {
	    squadsInCompetitionTableModel = new SquadsInCompetitionTableModel();
	    tableSquadsInCompetition.setModel(squadsInCompetitionTableModel);
	}

	int position = tableCompetitionsOverview.convertRowIndexToModel(tableCompetitionsOverview.getSelectedRow());
	setActualSquad(actualCompetition.getSquads().get(position));

    }

    /**
     * Sets the actual squad.
     * 
     * @param squad
     *            the new actual squad
     */
    private void setActualSquad(Squad squad) {
	actualSquad = squad;

    }

    /**
     * Checks if the actual competition has changed.
     * 
     * @return true, if actual competition has changed
     */
    private boolean isActualCompetitionChanged() {
	if (actualCompetition != null) {
	    return !(txtFieldDescription.getText().equals(actualCompetition.getDescription())
		    && txtFieldDate.getText().equals(
			    DateFormatConverter.convertDateToString(actualCompetition.getDate()))
		    && txtFieldEndTime.getText().equals(actualCompetition.getEndTime())
		    && txtFieldStartTime.getText().equals(actualCompetition.getStartTime()) && txtFieldProgramClass
		    .getText().equals(actualCompetition.getProgramClass()));
	} else {
	    return !(txtFieldDescription.getText().equals("") && txtFieldDate.getText().equals("")
		    && txtFieldEndTime.getText().equals("") && txtFieldStartTime.getText().equals("") && txtFieldProgramClass
		    .getText().equals(""));
	}
    }

    /**
     * Inits the content of the GUI.
     */
    private void initGUI() {
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 255, 0, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
	gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	setLayout(gridBagLayout);

	JPanel panelInfoBorder = new JPanel();
	panelInfoBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Allgemeine Infos",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelInfoBorder = new GridBagConstraints();
	gbc_panelInfoBorder.fill = GridBagConstraints.BOTH;
	gbc_panelInfoBorder.insets = new Insets(0, 0, 5, 5);
	gbc_panelInfoBorder.gridx = 0;
	gbc_panelInfoBorder.gridy = 0;
	add(panelInfoBorder, gbc_panelInfoBorder);
	GridBagLayout gbl_panelInfoBorder = new GridBagLayout();
	gbl_panelInfoBorder.columnWidths = new int[] { 0, 0 };
	gbl_panelInfoBorder.rowHeights = new int[] { 0, 0 };
	gbl_panelInfoBorder.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panelInfoBorder.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panelInfoBorder.setLayout(gbl_panelInfoBorder);

	JPanel panelInfo = new JPanel();
	GridBagConstraints gbc_panelInfo = new GridBagConstraints();
	gbc_panelInfo.fill = GridBagConstraints.BOTH;
	gbc_panelInfo.gridx = 0;
	gbc_panelInfo.gridy = 0;
	panelInfoBorder.add(panelInfo, gbc_panelInfo);
	GridBagLayout gbl_panelInfo = new GridBagLayout();
	gbl_panelInfo.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
	gbl_panelInfo.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
	gbl_panelInfo.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	gbl_panelInfo.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	panelInfo.setLayout(gbl_panelInfo);

	JLabel lblDescription = new JLabel("Beschreibung: ");
	GridBagConstraints gbc_lblDescription = new GridBagConstraints();
	gbc_lblDescription.anchor = GridBagConstraints.WEST;
	gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
	gbc_lblDescription.gridx = 0;
	gbc_lblDescription.gridy = 0;
	panelInfo.add(lblDescription, gbc_lblDescription);

	txtFieldDescription = new JTextField();
	txtFieldDescription.addKeyListener(new KeyReleasedApater());
	GridBagConstraints gbc_txtFieldDescription = new GridBagConstraints();
	gbc_txtFieldDescription.gridwidth = 4;
	gbc_txtFieldDescription.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldDescription.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldDescription.gridx = 1;
	gbc_txtFieldDescription.gridy = 0;
	panelInfo.add(txtFieldDescription, gbc_txtFieldDescription);
	txtFieldDescription.setColumns(10);

	JLabel lblDate = new JLabel("Datum: ");
	GridBagConstraints gbc_lblDate = new GridBagConstraints();
	gbc_lblDate.anchor = GridBagConstraints.WEST;
	gbc_lblDate.insets = new Insets(0, 0, 5, 5);
	gbc_lblDate.gridx = 0;
	gbc_lblDate.gridy = 1;
	panelInfo.add(lblDate, gbc_lblDate);

	txtFieldDate = new JFormattedTextField(new DateFormatter(DateFormat.getDateInstance(DateFormat.SHORT,
		Locale.GERMAN)));
	txtFieldDate.addKeyListener(new KeyReleasedApater());
	GridBagConstraints gbc_txtFieldDate = new GridBagConstraints();
	gbc_txtFieldDate.gridwidth = 4;
	gbc_txtFieldDate.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldDate.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldDate.gridx = 1;
	gbc_txtFieldDate.gridy = 1;
	panelInfo.add(txtFieldDate, gbc_txtFieldDate);
	txtFieldDate.setColumns(10);

	JLabel lblStartTime = new JLabel("Startzeit: ");
	GridBagConstraints gbc_lblStartTime = new GridBagConstraints();
	gbc_lblStartTime.anchor = GridBagConstraints.WEST;
	gbc_lblStartTime.insets = new Insets(0, 0, 5, 5);
	gbc_lblStartTime.gridx = 0;
	gbc_lblStartTime.gridy = 2;
	panelInfo.add(lblStartTime, gbc_lblStartTime);

	txtFieldStartTime = new JTextField();
	txtFieldStartTime.addKeyListener(new KeyReleasedApater());
	GridBagConstraints gbc_txtFieldStartTime = new GridBagConstraints();
	gbc_txtFieldStartTime.gridwidth = 4;
	gbc_txtFieldStartTime.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldStartTime.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldStartTime.gridx = 1;
	gbc_txtFieldStartTime.gridy = 2;
	panelInfo.add(txtFieldStartTime, gbc_txtFieldStartTime);
	txtFieldStartTime.setColumns(10);

	JLabel lblEndTime = new JLabel("Endzeit: ");
	GridBagConstraints gbc_lblEndTime = new GridBagConstraints();
	gbc_lblEndTime.anchor = GridBagConstraints.WEST;
	gbc_lblEndTime.insets = new Insets(0, 0, 5, 5);
	gbc_lblEndTime.gridx = 0;
	gbc_lblEndTime.gridy = 3;
	panelInfo.add(lblEndTime, gbc_lblEndTime);

	txtFieldEndTime = new JTextField();
	txtFieldEndTime.addKeyListener(new KeyReleasedApater());
	GridBagConstraints gbc_txtFieldEndTime = new GridBagConstraints();
	gbc_txtFieldEndTime.gridwidth = 4;
	gbc_txtFieldEndTime.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldEndTime.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldEndTime.gridx = 1;
	gbc_txtFieldEndTime.gridy = 3;
	panelInfo.add(txtFieldEndTime, gbc_txtFieldEndTime);
	txtFieldEndTime.setColumns(10);

	JLabel lblProgramClass = new JLabel("Programmklasse: ");
	GridBagConstraints gbc_lblProgramClass = new GridBagConstraints();
	gbc_lblProgramClass.anchor = GridBagConstraints.WEST;
	gbc_lblProgramClass.insets = new Insets(0, 0, 5, 5);
	gbc_lblProgramClass.gridx = 0;
	gbc_lblProgramClass.gridy = 4;
	panelInfo.add(lblProgramClass, gbc_lblProgramClass);

	txtFieldProgramClass = new JTextField();
	txtFieldProgramClass.addKeyListener(new KeyReleasedApater());
	GridBagConstraints gbc_txtFieldProgramClass = new GridBagConstraints();
	gbc_txtFieldProgramClass.gridwidth = 4;
	gbc_txtFieldProgramClass.insets = new Insets(0, 0, 5, 0);
	gbc_txtFieldProgramClass.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtFieldProgramClass.gridx = 1;
	gbc_txtFieldProgramClass.gridy = 4;
	panelInfo.add(txtFieldProgramClass, gbc_txtFieldProgramClass);
	txtFieldProgramClass.setColumns(10);

	btnCancel = new JButton("Abbrechen");
	GridBagConstraints gbc_btnCancel = new GridBagConstraints();
	gbc_btnCancel.anchor = GridBagConstraints.EAST;
	gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
	gbc_btnCancel.gridx = 3;
	gbc_btnCancel.gridy = 5;
	panelInfo.add(btnCancel, gbc_btnCancel);

	btnSaveCompetition = new JButton("Speichern");
	GridBagConstraints gbc_btnSave = new GridBagConstraints();
	gbc_btnSave.gridx = 4;
	gbc_btnSave.gridy = 5;
	panelInfo.add(btnSaveCompetition, gbc_btnSave);

	JPanel panelOverviewBorder = new JPanel();
	panelOverviewBorder.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
		"\u00DCbersicht", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelOverviewBorder = new GridBagConstraints();
	gbc_panelOverviewBorder.gridheight = 2;
	gbc_panelOverviewBorder.fill = GridBagConstraints.BOTH;
	gbc_panelOverviewBorder.gridx = 1;
	gbc_panelOverviewBorder.gridy = 0;
	add(panelOverviewBorder, gbc_panelOverviewBorder);
	GridBagLayout gbl_panelOverviewBorder = new GridBagLayout();
	gbl_panelOverviewBorder.columnWidths = new int[] { 0, 0 };
	gbl_panelOverviewBorder.rowHeights = new int[] { 0, 0, 0 };
	gbl_panelOverviewBorder.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panelOverviewBorder.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
	panelOverviewBorder.setLayout(gbl_panelOverviewBorder);

	JScrollPane scrollPaneOverview = new JScrollPane();
	GridBagConstraints gbc_scrollPaneOverview = new GridBagConstraints();
	gbc_scrollPaneOverview.insets = new Insets(0, 0, 5, 0);
	gbc_scrollPaneOverview.fill = GridBagConstraints.BOTH;
	gbc_scrollPaneOverview.gridx = 0;
	gbc_scrollPaneOverview.gridy = 0;
	panelOverviewBorder.add(scrollPaneOverview, gbc_scrollPaneOverview);

	tableCompetitionsOverview = new JTable();
	tableCompetitionsOverview.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	competitionOverviewTableModel = new CompetitionOverviewTableModel(gymCupController);

	tableCompetitionsOverview.setModel(competitionOverviewTableModel);
	scrollPaneOverview.setViewportView(tableCompetitionsOverview);

	panel = new JPanel();
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 0;
	gbc_panel.gridy = 1;
	panelOverviewBorder.add(panel, gbc_panel);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[] { 0, 0, 0 };
	gbl_panel.rowHeights = new int[] { 0, 0 };
	gbl_panel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
	gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panel.setLayout(gbl_panel);

	btnDeleteCompetition = new JButton("Entfernen");
	GridBagConstraints gbc_btnDeleteCompetition = new GridBagConstraints();
	gbc_btnDeleteCompetition.anchor = GridBagConstraints.EAST;
	gbc_btnDeleteCompetition.insets = new Insets(0, 0, 0, 5);
	gbc_btnDeleteCompetition.gridx = 0;
	gbc_btnDeleteCompetition.gridy = 0;
	panel.add(btnDeleteCompetition, gbc_btnDeleteCompetition);

	btnAddCompetition = new JButton("Hinzuf\u00fcgen");
	GridBagConstraints gbc_btnAddCompetition = new GridBagConstraints();
	gbc_btnAddCompetition.gridx = 1;
	gbc_btnAddCompetition.gridy = 0;
	panel.add(btnAddCompetition, gbc_btnAddCompetition);

	JPanel panelSquadsBorder = new JPanel();
	panelSquadsBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Riegen",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelSquadsBorder = new GridBagConstraints();
	gbc_panelSquadsBorder.fill = GridBagConstraints.BOTH;
	gbc_panelSquadsBorder.insets = new Insets(0, 0, 0, 5);
	gbc_panelSquadsBorder.gridx = 0;
	gbc_panelSquadsBorder.gridy = 1;
	add(panelSquadsBorder, gbc_panelSquadsBorder);
	GridBagLayout gbl_panelSquadsBorder = new GridBagLayout();
	gbl_panelSquadsBorder.columnWidths = new int[] { 0, 0, 0 };
	gbl_panelSquadsBorder.rowHeights = new int[] { 0, 0, 0 };
	gbl_panelSquadsBorder.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
	gbl_panelSquadsBorder.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
	panelSquadsBorder.setLayout(gbl_panelSquadsBorder);

	JScrollPane scrollPaneSquadsCompetition = new JScrollPane();
	GridBagConstraints gbc_scrollPaneSquadsCompetition = new GridBagConstraints();
	gbc_scrollPaneSquadsCompetition.gridwidth = 2;
	gbc_scrollPaneSquadsCompetition.insets = new Insets(0, 0, 5, 5);
	gbc_scrollPaneSquadsCompetition.fill = GridBagConstraints.BOTH;
	gbc_scrollPaneSquadsCompetition.gridx = 0;
	gbc_scrollPaneSquadsCompetition.gridy = 0;
	panelSquadsBorder.add(scrollPaneSquadsCompetition, gbc_scrollPaneSquadsCompetition);

	tableSquadsInCompetition = new JTable();
	tableSquadsInCompetition.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	scrollPaneSquadsCompetition.setViewportView(tableSquadsInCompetition);

	btnRemoveSquad = new JButton("Entfernen");
	GridBagConstraints gbc_btnEntfernen = new GridBagConstraints();
	gbc_btnEntfernen.anchor = GridBagConstraints.EAST;
	gbc_btnEntfernen.insets = new Insets(0, 0, 0, 5);
	gbc_btnEntfernen.gridx = 0;
	gbc_btnEntfernen.gridy = 1;
	panelSquadsBorder.add(btnRemoveSquad, gbc_btnEntfernen);
	btnRemoveSquad.setEnabled(false);

	btnAddSquad = new JButton("Hinzuf\u00fcgen...");
	GridBagConstraints gbc_btnAddSquad = new GridBagConstraints();
	gbc_btnAddSquad.gridx = 1;
	gbc_btnAddSquad.gridy = 1;
	panelSquadsBorder.add(btnAddSquad, gbc_btnAddSquad);

	updateButtons(false);
    }

    private void updateCompetitionTextFields() {
	if (actualCompetition != null) {
	    txtFieldDescription.setText(actualCompetition.getDescription());
	    txtFieldDate.setText(DateFormatConverter.convertDateToString(actualCompetition.getDate()));
	    txtFieldEndTime.setText(actualCompetition.getEndTime());
	    txtFieldStartTime.setText(actualCompetition.getStartTime());
	    txtFieldProgramClass.setText(actualCompetition.getProgramClass());
	} else {
	    txtFieldDescription.setText("");
	    txtFieldDate.setText("");
	    txtFieldEndTime.setText("");
	    txtFieldStartTime.setText("");
	    txtFieldProgramClass.setText("");
	}

    }

    /**
     * Update selected Competition renews the actual selected Competition from
     * the table.
     */
    private void updateCompetitionInfos() {
	setSquadsTableModel();
	int position = tableCompetitionsOverview.convertRowIndexToModel(tableCompetitionsOverview.getSelectedRow());
	setActualCompetition(gymCupController.getGymCup().getCompetitions().get(position));
	updateCompetitionTextFields();
    }

    /**
     * Removes the actual squad from the refered competition.
     * 
     * @param competition
     *            the competition
     * @param squad
     *            the squad
     */
    private void removeActualSquadFromCompetition(Competition competition, Squad squad) {
	for (Competition comp : gymCupController.getGymCup().getCompetitions()) {
	    if (comp.equals(actualCompetition)) {
		DBController.removeSquadFromCompetition(comp, squad);
		comp.removeSquad(squad);
		gymCupController.getGymCup().addSquadUnallocated(squad);
		squadsInCompetitionTableModel.fireTableDataChanged();
	    }
	}
    }

    /**
     * Sets the squads table model.
     */
    private void setSquadsTableModel() {
	if (squadsInCompetitionTableModel == null) {
	    squadsInCompetitionTableModel = new SquadsInCompetitionTableModel();
	    tableSquadsInCompetition.setModel(squadsInCompetitionTableModel);
	}
    }

    /**
     * Sets the actual Competition which is selected
     * 
     * @param competition
     *            the new actual competition
     */
    private void updateActualCompetition(Competition newComp) {
	actualCompetition.setDescription(newComp.getDescription());
	actualCompetition.setDate(newComp.getDate());
	actualCompetition.setEndTime(newComp.getEndTime());
	actualCompetition.setStartTime(newComp.getStartTime());
	actualCompetition.setProgramClass(newComp.getProgramClass());
    }

    /**
     * Sets the actual competition.
     * 
     * @param competition
     *            the new actual competition
     */
    private void setActualCompetition(Competition competition) {
	this.actualCompetition = competition;
	squadsInCompetitionTableModel.setCompetition(actualCompetition);
    }

    private void addCompetition() {
	Competition competition = null;
	try {
	    competition = new Competition(txtFieldDescription.getText(), DateFormatConverter
		    .convertStringToDate(txtFieldDate.getText()), txtFieldStartTime.getText(), txtFieldEndTime
		    .getText(), txtFieldProgramClass.getText());

	} catch (ParseException e1) {
	}
	if (gymCupController.getGymCup().addCompetition(competition)) {
	    DBController.addCompetitionToGymCup(competition, gymCupController.getGymCup());
	    competitionOverviewTableModel.fireTableDataChanged();
	}
    }

    /**
     * Clean competition informations
     */
    private void cleanCompetitionInfos() {
	if (squadsInCompetitionTableModel != null) {
	    squadsInCompetitionTableModel.setCompetition(null);
	}
	actualCompetition = null;
	txtFieldDescription.setText("");
	txtFieldDate.setText("");
	txtFieldStartTime.setText("");
	txtFieldEndTime.setText("");
	txtFieldProgramClass.setText("");
	updateSaveCanelButtons(false);
    }

    private void changesCompetitionInfos() {
	if (isActualCompetitionChanged()) {
	    updateSaveCanelButtons(true);
	} else {
	    updateSaveCanelButtons(false);
	}
    }

    private void updateSaveCanelButtons(Boolean enable) {
	btnCancel.setEnabled(enable);
	btnSaveCompetition.setEnabled(enable);
    }
    
    private class KeyReleasedApater extends KeyAdapter {

	public void keyReleased(KeyEvent e) {
	    changesCompetitionInfos();
	}
    }

}
