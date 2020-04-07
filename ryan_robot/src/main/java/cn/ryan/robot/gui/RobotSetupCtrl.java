package cn.ryan.robot.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.ryan.exec.RyanDBExec;
import cn.ryan.model.rb.RbRobot;
import cn.ryan.model.rb.RbRobotMode;
import cn.ryan.model.rb.RbRobotParams;
import cn.ryan.robot.RobotSetup;
import cn.ryan.robot.RobotSetupList;
import cn.ryan.robot.action.RobotActionManager;
import cn.ryan.robot.utils.RobotUtil;
import cn.ryan.service.rb.RbRobotModeService;
import cn.ryan.utils.RyanLangUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RobotSetupCtrl implements Initializable {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RobotSetupCtrl.class);

    @FXML
    private ComboBox<String> mdBox;

    @FXML
    private ComboBox<String> statusBox;

    @FXML
    private TextArea mdesc;

    @FXML
    private VBox mpox;

    // 添加VBox面板序號
    private int mpidx = 1;

    // 数据库操作类
    private RyanDBExec rdb = RyanDBExec.getInstance();
    // 機器模式下標對位置
    private Map<Integer, String> mdMap = new LinkedHashMap<Integer, String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 读取数据操作服务类
        RbRobotModeService rmservice = rdb.getService("rbRobotModeService", RbRobotModeService.class);
        // 从数据库中直接读取机器所有模式
        List<RbRobot> rblst = rmservice.findAllRobots();
        // 循环封装下拉内容
        ObservableList<String> labs = FXCollections.observableArrayList();
        int mdIdx = 0;
        for (RbRobot rb : rblst) {
            labs.add(RyanLangUtil.getMsgByCode(rb.getLabel()));
            mdMap.put(mdIdx, rb.getMode());
            mdIdx++;
        }
        mdBox.setItems(labs);
        mdBox.getSelectionModel().select(0);

        // 封装状态下拉
        ObservableList<String> slst = FXCollections.observableArrayList();
        slst.add(RyanLangUtil.getMsgByCode("PUB.status_0"));
        slst.add(RyanLangUtil.getMsgByCode("PUB.status_1"));
        statusBox.setItems(slst);
        statusBox.getSelectionModel().select(0);
    }

    public void onclickMode() {
        int mdIdx = mdBox.getSelectionModel().getSelectedIndex();
        System.out.println("idx:" + mdIdx + "-->mode:" + mdMap.get(mdIdx));
    }

    public void addParamSet() {
        System.out.println("click...add.");
        // HBox一行數據面板
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setId(String.valueOf(mpidx));
        // 頁面佈局刪除按鈕
        Label pfxW = new Label();
        pfxW.getStyleClass().add("png-btn");
        pfxW.getStyleClass().add("png-btn-del");
        pfxW.setId("0");
        // 註冊事件
        Map<String, Object> ops = new LinkedHashMap<String, Object>();
        ops.put("TYPE", "DEL");
        ops.put("IDX", String.valueOf(mpidx));
        ops.put("MPOX", mpox);
        RobotActionManager.addSetupAction(pfxW, ops);
        // 參數名稱
        TextField rbName = new TextField();
        rbName.setId("rbName");
        // 參數值
        TextField rbValue = new TextField();
        rbValue.setId("rbValue");
        // 參數描述
        TextField rbDesc = new TextField();
        rbDesc.setId("rbDesc");
        // 添加面Hbox面板
        hb.getChildren().add(pfxW);
        hb.getChildren().add(rbName);
        hb.getChildren().add(rbValue);
        hb.getChildren().add(rbDesc);
        mpox.getChildren().add(hb);
        mpidx++;
    }

    /**
     * 參數保存
     */
    public void saveRbParams() {
        // 主對象
        RbRobotMode rrm = new RbRobotMode();
        // 封裝保存對象
        List<RbRobotParams> rps = new ArrayList<RbRobotParams>();
        // 取得所有讀取的參數
        ObservableList<Node> vnos = mpox.getChildren();
        for (Node nd : vnos) {
            if (nd instanceof HBox) {
                HBox hbs = (HBox) nd;
                ObservableList<Node> nps = hbs.getChildren();
                RbRobotParams rp = new RbRobotParams();
                for (Node np : nps) {
                    if (np instanceof TextField) {
                        TextField tfx = (TextField) np;
                        if ("rbName".equals(tfx.getId())) {
                            rp.setRpName(tfx.getText());
                        } else if ("rbValue".equals(tfx.getId())) {
                            rp.setRpValue(tfx.getText());
                        } else if ("rbDesc".equals(tfx.getId())) {
                            rp.setRpDesc(tfx.getText());
                        }
                        System.out.println(tfx.getId());
                    } else if (np instanceof Label) {
                        rp.setRpid(Integer.parseInt(np.getId()));
                    }
                }
                rps.add(rp);
            }
        }
        // 保存數據庫
        // 读取数据操作服务类
        RbRobotModeService rmservice = rdb.getService("rbRobotModeService", RbRobotModeService.class);
        rmservice.saveOrUpdate(rrm, rps);
        // 隱藏當前的窗口
        RobotUtil.hideWindow(RobotSetup.class);
        // 顯示列表窗口
        RobotUtil.showWindow(RobotSetupList.class);
        // 重新加载分页列表

        System.out.println("<>all pas:" + rps);
    }

    /**
     * 取消則返回之前的列表
     */
    public void cancelRbParams() {
        // 隱藏當前的窗口
        RobotUtil.hideWindow(RobotSetup.class);
        // 顯示列表窗口
        RobotUtil.showWindow(RobotSetupList.class);
    }

    public void onclickStatus() {

    }
}
