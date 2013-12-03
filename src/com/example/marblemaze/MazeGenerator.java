package com.example.marblemaze;

import java.util.List;
import java.util.ArrayList;

/**
 * // -------------------------------------------------------------------------
 * /** A random maze generator with various algorithms for maze generation.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.11.15
 */
public class MazeGenerator
{
    private ArrayList<Wall> cellWalls;
    private ArrayList<Cell> cells;
    private Maze            maze;


    /**
     * Creates a new generator.
     */
    public MazeGenerator()
    {
        maze = new Maze(15, 10);
        cellWalls.addAll(maze.getAdjacentWalls(maze.getCell(0, 0))); // All
// walls
// around startPoint
    }


    /**
     * Returns the maze.
     *
     * @return The maze
     */
    public Maze getMaze()
    {
        return maze;
    }


    /**
     * Generates a maze using a randomized form of Prim's Algorithm.
     */
    public void primMaze()
    {
        while (!cellWalls.isEmpty())
        {
            int randWall =
                (int)Math.floor(Math.random() * (cellWalls.size() - 1) + 0.5);
            List<Cell> cAdjCells = new ArrayList<Cell>();
            cAdjCells.addAll(maze.getAdjacentCells(cellWalls.get(randWall)));
            if (!cells.containsAll(cAdjCells)) // if the maze cell opposite the
                                               // wall
                                               // is not a part of the maze yet.
            {
                Cell cCurrent =
                    cells.contains(cAdjCells.get(0))
                        ? cAdjCells.get(0)
                        : cAdjCells.get(1);
                Cell cOpposite =
                    cells.contains(cAdjCells.get(0))
                        ? cAdjCells.get(1)
                        : cAdjCells.get(0);
                cells.add(cOpposite); // add the opposite cell
                int wallPos =
                    detWalls(cCurrent, cOpposite, cellWalls.get(randWall));
                cOpposite.setWall(cCurrent.oppositeWall(wallPos), false);
                cCurrent.setWall(wallPos, false); // Remove the wall at the
                                                  // position of randWall
                                                  // and set wall to false in
// each cell
                cellWalls.addAll(maze.getAdjacentWalls(cOpposite)); // add all
                                                                 // walls around
// that
            }
            else
            {
                cellWalls.remove(cellWalls.get(randWall));
            }
            cAdjCells.clear();
        }
    }


    /**
     * Determines the integer position of the wall between two cells.
     *
     * @param c1
     *            The first cell
     * @param c2
     *            The second cell
     * @param wall
     *            The wall separating the two cells.
     * @return Integer position of the wall between the two cells.
     */
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
