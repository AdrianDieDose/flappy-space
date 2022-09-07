package com.programmisten.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.programmisten.game.FlappySpace;
import com.programmisten.game.sprites.Player;

import org.w3c.dom.Text;

public class PlayState extends State {
    private  Player player;
    private static final int velocityBoostAtGround = 300;
    private static final int jumpHeight = 280;
    private Texture background;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("spaceshort.jpg");
        player = new Player(30,300);
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
        player.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, FlappySpace.WIDTH / 2,FlappySpace.HEIGHT / 2);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getTexture().getWidth() / 3, player.getTexture().getHeight() / 3);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
