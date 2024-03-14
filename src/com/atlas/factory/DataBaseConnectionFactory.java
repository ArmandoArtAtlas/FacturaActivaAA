/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlas.factory;

import com.atlas.readerSql.ConectorMsSql;
import com.atlas.readerSql.ConectorPostgres;
import com.atlas.readerSql.ConectorSql;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author armandovaler
 */
public class DataBaseConnectionFactory {

    private static final Map<DataBaseType, ConectorDB> connections = new HashMap<>();

    static {
        connections.put(DataBaseType.HanaSQL, new ConectorSql());
        connections.put(DataBaseType.PostgreSQL, new ConectorPostgres());
        connections.put(DataBaseType.MsSQL, new ConectorMsSql());

    }

    public static ConectorDB createConnection(DataBaseType dataBasetype) {

        return connections.get(dataBasetype);

    }

}
