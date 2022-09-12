package com.programmisten.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.programmisten.game.FlappySpace;

public class PlayState extends State{
    private Texture player;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        player = new Texture("vehicle1.png");
        cam.setToOrtho(false, FlappySpace.WIDTH / 2, FlappySpace.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(player, 75, 75, player.getWidth() / 2, player.getHeight() / 2);
        sb.end();
    }

    @Override
    public void disposal() {

    }
}
