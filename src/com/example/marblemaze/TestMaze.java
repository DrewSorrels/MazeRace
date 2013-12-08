package com.example.marblemaze;
import student.TestCase;

public class TestMaze extends TestCase
{
    private Maze test;

    public void setUp()
    {
        test = new Maze(2, 2);
    }

    public void testGetWallFromCell()
    {
        assertEquals(test.getWallFromCell(new Cell(1,1), 0), (new Wall(1,0,true)));
    }
}
