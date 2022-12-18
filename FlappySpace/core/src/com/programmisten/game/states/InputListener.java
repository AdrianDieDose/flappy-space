package com.programmisten.game.states;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputListener extends ApplicationAdapter implements Input.TextInputListener {
    String savedText;


    @Override
    public void input(String text) {
        savedText = text;
        System.out.println(savedText);
    }

    @Override
    public void canceled() {
        savedText = "USER";

    }

    public String getText(){
        return this.savedText;
    }
}
