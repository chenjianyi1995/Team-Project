package com.mygdx.game.Sprites;

import com.badlogic.gdx.audio.Sound;
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

    private boolean setToDestroyed;
    boolean destroyed;

    private static int health;
    private static int level;
    private static int damage;
    private static int movSpd;
    Random rand = new Random();
    public Vector2 velocity;
    public Enemy(playscreen screen, float x, float y){
       // super(screen.getAtlas().findRegion("RAM"));
        this.screen = screen;
        this.world = screen.getWorld();
        setPosition(x,y);

        ramstand = new TextureRegion(screen.getAtlas().findRegion("RAM"), 0, 0, 14, 16);
        setRegion(ramstand);
        setBounds(0,0,14/MyGdxGame.PPM,16/MyGdxGame.PPM);
        defineEnemy();
        //setToDestroyed = false;
        //destroyed = false;
            //Enemy randomly move to 12 directions.
            int n = rand.nextInt(12) + 1;
            if (n == 1) {
                velocity = new Vector2(2, 1);
            } else if (n == 2) {
                velocity = new Vector2(-2, 1);
            } else if (n == 3) {
                velocity = new Vector2(1, 2);
            } else if (n == 4) {
                velocity = new Vector2(1, -2);
            } else if (n == 5) {
                velocity = new Vector2(2, 2);
            } else if (n == 6) {
                velocity = new Vector2(-2,-2);
            } else if (n == 7) {
                velocity = new Vector2(2, -2);
            } else if (n == 8) {
                velocity = new Vector2(-2, 2);
            } else if (n == 9) {
                velocity = new Vector2(2, 0);
            } else if (n == 10) {
                velocity = new Vector2(-2, 0);
            } else if (n == 11) {
                velocity = new Vector2(0, 2);
            } else if (n == 12) {
                velocity = new Vector2(0, -2);
            }
    }
    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
        if(setToDestroyed && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;

        }
        else if(!destroyed) {
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            b2body.setLinearVelocity(velocity);
        }
        setLevel(dt);
    }
    public void defineEnemy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(1380 / MyGdxGame.PPM, 1320 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        /*health = 20;
        level = 1;
        damage = 5;
        movSpd = 1;*/

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
    public void hit() { setToDestroyed = true; }
    public boolean isDestroyed() {return destroyed;}
    public static int getLevel() {
        return level;
    }
    public static int getHealth() {
        return health;
    }
    public static int getDamage() {
        return damage;
    }
    public static int getMovSpd(){
        return movSpd;
    }
    public void setLevel(float dt) {
        int diff = 0;
        if(Hud.getTotTime() % 10 == 0) {
            diff = Hud.getTotTime() / 10;
        }
        level = level + (level * (diff/10));
    }
    public void setHealth() {

    }
    public void setDamage() {

    }
    public void setMovSpd() {

    }
    public void reverseVelocity(boolean x, boolean y){  //Enemy bounce back when hitting walls.
        /*if (velocity.x>0 && velocity.y>0)
            velocity.y = -velocity.y;
        if (velocity.x>0 && velocity.y<0)
            velocity.x = -velocity.x;
        if (velocity.x<0 && velocity.y>0)
            velocity.y = -velocity.y;
        if (velocity.x<0 && velocity.y<0)
            velocity.x = -velocity.x;
        if (velocity.x == 0)
            velocity.y = -velocity.y;
        if (velocity.y == 0)
            velocity.x = -velocity.x;*/
        velocity.x = -velocity.x;
        velocity.y = -velocity.y;
    }
}
