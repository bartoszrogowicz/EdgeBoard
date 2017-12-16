package com.edgeboard.edgeboard;

import android.content.Context;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.edgeboard.edgeboard.drawing.TouchSquareLayout;

public class SquareActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    TouchSquareLayout touchSquareLayout;
    private TextToSpeechUtils tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);

        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        touchSquareLayout = new TouchSquareLayout(this, v);
        layout.addView(touchSquareLayout);

    }


    @Override
    public void onInit(int status) {
       tts.onInit(status);
    }

    @Override
    public void onDestroy() {
        //tts.destroySpeech();
        super.onDestroy();
    }
}
