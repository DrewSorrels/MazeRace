package com.example.marblemaze;

import android.graphics.RectF;
import com.example.marblemaze.observableevents.ObservableMazeComponent;
import com.example.marblemaze.observableevents.WallAddedEvent;
import com.example.marblemaze.observableevents.WallRemovedEvent;
import java.util.Observer;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

/**
 * // -------------------------------------------------------------------------
 * /** Wall acts as the borders and walls in the maze. Collides with the ball.
 *
 * @author Drew Sorrels (amsorr)
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.07
 */
public class Wall
    extends RectangleShape
{
    private float      xStart;
    private float      yStart;
    private float      height;
    private float      width;
    private boolean    horizontal;
    private boolean    exists;

    private ObservableMazeComponent observable;


    /**
     * Default constructor for a wall.
     */
    public Wall()
    {
        this(0, 0, true);
    }


    /**
     * Creates a new wall object.
     *
     * @param x
     *            The x coordinate of the wall
     * @param y
     *            The y coordinate of the wall
     * @param horizontal
     *            Boolean orientation of the wall.
     */
    public Wall(float x, float y, boolean horizontal)
    {
        super();

        this.observable = new ObservableMazeComponent();

        xStart = x;
        yStart = y;
        this.horizontal = horizontal;
        this.exists = true;

        setFillColor(Color.black);

        // Set up bounds
        float iShort = 0.1f;
        float iLong = 1.1f;
        float xExtent = horizontal ? iLong : iShort;
        float yExtent = horizontal ? iShort : iLong;
        setBounds(new RectF(xStart, yStart, xStart + xExtent, yStart + yExtent));
    }


    /**
     * Destroys the wall.
     */
    public void destroyWall()
    {
        exists = false;
        notifyObservers(new WallRemovedEvent(this));
    }


    /**
     * Sets the wall to the given value
     *
     * @param value
     *            If the wall exists or not.
     */
    public void setWall(boolean value)
    {
        if (exists == value)
        {
            return; // so that we don't unnecessarily notify observers
        }

        exists = value;

        if (exists)
        {
            notifyObservers(new WallAddedEvent(this));
        }
        else
        {
            notifyObservers(new WallRemovedEvent(this));
        }
    }


    /**
     * Returns whether the wall is present on the maze.
     *
     * @return If the wall exists.
     */
    public boolean exists()
    {
        return exists;
    }


    /**
     * Determines if the walls occupy the same position and orientation.
     *
     * @param other
     *            The other wall to compare this to.
     * @return Whether the walls are the same.
     */
    public boolean equals(Object other)
    {
        if (other instanceof Wall)
        {
            return xStart == ((Wall)other).getX()
                && yStart == ((Wall)other).getY()
                && horizontal == ((Wall)other).isHorizontal();
        }
        return false;

    }


    /**
     * Returns whether the wall is horizontal.
     *
     * @return boolean if the wall is horizontal.
     */
    public boolean isHorizontal()
    {
        return horizontal;
    }


    /**
     * Sets width and height based on orientation of the wall.
     */
    public void setWidthAndHeight()
    {
        if (horizontal)
        {
            height = 0.8f;
            width = 3;
        }
        else
        {
            height = 3;
            width = 0.8f;
        }

        // Sort of a hackaround to re-add the wall to the MazeScreen
        // to reflect its new bounds
        notifyObservers(new WallRemovedEvent(this));
        notifyObservers(new WallAddedEvent(this));
    }


    /**
     * Returns the x coordinate of the wall
     *
     * @return The x coordinate
     */
    public float getX()
    {
        return xStart;
    }


    /**
     * Returns y coordinate of the wall.
     *
     * @return The y coordinate
     */
    public float getY()
    {
        return yStart;
    }


    /**
     * Returns the width of the wall.
     *
     * @return the width.
     */
    public float getWidth()
    {
        return width;
    }


    /**
     * Returns the height of the wall.
     *
     * @return the height
     */
    public float getHeight()
    {
        return height;
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

}
