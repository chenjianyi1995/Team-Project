package com.mygdx.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Bufflalo;

/**
 * Created by Healer on 2017/4/23.
 */

public class GameOverScreen implements Screen {
    private Viewport viewport;
    private Stage stage;
    public static Integer score;
    private MyGdxGame game;

    public GameOverScreen(MyGdxGame game){

        this.game = game;
        viewport = new FitViewport(MyGdxGame.v_width, MyGdxGame.v_hieght, new OrthographicCamera());
        stage = new Stage(viewport, ((MyGdxGame) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);
        score = Hud.getScore();
        Label gameOverLabel = new Label("GAME OVER", font);
        Label scoreLabel = new Label("Final Score :", font);
        Label scoreNum = new Label(String.format("%d", score), font);
        Label playAgainLabel = new Label("Click To Play Again", font);


        table.add(gameOverLabel).expand().padTop(10);
        table.row();
        table.add(scoreLabel).expandX().pad(10,10,10,10);
        table.add(scoreNum).expandX().padTop(10);
        table.row();
        table.add(playAgainLabel).expand().padTop(10);


        stage.addActor(table);



    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()){
            game.setScreen(new playscreen((MyGdxGame) game));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
