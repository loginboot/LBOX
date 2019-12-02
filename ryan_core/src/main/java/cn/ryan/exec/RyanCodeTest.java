package cn.ryan.exec;

import java.io.File;

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
        System.out.println(ds);
        System.out.println("over.");

    }

}
