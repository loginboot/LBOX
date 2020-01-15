package cn.ryan.robot.action;

import java.util.Map;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class SetupAction implements EventHandler<Event> {

    private Map<String, Object> ops;

    public SetupAction(Map<String, Object> ops) {
        this.ops = ops;
    }

    @Override
    public void handle(Event event) {
        // 所有操作事件
        String type = (String) ops.get("TYPE");
        if ("DEL".equals(type)) {
            VBox vbd = (VBox) ops.get("MPOX");
            String idx = (String) ops.get("IDX");
            // 刪除整行數據
            ObservableList<Node> vnos = vbd.getChildren();
            Node dn = null;
            for (Node nd : vnos) {
                if (nd.getId().equals(idx)) {
                    // 刪除
                    dn = nd;
                }
            }
            // 執行刪除
            if (dn != null) {
                vnos.remove(dn);
            }
        }
    }

}
