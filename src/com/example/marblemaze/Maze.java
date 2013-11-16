package com.example.marblemaze;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  The Maze is simply a maze through which the user must direct the marble to
 *  travel through.
 *
 *  @author nkilmer8
 *  @version Nov 15, 2013
 */
public class Maze
{
    private Cell[][] grid;
    private Wall[][] wallGrid;
    private Cell start;
    private Cell end;


    // ----------------------------------------------------------
    /**
     * Create a new Maze object.
     * @param size is the length of the side of the maze
     */
    public Maze(int size)
    {
        start = new Cell();
        end = new Cell(size - 1, size - 1);
        grid = new Cell[size][size];
        for(int i = 0; i < size; i++)
            for(int a = 0; i < size; a++)
            {
                grid[i][a] = new Cell();
            }


        wallGrid = new Wall[size+1][size+1];

        for(int i = 0; i < size+1; i++)
            for(int a = 0; i < size+1; a++)
            {
                wallGrid[i][a] = new Wall();
            }
    }

    // ----------------------------------------------------------
    /**
     * gets the wall that is next to the cell in the appropriate direction
     * @param example is the cell to be tested
     * @param direction is the direction to be tested
     * @return the specified cell wall
     */
    public Cell getWallFromCell(Cell example, int direction)
    {
        Cell cellWall = example;
        if(direction == 0)
        {
            cellWall = example.north();
        }
        else if(direction==1)
        {
            cellWall = example.east();
        }
        else if(direction==2)
        {
            cellWall = example.south();
        }
        else if(direction==3)
        {
            cellWall = example.west();
        }
        return cellWall;
    }

    // ----------------------------------------------------------
    /**
     * finds all the walls surroudning a specific cell.
     * @param example is the cell to be tested
     * @return an arraylist of the walls surrounding the cell
     */
    public ArrayList<Wall> getAdjacentWalls(Cell example)
    {
        int x = example.getX();
        int y = example.getY();
        ArrayList<Wall> wallArray = new ArrayList<Wall>();
        if(wallGrid[x+1][y].exists())
        {
            wallArray.add(wallGrid[x+1][y]);
        }
        else if(wallGrid[x][y+1].exists())
        {
            wallArray.add(wallGrid[x][y+1]);
        }
        else if(wallGrid[x-1][y].exists())
        {
            wallArray.add(wallGrid[x-1][y]);
        }
        else if(wallGrid[x][y-1].exists())
        {
            wallArray.add(wallGrid[x][y-1]);
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
     * @param wally is the specified wall
     * @return an arrayList of adjacent Cells
     */
    public ArrayList<Cell> getAdjacentCells(Wall wally)
    {
        int x = (int)wally.getX();
        int y = (int)wally.getY();
        ArrayList<Cell> cellArray = new ArrayList<Cell>();
        if(wally.isHorizontal())
        {
            cellArray.add(grid[x][y]);
            if(y-1>=0)
            {
                cellArray.add(grid[x][y-1]);
            }
        }
        else
        {
            cellArray.add(grid[x][y]);
            if(x-1>=0)
            {
                cellArray.add(grid[x-1][y]);
            }
        }
        return cellArray;
    }

    // ----------------------------------------------------------
    /**
     * @param a is the x-coordinate
     * @param b is the y-coordinate
     * @return the specified Cell
     */
    public Cell getCell(int a, int b)
    {
        return grid[a][b];
    }



}
