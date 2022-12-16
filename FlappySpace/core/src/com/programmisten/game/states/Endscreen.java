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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


public class Endscreen extends State{
    private Texture background;
    private Texture gameOver;
    private Texture homeBtn;
    private Texture muteBtn;
    private Texture unmuteBtn;

    private Stage stage;
    private Label title;
    private Label score1;
    private Label score2;
    private Label score3;
    private Label score4;
    private Label score5;


    private Label.LabelStyle skin;

    private static final int  buttonScale = 25;
    private static final int  buttonMargin = 5;
    private static final int BtnTextGap = 130;

    private Rectangle homeBtnBounds;
    private Rectangle muteBtnBounds;

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


        homeBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(homeBtn.getWidth()/2),FlappySpace.HEIGHT /4 - BtnTextGap, homeBtn.getWidth(), homeBtn.getHeight());
        muteBtnBounds =  new Rectangle((FlappySpace.WIDTH/4)-(muteBtn.getWidth()/4),FlappySpace.HEIGHT /4 - BtnTextGap, muteBtn.getWidth() / 2, homeBtn.getHeight() / 2);

        button = Gdx.audio.newSound(Gdx.files.internal("click.wav"));

        stage = new Stage(new StretchViewport(FlappySpace.WIDTH, FlappySpace.HEIGHT));
        skin = new Label.LabelStyle();
        skin.font = new BitmapFont(Gdx.files.internal("skin.fnt"), false);


        cam.setToOrtho(false, FlappySpace.WIDTH/2, FlappySpace.HEIGHT/2);
        rebuildStage();
        connectDb();
    }


    private void connectDb(){

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test2", "root", "")) {
            String sql = "SELECT * FROM scoreboard;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));


        } catch (Exception e) {
            System.out.println(e);
        }


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
        title = new Label(" RANK   SCORE   NAME", skin);
        score1 = new Label("  NR1          "+ score + "        " + name, skin);
        score2 = new Label("  NR2          "+ score + "        " + name, skin);
        score3 = new Label("  NR3          "+ score + "        " + name, skin);
        score4 = new Label("  NR4          "+ score + "        " + name, skin);
        score5 = new Label("  NR5          "+ score + "        " + name, skin);

        layer.add(title);
        layer.row();
        layer.add(score1);
        layer.row();
        layer.add(score2);
        layer.row();
        layer.add(score3);
        layer.row();
        layer.add(score4);
        layer.row();
        layer.add(score5);
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
            // Collides with play button
            if(homeBtnBounds.contains(tmp.x, tmp.y)){
                PlaySound(button, 0.6f);
                gsm.set(new MenuState(gsm));
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
