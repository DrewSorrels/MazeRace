package com.example.marblemaze;

import student.TestCase;

public class TestCell extends TestCase
{
    private Cell test;

    public void setUp()
    {
        test = new Cell(2,2);
    }

    public void testGetNumWalls()
    {
        assertEquals(test.getNumWalls(), 4);
    }
}
