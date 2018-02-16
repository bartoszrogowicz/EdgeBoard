package com.edgeboard.edgeboard.drawing.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.edgeboard.edgeboard.R;
import com.edgeboard.edgeboard.TextToSpeechUtils;
import com.edgeboard.edgeboard.drawing.tutorial.TutorialView;

/**
 * Created by Krystian on 2018-02-16.
 */

public class TutorialActivity extends BaseWritingActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        LinearLayout layout = (LinearLayout)findViewById(R.id.tutLayout);

        TextToSpeechUtils ttsUtils = new TextToSpeechUtils(tts);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        TutorialView view = new TutorialView(this, vibrator, ttsUtils);
        layout.addView(view);
    }
}
