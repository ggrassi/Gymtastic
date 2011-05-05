package ch.hsr.gymtastic.server.application;

import javax.swing.UIManager;

import ch.hsr.gymtastic.server.application.controller.GymCupController;
import ch.hsr.gymtastic.server.presentation.frames.ServerFrame;

public class Gymtastic_Server {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// ServerFrame.emulateCup();
		// ch.hsr.gymtastic.presentation.ServerFrame.newServerFrame();
//		try {
//			CupManagementModel cupManagementModel = new CupManagementModel();
//			CompetitionModel competitionModel = new CompetitionModel();
//			RankingModel rankingModel = new RankingModel();
//			AthleteModel athleteModel = new AthleteModel();
//			ModelController modelController = new ModelController(
//					cupManagementModel, competitionModel, rankingModel,
//					athleteModel);
//			modelController.setModelControllerToModels();
//			ServerFrame serverFrame = new ServerFrame(
//					new NetworkServerController(), cupManagementModel,
//					competitionModel, rankingModel, athleteModel);
//		} catch (ConnectException e) {
//			e.printStackTrace();
//		}
		new ServerFrame(new GymCupController());

	}

}
