package ch.hsr.gymtastic.presentation.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;

public class CompetitionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableCompetitions;
	private JTextField txtFieldProgramClass;
	private JTextField txtFieldEndTime;
	private JTextField txtFieldStartTime;
	private JTextField txtFieldDate;
	private JTextField txtFieldDescription;
	private JTable table;

	public CompetitionPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelInfoBorder = new JPanel();
		panelInfoBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Allgemeine Infos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelInfoBorder = new GridBagConstraints();
		gbc_panelInfoBorder.fill = GridBagConstraints.BOTH;
		gbc_panelInfoBorder.insets = new Insets(0, 0, 5, 5);
		gbc_panelInfoBorder.gridx = 0;
		gbc_panelInfoBorder.gridy = 0;
		add(panelInfoBorder, gbc_panelInfoBorder);
		GridBagLayout gbl_panelInfoBorder = new GridBagLayout();
		gbl_panelInfoBorder.columnWidths = new int[]{0, 0};
		gbl_panelInfoBorder.rowHeights = new int[]{0, 0};
		gbl_panelInfoBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelInfoBorder.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelInfoBorder.setLayout(gbl_panelInfoBorder);
		
		JPanel panelInfo = new JPanel();
		GridBagConstraints gbc_panelInfo = new GridBagConstraints();
		gbc_panelInfo.fill = GridBagConstraints.BOTH;
		gbc_panelInfo.gridx = 0;
		gbc_panelInfo.gridy = 0;
		panelInfoBorder.add(panelInfo, gbc_panelInfo);
		GridBagLayout gbl_panelInfo = new GridBagLayout();
		gbl_panelInfo.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelInfo.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelInfo.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelInfo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInfo.setLayout(gbl_panelInfo);
		
		JLabel lblDescription = new JLabel("Beschreibung: ");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 0;
		panelInfo.add(lblDescription, gbc_lblDescription);
		
		txtFieldDescription = new JTextField();
		GridBagConstraints gbc_txtFieldDescription = new GridBagConstraints();
		gbc_txtFieldDescription.gridwidth = 2;
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
		
		txtFieldDate = new JTextField();
		GridBagConstraints gbc_txtFieldDate = new GridBagConstraints();
		gbc_txtFieldDate.gridwidth = 2;
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
		GridBagConstraints gbc_txtFieldStartTime = new GridBagConstraints();
		gbc_txtFieldStartTime.gridwidth = 2;
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
		GridBagConstraints gbc_txtFieldEndTime = new GridBagConstraints();
		gbc_txtFieldEndTime.gridwidth = 2;
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
		GridBagConstraints gbc_txtFieldProgramClass = new GridBagConstraints();
		gbc_txtFieldProgramClass.gridwidth = 2;
		gbc_txtFieldProgramClass.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldProgramClass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldProgramClass.gridx = 1;
		gbc_txtFieldProgramClass.gridy = 4;
		panelInfo.add(txtFieldProgramClass, gbc_txtFieldProgramClass);
		txtFieldProgramClass.setColumns(10);
		
		JButton btnCancel = new JButton("Abbrechen");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.NORTH;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 5;
		panelInfo.add(btnCancel, gbc_btnCancel);
		
		JButton btnSave = new JButton("Speichern");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.NORTH;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 5;
		panelInfo.add(btnSave, gbc_btnSave);
		
		JButton btnAdd = new JButton("Hinzufügen");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.NORTH;
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 5;
		panelInfo.add(btnAdd, gbc_btnAdd);
		
		JPanel panelOverviewBorder = new JPanel();
		panelOverviewBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u00DCbersicht", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelOverviewBorder = new GridBagConstraints();
		gbc_panelOverviewBorder.gridheight = 2;
		gbc_panelOverviewBorder.fill = GridBagConstraints.BOTH;
		gbc_panelOverviewBorder.gridx = 1;
		gbc_panelOverviewBorder.gridy = 0;
		add(panelOverviewBorder, gbc_panelOverviewBorder);
		GridBagLayout gbl_panelOverviewBorder = new GridBagLayout();
		gbl_panelOverviewBorder.columnWidths = new int[]{0, 0};
		gbl_panelOverviewBorder.rowHeights = new int[]{0, 0};
		gbl_panelOverviewBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelOverviewBorder.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelOverviewBorder.setLayout(gbl_panelOverviewBorder);
		
		JScrollPane scrollPaneOverview = new JScrollPane();
		GridBagConstraints gbc_scrollPaneOverview = new GridBagConstraints();
		gbc_scrollPaneOverview.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneOverview.gridx = 0;
		gbc_scrollPaneOverview.gridy = 0;
		panelOverviewBorder.add(scrollPaneOverview, gbc_scrollPaneOverview);
		
		tableCompetitions = new JTable();
		tableCompetitions.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Wettkampf", "New column", "New column"
			}
		));
		scrollPaneOverview.setViewportView(tableCompetitions);
		
		JPanel panelSquadsBorder = new JPanel();
		panelSquadsBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Riegen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelSquadsBorder = new GridBagConstraints();
		gbc_panelSquadsBorder.fill = GridBagConstraints.BOTH;
		gbc_panelSquadsBorder.insets = new Insets(0, 0, 0, 5);
		gbc_panelSquadsBorder.gridx = 0;
		gbc_panelSquadsBorder.gridy = 1;
		add(panelSquadsBorder, gbc_panelSquadsBorder);
		GridBagLayout gbl_panelSquadsBorder = new GridBagLayout();
		gbl_panelSquadsBorder.columnWidths = new int[]{0, 0, 0};
		gbl_panelSquadsBorder.rowHeights = new int[]{0, 0, 0};
		gbl_panelSquadsBorder.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelSquadsBorder.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelSquadsBorder.setLayout(gbl_panelSquadsBorder);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelSquadsBorder.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Riege"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnEntfernen = new JButton("Entfernen");
		GridBagConstraints gbc_btnEntfernen = new GridBagConstraints();
		gbc_btnEntfernen.anchor = GridBagConstraints.EAST;
		gbc_btnEntfernen.insets = new Insets(0, 0, 0, 5);
		gbc_btnEntfernen.gridx = 0;
		gbc_btnEntfernen.gridy = 1;
		panelSquadsBorder.add(btnEntfernen, gbc_btnEntfernen);
		
		JButton btnHinzufgen = new JButton("Hinzufügen...");
		GridBagConstraints gbc_btnHinzufgen = new GridBagConstraints();
		gbc_btnHinzufgen.gridx = 1;
		gbc_btnHinzufgen.gridy = 1;
		panelSquadsBorder.add(btnHinzufgen, gbc_btnHinzufgen);
		initGUI();
		initListeners();
	}

	private void initListeners() {
		// TODO Auto-generated method stub
		
	}

	private void initGUI() {
		// TODO Auto-generated method stub
		
	}
	

}
