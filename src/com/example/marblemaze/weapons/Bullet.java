package com.example.marblemaze.weapons;


// -------------------------------------------------------------------------
/**
 * The Bullet interface provides methods that the laser and rocket must
 * implement
 *
 * @author Nicholas Kilmer (nkilmer8)
 * @version 2013.12.06
 */
public interface Bullet
{

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param x
     *            is the x velocity
     * @param y
     *            is the y velocity
     */
    void move(float x, float y);
}
