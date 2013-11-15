package com.example.marblemaze;

public class Wall
{
    private int xStart;
    private int yStart;
    private boolean horizontal;
    private boolean exists;

    public Wall() {
        this(0, 0, true);
    }
    public Wall(int x, int y, boolean horizontal) {
        xStart = x;
        yStart = y;
        this.horizontal = horizontal;
        this.exists = true;
    }
    public void destroyWall() {
        exists = false;
    }
    public boolean exists() {
        return exists;
    }
    public boolean isHorizontal() {
        return horizontal;
    }

}
