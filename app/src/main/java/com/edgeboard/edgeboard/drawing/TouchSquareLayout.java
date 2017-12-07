package com.edgeboard.edgeboard.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.edgeboard.edgeboard.R;


/**
 * Created by Krystian on 2017-11-28.
 */
public class TouchSquareLayout extends View {

    private Paint squarePaint = new Paint(), trianglePaint = new Paint();
    private Square square;
    private CornerTriangle t1, t2, t3, t4;

    public TouchSquareLayout(Context context) {
        super(context);
        setBackgroundResource(R.drawable.pic);

        square = new Square();
        t1 = new CornerTriangle();
        t2 = new CornerTriangle();
        t3 = new CornerTriangle();
        t4 = new CornerTriangle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        square.setSquare(getLeft(), getRight(), getTop(), getBottom());
        square.scaleSquare(0.8f);
        float triangleSide = square.getWidth()/3;
        t1.setAsTopLeft(square.getLeft(), square.getTop(), triangleSide);
        t2.setAsTopRight(square.getRight(), square.getTop(), triangleSide);
        t3.setAsBottomRight(square.getRight(), square.getBottom(), triangleSide);
        t4.setAsBottomLeft(square.getLeft(), square.getBottom(), triangleSide);

        squarePaint.setColor(Color.RED);
        squarePaint.setStyle(Paint.Style.FILL);

        trianglePaint.setColor(Color.BLUE);
        trianglePaint.setStyle(Paint.Style.FILL);

        canvas.drawRect(square.getLeft(), square.getTop(), square.getRight(), square.getBottom(), squarePaint);
        canvas.drawPath(t1.getPath(), trianglePaint);
        canvas.drawPath(t2.getPath(), trianglePaint);
        canvas.drawPath(t3.getPath(), trianglePaint);
        canvas.drawPath(t4.getPath(), trianglePaint);

    }


}
