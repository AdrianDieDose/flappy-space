package com.programmisten.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.programmisten.game.states.GameStateManager;
import com.programmisten.game.states.MenuState;

public class FlappySpace extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "FlappySpace";

	private GameStateManager gsm;

	private SpriteBatch batch;
	Texture img;

	private Sound warped;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		img = new Texture("badlogic.jpg");
		warped = Gdx.audio.newSound(Gdx.files.internal("warped.wav"));
		ScreenUtils.clear(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		LoopSound(warped, 0.6f);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		/*
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		 */
	}
	
	@Override
	public void dispose () {
		StopSound(warped);
		/*
		batch.dispose();
		img.dispose();
		 */
	}

	private void LoopSound(Sound sound, float volume){
		sound.loop(volume);
	}
	private void StopSound(Sound sound){
		sound.stop();
	}
}
