package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screen.playscreen;

public class MyGdxGame extends Game {
	public static final int v_width = 400;
	public static final int v_hieght = 208;
	public static final float PPM =100;

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new playscreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
