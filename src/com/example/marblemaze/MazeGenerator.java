package com.example.marblemaze;

import java.util.ArrayList;

public class MazeGenerator
{
    private ArrayList<Wall> cellWalls;
    private ArrayList<Cell> cells;
    private Maze            maze;


    public MazeGenerator()
    {
        maze = new Maze(10);
// cellWalls.add(maze.); //All walls around startPoint
    }


    public void primMaze()
    {
        while (!cellWalls.isEmpty())
        {
            int randWall =
                (int)Math.floor(Math.random() * (cellWalls.size() - 1) + 0.5);
// List<Cell> cAdjCells = new ArrayList<Cell>();
// cAdjCells.add(maze.getAdjadcentCells(cellWalls.get(randWall)));
            // if(!cell.containsAll(cAdjCells)) //if the maze cell opposite the
// wall
// is not a part of the maze yet.
// {
// Cell cCurrent = cells.contains(cAdjCells.get(0) ? cAdjCells.get(0) :
// cAdjCells.get(1);
// Cell cOpposite = cells.contains(cAdjCells.get(0) ? cAdjCells.get(1) :
// cAdjCells.get(0);
// cells.add(cOpposite); //add the opposite cell
// int wallPos = detWalls(cCurrent, cOpposite, cellWalls.get(randWall));
// cOpposite.removeWall(cCurent.oppositeWall(wallPos));
// cCurrent.removeWall(wallPos); //Remove the wall at the position of randWall
// and set wall to false in each cell
// cellWalls.add(maze.getAdjacentWalls(cOpposite)) //add all walls around that
// } else {
// cellWalls.remove(cellWalls.get(randWall));
// }
// adjCells.clear();
        }
    }


    private int detWalls(Cell c1, Cell c2, Wall wall)
    {
        if (wall.isHorizontal())
        {
            if (c1.getY() + 1 == c2.getY())
            {
                return 2;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            if (c1.getX() + 1 == c2.getX())
            {
                return 1;
            }
            else
            {
                return 3;
            }
        }

    }

}
