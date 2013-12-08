package com.example.marblemaze.weapons;

import sofia.graphics.RectangleShape;
import sofia.util.Timer;

// -------------------------------------------------------------------------
/**
 * Spawners create dangerous objects that the marble must avoid.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.07
 */
public abstract class WeaponSpawner
    extends RectangleShape
{
    private int  x;
    private int  y;
    private long cooldown;


    /**
     * Creates new Weapon Factory at given coordinates with the given cooldown.
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param cd
     *            Cooldown for the weapon factory.
     */
    public WeaponSpawner(int x, int y, long cd)
    {
        super(x, y, x + 0.8f, y + 0.8f);
        this.x = x;
        this.y = y;
        cooldown = cd;

        Timer.callRepeatedly(this, "createBullet", cooldown);

    }


    public abstract Bullet createBullet();

}
