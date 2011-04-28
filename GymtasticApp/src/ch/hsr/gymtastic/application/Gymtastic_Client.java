package ch.hsr.gymtastic.application;

import ch.hsr.gymtastic.application.models.ClientModel;
import ch.hsr.gymtastic.presentation.client.ClientConnectionFrame;

public class Gymtastic_Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ClientConnectionFrame.newClientConnectionFrame(new ClientModel());

	}

}
