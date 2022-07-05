package me.madcraft.madcraftwincounter.database;

import me.madcraft.madcraftwincounter.util.MadPlayer;

import java.sql.*;

public class MySQL implements Database{
    private static MySQL mySQL;
    private Connection connection;


    public MySQL() {
        String user = config.getString("mysql.user");
        String pass = config.getString("mysql.pass");
        String name = config.getString("mysql.name");
        String ip = config.getString("mysql.ip");
        int port = config.getInt("mysql.port");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + name, user, pass);
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS players(uuid CHAR(36) PRIMARY KEY,wins INT DEFAULT 0);");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MySQL get() {
        if (mySQL == null) mySQL = new MySQL();
        return mySQL;
    }

    @Override
    public void insert(MadPlayer player) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT OR IGNORE INTO players(uuid) VALUES (?);");
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
            PreparedStatement stmt = connection.prepareStatement("UPDATE OR IGNORE players SET wins = ? WHERE uuid = ?;");
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
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM players WHERE uuid = ?");
            preparedStatement.setString(1,player.getUuid().toString());
            ResultSet rs = preparedStatement.executeQuery();
            player.setWins(rs.getInt("wins"));
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
