package com.anson.mc.hbc.core.mysql;

import com.anson.mc.hbc.core.configs.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceManager {
    private static DataSource dataSource;
    private static DataSourceManager dataSourceManager;

    public DataSourceManager(){
        if (!ConfigManager.sql_enabled) return;
        HikariConfig hikariConfig = new HikariConfig();
        String host = ConfigManager.host;
        String database = ConfigManager.database;
        int port = ConfigManager.sql_port;
        int pool_min = ConfigManager.pool_min;
        int pool_max = ConfigManager.pool_max;
        boolean ssl = ConfigManager.use_ssl;
        String password = ConfigManager.password;
        String username = ConfigManager.username;
        String jdbc = "jdbc:mysql://"+host+":"+port+"/"+database+"?"+"useSSL="+ssl;
        hikariConfig.setJdbcUrl(jdbc);
        hikariConfig.setPoolName("HiByeCraft Pool");
        hikariConfig.setMaximumPoolSize(pool_max);
        hikariConfig.setMinimumIdle(pool_min);
        hikariConfig.setPassword(password);
        hikariConfig.setUsername(username);
        hikariConfig.addDataSourceProperty("cachePrepStmts",true);
        /*config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setAutoCommit(false);*/
        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("useServerPrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        //config.addDataSourceProperty("useUnicode",true);
        hikariConfig.addDataSourceProperty("characterEncoding","utf8");

        dataSource = new HikariDataSource(hikariConfig);
    }

    public static DataSource getDataSource() {
        if (!ConfigManager.sql_enabled) return null;
        return dataSource;
    }

    public static DataSourceManager getInstance() {
        if (dataSourceManager == null) dataSourceManager = new DataSourceManager();
        return dataSourceManager;
    }

    public Connection getConnection() throws SQLException {
        if (!ConfigManager.sql_enabled) {
            System.out.println("SQL is disabled!!!");
            return null;
        }
        return dataSource.getConnection();
    }
}
