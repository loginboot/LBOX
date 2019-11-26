package cn.ryan.robot;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Robot extends Application {

	/**
	 * 日志
	 */
	private static Logger log = LogManager.getLogger(Robot.class);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("FXML Example");
		URL ur = getClass().getResource("/fxml/login.fxml");
		Pane myPane = (Pane)FXMLLoader.load(ur);
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();
	}

}
