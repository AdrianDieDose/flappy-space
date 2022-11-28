package com.programmisten.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.programmisten.game.FlappySpace;

import org.w3c.dom.Text;

public class Endscreen extends State{
    private Texture background;
    private Texture gameOver;
    private Texture homeBtn;
    private Texture muteBtn;
    private Texture unmuteBtn;
    private Texture highscoreBtn;
    private static final int  buttonScale = 25;
    private static final int  buttonMargin = 5;
    private static final int BtnTextGap = 50;

    private Rectangle homeBtnBounds;
    private Rectangle highscoreBtnBounds;
    private Rectangle muteBtnBounds;


    public Endscreen(GameStateManager gsm) {
        super(gsm);

        background = new Texture("spaceshort.jpg");
        homeBtn = new Texture("home.png");
        muteBtn = new Texture("mute.png");
        unmuteBtn = new Texture("unmute.png");
        highscoreBtn = new Texture("highscore1.png");

        homeBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(homeBtn.getWidth()/2),FlappySpace.HEIGHT /4 - BtnTextGap, homeBtn.getWidth(), homeBtn.getHeight());
        muteBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(muteBtn.getWidth()/4),FlappySpace.HEIGHT /4 - BtnTextGap, muteBtn.getWidth() / 2, homeBtn.getHeight() / 2);
        highscoreBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(highscoreBtn.getWidth()/4),FlappySpace.HEIGHT /4 - BtnTextGap, highscoreBtn.getWidth() / 2, homeBtn.getHeight() / 2);



        cam.setToOrtho(false, FlappySpace.WIDTH/2, FlappySpace.HEIGHT/2);

    }

    @Override
    protected void handleInput() {
        // If touched
        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            // Collides with play button
            if(homeBtnBounds.contains(tmp.x, tmp.y)){
                gsm.set(new MenuState(gsm));
                dispose();
            }
            /*if(muteBtnBounds.contains(tmp.x, tmp.y)){
                //
                dispose();
            }
            if(highscoreBtnBounds.contains(tmp.x, tmp.y)){
                //gsm.set((gsm));
                dispose();
            }*/

        }

    }

    @Override
    public void update(float dt)  {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, FlappySpace.WIDTH / 2,FlappySpace.HEIGHT / 2);
        sb.draw(homeBtn,(FlappySpace.WIDTH/4)-(homeBtn.getWidth()/2),FlappySpace.HEIGHT /4 - BtnTextGap, homeBtn.getWidth(), homeBtn.getHeight());
        sb.end();


    }

    @Override
    public void dispose() {
        homeBtn.dispose();
        background.dispose();
        highscoreBtn.dispose();
        muteBtn.dispose();
        unmuteBtn.dispose();


    }
}
