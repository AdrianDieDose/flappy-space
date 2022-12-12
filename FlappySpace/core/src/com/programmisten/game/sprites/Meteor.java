package com.programmisten.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Meteor {
    private Vector2 posTopMeteor, posBotMeteor;
    private Texture meteorBot, meteorTop;
    private Rectangle boundsTop, boundsBot;

    private static final int topMeteorY = 250;
    private static final int botMeteorY = 50;
    private static final int startMeteorXPos = 250;
    private static final double meteorSpeed = 3.5;
    private static final int meteorFluctuation = 70;





    public Meteor() {
        posTopMeteor = new Vector2(startMeteorXPos,(int)(Math.random() * meteorFluctuation) + topMeteorY);
        posBotMeteor = new Vector2(startMeteorXPos+50,(int)(Math.random() * meteorFluctuation) + botMeteorY);
        meteorBot = new Texture("meteor.png");
        meteorTop = new Texture("meteor.png");

        boundsBot = new Rectangle(posBotMeteor.x, posBotMeteor.y, (float) (meteorBot.getWidth()*1.5), (float) (meteorBot.getHeight()*1.5));
        boundsTop = new Rectangle(posTopMeteor.x, posTopMeteor.y, (float) (meteorTop.getWidth()*1.5), (float) (meteorTop.getHeight()*1.5));

    }

    public void update(){
        this.posBotMeteor.x -= meteorSpeed;
        this.posTopMeteor.x -= meteorSpeed;

        boundsBot.setPosition(posBotMeteor.x, posBotMeteor.y);
        boundsTop.setPosition(posTopMeteor.x, posTopMeteor.y);
    }



    public Vector2 getBotMeteorPosition() {
        return posTopMeteor;
    }
    public Vector2 getTopMeteorPosition() {
        return posBotMeteor;
    }


    public Texture getMeteorBotTexture() {
        return meteorBot;
    }
    public Texture getMeteorTopTexture() {
        return meteorTop;
    }

    public Rectangle getBoundsBot() {
        return boundsBot;
    }
    public Rectangle getBoundsTop() {
        return boundsTop;
    }

    public boolean collides(Rectangle player){
        return  player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public Vector2 getPosTopMeteor() {
        return posTopMeteor;
    }

    public Vector2 getPosBotMeteor() {
        return posBotMeteor;
    }
}
