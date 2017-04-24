package com.mygdx.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Bufflalo;
import com.mygdx.game.Sprites.Bullet;
import com.mygdx.game.Sprites.Enemy;
import com.mygdx.game.Tools.B2d;
import com.mygdx.game.Tools.WorldCL;

import java.util.ArrayList;
import java.util.Arrays;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;


/**
 * Created by Jianyi on 2/26/2017.
 */

public class playscreen implements Screen {

    private MyGdxGame game;
    private TextureAtlas atlas;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    private Bufflalo player;
    private Enemy enemy;
    private static java.util.List<Bullet> bulletList;

    private Music music;

    public playscreen(MyGdxGame game){
        atlas = new TextureAtlas("buff.pack");
        this.game = (MyGdxGame) game;

        gamecam = new OrthographicCamera();

        gamePort = new FitViewport(MyGdxGame.v_width / MyGdxGame.PPM, MyGdxGame.v_hieght / MyGdxGame.PPM,gamecam);
        hud =  new Hud(game.batch);


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map simple.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MyGdxGame.PPM);

        world = new World(new Vector2(0,0),true);
        b2dr = new Box2DDebugRenderer();


        new B2d(this);
        player = new Bufflalo(this);
        world.setContactListener(new WorldCL());
        //enemy = new Enemy(this);
        bulletList = new ArrayList<Bullet>(Arrays.asList(new Bullet(world, player, 'n')));
        enemy = new Enemy(this,32/MyGdxGame.PPM,32/MyGdxGame.PPM);
        music = MyGdxGame.manager.get("audio/background.ogg", Music.class);
        music.setLooping(true);
        music.play();
    }

    public  TextureAtlas getAtlas(){
        return atlas;
    }
    @Override
    public void show() {

    }

    public void shooting() {
        //player.b2body.getPosition().x;
        //player.b2body.getPosition().y;
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            Bullet bullet = new Bullet(world, player, 'w');
            playscreen.bulletList.add(bullet);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            Bullet bullet = new Bullet(world, player, 'a');
            playscreen.bulletList.add(bullet);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            Bullet bullet = new Bullet(world, player, 's');
            playscreen.bulletList.add(bullet);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            Bullet bullet = new Bullet(world, player, 'd');
            playscreen.bulletList.add(bullet);
        }
    }
    public void handleInput(float dt) {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse(new Vector2(0.1f * player.getMovSpd(), 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f * player.getMovSpd(), 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <= 2) {
            player.b2body.applyLinearImpulse(new Vector2(0, 0.1f * player.getMovSpd()), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.b2body.getLinearVelocity().y >= -2) {
            player.b2body.applyLinearImpulse(new Vector2(0, -0.1f * player.getMovSpd()), player.b2body.getWorldCenter(), true);
        }



        //speed up
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && player.b2body.getLinearVelocity().x <= 5) {
            player.b2body.applyLinearImpulse(new Vector2(2f, 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && player.b2body.getLinearVelocity().x >= -5) {
            player.b2body.applyLinearImpulse(new Vector2(-2f, 0), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && player.b2body.getLinearVelocity().y <= 5) {
            player.b2body.applyLinearImpulse(new Vector2(0, 2f), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && player.b2body.getLinearVelocity().y >= -5) {
            player.b2body.applyLinearImpulse(new Vector2(0, -2f), player.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.b2body.setLinearVelocity(0, 0);
            player.b2body.setAngularVelocity(0);
        }
    }

    public void update(float dt){
        handleInput(dt);
        shooting();

        world.step(1/60f, 6, 2);
        player.update(dt);
        enemy.update(dt);
        hud.update(dt);

        gamecam.position.x = player.b2body.getPosition().x;
        gamecam.position.y = player.b2body.getPosition().y;
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
        game.batch.begin();
        player.draw(game.batch);
        enemy.draw(game.batch);
       /* if(!bulletList.isEmpty()) {
            for(int i = 0; i < bulletList.size(); i++) {
                Bullet bullet =bulletList.get(i);
                bullet.draw(game.batch);
                bulletList.remove(i);
            }
        }
        */
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
