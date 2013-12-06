package com.example.marblemaze;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 * The marble maze game's main screen. Handles displaying the maze and
 * processing the physics of the marble.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.03
 */

public class MarbleMazeScreen
    extends ShapeScreen
    implements SensorEventListener
{
    private static final float ACCELERATION_COEFFICIENT = 4.0f;
    private static final int   COORDINATE_SYSTEM_HEIGHT = 25;
    private float              ratio;

    private MarbleShape             squishy;
    private SensorManager      sensorManager;
    private Sensor             accelerometer;

    private RectangleShape     pauseButton;

    private MazeGenerator      mazeGen;

    private Maze               maze;


    @Override
    public void initialize()
    {
        // setupSampleMaze();
        setupMaze();
        setupPhysics();
        setupMarble();
        setupWalls(); // TODO remove this after maze generation works
        setupAccelerometer();
        setupUi();
    }


    /**
     * Sets up a sample 2x2 maze for display purposes and adds the walls to the
     * screen.
     */
    private void setupSampleMaze()
    {
        final int MAZE_SIZE = 2;
        maze = new Maze(MAZE_SIZE, MAZE_SIZE);
        Cell topleft = maze.getCell(0, 0);
        topleft.setWall(0, true);
        topleft.setWall(1, true);
        topleft.setWall(2, true);
        topleft.setWall(3, true);
        Cell topright = maze.getCell(0, 1);
        topright.setWall(3, true);

        for (int i = 0; i < MAZE_SIZE; i++)
        {
            for (int j = 0; j < MAZE_SIZE; j++)
            {
                Cell cellulose = maze.getCell(i, j);
                for (Wall walle : cellulose.getWalls())
                {
                    System.out.println(walle.getBounds());
                    add(walle);
                }
            }
        }
    }


    /**
     * Generates and displays the maze.
     */
    private void setupMaze()
    {
        mazeGen = new MazeGenerator();
        String algorithm = getIntent().getExtras().getString("algorithm");

        if (algorithm.equals("prim"))
        {
            mazeGen.primMaze();
        }
        if (algorithm.equals("dfs"))
        {
            mazeGen.dfsMaze();
        }

        maze = mazeGen.getMaze();
    }


    /**
     * Sets up the maze screen's coordinate system and gravity.
     */
    private void setupPhysics()
    {
        getCoordinateSystem().height(COORDINATE_SYSTEM_HEIGHT);
        ratio = getHeight() / COORDINATE_SYSTEM_HEIGHT;

        // Apply no gravity.
        setGravity(0, 0);
    }


    /**
     * Instantiates & adds the marble.
     */
    private void setupMarble()
    {
        // Instantiate & add the marble.
        squishy = new MarbleShape(15, 15);
        add(squishy);
    }


    /**
     * Adds four sample walls to the canvas.
     */
    private void setupWalls()
    {
        // Add walls into the maze.
        RectangleShape topWall =
            new RectangleShape(1, 0, getCoordinateSystemWidth() - 1, 1);
        topWall.setFillColor(Color.blue);
        RectangleShape leftWall =
            new RectangleShape(0, 0, 1, getCoordinateSystemHeight());
        leftWall.setFillColor(Color.red);
        RectangleShape bottomWall =
            new RectangleShape(
                1,
                49,
                getCoordinateSystemWidth() - 1,
                getCoordinateSystemHeight());
        bottomWall.setFillColor(Color.yellow);
        RectangleShape rightWall =
            new RectangleShape(
                getCoordinateSystemWidth() - 1,
                0,
                getCoordinateSystemWidth(),
                50);
        rightWall.setFillColor(Color.green);

        add(topWall);
        add(leftWall);
        add(bottomWall);
        add(rightWall);
    }


    /**
     * Sets up accelerometer event capturing.
     */
    private void setupAccelerometer()
    {
        // Initialize framework for getting accelerometer tilt events.
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer =
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(
            this,
            accelerometer,
            SensorManager.SENSOR_DELAY_NORMAL);
    }


    /**
     * Sets up the UI (currently just the pause button).
     */
    private void setupUi()
    {
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
     * Pauses the game, opening a PauseScreen intent.
     */
    private void pause()
    {
        Intent intent = new Intent(this, PauseScreen.class);
        startActivity(intent);
    }


    /**
     * Detects when a sensor's accuracy changes, in this case that of the
     * accelerometer.
     *
     * @param accel
     *            the sensor whose accuracy changed
     * @param accuracy
     *            the new accuracy
     */
    public void onAccuracyChanged(Sensor accel, int accuracy)
    {
        // can be safely ignored for this project
    }


    /**
     * Detects when a sensor changes, in this case the accelerometer.
     *
     * @param event
     *            the event with details of the sensor change
     */
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
     * @pre ratio has been set (e.g. setupPhysics/initialize has been called)
     * @return see above
     */
    public int getCoordinateSystemWidth()
    {
        return (int)(getWidth() / ratio);
    }

}
