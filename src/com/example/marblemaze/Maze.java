package com.example.marblemaze;

import java.util.List;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * The Maze is simply a maze through which the user must direct the marble to
 * travel through.
 *
 * @author nkilmer8, amsorr
 * @version Nov 15, 2013
 */
public class Maze
{
    private Cell[][]    grid;
    private List<Wall>  walls;
    private Cell        start;
    private Cell        end;
    private MarbleShape marble;


    // ----------------------------------------------------------
    /**
     * Create a new Maze object.
     *
     * @param width
     *            is the width of the side of the maze
     * @param height
     *            is the height of the maze
     */
    public Maze(int width, int height)
    {
        start = new Cell();
        end = new Cell(width - 1, height - 1);
        grid = new Cell[width][height];
        walls = new ArrayList<Wall>();

        List<Wall> temp = new ArrayList<Wall>();
        for (int i = 0; i < width; i++)
        {
            for (int a = 0; a < height; a++)
            {
                grid[i][a] = new Cell();
                temp.addAll(grid[i][a].getWalls());
                // Iterate over each of the walls to add it to the list of walls
                // if a wall at that position and orientation isn't already
                // there.
                for (Wall w : temp)
                {
                    if (!walls.contains(w))
                    {
                        walls.add(w);
                    }
                }

            }
        }
    }


    // ----------------------------------------------------------
    /**
     * gets the wall that is next to the cell in the appropriate direction
     *
     * @param example
     *            is the cell to be tested
     * @param direction
     *            is the direction to be tested
     * @return the specified cell wall
     */
    public Wall getWallFromCell(Cell example, int direction)
    {
        Wall cellWall;// = example;

        if (direction == 0 || direction == 3)
        {
            cellWall =
                findWall(example.getX(), example.getY(), direction % 2 == 0);
            // The wall that is at the top left corner horizontal or vertical
            // based on the position (0 is horizontal)
        }
        else if (direction == 1)
        {
            cellWall = findWall(example.getX() + 1, example.getY(), false);
        }
        else
        {
            cellWall = findWall(example.getX(), example.getY() + 1, true);
        }
        return cellWall;
    }


    /**
     * Returns a wall at the given position and orientation
     *
     * @param x
     *            The x coordinate of the wall
     * @param y
     *            The y coordinate of the wall
     * @param horizontal
     *            Whether it is horizontal or not.
     * @return A Wall with the given stats or null if it isn't there.
     */
    private Wall findWall(int x, int y, boolean horizontal)
    {
        for (Wall w : walls)
        {
            if (w.equals(new Wall(x, y, horizontal)))
            {
                return w;
            }
        }
        return null;
    }


    // ----------------------------------------------------------
    /**
     * finds all the walls surroudning a specific cell.
     *
     * @param example
     *            is the cell to be tested
     * @return an arraylist of the walls surrounding the cell
     */
    public ArrayList<Wall> getAdjacentWalls(Cell example)
    {
        int x = example.getX();
        int y = example.getY();
        ArrayList<Wall> wallArray = new ArrayList<Wall>();

        for (Wall w : walls)
        {
            // If it's x is equal and y is equal or one larger. Only for when it
            // is horizontal
            if (w.isHorizontal()
                && (w.getX() == example.getX() && (w.getY() == example.getY() || w
                    .getY() == example.getY() + 1)))
            {
                wallArray.add(w);
            } // If it is vertical, then y must be the same and x must be same
              // or one higher.
            else if (!w.isHorizontal()
                && ((w.getX() == example.getX() || w.getX() == example.getX() + 1) && w
                    .getY() == example.getY()))
            {
                wallArray.add(w);
            }
        }
        return wallArray;
    }


    // ----------------------------------------------------------
    /**
     * @return the starting location
     */
    public Cell getStart()
    {
        return start;
    }


    // ----------------------------------------------------------
    /**
     * @return ending location
     */
    public Cell getGoal()
    {
        return end;
    }


    // ----------------------------------------------------------
    /**
     * Finds the adjacent Cells
     *
     * @param wally
     *            is the specified wall
     * @return an arrayList of adjacent Cells
     */
    public ArrayList<Cell> getAdjacentCells(Wall wally)
    {
        int x = (int)wally.getX();
        int y = (int)wally.getY();
        ArrayList<Cell> cellArray = new ArrayList<Cell>();
        if (wally.isHorizontal())
        {
            cellArray.add(grid[x][y]);
            if (y - 1 >= 0)
            {
                cellArray.add(grid[x][y - 1]);
            }
        }
        else
        {
            cellArray.add(grid[x][y]);
            if (x - 1 >= 0)
            {
                cellArray.add(grid[x - 1][y]);
            }
        }
        return cellArray;
    }


    // ----------------------------------------------------------
    /**
     * @param a
     *            is the x-coordinate
     * @param b
     *            is the y-coordinate
     * @return the specified Cell
     */
    public Cell getCell(int a, int b)
    {
        return grid[a][b];
    }


    // ----------------------------------------------------------
    /**
     * makes certain cells Holes - only cells surounded on three sides by walls
     */
    public void Hole()
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (grid[i][j].getNumWalls() == 3)
                {
                    if (Math.random() < .2)
                    {
                        grid[i][j].makeHole();
                    }

                }
            }
        }
    }


    public MarbleShape getMarble()
    {
        return marble;
    }


    public void setMarble(MarbleShape marble)
    {
        this.marble = marble;
    }

}
