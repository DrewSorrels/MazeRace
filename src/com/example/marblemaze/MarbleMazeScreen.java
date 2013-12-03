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
    private static final float ACCELERATION_COEFFICIENT = 4.0f;
    private static final int   COORDINATE_SYSTEM_HEIGHT = 50;
    private float              ratio;

    private Marble             squishy;
    private SensorManager      sensorManager;
    private Sensor             accelerometer;
    private Maze               maze;

    /**
     * Stores the gravity at the moment the user pauses so that it can reset the
     * gravity to that value later.
     */
    private PointF             pauseGravity;

    /**
     * Stores the marble's velocity at the moment the user pauses so that it can
     * reset its velocity to that value later.
     */
    private PointF             pauseMarbleVelocity;

    /**
     * A translucent black rectangle filling up the entire screen.
     */
    private RectangleShape     pauseMask;

    private RectangleShape     pauseButton;
    private RectangleShape     resumeButton;


    @Override
    public void initialize()
    {
        getCoordinateSystem().height(COORDINATE_SYSTEM_HEIGHT);
        ratio = getHeight() / COORDINATE_SYSTEM_HEIGHT;
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

        // pause button is 56x40
        float pauseWidth = 5 * 56 / 40f;
        float pauseHeight = 5;
        pauseButton =
            new RectangleShape(
                getCoordinateSystemWidth() - pauseWidth,
                0,
                getCoordinateSystemWidth(),
                pauseHeight);
        pauseButton.setImage("pause_button");
        add(pauseButton);

        // resume button is 121x39, but we need to center it
        float middleX = getCoordinateSystemWidth() / 2;
        float middleY = getCoordinateSystemHeight() / 2;
        float resumeWidth = 5 * 121 / 39f;
        float resumeHeight = 5;

        resumeButton =
            new RectangleShape(middleX - resumeWidth / 2, middleY
                - resumeHeight / 2, middleX + resumeWidth / 2, middleY
                + resumeHeight / 2);
        resumeButton.setImage("resume_button");
    }


    // ----------------------------------------------------------
    /**
     * Captures touch events.
     *
     * @param x
     *            the x-coordinate of the touch event
     * @param y
     *            the y-coordinate of the touch event
     */
    public void onTouchDown(float x, float y)
    {
        if (pauseButton.contains(x, y))
        {
            pause();
        }
    }


    /**
     * Pauses the game, overlaying a pause mask and storing current gravity and
     * marble velocity so it can be re-applied upon resumption.
     */
    private void pause()
    {
        // Open the PauseScreen intent
    }


    public void onAccuracyChanged(Sensor accel, int accuracy)
    {
        // can be safely ignored for this project
    }


    public void onSensorChanged(SensorEvent event)
    {
        float x = event.values[1];
        float y = event.values[0];

        x = (float)(Math.signum(x) * Math.sqrt(Math.abs(x)));
        y = (float)(Math.signum(y) * Math.sqrt(Math.abs(y)));

        setGravity(ACCELERATION_COEFFICIENT * x, ACCELERATION_COEFFICIENT * y);
    }


    // ----------------------------------------------------------
    /**
     * Returns the height of the maze's coordinate system.
     *
     * @return see above
     */
    public int getCoordinateSystemHeight()
    {
        return COORDINATE_SYSTEM_HEIGHT;
    }


    // ----------------------------------------------------------
    /**
     * Returns the width of the maze's coordinate system.
     *
     * @pre ratio has been set (e.g. initialize() has been called)
     * @return see above
     */
    public int getCoordinateSystemWidth()
    {
        return (int)(getWidth() / ratio);
    }

}
