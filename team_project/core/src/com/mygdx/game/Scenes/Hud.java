package com.mygdx.game.Scenes;

import com.badlogic.gdx.audio.Sound;
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
import com.mygdx.game.Sprites.Bufflalo;


/**
 * Created by Jianyi on 2/26/2017.
 */

public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private static Integer worldTime;
    private float timecount;
    private static Integer score;
    private int level;



    private Label scorelabel;
    private Label timelabel;
    private Label levellabel;
    private Label currentTime;
    private Label totalScore;
    private Label currentLevel;
    private Label health;
    private Label xp;
    private Label healthLabel;
    private Label xpLabel;


    public Hud(SpriteBatch sb){
        worldTime = 0;
        timecount = 0;
        score = 0;
        level = 1;

        viewport = new FitViewport(MyGdxGame.v_width, MyGdxGame.v_hieght, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scorelabel = new Label("Score", new  Label.LabelStyle(new BitmapFont(), Color.WHITE));
        totalScore = new Label(String.format("%d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timelabel = new Label("Time", new  Label.LabelStyle(new BitmapFont(), Color.WHITE));
        currentTime = new Label(String.format("%d", worldTime), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levellabel = new Label("Level", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        currentLevel = new Label(String.format("%d", level), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        health = new Label(String.format("%d", Bufflalo.getHealth(false)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        xp = new Label(String.format("%d / %d", Bufflalo.getExperience(), Bufflalo.getXpToNext()), new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        healthLabel = new Label("Health", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        xpLabel = new Label("XP", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        scorelabel.setFontScale(1.7f);
        totalScore.setFontScale(1.7f);
        timelabel.setFontScale(1.7f);
        currentTime.setFontScale(1.7f);
        levellabel.setFontScale(1.7f);
        currentLevel.setFontScale(1.7f);


        healthLabel.setFontScale(1.7f);
        xpLabel.setFontScale(1.7f);
        health.setFontScale(2f);
        xp.setFontScale(2f);

        table.add(scorelabel).expandX().padTop(8);
        table.add(timelabel).expandX().padTop(8);
        table.add(levellabel).expandX().padTop(8);
        table.row();
        table.add(totalScore).expandX().padTop(10);
        table.add(currentTime).expandX().padTop(10);
        table.add(currentLevel).expandX().padTop(10);
        table.row();
        table.add(healthLabel).expand().padTop(500);
        table.add().expand().padTop(0);
        table.add(xpLabel).expand().padTop(500);
        table.row();
        table.add(health).expand().padTop(0);
        table.add().expand().padTop(0);
        table.add(xp).expand().padTop(0);


        stage.addActor(table);
    }
    public void update(float dt){
        timecount += dt;
        if(timecount >= 1) {
            worldTime ++;
            currentTime.setText(String.format("%d", worldTime));
            timecount = 0;
        }
        level = Bufflalo.getLevel();
        currentLevel.setText(String.format("%d", level));
        totalScore.setText(String.format("%d", score));
        health.setText(String.format("%d", Bufflalo.getHealth(false)));
        if(Bufflalo.getHealth(false) > (Bufflalo.getHealth(true)*0.5)){
            health.setStyle(new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        }
        else{
            health.setStyle(new Label.LabelStyle(new BitmapFont(), Color.RED));
        }
        xp.setText(String.format("%d / %d", Bufflalo.getExperience(), Bufflalo.getXpToNext()));
    }
    public static void addscore(){
        score ++;
        MyGdxGame.manager.get("audio/score.wav", Sound.class).play();
    }

    public static Integer getScore() { return score; }
    public static Integer getTotTime() {
        return worldTime;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
