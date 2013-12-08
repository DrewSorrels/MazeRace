package com.example.marblemaze.weapons;

import com.example.marblemaze.observableevents.BulletAddedEvent;
import sofia.graphics.Color;

/**
 * // -------------------------------------------------------------------------
 * /** Creates a spawner object that spawns lasers in a given direction.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.07
 */
public class LaserSpawner
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
     *            Cooldown of the LaserSpawner.
     */
    public LaserSpawner(int x, int y, long cd)
    {
        super(x, y, cd);
        this.x = x;
        this.y = y;
        direction = 1;

        this.setFillColor(Color.red);
        this.setColor(Color.greenYellow);
    }


    @Override
    public Bullet createBullet()
    {
        Bullet b = new Laser(x + 1.5f, y, direction);
        if (direction % 2 == 0)
        {
            b.move(0, 0.4f);
        }
        else
        {
            b.move(0.4f, 0);
        }
        notifyObservers(new BulletAddedEvent(b));
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
