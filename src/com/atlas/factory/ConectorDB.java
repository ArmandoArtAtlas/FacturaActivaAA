/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlas.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author armandovaler
 */
public interface ConectorDB {
    
    public Connection getConnection();
    public ResultSet querySQL(String query);
    public void queryInsertSQL(String query);
    public void showData(ResultSet rs)throws SQLException;
    public ArrayList<ArrayList<String>> getData(ResultSet rs) throws SQLException;
    public ArrayList<HashMap<String, String>> getDataDict(ResultSet rs)throws SQLException;
    public void close();
    
}
