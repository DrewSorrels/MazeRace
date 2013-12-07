package Weapons;

import sofia.util.Timer;

public abstract class WeaponFactory
{
    private int   x;
    private int   y;
    private int   cooldown;

    private Timer time;


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
    public WeaponFactory(int x, int y, int cd)
    {
        this.x = x;
        this.y = y;
        cooldown = cd;

        // time = new Timer(this, null, cd, cd);
    }


    public abstract void createBullet();


    public void start()
    {
        time.start();
    }


    public void stop()
    {
        time.stop();
    }
}
