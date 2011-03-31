package views;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.border.TitledBorder;
import javax.swing.JList;

import network.RMIClientInterface;
import network.RMIServer;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;

import domain.Dummy;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ServerPrototype implements Observer {

    private JFrame frame;
    private RMIServer server;
    JList list;
    JList listDummy = new JList();

    /**
     * Launch the application.
     * 
     * @param server
     */
    public static void newServerFrame(final RMIServer server) {

	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    ServerPrototype window = new ServerPrototype(server);
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
    public ServerPrototype(RMIServer server) {
	this.server = server;
	server.addObserver(this);
	updateDummies();
	initialize();
    }

    private void updateDummies() {
	DefaultListModel model = new DefaultListModel();
	for (Dummy d : server.getDummies()) {
	    model.addElement(d);
	}
	
	listDummy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	listDummy.setModel(model);

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(new BorderLayout(0, 0));

	JPanel panel = new JPanel();
	frame.getContentPane().add(panel, BorderLayout.CENTER);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[] { 0, 0 };
	gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
	gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panel.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
	panel.setLayout(gbl_panel);

	JPanel panel_2 = new JPanel();
	panel_2.setBorder(new TitledBorder(null, "Connected Clients", TitledBorder.LEADING, TitledBorder.TOP, null,
		null));
	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	gbc_panel_2.insets = new Insets(0, 0, 5, 0);
	gbc_panel_2.fill = GridBagConstraints.BOTH;
	gbc_panel_2.gridx = 0;
	gbc_panel_2.gridy = 0;
	panel.add(panel_2, gbc_panel_2);
	panel_2.setLayout(new BorderLayout(0, 0));

	list = new JList();
	
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	panel_2.add(list, BorderLayout.CENTER);

	JPanel panel_1 = new JPanel();
	panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Dummies",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	gbc_panel_1.insets = new Insets(0, 0, 5, 0);
	gbc_panel_1.fill = GridBagConstraints.BOTH;
	gbc_panel_1.gridx = 0;
	gbc_panel_1.gridy = 1;
	panel.add(panel_1, gbc_panel_1);
	panel_1.setLayout(new BorderLayout(0, 0));

	panel_1.add(listDummy);

	JPanel panel_3 = new JPanel();
	GridBagConstraints gbc_panel_3 = new GridBagConstraints();
	gbc_panel_3.fill = GridBagConstraints.BOTH;
	gbc_panel_3.gridx = 0;
	gbc_panel_3.gridy = 2;
	panel.add(panel_3, gbc_panel_3);
	panel_3.setLayout(new BorderLayout(0, 0));

	final JButton btnNewButton = new JButton("send dummy to client");
	btnNewButton.setEnabled(false);
	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		RMIClientInterface stub = (RMIClientInterface) list.getSelectedValue();
		try {
		    stub.uploadSquadToClient((Dummy) listDummy.getSelectedValue());
		} catch (RemoteException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		server.getDummies().remove((Dummy)listDummy.getSelectedValue());
		updateDummies();
	    }
	});
	panel_3.add(btnNewButton, BorderLayout.EAST);
	
	listDummy.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent arg0) {
		    if(list.getSelectedValue() != null && listDummy.getSelectedValue() != null)
		    {
			btnNewButton.setEnabled(true);
		    }
		    else
		    {
			btnNewButton.setEnabled(false);
			
		    }
		}
	});
	
	list.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent arg0) {
		    if(listDummy.getSelectedValue() != null && list.getSelectedValue() != null)
		    {
			btnNewButton.setEnabled(true);
		    }
		    else
		    {
			btnNewButton.setEnabled(false);
			
		    }
		}
	});
	
	
    }

    private void updateList() {
	DefaultListModel model = new DefaultListModel();
	for (RMIClientInterface c : server.getClient()) {
	    model.addElement(c);
	}
	list.setModel(model);
	
    }

    @Override
    public void update(Observable arg0, Object arg1) {
	updateList();
	updateDummies();

    }
}
