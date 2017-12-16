package com.edgeboard.edgeboard.drawing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.edgeboard.edgeboard.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystian on 2017-11-28.
 */
public class TouchSquareLayout extends View {

    private Paint squarePaint = new Paint(), trianglePaint = new Paint();
    private Square square;
    private CornerTriangle[] triangles;
    private float touchX = 0 , touchY = 0;
    private CornerType lastSelectedCorner;
    private TextView text;
    private List<CornerType> sequence = new ArrayList<>();
    private StringBuilder sentence = new StringBuilder();
    private boolean capitalize = false;
    private Vibrator vibrator;

    public TouchSquareLayout(Context context, Vibrator vibrator) {
        super(context);
        this.vibrator = vibrator;

        float w = (float)context.getResources().getDisplayMetrics().widthPixels;
        float h = (float)context.getResources().getDisplayMetrics().heightPixels;
        Region clip = new Region(0, 0, (int)w, (int)h);

        this.square = new Square(w, h);
        this.square.scaleSquare(0.8f);
        float triangleSide = square.getWidth()/2.5f;

        setTriangles(square, triangleSide, clip);
        setPaints();

        text = (TextView)((Activity)context).findViewById(R.id.text1);
        lastSelectedCorner = CornerType.NONE;

    }

    private void setTriangles(Square square, float triangleSide, Region clip) {
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

    private void setPaints() {
        squarePaint.setColor(Color.RED);
        squarePaint.setStyle(Paint.Style.FILL);
        trianglePaint.setColor(Color.BLUE);
        trianglePaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(square.getLeft(), square.getTop(), square.getRight(), square.getBottom(), squarePaint);
        for (CornerTriangle c: triangles) {
            canvas.drawPath(c.getPath(), trianglePaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                updateSentence();
                printSentence();
                sequence.clear();
                lastSelectedCorner = CornerType.NONE;
                invalidate();
                return false;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                touchX = event.getX();
                touchY = event.getY();
                updateSwipeSequence();
                invalidate();
                return true;
            default:
                return false;
        }
    }

    private void updateSentence() {
        Alphabet abc = Alphabet.fromValue(sequence);
        switch(abc) {
            case CLEAR:
                clearSentence();
                break;
            case BACK_SPACE:
                deleteLastLetterFromSentence();
                break;
            case CAPITALIZE:
                capitalize = !capitalize;
                break;
            case NOT_A_LETTER:
                break;
            default:
                writeLetterToSentence(abc);
                break;
        }
    }

    private void printSentence() {
        String output = sentence.toString();
        text.setText(output);
    }

    private void clearSentence() {
        sentence.setLength(0);
    }

    private void deleteLastLetterFromSentence() {
        sentence.setLength(sentence.length() - 1);
    }
    private void writeLetterToSentence(Alphabet abc) {
        Character letter = abc.getLetter();
        if(capitalize) {
            letter = Character.toUpperCase(letter);
        }
        sentence.append(letter);
    }

    private void updateSwipeSequence() {
        for (CornerTriangle cT : triangles) {
            if(cT.getRegion().contains((int) touchX, (int) touchY) && lastSelectedCorner != cT.getCornerType()) {
                lastSelectedCorner = cT.getCornerType();
                if(sequence.size() <= 5) {
                    sequence.add(cT.getCornerType());
                    vibrator.vibrate(200);
                }
            }
        }
    }
}
