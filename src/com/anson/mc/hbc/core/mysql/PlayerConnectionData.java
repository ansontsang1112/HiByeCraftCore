package com.anson.mc.hbc.core.mysql;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PlayerConnectionData {
    private String playername;
    private String ip;
    private LocalDateTime lastLogin;
    private LocalDateTime lastLogout;

    public PlayerConnectionData(String playername, String ip, long lastLogin, long lastLogout) {
        this.playername = playername;
        this.ip = ip;
        this.lastLogin = new Timestamp(lastLogin).toLocalDateTime();
        this.lastLogout = new Timestamp(lastLogout).toLocalDateTime();
    }

    public String getPlayername() {
        return playername;
    }

    public String getIp() {
        return ip;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public LocalDateTime getLastLogout() {
        return lastLogout;
    }
}
