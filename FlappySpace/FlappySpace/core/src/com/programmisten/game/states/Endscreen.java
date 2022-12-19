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

    private Texture homeBtn;
    private Texture saveBtn;
    private Texture highscoreBtn;


    private Stage stage;
    private Label title, score_lbl, lose;




    private Label.LabelStyle skin;
    private InputListener inputListener;


    private static final int  buttonScale = 25;
    private static final int  buttonMargin = 5;
    private static final int BtnTextGap = 130;

    private Rectangle homeBtnBounds;
    private Rectangle saveBtnBounds;
    private Rectangle highscoreBtnBounds;

    private int score = 0;

    private String userName;

    sql conn = new sql();


    private Sound button;

    private boolean il;


    public Endscreen(GameStateManager gsm, int highscore, String user) {
        super(gsm);
        score = highscore;

        inputListener = new InputListener();
        inputListener.setSavedText(user);








        background = new Texture("spaceshort.jpg");
        homeBtn = new Texture("home.png");
        saveBtn = new Texture("mute.png");
        highscoreBtn = new Texture("highscore1.png");


        homeBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(homeBtn.getWidth()/2),FlappySpace.HEIGHT /4 - BtnTextGap, homeBtn.getWidth(), homeBtn.getHeight());
        saveBtnBounds =  new Rectangle(210,FlappySpace.HEIGHT /2 - saveBtn.getHeight(), saveBtn.getWidth(), saveBtn.getHeight());
        highscoreBtnBounds = new Rectangle(0, FlappySpace.HEIGHT /2 - highscoreBtn.getHeight(), highscoreBtn.getWidth(), highscoreBtn.getHeight());

        button = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        stage = new Stage(new StretchViewport(FlappySpace.WIDTH, FlappySpace.HEIGHT));
        skin = new Label.LabelStyle();
        skin.font = new BitmapFont(Gdx.files.internal("skin.fnt"), false);

        //skin2 = new Skin();



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
        title = new Label("  Your Score: ", skin);
        lose = new Label("  "+ userName +" DIED", skin);
        score_lbl = new Label("  "+score, skin);



        //spielerName = new TextField("", skin);
        // Add current score and input




        layer.add(lose);
        layer.row();
        layer.add(title);
        layer.row();
        layer.add(score_lbl);
        layer.row();
        //layer.add(spielerName);



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
                gsm.set(new HighscoreState(gsm, userName, score));
                dispose();
            }
            if(saveBtnBounds.contains(tmp.x, tmp.y)){
                if(userName == "USER") {
                    Gdx.input.getTextInput(inputListener, "Congratulations!!!\nEnter your name for Ranking:", "", "");
                    conn.UpdateScore(inputListener.getText(), score);
                }
                PlaySound(button, 0.6f);
            }








        }

    }

    @Override
    public void update(float dt)  {
        handleInput();
        userName = inputListener.getText();
        rebuildStage();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, FlappySpace.WIDTH / 2,FlappySpace.HEIGHT / 2);
        sb.draw(homeBtn,(FlappySpace.WIDTH/4)-(homeBtn.getWidth()/2),FlappySpace.HEIGHT /4 - BtnTextGap, homeBtn.getWidth(), homeBtn.getHeight());
        sb.draw(highscoreBtn,0, FlappySpace.HEIGHT /2 - highscoreBtn.getHeight(), highscoreBtn.getWidth(), highscoreBtn.getHeight() );
        sb.draw(saveBtn, 210, FlappySpace.HEIGHT /2 - saveBtn.getHeight(),saveBtn.getWidth(), saveBtn.getHeight());
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        homeBtn.dispose();
        background.dispose();

        saveBtn.dispose();



    }
}
