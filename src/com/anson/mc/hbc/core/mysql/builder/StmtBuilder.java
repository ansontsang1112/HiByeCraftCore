package com.anson.mc.hbc.core.mysql.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class StmtBuilder {
    protected String table;

    public StmtBuilder(String table) {
        this.table = table;
    }

    public abstract PreparedStatement build(Connection connection) throws SQLException;
}
