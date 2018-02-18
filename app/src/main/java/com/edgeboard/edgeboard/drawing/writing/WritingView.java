package com.edgeboard.edgeboard.drawing.writing;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

import com.edgeboard.edgeboard.R;
import com.edgeboard.edgeboard.TextToSpeechUtils;
import com.edgeboard.edgeboard.drawing.domain.Alphabet;
import com.edgeboard.edgeboard.drawing.BaseWritingView;

import java.text.DecimalFormat;

/**
 * Created by Krystian on 2018-02-15.
 */

public class WritingView extends BaseWritingView {

    private SentenceService sentenceService;

    private TextView textView;

    private double startTime = 0;

    public WritingView(Context context) {
        super(context);
    }

    public WritingView(Context context, Vibrator vibrator, TextToSpeechUtils ttsUtils) {
        super(context, vibrator, ttsUtils);
        this.textView = (TextView)((Activity)context).findViewById(R.id.text1);
        this.sentenceService = new SentenceService(ttsUtils);
    }

    public void handleEndOfSequence() {
        Alphabet alphabetAction = writingSequenceService.getEnumFromSequence();
        sentenceService.updateSentenceAndReadText(alphabetAction);
        updateTextView();
        writingSequenceService.clearSequence();

        showToastMessage();
    }

    private void updateTextView() {
        textView.setText(sentenceService.sentence.toString());
    }

    public void showToastMessage() {
        String time = new DecimalFormat("##.##").format(writingTimeInSeconds(startTime));
        String toastText = "You wrote a letter in " + time + "seconds";
        Toast toast = Toast.makeText(getContext(), toastText, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void handleStartWriting() {
        startTime = System.currentTimeMillis();
    }

    private double writingTimeInSeconds(double startTime) {
        double stopTime = System.currentTimeMillis();
        return ((stopTime - startTime) / 1000) ;
    }
    
    protected void handleWriting() {}
}
