package com.example.marblemaze;

import sofia.graphics.RectangleShape;

public class Wall
    extends RectangleShape
{
    private float   xStart;
    private float   yStart;
    private float   height;
    private float   width;
    private boolean horizontal;
    private boolean exists;


    public Wall()
    {
        this(0, 0, true);
    }


    public Wall(float x, float y, boolean horizontal)
    {
        super();
        xStart = x;
        yStart = y;
        this.horizontal = horizontal;
        this.exists = true;
    }


    public void destroyWall()
    {
        exists = false;
    }


    public boolean exists()
    {
        return exists;
    }


    public boolean isHorizontal()
    {
        return horizontal;
    }


    public void setWidthAndHeight()
    {
        if (horizontal)
        {
            height = 0.8f;
            width = 3;
        }
        else
        {
            height = 3;
            width = 0.8f;
        }
    }


    public float getX()
    {
        return xStart;
    }


    public float getY()
    {
        return yStart;
    }

}
