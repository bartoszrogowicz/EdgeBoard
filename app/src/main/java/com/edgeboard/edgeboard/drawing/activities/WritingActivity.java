package com.edgeboard.edgeboard.drawing.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.edgeboard.edgeboard.R;
import com.edgeboard.edgeboard.TextToSpeechUtils;
import com.edgeboard.edgeboard.drawing.writing.WritingView;

/**
 * Created by Krystian on 2018-02-15.
 */

public class WritingActivity extends BaseWritingActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add timer presentation.
        setContentView(R.layout.activity_write);
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);


        TextToSpeechUtils ttsUtils = new TextToSpeechUtils(tts);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        WritingView view = new WritingView(this, vibrator, ttsUtils);
        layout.addView(view);
    }
}
