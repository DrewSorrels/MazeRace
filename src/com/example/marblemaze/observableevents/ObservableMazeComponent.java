package com.example.marblemaze.observableevents;

import java.util.Observable;

// -------------------------------------------------------------------------
/**
 * Represents an observable maze component. This interface is necessary because
 * most maze components (walls, marbleshapes) extend some subclass of Shape and
 * thus cannot extend Observable.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.07
 */
public class ObservableMazeComponent extends Observable
{
    // ----------------------------------------------------------
    /**
     * Notifies observers that a change has occurred.
     * @param arg information about the change
     */
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }
}
