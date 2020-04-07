package cn.ryan.robot.gui;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;

import cn.ryan.exec.RyanDBExec;
import cn.ryan.model.rb.RbRobot;
import cn.ryan.model.rb.RbRobotMode;
import cn.ryan.robot.Robot;
import cn.ryan.robot.RobotSetup;
import cn.ryan.robot.RobotSetupList;
import cn.ryan.robot.action.PageAction;
import cn.ryan.robot.utils.RobotUtil;
import cn.ryan.service.rb.RbRobotModeService;
import cn.ryan.utils.RyanLangUtil;
import cn.ryan.utils.RyanUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RobotSetupListCtrl implements Initializable {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RobotSetupListCtrl.class);

    @FXML
    private ComboBox<Integer> pgBox;

    @FXML
    private HBox pageBar;

    @FXML
    private Label firstPage;

    @FXML
    private Label prevPage;

    @FXML
    private Label nextPage;

    @FXML
    private Label lastPage;

    @FXML
    private TextField currPage;

    @FXML
    private TableView<RbRobotMode> robotModes = new TableView<RbRobotMode>();

    @FXML
    private TableColumn<RbRobotMode, Integer> rbid = new TableColumn<RbRobotMode, Integer>("序号");

    @FXML
    private TableColumn<RbRobotMode, String> modeName = new TableColumn<RbRobotMode, String>("模型名稱");

    @FXML
    private TableColumn<RbRobotMode, String> statusName = new TableColumn<RbRobotMode, String>("狀態");

    @FXML
    private TableColumn<RbRobotMode, String> mdesc = new TableColumn<RbRobotMode, String>("功能描述");

    // 數據對象
    private static ObservableList<RbRobotMode> rbDatas = FXCollections.observableArrayList();

    // 分頁工具欄操作
    private PageAction pageAction;
    // 默认为第1页
    private int page = 1;
    // 默认为10笔一页
    private int pageSize = 10;

    // 数据库操作类
    private RyanDBExec rdb = RyanDBExec.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //表格中的TableColumn与模型类中属性一一对应
        rbid.setCellValueFactory(new PropertyValueFactory<>("rbid"));
        modeName.setCellValueFactory(new PropertyValueFactory<>("modeName"));
        statusName.setCellValueFactory(new PropertyValueFactory<>("statusName"));
        mdesc.setCellValueFactory(new PropertyValueFactory<>("mdesc"));

        // 读取数据操作服务类
        RbRobotModeService rmservice = rdb.getService("rbRobotModeService", RbRobotModeService.class);
        Map<String, Object> search = new LinkedHashMap<String, Object>();
        search.put("page", page);
        search.put("pageSize", pageSize);
        Page<RbRobotMode> rrms = rmservice.findByPage(search);
        for (RbRobotMode rrm : rrms.getContent()) {
            rrm.setModeName(RyanLangUtil.getMsgByCode(rrm.getMode().getLabel()));
            rrm.setStatusName("Normal");
            rbDatas.add(rrm);
        }
        robotModes.setItems(rbDatas);
        // 初始化工具欄
        pageAction = new PageAction(pageBar, 35, page, pageSize);
    }

    public void chgFirstPage() {
        page = pageAction.firstPage();
    }

    public void chgPrevPage() {
        page = pageAction.prevPage();
    }

    public void chgNextPage() {
        page = pageAction.nextPage();
    }

    public void chgLastPage() {
        page = pageAction.lastPage();
    }

    public void chgCurrPage() {
        String tpg = currPage.getText();
        if (!RyanUtil.isEmpty(tpg) && RyanUtil.isNumber(tpg)) {
            int currPage = Integer.parseInt(tpg);
            page = pageAction.chgPage(currPage);
        } else {
            System.out.println("<>:" + tpg);
        }
    }

    public void onclickComboBox() {
        pageSize = pgBox.getSelectionModel().getSelectedItem();
        System.out.println(pageSize);
        // 重新计算分页Size
        page = pageAction.chagPageSize(pageSize);
    }

    public void addSetup() {
        // 隱藏當前的窗口
        RobotUtil.hideWindow(RobotSetupList.class);
        // 顯示列表窗口
        RobotUtil.showWindow(RobotSetup.class);
    }

    public void updSetup() {

    }

    public void viewSetup() {

    }

    public void delSetup() {

    }
}
