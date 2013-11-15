package com.example.marblemaze;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Cell
{

    private boolean[] walls;
    private int       x;
    private int       y;


    public Cell()
    {
        this(0, 0);
    }


    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
        for (int i = 0; i < walls.length; i++)
        {
            walls[i] = true;
        }

    }


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


    public int oppositeWall(int wallIndex)
    {
        return (wallIndex + 2) % walls.length;
    }


    public void setWall(int index, boolean val)
    {
        walls[index] = val;
    }


    public Cell east()
    {
        return new Cell(x + 1, y);
    }


    public Cell south()
    {
        return new Cell(x, y + 1);
    }


    public Cell west()
    {
        return new Cell(x - 1, y);
    }


    public Cell north()
    {
        return new Cell(x, y - 1);
    }


    public int getX()
    {
        return x;
    }


    public int getY()
    {
        return y;
    }
}
