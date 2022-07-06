package me.madcraft.madcraftwincounter.database;

import me.madcraft.madcraftwincounter.util.MadPlayer;

import java.sql.*;

public class SQLite implements Database {
    private static SQLite sqLite;
    private Connection connection;


    public SQLite() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + config.getString("sqlite.name") + ".db");
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS players(uuid CHAR(36) PRIMARY KEY,wins INT DEFAULT 0);");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static SQLite get() {
        if (sqLite == null) sqLite = new SQLite();
        return sqLite;
    }

    @Override
    public void insert(MadPlayer player) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT OR IGNORE INTO players(uuid) VALUES(?);");
            stmt.setString(1, player.getUuid().toString());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(MadPlayer player) {
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE OR IGNORE players SET wins =? WHERE uuid =?;");
            stmt.setInt(1, player.getWins());
            stmt.setString(2, player.getUuid().toString());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPlayer(MadPlayer player) {
        ResultSet rs = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players WHERE uuid =?;");
            preparedStatement.setString(1,player.getUuid().toString());
            rs = preparedStatement.executeQuery();
            player.setWins(rs.getInt(1));
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

}
