package views;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.border.TitledBorder;
import javax.swing.JList;

import network.RMIClientInterface;
import network.RMIServer;

public class ServerPrototype implements Observer{

    private JFrame frame;
    private RMIServer server;
    JList list;

    /**
     * Launch the application.
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
	initialize();
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
	gbl_panel.columnWidths = new int[]{0, 0};
	gbl_panel.rowHeights = new int[]{0, 0, 0};
	gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	panel.setLayout(gbl_panel);
	
	JPanel panel_2 = new JPanel();
	panel_2.setBorder(new TitledBorder(null, "Connected Clients", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
	gbc_panel_2.insets = new Insets(0, 0, 5, 0);
	gbc_panel_2.fill = GridBagConstraints.BOTH;
	gbc_panel_2.gridx = 0;
	gbc_panel_2.gridy = 0;
	panel.add(panel_2, gbc_panel_2);
	panel_2.setLayout(new BorderLayout(0, 0));
	
	list = new JList();
	panel_2.add(list, BorderLayout.CENTER);
	
	
	JPanel panel_1 = new JPanel();
	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	gbc_panel_1.fill = GridBagConstraints.BOTH;
	gbc_panel_1.gridx = 0;
	gbc_panel_1.gridy = 1;
	panel.add(panel_1, gbc_panel_1);
    }
    
    private void updateList(){
    DefaultListModel model = new DefaultListModel();
	for (RMIClientInterface c : server.getClient()) {
		model.addElement(c);
	}
	list.setModel(model);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
	updateList();
	
    }
}
