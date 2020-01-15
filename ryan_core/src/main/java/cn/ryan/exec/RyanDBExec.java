package cn.ryan.exec;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.ryan.security.RyanAuthToken;

public class RyanDBExec {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RyanDBExec.class);

    private static RyanDBExec rdb;

    private ApplicationContext ctx;

    public static void main(String[] args) {
        RyanDBExec rdb = RyanDBExec.getInstance();
        rdb.login("admin", "admin");
    }

    private RyanDBExec() {
        load();
    }

    private void load() {
        boolean isFile = false;
        File file = new File("./config/ryan-application.xml");
        if (file.exists()) {
            isFile = true;
        }
        log.info("Loading Ryan DB action");
        if (isFile) {
            String[] files = new String[] { "./config/ryan-application.xml", "./config/ryan-webctx.xml",
                    "./config/ryan-constant.xml" };
            ctx = new FileSystemXmlApplicationContext(files);
        } else {
            String[] files = new String[] { "./ryan-application.xml", "./security/ryan-shiro.xml" };
            ctx = new ClassPathXmlApplicationContext(files);
            System.out.println(ctx);
        }
    }

    public static synchronized RyanDBExec getInstance() {
        if (rdb == null) {
            rdb = new RyanDBExec();
        }
        return rdb;
    }

    public ApplicationContext getCtx() {
        return ctx;
    }

    /**
     * 通过服务类型名取得service实现类
     * @param <T>
     * @param sname
     * @param requiredType
     * @return
     */
    public <T> T getService(String sname, Class<T> requiredType) {
        return ctx.getBean(sname, requiredType);
    }

    /**
     * 用户登录
     * @param uname
     * @param pwd
     * @return
     */
    public Subject login(String uname, String pwd) {
        // 从CTX中取得Shiro Manager管理器
        SecurityManager securityManager = ctx.getBean("securityManager", SecurityManager.class);
        SecurityUtils.setSecurityManager(securityManager);
        // 构建登录对象
        RyanAuthToken ratoken = new RyanAuthToken(uname, pwd);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(ratoken);
            System.out.println("We've authenticated! :)");
        } catch (AuthenticationException e) {
            System.out.println("We did not authenticate :(");
            e.printStackTrace();
        }

        return currentUser;
    }
}
