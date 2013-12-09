package com.example.marblemaze;

import com.example.marblemaze.observableevents.HoleAddedEvent;
import com.example.marblemaze.observableevents.MarbleAddedEvent;
import com.example.marblemaze.observableevents.MarbleRemovedEvent;
import com.example.marblemaze.observableevents.WallAddedEvent;
import com.example.marblemaze.observableevents.WallRemovedEvent;
import com.example.marblemaze.observableevents.WeaponSpawnerAddedEvent;
import com.example.marblemaze.weapons.LaserSpawner;
import com.example.marblemaze.weapons.RocketSpawner;
import com.example.marblemaze.weapons.WeaponSpawner;
import java.util.*;

// -------------------------------------------------------------------------
/**
 * The Maze is simply a maze through which the user must direct the marble to
 * travel through.
 *
 * @author Nick Kilmer (nkilmer8)
 * @author Drew Sorrels (amsorr)
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.08
 */
public class Maze
    extends Observable
    implements Observer
{
    private Cell[][]    grid;
    private List<Wall>  walls;
    private Cell        start;
    private Cell        end;
    private MarbleShape marble;
    private List<Hole>  holes;


    // ----------------------------------------------------------
    /**
     * Create a new Maze object.
     *
     * @param width
     *            is the width of the side of the maze
     * @param height
     *            is the height of the maze
     */
    public Maze(int width, int height)
    {
        holes = new ArrayList<Hole>();
        grid = new Cell[width][height];
        walls = new ArrayList<Wall>();

        List<Wall> temp = new ArrayList<Wall>();
        for (int i = 0; i < width; i++)
        {
            for (int a = 0; a < height; a++)
            {
                grid[i][a] = new Cell(i, a);
                grid[i][a].addObserver(this);
                temp.addAll(grid[i][a].getWalls());
                // Iterate over each of the walls to add it to the list of walls
                // if a wall at that position and orientation isn't already
                // there.
                for (Wall w : temp)
                {
                    if (!walls.contains(w))
                    {
                        walls.add(w);
                    }
                }
                temp.clear();
            }
        }
        start = grid[0][0];
        end = grid[width - 1][height - 1];

        start.addObserver(this);
        end.addObserver(this);
    }


    // ----------------------------------------------------------
    /**
     * gets the wall that is next to the cell in the appropriate direction
     *
     * @param example
     *            is the cell to be tested
     * @param direction
     *            is the direction to be tested
     * @return the specified cell wall
     */
    public Wall getWallFromCell(Cell example, int direction)
    {
        Wall cellWall;

        if (direction == 0 || direction == 3)
        {
            cellWall =
                findWall(example.getX(), example.getY(), direction % 2 == 0);
            // The wall that is at the top left corner horizontal or vertical
            // based on the position (0 is horizontal)
        }
        else if (direction == 1)
        {
            cellWall = findWall(example.getX() + 1, example.getY(), false);
        }
        else
        {
            cellWall = findWall(example.getX(), example.getY() + 1, true);
        }
        return cellWall;
    }


    /**
     * Find wall between two cells.
     *
     * @param c1
     *            The first wall
     * @param c2
     *            The second wall.
     * @return wall between c1 and c2. null if they are not next to eachother.
     */
    public Wall getWallFromCells(Cell c1, Cell c2)
    {
        // If the y cords are the same
        if (c1.getY() == c2.getY())
        {
            if (c1.getX() + 1 == c2.getX())
            {
                return getWallFromCell(c1, 1);
            }
            else if (c1.getX() == c2.getX() + 1)
            {
                return getWallFromCell(c1, 3);
            }
        }
        // If x coords are the same.
        else if (c1.getX() == c2.getX())
        {
            if (c1.getY() + 1 == c2.getY())
            {
                return getWallFromCell(c1, 2);
            }
            else if (c1.getY() == c2.getY() + 1)
            {
                return getWallFromCell(c1, 0);
            }
        }

        return null;
    }


    /**
     * Returns a wall at the given position and orientation
     *
     * @param x
     *            The x coordinate of the wall
     * @param y
     *            The y coordinate of the wall
     * @param horizontal
     *            Whether it is horizontal or not.
     * @return A Wall with the given stats or null if it isn't there.
     */
    public Wall findWall(int x, int y, boolean horizontal)
    {
        for (Wall w : walls)
        {
            if (w.equals(new Wall(x, y, horizontal)))
            {
                return w;
            }
        }
        return null;
    }


    // ----------------------------------------------------------
    /**
     * finds all the walls surrounding a specific cell.
     *
     * @param example
     *            is the cell to be tested
     * @return an ArrayList<Wall> of the walls surrounding the cell
     */
    public ArrayList<Wall> getAdjacentWalls(Cell example)
    {
        ArrayList<Wall> wallArray = new ArrayList<Wall>();
        for (Wall w : walls)
        {
            // If it's x is equal and y is equal or one larger. Only for when it
            // is horizontal
            if (w.isHorizontal()
                && (w.getX() == example.getX() && (w.getY() == example.getY() || w
                    .getY() == example.getY() + 1)))
            {
                wallArray.add(w);
            } // If it is vertical, then y must be the same and x must be same
              // or one higher.
            else if (!w.isHorizontal()
                && ((w.getX() == example.getX() || w.getX() == example.getX() + 1) && w
                    .getY() == example.getY()))
            {
                wallArray.add(w);
            }
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
     *
     * @param wally
     *            is the specified wall
     * @return an arrayList of adjacent Cells
     */
    public ArrayList<Cell> getAdjacentCells(Wall wally)
    {
        int x = (int)wally.getX();
        int y = (int)wally.getY();
        ArrayList<Cell> cellArray = new ArrayList<Cell>();
        if (x < this.width() && y < this.height())
        {
            if (wally.isHorizontal())
            {
                cellArray.add(grid[x][y]);
                if (y - 1 >= 0)
                {
                    cellArray.add(grid[x][y - 1]);
                }
            }
            else
            {
                cellArray.add(grid[x][y]);
                if (x - 1 >= 0)
                {
                    cellArray.add(grid[x - 1][y]);
                }
            }
        }
        return cellArray;
    }


    // ----------------------------------------------------------
    /**
     * Removes w from list of walls.
     *
     * @param w
     *            the wall
     * @return the wall that was removed
     */
    public Wall removeWall(Wall w)
    {
        if (w == null)
        {
            return null;
        }

        setChanged();
        notifyObservers(new WallRemovedEvent(w));

        walls.remove(w);
        return w;
    }


    // ----------------------------------------------------------
    /**
     * @param a
     *            is the x-coordinate
     * @param b
     *            is the y-coordinate
     * @return the specified Cell
     */
    public Cell getCell(int a, int b)
    {

        return grid[a][b];

    }


    /**
     * Returns the width of the maze.
     *
     * @return width of maze.
     */
    public int width()
    {
        return grid.length;
    }


    /**
     * Returns height of the maze.
     *
     * @return the height
     */
    public int height()
    {
        return grid[0].length;
    }


    // ----------------------------------------------------------
    /**
     * makes certain cells Holes - only cells surrounded on three sides by
     * walls.
     */
    public void addHoles()
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (grid[i][j].getNumWalls() == 3 && Math.random() < .2)
                {
                    addHole(grid[i][j]);
                }
            }
        }
    }


    /**
     * Adds a hole to the maze and notifies observers.
     *
     * @param c
     *            The cell where the hole will be added.
     */
    public void addHole(Cell c)
    {
        Hole h = new Hole(c.getBounds());
        holes.add(h);
        setChanged();
        notifyObservers(new HoleAddedEvent(h));
    }


    /**
     * Adds WeaponSpawners to the Maze.
     */
    public void addSpawners()
    {
        List<WeaponSpawner> spawners = new ArrayList<WeaponSpawner>();
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                // When there are 3 walls...
                if (grid[i][j].getNumWalls() == 3
                    && (i != 0 && j != 0 && i != grid.length - 1 && j != grid.length - 1))
                {
                    double chance = Math.random();
                    ArrayList<Integer> wList = grid[i][j].getWallPos();
                    int dir = 0;
                    // Find the direction that there is not a wall.
                    for (int k = 0; k < wList.size(); k++)
                    {
                        if (!wList.contains(k))
                        {
                            dir = k;
                            break;
                        }
                    }
                    // 10% chance to add either spawner.
                    if (chance < .1)
                    {
                        WeaponSpawner w = new LaserSpawner(i, j, 2000, dir);
                        spawners.add(w);
                        setChanged();
                        notifyObservers(new WeaponSpawnerAddedEvent(w));
                    }
                    else if (chance < .2)
                    {
                        WeaponSpawner w = new RocketSpawner(i, j, 4000, dir);
                        spawners.add(w);
                        setChanged();
                        notifyObservers(new WeaponSpawnerAddedEvent(w));
                    }
                }
            }
        }

    }


    // ----------------------------------------------------------
    /**
     * Returns the marble belonging to the maze.
     *
     * @return the marble
     */
    public MarbleShape getMarble()
    {
        return marble;
    }


    // ----------------------------------------------------------
    /**
     * Attaches a new marble to the maze.
     *
     * @param marble
     *            the marble to attach
     */
    public void setMarble(MarbleShape marble)
    {
        /*
         * Notify observers with the old marble (so it can be removed) and the
         * new marble so it can be added.
         */
        setChanged();
        notifyObservers(new MarbleAddedEvent(this.marble, marble));

        this.marble = marble;
        marble.addObserver(this);
    }


    // private Map<Cell, Double> fScores;

    // ----------------------------------------------------------
// /**
// * @return a Queue representing the shortest path to solve a maze
// */
// public Queue<Cell> solveAStar()
// {
// Map<Cell, Double> gScores;
//
// Set<Cell> closed = new HashSet<Cell>();
// List<Cell> open = new ArrayList<Cell>();
// Map<Cell, Cell> cameFrom = new HashMap<Cell, Cell>();
//
// gScores = new HashMap<Cell, Double>();
// fScores = new HashMap<Cell, Double>();
//
// open.add(start);
// gScores.put(start, 0d);
// fScores.put(start, gScores.get(start) + heuristicAStar(start, end));
//
// while (open.size() > 0)
// {
// Collections.sort(open, new HeuristicComparator());
// Cell current = open.get(0);
//
// if (current.equals(end))
// {
// return reconstructPath(cameFrom, current);
// }
//
// open.remove(current);
// closed.add(current);
// for (Cell poss : current.getAccessibleNeighbors())
// {
// double tentativeGScore = gScores.get(current) + 1;
// double tentativeFScore =
// tentativeGScore + heuristicAStar(poss, end);
// if (closed.contains(poss)
// && tentativeFScore > fScores.get(poss))
// {
// continue;
// }
//
// if (!open.contains(poss)
// || tentativeFScore < fScores.get(poss))
// {
// cameFrom.put(poss, current);
// gScores.put(poss, tentativeGScore);
// fScores.put(poss, tentativeFScore);
// if (!open.contains(poss))
// {
// open.add(poss);
// }
// }
// }
// }
//
// return null;
// }
//
//
// /**
// * reconstructs the path and returns the correct list.
// */
// private LinkedList<Cell> reconstructPath(
// Map<Cell, Cell> cameFrom,
// Cell current)
// {
// LinkedList<Cell> ret;
// if (cameFrom.containsKey(current))
// {
// ret = reconstructPath(cameFrom, cameFrom.get(current));
// }
// else
// {
// ret = new LinkedList<Cell>();
//
// }
// ret.add(current);
// return ret;
// }
//
//
// /**
// * Writes the comparator in order to specify the solution
// *
// * @author Dennis Lysenko (dlysenko)
// * @version 2013.12.08
// */
// private class HeuristicComparator
// implements Comparator<Cell>
// {
// /**
// * @return the integer showing the compare
// */
// public int compare(Cell a, Cell b)
// {
// return (int)Math.signum(fScores.get(a) - fScores.get(b));
// }
// }
//
//
// /**
// * returns the distance between the two cells
// *
// * @return a double of the distance
// */
// private double distBetween(Cell a, Cell b)
// {
// return Math.sqrt(Math.pow(a.getX() - b.getX(), 2)
// + Math.pow(a.getY() - b.getY(), 2));
// }
//
//
// /**
// * returns the distance between the two cells
// */
// private double heuristicAStar(Cell a, Cell b)
// {
// return distBetween(a, b);
// }

    /**
     * Handles updates from the MarbleShape (when it dies) or the walls when
     * they are set to existent/nonexistent.
     *
     * @param obs
     *            the object that was updated
     * @param event
     *            the type of event that happened
     */
    public void update(Observable obs, Object event)
    {
        if (event instanceof WallRemovedEvent
            || event instanceof WallAddedEvent
            || event instanceof MarbleRemovedEvent
            || event instanceof HoleAddedEvent)
        {
            setChanged();
            notifyObservers(event);
        }

        if (event instanceof MarbleRemovedEvent)
        {
            this.marble = null;
        }
    }


    /**
     * Finds if the cell is inside the bounds of this maze.
     *
     * @param c
     *            The cell
     * @return true if in-bounds
     */
    public boolean inBounds(Cell c)
    {
        return c.getX() >= 0 && c.getX() < width() && c.getY() >= 0
            && c.getY() < height();
    }


    // ----------------------------------------------------------
    /**
     * Tests whether or not the given x and y are in-bounds.
     *
     * @param x
     *            the x to test
     * @param y
     *            the y to text
     * @return true if in-bounds
     */
    public boolean inBounds(int x, int y)
    {
        return inBounds(new Cell(x, y));

    }


    // ----------------------------------------------------------
    /**
     * Starts or stops all of the walls in the maze from blinking.
     *
     * @param b
     *            true to start blinking, false to stop
     */
    public void setBlinkingWalls(boolean b)
    {
        for (Wall wall : walls)
        {
            if (wall.isBlinking() != b)
            {
                wall.setBlinking(b);
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * @return the list of walls
     */
    public List<Wall> getWalls()
    {
        return walls;
    }


    // ----------------------------------------------------------
    /**
     * @return the list of holes
     */
    public List<Hole> getHoles()
    {
        return holes;
    }
}
