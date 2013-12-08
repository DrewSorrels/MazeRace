package com.example.marblemaze.weapons;

import android.graphics.RectF;
import com.example.marblemaze.Wall;
import com.example.marblemaze.MarbleShape;
import com.example.marblemaze.observableevents.BulletAddedEvent;
import com.example.marblemaze.observableevents.BulletRemovedEvent;
import com.example.marblemaze.observableevents.ObservableMazeComponent;
import java.util.Observer;
import sofia.graphics.Color;
import sofia.graphics.LineShape;
import sofia.graphics.Shape;

// -------------------------------------------------------------------------
/**
 * This method will create a laser that the user must doge
 *
 * @author Nicholas Kilmer (nkilmer8)
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class Laser
    extends LineShape
    implements Bullet
{
    private static final float      DENSITY     = 1f;
    private static final float      FRICTION    = 0.01f;
    private static final float      RESTITUTION = 0.2f;

    private int                     direction;
    private ObservableMazeComponent observable;


    // ----------------------------------------------------------
    /**
     * Create a new Laser object.
     *
     * @param x
     *            the x-coordinate to create it at
     * @param y
     *            the y-coordinate to create it at
     * @param dir
     *            the direction the laser shoots. 0 = up, 1 = right, 2 = down, 3
     *            = left
     */
    public Laser(float x, float y, int dir)
    {
        super();
        this.setBullet(true);
        this.observable = new ObservableMazeComponent();

        this.setColor(Color.red);
        this.setFriction(Laser.FRICTION);
        this.setRestitution(Laser.RESTITUTION);
        this.setDensity(Laser.DENSITY);
        //this.setGravityScale(0.0f);

        this.direction = dir;
        // Set up bounds
        float iShort = 0.05f;
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
        notifyObservers(new BulletAddedEvent(this));
    }


    /**
     * moves the laser
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


    /**
     * Removes the laser.
     */
    public void remove()
    {
        super.remove();
        notifyObservers(new BulletRemovedEvent(this));
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

    // ----------------------------------------------------------
    /**
     * removes the laser when it hits a wall
     * @param walle is the wall that the laser is colliding with
     */
    public void onCollisionWith(Wall walle) {
        this.remove();
    }


    // ----------------------------------------------------------
    /**
     * Adds an observer that would like to be notified of changes to this wall.
     *
     * @param obs
     *            the observer in question
     */
    public void addObserver(Observer obs)
    {
        observable.addObserver(obs);
    }


    // ----------------------------------------------------------
    /**
     * Notifies all of the given observers that a change has been made.
     *
     * @param arg
     *            information about the change that was made
     */
    public void notifyObservers(Object arg)
    {
        observable.notifyObservers(arg);
    }


    /**
     * @return this laser as a bullet.
     */
    public Shape getShape()
    {
        return this;
    }
}
