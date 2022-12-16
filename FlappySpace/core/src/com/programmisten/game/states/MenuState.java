package com.programmisten.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.programmisten.game.FlappySpace;


public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private Rectangle playBtnBounds;
    private Texture title;
    private Texture player;
    private static final int playBtnTextGap = 50;

    private Sound button;



    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("spaceshort.jpg");
        playBtn = new Texture("play.png");
        playBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(playBtn.getWidth()/4),FlappySpace.HEIGHT /4 - playBtnTextGap, playBtn.getWidth() / 2, playBtn.getHeight() / 2);

        button = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        title = new Texture("title.png");
        player = new Texture("vehicle1.png");
        cam.setToOrtho(false, FlappySpace.WIDTH/2, FlappySpace.HEIGHT/2);


    }

    private void PlaySound(Sound sound, float volume){
        sound.play(volume);
    }


    @Override
    public void handleInput() {
        // If touched
        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            // Collides with play button
            if(playBtnBounds.contains(tmp.x, tmp.y)){
                PlaySound(button, 0.6f);
                gsm.set(new PlayState(gsm));
                dispose();
            }

        }

    }

    @Override
    public void update(float dt) {
    handleInput();
    }
    // Hardcoded for aligning :/
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, FlappySpace.WIDTH / 2,FlappySpace.HEIGHT / 2);
        sb.draw(playBtn, (FlappySpace.WIDTH/4)-(playBtn.getWidth()/4),FlappySpace.HEIGHT /4 - playBtnTextGap, playBtn.getWidth() / 2, playBtn.getHeight() / 2);
        // We need to scale that..
        sb.draw(title, FlappySpace.WIDTH/9,FlappySpace.HEIGHT /4 + playBtnTextGap, title.getWidth() / 4, title.getHeight()/4);

        sb.draw(player,(FlappySpace.WIDTH/4)-(player.getWidth()/4),(FlappySpace.HEIGHT /4), player.getWidth() / 2, player.getHeight() / 2);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        title.dispose();
        player.dispose();
    }
}
