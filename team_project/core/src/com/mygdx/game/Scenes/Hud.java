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

    }
}
