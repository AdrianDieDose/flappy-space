package com.programmisten.game.db;

public class Database {
}
    private String name;
    private int score;


    public Database(String name, int quantity) {
        this.name = name;
        this.score = score;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public String toString() {
        String output = score + " x " + name;

        return output;
    }
}
