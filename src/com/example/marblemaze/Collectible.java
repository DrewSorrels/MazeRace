package com.example.marblemaze;

/**
 * // -------------------------------------------------------------------------
 * /** Items that can be collected in mazes.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.06
 */
public class Collectible
{
    private int x;
    private int y;


    /**
     * Creates a new Collectible object.
     *
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public Collectible(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * Returns the x coordinate
     *
     * @return the x coordinate
     */
    public int getX()
    {
        return x;
    }


    /**
     * Returns the y coordinate
     *
     * @return the y coordinate.
     */
    public int getY()
    {
        return y;
    }

//    public void collideWith();


}
