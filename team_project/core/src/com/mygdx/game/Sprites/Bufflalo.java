package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by ALwyn on 2017/3/12.
 */

public class Bufflalo extends Sprite{
    public World world;
    public Body b2body;

    public Bufflalo(World world){
        this.world = world;
        defineBuffalo();
    }

    public void defineBuffalo(){
        BodyDef bdef =new BodyDef();
        bdef.position.set(32,32);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fedf= new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

        fedf.shape = shape;
        b2body.createFixture(fedf);
    }
}
