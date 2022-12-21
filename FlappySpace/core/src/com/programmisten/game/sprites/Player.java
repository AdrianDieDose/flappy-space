package com.programmisten.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private static final int GRAVITY = -10;
    private Vector2 position;
    private Vector2 velocity;
    private Texture player;
    private Rectangle bounds;
    private Animation shipAnimation;

    private static final int jumpHeight = 250;


    public Player(int x, int y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        player = new Texture("spritesheet.png");
        shipAnimation = new Animation(new TextureRegion(player), 3, 0.5f);
        bounds = new Rectangle(x, y, player.getWidth() / 12, player.getHeight() / 6);

    }

    public void update(float dt){
        shipAnimation.update(dt);
        velocity.add(0, GRAVITY);
        velocity.scl(dt);
        position.add(0,velocity.y);

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return shipAnimation.getFrame();
    }

    public void jump(){
        this.velocity.y = jumpHeight;
    }


    public Rectangle getBounds(){
        return bounds;
    }


}
