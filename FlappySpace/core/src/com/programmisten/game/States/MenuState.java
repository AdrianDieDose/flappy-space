package com.programmisten.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.programmisten.game.FlappySpace;

import org.w3c.dom.Text;


public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private Texture title;
    private Texture player;

    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("spaceshort.jpg");
        playBtn = new Texture("play.png");
        title = new Texture("title.png");
        player = new Texture("vehicle1.png");
    }


    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0, FlappySpace.WIDTH, FlappySpace.HEIGHT);
        sb.draw(playBtn, (FlappySpace.WIDTH / 2) - (playBtn.getWidth()/2), (FlappySpace.HEIGHT / 3));
        // We need to scale that..
        sb.draw(title, (FlappySpace.WIDTH / 2) - (title.getWidth()/2), (FlappySpace.HEIGHT / 2));
        sb.draw(player, (FlappySpace.WIDTH / 2) - (player.getWidth()/2), (FlappySpace.HEIGHT / 2));
        sb.end();

    }
}
