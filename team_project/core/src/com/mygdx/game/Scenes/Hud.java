package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Jianyi on 2/26/2017.
 */

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldtime;
    private float timecount;
    private Integer score;

    Label countdownlabel;
    Label scorelabel;
    Label timelabel;
    Label levellabel;
    Label worldlabel;
    Label bufflabel;

    public Hud(SpriteBatch sb){
        timeCount=0;
        score = 0;

        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxgame.V)_HEIGHT, new OrthographicCamera());
        stage = new stage(viewport, sb);

        Table table= new table();
        table.top();
        table.setFillParent(true);

        ScoreLabel = new Label(String.format("%06d", score), new  Label.LabelStyle(new BitmapFont(), Color. WHITE))；
        timeLabel = new Label(new Label("TIME", new  Label.LabelStyle(new BitmapFont(), Color. WHITE)));
        levelLabel = new Label("Level" new  Label.LabelStyle(new BitmapFont(), Color. WHITE)));
        table.add()
    }
}
