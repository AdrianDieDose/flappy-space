package com.programmisten.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private static final int GRAVITY = -10;
    private Vector2 position;
    private Vector2 velocity;
    private Texture player;
    private Rectangle bounds;

    private static final int jumpHeight = 250;


    public Player(int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        player = new Texture("vehicle1.png");
        bounds = new Rectangle(x, y, player.getWidth() / 4, player.getHeight() / 4);

    }

    public void update(float dt){
        velocity.add(0, GRAVITY);
        velocity.scl(dt);
        position.add(0,velocity.y);

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return player;
    }

    public void jump(){
        this.velocity.y = jumpHeight;
    }

    public void setVelocity(int y) {
        this.velocity.y = y;
    }

    public Rectangle getBounds(){
        return bounds;
    }


}
