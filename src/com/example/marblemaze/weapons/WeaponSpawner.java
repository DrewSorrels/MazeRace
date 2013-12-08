package com.example.marblemaze.weapons;

import sofia.util.Timer;

public abstract class WeaponSpawner
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
        this.x = x;
        this.y = y;
        cooldown = cd;

        Timer.callRepeatedly(this, "createBullet", cooldown);
    }


    public abstract Bullet createBullet();

}
