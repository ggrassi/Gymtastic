package ch.hsr.gymtastic.application;

import java.net.ConnectException;

import ch.hsr.gymtastic.application.controller.server.ModelController;
import ch.hsr.gymtastic.application.controller.server.NetworkServerController;
import ch.hsr.gymtastic.application.models.CompetitionModel;
import ch.hsr.gymtastic.application.models.CupManagementModel;
import ch.hsr.gymtastic.presentation.server.ServerFrame;

public class Gymtastic_Server {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// ServerFrame.emulateCup();
	// ch.hsr.gymtastic.presentation.ServerFrame.newServerFrame();
	try {
	    CupManagementModel cupManagementModel = new CupManagementModel();
	    CompetitionModel competitionModel = new CompetitionModel();
	    ModelController modelController = new ModelController(cupManagementModel, competitionModel);
	    modelController.setModelControllerToModels();
	    ServerFrame serverFrame = new ServerFrame(new NetworkServerController(), cupManagementModel, competitionModel);
	} catch (ConnectException e) {
	    e.printStackTrace();
	}

    }

}
