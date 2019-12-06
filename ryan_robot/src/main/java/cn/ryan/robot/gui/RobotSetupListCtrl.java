package cn.ryan.robot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class RobotSetupListCtrl {

    @FXML
    private ComboBox<Integer> pgBox;

    public void onclickComboBox() {
      Integer vs =  pgBox.getSelectionModel().getSelectedItem();
      System.out.println(vs);
    }

}
