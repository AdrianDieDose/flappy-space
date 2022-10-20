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
    private Texture background;

    //Meteor
    private Meteor meteors;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("spaceshort.jpg");
        player = new Player(30,100);

        meteors = new Meteor();
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
            player.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        //On ground hit
        if(player.getPosition().y <= FlappySpace.HEIGHT / 14){
            player.jump();
        }
        meteors.update();

        player.update(dt);
        // On meteors off screen
        if(meteors.getBotMeteorPosition().x <=-100 && meteors.getBotMeteorPosition().x <= -100) {
            meteors = new Meteor();
        }

        //On collide
        if(meteors.collides(player.getBounds())){
            gsm.set(new MenuState(gsm));
        }


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, FlappySpace.WIDTH / 2,FlappySpace.HEIGHT / 2);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getTexture().getWidth() / 3, player.getTexture().getHeight() / 3);
        sb.draw(meteors.getMeteorBotTexture(), meteors.getBotMeteorPosition().x,meteors.getBotMeteorPosition().y, meteors.getMeteorBotTexture().getWidth()*2, meteors.getMeteorBotTexture().getHeight()*2);
        sb.draw(meteors.getMeteorTopTexture(), meteors.getTopMeteorPosition().x,meteors.getTopMeteorPosition().y, meteors.getMeteorTopTexture().getWidth()*2, meteors.getMeteorTopTexture().getHeight()*2);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();

    }
}
