package com.edgeboard.edgeboard.drawing.domain;

/**
 * Created by Krystian on 2017-12-07.
 */
public class Square {

    /**
     * Variables describe placement on screen of square edges.
     */
    private float left;
    private float right;
    private float top;
    private float bottom;

    public Square(float w, float h) {
        setSquare(w, h);
    }

    private void setSquare(float w, float h) {
        float squareSideLength = Math.min(w,h)/2;

        float widthCenter = w / 2;
        float heightCenter = h / 2;

        left = widthCenter - squareSideLength;
        right = widthCenter + squareSideLength;
        top = heightCenter - squareSideLength;
        bottom = heightCenter + squareSideLength;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }

    public float getWidth() {
        return right - left;
    }

    public float getHeight() {
        return bottom-top;
    }

}
