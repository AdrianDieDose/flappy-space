package com.programmisten.game.states;

import static java.lang.Math.round;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.programmisten.game.FlappySpace;
import com.programmisten.game.sprites.Player;
import com.programmisten.game.sprites.Meteor;

import org.w3c.dom.Text;

public class PlayState extends State {
    private  Player player;
    private Meteor[] meteor = new Meteor[4];

    private static final int velocityBoostAtGround = 100;
    private static final int jumpHeight = 250;
    private Texture background;



    //Meteor speed
    private int Speed = 3;
    private int startPos = 250;


    int[] max = {650,650,650,650};
    int[] min = {60,60,60,60};
    int[] range = new int[4];

    int[] size = {90,80,50,70};





    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("spaceshort.jpg");
        player = new Player(30,300);
        for (int i = 0; i< meteor.length/2; i++) {
            range[i] = max[i]-min[i]+1;
        }
        meteor[0] = new Meteor(startPos, (int) (Math.random() * range[0]) + min[0], size[0]);
        meteor[1] = new Meteor(startPos, (int) (Math.random() * range[1]) + min[1],size[1]);
        meteor[2] = new Meteor(startPos+150, (int) (Math.random() * range[2]) + min[2],size[2]);
        meteor[3] = new Meteor(startPos+150, (int) (Math.random() * range[3]) + min[3],size[3]);

        cam.setToOrtho(false, FlappySpace.WIDTH/2, FlappySpace.HEIGHT/2);

    }

    @Override
    protected void handleInput() {
        // For tapping

        if (Gdx.input.justTouched()) {
            player.setVelocity(jumpHeight);
        }


        /* For Holding
        if (Gdx.input.isTouched()) {
            player.setVelocity(jumpHeight);
        }*/
    }

    @Override
    public void update(float dt) {
        handleInput();
        if(player.getPosition().y <= 10){
            player.setVelocity(velocityBoostAtGround);
        }
        for (int i = 0; i< meteor.length; i++){
            meteor[i].setPosition1((int) (meteor[i].getPosition().x- Speed), (int) meteor[i].getPosition().y);
            if(meteor[i].getPosition().x <=-100) {
                meteor[i].setPosition1(startPos, (int) (Math.random() * range[i]) + min[i]);
            }
            if(meteor[i].collides(player.getBounds())){
                gameOver();
            }
        }
        player.update(dt);
    }

    private void gameOver(){
        System.out.println("Treffer");
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        ShapeRenderer shapeRenderer = new ShapeRenderer();

        sb.begin();
        sb.draw(background, 0, 0, FlappySpace.WIDTH / 2, FlappySpace.HEIGHT / 2);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getTexture().getWidth() / 3, player.getTexture().getHeight() / 3);
        sb.end();

        for (int i = 0; i< meteor.length; i++){
            sb.begin();
            sb.draw(meteor[i].getTexture(), meteor[i].getPosition().x, meteor[i].getPosition().y, size[i], size[i]);
            sb.end();

        }


    }

    @Override
    public void dispose() {
        background.dispose();
   
    }
}
