package ch.hsr.gymtastic.server.presentation.frames.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ch.hsr.gymtastic.server.presentation.frames.JudgeDetailFrame;

public class JudgePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtFieldSearchJudge;

	public JudgePanel() {
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

		JPanel panelStatisticsBorder = new JPanel();
		panelStatisticsBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Statistik",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelStatisticsBorder = new GridBagConstraints();
		gbc_panelStatisticsBorder.fill = GridBagConstraints.BOTH;
		gbc_panelStatisticsBorder.insets = new Insets(0, 0, 5, 0);
		gbc_panelStatisticsBorder.gridx = 0;
		gbc_panelStatisticsBorder.gridy = 0;
		add(panelStatisticsBorder, gbc_panelStatisticsBorder);
		GridBagLayout gbl_panelStatisticsBorder = new GridBagLayout();
		gbl_panelStatisticsBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelStatisticsBorder.rowHeights = new int[] { 0, 0 };
		gbl_panelStatisticsBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelStatisticsBorder.rowWeights = new double[] { 0.0,
				Double.MIN_VALUE };
		panelStatisticsBorder.setLayout(gbl_panelStatisticsBorder);

		JPanel panelStatistics = new JPanel();
		GridBagConstraints gbc_panelStatistics = new GridBagConstraints();
		gbc_panelStatistics.fill = GridBagConstraints.BOTH;
		gbc_panelStatistics.gridx = 0;
		gbc_panelStatistics.gridy = 0;
		panelStatisticsBorder.add(panelStatistics, gbc_panelStatistics);
		GridBagLayout gbl_panelStatistics = new GridBagLayout();
		gbl_panelStatistics.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelStatistics.rowHeights = new int[] { 0, 0 };
		gbl_panelStatistics.columnWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelStatistics.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelStatistics.setLayout(gbl_panelStatistics);

		JLabel lblJudges = new JLabel("Anzahl Kampfrichter: ");
		GridBagConstraints gbc_lblJudges = new GridBagConstraints();
		gbc_lblJudges.insets = new Insets(0, 0, 0, 5);
		gbc_lblJudges.gridx = 0;
		gbc_lblJudges.gridy = 0;
		panelStatistics.add(lblJudges, gbc_lblJudges);

		JLabel lblJudgesAmount = new JLabel("0");
		GridBagConstraints gbc_lblJudgesAmount = new GridBagConstraints();
		gbc_lblJudgesAmount.gridx = 1;
		gbc_lblJudgesAmount.gridy = 0;
		panelStatistics.add(lblJudgesAmount, gbc_lblJudgesAmount);

		JPanel panelJudgesBorder = new JPanel();
		panelJudgesBorder.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Kampfrichter",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelJudgesBorder = new GridBagConstraints();
		gbc_panelJudgesBorder.fill = GridBagConstraints.BOTH;
		gbc_panelJudgesBorder.gridx = 0;
		gbc_panelJudgesBorder.gridy = 1;
		add(panelJudgesBorder, gbc_panelJudgesBorder);
		GridBagLayout gbl_panelJudgesBorder = new GridBagLayout();
		gbl_panelJudgesBorder.columnWidths = new int[] { 0, 0 };
		gbl_panelJudgesBorder.rowHeights = new int[] { 0, 0 };
		gbl_panelJudgesBorder.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_panelJudgesBorder.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelJudgesBorder.setLayout(gbl_panelJudgesBorder);

		JPanel panelJudges = new JPanel();
		GridBagConstraints gbc_panelJudges = new GridBagConstraints();
		gbc_panelJudges.fill = GridBagConstraints.BOTH;
		gbc_panelJudges.gridx = 0;
		gbc_panelJudges.gridy = 0;
		panelJudgesBorder.add(panelJudges, gbc_panelJudges);
		GridBagLayout gbl_panelJudges = new GridBagLayout();
		gbl_panelJudges.columnWidths = new int[] { 0, 0 };
		gbl_panelJudges.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelJudges.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelJudges.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelJudges.setLayout(gbl_panelJudges);

		JPanel panelJudgesSearch = new JPanel();
		GridBagConstraints gbc_panelJudgesSearch = new GridBagConstraints();
		gbc_panelJudgesSearch.insets = new Insets(0, 0, 5, 0);
		gbc_panelJudgesSearch.fill = GridBagConstraints.BOTH;
		gbc_panelJudgesSearch.gridx = 0;
		gbc_panelJudgesSearch.gridy = 0;
		panelJudges.add(panelJudgesSearch, gbc_panelJudgesSearch);
		GridBagLayout gbl_panelJudgesSearch = new GridBagLayout();
		gbl_panelJudgesSearch.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelJudgesSearch.rowHeights = new int[] { 0, 0 };
		gbl_panelJudgesSearch.columnWeights = new double[] { 1.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_panelJudgesSearch.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelJudgesSearch.setLayout(gbl_panelJudgesSearch);

		txtFieldSearchJudge = new JTextField();
		GridBagConstraints gbc_txtFieldSearchJudge = new GridBagConstraints();
		gbc_txtFieldSearchJudge.insets = new Insets(0, 0, 0, 5);
		gbc_txtFieldSearchJudge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldSearchJudge.gridx = 0;
		gbc_txtFieldSearchJudge.gridy = 0;
		panelJudgesSearch.add(txtFieldSearchJudge, gbc_txtFieldSearchJudge);
		txtFieldSearchJudge.setColumns(10);

		JButton btnShowJudge = new JButton("Anzeigen...");
		btnShowJudge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JudgeDetailFrame.open();
			}
		});
		GridBagConstraints gbc_btnShowJudge = new GridBagConstraints();
		gbc_btnShowJudge.insets = new Insets(0, 0, 0, 5);
		gbc_btnShowJudge.gridx = 2;
		gbc_btnShowJudge.gridy = 0;
		panelJudgesSearch.add(btnShowJudge, gbc_btnShowJudge);

		JButton btnAddJudge = new JButton("Hinzuf\u00fcgen...");
		GridBagConstraints gbc_btnAddJudge = new GridBagConstraints();
		gbc_btnAddJudge.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddJudge.gridx = 3;
		gbc_btnAddJudge.gridy = 0;
		panelJudgesSearch.add(btnAddJudge, gbc_btnAddJudge);

		JButton btnRemoveJudge = new JButton("Entfernen");
		GridBagConstraints gbc_btnRemoveJudge = new GridBagConstraints();
		gbc_btnRemoveJudge.gridx = 4;
		gbc_btnRemoveJudge.gridy = 0;
		panelJudgesSearch.add(btnRemoveJudge, gbc_btnRemoveJudge);

		JScrollPane scrollPaneJudges = new JScrollPane();
		GridBagConstraints gbc_scrollPaneJudges = new GridBagConstraints();
		gbc_scrollPaneJudges.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneJudges.gridx = 0;
		gbc_scrollPaneJudges.gridy = 1;
		panelJudges.add(scrollPaneJudges, gbc_scrollPaneJudges);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"New column", "New column", "New column", "New column",
				"New column" }));
		scrollPaneJudges.setViewportView(table);

	}

}
