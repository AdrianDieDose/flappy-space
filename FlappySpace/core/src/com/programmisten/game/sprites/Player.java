package com.programmisten.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private static final int GRAVITY = -10;
    private Vector2 position;
    private Vector2 velocity;

    private Texture player;
    private Rectangle bounds;


    public Player(int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        player = new Texture("vehicle1.png");
        bounds = new Rectangle();
        bounds.set(x, y, getTexture().getWidth()/3,getTexture().getHeight()/3);

    }

    public void update(float dt){
        velocity.add(0, GRAVITY);
        velocity.scl(dt);
        position.add(0,velocity.y);
        velocity.scl(1/dt);
        bounds.setPosition(position);
    }

    public void setPosition(int x, int y) {

        bounds.setPosition(position);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Texture getTexture() {
        return player;
    }
    public void setVelocity(int y) {
        this.velocity.y = y;
    }

}
