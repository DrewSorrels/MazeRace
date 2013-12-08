package com.example.marblemaze.observableevents;

import com.example.marblemaze.weapons.WeaponSpawner;

// -------------------------------------------------------------------------
/**
 * Event for when a weapon spawner is added to the maze.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class WeaponSpawnerAddedEvent
{
    private WeaponSpawner wep;


    // ----------------------------------------------------------
    /**
     * Create a new WeaponSpawnerAdded object.
     *
     * @param w
     *            The spawner that was added
     */
    public WeaponSpawnerAddedEvent(WeaponSpawner w)
    {
        wep = w;
    }


    // ----------------------------------------------------------
    /**
     * Getter for the WeaponSpawner that was added.
     *
     * @return the WeaponSpawner
     */
    public WeaponSpawner getWeaponSpawner()
    {
        return wep;
    }
}
