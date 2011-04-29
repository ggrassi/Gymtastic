package ch.hsr.gymtastic.application;

import java.net.ConnectException;

import javax.swing.UIManager;

import ch.hsr.gymtastic.application.controller.server.NetworkServerController;
import ch.hsr.gymtastic.application.models.CompetitionModel;
import ch.hsr.gymtastic.application.models.CupManagementModel;
import ch.hsr.gymtastic.presentation.server.ServerFrame;

public class Gymtastic_Server {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// ServerFrame.emulateCup();
		// ch.hsr.gymtastic.presentation.ServerFrame.newServerFrame();
		try {
			ServerFrame serverFrame = new ServerFrame(
					new NetworkServerController(), new CupManagementModel(),
					new CompetitionModel());
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
