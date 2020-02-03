package com.example.a112stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int iterations = 0;
    boolean beginning;
    TextView textViewAction;
    boolean wasBeginning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAction = findViewById(R.id.textView);

        if (savedInstanceState != null){
            iterations = savedInstanceState.getInt("iterations");
            beginning = savedInstanceState.getBoolean("beginning");
            textViewAction.setText(savedInstanceState.getString("textViewAction"));
        }
        begin();
    }

    void begin(){
        final TextView textView = findViewById(R.id.textView2);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf(iterations));
                if (beginning){
                    iterations++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    public void onClickStart(View view) {
        beginning = true;
        textViewAction.setText("Секундомер запущен");
    }

    public void onClickStop(View view) {
        beginning = false;
        textViewAction.setText("Секундомер остановлен");
    }

    public void onClickReset(View view) {
        beginning = false;
        iterations = 0;
        textViewAction.setText("Секундомер сброшен");
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (wasBeginning){
            beginning = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasBeginning = beginning;
        beginning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasBeginning){
            beginning = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasBeginning = beginning;
        beginning = false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (wasBeginning){
            beginning = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wasBeginning = beginning;
        beginning = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("iterations",iterations);
        outState.putBoolean("beginning",beginning);
        outState.putString("textViewAction",textViewAction.getText().toString());
    }
}
