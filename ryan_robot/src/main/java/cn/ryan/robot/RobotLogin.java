package cn.ryan.robot;

import java.net.URL;

import cn.ryan.utils.RyanLangUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RobotLogin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 设置标题
        primaryStage.setTitle(RyanLangUtil.getMsgByCode("robot.login"));
        URL ur = getClass().getResource("/fxml/login.fxml");
        Pane lgn = FXMLLoader.load(ur, RyanLangUtil.getResBundle());
        Scene ms = new Scene(lgn, 400, 240);
        primaryStage.setScene(ms);
        primaryStage.setResizable(false);
        // 初始調用controller
        primaryStage.show();
        Robot.stgMap.put(RobotLogin.class.getSimpleName(), primaryStage);
    }

    public void show() {
        try {
            Stage st = new Stage();
            start(st);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
