package com.example.marblemaze;

import java.util.ArrayList;

/**
 * Cell is a helper class to make generating mazes easier. NOTE: Different
 * from cells in the maze solver project.
 *
 * @author Drew Sorrels (amsorr)
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.06
 */
public class Cell
{

    private Wall[]  walls;
    private int     x;
    private int     y;
    private boolean isHole;


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
        walls = new Wall[4];
        this.x = x;
        this.y = y;
        for (int i = 0; i < walls.length; i++)
        {
            if (i == 0 || i == 3)
            {
                walls[i] = new Wall(x, y, i % 2 == 0);
            }
            else if (i == 1)
            {
                walls[i] = new Wall(x + 1, y, i % 2 == 0);
            }
            else
            {
                walls[i] = new Wall(x, y + 1, i % 2 == 0);
            }
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
            if (walls[i].exists())
            {
                count++;
            }
        }
        return count;
    }


    /**
     * Returns an ArrayList of the walls.
     *
     * @return ArrayList<Wall> of all walls that exist.
     */
    public ArrayList<Wall> getWalls()
    {
        ArrayList<Wall> ilWalls = new ArrayList<Wall>();
        for (int i = 0; i < walls.length; i++)
        {
            if (walls[i].exists())
            {
                ilWalls.add(walls[i]);
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
        while (!walls[randIndex].exists())
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
            walls[index].setWall(val);
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
     *
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
