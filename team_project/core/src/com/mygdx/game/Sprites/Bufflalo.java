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
import com.mygdx.game.TextureManager;

/**
 * Created by ALwyn on 2017/3/12.
 */

public class Bufflalo extends Sprite{
    public World world;
    public Body b2body;
    private TextureRegion buffstand;

    public Bufflalo(playscreen screen){
        super(screen.getAtlas().findRegion("buff"));
        this.world = screen.getWorld();

        defineBuffalo();
        buffstand = new TextureRegion(getTexture(), 0, 0, 22, 16);
        setBounds(0,0,22/MyGdxGame.PPM,16/MyGdxGame.PPM);
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
        fdef.filter.categoryBits = MyGdxGame.buff_bit;
        fdef.filter.maskBits = MyGdxGame.default_bit | MyGdxGame.enemy_bit;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/MyGdxGame.PPM, 5/MyGdxGame.PPM),new Vector2(2/MyGdxGame.PPM, 5/MyGdxGame.PPM) );
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("head");
    }
}
