package cn.ryan.robot.action;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RobotActionManager {

	public static void addMenuAction(ListView<Label> mlist) {
		// 註冊菜單事件
		MenuAction ma = new MenuAction(mlist);
		mlist.setOnMouseClicked(ma);
	}

}
