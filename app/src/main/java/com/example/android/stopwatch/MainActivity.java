package com.example.android.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timer;
    private Handler handler;
    private Update update;
    private long startTime;
    private boolean stopped;
    private TextView button;
    private int last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.timer);
        button = findViewById(R.id.button);
        handler = new Handler();
        update = new Update();
        stopped=true;
        last=0;
    }

    public void buttonPressed(View v) {
        if (stopped==true) {
            button.setText("STOP!!");
            startTime=System.currentTimeMillis();
            handler.postDelayed(update,1000);
            stopped=false;
        }
        else {
            button.setText("GO!!");
            handler.removeCallbacks(update);
            last=Integer.parseInt(timer.getText().toString());
            stopped=true;
        }


    }

    private class Update implements Runnable {

        @Override
        public void run() {
            long current = System.currentTimeMillis();
            long elapsed = (current-startTime)/1000 +last;
            timer.setText(""+elapsed);
            handler.postDelayed(update,1000);
        }

    }

}
