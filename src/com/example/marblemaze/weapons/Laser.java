package com.example.marblemaze.weapons;

import sofia.graphics.Color;
import android.graphics.RectF;
import com.example.marblemaze.MarbleShape;
import sofia.graphics.LineShape;

// -------------------------------------------------------------------------
/**
 * This method will create a laser that the user must dodge
 *
 * @author Nicholas Kilmer (nkilmer8)
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.06
 */
public class Laser
    extends LineShape
    implements Bullet
{
    private static final float DENSITY     = 10f;
    private static final float FRICTION    = 0.4f;
    private static final float RESTITUTION = 0.0f;

    private int                direction;


    // ----------------------------------------------------------
    /**
     * Create a new Laser object.
     */
    public Laser(float x, float y, int dir)
    {
        super();
        this.setBullet(true);

        this.setColor(Color.red);
        this.setFriction(Laser.FRICTION);
        this.setRestitution(Laser.RESTITUTION);
        this.setDensity(Laser.DENSITY);

        this.direction = dir;
        // Set up bounds
        float iShort = 0.1f;
        float iLong = 1.1f;
        float xExtent = dir % 2 == 0 ? iShort : iLong;
        float yExtent = dir % 2 == 0 ? iLong : iShort;

        // Depending on the direction, 0 being up and following clockwise,
        // Set the bounds of the laser.
        if (dir == 0)
        {
            setBounds(new RectF(x, y, x + xExtent, y - yExtent));
        }
        else if (dir == 1)
        {
            setBounds(new RectF(x, y, x + xExtent, y + yExtent));
        }
        else if (dir == 2)
        {
            setBounds(new RectF(x, y, x + xExtent, y + yExtent));
        }
        else
        {
            setBounds(new RectF(x, y, x - xExtent, y + yExtent));
        }
    }


    /**
     * Place a description of your method here.
     *
     * @param x
     *            is the x velocity
     * @param y
     *            is the y velocity
     */
    public void move(float x, float y)
    {
        switch (direction)
        {
            case 0:
                this.setLinearVelocity(0, -y);
                break;
            case 1:
                this.setLinearVelocity(x, 0);
                break;
            case 2:
                this.setLinearVelocity(0, y);
                break;
            case 3:
                this.setLinearVelocity(-x, 0);
                break;
            default:
                break;
        }

    }


    // ----------------------------------------------------------
    /**
     * removes the marble when it is hit by the laser
     *
     * @param first
     *            is the marble
     */
    public void onCollisionWith(MarbleShape first)
    {
        first.animate(1000).rotation(720).fillColor(Color.red).alpha(0)
            .removeWhenComplete().play();
        this.remove();
    }

}
