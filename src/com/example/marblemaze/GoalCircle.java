package com.example.marblemaze;

import java.util.Observer;
import android.graphics.RectF;
import com.example.marblemaze.observableevents.ObservableMazeComponent;
import com.example.marblemaze.observableevents.VictoryEvent;
import sofia.graphics.Color;
import sofia.graphics.OvalShape;

// -------------------------------------------------------------------------
/**
 * A circle that represents the goal cell of the maze. When touched, it takes
 * the user to the victory screen.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.08
 */
public class GoalCircle
    extends OvalShape
{
    private ObservableMazeComponent observable;

    // ----------------------------------------------------------
    /**
     * Create a new GoalCircle object.
     *
     * @param bounds
     *              WHAT THE FOX SAY
     */
    public GoalCircle(RectF bounds)
    {
        super(bounds);
        setFillColor(Color.forestGreen);
        observable = new ObservableMazeComponent();
    }


    /**
     * this method declares what will happen when the user runs into a hole
     *
     * @param first
     *            is the marble
     */
    public void onCollisionWith(MarbleShape first)
    {
        first.animate(1000).color(Color.yellow).alpha(100).play();
        notifyObservers(new VictoryEvent());
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
