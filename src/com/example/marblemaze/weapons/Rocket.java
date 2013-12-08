package com.example.marblemaze.weapons;



import com.example.marblemaze.MarbleShape;
import sofia.graphics.RectangleShape;
import sofia.graphics.Shape;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author nkilmer84
 *  @version Dec 6, 2013
 */
public class Rocket extends RectangleShape implements Bullet
{
    private RectangleShape rock;

    /**
     * Place a description of your method here.
     * @param x is the x velocity
     * @param y is the y velocity
     */
    public void move(int x, int y)
    {
        rock.applyLinearImpulse(x, y);

    }

 // ----------------------------------------------------------
    /**
     * removes the marble when it is hit by the rocket
     * @param first is the marble/bullet
     */
    public void onCollisionWith(MarbleShape first)
    {
        first.animate(2000).rotation(560).alpha(0).removeWhenComplete()
        .play();
        this.remove();
    }

    public void move(float x, float y)
    {
        // TODO Auto-generated method stub

    }

}
