package com.target11.database;

import javax.swing.*;
import java.sql.*;

public class DBHelper {

    private static final String DB_URL = "jdbc:sqlite:assistme.db";
    private static Connection conn = null;
    private static Statement stmt = null;

    static {
        createConnection();
        // setupLinkTB();
    }


    private static void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("inside of CerateConnection method ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cant load database", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }


    public static void setupLinkTB() {
        String TABLE_NAME = "L_LINK_TB";
        String CREATE_TABLE = "CREATE TABLE L_LINK_TB (L_LINK_ID INT PRIMARY KEY AUTOINCREMENT NOT NULL,L_NAME TEXT NOT NULL, L_PATH INT NOT NULL);";
        System.out.println(CREATE_TABLE);
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, TABLE_NAME, null);
            if (rs.next()) {
                System.out.println("Table is present ");
            } else {
                System.out.println("Table not found so creating new");
                stmt.execute(CREATE_TABLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insetLink() {

    }


    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        } else {
            createConnection();
            return conn;
        }
    }


    public static void close() {
        /*try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }*/
    }
}
