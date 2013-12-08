package com.example.marblemaze.observableevents;

import com.example.marblemaze.weapons.Bullet;

/**
 * // -------------------------------------------------------------------------
 * /** Event for when a bullet is removed.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.07
 */
public class BulletRemovedEvent
{
    private Bullet bull;


    /**
     * Creates new BulletRemovedEvent
     *
     * @param b
     *            The bullet.
     */
    public BulletRemovedEvent(Bullet b)
    {
        bull = b;
    }


    /**
     * Returns the bullet that was removed.
     *
     * @return The bullet.
     */
    public Bullet getBullet()
    {
        return bull;
    }
}
