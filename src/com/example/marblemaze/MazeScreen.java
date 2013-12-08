package com.example.marblemaze;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.marblemaze.observableevents.MarbleAddedEvent;
import com.example.marblemaze.observableevents.MarbleRemovedEvent;
import com.example.marblemaze.observableevents.WallAddedEvent;
import com.example.marblemaze.observableevents.WallRemovedEvent;
import com.example.marblemaze.weapons.LaserSpawner;
import com.example.marblemaze.weapons.WeaponSpawner;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

// -------------------------------------------------------------------------
/**
 * The marble maze game's main screen. Handles displaying the maze and
 * processing the physics of the marble.
 *
 * @author Dennis Lysenko (dlysenko)
 * @version 2013.12.06
 */

public class MazeScreen
    extends ShapeScreen
    implements SensorEventListener, Observer
{
    /**
     * The coefficient by which the accelerometer's built in motion sensor
     * values are multiplied to find a suitable gravity value.
     */
    private static final float ACCELERATION_COEFFICIENT = 4.0f;

    /**
     * The height (in meters) of the coordinate system, scaled to fit the
     * screen. As an example, if the screen is 1000 pixels high and the
     * coordinate system height is 25, one meter = 40 pixels.
     */
    private static final int   COORDINATE_SYSTEM_HEIGHT = 15;

    /**
     * The amount of pixels in a meter based on the coordinate system height.
     */
    private float              pixelsPerMeter;

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
        setupAddWalls();
        setupPhysics();
        setupMarble();
        setupAccelerometer();
        setupUi();

        setupSpawners();
    }


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    /**
     * Sets up a sample 2x2 maze for display purposes and adds the walls to the
     * screen.
     */
    private void setupSampleMaze()
    {
        final int MAZE_SIZE = 4;
        maze = new Maze(MAZE_SIZE, MAZE_SIZE);
        Cell topleft = maze.getCell(0, 0);
        topleft.setWall(0, true);
        topleft.setWall(1, true);
        topleft.setWall(2, true);
        topleft.setWall(3, true);
        Cell topright = maze.getCell(0, 3);
        topright.setWall(2, true);
    }


    /**
     * Generates and displays the maze based on the selected algorithm.
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
        maze.addObserver(this);
    }


    /**
     * Adds all the walls from <code>maze</code> to
     */
    private void setupAddWalls()
    {
        System.out.println("WAT");
        for (int i = 0; i < maze.width(); i++)
        {
            for (int j = 0; j < maze.height(); j++)
            {
                System.out.println("WAT" + i + ", " + j);
                Cell cellulose = maze.getCell(i, j);
                for (Wall walle : cellulose.getWalls())
                {
                    add(walle);
                }
            }
        }
    }


    /**
     * Sets up the maze screen's coordinate system and gravity.
     */
    private void setupPhysics()
    {
        getCoordinateSystem().height(COORDINATE_SYSTEM_HEIGHT);
        pixelsPerMeter = getHeight() / COORDINATE_SYSTEM_HEIGHT;

        // Apply no gravity.
        setGravity(0, 0);
    }


    /**
     * Instantiates & adds the marble.
     *
     * @pre maze is not a null pointer
     */
    private void setupMarble()
    {
        MarbleShape squishy = new MarbleShape(0.5f, 0.5f);

        maze.setMarble(squishy);
    }


    /**
     * Instantiates and adds spawners.
     */
    private void setupSpawners()
    {
        ArrayList<WeaponSpawner> spawners = new ArrayList<WeaponSpawner>();

        spawners.add(new LaserSpawner(1, 1, 1));
        for (WeaponSpawner w : spawners)
        {
            add(w);
        }
    }


    /**
     * Adds four sample walls to the canvas.
     */
    private void setupWalls()
    {
        RectangleShape topWall =
            new RectangleShape(1, 0, getCoordinateSystemWidth() - 1, 1);
        topWall.setFillColor(Color.blue);
        RectangleShape leftWall =
            new RectangleShape(0, 0, 1, getCoordinateSystemHeight());
        leftWall.setFillColor(Color.red);
        RectangleShape bottomWall =
            new RectangleShape(
                1,
                getCoordinateSystemHeight() - 1,
                getCoordinateSystemWidth() - 1,
                getCoordinateSystemHeight());
        bottomWall.setFillColor(Color.yellow);
        RectangleShape rightWall =
            new RectangleShape(
                getCoordinateSystemWidth() - 1,
                0,
                getCoordinateSystemWidth(),
                getCoordinateSystemHeight());
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
        float pauseWidth = 1 * 56 / 40f;
        float pauseHeight = 1;
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
        maze.getMarble().applyLinearImpulse(0.01f * x, 0.01f * y);
    }


    // ----------------------------------------------------------
    /**
     * Returns the height of the maze's coordinate system (
     * {@code COORDINATE_SYSTEM_HEIGHT}).
     *
     * @return the height of the maze's coordinate system
     */
    public int getCoordinateSystemHeight()
    {
        return COORDINATE_SYSTEM_HEIGHT;
    }


    // ----------------------------------------------------------
    /**
     * Returns the width of the maze's coordinate system.
     *
     * @pre {@code pixelsPerMeter} has been set (i.e. {@code setupPhysics} has
     *      been called)
     * @return the width of the maze's coordinate system
     */
    public int getCoordinateSystemWidth()
    {
        return (int)(getWidth() / pixelsPerMeter);
    }


    public void update(Observable obs, Object event)
    {
        System.out.println("observer updated");
        if (event instanceof WallRemovedEvent)
        {
            System.out.println("wallremoved");
            ((WallRemovedEvent)event).getWall().remove();
        }
        if (event instanceof WallAddedEvent)
        {
            System.out.println("walladded");
            add(((WallRemovedEvent)event).getWall());
        }
        if (event instanceof MarbleAddedEvent)
        {
            System.out.println("marbleadded");
            MarbleAddedEvent maEvent = (MarbleAddedEvent)event;
            if (maEvent.getOldMarble() != null)
            {
                maEvent.getOldMarble().remove();
            }
            add(maEvent.getNewMarble());
        }
        if (event instanceof MarbleRemovedEvent)
        {
            System.out.println("marbleremoved");
            ((MarbleRemovedEvent)event).getMarble().remove();
        }
    }

}
