package ch.hsr.gymtastic.client.presentation.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ch.hsr.gymtastic.client.application.controller.NetworkClientController;
import ch.hsr.gymtastic.client.presentation.frames.ClientFrame;
import ch.hsr.gymtastic.client.presentation.models.AthleteOverviewTableModel;
import ch.hsr.gymtastic.domain.DeviceType;
import ch.hsr.gymtastic.domain.Squad;
import ch.hsr.gymtastic.technicalServices.network.exceptions.TransmissionException;

/**
 * The Class ActualSquadPanel shows the GUI with all Informations about the Squad.
 */
public class ActualSquadPanel extends JPanel {
	
	private static final long serialVersionUID = 9138092871453323277L;
	private JTable tableOverview;
	private JPanel panelAthletesTable;
	private JPanel panelAthletes;
	private JScrollPane scrollPane;
	private AthleteOverviewTableModel tableOverviewModel;
	private Squad actualSquad;
	private DeviceType deviceType;
	private JButton btnFinishEvaluation;
	private JPanel panelAthleteControl;
	private NetworkClientController networkController;
	private final ClientFrame clientFrame;


	/**
	 * Instantiates a new actual squad panel.
	 *
	 * @param actualSquad the actual squad
	 * @param deviceType the device type
	 * @param networkController the network controller
	 * @param clientFrame the client frame
	 */
	public ActualSquadPanel(Squad actualSquad, DeviceType deviceType, NetworkClientController networkController, ClientFrame clientFrame) {
		
		this.actualSquad = actualSquad;
		this.deviceType = deviceType;
		this.networkController = networkController;
		this.clientFrame = clientFrame;
		initGUI();
		initListeners();
	}

	/**
	 * Inits the listeners.
	 */
	private void initListeners() {
		btnFinishEvaluation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					networkController.sendObjectToServer(actualSquad);
					clientFrame.endRound();
				} catch (TransmissionException e) {
					JOptionPane.showMessageDialog(clientFrame.getFrame(), "Das Senden zum Server ist fehlgeschlagen.",
						    "Verbindungsfehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Initialize the contents of the Panel
	 */
	private void initGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		tableOverviewModel = new AthleteOverviewTableModel(actualSquad,
				deviceType);

		panelAthletesTable = new JPanel();
		panelAthletesTable.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Athleten",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		GridBagConstraints gbc_panelAthletesTable = new GridBagConstraints();
		gbc_panelAthletesTable.fill = GridBagConstraints.BOTH;
		gbc_panelAthletesTable.gridx = 0;
		gbc_panelAthletesTable.gridy = 0;
		add(panelAthletesTable, gbc_panelAthletesTable);
		panelAthletesTable.setLayout(new BorderLayout(0, 0));

		panelAthletes = new JPanel();
		panelAthletesTable.add(panelAthletes);
		GridBagLayout gbl_panelAthletes = new GridBagLayout();
		gbl_panelAthletes.columnWidths = new int[] { 0, 0 };
		gbl_panelAthletes.rowHeights = new int[] { 0, 0 };
		gbl_panelAthletes.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelAthletes.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelAthletes.setLayout(gbl_panelAthletes);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panelAthletes.add(scrollPane, gbc_scrollPane);

		tableOverview = new JTable();
		tableOverview.setModel(tableOverviewModel);
		scrollPane.setViewportView(tableOverview);

		panelAthleteControl = new JPanel();
		panelAthletesTable.add(panelAthleteControl, BorderLayout.SOUTH);
		panelAthleteControl.setLayout(new BorderLayout(0, 0));
		
				btnFinishEvaluation = new JButton("Bewertung abschliessen");
				panelAthleteControl.add(btnFinishEvaluation, BorderLayout.EAST);
	}

}
