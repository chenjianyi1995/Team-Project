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

	public static final short default_bit = 1;
	public static final short buff_bit = 2;
	public static final short enemy_bit = 4;

	public SpriteBatch batch;

	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/background.ogg", Music.class);
		manager.load("audio/score.wav", Sound.class);
		manager.finishLoading();
		setScreen(new playscreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
