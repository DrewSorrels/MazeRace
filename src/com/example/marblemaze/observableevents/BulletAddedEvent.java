package com.example.marblemaze.observableevents;

import com.example.marblemaze.weapons.Bullet;

/**
 * // -------------------------------------------------------------------------
 * /** Event that fires when a bullet is added.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.07
 */
public class BulletAddedEvent
{
    private Bullet bull;


    /**
     * New event for when the bullet is added.
     *
     * @param b
     *            The bullet.
     */
    public BulletAddedEvent(Bullet b)
    {
        bull = b;
    }


    /**
     * Returns the bullet
     *
     * @return the bullet.
     */
    public Bullet getBullet()
    {
        return bull;
    }

}
