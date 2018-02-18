package com.edgeboard.edgeboard.drawing.domain;

/**
 * Created by Krystian on 2017-12-07.
 */
class Point {

    private float x;
    private float y;

    Point() {}

    Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) { this.x = x; }
    public float getX() { return this.x; }
    public void setY(float y) { this.y = y; }
    public float getY() { return this.y; }
}
