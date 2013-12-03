package com.example.marblemaze;

import java.util.ArrayList;

/**
 * // -------------------------------------------------------------------------
 * /** Cell is a helper class to make generating mazes easier. NOTE: Different
 * from cells in the maze solver project.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.02
 */
public class Cell
{

    private boolean[] walls;
    private int       x;
    private int       y;
    private boolean isHole;


    /**
     * Creates a new Cell object.
     */
    public Cell()
    {
        this(0, 0);
    }


    /**
     * Creates a new Cell at position x, y.
     *
     * @param x
     *            The x coordinate of the cell.
     * @param y
     *            The y coordinate of the cell.
     */
    public Cell(int x, int y)
    {
        walls = new boolean[4];
        this.x = x;
        this.y = y;
        for (int i = 0; i < walls.length; i++)
        {
            walls[i] = true;
        }
        isHole = false;

    }


    /**
     * Returns the number of walls around the cell.
     *
     * @return Number of walls around this cell.
     */
    public int getNumWalls()
    {
        int count = 0;
        for (int i = 0; i < walls.length; i++)
        {
            if (walls[i])
            {
                count++;
            }
        }
        return count;
    }


    /**
     * Returns an ArrayList of the wall positions in an int form. 0 is north,
     * and follows a clockwise direction.
     *
     * @return ArrayList<Integer> with wall positions.
     */
    public ArrayList<Integer> getWalls()
    {
        ArrayList<Integer> ilWalls = new ArrayList<Integer>();
        for (int i = 0; i < walls.length; i++)
        {
            if (walls[i])
            {
                ilWalls.add(i);
            }
        }
        return ilWalls;
    }


    /**
     * Gets a random integer position for a wall around this cell. Only returns
     * walls that exist.
     *
     * @return A position of a wall.
     */
    public int getRandomWallIndex()
    {
        int randIndex =
            (int)Math.floor((Math.random() * (walls.length - 1)) + 0.5);
        while (!walls[randIndex])
        {
            randIndex =
                (int)Math.floor((Math.random() * (walls.length - 1)) + 0.5);
        }
        return randIndex;
    }


    /**
     * Find the opposite wall index
     *
     * @param wallIndex
     *            The int position of the wall that you want to find the
     *            opposite position of.
     * @return The int position of the wall opposite the wallIndex
     */
    public int oppositeWall(int wallIndex)
    {
        return (wallIndex + 2) % walls.length;
    }


    /**
     * Set the value of a wall at given position.
     *
     * @param index
     *            The int index of the wall to be changed.
     * @param val
     *            Boolean value to set the wall to.
     */
    public void setWall(int index, boolean val)
    {
        try
        {
            walls[index] = val;
        }
        catch (Exception e)
        {
            System.out.println("Outside bounds of the walls array");
        }
    }


    /**
     * The cell one to the right.
     *
     * @return The cell to the right.
     */
    public Cell east()
    {
        return new Cell(x + 1, y);
    }


    /**
     * The cell one down.
     *
     * @return Cell one down.
     */
    public Cell south()
    {
        return new Cell(x, y + 1);
    }


    /**
     * Cell one left
     *
     * @return Cell to the left.
     */
    public Cell west()
    {
        return new Cell(x - 1, y);
    }


    /**
     * Cell one up.
     *
     * @return The cell one up.
     */
    public Cell north()
    {
        return new Cell(x, y - 1);
    }


    /**
     * Get the x coordinate of the cell.
     *
     * @return x coordinate of the cell.
     */
    public int getX()
    {
        return x;
    }


    /**
     * Gets the y coordinate of the cell.
     *
     * @return y coordinate of the cell.
     */
    public int getY()
    {
        return y;
    }

    // ----------------------------------------------------------
    /**
     * determines if the cell is a hole
     * @return boolean isTrue
     */
    public boolean isHole()
    {
        return isHole;
    }

    // ----------------------------------------------------------
    /**
     * sets the cell to be a hole
     */
    public void makeHole()
    {
        isHole = true;
    }

}
