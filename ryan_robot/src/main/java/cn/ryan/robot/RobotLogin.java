package cn.ryan.robot;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RobotLogin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login Example");
        URL ur = getClass().getResource("/fxml/login.fxml");
        Pane lgn = FXMLLoader.load(ur);

        Scene ms = new Scene(lgn, 320, 300);
        primaryStage.setScene(ms);

        // 初始調用controller

        primaryStage.show();
    }

    public void show() throws Exception {
        Stage st = new Stage();
        start(st);
    }
}
