package cn.ryan.robot.action;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PageAction {

    private HBox pgbox;

    private int page;

    private int pageSize;

    private int totalPage;

    private int total;

    public PageAction(HBox pgbox, int total, int page, int pageSize) {
        this.pgbox = pgbox;
        this.page = page;
        this.total = total;
        this.pageSize = pageSize;
        // 页数计算
        chgTotalPage();
    }

    public int chagPageSize(int pageSize) {
        this.pageSize = pageSize;
        // 页数计算
        chgTotalPage();
        return page;
    }

    public int chgPage(int page) {
        if (page > totalPage) {
            this.page = totalPage;
        } else if (page < 1) {
            this.page = 1;
        } else {
            this.page = page;
        }
        // 刷新
        reflashShow();
        return page;
    }

    public int chgTotal(int total) {
        this.total = total;
        // 页数计算
        chgTotalPage();
        return page;
    }

    public int nextPage() {
        if (page < totalPage) {
            page++;
        }
        // 刷新
        reflashShow();
        return page;
    }

    public int prevPage() {
        if (page > 1) {
            page--;
        }
        // 刷新
        reflashShow();
        return page;
    }

    public int firstPage() {
        page = 1;
        // 刷新
        reflashShow();
        return 1;
    }

    // 返回最后一頁
    public int lastPage() {
        page = totalPage;
        // 刷新
        reflashShow();
        return totalPage;
    }

    private void reflashShow() {
        // 取得所有讀取的參數
        ObservableList<Node> vnos = pgbox.getChildren();
        for (Node vo : vnos) {
            // ID
            String id = vo.getId();
            if (vo instanceof Label) {
                Label lab = (Label) vo;
                // 樣式更新
                ObservableList<String> sts = lab.getStyleClass();
                if (totalPage != 1) {
                    // 第一頁
                    if (page == 1) {
                        if ("firstPage".equals(id)) {
                            chgClassName(sts, "pg-last-left", "pgn-last-left");
                        } else if ("prevPage".equals(id)) {
                            chgClassName(sts, "pg-left", "pgn-left");
                        } else if ("nextPage".equals(id)) {
                            chgClassName(sts, "pgn-right", "pg-right");
                        } else if ("lastPage".equals(id)) {
                            chgClassName(sts, "pgn-last-right", "pg-last-right");
                        }
                        // 最后一頁
                    } else if (page == totalPage) {
                        if ("firstPage".equals(id)) {
                            chgClassName(sts, "pgn-last-left", "pg-last-left");
                        } else if ("prevPage".equals(id)) {
                            chgClassName(sts, "pgn-left", "pg-left");
                        } else if ("nextPage".equals(id)) {
                            chgClassName(sts, "pg-right", "pgn-right");
                        } else if ("lastPage".equals(id)) {
                            chgClassName(sts, "pg-last-right", "pgn-last-right");
                        }
                    } else {
                        // 其他頁面
                        if ("firstPage".equals(id)) {
                            chgClassName(sts, "pgn-last-left", "pg-last-left");
                        } else if ("prevPage".equals(id)) {
                            chgClassName(sts, "pgn-left", "pg-left");
                        } else if ("nextPage".equals(id)) {
                            chgClassName(sts, "pgn-right", "pg-right");
                        } else if ("lastPage".equals(id)) {
                            chgClassName(sts, "pgn-last-right", "pg-last-right");
                        }
                    }
                    // 總共只有一頁
                } else {
                    if ("firstPage".equals(id)) {
                        chgClassName(sts, "pg-last-left", "pgn-last-left");
                    } else if ("prevPage".equals(id)) {
                        chgClassName(sts, "pg-left", "pgn-left");
                    } else if ("nextPage".equals(id)) {
                        chgClassName(sts, "pg-right", "pgn-right");
                    } else if ("lastPage".equals(id)) {
                        chgClassName(sts, "pg-last-right", "pgn-last-right");
                    }
                }
            } else if (vo instanceof TextField) {
                if ("currPage".equals(id)) {
                    TextField tfx = (TextField) vo;
                    tfx.setText(String.valueOf(page));
                }
            } else if (vo instanceof Text) {
                if ("totalPage".equals(id)) {
                    Text tfx = (Text) vo;
                    tfx.setText(String.valueOf(totalPage));
                }
            }
        }
    }

    /**
     * 樣式更新
     * @param sts
     * @param bfn
     * @param afn
     */
    private void chgClassName(ObservableList<String> sts, String bfn, String afn) {
        if (sts.contains(bfn)) {
            sts.remove(bfn);
        }
        if (!sts.contains(afn)) {
            sts.add(afn);
        }
    }

    /**
     * 重新计算总页数据
     */
    private void chgTotalPage() {
        // 總頁數
        int pg = total / pageSize;
        if (total % pageSize != 0) {
            pg++;
        }
        this.totalPage = pg;
        this.page = 1; // 重新刷新回第一页
        // 刷新
        reflashShow();
    }
}
