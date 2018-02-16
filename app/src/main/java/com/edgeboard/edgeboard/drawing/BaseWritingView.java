package com.edgeboard.edgeboard.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;

import com.edgeboard.edgeboard.TextToSpeechUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystian on 2017-12-23.
 */

public abstract class BaseWritingView extends View implements SequenceService, TrianglesService {

    protected Vibrator vibrator;
    protected TextToSpeechUtils ttsUtils;

    protected List<CornerType> sequence = new ArrayList<>();

    public BaseWritingView(Context context) {
        super(context);
    }

    public BaseWritingView(Context context, Vibrator vibrator, TextToSpeechUtils ttsUtils) {
        super(context);
        this.vibrator = vibrator;
        this.ttsUtils = ttsUtils;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        prepareTriangles();
    }

    public void prepareTriangles() {
        // prepare triangles to draw in onDraw()
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw triangles
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                handleEndOfSequence();
                invalidate();
                return false;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                updateSequence(event.getX(), event.getY());
                handleWriting();
                invalidate();
                return true;
            default:
                return false;
        }
    }

    public abstract void handleEndOfSequence();

    public void updateSequence(final float x, final float y) {

    }

    protected abstract void handleWriting();

}
