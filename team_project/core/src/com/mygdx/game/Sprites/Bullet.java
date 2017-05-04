package com.mygdx.game.Sprites;

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
 * Define fireball parameters, collision.
 * Set positions at where the fireball is shot.
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

        bulletstand = new TextureRegion(screen.getAtlas().findRegion("fireball"),4, 5,9,9);
        setBounds(0,0,18/MyGdxGame.PPM,18/MyGdxGame.PPM);
        setRegion(bulletstand);
    }

    private void defineBullet() {
        BodyDef bdef = new BodyDef();
        if(direction == 'w') bdef.position.set(player.getX() + player.getWidth()/2, player.getY() + player.getHeight() + this.getHeight());
        else if(direction == 'a') bdef.position.set(player.getX() - this.getWidth(), player.getY() + player.getHeight()/2);
        else if(direction == 's') bdef.position.set(player.getX() + player.getWidth()/2, player.getY() - this.getHeight());
        else if(direction == 'd') bdef.position.set(player.getX() + player.getWidth() + this.getWidth(), player.getY() + player.getHeight()/2);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ MyGdxGame.PPM);

        fdef.filter.categoryBits = MyGdxGame.FIREBALL_BIT;
        fdef.filter.maskBits = MyGdxGame.BUFFALO_BIT |
                MyGdxGame.GROUND_BIT |
                MyGdxGame.OBJECT_BIT |
                MyGdxGame.RAM_BIT;
        fdef.shape = shape;


        b2body.createFixture(fdef).setUserData(this);


    }

    public void update(float dt){
        statetime += dt;
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
        if((statetime > 2 || setToDestroyed) && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
        }

    }

    public void setToDestroyed(){
        setToDestroyed = true;
    }
    public boolean isDestroyed(){
        return destroyed;
    }
}