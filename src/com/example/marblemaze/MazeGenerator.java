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


    public void dfsMaze()
    {
        Cell end = maze.getGoal();
        sCells.push(end);

    }


    public Cell getRandAdjCell(Cell c)
    {
        int wIndex = c.getRandomWallIndex();
        Wall w = maze.getWallFromCell(c, wIndex);
        cells.addAll(maze.getAdjacentCells());
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
                                                                    // walls
// around
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
