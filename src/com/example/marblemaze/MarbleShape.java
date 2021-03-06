package com.example.marblemaze;

import com.example.marblemaze.observableevents.MarbleRemovedEvent;
import com.example.marblemaze.observableevents.ObservableMazeComponent;
import java.util.Observer;
import sofia.graphics.Color;
import sofia.graphics.OvalShape;
import sofia.graphics.ShapeMotion;

// -------------------------------------------------------------------------
/**
 * This marble is an oval shape that rolls around on the phone screen.
 *
 * @author Dennis Lysenko (dlysenko)
 * @author Nicholas Kilmer (nkilmer8)
 * @version 2013.12.08
 */

public class MarbleShape
    extends OvalShape
{
    private static final float      DENSITY     = 10f;
    private static final float      RADIUS      = 0.3f;
    private static final float      FRICTION    = 0.4f;
    private static final float      RESTITUTION = 0.3f;

    private ObservableMazeComponent observable;

    /**
     * When the marble hits a hole, dying is set to true so that subsequent
     * collisions with holes do not start unnecessary animations.
     */
    private boolean                 dying;


    // ----------------------------------------------------------
    /**
     * Create a new Marble object at the specified location.
     *
     * @param x
     *            the starting x-location
     * @param y
     *            the starting y-location
     */
    public MarbleShape(float x, float y)
    {
        super(x, y, MarbleShape.RADIUS);

        this.observable = new ObservableMazeComponent();

        this.setDensity(MarbleShape.DENSITY);
        this.setFillColor(Color.gray);
        this.setColor(Color.black);
        this.setFriction(MarbleShape.FRICTION);
        this.setShapeMotion(ShapeMotion.DYNAMIC); // dynamic = respond to force
        this.setRestitution(MarbleShape.RESTITUTION); // "bouncy-ness"
    }



    @Override
    public void remove()
    {
        super.remove();
        notifyObservers(new MarbleRemovedEvent(this));
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


    // ----------------------------------------------------------
    /**
     * Returns the value of <code>dying</code> See the javadoc for
     * <code>dying</code> for an explanation of its use.
     *
     * @return the value of <code>dying</code>
     */
    public boolean isDying()
    {
        return dying;
    }


    // ----------------------------------------------------------
    /**
     * Sets the value of <code>dying</code> See the javadoc for
     * <code>dying</code> for an explanation of its use.
     * @param dying the value to set
     */
    public void setDying(boolean dying)
    {
        this.dying = dying;
    }
}
