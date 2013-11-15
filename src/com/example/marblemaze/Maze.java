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
        wallGrid = new Wall[size][size];

        for(int i = 0; i < size; i++)
            for(int a = 0; i < size; a++)
            {
                wallGrid[i][a] = new Wall();
            }
    }

    // ----------------------------------------------------------
    /**
     * gets the wall that is next to the cell in the appropriate direction
     * @param example is the cell to be tested
     * @param poop is the direction to be tested
     * @return the specified cell wall
     */
    public Cell getWallFromCell(Cell example, int poop)
    {
        Cell cellWall = example;
        if(poop == 0)
        {
            cellWall = example.north();
        }
        else if(poop==1)
        {
            cellWall = example.east();
        }
        else if(poop==2)
        {
            cellWall = example.south();
        }
        else if(poop==3)
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
    public ArrayList<Wall> getWalls(Cell example)
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



}
