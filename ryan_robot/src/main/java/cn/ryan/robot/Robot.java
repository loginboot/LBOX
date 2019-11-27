package cn.ryan.robot;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Robot extends Application {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(Robot.class);

    // 拖动时下标参数
    private double x1;
    private double y1;
    private double x_stage;
    private double y_stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FXML Example");
        URL ur = getClass().getResource("/fxml/main.fxml");
        Pane mp = (Pane) FXMLLoader.load(ur);
        Scene ms = new Scene(mp, 320, 300);
        //将鼠标改成小手 
        ms.setCursor(Cursor.CLOSED_HAND);
        // 注册整个窗口拖动事件
        addMoveEvent(primaryStage, ms);
        // 设置背景为空
        ms.setFill(null);
        // 设置整个窗口透明并无标题
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(ms);
        primaryStage.show();
    }

    /**
     * 添加移动事件
     * @param scene
     */
    private void addMoveEvent(final Stage primaryStage, Scene scene) {
        // 添加事件
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent m) {
                // 计算
                primaryStage.setX(x_stage + m.getScreenX() - x1);
                primaryStage.setY(y_stage + m.getScreenY() - y1);
            }
        });
        scene.setOnDragEntered(null);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent m) {
                // 按下鼠标后，记录当前鼠标的坐标
                x1 = m.getScreenX();
                y1 = m.getScreenY();
                x_stage = primaryStage.getX();
                y_stage = primaryStage.getY();
            }
        });
    }

}
