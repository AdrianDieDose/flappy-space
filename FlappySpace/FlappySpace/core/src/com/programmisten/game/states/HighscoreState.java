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


public class HighscoreState extends State{
    private final Texture background;
    private final Texture homeBtn;
    private final Texture muteBtn;
    private final Texture unmuteBtn;


    private final Stage stage;

    private final Label[] scores_lbl = new Label[5];

    private final Label.LabelStyle skin;

    private static final int BtnTextGap = 130;

    private final Rectangle homeBtnBounds;
    private final Texture highscoreBtn;
    private final Rectangle highscoreBtnBounds;


    private int score;
    private Sound button;
    private String user;


    sql conn = new sql();




    public HighscoreState(GameStateManager gsm, String username, int score1) {
        super(gsm);
        user = username;

        score = score1;

        background = new Texture("spaceshort.jpg");
        homeBtn = new Texture("home.png");
        muteBtn = new Texture("mute.png");
        unmuteBtn = new Texture("unmute.png");
        highscoreBtn = new Texture("highscore1pressed.png");


        homeBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(homeBtn.getWidth()/2),FlappySpace.HEIGHT /4 - BtnTextGap, homeBtn.getWidth(), homeBtn.getHeight());
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
        Label title = new Label(" RANK   SCORE   NAME", skin);
        int[] scores = new int[5];
        String[] names = new String[5];

        scores = conn.extraxtScores();
        names = conn.extraxtNames();

        layer.add(title);
        layer.row();
        for(int i = 0; i < 5; i++)
        {
           scores_lbl[i]  = new Label(" NR"+(i+1)+" | "+ scores[i] + " | " + names[i], skin);
            layer.add(scores_lbl[i]);
            layer.row();
        }


        return layer;
    }

    private void PlaySound(Sound sound, float volume){
        sound.play(volume);
    }


    @Override
    protected void handleInput() {
        // If touched
        if (Gdx.input.justTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            // Collides with play button
            if(homeBtnBounds.contains(tmp.x, tmp.y)){
                PlaySound(button, 0.6f);
                gsm.set(new MenuState(gsm));
                dispose();
            }

            if(highscoreBtnBounds.contains(tmp.x, tmp.y)){
                PlaySound(button, 0.6f);
                gsm.set(new Endscreen(gsm, score, user));
                System.out.println(score+user+false);

                dispose();
            }



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
