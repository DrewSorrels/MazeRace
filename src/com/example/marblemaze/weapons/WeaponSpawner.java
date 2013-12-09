package com.example.marblemaze.weapons;

import com.example.marblemaze.observableevents.ObservableMazeComponent;
import java.util.Observer;
import sofia.graphics.RectangleShape;
import sofia.util.Timer;

// -------------------------------------------------------------------------
/**
 * Spawners create dangerous objects that the marble must avoid.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.07
 */
public abstract class WeaponSpawner
    extends RectangleShape
{

    /**
     * the observer for the weapon spawner
     */
    protected ObservableMazeComponent observer;


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
        super(x, y, x + 1, y + 1);
        long                    cooldown;
        cooldown = cd;
        observer = new ObservableMazeComponent();

        Timer.callRepeatedly(this, "createBullet", cooldown);

    }


    // ----------------------------------------------------------
    /**
     * Creates and fires a bullet.
     *
     * @return the bullet that was fired.
     */
    public abstract Bullet createBullet();


    // ----------------------------------------------------------
    /**
     * Adds an observer that would like to be notified of changes to this wall.
     *
     * @param obs
     *            the observer in question
     */
    public void addObserver(Observer obs)
    {
        observer.addObserver(obs);
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
        observer.notifyObservers(arg);
    }

}
