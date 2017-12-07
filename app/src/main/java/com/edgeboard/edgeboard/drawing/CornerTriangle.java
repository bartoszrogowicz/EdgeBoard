package com.edgeboard.edgeboard.drawing;

import android.graphics.Path;

/**
 * Created by Krystian on 2017-11-28.
 */
public class CornerTriangle {

    private Point cornerPoint, horizontalPoint, verticalPoint;
    private float sideLength;

    CornerTriangle() {}

    public void setTriangle(float x, float y, float sideLength) {
        cornerPoint = new Point(x, y);
        horizontalPoint = new Point();
        this.horizontalPoint.setY(y);
        verticalPoint = new Point();
        this.verticalPoint.setX(x);
        this.sideLength = sideLength;
    }

    public Point getCornerPoint() {
        return cornerPoint;
    }

    public void setCornerPoint(Point cornerPoint) {
        this.cornerPoint = cornerPoint;
    }

    public Point getHorizontalPoint() {
        return horizontalPoint;
    }

    public void setHorizontalPoint(Point horizontalPoint) {
        this.horizontalPoint = horizontalPoint;
    }

    public Point getVerticalPoint() {
        return verticalPoint;
    }

    public void setVerticalPoint(Point verticalPoint) {
        this.verticalPoint = verticalPoint;
    }

    public void setAsTopLeft(float x, float y, float sideLength) {
        setTriangle(x, y, sideLength);
        horizontalPoint.setX(cornerPoint.getX() + sideLength);
        verticalPoint.setY(cornerPoint.getY() + sideLength);
    }
    public void setAsTopRight(float x, float y, float sideLength) {
        setTriangle(x, y, sideLength);
        horizontalPoint.setX(cornerPoint.getX() - sideLength);
        verticalPoint.setY(cornerPoint.getY() + sideLength);
    }
    public void setAsBottomLeft(float x, float y, float sideLength) {
        setTriangle(x, y, sideLength);
        horizontalPoint.setX(cornerPoint.getX() + sideLength);
        verticalPoint.setY(cornerPoint.getY() - sideLength);
    }
    public void setAsBottomRight(float x, float y, float sideLength) {
        setTriangle(x, y, sideLength);
        horizontalPoint.setX(cornerPoint.getX() - sideLength);
        verticalPoint.setY(cornerPoint.getY() - sideLength);
    }

    public Path getPath() {
        Path p = new Path();
        p.moveTo(cornerPoint.getX(), cornerPoint.getY());
        p.lineTo(horizontalPoint.getX(), horizontalPoint.getY());
        // p.moveTo(horizontalPoint.getX(), horizontalPoint.getY());
        p.lineTo(verticalPoint.getX(), verticalPoint.getY());
        // p.moveTo(verticalPoint.getX(), verticalPoint.getY());
        p.lineTo(cornerPoint.getX(), cornerPoint.getY());
        p.close();
        return p;
    }

}
