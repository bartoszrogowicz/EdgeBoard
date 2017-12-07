package com.edgeboard.edgeboard.drawing;

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

    Square() {}

    public void setSquare(float leftSide, float rightSide, float topSide, float bottomSide) {
        float maxWidth = rightSide - leftSide;
        float maxHeight = bottomSide - topSide;
        float squareSideLength = Math.min(maxWidth,maxHeight)/2;

        float widthCenter = maxWidth / 2;
        float heightCenter = maxHeight / 2;

        left = widthCenter - squareSideLength;
        right = widthCenter + squareSideLength;
        top = heightCenter - squareSideLength;
        bottom = heightCenter + squareSideLength;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getWidth() {
        return right - left;
    }

    public float getHeight() {
        return bottom-top;
    }

    public float getVerticalCenter() {
        return getHeight() / 2;
    }

    public float getHorizontalCenter() {
        return getWidth() / 2;
    }

    public float[] getTopLeftCorner() {
        return new float[]{left, top};
    }
    public float[] getTopRightCorner() {
        return new float[]{right, top};
    }
    public float[] getBottomLeftCorner() {
        return new float[]{left, bottom};
    }
    public float[] getBottomRightCorner() {
        return new float[]{right, bottom};
    }

    public void scaleSquare(float scale) {
        float deltaHeight = (getHeight() * (1 - scale))/2;
        float deltaWidth = (getWidth() * (1 - scale))/2;

        left += deltaWidth;
        right -= deltaWidth;
        top += deltaHeight;
        bottom -= deltaHeight;
    }

}
