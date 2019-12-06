package cn.ryan.exec;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RyanCodeTest {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RyanCodeTest.class);

    public static void main(String[] args) {
        ApplicationContext ctx = null;
        boolean isFile = false;
        File file = new File("./config/lyods-application.xml");
        if (file.exists()) {
            isFile = true;
        }
        if (isFile) {
            log.info("Start RM scan ...");
            String[] files = new String[] { "./config/lyods-application.xml", "./config/lyods-webctx.xml",
                    "./config/applicationContext-constant.xml" };
            ctx = new FileSystemXmlApplicationContext(files);
        } else {
            log.info("Start RM scan ...");
            String[] files = new String[] { "./ryan-application.xml" };
            ctx = new ClassPathXmlApplicationContext(files);
        }
        System.out.println("<>...ison");
        System.out.println(ctx);
        DataSource ds = ctx.getBean("dataSource", DataSource.class);
        try {
            System.out.println(ds.getConnection());
            Connection conn = ds.getConnection();
            String ts = "select * from T_RB_FILE_TYPE";
            PreparedStatement search = null;
            ResultSet rs = null;
            search = conn.prepareStatement(ts);
            rs = search.executeQuery();
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                Map<String, Object> row = new LinkedHashMap<String, Object>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String colName = rsmd.getColumnName(i);
                    String tmpName = colName.toUpperCase();
                    row.put(tmpName, rs.getObject(colName));
                }
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("over.");

    }

}
