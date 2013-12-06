package com.example.marblemaze;

import java.util.Stack;
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
    // Prims Algorithm stuff
    private ArrayList<Wall>  cellWalls;
    private ArrayList<Cell>  cells;

    private Stack<Cell>      sCells;

    private Maze             maze;

    private static final int MAZE_WIDTH  = 15;
    private static final int MAZE_HEIGHT = 10;


    /**
     * Creates a new generator.
     */
    public MazeGenerator()
    {
        maze = new Maze(MAZE_WIDTH, MAZE_HEIGHT);
        cellWalls = new ArrayList<Wall>();
        cells = new ArrayList<Cell>();

        cellWalls.addAll(maze.getAdjacentWalls(maze.getCell(0, 0))); // All
// walls
// around startPoint

        sCells = new Stack<Cell>();
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
     * Generates a maze based on the DFS Algorithm.
     */
    public void dfsMaze()
    {
        Cell end = maze.getGoal();
        sCells.push(end);

        for (int i = 0; i < maze.width(); i++)
        {
            for (int j = 0; j < maze.height(); j++)
            {
                cells.add(maze.getCell(i, j));
            }
        }

        while (!cells.isEmpty())
        {
            ArrayList<Cell> tempCells = getUnvisitedNeighbors(sCells.peek());
            if (tempCells.size() > 0)
            {
                Cell currentCell =
                    tempCells.get((int)Math.random() * (tempCells.size() - 1));

                sCells.push(currentCell);
                cells.remove(currentCell);

            }
            else
            {
                sCells.pop();
            }
        }

    }


// /**
// * Returns a random cell adjacent to the supplied cell.
// *
// * @param c
// * The cell
// * @return a random cell around this cell.
// */
// private Cell getRandAdjCell(Cell c)
// {
// cells.clear(); // Clear cell list to make sure there are not any cells
// // in it.
// int wIndex = c.getRandomWallIndex();
// Wall w = maze.getWallFromCell(c, wIndex);
// cells.addAll(maze.getAdjacentCells(w));
// // Add them all to the cell list and find a random one.
// Cell randCell = cells.get((int)Math.random() * (cells.size() - 1));
// cells.clear(); // Clear cell list.
//
// return randCell;
// }

    private ArrayList<Cell> getUnvisitedNeighbors(Cell c)
    {
        ArrayList<Cell> adjCells = new ArrayList<Cell>();

        if (!c.east().isVisited())
        {
            adjCells.add(c.east());
        }
        if (!c.west().isVisited())
        {
            adjCells.add(c.west());
        }
        if (!c.north().isVisited())
        {
            adjCells.add(c.north());
        }
        if (!c.south().isVisited())
        {
            adjCells.add(c.south());
        }

        return adjCells;

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
                // If the cell is already in the list, make this the current
                // cell. Otherwise add the other cell.
                Cell cCurrent =
                    cells.contains(cAdjCells.get(0))
                        ? cAdjCells.get(0)
                        : cAdjCells.get(1);
                // Add the other cell.
                Cell cOpposite =
                    cells.contains(cAdjCells.get(0))
                        ? cAdjCells.get(1)
                        : cAdjCells.get(0);

                cells.add(cOpposite); // add the opposite cell
                int wallPos =
                    detWalls(cCurrent, cOpposite, cellWalls.get(randWall)); // randWall
// is the position of the randomWall in between the two cells.

                cOpposite.setWall(cCurrent.oppositeWall(wallPos), false);
                cCurrent.setWall(wallPos, false); // Remove the wall at the
                                                  // position of randWall
                                                  // and set wall to false in
                                                  // each cell
                // Only add walls that are not already in the maze.
                for (Wall w : maze.getAdjacentWalls(cOpposite))
                {
                    if (!cellWalls.contains(w))
                    {
                        cellWalls.add(w);
                    }
                }
            }
            else
            // If the cell on the otherside of the wall is in the list of walls,
// remove it.
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
            if (c1.getY() + 1 == c2.getY()) // If the first cell is above the
// second cell
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
            if (c1.getX() + 1 == c2.getX()) // If the first cell is to the left
// of the second cell
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
