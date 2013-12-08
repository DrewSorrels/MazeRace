package com.example.marblemaze.weapons;

import java.util.Observer;
import sofia.graphics.Shape;

// -------------------------------------------------------------------------
/**
 * The Bullet interface provides methods that the laser and rocket must
 * implement
 *
 * @author Nicholas Kilmer (nkilmer8)
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.06
 */
public interface Bullet
{

    // ----------------------------------------------------------
    /**
     * moves the bullet
     *
     * @param x
     *            is the x velocity
     * @param y
     *            is the y velocity
     */
    void move(float x, float y);


    // ----------------------------------------------------------
    /**
     * Removes the bullet.
     */
    void remove();


    /**
     * Returns the shape in the bullet.
     *
     * @return A Bullet (Shape)
     */
    Shape getShape();


    // ----------------------------------------------------------
    /**
     * Adds an observer that would like to be notified of changes to this wall.
     *
     * @param obs
     *            the observer in question
     */
    void addObserver(Observer obs);


    // ----------------------------------------------------------
    /**
     * Notifies all of the given observers that a change has been made.
     *
     * @param arg
     *            information about the change that was made
     */
    void notifyObservers(Object arg);
}
