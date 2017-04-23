package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by zijunxu on 4/3/17.
 */

public class Bullet extends Sprite {
    public World world;
    public Body b2body;

    private char direction;
    private Bufflalo player;

    public Bullet(World world, Bufflalo player, char direction){
        this.world = world;
        this.player = player;
        this.direction = direction;
        defineBullet();
    }

    private void defineBullet() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(player.b2body.getPosition().x / MyGdxGame.PPM,player.b2body.getPosition().y / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/ MyGdxGame.PPM);


        fdef.shape = shape;
        b2body.createFixture(fdef);


    }

}
