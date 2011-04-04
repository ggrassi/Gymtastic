package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class Server {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void newServerFrame() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Server window = new Server();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public Server() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 638, 474);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	
	JPanel panelGymCup = new JPanel();
	tabbedPane.addTab("Cupverwaltung", null, panelGymCup, null);
	
	JPanel panelCompetition = new JPanel();
	tabbedPane.addTab("Wettkampfverwaltung", null, panelCompetition, null);
	
	JPanel panelAthlete = new JPanel();
	tabbedPane.addTab("Athleten", null, panelAthlete, null);
	
	JPanel panelJudge = new JPanel();
	tabbedPane.addTab("Kampfrichter", null, panelJudge, null);
	
	JPanel panelDeviceType = new JPanel();
	tabbedPane.addTab("Ger\u00E4tezuweisung", null, panelDeviceType, null);
	
	JPanel RoundAllocatoin = new JPanel();
	tabbedPane.addTab("Durchgangssteuerung", null, RoundAllocatoin, null);
	GridBagLayout gbl_RoundAllocatoin = new GridBagLayout();
	gbl_RoundAllocatoin.columnWidths = new int[]{0, 0};
	gbl_RoundAllocatoin.rowHeights = new int[]{0, 0, 0, 0};
	gbl_RoundAllocatoin.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_RoundAllocatoin.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	RoundAllocatoin.setLayout(gbl_RoundAllocatoin);
	
	JPanel panelCompetitionControlBorder = new JPanel();
	panelCompetitionControlBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Aktueller Wettkampf", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelCompetitionControlBorder = new GridBagConstraints();
	gbc_panelCompetitionControlBorder.anchor = GridBagConstraints.NORTH;
	gbc_panelCompetitionControlBorder.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelCompetitionControlBorder.insets = new Insets(0, 0, 5, 0);
	gbc_panelCompetitionControlBorder.gridx = 0;
	gbc_panelCompetitionControlBorder.gridy = 0;
	RoundAllocatoin.add(panelCompetitionControlBorder, gbc_panelCompetitionControlBorder);
	GridBagLayout gbl_panelCompetitionControlBorder = new GridBagLayout();
	gbl_panelCompetitionControlBorder.columnWidths = new int[]{0, 0};
	gbl_panelCompetitionControlBorder.rowHeights = new int[]{0, 0};
	gbl_panelCompetitionControlBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_panelCompetitionControlBorder.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panelCompetitionControlBorder.setLayout(gbl_panelCompetitionControlBorder);
	
	JPanel panelCompetitionControl = new JPanel();
	GridBagConstraints gbc_panelCompetitionControl = new GridBagConstraints();
	gbc_panelCompetitionControl.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelCompetitionControl.gridx = 0;
	gbc_panelCompetitionControl.gridy = 0;
	panelCompetitionControlBorder.add(panelCompetitionControl, gbc_panelCompetitionControl);
	GridBagLayout gbl_panelCompetitionControl = new GridBagLayout();
	gbl_panelCompetitionControl.columnWidths = new int[]{0, 0, 0, 0, 0};
	gbl_panelCompetitionControl.rowHeights = new int[]{0, 0};
	gbl_panelCompetitionControl.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panelCompetitionControl.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panelCompetitionControl.setLayout(gbl_panelCompetitionControl);
	
	JLabel lblCompetition = new JLabel("Wettkampf: ");
	GridBagConstraints gbc_lblCompetition = new GridBagConstraints();
	gbc_lblCompetition.anchor = GridBagConstraints.EAST;
	gbc_lblCompetition.insets = new Insets(0, 0, 0, 5);
	gbc_lblCompetition.gridx = 0;
	gbc_lblCompetition.gridy = 0;
	panelCompetitionControl.add(lblCompetition, gbc_lblCompetition);
	
	JComboBox cmbCompetition = new JComboBox();
	GridBagConstraints gbc_cmbCompetition = new GridBagConstraints();
	gbc_cmbCompetition.fill = GridBagConstraints.HORIZONTAL;
	gbc_cmbCompetition.insets = new Insets(0, 0, 0, 5);
	gbc_cmbCompetition.gridx = 1;
	gbc_cmbCompetition.gridy = 0;
	panelCompetitionControl.add(cmbCompetition, gbc_cmbCompetition);
	
	JButton btnStartCompetition = new JButton("Wettkampf Starten");
	GridBagConstraints gbc_btnStartCompetition = new GridBagConstraints();
	gbc_btnStartCompetition.insets = new Insets(0, 0, 0, 5);
	gbc_btnStartCompetition.gridx = 2;
	gbc_btnStartCompetition.gridy = 0;
	panelCompetitionControl.add(btnStartCompetition, gbc_btnStartCompetition);
	
	JButton btnStopCompetition = new JButton("Wettkampf anhalten");
	GridBagConstraints gbc_btnStopCompetition = new GridBagConstraints();
	gbc_btnStopCompetition.gridx = 3;
	gbc_btnStopCompetition.gridy = 0;
	panelCompetitionControl.add(btnStopCompetition, gbc_btnStopCompetition);
	
	JPanel panelClientStatusBorder = new JPanel();
	panelClientStatusBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ger\u00E4testatus", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelClientStatusBorder = new GridBagConstraints();
	gbc_panelClientStatusBorder.fill = GridBagConstraints.BOTH;
	gbc_panelClientStatusBorder.insets = new Insets(0, 0, 5, 0);
	gbc_panelClientStatusBorder.gridx = 0;
	gbc_panelClientStatusBorder.gridy = 1;
	RoundAllocatoin.add(panelClientStatusBorder, gbc_panelClientStatusBorder);
	GridBagLayout gbl_panelClientStatusBorder = new GridBagLayout();
	gbl_panelClientStatusBorder.columnWidths = new int[]{0, 0};
	gbl_panelClientStatusBorder.rowHeights = new int[]{0, 0};
	gbl_panelClientStatusBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_panelClientStatusBorder.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	panelClientStatusBorder.setLayout(gbl_panelClientStatusBorder);
	
	JPanel panelClientStatus = new JPanel();
	GridBagConstraints gbc_panelClientStatus = new GridBagConstraints();
	gbc_panelClientStatus.fill = GridBagConstraints.BOTH;
	gbc_panelClientStatus.gridx = 0;
	gbc_panelClientStatus.gridy = 0;
	panelClientStatusBorder.add(panelClientStatus, gbc_panelClientStatus);
	GridBagLayout gbl_panelClientStatus = new GridBagLayout();
	gbl_panelClientStatus.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
	gbl_panelClientStatus.rowHeights = new int[]{0, 0, 0, 0};
	gbl_panelClientStatus.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panelClientStatus.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	panelClientStatus.setLayout(gbl_panelClientStatus);
	
	JLabel lblFloor = new JLabel("Boden");
	GridBagConstraints gbc_lblFloor = new GridBagConstraints();
	gbc_lblFloor.anchor = GridBagConstraints.WEST;
	gbc_lblFloor.insets = new Insets(0, 0, 5, 5);
	gbc_lblFloor.gridx = 0;
	gbc_lblFloor.gridy = 0;
	panelClientStatus.add(lblFloor, gbc_lblFloor);
	
	JLabel lblFloorStatusText = new JLabel("Ger\u00E4tedurchgang nicht gestartet");
	GridBagConstraints gbc_lblFloorStatusText = new GridBagConstraints();
	gbc_lblFloorStatusText.anchor = GridBagConstraints.WEST;
	gbc_lblFloorStatusText.insets = new Insets(0, 0, 5, 5);
	gbc_lblFloorStatusText.gridx = 2;
	gbc_lblFloorStatusText.gridy = 0;
	panelClientStatus.add(lblFloorStatusText, gbc_lblFloorStatusText);
	
	JLabel lblVault = new JLabel("Sprung");
	GridBagConstraints gbc_lblVault = new GridBagConstraints();
	gbc_lblVault.anchor = GridBagConstraints.WEST;
	gbc_lblVault.insets = new Insets(0, 0, 5, 5);
	gbc_lblVault.gridx = 4;
	gbc_lblVault.gridy = 0;
	panelClientStatus.add(lblVault, gbc_lblVault);
	
	JLabel lblVaultStatusText = new JLabel("Ger\u00E4tedurchgang nicht gestartet");
	GridBagConstraints gbc_lblVaultStatusText = new GridBagConstraints();
	gbc_lblVaultStatusText.anchor = GridBagConstraints.WEST;
	gbc_lblVaultStatusText.insets = new Insets(0, 0, 5, 0);
	gbc_lblVaultStatusText.gridx = 6;
	gbc_lblVaultStatusText.gridy = 0;
	panelClientStatus.add(lblVaultStatusText, gbc_lblVaultStatusText);
	
	JLabel lblPommelHorse = new JLabel("Pferd");
	GridBagConstraints gbc_lblPommelHorse = new GridBagConstraints();
	gbc_lblPommelHorse.anchor = GridBagConstraints.WEST;
	gbc_lblPommelHorse.insets = new Insets(0, 0, 5, 5);
	gbc_lblPommelHorse.gridx = 0;
	gbc_lblPommelHorse.gridy = 1;
	panelClientStatus.add(lblPommelHorse, gbc_lblPommelHorse);
	
	JLabel lblPommelHorseStatusText = new JLabel("Ger\u00E4tedurchgang nicht gestartet");
	GridBagConstraints gbc_lblPommelHorseStatusText = new GridBagConstraints();
	gbc_lblPommelHorseStatusText.insets = new Insets(0, 0, 5, 5);
	gbc_lblPommelHorseStatusText.gridx = 2;
	gbc_lblPommelHorseStatusText.gridy = 1;
	panelClientStatus.add(lblPommelHorseStatusText, gbc_lblPommelHorseStatusText);
	
	JLabel lblParallelBars = new JLabel("Barren");
	GridBagConstraints gbc_lblParallelBars = new GridBagConstraints();
	gbc_lblParallelBars.anchor = GridBagConstraints.WEST;
	gbc_lblParallelBars.insets = new Insets(0, 0, 5, 5);
	gbc_lblParallelBars.gridx = 4;
	gbc_lblParallelBars.gridy = 1;
	panelClientStatus.add(lblParallelBars, gbc_lblParallelBars);
	
	JLabel lblParallelBarsStatusText = new JLabel("Ger\u00E4tedurchgang nicht gestartet");
	GridBagConstraints gbc_lblParallelBarsStatusText = new GridBagConstraints();
	gbc_lblParallelBarsStatusText.anchor = GridBagConstraints.WEST;
	gbc_lblParallelBarsStatusText.insets = new Insets(0, 0, 5, 0);
	gbc_lblParallelBarsStatusText.gridx = 6;
	gbc_lblParallelBarsStatusText.gridy = 1;
	panelClientStatus.add(lblParallelBarsStatusText, gbc_lblParallelBarsStatusText);
	
	JLabel lblRings = new JLabel("Ring");
	GridBagConstraints gbc_lblRings = new GridBagConstraints();
	gbc_lblRings.anchor = GridBagConstraints.WEST;
	gbc_lblRings.insets = new Insets(0, 0, 0, 5);
	gbc_lblRings.gridx = 0;
	gbc_lblRings.gridy = 2;
	panelClientStatus.add(lblRings, gbc_lblRings);
	
	JLabel lblRingsStatusText = new JLabel("Ger\u00E4tedurchgang nicht gestartet");
	GridBagConstraints gbc_lblRingsStatusText = new GridBagConstraints();
	gbc_lblRingsStatusText.insets = new Insets(0, 0, 0, 5);
	gbc_lblRingsStatusText.gridx = 2;
	gbc_lblRingsStatusText.gridy = 2;
	panelClientStatus.add(lblRingsStatusText, gbc_lblRingsStatusText);
	
	JLabel lblHighBar = new JLabel("Reck");
	GridBagConstraints gbc_lblHighBar = new GridBagConstraints();
	gbc_lblHighBar.anchor = GridBagConstraints.WEST;
	gbc_lblHighBar.insets = new Insets(0, 0, 0, 5);
	gbc_lblHighBar.gridx = 4;
	gbc_lblHighBar.gridy = 2;
	panelClientStatus.add(lblHighBar, gbc_lblHighBar);
	
	JLabel lblHighBarStatusText = new JLabel("Ger\u00E4tedurchgang nicht gestartet");
	GridBagConstraints gbc_lblHighBarStatusText = new GridBagConstraints();
	gbc_lblHighBarStatusText.anchor = GridBagConstraints.WEST;
	gbc_lblHighBarStatusText.gridx = 6;
	gbc_lblHighBarStatusText.gridy = 2;
	panelClientStatus.add(lblHighBarStatusText, gbc_lblHighBarStatusText);
	
	JPanel panelRoundControlBorder = new JPanel();
	panelRoundControlBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Steuerung", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panelRoundControlBorder = new GridBagConstraints();
	gbc_panelRoundControlBorder.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelRoundControlBorder.gridx = 0;
	gbc_panelRoundControlBorder.gridy = 2;
	RoundAllocatoin.add(panelRoundControlBorder, gbc_panelRoundControlBorder);
	GridBagLayout gbl_panelRoundControlBorder = new GridBagLayout();
	gbl_panelRoundControlBorder.columnWidths = new int[]{0, 0};
	gbl_panelRoundControlBorder.rowHeights = new int[]{0, 0};
	gbl_panelRoundControlBorder.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_panelRoundControlBorder.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	panelRoundControlBorder.setLayout(gbl_panelRoundControlBorder);
	
	JPanel panelRoundControl = new JPanel();
	GridBagConstraints gbc_panelRoundControl = new GridBagConstraints();
	gbc_panelRoundControl.fill = GridBagConstraints.HORIZONTAL;
	gbc_panelRoundControl.gridx = 0;
	gbc_panelRoundControl.gridy = 0;
	panelRoundControlBorder.add(panelRoundControl, gbc_panelRoundControl);
	GridBagLayout gbl_panelRoundControl = new GridBagLayout();
	gbl_panelRoundControl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
	gbl_panelRoundControl.rowHeights = new int[]{0, 0};
	gbl_panelRoundControl.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panelRoundControl.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	panelRoundControl.setLayout(gbl_panelRoundControl);
	
	JLabel labelRound = new JLabel("Durchgang");
	GridBagConstraints gbc_labelRound = new GridBagConstraints();
	gbc_labelRound.insets = new Insets(0, 0, 0, 5);
	gbc_labelRound.gridx = 0;
	gbc_labelRound.gridy = 0;
	panelRoundControl.add(labelRound, gbc_labelRound);
	
	JSpinner spnRound = new JSpinner();
	GridBagConstraints gbc_spnRound = new GridBagConstraints();
	gbc_spnRound.insets = new Insets(0, 0, 0, 5);
	gbc_spnRound.gridx = 1;
	gbc_spnRound.gridy = 0;
	panelRoundControl.add(spnRound, gbc_spnRound);
	
	JLabel lblDescrRound = new JLabel("von 6 Durchg\u00E4ngen");
	GridBagConstraints gbc_lblDescrRound = new GridBagConstraints();
	gbc_lblDescrRound.insets = new Insets(0, 0, 0, 5);
	gbc_lblDescrRound.gridx = 2;
	gbc_lblDescrRound.gridy = 0;
	panelRoundControl.add(lblDescrRound, gbc_lblDescrRound);
	
	JButton btnDurchgangFreigeben = new JButton("Durchgang Freigeben");
	GridBagConstraints gbc_btnDurchgangFreigeben = new GridBagConstraints();
	gbc_btnDurchgangFreigeben.insets = new Insets(0, 0, 0, 5);
	gbc_btnDurchgangFreigeben.gridx = 4;
	gbc_btnDurchgangFreigeben.gridy = 0;
	panelRoundControl.add(btnDurchgangFreigeben, gbc_btnDurchgangFreigeben);
	
	JPanel panelRanking = new JPanel();
	tabbedPane.addTab("Ranglistengenerierung", null, panelRanking, null);
	
	JPanel panelLogo = new JPanel();
	frame.getContentPane().add(panelLogo, BorderLayout.NORTH);
	
	JPanel panelStatus = new JPanel();
	frame.getContentPane().add(panelStatus, BorderLayout.SOUTH);
    }

}
