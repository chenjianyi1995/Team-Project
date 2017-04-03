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
 * Created by JonYoung on 3/5/17.
 */

public class Enemy extends Sprite{
    protected World world;
    protected playscreen screen;
    public Body b2body;
    private TextureRegion ramstand;

    public Enemy(playscreen screen, float x, float y){
        super(screen.getAtlas().findRegion("ram"));

        this.world = screen.getWorld();
        setPosition(x,y);
        defineEnemy();
        ramstand = new TextureRegion(getTexture(), 25, 0, 22, 16);
        setBounds(0,0,22/MyGdxGame.PPM,16/MyGdxGame.PPM);
        setRegion(ramstand);
    }

    public Enemy(Body b2body) {
        this.b2body = b2body;
    }

    public Enemy(Sprite sprite, Body b2body) {
        super(sprite);
        this.b2body = b2body;
    }

    public void defineEnemy(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(1380 / MyGdxGame.PPM, 1320 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / MyGdxGame.PPM);

        fdef.filter.categoryBits = MyGdxGame.enemy_bit;
        fdef.filter.maskBits = MyGdxGame.enemy_bit | MyGdxGame.default_bit | MyGdxGame.buff_bit;

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2 , b2body.getPosition().y - getHeight()/2);
    }
}
