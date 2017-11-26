package com.edgeboard.edgeboard;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

/**
 * Created by bartek on 25.11.2017.
 */

public class TextToSpeechUtils extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;

    public TextToSpeechUtils(TextToSpeech tts) {

        this.tts = tts;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);
        TextToSpeech speechHandle = new TextToSpeech(this, this);
        tts = speechHandle;
        configureSpeechButton();
    }

    private void configureSpeechButton() {

        Button btnSpeak = (Button) findViewById(R.id.speech_button);
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText textPlain = (EditText) findViewById(R.id.speech_text);
                speakOut(textPlain);
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                Log.e("TTS", "This language is not supported");
            }

        } else {
            Log.e("TTL", "Initialization Failed");
        }
    }

    public void destroySpeech() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    public void speakOut(EditText speechText) {

        String convertedText = speechText.getText().toString();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(convertedText, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
}
