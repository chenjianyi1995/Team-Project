package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Sprites.Bufflalo;
import com.mygdx.game.Sprites.Bullet;
import com.mygdx.game.Sprites.Enemy;

/**
 * Created by Jianyi on 4/2/2017.
 */

public class WorldCL implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        int cdef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (cdef){

            case MyGdxGame.BUFFALO_BIT | MyGdxGame.RAM_BIT:
                if(fixA.getFilterData().categoryBits == MyGdxGame.BUFFALO_BIT)
                    ((Bufflalo) fixA.getUserData()).die();
                else
                    ((Bufflalo) fixB.getUserData()).die();
                break;

            /*
            case MyGdxGame.RAM_BIT | MyGdxGame.FIREBALL_BIT:
                if(fixA.getFilterData().categoryBits == MyGdxGame.RAM_BIT){
                    ((Enemy)fixA.getUserData()).hit((Bufflalo) fixB.getUserData());
                }
                else
                {
                    ((Enemy)fixB.getUserData()).hit((Bufflalo) fixA.getUserData());
                }
                break;
            */
            case MyGdxGame.FIREBALL_BIT | MyGdxGame.RAM_BIT:

                if(fixA.getFilterData().categoryBits == MyGdxGame.FIREBALL_BIT){
                    ((Bullet)fixA.getUserData()).setToDestroyed();
                    ((Enemy)fixB.getUserData()).hit();
                }

                else if(fixB.getFilterData().categoryBits == MyGdxGame.FIREBALL_BIT)
                    ((Bullet)fixB.getUserData()).setToDestroyed();
                    ((Enemy)fixA.getUserData()).hit();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
