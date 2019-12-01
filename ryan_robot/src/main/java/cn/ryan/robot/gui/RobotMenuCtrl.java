package cn.ryan.robot.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import cn.ryan.robot.Robot;
import cn.ryan.robot.action.RobotActionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class RobotMenuCtrl implements Initializable {

	@FXML
	private VBox mbox;

	/**
	 * 初始化实例时加载处理
	 */
	public void initialize(URL location, ResourceBundle resources) {
		// 将此Controller添加到容器中
		Robot.ctrsMap.put(this.getClass().getSimpleName(), this);
		System.out.println("load...");
	}

	/**
	 * 用户登录
	 * 
	 * @param uname
	 * @param pwd
	 * @return
	 */
	public boolean login(String uname, String pwd) {

		return false;
	}

	/**
	 * 用户退出
	 */
	public void logout() {
		// 清除数据库登录Session

		// 更新menu list显示为未登录

	}

	/**
	 * 菜单刷新
	 * 
	 * @param labs
	 */
	public void reflashMenuList(List<Label> labs) {
		ListView<Label> mlist = new ListView<Label>();
		mlist.getStyleClass().add("win-menu-list");
		// 先清除内容
		double hsize = 30 * 3 + 2;
		if (labs == null) {
			// 未登录时添加按钮信息
			Label login = new Label("Login");
			login.setId("LOGIN");
			Label exit = new Label("Exit");
			exit.setId("EXIT");
			Label dfd = new Label("UPDATE");
			dfd.setId("DTD");
			// 註冊事件
			RobotActionManager.addMenuAction(mlist);
			// 添加内容
			mlist.getItems().addAll(login, exit, dfd);
			System.out.println("aasd.");
		} else {
			// 已经登录
			hsize = labs.size() * 30 + 2;
			mlist.getItems().addAll(labs);
		}
		mlist.setPrefHeight(hsize);
		mbox.getChildren().clear();
		mbox.getChildren().add(mlist);
	}
}
