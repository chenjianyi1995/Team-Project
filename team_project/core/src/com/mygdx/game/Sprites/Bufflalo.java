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
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.playscreen;

import java.util.Random;


/**
 * Define buffalo parameters, collision.
 * Track buffalo states.
 * Also, enemy spawning is defined here.
 */
public class Bufflalo extends Sprite{
    public enum State {DEAD};

    public World world;
    public static Body b2body;

    private TextureRegion buffstand;
    private TextureRegion bufflaloDead;


    private static int health;
    private static int baseHealth = 50;
    private static int level;
    private static int experience;
    private static int xpToNext;
    private static int damage;
    private static float movSpd;
    private boolean bufflaloIsDead;

    public float stateTime;
    public float secondState;

    public Object currentState;
    private Array<Bullet> fireballs;
    private Array<Enemy> enemies;
    private int spawns;
    private float spawnTime;
    private playscreen screen;

    public Bufflalo(playscreen screen){
        this.screen = screen;
        this.world = screen.getWorld();

        spawns = 0;

        defineBuffalo();
        buffstand = new TextureRegion(screen.getAtlas().findRegion("buffalo"), 0, 3, 12, 16);
        bufflaloDead= new TextureRegion(screen.getAtlas().findRegion("buffalo"), 72, 0, 12, 16);


        setBounds(0,0,27/MyGdxGame.PPM,31/MyGdxGame.PPM);
        setRegion(buffstand);
        fireballs = new Array<Bullet>();
        enemies = new Array<Enemy>();
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
        for(Bullet ball : fireballs){
            ball.update(dt);
            if(ball.isDestroyed())
                fireballs.removeValue(ball, true);
        }
        for(Enemy enemy : enemies) {
            enemy.update(dt);
            if (enemy.isDestroyed()) {
                enemies.removeValue(enemy, true);

            }
        }

        generateEnemy(dt);
        if(experience >= xpToNext)
            levelUP();

    }



    public void defineBuffalo(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(200 / MyGdxGame.PPM,200 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/ MyGdxGame.PPM);

        fdef.filter.categoryBits = MyGdxGame.BUFFALO_BIT;
        fdef.filter.maskBits = MyGdxGame.RAM_BIT |
                MyGdxGame.GROUND_BIT |
                MyGdxGame.NOTHING_BIT;

        health = baseHealth;
        level = 1;
        experience = 0;
        xpToNext = 100;
        damage = 5;
        movSpd = 1;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    public static int getHealth(boolean base) {
        if(base)
            return baseHealth;
        else
            return health;
    }
    public static int getLevel() {
        return level;
    }
    public void levelUP(){
        experience = experience - xpToNext;
        setXpToNext();
        level ++;
        baseHealth = baseHealth + 100;
        health = baseHealth;
        damage = damage + 10;
        movSpd += 0.5f;
        MyGdxGame.manager.get("audio/buffup.mp3", Sound.class).play();
    }
    public static int getExperience() {
        return experience;
    }
    public static int getXpToNext() {
        return xpToNext;
    }
    //may want to set xpGain to be variable to diff (from time calculation for enemy level
    public static void setExperience(int xpGain) {
        experience = experience + xpGain;
    }
    public static void setXpToNext(){
        xpToNext = xpToNext + xpToNext * Enemy.getLevel();
    }
    public static int getDamage(){
        return damage;
    }
    public static float getMovSpd() {
        return movSpd;
    }
    public void hitEnemy(){
        health = health - Enemy.getDamage();
        MyGdxGame.manager.get("audio/pain.mp3", Sound.class).play();
        if(health <= 0)
            die();
    }
    public void die() {

        if (!isDead()) {

            MyGdxGame.manager.get("audio/background.mp3",Music.class).stop();
            MyGdxGame.manager.get("audio/gameover.wav", Sound.class).play();
            bufflaloIsDead = true;
            Filter filter = new Filter();
            filter.maskBits = MyGdxGame.NOTHING_BIT;

            for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }
        }
    }
    public boolean isDead(){
        return bufflaloIsDead;
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
    public void generateEnemy(float dt){
        stateTime += dt;
        secondState += dt;
        int currentTime = (int) stateTime;
        if(currentTime == 2 && spawns <= 3){
            Random rand = new Random();
            int value = rand.nextInt(4);
            spawns = value;
            stateTime = 0;
        }
        int secondTime = (int) secondState;
        if(secondTime == 15){
            Enemy.setLevel();
            secondState = 0;
        }

        switch (spawns) {
            case 0:
                spawnEnemy(1380, 1320, 2, dt);
                break;
            case 1:
                spawnEnemy(1800, 200, 2, dt);
                break;
            case 2:
                spawnEnemy(400, 1200, 2, dt);
                break;
            case 3:
                spawnEnemy(2400, 1000, 2, dt);
                break;

        }
        if(spawns > 3) {
            spawns = 0;
        }

    }
    public void spawnEnemy(float x, float y, int rate, float dt){
        spawnTime += dt;
        int currentSpawnTime = (int) spawnTime;
        if (currentSpawnTime == rate) {
            Enemy enemy = new Enemy(screen, x, y);
            enemies.add(enemy);
            spawnTime = 0;
        }
    }
    public void draw(Batch batch){
        super.draw(batch);
        for(Bullet ball : fireballs)
            ball.draw(batch);
        for(Enemy enemy : enemies)
            enemy.draw(batch);
    }
}
