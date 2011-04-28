package ch.hsr.gymtastic.presentation.panels.server;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;


public class RankingPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel panel_2;
	private JPanel panel_1;
	private JLabel lblCompetition;
	private JComboBox comboBox;
	private JRadioButton rdbtnStartliste;
	private JRadioButton rdbtnRangliste;
	private JPanel panel_3;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JButton btnPrint;

	public RankingPanel() {
		initGUI();
		initListeners();
	}

	private void initListeners() {
		
	}

	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Auswahl",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel_2.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		lblCompetition = new JLabel("Wettkampf: ");
		GridBagConstraints gbc_lblCompetition = new GridBagConstraints();
		gbc_lblCompetition.insets = new Insets(0, 0, 0, 5);
		gbc_lblCompetition.anchor = GridBagConstraints.EAST;
		gbc_lblCompetition.gridx = 0;
		gbc_lblCompetition.gridy = 0;
		panel_1.add(lblCompetition, gbc_lblCompetition);

		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel_1.add(comboBox, gbc_comboBox);

		rdbtnStartliste = new JRadioButton("Startliste");
		GridBagConstraints gbc_rdbtnStartliste = new GridBagConstraints();
		gbc_rdbtnStartliste.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnStartliste.gridx = 3;
		gbc_rdbtnStartliste.gridy = 0;
		panel_1.add(rdbtnStartliste, gbc_rdbtnStartliste);

		rdbtnRangliste = new JRadioButton("Rangliste");
		GridBagConstraints gbc_rdbtnRangliste = new GridBagConstraints();
		gbc_rdbtnRangliste.gridx = 4;
		gbc_rdbtnRangliste.gridy = 0;
		panel_1.add(rdbtnRangliste, gbc_rdbtnRangliste);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Liste",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_3.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnPrint = new JButton("Drucken...");

		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.anchor = GridBagConstraints.EAST;
		gbc_btnPrint.gridx = 1;
		gbc_btnPrint.gridy = 1;
		panel.add(btnPrint, gbc_btnPrint);

	}

}
