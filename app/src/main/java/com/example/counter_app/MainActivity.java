package com.example.counter_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView counter;
    private Button btnreset;
    private Button btnPause;
    private Button btnexit;
    private int MY_STD_ID = 2019160255;
    boolean pause = false;
    boolean running = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.counting);
        Button btnStart = findViewById(R.id.btnStart);
        btnreset = findViewById(R.id.btnreset);
        btnPause = findViewById(R.id.btnPause);
        btnexit = findViewById(R.id.btnexit);

        btnexit.setOnClickListener(view -> finish());
        btnStart.setOnClickListener(view -> {
            running = true;
            ExampleMyId exampleMyId = new ExampleMyId();
            exampleMyId.execute(running);
        });

        btnreset.setOnClickListener(view -> {
            MY_STD_ID = 2019160255;
            counter.setText(String.valueOf(MY_STD_ID));
            pause = true;
        });

        btnPause.setOnClickListener(view -> {
            if (pause == true) {
                pause = false;
            } else {
                pause = true;
            }
        });

    }
    class ExampleMyId extends AsyncTask<Boolean, Integer, Integer> {

        @Override
        protected Integer doInBackground(Boolean... booleans) {
            boolean running = false;
            while (running) {
                if (pause == false) {
                    MY_STD_ID--;
                    publishProgress(MY_STD_ID);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            counter.setText(String.valueOf(MY_STD_ID));
        }
    }
}