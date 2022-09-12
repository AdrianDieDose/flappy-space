package com.programmisten.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.programmisten.game.FlappySpace;
import com.programmisten.game.sprites.Player;
import com.programmisten.game.sprites.Meteor;

import org.w3c.dom.Text;

public class PlayState extends State {
    private  Player player;
    private Meteor meteor1;
    private Meteor meteor2;
    private static final int velocityBoostAtGround = 300;
    private static final int jumpHeight = 280;
    private Texture background;

    //Meteor speed
    private int meteorSpeed = 3;
    private int startPos = 250;

    //Meteor1
    int max1 = 250;
    int min1 = 150;
    int range1 = max1 - min1 + 1;

    //Meteor2
    int max2 = 50;
    int min2 = 0;
    int range2 = max2 - min2 + 1;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("spaceshort.jpg");
        player = new Player(30,300);

        meteor1 = new Meteor(startPos,(int)(Math.random() * range1) + max1);
        meteor2 = new Meteor(startPos,(int)(Math.random() * range2) + max2);
        cam.setToOrtho(false, FlappySpace.WIDTH/2, FlappySpace.HEIGHT/2);

    }

    @Override
    protected void handleInput() {
        // For tapping
        /*
        if (Gdx.input.justTouched()) {
            player.setVelocity(jumpHeight);
        }
         */

        // For Holding
        if (Gdx.input.isTouched()) {
            player.setVelocity(jumpHeight);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if(player.getPosition().y <= FlappySpace.HEIGHT / 14){
            player.setVelocity(velocityBoostAtGround);
        }
        meteor1.setPosition((int) (meteor1.getPosition().x- meteorSpeed), (int) meteor1.getPosition().y);
        meteor2.setPosition((int) (meteor2.getPosition().x - meteorSpeed), (int) meteor2.getPosition().y);
        player.update(dt);
        if(meteor1.getPosition().x <=-100 && meteor2.getPosition().x <= -100) {
            meteor1 = new Meteor(startPos,(int)(Math.random() * range1) + max1);
            meteor2 = new Meteor(startPos,(int)(Math.random() * range2) + max2);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, FlappySpace.WIDTH / 2,FlappySpace.HEIGHT / 2);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getTexture().getWidth() / 3, player.getTexture().getHeight() / 3);
        sb.draw(meteor1.getTexture(), meteor1.getPosition().x,meteor1.getPosition().y, meteor1.getTexture().getWidth()*2, meteor1.getTexture().getHeight()*2);
        sb.draw(meteor2.getTexture(), meteor2.getPosition().x,meteor2.getPosition().y, meteor2.getTexture().getWidth()*2, meteor2.getTexture().getHeight()*2);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
