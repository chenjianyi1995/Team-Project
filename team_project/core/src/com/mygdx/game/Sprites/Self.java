package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by JonYoung on 3/5/17.
 */

public class Self extends Sprite {
    public World world;
    public Body b2body;

    public Self (World world) {
        this.world = world;
        defineSelf();
    }
    public void defineSelf() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32, 32);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

    }
}
