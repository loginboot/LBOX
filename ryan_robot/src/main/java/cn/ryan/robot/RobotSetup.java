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

/**
 * 
 * @author cn.ryan.robot
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-09
 * @description Ryan機器人參數修改或新增界面
 *
 */
public class RobotSetup extends Application {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RobotSetup.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 设置图标
        try {
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo_title.png")));
        } catch (Exception e) {
            log.error("Load title logo png file error:", e);
        }
        primaryStage.setTitle(RyanLangUtil.getMsgByCode("robot.param"));
        URL ur = getClass().getResource("/fxml/setup.fxml");
        Pane lgn = FXMLLoader.load(ur, RyanLangUtil.getResBundle());

        Scene ms = new Scene(lgn, 780, 500);
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
