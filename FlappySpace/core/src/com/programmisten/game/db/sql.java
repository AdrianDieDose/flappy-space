package com.programmisten.game.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class sql {

    public String[]  extraxtNames() {
        PreparedStatement stmt;
        ResultSet rs;
        Connection conn;
        String[] result = new String[5];
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flappySpace", "root", "");


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

    public int[]  extraxtScores() {
        PreparedStatement stmt;
        ResultSet rs;
        Connection conn;
        int[] result = new int[5];
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flappySpace", "root", "");


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

    public void UpdateScore(String search_name, int score)
    {
        PreparedStatement pstmt;
        Statement stmt;
        ResultSet rs;
        Connection conn;

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flappySpace","root","");

            String search = "Select name where name = '" +search_name+ "';";
            String update = "UPDATE spiel SET "+score+" = "+score+" WHERE name = '"+search_name+"';";
            String insert = "Insert into spieler (name, score) Values ("+search_name+","+score+");";

            pstmt = conn.prepareStatement(search);
            rs = pstmt.executeQuery();

            rs.next();
            String name = rs.getString("name");

            if(search_name.equals(name))
            {
                stmt = conn.createStatement();
                stmt.executeQuery(update);
            }
            else
            {
                stmt = conn.createStatement();
                stmt.executeQuery(insert);
            }



            conn.close();
            stmt.close();
            //JOptionPane.showMessageDialog(null, "Speicherung Erfolgreich", "Meldung", JOptionPane.INFORMATION_MESSAGE);

        }
        catch(Exception e)
        {

        }

    }

}
