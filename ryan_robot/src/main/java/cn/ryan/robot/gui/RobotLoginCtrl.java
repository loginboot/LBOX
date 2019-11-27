package cn.ryan.robot.gui;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class RobotLoginCtrl {

	@FXML
	private Text actiontarget;

	@FXML
	protected void handleSubmitButtonAction() {
	    actiontarget.setText("Sign in button pressed");
		System.out.println("The button was clicked!");
	}
}
