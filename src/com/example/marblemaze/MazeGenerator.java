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
//          List<Cell> cAdjCells = new ArrayList<Cell>();
//            cAdjCells.add(maze.getAdjadcentCells(cellWalls.get(randWall)));
            // if(!cell.containsAll(cAdjCells)) //if the maze cell opposite the wall
// is not a part of the maze yet.
//            {
//            Cell cCurrent = cells.contains(cAdjCells.get(0) ? cAdjCells.get(0) : cAdjCells.get(1);
//                Cell cOpposite = cells.contains(cAdjCells.get(0) ? cAdjCells.get(1) : cAdjCells.get(0);
//                cells.add(cOpposite);  //add the opposite cell


//                      cCurrent.removeWall(cCurent.oppositeWall(wallPos));
//                    cOpposite.removeWall(wallPos);  //Remove the wall at the position of randWall and set wall to false in each cell
//                    cellWalls.add(maze.getAdjacentWalls(cOpposite))  //add all walls around that
//            } else {
//              cellWalls.remove(cellWalls.get(randWall));
//        }
//            adjCells.clear();
        }
    }
    private int detWalls() {
        return 1;
    }

}
