package com.edgeboard.edgeboard.drawing;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;

import com.edgeboard.edgeboard.drawing.domain.CornerTriangle;
import com.edgeboard.edgeboard.drawing.domain.Square;

/**
 * Created by krystian on 16.02.18.
 */

class TrianglesService {

    CornerTriangle[] triangles;

    Paint paint;

    TrianglesService() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#191970"));
        paint.setStyle(Paint.Style.FILL);
    }

    void prepareScaledTriangles(int screenWidth, int screenHeight) {
        Region clip = new Region(0, 0, screenWidth, screenHeight);
        float triangleSideLength = getTriangleSideLength(screenWidth, screenHeight);
        Square squareContainer = new Square(screenWidth, screenHeight);

        setTrianglesRegions(squareContainer, triangleSideLength, clip);
    }

    private float getTriangleSideLength(int screenWidth, int screenHeight) {
        float triangleSideLength = screenWidth < screenHeight ? (float) screenWidth : (float) screenHeight;
        return triangleSideLength / 2.3f;
    }

    private void setTrianglesRegions(Square square, float triangleSide, Region clip) {
        triangles = new CornerTriangle[4];
        triangles[0] = new CornerTriangle(square.getLeft(), square.getTop(), triangleSide, clip);
        triangles[1] = new CornerTriangle(square.getRight(), square.getTop(), triangleSide, clip);
        triangles[2] = new CornerTriangle(square.getRight(), square.getBottom(), triangleSide, clip);
        triangles[3] = new CornerTriangle(square.getLeft(), square.getBottom(), triangleSide, clip);
        triangles[0].setAsTopLeft();
        triangles[1].setAsTopRight();
        triangles[2].setAsBottomRight();
        triangles[3].setAsBottomLeft();
    }


}
