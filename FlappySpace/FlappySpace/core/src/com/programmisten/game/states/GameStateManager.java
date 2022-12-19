package com.programmisten.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

// Im GSM wird die Datenstruktur Stack genutzt um die verschiedenen Screens/States zu zu speichern.
// Diese Klasse implementiert Methoden um:
// Ein state mit push in den stack zu speichern.
// Mit pop diesen wieder zu entfernt
// Set um diesen zu setztn.

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}