package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {
    private TextView mTextField, timelefttext;
    private WarningDialog warningDialog = new WarningDialog();
    private ImageView backimge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Intent i = getIntent();
        int timeLeft = i.getIntExtra("timer", 0) * 200 * 1;
        mTextField = findViewById(R.id.textView);
        timelefttext = findViewById(R.id.timeLeft);
        backimge = findViewById(R.id.backimage3);
        backimge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;
                Intent i = new Intent(TimerActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
            }
        });

        new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextField.setText("Заказ приедет через: ");
                timelefttext.setText((millisUntilFinished / 1000) / 60 + " : " + (millisUntilFinished / 1000) % 60);
            }

            public void onFinish() {
                mTextField.setText("Ваш заказ готов!");
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        warningDialog.show(getSupportFragmentManager(), "");
    }

}