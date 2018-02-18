package com.edgeboard.edgeboard.drawing.domain;

import android.graphics.Path;
import android.graphics.Region;

/**
 * Created by Krystian on 2017-11-28.
 */
public class CornerTriangle {

    private Point cornerPoint, horizontalPoint, verticalPoint;
    private final Region region, clip;
    private final float sideLength;
    private CornerType cornerType;

    public CornerTriangle(float cornerX, float cornerY, float sideLength, Region clip) {
        setTriangle(cornerX, cornerY);
        this.clip = clip;
        this.sideLength = sideLength;
        this.region = new Region();
        this.cornerType = CornerType.NONE;
    }

    public Region getRegion() {
        return region;
    }
    public CornerType getCornerType() { return cornerType; }

    private void setTriangle(float x, float y) {
        cornerPoint = new Point(x, y);
        horizontalPoint = new Point();
        this.horizontalPoint.setY(y);
        verticalPoint = new Point();
        this.verticalPoint.setX(x);
    }

    public void setAsTopLeft() {
        setCornerTriangle(sideLength, sideLength);
        cornerType = CornerType.TOP_LEFT;
    }
    public void setAsTopRight() {
        setCornerTriangle(-sideLength, sideLength);
        cornerType = CornerType.TOP_RIGHT;
    }
    public void setAsBottomLeft() {
        setCornerTriangle(sideLength, -sideLength);
        cornerType = CornerType.BOT_LEFT;
    }
    public void setAsBottomRight() {
        setCornerTriangle(-sideLength, -sideLength);
        cornerType = CornerType.BOT_RIGHT;
    }

    private void setCornerTriangle(float xSide, float ySide) {
        horizontalPoint.setX(cornerPoint.getX() + xSide);
        verticalPoint.setY(cornerPoint.getY() + ySide);
        this.region.setPath(getPath(), clip);
    }

    public Path getPath() {
        Path p = new Path();
        p.moveTo(cornerPoint.getX(), cornerPoint.getY());
        p.lineTo(horizontalPoint.getX(), horizontalPoint.getY());
        p.lineTo(verticalPoint.getX(), verticalPoint.getY());
        p.lineTo(cornerPoint.getX(), cornerPoint.getY());
        p.close();
        return p;
    }

}
