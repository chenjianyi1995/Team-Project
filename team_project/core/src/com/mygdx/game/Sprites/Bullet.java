package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
<<<<<<< HEAD
import com.badlogic.gdx.graphics.g2d.TextureRegion;
=======
>>>>>>> 3d580c72acc0a7b662ee6683b6a0aa01348094ce
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

/**
 * Created by zijunxu on 4/3/17.
 */


public class Bullet extends Sprite {
    public World world;
    public Body b2body;
   // private TextureRegion bulletstand;

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
<<<<<<< HEAD

=======
>>>>>>> 3d580c72acc0a7b662ee6683b6a0aa01348094ce
    }

}