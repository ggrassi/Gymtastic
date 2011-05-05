package ch.hsr.gymtastic.client.presentation.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ch.hsr.gymtastic.client.application.controller.CompetitionInfoController;
import ch.hsr.gymtastic.client.application.controller.GymCupInfoController;
import ch.hsr.gymtastic.client.application.controller.SquadController;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.GymCupClientInfo;
import ch.hsr.gymtastic.technicalServices.utils.DateFormatConverter;

public class OverviewPanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelOverview;
	private JPanel panelCupInformation;
	private JLabel lblCupNameText;
	private JLabel lblCupName;
	private JLabel lblCupLocationText;
	private JLabel lblCupLocation;
	private JLabel lblStartDateText;
	private JLabel lblStartDate;
	private JLabel lblEndDateText;
	private JLabel lblEndDate;
	private JPanel panelCompetitionInformation;
	private JLabel lblActualCompetition;
	private JLabel lblActualCompetitionText;
	private JLabel lblActualRound;
	private JLabel lblActualRoundText;
	private JLabel lblActualSquad;
	private JLabel lblActualSquadText;
	private JLabel lblDeviceText;
	private JLabel lblDevice;
	private JPanel panelControl;
	private JButton btnStartRound;
	private GymCupInfoController gymCupInfoController;
	private CompetitionInfoController competitionInfoController;
	private SquadController squadController;
	private JLabel lblRoundInfo;
	private DeviceType deviceType;

	public OverviewPanel(GymCupInfoController gymCupInfoController,
			CompetitionInfoController competitionInfoController,
			SquadController squadController, DeviceType deviceType) {
		this.gymCupInfoController = gymCupInfoController;
		this.competitionInfoController = competitionInfoController;
		this.squadController = squadController;
		this.deviceType = deviceType;
		this.gymCupInfoController.addObserver(this);
		this.competitionInfoController.addObserver(this);
		this.squadController.addObserver(this);
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
		gbl_panelCupInformation.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		panelCupInformation.setLayout(gbl_panelCupInformation);

		lblCupNameText = new JLabel("Veranstaltungsname:");
		GridBagConstraints gbc_lblCupNameText = new GridBagConstraints();
		gbc_lblCupNameText.anchor = GridBagConstraints.WEST;
		gbc_lblCupNameText.insets = new Insets(0, 0, 5, 5);
		gbc_lblCupNameText.gridx = 0;
		gbc_lblCupNameText.gridy = 0;
		panelCupInformation.add(lblCupNameText, gbc_lblCupNameText);

		lblCupName = new JLabel("");
		GridBagConstraints gbc_lblCupName = new GridBagConstraints();
		gbc_lblCupName.anchor = GridBagConstraints.WEST;
		gbc_lblCupName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCupName.gridx = 1;
		gbc_lblCupName.gridy = 0;
		panelCupInformation.add(lblCupName, gbc_lblCupName);

		lblCupLocationText = new JLabel("Veranstaltungsort:");
		GridBagConstraints gbc_lblCupLocationText = new GridBagConstraints();
		gbc_lblCupLocationText.anchor = GridBagConstraints.WEST;
		gbc_lblCupLocationText.insets = new Insets(0, 0, 5, 5);
		gbc_lblCupLocationText.gridx = 0;
		gbc_lblCupLocationText.gridy = 1;
		panelCupInformation.add(lblCupLocationText, gbc_lblCupLocationText);

		lblCupLocation = new JLabel("");
		GridBagConstraints gbc_lblCupLocation = new GridBagConstraints();
		gbc_lblCupLocation.anchor = GridBagConstraints.WEST;
		gbc_lblCupLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblCupLocation.gridx = 1;
		gbc_lblCupLocation.gridy = 1;
		panelCupInformation.add(lblCupLocation, gbc_lblCupLocation);

		lblStartDateText = new JLabel("Startdatum:");
		GridBagConstraints gbc_lblStartDateText = new GridBagConstraints();
		gbc_lblStartDateText.anchor = GridBagConstraints.WEST;
		gbc_lblStartDateText.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDateText.gridx = 0;
		gbc_lblStartDateText.gridy = 2;
		panelCupInformation.add(lblStartDateText, gbc_lblStartDateText);

		lblStartDate = new JLabel("");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.anchor = GridBagConstraints.WEST;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 1;
		gbc_lblStartDate.gridy = 2;
		panelCupInformation.add(lblStartDate, gbc_lblStartDate);

		lblEndDateText = new JLabel("Enddatum:");
		GridBagConstraints gbc_lblEndDateText = new GridBagConstraints();
		gbc_lblEndDateText.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDateText.anchor = GridBagConstraints.WEST;
		gbc_lblEndDateText.gridx = 0;
		gbc_lblEndDateText.gridy = 3;
		panelCupInformation.add(lblEndDateText, gbc_lblEndDateText);

		lblEndDate = new JLabel("");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.anchor = GridBagConstraints.WEST;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 1;
		gbc_lblEndDate.gridy = 3;
		panelCupInformation.add(lblEndDate, gbc_lblEndDate);

		lblDeviceText = new JLabel("Ger\u00E4t:");
		GridBagConstraints gbc_lblDeviceText = new GridBagConstraints();
		gbc_lblDeviceText.anchor = GridBagConstraints.WEST;
		gbc_lblDeviceText.insets = new Insets(0, 0, 0, 5);
		gbc_lblDeviceText.gridx = 0;
		gbc_lblDeviceText.gridy = 5;
		panelCupInformation.add(lblDeviceText, gbc_lblDeviceText);

		lblDevice = new JLabel("" + deviceType);
		GridBagConstraints gbc_lblDevice = new GridBagConstraints();
		gbc_lblDevice.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblDevice.insets = new Insets(0, 0, 0, 5);
		gbc_lblDevice.gridx = 1;
		gbc_lblDevice.gridy = 5;
		panelCupInformation.add(lblDevice, gbc_lblDevice);

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
		gbl_panelCompetitionInformation.rowHeights = new int[] { 0, 0, 0, 0, 0,
				0 };
		gbl_panelCompetitionInformation.columnWeights = new double[] { 0.0,
				0.0, Double.MIN_VALUE };
		gbl_panelCompetitionInformation.rowWeights = new double[] { 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCompetitionInformation.setLayout(gbl_panelCompetitionInformation);

		lblActualCompetition = new JLabel("Wettkampf:");
		GridBagConstraints gbc_lblActualCup = new GridBagConstraints();
		gbc_lblActualCup.anchor = GridBagConstraints.WEST;
		gbc_lblActualCup.insets = new Insets(0, 0, 5, 5);
		gbc_lblActualCup.gridx = 0;
		gbc_lblActualCup.gridy = 0;
		panelCompetitionInformation.add(lblActualCompetition, gbc_lblActualCup);

		lblActualCompetitionText = new JLabel("");
		GridBagConstraints gbc_lblActualCupText = new GridBagConstraints();
		gbc_lblActualCupText.anchor = GridBagConstraints.WEST;
		gbc_lblActualCupText.insets = new Insets(0, 0, 5, 0);
		gbc_lblActualCupText.gridx = 1;
		gbc_lblActualCupText.gridy = 0;
		panelCompetitionInformation.add(lblActualCompetitionText,
				gbc_lblActualCupText);

		lblActualRound = new JLabel("Durchgang:");
		GridBagConstraints gbc_lblActualCompetition = new GridBagConstraints();
		gbc_lblActualCompetition.anchor = GridBagConstraints.WEST;
		gbc_lblActualCompetition.insets = new Insets(0, 0, 5, 5);
		gbc_lblActualCompetition.gridx = 0;
		gbc_lblActualCompetition.gridy = 1;
		panelCompetitionInformation.add(lblActualRound,
				gbc_lblActualCompetition);

		lblActualRoundText = new JLabel("");
		GridBagConstraints gbc_lblActualCompetitionText = new GridBagConstraints();
		gbc_lblActualCompetitionText.anchor = GridBagConstraints.WEST;
		gbc_lblActualCompetitionText.insets = new Insets(0, 0, 5, 0);
		gbc_lblActualCompetitionText.gridx = 1;
		gbc_lblActualCompetitionText.gridy = 1;
		panelCompetitionInformation.add(lblActualRoundText,
				gbc_lblActualCompetitionText);

		lblActualSquad = new JLabel("Riege:");
		GridBagConstraints gbc_lblActualSquad = new GridBagConstraints();
		gbc_lblActualSquad.anchor = GridBagConstraints.WEST;
		gbc_lblActualSquad.insets = new Insets(0, 0, 5, 5);
		gbc_lblActualSquad.gridx = 0;
		gbc_lblActualSquad.gridy = 2;
		panelCompetitionInformation.add(lblActualSquad, gbc_lblActualSquad);

		lblActualSquadText = new JLabel("");
		GridBagConstraints gbc_lblActualSquadText = new GridBagConstraints();
		gbc_lblActualSquadText.insets = new Insets(0, 0, 5, 0);
		gbc_lblActualSquadText.anchor = GridBagConstraints.WEST;
		gbc_lblActualSquadText.gridx = 1;
		gbc_lblActualSquadText.gridy = 2;
		panelCompetitionInformation.add(lblActualSquadText,
				gbc_lblActualSquadText);

		lblRoundInfo = new JLabel("STATUS INFO");
		GridBagConstraints gbc_lblRoundInfo = new GridBagConstraints();
		gbc_lblRoundInfo.insets = new Insets(0, 0, 0, 5);
		gbc_lblRoundInfo.gridx = 0;
		gbc_lblRoundInfo.gridy = 4;
		panelCompetitionInformation.add(lblRoundInfo, gbc_lblRoundInfo);

		panelControl = new JPanel();
		GridBagConstraints gbc_panelControl = new GridBagConstraints();
		gbc_panelControl.anchor = GridBagConstraints.SOUTH;
		gbc_panelControl.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelControl.gridx = 0;
		gbc_panelControl.gridy = 2;
		panelOverview.add(panelControl, gbc_panelControl);
		panelControl.setLayout(new BorderLayout(0, 0));

		btnStartRound = new JButton("Durchgang starten");
		btnStartRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelControl.add(btnStartRound, BorderLayout.EAST);

	}

	private void initListeners() {
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GymCupInfoController) {
			setGymCupInfos();
		} else if (o instanceof CompetitionInfoController) {
			setCompetitionInfos();
		} else if (o instanceof SquadController) {
			setRoundInfos();

		}
	}

	private void setRoundInfos() {
		lblActualRoundText.setText("" + squadController.getRoundNr());
		lblActualSquadText
				.setText("" + squadController.getSquad().getSquadId());

	}

	private void setCompetitionInfos() {
		lblActualCompetitionText.setText(competitionInfoController
				.getCompetitionInfo().getCompetitionName());

	}

	private void setGymCupInfos() {
		GymCupClientInfo gymCupClientInfo = gymCupInfoController
				.getGymCupClientInfo();
		lblCupName.setText(gymCupClientInfo.getName());
		lblCupLocation.setText(gymCupClientInfo.getLocation());
		lblDevice.setText(gymCupClientInfo.getDeviceType().toString());
		lblStartDate.setText(DateFormatConverter
				.convertDateToString(gymCupClientInfo.getStartDate()));
		lblEndDate.setText(DateFormatConverter
				.convertDateToString(gymCupClientInfo.getEndDate()));
	}

}
