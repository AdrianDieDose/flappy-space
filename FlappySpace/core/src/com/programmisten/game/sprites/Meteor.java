package com.programmisten.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Meteor {
    private Vector3 position;
    private Vector3 velocity;

    private Texture meteor;



    public Meteor(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0,0);
        meteor = new Texture("meteor.png");
    }


    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }
    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return meteor;
    }
}
