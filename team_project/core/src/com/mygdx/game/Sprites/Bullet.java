package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Sprites.Bufflalo;
import com.mygdx.game.Screen.playscreen;
import com.mygdx.game.Scenes.Hud;
import com.sun.org.apache.xpath.internal.operations.String;

import java.awt.image.BufferedImage;

/**
 * Created by zijunxu on 4/3/17.
 */


public class Bullet extends Sprite {

    playscreen screen;
    World world;
    private TextureRegion bulletstand;

    boolean destroyed;
    boolean setToDestroyed;
    float statetime;

    private char direction;
    private Bufflalo player;
    private Texture texture;
    Body b2body;

    public Bullet(playscreen screen, float x, float y, char direction){
        //super(screen.getAtlas().findRegion("sfireball"));
        this.screen = screen;
        this.world = screen.getWorld();
        this.player = player;
        this.direction = direction;

        bulletstand = new TextureRegion(screen.getAtlas().findRegion("sfireball"),49, 0,16,16);
        setRegion(bulletstand);
        setBounds(0,0,16/MyGdxGame.PPM,16/MyGdxGame.PPM);

        defineBullet();
    }

    private void defineBullet() {
        //bulletstand = new Texture(Gdx.files.internal("fireball.png"));
        BodyDef bdef = new BodyDef();
        if(direction == 'w') bdef.position.set(getX(),getY());
        else if(direction == 'a') bdef.position.set(player.getX() + this.getWidth(), player.getY() + player.getHeight()/2);
        else if(direction == 's') bdef.position.set(player.getX() + player.getHeight()/2, player.getY() + this.getHeight());
        else if(direction == 'd') bdef.position.set(player.getX() + player.getWidth() + this.getWidth(), player.getY() + player.getHeight()/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/ MyGdxGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt){
        statetime += dt;
        if((statetime > 2 || setToDestroyed) && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
        }
        if(b2body.getLinearVelocity().y > 2f)
            b2body.setLinearVelocity(b2body.getLinearVelocity().x, 2f);
        if((b2body.getLinearVelocity().x < 0) || (b2body.getLinearVelocity().x >0))
            setToDestroyed();
    }

    public void setToDestroyed(){
        setToDestroyed = true;
    }
    public boolean isDestroyed(){
        return destroyed;
    }
}