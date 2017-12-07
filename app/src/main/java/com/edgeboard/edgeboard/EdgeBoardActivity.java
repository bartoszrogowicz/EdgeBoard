package com.edgeboard.edgeboard;

//import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class EdgeBoardActivity extends AppCompatActivity {

   // Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edge_board);

       // vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        configureStartButton();
        configureTutorialButton();
    }

    private void configureTutorialButton() {
        Button tutorialButton = (Button) findViewById(R.id.tutorialButton);
        tutorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EdgeBoardActivity.this, SquareActivity.class));
               // vibrator.vibrate(500);
            }
        });
    }

    private void configureStartButton() {
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EdgeBoardActivity.this, ModeSelectActivity.class));
               // vibrator.vibrate(500);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edge_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
