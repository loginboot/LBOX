package cn.ryan.robot;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RobotSetup extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Add Example");
        URL ur = getClass().getResource("/fxml/setup.fxml");
        Pane lgn = FXMLLoader.load(ur);

        Scene ms = new Scene(lgn, 680, 300);
        primaryStage.setScene(ms);

        // 初始調用controller
        primaryStage.show();
        Robot.stgMap.put(RobotSetup.class.getSimpleName(), primaryStage);
    }

    public void show() {
        try {
            Stage st = new Stage();
            start(st);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
