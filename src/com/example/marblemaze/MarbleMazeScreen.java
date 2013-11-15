package com.example.marblemaze;

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
 * @version Nov 14, 2013
 */

public class MarbleMazeScreen
    extends ShapeScreen
    implements SensorEventListener
{
    private Marble        squishy;
    private SensorManager sensorManager;
    private Sensor        accelerometer;


    @Override
    public void initialize()
    {
        getCoordinateSystem().width(50);

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
    }


    public void onAccuracyChanged(Sensor accelerometer, int accuracy)
    {
        // can be safely ignored for this demo
    }

    private boolean      accelerationInitialized  = false;
    private static float ACCELERATION_COEFFICIENT = 2.0f;
    private static float ACCELEROMETER_COEFFICIENT = 5.0f;


    public void onSensorChanged(SensorEvent event)
    {
        // hold with usb port on right side
        // tilting left down makes y go negative
        // tilting right down makes y go positive
        // tilting top down makes x go negative
        // tilting bottom down makes x go positive
        float x = -event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if (accelerationInitialized)
        {
            System.out.println("" + x + ", " + y + ", " + z);

            float ax = ACCELERATION_COEFFICIENT * x;
            float ay = ACCELERATION_COEFFICIENT * y;

            PointF lv = squishy.getLinearVelocity();

            lv.x += ax;
            lv.y += ay;

            System.out.println("" + ax + ", " + ay + "::" + lv.x + ", " + lv.y);

            if (lv.x < 0 && x < 0 && lv.x < ACCELEROMETER_COEFFICIENT * x)
            {
                lv.x = ACCELEROMETER_COEFFICIENT * x;
            }
            if (lv.x > 0 && x > 0 && lv.x > ACCELEROMETER_COEFFICIENT * x)
            {
                lv.x = ACCELEROMETER_COEFFICIENT * x;
            }
            if (lv.y < 0 && y < 0 && lv.y < ACCELEROMETER_COEFFICIENT * y)
            {
                lv.y = ACCELEROMETER_COEFFICIENT * y;
            }
            if (lv.y > 0 && y > 0 && lv.y > ACCELEROMETER_COEFFICIENT * y)
            {
                lv.y = ACCELEROMETER_COEFFICIENT * y;
            }

            squishy.setLinearVelocity(lv);
        }
        else
        {
            accelerationInitialized = true;
        }
    }

}
