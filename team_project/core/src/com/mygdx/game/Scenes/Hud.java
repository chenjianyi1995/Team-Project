package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Jianyi on 2/26/2017.
 */

public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private int worldTime;
    private int timecount;
    private int score;
    private int level;



    Label scorelabel;
    Label timelabel;
    Label levellabel;
    Label currentTime;
    Label totalScore;
    Label currentLevel;

    public Hud(SpriteBatch sb){
        worldTime = 0;
        timecount = 0;
        score = 0;

        viewport = new FitViewport(MyGdxGame.v_width, MyGdxGame.v_hieght, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scorelabel = new Label("SCORE", new  Label.LabelStyle(new BitmapFont(), Color.WHITE));
        totalScore = new Label(String.format("%d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timelabel = new Label("TIME", new  Label.LabelStyle(new BitmapFont(), Color.WHITE));
        currentTime = new Label(String.format("%d", worldTime), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levellabel = new Label("Level", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        currentLevel = new Label(String.format("%d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(scorelabel).expandX().padTop(8);
        table.add(timelabel).expandX().padTop(8);
        table.add(levellabel).expandX().padTop(8);
        table.row();
        table.add(totalScore).expandX().padTop(10);
        table.add(currentTime).expandX().padTop(10);
        table.add(currentLevel).expandX().padTop(10);


        stage.addActor(table);
    }
    public void update(float dt){
        timecount += dt;
        if(timecount >= 1) {
            worldTime ++;
            currentTime.setText(String.format("%d", worldTime));
            timecount = 0;
        }
    }
    public void setScore(int score){
        this.score = score;
    }
    public void setLevel(int level){
        this.level = level;
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
