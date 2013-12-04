package com.example.marblemaze;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * The Maze is simply a maze through which the user must direct the marble to
 * travel through.
 *
 * @author nkilmer8
 * @version Nov 15, 2013
 */
public class Maze
{
    private Cell[][] grid;
    private Wall[][] wallGrid;
    private Cell     start;
    private Cell     end;


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
        for (int i = 0; i < width; i++)
        {
            for (int a = 0; a < height; a++)
            {
                grid[i][a] = new Cell();
            }
        }

        wallGrid = new Wall[width + 1][height + 1];

        for (int i = 0; i < width + 1; i++)
        {
            for (int a = 0; i < height + 1; a++)
            {
                wallGrid[i][a] = new Wall();
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
    public Wall getWallFromCell(Cell example, int direction) //WE MAY HAVE TO CHANGE THE WAY THAT THIS CLASS WORKS FUNDAMENTALLY TO INCORPORATE WALLS PROPERLY.
    {
        Wall cellWall;// = example;
        if (direction == 0)
        {
            cellWall = wallGrid[example.getX()][example.getY()];//example.north();
        }
        else if (direction == 1)
        {
            cellWall = wallGrid[example.getX() + 1][example.getY()];
        }
        else if (direction == 2)
        {
            cellWall = wallGrid[example.getX() + 1][example.getY() + 1];
        }
        else
        {
            cellWall = wallGrid[example.getX()][example.getY() + 1];
        }
        return cellWall;
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
        if (wallGrid[x + 1][y].exists())
        {
            wallArray.add(wallGrid[x + 1][y]);
        }
        else if (wallGrid[x][y + 1].exists())
        {
            wallArray.add(wallGrid[x][y + 1]);
        }
        else if (wallGrid[x - 1][y].exists())
        {
            wallArray.add(wallGrid[x - 1][y]);
        }
        else if (wallGrid[x][y - 1].exists())
        {
            wallArray.add(wallGrid[x][y - 1]);
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
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                if(grid[i][j].getNumWalls() == 3)
                {
                    if(Math.random()<.2)
                    {
                        grid[i][j].makeHole();
                    }

                }
            }
        }
    }
}
