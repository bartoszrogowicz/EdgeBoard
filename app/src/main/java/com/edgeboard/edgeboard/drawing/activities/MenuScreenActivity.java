package com.edgeboard.edgeboard.drawing.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.edgeboard.edgeboard.R;

public class MenuScreenActivity extends AppCompatActivity {

    Vibrator vibrator;
    private static Boolean TUTORIAL = false;
    private static Boolean WRITING = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
        configureStartButton();
        configureTutorialButton();
    }

    private void configureTutorialButton() {
        Button tutorialButton = (Button) findViewById(R.id.tutorialButton);
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuScreenActivity.this, TutorialActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("state", TUTORIAL);
                intent.putExtras(b);
                startActivity(intent);
                vibrator.vibrate(500);
            }
        });
    }

    private void configureStartButton() {
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuScreenActivity.this, WritingActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("state", WRITING);
                intent.putExtras(b);
                startActivity(intent);
                vibrator.vibrate(500);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edge_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
