package Weapons;

import com.example.marblemaze.MarbleShape;
import sofia.graphics.LineShape;
import sofia.graphics.Shape;

// -------------------------------------------------------------------------
/**
 *  This method will create a laser that the user must dodge
 *
 *  @author Nicholas Kilmer (nkilmer8)
 *  @version 2013.12.06
 */
public class Laser extends LineShape implements Bullet
{
    private LineShape bull;

    // ----------------------------------------------------------
    /**
     * Create a new Laser object.
     */
    public Laser()
    {
        bull.setBullet(true);

    }
    /**
     * Place a description of your method here.
     * @param x is the x velocity
     * @param y is the y velocity
     */
    public void move(int x, int y)
    {
        bull.applyLinearImpulse(x,y);
    }

 // ----------------------------------------------------------
    /**
     * removes the marble when it is hit by the laser
     * @param first is the marble/bullet
     * @param second is the bullet/marble
     */
    public void onCollisionBetween(Shape first, Shape second)
    {
        if(first instanceof MarbleShape && second instanceof Laser)
        {
            first.animate(1000).rotation(720).alpha(0).removeWhenComplete()
            .play();
            this.remove();
        }
        if(first instanceof Laser && second instanceof MarbleShape)
        {
            second.animate(1000).rotation(720).alpha(0).removeWhenComplete()
            .play();
            this.remove();
        }

    }



}
