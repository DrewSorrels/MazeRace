package com.example.marblemaze;

public class Wall
{
    private int xStart;
    private int yStart;
    private boolean horizontal;

    public Wall() {
        this(0, 0, true);
    }
    public Wall(int x, int y, boolean horizontal) {
        xStart = x;
        yStart = y;
        this.horizontal = horizontal;
    }

}
