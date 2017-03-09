package com.mygdx.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Scenes.Hud;

/**
 * Created by Jianyi on 2/26/2017.
 */

public class playscreen implements Screen {
    private MyGdxGame game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;


    public playscreen(Game game){
        this.game = (MyGdxGame) game;
        texture = new Texture("demo.png");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MyGdxGame.v_width,MyGdxGame.v_hieght,gamecam);
        hud =  new Hud (game.batch);

        mapLoader = new TmxMapLoader();
        map= mapLoader.load("TMX.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        gamecam.position.set(gamePort.getScreenWidth()/3, gamePort.getScreenHeight()/3,0);
    }

    @Override
    public void show() {

    }

    public void handleInput(){
        
    }

    public void update(float delta){
        handleInput();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(texture,0,0);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

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

    }
}
