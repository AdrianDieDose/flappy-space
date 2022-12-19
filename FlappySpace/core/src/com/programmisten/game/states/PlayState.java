package com.programmisten.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.programmisten.game.FlappySpace;
import com.programmisten.game.sprites.Meteor;
import com.programmisten.game.sprites.Player;

public class PlayState extends State {
    private Player player;
    private Texture background;

    //Meteor
    private Meteor meteors;

    private Stage stage;
    public int score = 0;
    private Label lbl_Score;
    private Label.LabelStyle skin;

    private Sound hit, fly, point, booster, thrust, crash;




    public PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("spaceshort.jpg");



        player = new Player(30, 100);
        meteors = new Meteor();

        stage = new Stage(new StretchViewport(FlappySpace.WIDTH, FlappySpace.HEIGHT));
        skin = new Label.LabelStyle();
        skin.font = new BitmapFont(Gdx.files.internal("skin.fnt"), false);


        cam.setToOrtho(false, FlappySpace.WIDTH / 2, FlappySpace.HEIGHT / 2);

        hit = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
        point = Gdx.audio.newSound(Gdx.files.internal("point.wav"));
        fly = Gdx.audio.newSound(Gdx.files.internal("fly.wav"));
        booster = Gdx.audio.newSound(Gdx.files.internal("booster.wav"));
        thrust = Gdx.audio.newSound(Gdx.files.internal("thrust.wav"));
        crash = Gdx.audio.newSound(Gdx.files.internal("crash.wav"));

        
        rebuildStage();



    }

    private void rebuildStage() {
        stage.clear();
        Stack stack = new Stack();
        stack.setSize(FlappySpace.WIDTH-30, FlappySpace.HEIGHT);
        stage.addActor(stack);
        stack.add(addScoreLabel());
    }

    private void PlaySound(Sound sound, float volume){
        sound.play(volume);
    }
    private void StopSound(Sound sound){
        sound.stop();
    }
    private void LoopSound(Sound sound, float volume){
        sound.loop(volume);
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

        // For Holding
        if (Gdx.input.justTouched()) {
            StopSound(thrust);
            StopSound(booster);
            PlaySound(booster, 0.4f);
            PlaySound(thrust, 0.4f);
            player.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        //On ground hit
        if (player.getPosition().y <= FlappySpace.HEIGHT / 20) {
            player.jump();
            PlaySound(crash, 0.8f);
        }
        meteors.update();
        player.update(dt);

        // On meteors off screen
        if (meteors.getPosBotMeteor().x <= -80) {
            score ++;
            meteors = new Meteor();
            PlaySound(point, 0.4f);

            rebuildStage();
        }
        //On collide
        if (meteors.collides(player.getBounds())) {
            StopSound(thrust);
            StopSound(booster);
            PlaySound(hit, 0.4f);

            gsm.set(new Endscreen(gsm, score, "USER",true));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, FlappySpace.WIDTH / 2, FlappySpace.HEIGHT / 2);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y, player.getTexture().getRegionWidth() / 3, player.getTexture().getRegionHeight() / 3);
        sb.draw(meteors.getMeteorBotTexture(), meteors.getPosBotMeteor().x, meteors.getPosBotMeteor().y, meteors.getMeteorBotTexture().getWidth() * 2, meteors.getMeteorBotTexture().getHeight() * 2);
        sb.draw(meteors.getMeteorTopTexture(), meteors.getPosTopMeteor().x, meteors.getPosTopMeteor().y, meteors.getMeteorTopTexture().getWidth() * 2, meteors.getMeteorTopTexture().getHeight() * 2);
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
