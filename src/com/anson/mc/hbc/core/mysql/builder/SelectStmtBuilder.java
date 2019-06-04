package com.anson.mc.hbc.core.mysql.builder;

import com.anson.mc.hbc.core.mysql.DataSourceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SelectStmtBuilder extends StmtBuilder{
    private String[] select = new String[0];
    private Map<String,Object> where = new HashMap<>();
    private boolean and = true;

    public SelectStmtBuilder(String table) {
        super(table);
    }

    public SelectStmtBuilder select(String... select){
        this.select = select;
        return this;
    }

    public SelectStmtBuilder where(String column, Object val){
        where.put(column,val);
        return this;
    }

    public SelectStmtBuilder and(boolean and){
        this.and = and;
        return this;
    }

    @Override
    public PreparedStatement build(Connection connection) throws SQLException {
        String condition = and ? " AND " : " OR ";
        StringBuilder where = new StringBuilder("WHERE ");
        List<String> columns = new LinkedList<>(this.where.keySet());
        for (int i = 0; i < columns.size(); i++) {
            String col = columns.get(i);
            where.append("`").append(col).append("`=?");
            if (i != columns.size() - 1){
                where.append(condition);
            }
        }
        String stmt = "SELECT "+String.join(", ",select)+" FROM `"+table+"` "+where.toString();
        PreparedStatement statement = connection.prepareStatement(stmt);
        for (int i = 0; i < columns.size(); i++) {
            Object obj = this.where.get(columns.get(i));
            statement.setObject(i+1,obj);
        }
        return statement;
    }
}