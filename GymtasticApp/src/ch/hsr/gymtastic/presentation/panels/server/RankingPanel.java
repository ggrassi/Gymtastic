package ch.hsr.gymtastic.presentation.panels.server;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JList;

import ch.hsr.gymtastic.application.models.RankingModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RankingPanel extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panelStartlist;
	private JRadioButton rdbtnStartlist;
	private JComboBox comboBoxStartlist;
	private JPanel panelRankingTable;
	private JRadioButton rdbtnRankingList;
	private JComboBox comboBoxRankingTable;
	private JPanel panelControl;
	private JButton btnPreview;
	private JButton btnSave;
	private JPanel panelPreview;
	private JFileChooser chooser;
	private ButtonGroup buttonGroupParticipation;
	private RankingModel rankingModel;
	private DefaultComboBoxModel comboBoxStartlistModel;
	private DefaultComboBoxModel comboBoxRankingModel;

	public RankingPanel(RankingModel rankingModel) {
		this.rankingModel = rankingModel;
		this.rankingModel.addObserver(this);
		initGUI();
		initListeners();
	}

	private void initListeners() {
		btnSave.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				File file = null;

				 chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(false);
//				chooser.setFileFilter(new FileFilter() {
//					public boolean accept(File f) {
//						return f.isDirectory()
//								|| f.getName().toLowerCase().endsWith(".pdf");
//					}
//
//					public String getDescription() {
//						return "Adobe PDF-Dateien (*.pdf)";
//					}
//				});

				int result = chooser.showSaveDialog(null);

				if (result == JFileChooser.APPROVE_OPTION)
					file = chooser.getSelectedFile();

				try {
					FileOutputStream fout = new FileOutputStream(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		
		
		rdbtnStartlist.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				comboBoxStartlist.setEnabled(true);
				comboBoxRankingTable.setEnabled(false);
			}
		});
		
		rdbtnRankingList.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				comboBoxStartlist.setEnabled(false);
				comboBoxRankingTable.setEnabled(true);
			}
			
		});

	}

	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		panel = new JPanel();
	
	
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		panelStartlist = new JPanel();
		panelStartlist.setBorder(new TitledBorder(null, "Startliste",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelStartlist = new GridBagConstraints();
		gbc_panelStartlist.insets = new Insets(0, 0, 5, 5);
		gbc_panelStartlist.fill = GridBagConstraints.BOTH;
		gbc_panelStartlist.gridx = 0;
		gbc_panelStartlist.gridy = 0;
		panel.add(panelStartlist, gbc_panelStartlist);
		GridBagLayout gbl_panelStartlist = new GridBagLayout();
		gbl_panelStartlist.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelStartlist.rowHeights = new int[] { 0, 0 };
		gbl_panelStartlist.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelStartlist.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelStartlist.setLayout(gbl_panelStartlist);

		rdbtnStartlist = new JRadioButton("Startliste");
		
	
		GridBagConstraints gbc_rdbtnStartlist = new GridBagConstraints();
		gbc_rdbtnStartlist.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnStartlist.gridx = 0;
		gbc_rdbtnStartlist.gridy = 0;
		panelStartlist.add(rdbtnStartlist, gbc_rdbtnStartlist);

		comboBoxStartlist = new JComboBox();
		comboBoxStartlistModel = new DefaultComboBoxModel();
		comboBoxStartlist.setModel(comboBoxStartlistModel);
		GridBagConstraints gbc_comboBoxStartlist = new GridBagConstraints();
		gbc_comboBoxStartlist.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStartlist.gridx = 1;
		gbc_comboBoxStartlist.gridy = 0;
		panelStartlist.add(comboBoxStartlist, gbc_comboBoxStartlist);

		panelRankingTable = new JPanel();
		panelRankingTable.setBorder(new TitledBorder(null, "Rangliste",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelRankingTable = new GridBagConstraints();
		gbc_panelRankingTable.insets = new Insets(0, 0, 5, 5);
		gbc_panelRankingTable.fill = GridBagConstraints.BOTH;
		gbc_panelRankingTable.gridx = 1;
		gbc_panelRankingTable.gridy = 0;
		panel.add(panelRankingTable, gbc_panelRankingTable);
		GridBagLayout gbl_panelRankingTable = new GridBagLayout();
		gbl_panelRankingTable.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelRankingTable.rowHeights = new int[] { 0, 0 };
		gbl_panelRankingTable.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelRankingTable.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelRankingTable.setLayout(gbl_panelRankingTable);

		rdbtnRankingList = new JRadioButton("Rangliste");
		GridBagConstraints gbc_rdbtnRankingList = new GridBagConstraints();
		gbc_rdbtnRankingList.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnRankingList.gridx = 0;
		gbc_rdbtnRankingList.gridy = 0;
		panelRankingTable.add(rdbtnRankingList, gbc_rdbtnRankingList);

		comboBoxRankingTable = new JComboBox();
		comboBoxRankingModel = new DefaultComboBoxModel();
		comboBoxRankingTable.setModel(comboBoxRankingModel);
		GridBagConstraints gbc_comboBoxRankingTable = new GridBagConstraints();
		gbc_comboBoxRankingTable.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRankingTable.gridx = 1;
		gbc_comboBoxRankingTable.gridy = 0;
		panelRankingTable.add(comboBoxRankingTable, gbc_comboBoxRankingTable);
		
		buttonGroupParticipation = new ButtonGroup();
		buttonGroupParticipation.add(rdbtnStartlist);
		buttonGroupParticipation.add(rdbtnRankingList);
		rdbtnStartlist.setSelected(true);
		
		comboBoxStartlist.setEnabled(true);
		comboBoxRankingTable.setEnabled(false);
		

		panelControl = new JPanel();
		panelControl.setBorder(new TitledBorder(null, "Document Verwaltung",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelControl = new GridBagConstraints();
		gbc_panelControl.insets = new Insets(0, 0, 5, 0);
		gbc_panelControl.fill = GridBagConstraints.BOTH;
		gbc_panelControl.gridx = 2;
		gbc_panelControl.gridy = 0;
		panel.add(panelControl, gbc_panelControl);
		GridBagLayout gbl_panelControl = new GridBagLayout();
		gbl_panelControl.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelControl.rowHeights = new int[] { 0, 0 };
		gbl_panelControl.columnWeights = new double[] { 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelControl.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelControl.setLayout(gbl_panelControl);

		btnPreview = new JButton("Vorschau");
		GridBagConstraints gbc_btnPreview = new GridBagConstraints();
		gbc_btnPreview.insets = new Insets(0, 0, 0, 5);
		gbc_btnPreview.gridx = 0;
		gbc_btnPreview.gridy = 0;
		panelControl.add(btnPreview, gbc_btnPreview);

		btnSave = new JButton("Speichern");

		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 0;
		panelControl.add(btnSave, gbc_btnSave);

		panelPreview = new JPanel();
		panelPreview.setBorder(new TitledBorder(null, "Vorschau",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelPreview = new GridBagConstraints();
		gbc_panelPreview.insets = new Insets(0, 0, 0, 5);
		gbc_panelPreview.fill = GridBagConstraints.BOTH;
		gbc_panelPreview.gridx = 0;
		gbc_panelPreview.gridy = 1;
		gbc_panelPreview.gridwidth = 3;
		panel.add(panelPreview, gbc_panelPreview);
		panelPreview.setLayout(new BorderLayout(0, 0));

	}

	@Override
	public void update(Observable o, Object arg) {
		comboBoxStartlistModel = new DefaultComboBoxModel(rankingModel.getCompetitions());
		comboBoxRankingModel = new DefaultComboBoxModel(rankingModel.getCompetitions());
	}

}
