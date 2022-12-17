package com.programmisten.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.programmisten.game.FlappySpace;
import com.programmisten.game.db.sql;


public class Endscreen extends State{
    private Texture background;
    private Texture gameOver;
    private Texture homeBtn;
    private Texture muteBtn;
    private Texture unmuteBtn;
    private Texture highscoreBtn;


    private Stage stage;
    private Label title, score_lbl, lose;




    private Label.LabelStyle skin;

    private static final int  buttonScale = 25;
    private static final int  buttonMargin = 5;
    private static final int BtnTextGap = 130;

    private Rectangle homeBtnBounds;
    private Rectangle muteBtnBounds;
    private Rectangle highscoreBtnBounds;

    private int score = 0;
    private String name = "Adrian";
    private Sound button;





    public Endscreen(GameStateManager gsm, int highscore) {
        super(gsm);
        score = highscore;
        System.out.println(highscore);

        background = new Texture("spaceshort.jpg");
        homeBtn = new Texture("home.png");
        muteBtn = new Texture("mute.png");
        unmuteBtn = new Texture("unmute.png");
        highscoreBtn = new Texture("highscore1.png");


        homeBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(homeBtn.getWidth()/2),FlappySpace.HEIGHT /4 - BtnTextGap, homeBtn.getWidth(), homeBtn.getHeight());
        muteBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(muteBtn.getWidth()/4),FlappySpace.HEIGHT /4 - BtnTextGap, muteBtn.getWidth() / 2, homeBtn.getHeight() / 2);
        highscoreBtnBounds = new Rectangle(0, FlappySpace.HEIGHT /2 - highscoreBtn.getHeight(), highscoreBtn.getWidth(), highscoreBtn.getHeight());

        button = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        stage = new Stage(new StretchViewport(FlappySpace.WIDTH, FlappySpace.HEIGHT));
        skin = new Label.LabelStyle();
        skin.font = new BitmapFont(Gdx.files.internal("skin.fnt"), false);


        cam.setToOrtho(false, FlappySpace.WIDTH/2, FlappySpace.HEIGHT/2);
        rebuildStage();

    }



    private void rebuildStage() {
        stage.clear();
        Stack stack = new Stack();
        stack.setSize(FlappySpace.WIDTH-30, FlappySpace.HEIGHT);
        stage.addActor(stack);
        stack.add(addScoreLabel());
    }

    private Actor addScoreLabel() {
        Table layer = new Table();
        layer.setPosition(FlappySpace.WIDTH/5,FlappySpace.HEIGHT /4);
        title = new Label("  Your Score:", skin);
        lose = new Label("  YOU DIED", skin);
        score_lbl = new Label("  "+score, skin);
        // Add current score and input




        layer.add(lose);
        layer.row();
        layer.add(title);
        layer.row();
        layer.add(score_lbl);



        return layer;
    }

    private void PlaySound(Sound sound, float volume){
        sound.play(volume);
    }

    /*
    private String getHighscore() {

    }
    */

    @Override
    protected void handleInput() {
        // If touched
        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            // Collides with home button
            if(homeBtnBounds.contains(tmp.x, tmp.y)){
                PlaySound(button, 0.6f);
                gsm.set(new MenuState(gsm));
                dispose();
            }
            // Collides with highscore button
            if(highscoreBtnBounds.contains(tmp.x, tmp.y)){
                PlaySound(button, 0.6f);
                gsm.set(new HighscoreState(gsm, score));
                dispose();
            }

            /*if(muteBtnBounds.contains(tmp.x, tmp.y)){
                //
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
        sb.draw(highscoreBtn,0, FlappySpace.HEIGHT /2 - highscoreBtn.getHeight(), highscoreBtn.getWidth(), highscoreBtn.getHeight() );
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        homeBtn.dispose();
        background.dispose();

        muteBtn.dispose();
        unmuteBtn.dispose();



    }
}
