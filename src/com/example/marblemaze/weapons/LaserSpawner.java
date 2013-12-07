package com.example.marblemaze.weapons;

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
        direction = 0;
    }


    @Override
    public Bullet createBullet()
    {
        Bullet b = new Laser(x, y, 0);
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


    public void setDirection(int dir)
    {
        direction = dir;
    }

}
