package ch.hsr.gymtastic.client.application;

import javax.swing.UIManager;

import ch.hsr.gymtastic.client.application.controller.NetworkClientController;
import ch.hsr.gymtastic.client.application.controller.SquadController;
import ch.hsr.gymtastic.client.presentation.frames.ClientConnectionFrame;

public class Gymtastic_Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		ClientConnectionFrame.newClientConnectionFrame(new SquadController(), new NetworkClientController());

	}

}
