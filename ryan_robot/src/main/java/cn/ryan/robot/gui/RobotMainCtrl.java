package cn.ryan.robot.gui;

import java.net.URL;
import java.util.ResourceBundle;

import cn.ryan.robot.Robot;
import cn.ryan.robot.action.MenuAction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Window;

public class RobotMainCtrl implements Initializable {

    @FXML
    private VBox blueFlame;

    @FXML
    private Text chgTitle;

    private Popup pop;

    public void initialize(URL location, ResourceBundle resources) {
        //blueFlame.setCursor(Cursor.HAND);
        System.out.println("set cursor successful.");
        //将此Controller添加到容器中
        Robot.ctrsMap.put(this.getClass().getSimpleName(), this);

        // 增加popup內容
        try {
            Text txt = new Text("Login");

            txt.setOnMouseClicked(new MenuAction("aas"));
            Text txt2 = new Text("aasd2");
            Text txt3 = new Text("aasd3");
            VBox vb = new VBox();
            vb.getChildren().add(txt);
            vb.getChildren().add(txt2);
            vb.getChildren().add(txt3);
            vb.getStyleClass().add("main-win-menu-pop");
            URL ur = getClass().getResource("/css/robot.css");
            vb.getStylesheets().add(ur.toExternalForm());

            pop = new Popup();
            pop.getContent().add(vb);
            pop.setAutoHide(true);
            pop.setAutoFix(false);
            pop.setHideOnEscape(true);
            pop.setConsumeAutoHidingEvents(false);
            //pop.getStyleClass().add(DEFAULT_STYLE_CLASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onShowMenu() {
        System.out.println("clicked.");
        if (!pop.isShowing()) {
            System.out.println("is very show.");
            if (blueFlame.getScene() == null || blueFlame.getScene().getWindow() == null) {
                throw new IllegalStateException("Can not show popup. The node must be attached to a scene/window.");
            }
            Window parent = blueFlame.getScene().getWindow();
            final Point2D origin = blueFlame.localToScene(0, 0);
            final double anchorX = parent.getX() + origin.getX() + blueFlame.getScene().getX();
            final double anchorY = parent.getY() + origin.getY() + blueFlame.getScene().getY();
            pop.show(parent, anchorX + 25, anchorY + 38);
        }
    }
}
