package cn.ryan.robot;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.ryan.utils.RyanLangUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RobotLogin extends Application {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RobotLogin.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 设置图标
        try {
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo_title.png")));
        } catch (Exception e) {
            log.error("Load title logo png file error:", e);
        }
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
