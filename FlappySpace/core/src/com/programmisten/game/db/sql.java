package com.programmisten.game.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sql extends Thread{

    private boolean connectionInfo;

    public void run(){ // wird als 2. Instanz parallel ausgeführt
        while (true) {
            test();
        }
    }

    public void test(){ //prüft ob eine DB Verbindung möglich ist und schreibt true oder false in "connectionInfo"
        Connection conn;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.137.1:3306/flappySpace", "user2", "123456789");
            conn.close();
            connectionInfo = true;
        } catch (SQLException e) {
            connectionInfo = false;
        }
    }

    public String[]  extraxtNames() { // extrahiert die Namen hinter den besten 5 Scores aus der DB und übergibt diese in einem Array
        PreparedStatement stmt;
        ResultSet rs;
        Connection conn;
        String[] result = new String[5];
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.137.1:3306/flappySpace", "user2", "123456789");

            String extract = "SELECT * FROM spieler Order by score DESC;";

            stmt = conn.prepareStatement(extract);
            rs = stmt.executeQuery();
            for(int i = 0; i<5; i++){
                rs.next();
                result[i] = rs.getString("spieler");
            }

            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int[]  extraxtScores() { // extrahiert die besten 5 Scores aus der DB und übergibt diese in einem Array
        PreparedStatement stmt;
        ResultSet rs;
        Connection conn;
        int[] result = new int[5];
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.137.1:3306/flappySpace", "user2", "123456789");

            String extract = "SELECT * FROM spieler Order by score DESC;";

            stmt = conn.prepareStatement(extract);
            rs = stmt.executeQuery();
            for(int i = 0; i<5; i++){
                rs.next();
                result[i] = rs.getInt("score");
            }

            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean UpdateScore(String search_name, int score) // prüft ob der übergebene Name schon in der DB vorhanden ist und updatet den Score entweder oder leitet ihn weiter
    {
        PreparedStatement pstmt;
        Statement stmt;
        ResultSet rs;
        Connection conn;


        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.137.1:3306/flappySpace","user2", "123456789");

            String search = "SELECT spieler FROM spieler WHERE spieler = '" +search_name+ "';";
            String update = "UPDATE spieler SET score = "+score+" WHERE spieler = '"+search_name+"' AND score < '"+ score +"';";


            pstmt = conn.prepareStatement(search);
            rs = pstmt.executeQuery();

            rs.next();
            String name = rs.getString("spieler");


            if(name.equals(search_name))
            {
                stmt = conn.createStatement();
                stmt.executeUpdate(update);
            }

            conn.close();
            return true;

        }
        catch(Exception e)
        {
            insertScore(search_name, score);
            return false;
        }

    }



    public boolean insertScore(String search_name, int score)  // fügt einen neuen Datensatz in die Datenbank
    {
        Statement stmt;
        Connection conn;

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.137.1:3306/flappySpace","user2", "123456789");

            String insert = "INSERT INTO spieler (spieler, score) VALUES ('"+search_name+"',"+score+");";

            if(search_name != null) {
                stmt = conn.createStatement();
                stmt.executeUpdate(insert);
                stmt.close();
            }

            conn.close();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }

    }

    public boolean getConnectionInfo(){
        return connectionInfo;
    }

}
