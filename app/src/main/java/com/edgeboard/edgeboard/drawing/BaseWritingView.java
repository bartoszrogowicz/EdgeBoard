package com.edgeboard.edgeboard.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;

import com.edgeboard.edgeboard.TextToSpeechUtils;
import com.edgeboard.edgeboard.drawing.domain.CornerTriangle;

/**
 * Created by Krystian on 2017-12-23.
 */

public abstract class BaseWritingView extends View {


    private TrianglesService trianglesService;
    protected WritingSequenceService writingSequenceService;

    public BaseWritingView(Context context) {
        super(context);
    }

    protected BaseWritingView(Context context, Vibrator vibrator) {
        super(context);
        this.trianglesService = new TrianglesService();
        this.writingSequenceService = new WritingSequenceService(trianglesService, vibrator);
    }

    /**
     * Carried out before ${onDraw()}
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        trianglesService.prepareScaledTriangles(right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTriangles(canvas);
    }

    private void drawTriangles(Canvas canvas) {
        for(CornerTriangle c : trianglesService.triangles) {
            canvas.drawPath(c.getPath(), trianglesService.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                handleEndOfSequence();
                invalidate();
                return false;
            case MotionEvent.ACTION_DOWN:
                handleStartWriting();
            case MotionEvent.ACTION_MOVE:
                writingSequenceService.updateSequence(event.getX(), event.getY());
                handleWriting();
                invalidate();
                return true;
            default:
                return false;
        }
    }

    protected abstract void handleStartWriting();

    /**
     * Method carried after ending of touching screen.
     */
    protected abstract void handleEndOfSequence();

    /**
     * Method carried While touching screen. Sequence is already updated.
     */
    protected abstract void handleWriting();

}
