package com.edgeboard.edgeboard;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.Locale;

public class SquareActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    //private final TextToSpeechUtils tts = new TextToSpeechUtils();
    private TextToSpeechUtils tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);
        TextToSpeech speechHandle = new TextToSpeech(this, this);
        tts = new TextToSpeechUtils(speechHandle);

        configureSpeechButton();
    }


    private void configureSpeechButton() {

        Button btnSpeak = (Button) findViewById(R.id.speech_button);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText textPlain = (EditText) findViewById(R.id.speech_text);
                tts.speakOut(textPlain);
            }
        });
    }

    @Override
    public void onInit(int status) {
       tts.onInit(status);
    }

    @Override
    public void onDestroy() {
        tts.destroySpeech();
        super.onDestroy();
    }

}
