package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.playscreen;


/**
 * Created by ALwyn on 2017/3/12.
 */

public class Bufflalo extends Sprite{
    public World world;
    public static Body b2body;
    private TextureRegion buffstand;

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


    public Bufflalo(playscreen screen){
        super(screen.getAtlas().findRegion("buffalo"));
        this.world = screen.getWorld();

        defineBuffalo();
        buffstand = new TextureRegion(getTexture(), 0, 0, 12, 16);
        setBounds(0,0,12/MyGdxGame.PPM,16/MyGdxGame.PPM);
        setRegion(buffstand);

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
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
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/MyGdxGame.PPM, 5/MyGdxGame.PPM),new Vector2(2/MyGdxGame.PPM, 5/MyGdxGame.PPM) );
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("head");
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
    public boolean isDead(){
        return bufflaloIsDead;
    }

    public float getStateTimer(){
        return stateTimer;
    }
}
