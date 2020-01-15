package cn.ryan.robot;

import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.ryan.utils.RyanLangUtil;
import cn.ryan.utils.RyanUtil;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author cn.ryan.robot
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-09
 * @description Ryan機器人圖形頁面加載啟動主類
 *
 */
public class Robot extends Application {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(Robot.class);

    //这里是Robot创建一个Controller容器
    public static Map<String, Object> ctrsMap = new HashMap<String, Object>();
    // 用於刷新
    public static Map<String, Stage> stgMap = new HashMap<String, Stage>();

    // 拖动时下标参数
    private double x1;
    private double y1;
    private double x_stage;
    private double y_stage;

    /**
     * Robot啟動入口
     * @param args
     */
    public static void main(String[] args) {
        String lang = "zh_CN";
        // 讀取參數
        for (String arg : args) {
            String[] arr = arg.split("=", 2);
            String val = RyanUtil.trim(arr[1]);
            String op = RyanUtil.trim(arr[0]);
            if (op.equalsIgnoreCase("-LANG")) {
                lang = val;
            }
        }

        //啟動程序時，默認設置語言
        if ("zh_CN".equalsIgnoreCase(lang)) {
            Locale.setDefault(Locale.CHINA); //指定为中文
        } else {
            Locale.setDefault(Locale.US); //默認為英文
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("Begin start robot.");
        // 設置標題
        primaryStage.setTitle(RyanLangUtil.getMsgByCode("robot.title"));
        URL ur = getClass().getResource("/fxml/main.fxml");
        Pane mp = FXMLLoader.load(ur, RyanLangUtil.getResBundle());
        Scene ms = new Scene(mp, 320, 300);
        // 注册整个窗口拖动事件
        addMoveEvent(primaryStage, ms);
        // 设置背景为空
        ms.getRoot().setStyle("-fx-background-color: transparent");
        ms.setFill(null);
        // 设置整个窗口透明并无标题
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(ms);
        // 初始調用controller
        primaryStage.show();
        stgMap.put(Robot.class.getSimpleName(), primaryStage);
        log.info("Started robot successful.");
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
