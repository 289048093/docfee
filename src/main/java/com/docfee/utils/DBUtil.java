package com.docfee.utils;

import com.docfee.utils.contant.Files;

import java.io.File;
import java.sql.*;

/**
 * User: Jarvis.Li(李朝)
 * Date: 14-3-21
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public class DBUtil {

    private static Connection con = null;

    static {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            System.out.println(Files.DB_DIR);
            con = DriverManager.getConnection("jdbc:hsqldb:file:" + Files.DB_DIR + "/docfeedb", "sa", "");
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                stmt.execute("SET WRITE_DELAY 0 MILLIS");
            } finally {
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon() {
        return con;
    }

    public static void exec(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        try {
            stmt.execute(sql);
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        }
    }

//    public static void query(String sql) throws SQLException {
//        Statement stmt = con.createStatement();
//        try {
//           ResultSet rs =  stmt.executeQuery(sql);
//        } finally {
//            if (stmt != null && !stmt.isClosed()) {
//                stmt.close();
//            }
//        }
//    }

    public static void main(String[] args) throws SQLException {
        exec("create table tb_test(id int,name character)");
    }

    public static void close(Statement stmt){
        try {
            if(stmt!=null && !stmt.isClosed()){
                stmt.close();
            }
        } catch (SQLException e) {
        }
    }
}
