package com.edgeboard.edgeboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModeSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_screen);

        configureFreeWriteButton();
        configureGuessWordButton();
    }

    private void configureGuessWordButton() {
        Button freeWriteButton = (Button) findViewById(R.id.guessWordButton);
        freeWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ModeSelectActivity.this, SquareActivity.class));
            }
        });
    }

    private void configureFreeWriteButton() {
        Button backButton = (Button) findViewById(R.id.freeWriteButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ModeSelectActivity.this, SquareActivity.class));
            }
        });
    }
}
