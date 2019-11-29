package cn.ryan.robot.gui;

import java.net.URL;
import java.util.ResourceBundle;

import cn.ryan.robot.Robot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
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
            URL ur = getClass().getResource("/fxml/menu.fxml");
            Pane pm = FXMLLoader.load(ur);
            pop = new Popup();
            pop.getContent().add(pm);
            pop.setAutoHide(true);
            pop.setAutoFix(false);
            pop.setHideOnEscape(true);
            pop.setConsumeAutoHidingEvents(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示菜单界面
     */
    public void onShowMenu() {
        System.out.println("clicked.");
        if (!pop.isShowing()) {
            System.out.println("is very show.");
            // 刷新菜单
            RobotMenuCtrl rmenu = (RobotMenuCtrl) Robot.ctrsMap.get(RobotMenuCtrl.class.getSimpleName());
            if (rmenu != null) {
                rmenu.reflashMenuList(null);
            }
            // Popup窗口显示处理
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

    public void hidePop() {
        if (pop != null && pop.isShowing()) {
            pop.hide();
        }
    }
}
