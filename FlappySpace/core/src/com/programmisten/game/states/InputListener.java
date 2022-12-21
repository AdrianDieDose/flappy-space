package com.programmisten.game.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;
import com.programmisten.game.db.sql;

public class InputListener extends ApplicationAdapter implements Input.TextInputListener {
    String savedText;
    sql conn;
    int score;


    @Override
    public void input(String text) {
        savedText = text;
        conn = new sql();
        conn.UpdateScore(savedText, score);

        System.out.println(savedText);
    }

    @Override
    public void canceled() {
        savedText = "USER";

    }

    public String getText() {
        return this.savedText;
    }

    public void setSavedText(String text) {
        savedText = text;
    }


    public void setScore(int wert) {
        score = wert;

    }




    }


