package com.anson.mc.hbc.core.mysql.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class InsetStmtBuilder extends StmtBuilder{

    private Map<String, Object> columns = new LinkedHashMap<>();
    private Object[] values = new Object[0];
    private String primary;

    public InsetStmtBuilder(String table) {
        super(table);
    }

    public InsetStmtBuilder values(Object... objects){
        this.values = objects;
        return this;
    }

    public InsetStmtBuilder update(String col, Object val){
        columns.put(col,val);
        return this;
    }

    public InsetStmtBuilder primary(String key){
        this.primary = key;
        return this;
    }

    @Override
    public PreparedStatement build(Connection connection) throws SQLException {
        StringBuilder stmt = new StringBuilder("INSERT INTO `"+table+"` ");
        if (this.values.length < 1){
            Set<String> keys = columns.keySet();
            String[] strs = new String[keys.size()];
            Arrays.fill(strs,"?");
            stmt.append("(").append(String.join(", ", keys)).append(") VALUES (").append(String.join(", ", strs)).append(") ");
        }else{
            String[] strs = new String[values.length];
            Arrays.fill(strs,"?");
            stmt.append("VALUES (").append(String.join(", ",strs)).append(") ");
        }

        stmt.append("ON DUPLICATE KEY UPDATE ");
        List<String> update = columns.keySet().stream().filter(key->!key.equals(primary)).collect(Collectors.toList());
        for (int i = 0; i < update.size(); i++) {
            String key = update.get(i);
            stmt.append("`").append(key).append("`=?");
            if (i != update.size()-1){
                stmt.append(". ");
            }
        }
        PreparedStatement statement = connection.prepareStatement(stmt.toString());
        int i = 0;
        if (this.values.length < 1){
            List<String> keys = new LinkedList<>(columns.keySet());
            for (; i < keys.size(); i++) {
                statement.setObject(i+1,columns.get(keys.get(i)));
            }
        }else{
            for (; i < values.length; i++) {
                statement.setObject(i+1,values[i]);
            }
        }
        for (int j = i; i < update.size()+j; i++) {
            String key = update.get(i);
            statement.setObject(i+1,columns.get(key));
        }
        return statement;
    }
}
