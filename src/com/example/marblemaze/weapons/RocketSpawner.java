package com.example.marblemaze.weapons;

import sofia.util.Timer;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 * A spawner that creates rockets.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.07
 */
public class RocketSpawner
    extends WeaponSpawner
{

    private int direction;
    private int x;
    private int y;


    // ----------------------------------------------------------
    /**
     * Create a new LaserSpawner object.
     *
     * @param x
     *            The x coordinate
     * @param y
     *            The y coordinate
     * @param cd
     *            Cooldown of the RocketSpawner.
     */
    public RocketSpawner(int x, int y, long cd)
    {
        super(x, y, cd);
        this.x = x;
        this.y = y;
        direction = 1;

        this.setFillColor(Color.red);
        this.setColor(Color.greenYellow);
        Timer.callRepeatedly(this, "createBullet", cd);
    }


    @Override
    public Bullet createBullet()
    {
        Bullet b = new Rocket(x, y, direction);
        if (direction % 2 == 0)
        {
            b.move(0, 0.2f);
        }
        else
        {
            b.move(0.2f, 0);
        }
        return b;
    }


    // ----------------------------------------------------------
    /**
     * Changes direction of the spawner.
     *
     * @param dir
     *            The direction.
     */
    public void setDirection(int dir)
    {
        direction = dir;
    }

}
