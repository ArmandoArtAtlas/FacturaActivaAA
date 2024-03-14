/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlas.readerSql;

import com.atlas.factory.ConectorDB;
import com.atlas.oauth.OAuthConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author armandovaler
 */
public class ConectorMsSql implements ConectorDB {

    static Connection conector = null;

    @Override
    public Connection getConnection() {
        if (conector != null) {
            return conector;
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }

        try {
            conector = DriverManager.getConnection(OAuthConstants.MSSQL_ANN_DATABASE_URL, OAuthConstants.MSSQL_ANN_DATABASE_USER, OAuthConstants.MSSQL_ANN_DATABASE_PASS);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return conector;
    }

    @Override
    public ResultSet querySQL(String query) {
        ResultSet rs;
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            rs = stmt.getResultSet();
        } catch (Exception e) {
            System.out.println("ERROR EN QUERYSQL - NO EJECUTE: " + query);
            System.out.println(e.getMessage());
            System.out.println(e);
            rs = null;
        }
        return rs;

    }

    @Override
    public void queryInsertSQL(String query) {
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println("ERROR EN QUERYSQL - NO EJECUTE: " + query);
        }
    }

    @Override
    public void showData(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmeta = rs.getMetaData();
        int numColumnas = rsmeta.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= numColumnas; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public ArrayList<ArrayList<String>> getData(ResultSet rs) throws SQLException {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        ResultSetMetaData rsmeta = rs.getMetaData();

        int numColumnas = rsmeta.getColumnCount();

        while (rs.next()) {
            ArrayList<String> temp = new ArrayList<>();

            for (int i = 1; i <= numColumnas; i++) {
                temp.add(new String(rs.getString(i) + ""));
            }
            data.add(temp);
        }

        return data;
    }

    @Override
    public ArrayList<HashMap<String, String>> getDataDict(ResultSet rs) throws SQLException {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        ResultSetMetaData rsmeta = rs.getMetaData();

        int numColumnas = rsmeta.getColumnCount();

        while (rs.next()) {
            HashMap<String, String> temp = new HashMap<>();

            for (int i = 1; i <= numColumnas; i++) {

                if (rs.getString(i) != null) {
                    if (!rs.getString(i).toLowerCase().equals("null")) {

                        temp.put(rsmeta.getColumnName(i), rs.getString(i));
                    }
                }
            }
            data.add(temp);
        }
        return data;
    }

    @Override
    public void close() {
        try {
            conector.close();
            conector = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
