package com.programmisten.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Meteor {
    private Vector2 position;
    private Vector2 velocity;
    private Circle bounds;

    private Texture meteor;



    public Meteor(int x, int y, int diameter) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        meteor = new Texture("meteor.png");
        bounds = new Circle();
        bounds.setRadius(diameter/2);

    }

    public void setPosition1(int x, int y) {
        position.set(x,y);
        bounds.setPosition(position);
    }

    public boolean collides(Rectangle object){
        boolean overlaps = Intersector.overlaps(bounds, object);
        return overlaps;
    }

    public Circle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return meteor;
    }
}
