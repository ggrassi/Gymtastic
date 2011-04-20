package ch.hsr.gymtastic.presentation.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OverviewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelOverview;
	private JPanel panelCupInformation;
	private JLabel lblCupName;
	private JLabel lblCupNameText;
	private JLabel lblCupLocation;
	private JLabel lblCupLocationText;
	private JLabel lblStartDate;
	private JLabel lblStartDateText;
	private JLabel lblEndDate;
	private JLabel lblEndDateText;
	private JPanel panelCompetitionInformation;
	private JLabel lblActualCup;
	private JLabel lblActualCupText;
	private JLabel lblActualCompetition;
	private JLabel lblActualCompetitionText;
	private JLabel lblActualSquad;
	private JLabel lblActualSquadText;
	private JLabel lblDevice;
	private JLabel lblDeviceText;
	private JPanel panelControl;
	private JButton btnNewButton;

	/**
     * 
     */

	public OverviewPanel() {
		initGUI();
		initListeners();

	}

	private void initGUI() {
		setLayout(new BorderLayout(0, 0));

		panelOverview = new JPanel();
		add(panelOverview);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelOverview.setLayout(gbl_panel);

		panelCupInformation = new JPanel();
		panelCupInformation.setBorder(new TitledBorder(null,
				"Turncup Informationen", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelCupInformation = new GridBagConstraints();
		gbc_panelCupInformation.insets = new Insets(0, 0, 5, 0);
		gbc_panelCupInformation.fill = GridBagConstraints.BOTH;
		gbc_panelCupInformation.gridx = 0;
		gbc_panelCupInformation.gridy = 0;
		panelOverview.add(panelCupInformation, gbc_panelCupInformation);
		GridBagLayout gbl_panelCupInformation = new GridBagLayout();
		gbl_panelCupInformation.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelCupInformation.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCupInformation.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelCupInformation.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelCupInformation.setLayout(gbl_panelCupInformation);

		lblCupName = new JLabel("Veranstaltungsname:");
		GridBagConstraints gbc_lblCupName = new GridBagConstraints();
		gbc_lblCupName.anchor = GridBagConstraints.WEST;
		gbc_lblCupName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCupName.gridx = 0;
		gbc_lblCupName.gridy = 0;
		panelCupInformation.add(lblCupName, gbc_lblCupName);

		lblCupNameText = new JLabel("TV Rheintal");
		GridBagConstraints gbc_lblCupNameText = new GridBagConstraints();
		gbc_lblCupNameText.anchor = GridBagConstraints.WEST;
		gbc_lblCupNameText.insets = new Insets(0, 0, 5, 5);
		gbc_lblCupNameText.gridx = 1;
		gbc_lblCupNameText.gridy = 0;
		panelCupInformation.add(lblCupNameText, gbc_lblCupNameText);

		lblCupLocation = new JLabel("Veranstaltungsort:");
		GridBagConstraints gbc_lblCupLocation = new GridBagConstraints();
		gbc_lblCupLocation.anchor = GridBagConstraints.WEST;
		gbc_lblCupLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblCupLocation.gridx = 0;
		gbc_lblCupLocation.gridy = 1;
		panelCupInformation.add(lblCupLocation, gbc_lblCupLocation);

		lblCupLocationText = new JLabel("St. Gallen");
		GridBagConstraints gbc_lblCupLocationText = new GridBagConstraints();
		gbc_lblCupLocationText.anchor = GridBagConstraints.WEST;
		gbc_lblCupLocationText.insets = new Insets(0, 0, 5, 5);
		gbc_lblCupLocationText.gridx = 1;
		gbc_lblCupLocationText.gridy = 1;
		panelCupInformation.add(lblCupLocationText, gbc_lblCupLocationText);

		lblStartDate = new JLabel("Startdatum:");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.anchor = GridBagConstraints.WEST;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 0;
		gbc_lblStartDate.gridy = 2;
		panelCupInformation.add(lblStartDate, gbc_lblStartDate);

		lblStartDateText = new JLabel("11.11.2011");
		GridBagConstraints gbc_lblStartDateText = new GridBagConstraints();
		gbc_lblStartDateText.anchor = GridBagConstraints.WEST;
		gbc_lblStartDateText.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDateText.gridx = 1;
		gbc_lblStartDateText.gridy = 2;
		panelCupInformation.add(lblStartDateText, gbc_lblStartDateText);

		lblEndDate = new JLabel("Enddatum:");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.anchor = GridBagConstraints.WEST;
		gbc_lblEndDate.gridx = 0;
		gbc_lblEndDate.gridy = 3;
		panelCupInformation.add(lblEndDate, gbc_lblEndDate);

		lblEndDateText = new JLabel("12.11.2011");
		GridBagConstraints gbc_lblEndDateText = new GridBagConstraints();
		gbc_lblEndDateText.anchor = GridBagConstraints.WEST;
		gbc_lblEndDateText.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDateText.gridx = 1;
		gbc_lblEndDateText.gridy = 3;
		panelCupInformation.add(lblEndDateText, gbc_lblEndDateText);
		
		lblDevice = new JLabel("Ger\u00E4t:");
		GridBagConstraints gbc_lblDevice = new GridBagConstraints();
		gbc_lblDevice.anchor = GridBagConstraints.WEST;
		gbc_lblDevice.insets = new Insets(0, 0, 0, 5);
		gbc_lblDevice.gridx = 0;
		gbc_lblDevice.gridy = 5;
		panelCupInformation.add(lblDevice, gbc_lblDevice);
		
		lblDeviceText = new JLabel("Barren");
		GridBagConstraints gbc_lblDeviceText = new GridBagConstraints();
		gbc_lblDeviceText.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDeviceText.insets = new Insets(0, 0, 0, 5);
		gbc_lblDeviceText.gridx = 1;
		gbc_lblDeviceText.gridy = 5;
		panelCupInformation.add(lblDeviceText, gbc_lblDeviceText);

		panelCompetitionInformation = new JPanel();
		panelCompetitionInformation.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Wettkampf Informationen", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelCompetitionInformation = new GridBagConstraints();
		gbc_panelCompetitionInformation.insets = new Insets(0, 0, 5, 0);
		gbc_panelCompetitionInformation.fill = GridBagConstraints.BOTH;
		gbc_panelCompetitionInformation.gridx = 0;
		gbc_panelCompetitionInformation.gridy = 1;
		panelOverview.add(panelCompetitionInformation,
				gbc_panelCompetitionInformation);
		GridBagLayout gbl_panelCompetitionInformation = new GridBagLayout();
		gbl_panelCompetitionInformation.columnWidths = new int[] { 0, 0, 0 };
		gbl_panelCompetitionInformation.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panelCompetitionInformation.columnWeights = new double[] { 0.0,
				0.0, Double.MIN_VALUE };
		gbl_panelCompetitionInformation.rowWeights = new double[] { 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCompetitionInformation.setLayout(gbl_panelCompetitionInformation);

		lblActualCup = new JLabel("Wettkampf:");
		GridBagConstraints gbc_lblActualCup = new GridBagConstraints();
		gbc_lblActualCup.anchor = GridBagConstraints.WEST;
		gbc_lblActualCup.insets = new Insets(0, 0, 5, 5);
		gbc_lblActualCup.gridx = 0;
		gbc_lblActualCup.gridy = 0;
		panelCompetitionInformation.add(lblActualCup, gbc_lblActualCup);

		lblActualCupText = new JLabel("Wettkampf 1");
		GridBagConstraints gbc_lblActualCupText = new GridBagConstraints();
		gbc_lblActualCupText.anchor = GridBagConstraints.WEST;
		gbc_lblActualCupText.insets = new Insets(0, 0, 5, 0);
		gbc_lblActualCupText.gridx = 1;
		gbc_lblActualCupText.gridy = 0;
		panelCompetitionInformation.add(lblActualCupText, gbc_lblActualCupText);

		lblActualCompetition = new JLabel("Durchgang:");
		GridBagConstraints gbc_lblActualCompetition = new GridBagConstraints();
		gbc_lblActualCompetition.anchor = GridBagConstraints.WEST;
		gbc_lblActualCompetition.insets = new Insets(0, 0, 5, 5);
		gbc_lblActualCompetition.gridx = 0;
		gbc_lblActualCompetition.gridy = 1;
		panelCompetitionInformation.add(lblActualCompetition,
				gbc_lblActualCompetition);

		lblActualCompetitionText = new JLabel("Durchgang 2");
		GridBagConstraints gbc_lblActualCompetitionText = new GridBagConstraints();
		gbc_lblActualCompetitionText.anchor = GridBagConstraints.WEST;
		gbc_lblActualCompetitionText.insets = new Insets(0, 0, 5, 0);
		gbc_lblActualCompetitionText.gridx = 1;
		gbc_lblActualCompetitionText.gridy = 1;
		panelCompetitionInformation.add(lblActualCompetitionText,
				gbc_lblActualCompetitionText);

		lblActualSquad = new JLabel("Riege:");
		GridBagConstraints gbc_lblActualSquad = new GridBagConstraints();
		gbc_lblActualSquad.anchor = GridBagConstraints.WEST;
		gbc_lblActualSquad.insets = new Insets(0, 0, 5, 5);
		gbc_lblActualSquad.gridx = 0;
		gbc_lblActualSquad.gridy = 2;
		panelCompetitionInformation.add(lblActualSquad, gbc_lblActualSquad);

		lblActualSquadText = new JLabel("Riege 4");
		GridBagConstraints gbc_lblActualSquadText = new GridBagConstraints();
		gbc_lblActualSquadText.insets = new Insets(0, 0, 5, 0);
		gbc_lblActualSquadText.anchor = GridBagConstraints.WEST;
		gbc_lblActualSquadText.gridx = 1;
		gbc_lblActualSquadText.gridy = 2;
		panelCompetitionInformation.add(lblActualSquadText,
				gbc_lblActualSquadText);
		
		panelControl = new JPanel();
		GridBagConstraints gbc_panelControl = new GridBagConstraints();
		gbc_panelControl.anchor = GridBagConstraints.SOUTH;
		gbc_panelControl.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelControl.gridx = 0;
		gbc_panelControl.gridy = 2;
		panelOverview.add(panelControl, gbc_panelControl);
		panelControl.setLayout(new BorderLayout(0, 0));
		
		btnNewButton = new JButton("Durchgang starten");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelControl.add(btnNewButton, BorderLayout.EAST);

	}

	private void initListeners() {
	}

}
