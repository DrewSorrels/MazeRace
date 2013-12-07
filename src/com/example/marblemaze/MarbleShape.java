package com.example.marblemaze;

import java.util.Observable;
import java.util.Observer;
import com.example.marblemaze.observableevents.MarbleRemovedEvent;
import com.example.marblemaze.observableevents.ObservableMazeComponent;
import sofia.graphics.Color;
import sofia.graphics.OvalShape;
import sofia.graphics.ShapeMotion;

// -------------------------------------------------------------------------
/**
 *  This marble is an oval shape that rolls around on the phone screen.
 *
 *  @author Dennis Lysenko (dlysenko)
 *  @author Nicholas Kilmer (nkilmer8)
 *  @version 2013.12.06
 */

public class MarbleShape
    extends OvalShape implements ObservableMazeComponent
{
    private static final float DENSITY = 10f;
    private static final float RADIUS = 0.3f;
    private static final float FRICTION = 0.4f;
    private static final float RESTITUTION = 0.6f;

    private Observable observable;


    // ----------------------------------------------------------
    /**
     * Create a new Marble object at the specified location.
     * @param x the starting x-location
     * @param y the starting y-location
     */
    public MarbleShape(float x, float y)
    {
        super(x, y, MarbleShape.RADIUS);

        this.observable = new Observable();

        this.setDensity(MarbleShape.DENSITY);
        this.setFillColor(Color.gray);
        this.setColor(Color.black);
        this.setFriction(MarbleShape.FRICTION);
        this.setShapeMotion(ShapeMotion.DYNAMIC); // dynamic = respond to force
        this.setRestitution(MarbleShape.RESTITUTION); // "bouncy-ness"
    }

    // ----------------------------------------------------------
    /**
     * sets the gravity of the marble
     */
    public void setGravityToZero()
    {
        this.setGravityScale(0);
    }

    @Override
    public void remove() {
        super.remove();
        notifyObservers(new MarbleRemovedEvent(this));
    }

    public void addObserver(Observer obs)
    {
        observable.addObserver(obs);
    }

    public void notifyObservers(Object arg)
    {
        observable.notifyObservers(arg);
    }
}
