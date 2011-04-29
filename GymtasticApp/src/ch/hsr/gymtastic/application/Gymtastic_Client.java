package ch.hsr.gymtastic.application;

import javax.swing.UIManager;

import ch.hsr.gymtastic.application.models.ClientModel;
import ch.hsr.gymtastic.presentation.client.ClientConnectionFrame;

public class Gymtastic_Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		ClientConnectionFrame.newClientConnectionFrame(new ClientModel());

	}

}
