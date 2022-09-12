package com.programmisten.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.programmisten.game.FlappySpace;


public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private Texture title;
    private Texture player;
    private Texture settingsButton;
    private Texture highscoreButton;
    private int playBtnTextGap;
    private int buttonScale;
    private int buttonMargin;

    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("spaceshort.jpg");
        playBtn = new Texture("play.png");
        title = new Texture("title.png");
        player = new Texture("vehicle1.png");
        settingsButton = new Texture("settings1.png");
        highscoreButton = new Texture("highscore1.png");
        playBtnTextGap = 100;
        buttonScale = 50;
        buttonMargin = 10;
    }


    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            disposal();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }
    // Hardcoded for aligning :/
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FlappySpace.WIDTH, FlappySpace.HEIGHT);
        sb.draw(playBtn, (FlappySpace.WIDTH / 2) - (playBtn.getWidth() / 2), FlappySpace.HEIGHT / 2 - playBtnTextGap);
        // We need to scale that..
        sb.draw(title, FlappySpace.WIDTH / 4, FlappySpace.HEIGHT / 2 + playBtnTextGap, title.getWidth() / 10, title.getHeight() / 10);

        sb.draw(player, (FlappySpace.WIDTH / 2) - (player.getWidth() / 2), FlappySpace.HEIGHT / 2);
        sb.draw(settingsButton, FlappySpace.WIDTH - buttonScale - buttonMargin, FlappySpace.HEIGHT - buttonScale - buttonMargin, buttonScale, buttonScale);
        sb.draw(highscoreButton, 0 + buttonMargin, FlappySpace.HEIGHT - buttonScale - buttonMargin, buttonScale, buttonScale);
        sb.end();
        }
    @Override
    public void disposal(){
        background.dispose();
        playBtn.dispose();

    }
}
