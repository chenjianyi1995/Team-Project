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
    public World world;
    public Body b2body;
    private TextureRegion bulletstand;

    private char direction;
    private Bufflalo player;
    private Texture texture;

    public Bullet(World world, Bufflalo player, char direction, playscreen screen){
        super(screen.getAtlas().findRegion("sfireball"));
        this.world = screen.getWorld();
        this.player = player;
        this.direction = direction;
        defineBullet();
        bulletstand = new TextureRegion(getTexture(),49, 0,16,16);
        setBounds(0,0,16/MyGdxGame.PPM,16/MyGdxGame.PPM);
        setRegion(bulletstand);
    }

    private void defineBullet() {
        //bulletstand = new Texture(Gdx.files.internal("fireball.png"));
        BodyDef bdef = new BodyDef();
        if(direction == 'w') bdef.position.set(player.getX() + player.getWidth()/2, player.getY() + player.getHeight() + this.getHeight());
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

}