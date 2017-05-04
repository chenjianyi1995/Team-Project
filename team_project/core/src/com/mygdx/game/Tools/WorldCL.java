package com.mygdx.game.Tools;

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
 * Detects sprites and/or objects' collision.
 */

public class WorldCL implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        int cdef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (cdef){

            case MyGdxGame.BUFFALO_BIT | MyGdxGame.RAM_BIT:
                if(fixA.getFilterData().categoryBits == MyGdxGame.BUFFALO_BIT) {
                    ((Bufflalo) fixA.getUserData()).hitEnemy();
                    ((Enemy) fixB.getUserData()).setToDestroy(false);
                }
                else {
                    ((Bufflalo) fixB.getUserData()).hitEnemy();
                    ((Enemy) fixA.getUserData()).setToDestroy(false);
                }
                break;

            case MyGdxGame.FIREBALL_BIT | MyGdxGame.RAM_BIT:

                if(fixA.getFilterData().categoryBits == MyGdxGame.FIREBALL_BIT){
                    ((Bullet)fixA.getUserData()).setToDestroyed();
                    ((Enemy)fixB.getUserData()).hit();
                }

                else if(fixB.getFilterData().categoryBits == MyGdxGame.FIREBALL_BIT) {
                    ((Bullet) fixB.getUserData()).setToDestroyed();
                    ((Enemy) fixA.getUserData()).hit();
                }
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
