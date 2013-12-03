package com.example.marblemaze;

import android.content.Intent;
import android.content.Context;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 * A Controller for the marble maze game's main screen.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version Nov 15, 2013
 */

public class MarbleMazeScreen
    extends ShapeScreen
    implements SensorEventListener
{
    private static float   ACCELERATION_COEFFICIENT = 4.0f;
    private static int     COORDINATE_SYSTEM_HEIGHT = 50;

    private Marble         squishy;
    private SensorManager  sensorManager;
    private Sensor         accelerometer;
    private Maze           maze;

    /**
     * Stores the gravity at the moment the user pauses so that it can reset the
     * gravity to that value later.
     */
    private PointF         pauseGravity;

    /**
     * Stores the marble's velocity at the moment the user pauses so that it can
     * reset its velocity to that value later.
     */
    private PointF         pauseMarbleVelocity;

    /**
     * A translucent black rectangle filling up the entire screen.
     */
    private RectangleShape pauseMask;

    private RectangleShape pauseButton;
    private RectangleShape resumeButton;


    @Override
    public void initialize()
    {
        getCoordinateSystem().height(COORDINATE_SYSTEM_HEIGHT);
        // maze = new Maze(10, 20);

        // Apply no gravity.
        setGravity(0, 0);

        // Instantiate & add the marble.
        squishy = new Marble(15, 15);
        add(squishy);

        // Add walls into the maze.
        RectangleShape topWall = new RectangleShape(0, 0, 50, 10);
        topWall.setFillColor(Color.blue);
        RectangleShape leftWall = new RectangleShape(0, 0, 10, 50);
        leftWall.setFillColor(Color.red);
        RectangleShape bottomWall = new RectangleShape(0, 40, 50, 50);
        bottomWall.setFillColor(Color.yellow);
        RectangleShape rightWall = new RectangleShape(40, 0, 50, 50);
        rightWall.setFillColor(Color.green);

        add(topWall);
        add(leftWall);
        add(bottomWall);
        add(rightWall);

        // Initialize framework for getting accelerometer tilt events.
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer =
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(
            this,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL);

        // Instantiate pause mask
        pauseMask =
            new RectangleShape(
                0,
                0,
                2 * COORDINATE_SYSTEM_HEIGHT,
                2 * COORDINATE_SYSTEM_HEIGHT);
        pauseMask.setAlpha(120); // alpha is 0-255
        pauseMask.setFillColor(Color.black);


    }


    // ----------------------------------------------------------
    /**
     * Captures touch events.
     *
     * @param x the x-coordinate of the touch event
     * @param y the y-coordinate of the touch event
     */
    public void onTouchDown(float x, float y)
    {
        if (pauseButton.contains(x, y)) {
            pause();
        }
        if (resumeButton.contains(x, y)) {
            resume();
        }
    }


    /**
     * Pauses the game, overlaying a pause mask and storing current gravity and
     * marble velocity so it can be re-applied upon resumption.
     */
    private void pause()
    {
        // Store the current gravity (acceleration) & marble velocity
        // to re-apply it later
        pauseGravity = getGravity();
        pauseMarbleVelocity = squishy.getLinearVelocity();

        // Open pause screen
        add(pauseMask);
    }


    /**
     * Resumes the game, removing the pause mask and restoring gravity and
     * marble velocity.
     *
     * @pre pauseGravity and pauseMarbleVelocity have been initialized
     */
    private void resume()
    {
        setGravity(pauseGravity);
        squishy.setLinearVelocity(pauseMarbleVelocity);

        remove(pauseMask);
    }


    public void onAccuracyChanged(Sensor accel, int accuracy)
    {
        // can be safely ignored for this demo
    }


    public void onSensorChanged(SensorEvent event)
    {
        float x = event.values[1];
        float y = event.values[0];
        // float z = event.values[2];

        x = (float)(Math.signum(x) * Math.sqrt(Math.abs(x)));
        y = (float)(Math.signum(y) * Math.sqrt(Math.abs(y)));

        // System.out.println("" + x + ", " + y + ", " + z);

        setGravity(ACCELERATION_COEFFICIENT * x, ACCELERATION_COEFFICIENT * y);

// float ax = ACCELERATION_COEFFICIENT * x;
// float ay = ACCELERATION_COEFFICIENT * y;
//
// PointF lv = squishy.getLinearVelocity();
//
// lv.x += ax;
// lv.y += ay;
//
// System.out.println("" + ax + ", " + ay + "::" + lv.x + ", " + lv.y);
//
// if (Math.abs(oldXV) < Math.abs(ACCELEROMETER_COEFFICIENT * x))
// {
// if (lv.x < 0 && x < 0 && lv.x < ACCELEROMETER_COEFFICIENT * x)
// {
// lv.x = ACCELEROMETER_COEFFICIENT * x;
// }
// if (lv.x > 0 && x > 0 && lv.x > ACCELEROMETER_COEFFICIENT * x)
// {
// lv.x = ACCELEROMETER_COEFFICIENT * x;
// }
// }
//
// if (Math.abs(oldYV) < Math.abs(ACCELEROMETER_COEFFICIENT * y))
// {
// if (lv.y < 0 && y < 0 && lv.y < ACCELEROMETER_COEFFICIENT * y)
// {
// lv.y = ACCELEROMETER_COEFFICIENT * y;
// }
// if (lv.y > 0 && y > 0 && lv.y > ACCELEROMETER_COEFFICIENT * y)
// {
// lv.y = ACCELEROMETER_COEFFICIENT * y;
// }
// }
//
// squishy.setLinearVelocity(lv);
// oldXV = lv.x;
// oldYV = lv.y;
    }

}
