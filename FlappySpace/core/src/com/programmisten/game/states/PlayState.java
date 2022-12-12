package com.programmisten.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.programmisten.game.FlappySpace;
import com.programmisten.game.sprites.Player;
import com.programmisten.game.sprites.Meteor;

import org.w3c.dom.Text;

import java.awt.font.OpenType;

import jdk.internal.org.jline.utils.Log;
import sun.awt.image.PixelConverter;

public class PlayState extends State {
    private Player player;
    private Texture background;

    //Meteor
    private Meteor meteors;

    private Stage stage;
    public int score = 0;
    private Label lbl_Score;
    private Label.LabelStyle skin;




    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("spaceshort.jpg");



        player = new Player(30, 100);
        meteors = new Meteor();

        stage = new Stage(new StretchViewport(FlappySpace.WIDTH, FlappySpace.HEIGHT));
        skin = new Label.LabelStyle();
        skin.font = new BitmapFont(Gdx.files.internal("skin.fnt"), false);


        cam.setToOrtho(false, FlappySpace.WIDTH / 2, FlappySpace.HEIGHT / 2);
        
        
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
        layer.top().left();
        lbl_Score = new Label("Score: " + score, skin);
        layer.add(lbl_Score);
        return layer;
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
        if (Gdx.input.justTouched()) {
            player.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        //On ground hit
        if (player.getPosition().y <= FlappySpace.HEIGHT / 20) {
            player.jump();
        }
        meteors.update();

        player.update(dt);
        // On meteors off screen

        if (meteors.getBotMeteorPosition().x <= -100) {
            score ++;
            meteors = new Meteor();
            rebuildStage();
        }

        if(cam.position.x - cam.viewportWidth * 0.5f > meteors.getPosTopMeteor().x + meteors.getBoundsTop().getWidth()*2){

        }





        //On collide
        if (meteors.collides(player.getBounds())) {
            gsm.set(new Endscreen(gsm, score));
        }


        //score
        if (player.getPosition().x == meteors.getPosBotMeteor().x || player.getPosition().x == meteors.getPosTopMeteor().x) {
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, FlappySpace.WIDTH / 2, FlappySpace.HEIGHT / 2);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getTexture().getWidth() / 3, player.getTexture().getHeight() / 3);
        sb.draw(meteors.getMeteorBotTexture(), meteors.getBotMeteorPosition().x, meteors.getBotMeteorPosition().y, meteors.getMeteorBotTexture().getWidth() * 2, meteors.getMeteorBotTexture().getHeight() * 2);
        sb.draw(meteors.getMeteorTopTexture(), meteors.getTopMeteorPosition().x, meteors.getTopMeteorPosition().y, meteors.getMeteorTopTexture().getWidth() * 2, meteors.getMeteorTopTexture().getHeight() * 2);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    public int getScore() {
        return score;
    }

    @Override
    public void dispose() {
        background.dispose();
        skin.font.dispose();
        stage.dispose();


    }
}
