package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
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

    private Integer worldtime;
    private float timecount;
    private Integer score;

    Label scorelabel;
    Label timelabel;
    Label levellabel;

    public Hud(SpriteBatch sb){
        timecount=0;
        score = 0;

        viewport = new FitViewport(MyGdxGame.v_width, MyGdxGame.v_hieght, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scorelabel = new Label(String.format("%06d", score), new  Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timelabel = new Label("TIME", new  Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levellabel = new Label("Level", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(scorelabel).expandX().padTop(8);
        table.add(timelabel).expandX().padTop(8);
        table.add(levellabel).expandX().padTop(8);

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
