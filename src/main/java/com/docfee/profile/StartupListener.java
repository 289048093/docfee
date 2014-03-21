package com.docfee.profile;

import com.docfee.utils.DBUtil;
import com.docfee.utils.FileUtil;
import com.docfee.utils.LogUtil;
import com.docfee.utils.contant.Files;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User: Sed.Li(李朝)
 * Date: 14-3-21
 * Time: 下午12:45
 */
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.setProperty("web.root", FileUtil.getClassPathFilePath(""));
        FileUtil.mkDir(Files.LOG_DIR.value);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Statement stmt = null;
                try {
                    stmt = DBUtil.getCon().createStatement();
                    stmt.execute("shutdown");
                } catch (SQLException e) {
                    LogUtil.error(e);
                } finally {
                    try {
                        if (stmt != null && !stmt.isClosed()) {
                            stmt.close();
                        }
                    } catch (SQLException e) {
                    }
                }

            }
        });
//        try {
//            DBUtil.exec("create table tb_test(id int)");
//        } catch (SQLException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
