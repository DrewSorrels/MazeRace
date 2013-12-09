package com.example.marblemaze;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the (@link MazeGenerator) class.
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class MazeGeneratorTests
    extends TestCase
{
    private MazeGenerator mg;


    /**
     * sets up the test cases
     */
    public void setUp()
    {
        mg = new MazeGenerator();
    }


    // ----------------------------------------------------------
    /**
     * Tests the random dfs maze gen.
     */
    public void testDFSMaze()
    {
        mg.dfsMaze();
        assertNotNull(mg.getMaze());
    }


    // ----------------------------------------------------------
    /**
     * Tests the randomized prim's maze gen algorithm.
     */
    public void testPrimMaze()
    {
        mg.primMaze();
        assertNotNull(mg.getMaze());
    }


    // ----------------------------------------------------------
    /**
     * Tests tester method for maze gen.
     */
    public void testBasicMaze()
    {
        mg.testMaze();
        assertNotNull(mg.getWalls());
    }
}
