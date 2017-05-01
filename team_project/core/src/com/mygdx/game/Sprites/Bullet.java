package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.playscreen;


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
    Body b2body;

    public Bullet(Bufflalo player, playscreen screen, float x, float y, char direction){
        this.screen = screen;
        this.world = screen.getWorld();
        this.player = player;
        this.direction = direction;

        defineBullet();

        bulletstand = new TextureRegion(screen.getAtlas().findRegion("sfireball"),0, 0,16,16);
        setBounds(0,0,16/MyGdxGame.PPM,16/MyGdxGame.PPM);
        setRegion(bulletstand);
    }

    private void defineBullet() {
        BodyDef bdef = new BodyDef();
        if(direction == 'w') bdef.position.set(player.getX() + player.getWidth()/2, player.getY() + player.getHeight() + this.getHeight()/2);
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
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
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