package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.playscreen;

/**
 * Created by ALwyn on 2017/3/12.
 */

public class Bufflalo extends Sprite{
    public World world;
    public Body b2body;
    private TextureRegion buffstand;

    public Bufflalo(World world/*, playscreen screen*/){
        //super(screen.getAtlas().findRegion("buff"));
        this.world = world;

        defineBuffalo();
        /*buffstand = new TextureRegion(getTexture(),0,0,16,16);
        setBounds(0,0,16/ MyGdxGame.PPM,16/MyGdxGame.PPM);
        setRegion(buffstand);*/
    }

    public void defineBuffalo(){
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
}
