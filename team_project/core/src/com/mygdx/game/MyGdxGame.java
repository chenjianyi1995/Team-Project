package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screen.playscreen;

public class MyGdxGame extends Game {
	public static final int v_width = 480;
	public static final int v_hieght = 800;
	public static final float PPM = 100;

	public SpriteBatch batch;

	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/background.ogg", Music.class);
		manager.load("audio/score.wav", Sound.class);
		manager.load("audio/fireball.wav", Sound.class);
		manager.load("audio/gameover.wav", Sound.class);
		manager.finishLoading();
		setScreen(new playscreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	@Override
	public  void dispose(){
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}

