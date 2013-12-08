package com.example.marblemaze.weapons;

import com.example.marblemaze.observableevents.BulletRemovedEvent;
import com.example.marblemaze.observableevents.MarbleRemovedEvent;
import java.util.Observer;
import com.example.marblemaze.observableevents.ObservableMazeComponent;
import android.graphics.RectF;
import sofia.graphics.Color;
import com.example.marblemaze.MarbleShape;
import sofia.graphics.RectangleShape;
import sofia.graphics.Shape;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author nkilmer84
 * @author amsorr
 * @version Dec 6, 2013
 */
public class Rocket
    extends RectangleShape
    implements Bullet
{

    private static final float      DENSITY     = 10f;
    private static final float      FRICTION    = 0.4f;
    private static final float      RESTITUTION = 0.0f;

    private int                     direction;
    private ObservableMazeComponent observable;


    // ----------------------------------------------------------
    /**
     * Create a new Rocket object.
     *
     * @param x
     * @param y
     * @param dir
     */
    public Rocket(int x, int y, int dir)
    {
        super();
        this.setBullet(true);
        this.observable = new ObservableMazeComponent();

        this.setColor(Color.blue);
        this.setFillColor(Color.blue);
        this.setFriction(Rocket.FRICTION);
        this.setRestitution(Rocket.RESTITUTION);
        this.setDensity(Rocket.DENSITY);

        this.direction = dir;
        // Set up bounds
        float iShort = 0.5f;
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
     * removes the marble when it is hit by the rocket
     *
     * @param first
     *            is the marble/bullet
     */
    public void onCollisionWith(MarbleShape first)
    {
        first.animate(2000).rotation(560).alpha(0).removeWhenComplete().play();
        this.remove();
    }


    /**
     * Removes the Rocket.
     */
    public void remove()
    {
        super.remove();
        notifyObservers(new BulletRemovedEvent(this));
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
     * Returns this rocket as a shape.
     */
    public Shape getShape()
    {
        return this;
    }

}
