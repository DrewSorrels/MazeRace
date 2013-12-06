package com.example.marblemaze;

import sofia.graphics.LineShape;
import sofia.graphics.Shape;

// -------------------------------------------------------------------------
/**
 *  This method will create a laser that the user must dodge
 *
 *  @author Nicholas Kilmer (nkilmer8)
 *  @version 2013.12.06
 */
public class Laser extends LineShape implements Bullet
{
    private LineShape bull;

    // ----------------------------------------------------------
    /**
     * Create a new Laser object.
     */
    public Laser()
    {
        bull.setBullet(true);

    }
    /**
     * Place a description of your method here.
     * @param x is the x velocity
     * @param y is the y velocity
     */
    public void move(int x, int y)
    {
        bull.applyLinearImpulse(x,y);
    }

 // ----------------------------------------------------------
    /**
     * removes the marble when it is hit by the laser
     * @param marble is the marble
     * @param bullet is the bullet
     */
    public void onCollisionBetween(Shape marble, Shape bullet)
    {
        if(marble instanceof MarbleShape && bullet instanceof Rocket)
        {
            marble.animate(1000).rotation(720).alpha(0).removeWhenComplete()
            .play();
            this.remove();
        }

    }



}
