package com.mygdx.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    public playscreen(Game game){
        this.game = (MyGdxGame) game;
        texture = new Texture("demo.png");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(MyGdxGame.v_width,MyGdxGame.v_hieght,gamecam);
        hud =  new Hud (((MyGdxGame) game).batch);

        mapLoader = new TmxMapLoader();
        map= mapLoader.load("TMX.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        gamecam.position.set(gamePort.getScreenWidth()/3, gamePort.getScreenHeight()/3,0);
        world = new World(new Vector2((0, 0),true);
        b2dr= new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //layer for river
        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class));
        Rectangle rect = ((RectangleMapObject) object).getRectangle();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(rect.getX()+rect.getWidth()/3, rect.getY()+rect.getHeight()/3);

        body = world.createBody(bdef);

        shape.setAsBox(rect.getWidth() /3, rect.getHeight()/3);
        fdef.shape =shape;
        body.createFixture(fdef);

        //layer for trees
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class));
        Rectangle rect = ((RectangleMapObject) object).getRectangle();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(rect.getX()+rect.getWidth()/3, rect.getY()+rect.getHeight()/3);

        body = world.createBody(bdef);

        shape.setAsBox(rect.getWidth() /3, rect.getHeight()/3);
        fdef.shape =shape;
        body.createFixture(fdef);

        //layer for obstacle
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class));
        Rectangle rect = ((RectangleMapObject) object).getRectangle();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(rect.getX()+rect.getWidth()/3, rect.getY()+rect.getHeight()/3);

        body = world.createBody(bdef);

        shape.setAsBox(rect.getWidth() /3, rect.getHeight()/3);
        fdef.shape =shape;
        body.createFixture(fdef);

    }

    @Override
    public void show() {

    }

    public void handleInput(){
        
    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f, 0,2);

        gamecam.update();
        renderer.setView(gamecam);
    }
    @Override
    public void render(float delta) {
        update(delta);


        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //render our game map
        renderer.render();
        b2dr.render(world, gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        hud.stage.draw();;
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
