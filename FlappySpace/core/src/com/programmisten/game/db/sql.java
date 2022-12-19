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

    public int[]  extraxtScores() {
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

    public boolean UpdateScore(String search_name, int score)
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
            String insert = "INSERT INTO spieler (spieler, score) VALUES ('"+search_name+"',"+score+");";
            System.out.println(insert);


            pstmt = conn.prepareStatement(search);
            rs = pstmt.executeQuery();



            rs.next();
            String name = rs.getString("spieler");
            System.out.println(name);

            if(name.equals(search_name))
            {
                System.out.println(score);
                stmt = conn.createStatement();
                stmt.executeUpdate(update);
            }
            else
            {
                System.out.println(insert+1);
                stmt = conn.createStatement();
                stmt.executeUpdate(insert);
                System.out.println(insert+2);
            }



            conn.close();
            stmt.close();
            return true;

        }
        catch(Exception e)
        {

            insertScore(search_name, score);
            return false;
        }

    }

    public boolean insertScore(String search_name, int score)
    {
        Statement stmt;
        Connection conn;


        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.137.1:3306/flappySpace","user2", "123456789");

            String insert = "INSERT INTO spieler (spieler, score) VALUES ('"+search_name+"',"+score+");";
            System.out.println(insert);


                System.out.println(score);
                stmt = conn.createStatement();
                stmt.executeUpdate(insert);




            conn.close();
            stmt.close();
            return true;

        }
        catch(Exception e)
        {


            return false;
        }

    }



}
