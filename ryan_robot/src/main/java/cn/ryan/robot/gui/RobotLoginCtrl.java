package cn.ryan.robot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RobotLoginCtrl {

	@FXML
	private TextField textField;

	@FXML
	protected void doSomething() {
		System.out.println("The button was clicked!"+textField.getText());
	}
}
