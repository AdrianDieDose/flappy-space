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
    private Meteor[] meteor = new Meteor[6];

    private static final int velocityBoostAtGround = 300;
    private static final int jumpHeight = 280;
    private Texture background;

    //Meteor speed
    private int Speed = 3;
    private int startPos = 250;


    int[] max = {250,50,250,0,250,50};
    int[] min = {150,0,150,0,150,0};
    int[] range = new int[6];





    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("spaceshort.jpg");
        player = new Player(30,300);
        for (int i = 0; i< meteor.length/2; i++) {
            range[i] = max[i]-min[i]+1;
        }
        meteor[0] = new Meteor(startPos, (int) (Math.random() * range[0]) + max[0]);
        meteor[1] = new Meteor(startPos, (int) (Math.random() * range[1]) + max[1]);
        meteor[2] = new Meteor(startPos+150, (int) (Math.random() * range[2]) + max[2]);
        meteor[3] = new Meteor(startPos+150, (int) (Math.random() * range[3]) + max[3]);
        meteor[4] = new Meteor(startPos+300, (int) (Math.random() * range[4]) + max[4]);
        meteor[5] = new Meteor(startPos+300, (int) (Math.random() * range[5]) + max[5]);

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
        for (int i = 0; i< meteor.length; i++){
            meteor[i].setPosition((int) (meteor[i].getPosition().x- Speed), (int) meteor[i].getPosition().y);
        }


        player.update(dt);
        if(meteor[0].getPosition().x <=-100 && meteor[1].getPosition().x <= -100) {
            for (int i = 0; i < meteor.length; i++) {
                meteor[0] = new Meteor(startPos, (int) (Math.random() * range[0]) + max[0]);
                meteor[1] = new Meteor(startPos, (int) (Math.random() * range[1]) + max[1]);
            }
        }
            if(meteor[2].getPosition().x <=-50 && meteor[3].getPosition().x <= -50) {
                for (int i = 0; i< meteor.length; i++) {
                    meteor[2] = new Meteor(startPos+100, (int) (Math.random() * range[2]) + max[2]);
                    meteor[3] = new Meteor(startPos+100, (int) (Math.random() * range[3]) + max[3]);
                }
        }
        if(meteor[4].getPosition().x <=-50 && meteor[5].getPosition().x <= -50) {
            for (int i = 0; i< meteor.length; i++) {
                meteor[4] = new Meteor(startPos+100, (int) (Math.random() * range[4]) + max[4]);
                meteor[5] = new Meteor(startPos+100, (int) (Math.random() * range[5]) + max[5]);
            }
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, FlappySpace.WIDTH / 2, FlappySpace.HEIGHT / 2);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getTexture().getWidth() / 3, player.getTexture().getHeight() / 3);
        for (int i = 0; i< meteor.length; i++){
            sb.draw(meteor[i].getTexture(), meteor[i].getPosition().x, meteor[i].getPosition().y, meteor[i].getTexture().getWidth() * 2, meteor[i].getTexture().getHeight() * 2);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
