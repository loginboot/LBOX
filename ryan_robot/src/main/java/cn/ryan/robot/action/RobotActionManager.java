package cn.ryan.robot.action;

import javafx.scene.control.Label;

public class RobotActionManager {

    public static void addMenuAction(Label... labels) {
        // 註冊菜單事件
        for (Label label : labels) {
            MenuAction ma = new MenuAction(label.getId());
            label.setOnMouseClicked(ma);
        }
    }

}
