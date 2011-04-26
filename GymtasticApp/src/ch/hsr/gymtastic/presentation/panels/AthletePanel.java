package ch.hsr.gymtastic.presentation.panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ch.hsr.gymtastic.presentation.AthleteDetailFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AthletePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFieldSearchAthlete;
	private JTable tableAthletes;

	public AthletePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelStatisticsBorder = new JPanel();
		panelStatisticsBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Statistik", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelStatisticsBorder = new GridBagConstraints();
		gbc_panelStatisticsBorder.fill = GridBagConstraints.BOTH;
		gbc_panelStatisticsBorder.insets = new Insets(0, 0, 5, 0);
		gbc_panelStatisticsBorder.gridx = 0;
		gbc_panelStatisticsBorder.gridy = 0;
		add(panelStatisticsBorder, gbc_panelStatisticsBorder);
		GridBagLayout gbl_panelStatisticsBorder = new GridBagLayout();
		gbl_panelStatisticsBorder.columnWidths = new int[]{0, 0};
		gbl_panelStatisticsBorder.rowHeights = new int[]{0, 0};
		gbl_panelStatisticsBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelStatisticsBorder.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelStatisticsBorder.setLayout(gbl_panelStatisticsBorder);
		
		JPanel panelStatistics = new JPanel();
		GridBagConstraints gbc_panelStatistics = new GridBagConstraints();
		gbc_panelStatistics.fill = GridBagConstraints.BOTH;
		gbc_panelStatistics.gridx = 0;
		gbc_panelStatistics.gridy = 0;
		panelStatisticsBorder.add(panelStatistics, gbc_panelStatistics);
		GridBagLayout gbl_panelStatistics = new GridBagLayout();
		gbl_panelStatistics.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelStatistics.rowHeights = new int[]{0, 0};
		gbl_panelStatistics.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelStatistics.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelStatistics.setLayout(gbl_panelStatistics);
		
		JLabel lblAthletes = new JLabel("Athleten: ");
		GridBagConstraints gbc_lblAthletes = new GridBagConstraints();
		gbc_lblAthletes.insets = new Insets(0, 0, 0, 5);
		gbc_lblAthletes.gridx = 0;
		gbc_lblAthletes.gridy = 0;
		panelStatistics.add(lblAthletes, gbc_lblAthletes);
		
		JLabel lblAthletesAmount = new JLabel("0");
		GridBagConstraints gbc_lblAthletesAmount = new GridBagConstraints();
		gbc_lblAthletesAmount.insets = new Insets(0, 0, 0, 5);
		gbc_lblAthletesAmount.gridx = 1;
		gbc_lblAthletesAmount.gridy = 0;
		panelStatistics.add(lblAthletesAmount, gbc_lblAthletesAmount);
		
		JLabel lblSquads = new JLabel("Riegen: ");
		GridBagConstraints gbc_lblSquads = new GridBagConstraints();
		gbc_lblSquads.insets = new Insets(0, 0, 0, 5);
		gbc_lblSquads.gridx = 3;
		gbc_lblSquads.gridy = 0;
		panelStatistics.add(lblSquads, gbc_lblSquads);
		
		JLabel lblSquadsAmount = new JLabel("0");
		GridBagConstraints gbc_lblSquadsAmount = new GridBagConstraints();
		gbc_lblSquadsAmount.gridx = 4;
		gbc_lblSquadsAmount.gridy = 0;
		panelStatistics.add(lblSquadsAmount, gbc_lblSquadsAmount);
		
		JPanel panelAthletesBorder = new JPanel();
		panelAthletesBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Athleten", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelAthletesBorder = new GridBagConstraints();
		gbc_panelAthletesBorder.fill = GridBagConstraints.BOTH;
		gbc_panelAthletesBorder.gridx = 0;
		gbc_panelAthletesBorder.gridy = 1;
		add(panelAthletesBorder, gbc_panelAthletesBorder);
		GridBagLayout gbl_panelAthletesBorder = new GridBagLayout();
		gbl_panelAthletesBorder.columnWidths = new int[]{0, 0};
		gbl_panelAthletesBorder.rowHeights = new int[]{0, 0};
		gbl_panelAthletesBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelAthletesBorder.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelAthletesBorder.setLayout(gbl_panelAthletesBorder);
		
		JPanel panelAthletes = new JPanel();
		GridBagConstraints gbc_panelAthletes = new GridBagConstraints();
		gbc_panelAthletes.fill = GridBagConstraints.BOTH;
		gbc_panelAthletes.gridx = 0;
		gbc_panelAthletes.gridy = 0;
		panelAthletesBorder.add(panelAthletes, gbc_panelAthletes);
		GridBagLayout gbl_panelAthletes = new GridBagLayout();
		gbl_panelAthletes.columnWidths = new int[]{0, 0, 0};
		gbl_panelAthletes.rowHeights = new int[]{0, 0, 0};
		gbl_panelAthletes.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelAthletes.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelAthletes.setLayout(gbl_panelAthletes);
		
		JPanel panelAthletesSearch = new JPanel();
		GridBagConstraints gbc_panelAthletesSearch = new GridBagConstraints();
		gbc_panelAthletesSearch.gridwidth = 2;
		gbc_panelAthletesSearch.insets = new Insets(0, 0, 5, 0);
		gbc_panelAthletesSearch.fill = GridBagConstraints.BOTH;
		gbc_panelAthletesSearch.gridx = 0;
		gbc_panelAthletesSearch.gridy = 0;
		panelAthletes.add(panelAthletesSearch, gbc_panelAthletesSearch);
		GridBagLayout gbl_panelAthletesSearch = new GridBagLayout();
		gbl_panelAthletesSearch.columnWidths = new int[]{179, 0, 0, 0, 0, 0};
		gbl_panelAthletesSearch.rowHeights = new int[]{0, 0};
		gbl_panelAthletesSearch.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelAthletesSearch.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelAthletesSearch.setLayout(gbl_panelAthletesSearch);
		
		txtFieldSearchAthlete = new JTextField();
		GridBagConstraints gbc_txtFieldSearchAthlete = new GridBagConstraints();
		gbc_txtFieldSearchAthlete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldSearchAthlete.insets = new Insets(0, 0, 0, 5);
		gbc_txtFieldSearchAthlete.gridx = 0;
		gbc_txtFieldSearchAthlete.gridy = 0;
		panelAthletesSearch.add(txtFieldSearchAthlete, gbc_txtFieldSearchAthlete);
		txtFieldSearchAthlete.setColumns(10);
		
		JButton btnShowAthlete = new JButton("Anzeigen...");
		btnShowAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    AthleteDetailFrame.open();
			}
		});
		GridBagConstraints gbc_btnShowAthlete = new GridBagConstraints();
		gbc_btnShowAthlete.anchor = GridBagConstraints.EAST;
		gbc_btnShowAthlete.insets = new Insets(0, 0, 0, 5);
		gbc_btnShowAthlete.gridx = 2;
		gbc_btnShowAthlete.gridy = 0;
		panelAthletesSearch.add(btnShowAthlete, gbc_btnShowAthlete);
		
		JButton btnAddAthlete = new JButton("Hinzufügen...");
		GridBagConstraints gbc_btnAddAthlete = new GridBagConstraints();
		gbc_btnAddAthlete.anchor = GridBagConstraints.EAST;
		gbc_btnAddAthlete.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddAthlete.gridx = 3;
		gbc_btnAddAthlete.gridy = 0;
		panelAthletesSearch.add(btnAddAthlete, gbc_btnAddAthlete);
		
		JButton btnRemoveAthlete = new JButton("Entfernen");
		GridBagConstraints gbc_btnRemoveAthlete = new GridBagConstraints();
		gbc_btnRemoveAthlete.anchor = GridBagConstraints.EAST;
		gbc_btnRemoveAthlete.gridx = 4;
		gbc_btnRemoveAthlete.gridy = 0;
		panelAthletesSearch.add(btnRemoveAthlete, gbc_btnRemoveAthlete);
		
		JScrollPane scrollPaneAthletes = new JScrollPane();
		GridBagConstraints gbc_scrollPaneAthletes = new GridBagConstraints();
		gbc_scrollPaneAthletes.gridwidth = 2;
		gbc_scrollPaneAthletes.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneAthletes.gridx = 0;
		gbc_scrollPaneAthletes.gridy = 1;
		panelAthletes.add(scrollPaneAthletes, gbc_scrollPaneAthletes);
		
		tableAthletes = new JTable();
		tableAthletes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPaneAthletes.setViewportView(tableAthletes);
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
