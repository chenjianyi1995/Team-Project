package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;

/**
 * Created by ALwyn on 2017/3/12.
 */

public class Bufflalo extends Sprite{
    public World world;
    public Body b2body;

    private int experience;
    private int level;
    private int health;
    private int damage;


    public Bufflalo(World world){
        this.world = world;
        defineBuffalo();
    }

    public void defineBuffalo(){
        experience = 0;
        level = 2;
        health = 100;
        damage = 5;

        BodyDef bdef = new BodyDef();
        bdef.position.set(200 / MyGdxGame.PPM,200 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fedf= new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ MyGdxGame.PPM);

        fedf.shape = shape;
        b2body.createFixture(fedf);
    }
    public int getLevel() {
        return this.level;
    }
    public int getExperience() {
        return this.experience;
    }
    public int getHealth() {
        return this.health;
    }
    public int getDamage() {
        return this.damage;
    }
}
