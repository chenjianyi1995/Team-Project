package com.mygdx.game.Sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.playscreen;
import com.mygdx.game.Scenes.Hud;

import java.util.Random;
/**
 * Created by JonYoung on 3/5/17.
 */

public class Enemy extends Sprite{
    protected World world;
    protected playscreen screen;
    public Body b2body;
    private TextureRegion ramstand;

    public boolean toDestroy;
    public boolean destroyed;
    public boolean killed;

    private static int health;
    private static int baseHealth = 20;
    private static int level = 1;
    private static int damage;
    private static int baseDamage = 5;
    private static float movSpd;
    private static float baseMovSpd = 1;
    public boolean shot;

    public float stateTime;

    Random rand = new Random();
    public Vector2 velocity;
    public Enemy(playscreen screen, float x, float y){
        this.screen = screen;
        this.world = screen.getWorld();
        stateTime = 0;


        ramstand = new TextureRegion(screen.getAtlas().findRegion("RAM"), 0, 0, 14, 16);
        setRegion(ramstand);
        setBounds(0,0,14/MyGdxGame.PPM,16/MyGdxGame.PPM);
        defineEnemy(x, y);
    }
    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
        float a,b,x,y, length;
        a = Bufflalo.b2body.getPosition().x;
        b = Bufflalo.b2body.getPosition().y;
        x = this.b2body.getPosition().x;
        y = this.b2body.getPosition().y;
        float xma = x-a;
        float ymb = y-b;
        length = (float)(Math.sqrt(Math.pow(xma, 2) + Math.pow(ymb, 2)));
        float vel_x = xma/length;
        float vel_y = ymb/length;
        velocity = new Vector2(-vel_x*movSpd, -vel_y*movSpd);
        if(toDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            if(killed) {
                Hud.addscore();
                Bufflalo.setExperience(level * 15);
            }
        }

       else if(!destroyed) {
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            b2body.setLinearVelocity(velocity);
        }
    }
    public void defineEnemy(float x, float y){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x / MyGdxGame.PPM, y / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        health = baseHealth;
        damage = baseDamage;
        movSpd = baseMovSpd;

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / MyGdxGame.PPM);

        fdef.filter.categoryBits = MyGdxGame.RAM_BIT;
        fdef.filter.maskBits = MyGdxGame.FIREBALL_BIT |
                MyGdxGame.GROUND_BIT|
                MyGdxGame.BUFFALO_BIT;

        fdef.shape = shape;

        b2body.createFixture(fdef).setUserData(this);


    }

    public void setToDestroy(boolean kill){
        killed = kill;
        toDestroy = true;
    }
    public void hit() {
        setHealth(true);
        if(health <= 0)
            setToDestroy(true);
    }

    public boolean isDestroyed() { return destroyed; }

    public static int getLevel() {
        return level;
    }
    public static int getDamage() {
        return damage;
    }
    public static void setLevel() {
        int diff;
        diff = Hud.getTotTime() / 10;
        System.out.println(diff);
        level = level + (level * (diff/10));
        setHealth(false);
        setDamage();
        setMovSpd();
        MyGdxGame.manager.get("audio/ramup.mp3", Sound.class).play();
    }
    public static void setHealth(boolean shot) {
        if(shot) {
            health = health - Bufflalo.getDamage();
        }
        else {
            baseHealth = baseHealth + 1;
        }
    }
    public static void setDamage() {
        baseDamage = baseDamage + 2;
    }
    public static void setMovSpd() {
        baseMovSpd += 0.1f * baseMovSpd;
    }
}
