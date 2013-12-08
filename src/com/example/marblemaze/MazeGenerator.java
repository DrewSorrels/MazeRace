package com.example.marblemaze;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A random maze generator with various algorithms for maze generation.
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
    private static final int MAZE_HEIGHT = 15;


    /**
     * Creates a new generator.
     */
    public MazeGenerator()
    {
        maze = new Maze(MAZE_WIDTH, MAZE_HEIGHT);
        cellWalls = new ArrayList<Wall>();
        cells = new ArrayList<Cell>();

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
     * Returns list of walls.
     *
     * @return returns ArrayList<Wall>
     */
    public ArrayList<Wall> getWalls()
    {
        return cellWalls;
    }

    /**
     * Generates a predefined maze for testing.
     */
    public void testMaze() {
        maze = new Maze(2, 2);
        Cell tl = maze.getCell(0, 0);
        Cell tr = maze.getCell(1, 0);
        Cell bl = maze.getCell(0, 1);
        Cell br = maze.getCell(1, 1);

        maze.addHole(tl);
    }

    /**
     * Generates a maze based on the DFS Algorithm.
     */
    public void dfsMaze()
    {
        Cell end = maze.getGoal();
        sCells.push(end);
        // Add all cells to list of unvisited cells.
        for (int i = 0; i < maze.width(); i++)
        {
            for (int j = 0; j < maze.height(); j++)
            {
                cells.add(maze.getCell(i, j));
            }
        }
        // Add all walls to the list.
        for (Cell c : cells)
        {
            for (Wall w : c.getWalls())
            {
                if (!cellWalls.contains(w))
                {
                    cellWalls.add(w);
                }
            }
        }
        // While there are unvisited cells...
        while (!cells.isEmpty())
        {
            ArrayList<Cell> tempCells = new ArrayList<Cell>();
            if (sCells.size() > 0)
            {
                tempCells = getUnvisitedNeighbors(sCells.peek());
            }
            if (tempCells.size() > 0)
            {
                // If there are unvisited neighbors.
                Cell currentCell =
                    tempCells.get((int)Math.floor(Math.random()
                        * (tempCells.size() - 1) + 0.5));

                // Destroy the wall between 2 cells.
                Wall betweenCells =
                    maze.getWallFromCells(sCells.peek(), currentCell);
                cellWalls.remove(betweenCells);
                rmWall(sCells.peek(), currentCell, betweenCells);

                // Add currentCell to the stack and visit it. Remove it from
                // unvisited cells list.

                sCells.push(currentCell);
                currentCell.visitCell();

                cells.remove(currentCell);

            }
            else if (!sCells.isEmpty())
            {
                // Otherwise, pop it off the cell.
                sCells.pop();
            }
            else
            {
                // If there aren't any cells left in the stack,
                sCells.push(cells.get((int)Math.random() * (cells.size() - 1)));
            }
        }

    }


    /**
     * Generates a maze using a randomized form of Prim's Algorithm.
     */
    public void primMaze()
    {
        cellWalls.addAll(maze.getAdjacentWalls(maze.getCell(0, 0))); // All
        // walls around startPoint
        while (!cellWalls.isEmpty())
        {
            int randWall =
                (int)Math.floor(Math.random() * (cellWalls.size() - 1) + 0.5);
            // Efficient random int

            List<Cell> cAdjCells = new ArrayList<Cell>();
            cAdjCells.addAll(maze.getAdjacentCells(cellWalls.get(randWall)));

            if (!cells.containsAll(cAdjCells) && cAdjCells.size() > 1) // if the
// maze cell opposite the
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

                rmWall(cCurrent, cOpposite, cellWalls.get(randWall));
                // remove the wall.

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


    /**
     * Returns a random cell adjacent to the supplied cell.
     *
     * @param c
     *            The cell
     * @return a random cell around this cell.
     */
    @SuppressWarnings("unused")
    private Cell getRandAdjCell(Cell c)
    {
        ArrayList<Cell> randCells = new ArrayList<Cell>();

        randCells.clear();
        // Clear cell list to make sure there are not any cells in it.
        int wIndex = c.getRandomWallIndex();
        Wall w = maze.getWallFromCell(c, wIndex);
        randCells.addAll(maze.getAdjacentCells(w));
        // Add them all to the cell list and find a random one.

        Cell randCell = randCells.get((int)Math.random() * (cells.size() - 1));
        randCells.clear(); // Clear cell list.

        return randCell;
    }


    /**
     * Returns list of all unvisited neighbors.
     *
     * @return ArrayList<Cell> of unvisited cells.
     */
    private ArrayList<Cell> getUnvisitedNeighbors(Cell c)
    {
        ArrayList<Cell> adjCells = new ArrayList<Cell>();

        if (maze.inBounds(c.east()))
        {
            if (!maze.getCell(c.east().getX(), c.east().getY()).isVisited())
            {
                adjCells.add(maze.getCell(c.east().getX(), c.east().getY()));
            }
        }
        if (maze.inBounds(c.west()))
        {
            if (!maze.getCell(c.west().getX(), c.west().getY()).isVisited())
            {
                adjCells.add(maze.getCell(c.west().getX(), c.west().getY()));
            }
        }
        if (maze.inBounds(c.north()))
        {
            if (!maze.getCell(c.north().getX(), c.north().getY()).isVisited())
            {
                adjCells.add(maze.getCell(c.north().getX(), c.north().getY()));
            }
        }
        if (maze.inBounds(c.south()))
        {
            if (!maze.getCell(c.south().getX(), c.south().getY()).isVisited())
            {
                adjCells.add(maze.getCell(c.south().getX(), c.south().getY()));
            }
        }

        return adjCells;

    }


    /**
     * Removes the wall between the two cells and destroys it in each cell
     */
    private void rmWall(Cell c1, Cell c2, Wall w)
    {
        if (w == null)
        {
            return;
        }

        int wallPos = detWalls(c1, c2, w); // Find what position the wall is in.

        maze.removeWall(w);
        c2.setWall(c1.oppositeWall(wallPos), false);
        c1.setWall(wallPos, false);
        // Remove the wall at the position of w and set wall to false in
        // each cell
    }

}
