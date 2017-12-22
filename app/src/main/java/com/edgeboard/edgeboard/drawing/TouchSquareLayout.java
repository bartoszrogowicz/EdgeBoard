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
import com.edgeboard.edgeboard.TextToSpeechUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krystian on 2017-11-28.
 */
public class TouchSquareLayout extends View {

    private TextView text;
    private Vibrator vibrator;
    private TextToSpeechUtils textToSpeech;
    private boolean writingState;
    private boolean introRead = false;
    private int currentLetterIndex = 0;
    private LearningState learningState = LearningState.ALPHABET;
    private boolean correctLetterWrote = false;

    private static boolean WRITING = true;
    private static boolean TUTORIAL = false;

    private Paint squarePaint = new Paint(), trianglePaint = new Paint();
    private Square square;
    private CornerTriangle[] triangles;
    private CornerType lastSelectedCorner;
    private List<CornerType> sequence = new ArrayList<>();
    private int prevSequenceSize = 0;
    private StringBuilder sentence = new StringBuilder();
    private boolean capitalize = false;

    public TouchSquareLayout(Context context) {
        super(context);
    }

    public TouchSquareLayout(Context context, Vibrator vibrator, TextToSpeechUtils textToSpeech, boolean writingState) {
        super(context);
        this.text = (TextView)((Activity)context).findViewById(R.id.text1);
        this.vibrator = vibrator;
        this.textToSpeech = textToSpeech;
        this.writingState = writingState;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(square.getLeft(), square.getTop(), square.getRight(), square.getBottom(), squarePaint);
        for (CornerTriangle c: triangles) {
            canvas.drawPath(c.getPath(), trianglePaint);
        }
        if(!introRead){
            readActualWritingState();
            introRead = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initializeLayout(right, bottom);
    }

    private void readActualWritingState() {
        String intro = "Learn to write. Now learning, Alphabet. " +
                "If you want to change lesson to writing a sentence, press top right corner once. " +
                "Correct corner will vibrate once. Bad corner will vibrate twice." +
                " Current Letter: a";
        if(writingState == WRITING) {
            intro = "Write your text.";
        }
        textToSpeech.readText(intro);
    }

    private void initializeLayout(int right, int bottom) {
        float w = (float)right;
        float h = (float)bottom;
        Region clip = new Region(0, 0, (int)w, (int)h);

        this.square = new Square(w, h);
        this.square.scaleSquare(0.8f);
        float triangleSide = square.getWidth()/2.5f;

        setTriangles(triangleSide, clip);
        setPaints();
        lastSelectedCorner = CornerType.NONE;
    }

    private void setTriangles(float triangleSide, Region clip) {
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
        squarePaint.setColor(Color.parseColor("#3D59AB"));
        squarePaint.setStyle(Paint.Style.FILL);
        trianglePaint.setColor(Color.parseColor("#191970"));
        trianglePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if(writingState == WRITING) {
                    handleWriting();
                } else {
                    handleLearningStateChange();
                }
                sequence.clear();
                prevSequenceSize = 0;
                lastSelectedCorner = CornerType.NONE;
                correctLetterWrote = false;
                invalidate();
                return false;
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                prevSequenceSize = sequence.size();
                CornerType prev = CornerType.NONE;
                if(sequence.size() > 0) {
                    prev = sequence.get(sequence.size() - 1);
                }
                updateSwipeSequence(event.getX(), event.getY());
                if (writingState == TUTORIAL && sequence.size() != prevSequenceSize) {
                    if (sequence.get(sequence.size() - 1) != prev) {
                        handleTutorial();
                    }
                }

                invalidate();
                return true;
            default:
                return false;
        }
    }

    private void handleLearningStateChange() {
        if(sequence.size() == 1 && sequence.get(0) == CornerType.TOP_RIGHT) {
            switch(learningState) {
                case ALPHABET:
                    learningState = LearningState.PANGRAM;
                    textToSpeech.readText("now learning: Pangram");
                    break;
                case PANGRAM:
                    learningState = LearningState.ALPHABET;
                    textToSpeech.readText("now learning: Alphabet");
                    break;
                default:
                    break;
            }
        }
    }

    private void handleWriting() {
        updateSentence();
        printSentence();
    }

    private void updateSentence() {
        Alphabet abc = Alphabet.fromValue(sequence);
        switch(abc) {
            case CLEAR:
                clearSentence();
                textToSpeech.readText("Clear.");
                break;
            case BACK_SPACE:
                deleteLastLetterFromSentence();
                textToSpeech.readText("Back space.");
                break;
            case CAPITALIZE:
                capitalize = !capitalize;
                textToSpeech.readText("Capitalize.");
                break;
            case SPEAK:
                textToSpeech.readText(sentence.toString());
                break;
            case NOT_A_LETTER:
                break;
            default:
                writeLetterToSentence(abc);
                textToSpeech.readLetter(abc.getLetter());
                break;
        }
    }

    private void printSentence() {
        String output = sentence.toString();
        text.setText(output);
    }

    private void handleTutorial() {
        Alphabet currentCharToLearn;
        if (learningState == LearningState.ALPHABET) {
            currentCharToLearn = Alphabet.getFromAlphabetByCharIndex(currentLetterIndex);
        } else {
            currentCharToLearn = Alphabet.getFromPangramByCharIndex(currentLetterIndex);
        }
        int charSize = currentCharToLearn.getSequence().size();
        int seqSize = sequence.size();


        if(seqSize > charSize) {
            errorVibration();
        } else if(seqSize < charSize){
            if(seqSize == 1 && currentCharToLearn.getSequence().get(0) == sequence.get(0)) {
                correctLetterWrote = true;
                correctCornerVibration();
            } else if (correctLetterWrote) {
                for (int i = 1; i < sequence.size(); i++) {
                    if (sequence.get(i) == currentCharToLearn.getSequence().get(i)) {
                        correctCornerVibration();
                        correctLetterWrote = true;
                    } else {
                        errorVibration();
                        sequence.clear();
                        correctLetterWrote = false;
                    }
                }
            } else {
                errorVibration();
                sequence.clear();
            }
        } else if(charSize == seqSize && correctLetterWrote) {
                if (sequence.get(seqSize - 1) == currentCharToLearn.getSequence().get(seqSize-1)) {
                    endOfWritingVibration();
                    if(learningState == LearningState.ALPHABET) {
                        textToSpeech.readText("Next letter: " + Alphabet.getAlphabetCharByIndex(currentLetterIndex + 1));
                    } else {
                        textToSpeech.readText("Next letter: " + Alphabet.getAlphabetCharByIndex(currentLetterIndex + 1));
                    }
                    currentLetterIndex++;
                } else {
                    errorVibration();
                }
            sequence.clear();
            correctLetterWrote = false;
        }

        // get current letter sequence'
        // compare it to current sequence
        // if good - vibrate 3 times and get next letter form alphabet
        // if not - vibrate twice and reset sequence
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

    private void updateSwipeSequence(float touchX, float touchY) {
        for (CornerTriangle cT : triangles) {
            if(cT.getRegion().contains((int) touchX, (int) touchY) && lastSelectedCorner != cT.getCornerType()) {
                lastSelectedCorner = cT.getCornerType();
                if(sequence.size() <= 5) {
                    sequence.add(cT.getCornerType());
                    if(writingState) {
                        vibrator.vibrate(200);
                    }
                }
            }
        }
    }

    private void errorVibration() {
        vibrator.vibrate(new long[]{0, 200, 100, 200, 100}, -1);
    }

    private void correctCornerVibration() {
        vibrator.vibrate(200);
    }

    private void endOfWritingVibration() {
        vibrator.vibrate(new long[]{0, 100, 100, 100, 100, 100, 100}, -1);
    }
}
