package com.example.marblemaze;

import java.util.ArrayList;

public class MazeGenerator
{
    private ArrayList<Wall> cellWalls;
    private ArrayList<Cell> cells;
    private Maze            maze;


    public MazeGenerator()
    {
        maze = new Maze();
// cellWalls.add(maze.); //All walls around startPoint
    }


    public void primMaze()
    {
        while (!cellWalls.isEmpty())
        {
            int randWall =
                (int)Math.floor(Math.random() * (cellWalls.size() - 1) + 0.5);

            // if(cellWalls.get(randWall)) //if the maze cell opposite the wall
// is not a part of the maze yet.
//            {
//                cells.add(oppositeCell);  //add the opposite cell
//                    cellWalls.add(maze.)  //add all walls around that
//            } else {
//              cellWalls.remove(cellWalls.get(randWall));
//        }
        }
    }
}
