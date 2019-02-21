package com.anson.mc.hbc.core.mysql;

import com.anson.mc.hbc.core.configs.ConfigManager;
import com.anson.mc.hbc.core.main.HiByeCraftCore;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class PlayerDataManager {
    private static PlayerDataManager playerDataManager;
    private String table;

    private PlayerDataManager(){
        this.table = ConfigManager.table;
    }
    public static PlayerDataManager getInstance() {
        if (playerDataManager == null) playerDataManager = new PlayerDataManager();
        return playerDataManager;
    }

    public void createTable(){
        if (table == null) return;
        try(Connection connection = DataSourceManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `"+table+"` (`PlayerName` TINYTEXT NOT NULL, `PlayerUUID` VARCHAR(40) NOT NULL PRIMARY KEY, `IP` TINYTEXT NOT NULL, `LastLogin` BIGINT NOT NULL,`LastLogout` BIGINT NOT NULL)")){
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTable(Player player, String ip, long lastLogin){
        if (table == null) return;
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        HiByeCraftCore.plugin.getLogger().info("saving "+name+" login data...");
        long lastLogout = Timestamp.from(Instant.now()).getTime();
        try(Connection connection = DataSourceManager.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `"+table+"`VALUES(?,?,?,?,?) ON DUPLICATE KEY UPDATE `PlayerName`=?,`IP`=?,`LastLogin`=?,`LastLogout`=?")){
            statement.setString(1,name);
            statement.setString(2,uuid);
            statement.setString(3,ip);
            statement.setLong(4,lastLogin);
            statement.setLong(5,lastLogout);
            statement.setString(6,name);
            statement.setString(7,ip);
            statement.setLong(8,lastLogin);
            statement.setLong(9,lastLogout);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
