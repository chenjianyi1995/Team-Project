package com.mygdx.game.Sprites;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.playscreen;


/**
 * Created by ALwyn on 2017/3/12.
 */

public class Bufflalo extends Sprite{
    public enum State {DEAD};

    public World world;
    public static Body b2body;

    private TextureRegion buffstand;
    private TextureRegion bufflaloDead;


    private static int health;
    private static int level;
    private static int experience;
    private static int xpToNext;
    private static int damage;
    private static int attSpd;
    private static float movSpd;
    private boolean bufflaloIsDead;
    private float stateTimer;
    public Object currentState;
    private Array<Bullet> fireballs;
    private playscreen screen;

    public Bufflalo(playscreen screen){
        this.screen = screen;
        this.world = screen.getWorld();
        stateTimer = 0;

        defineBuffalo();
        buffstand = new TextureRegion(screen.getAtlas().findRegion("buffalo"), 0, 0, 12, 16);
        bufflaloDead= new TextureRegion(screen.getAtlas().findRegion("buffalo"), 72, 0, 12, 16);


        setBounds(0,0,12/MyGdxGame.PPM,16/MyGdxGame.PPM);
        setRegion(buffstand);
        fireballs = new Array<Bullet>();
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
        for(Bullet ball : fireballs){
            ball.update(dt);
            if(ball.isDestroyed())
                fireballs.removeValue(ball, true);
        }
        /*if(!isDead())
            die();*/
    }



    public void defineBuffalo(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(200 / MyGdxGame.PPM,200 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ MyGdxGame.PPM);


        health = 50;
        level = 1;
        experience = 0;
        xpToNext = 100;
        damage = 5;
        attSpd = 1;
        movSpd = 1;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

     /*   EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/MyGdxGame.PPM, 5/MyGdxGame.PPM),new Vector2(2/MyGdxGame.PPM, 5/MyGdxGame.PPM) );
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("head"); */
    }
    public void onEnemyHit() {

    }

    public static int getHealth() {
        return health;
    }
    public void setHealth(int newHealth, boolean isDamage) {
        if(isDamage) {
            health = health - newHealth;
        }
        else health = health + newHealth;
    }
    public static int getLevel() {
        return level;
    }
    public void setLevel() {
        if(experience >= xpToNext){
            level ++;
            xpToNext = xpToNext + 100;
        }
    }
    public static int getExperience() {
        return experience;
    }
    //may want to set xpGain to be variable to diff (from time calculation for enemy level
    public void setExperience(int xpGain) {
        experience = experience + xpGain;
    }
    public static int getDamage(){
        return damage;
    }
    public void setDamage(){
        damage = damage + damage;
    }
    public static int getAttSpd() {
        return attSpd;
    }
    public void setAttSpd() {
        attSpd ++;
    }
    public static float getMovSpd() {
        return movSpd;
    }
    public void setMovSpd() {
        movSpd ++;
    }

    public State getState() {
        if (bufflaloIsDead)
            return State.DEAD;
        return null;
    }
    public void hit(Enemy userData) {

        if (!isDead()) {

            MyGdxGame.manager.get("audio/background.ogg",Music.class).stop();
            MyGdxGame.manager.get("audio/gameover.wav", Sound.class).play();
            bufflaloIsDead = true;
            Filter filter = new Filter();
            filter.maskBits = MyGdxGame.NOTHING_BIT;

            for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }

            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }
    }

    public boolean isDead(){
        return bufflaloIsDead;
    }

    public float getStateTimer(){
        return stateTimer;
    }
    public void fire(char direction){
        Bullet bullet = new Bullet(this, screen, b2body.getPosition().x, b2body.getPosition().y, direction);
        fireballs.add(bullet);

        float bulletSpeed = 5f;
        if(direction == 'w') {
            bullet.b2body.applyLinearImpulse(new Vector2(0, bulletSpeed), bullet.b2body.getWorldCenter(), true);
            MyGdxGame.manager.get("audio/fireball.wav", Sound.class).play();
        }
        else if(direction == 'a') {
            bullet.b2body.applyLinearImpulse(new Vector2(-bulletSpeed, 0), bullet.b2body.getWorldCenter(), true);
            MyGdxGame.manager.get("audio/fireball.wav", Sound.class).play();
        }
        else if(direction == 's') {
            bullet.b2body.applyLinearImpulse(new Vector2(0, -bulletSpeed), bullet.b2body.getWorldCenter(), true);
            MyGdxGame.manager.get("audio/fireball.wav", Sound.class).play();
        }
        else if(direction == 'd') {
            bullet.b2body.applyLinearImpulse(new Vector2(bulletSpeed, 0), bullet.b2body.getWorldCenter(), true);
            MyGdxGame.manager.get("audio/fireball.wav", Sound.class).play();
        }

    }
    public void draw(Batch batch){
        super.draw(batch);
        for(Bullet ball : fireballs)
            ball.draw(batch);
    }
}
