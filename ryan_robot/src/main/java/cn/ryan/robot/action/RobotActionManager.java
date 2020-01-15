package cn.ryan.robot.action;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RobotActionManager {

    public static void addMenuAction(ListView<Label> mlist) {
        // 註冊菜單事件
        MenuAction ma = new MenuAction(mlist);
        mlist.setOnMouseClicked(ma);
    }

    public static void addSetupAction(Node nd, Map<String, Object> ops) {
        // 註冊事件
        SetupAction sa = new SetupAction(ops);
        // 添加操作對象
        nd.setOnMouseClicked(sa);
    }

}
