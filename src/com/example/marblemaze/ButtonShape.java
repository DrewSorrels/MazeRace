package com.example.marblemaze;

import android.graphics.PointF;
import android.graphics.RectF;
import sofia.graphics.Color;
import sofia.graphics.Drawing;
import sofia.graphics.RectangleShape;
import sofia.graphics.Shape;
import sofia.graphics.TextShape;

// -------------------------------------------------------------------------
/**
 * Represents a button shape in Sofia.
 *
 * @author Dennis Lysenko
 * @version 2013.12.03
 */

public class ButtonShape
    extends Shape
{
    private RectangleShape outline;
    private TextShape      textShape;
    private String         text;
    private int            fontSize;
    private PointF         position;


    // ----------------------------------------------------------
    /**
     * Create a new ButtonShape object.
     *
     * @param buttonText
     *            the text of the button
     * @param typeSize
     *            the font size of the text
     * @param topLeft
     *            position of the top left corner of the button
     */
    public ButtonShape(String buttonText, int typeSize, PointF topLeft)
    {
        this.text = buttonText;
        this.fontSize = typeSize;
        this.position = topLeft;
    }


    // ----------------------------------------------------------
    @Override
    protected void createFixtures()
    {
        textShape = new TextShape(text, position.x, position.y);
        textShape.setColor(Color.gray);
        textShape.setTypeSize(fontSize);

        RectF bounds = textShape.getBounds();

        outline =
            new RectangleShape(
                bounds.top,
                bounds.left,
                bounds.right,
                bounds.bottom);
        outline.setColor(Color.white);
        outline.setFillColor(Color.blue);

        System.out.println(textShape.getBounds().bottom);

        getParentView().add(outline);
        getParentView().add(textShape);
    }


    // ----------------------------------------------------------
    @Override
    public void draw(Drawing dng)
    {
    }


    // ----------------------------------------------------------
    @Override
    public RectF getBounds()
    {
        return outline.getBounds();
    }


    // ----------------------------------------------------------
    @Override
    public void setBounds(RectF bounds)
    {
        outline.setBounds(bounds);
    }


    // ----------------------------------------------------------
    /**
     * Getter for outline.
     *
     * @return outline
     */
    public RectangleShape getOutline()
    {
        return outline;
    }


    // ----------------------------------------------------------
    /**
     * Getter for text shape.
     *
     * @return textShape
     */
    public TextShape getTextShape()
    {
        return textShape;
    }

}
