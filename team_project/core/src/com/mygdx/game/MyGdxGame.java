package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screen.playscreen;

import java.awt.Toolkit;

/**
 * Set up AssetManager, which is used to load sound effects/background music.
 * Define collision parameters.
 */
public class MyGdxGame extends Game {
	public static final int v_width = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int v_hieght = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final float PPM = 100;

	//collisons
	public static final short NOTHING_BIT = 0;
	public static final short BUFFALO_BIT = 8;
	public static final short RAM_BIT = 2;
	public static final short FIREBALL_BIT = 16;
	public static final short GROUND_BIT = 1;
	public static final short OBJECT_BIT = 4;



	public SpriteBatch batch;

	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/background.mp3", Music.class);
		manager.load("audio/score.wav", Sound.class);
		manager.load("audio/fireball.wav", Sound.class);
		manager.load("audio/gameover.wav", Sound.class);
		manager.load("audio/pain.mp3", Sound.class);
        manager.load("audio/buffup.mp3", Sound.class);
        manager.load("audio/ramup.mp3", Sound.class);
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

