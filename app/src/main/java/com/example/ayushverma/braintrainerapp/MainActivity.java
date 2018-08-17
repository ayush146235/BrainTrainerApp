package com.example.ayushverma.braintrainerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int locationOfCorrectTag;
    private int correctAnswers = 0;
    private int totalAnswers = 0;
    private TextView textView7;
    private TextView textView6;
    private TextView textView4;
    private TextView textView5;
    private TextView textView2;
    private TextView textView10;
    private TextView textView8;
    private  LinearLayout linearLayout;

    public void tapped(View view) {

        //displaying Correct or Incorrect and setting Scoreboard

        totalAnswers++;

        TextView textView = (TextView) view;
        int tag = Integer.parseInt(textView.getTag().toString());
        textView10 = findViewById(R.id.textView10);
        textView2 = findViewById(R.id.textView2);


        if (tag == locationOfCorrectTag) {

            correctAnswers++;
            textView10.setText("Correct!");

        } else {

            textView10.setText("Incorrect!");

        }

        textView2.setText(Integer.toString(correctAnswers) + "/" + Integer.toString(totalAnswers));

        update();

    }

    public void update(){

        Random random = new Random();
        int a = random.nextInt(21);   //question number 1
        int b = random.nextInt(21);   //question number 2
        locationOfCorrectTag = random.nextInt(4);   //choose which textView  in gridLayout

        //textView displaying question
        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText(Integer.toString(a) + " + " + Integer.toString(b));

        //TextView displaying Solutions
        textView7 = findViewById(R.id.textView7);    //tag=0
        textView6 = findViewById(R.id.textView6);    //tag=1
        textView4 = findViewById(R.id.textView4);    //tag=2
        textView5 = findViewById(R.id.textView5);    //tag=3

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(a + b);

        //setting values for textViews
        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectTag) {
                switch (i) {
                    case 0:
                        textView7.setText(Integer.toString(a + b));
                        break;
                    case 1:
                        textView6.setText(Integer.toString(a + b));
                        break;
                    case 2:
                        textView4.setText(Integer.toString(a + b));
                        break;
                    case 3:
                        textView5.setText(Integer.toString(a + b));
                        break;
                }
            } else {

                int d = random.nextInt(11) + (a + b);
                while (arrayList.contains(d)) {
                    d = random.nextInt(11) + (a + b);
                }
                switch (i) {
                    case 0:
                        arrayList.add(d);
                        textView7.setText(Integer.toString(d));
                        break;
                    case 1:
                        arrayList.add(d);
                        textView6.setText(Integer.toString(d));
                        break;
                    case 2:
                        arrayList.add(d);
                        textView4.setText(Integer.toString(d));
                        break;
                    case 3:
                        arrayList.add(d);
                        textView5.setText(Integer.toString(d));
                        break;
                }

            }

        }
    }

    public void tryAgain(View view){

        textView2.setText("0/0");
        timer();
        linearLayout.setVisibility(View.GONE);
        textView7.setClickable(true);
        textView6.setClickable(true);
        textView4.setClickable(true);
        textView5.setClickable(true);
        textView10.setText("");
        textView8.setText("Score : ");
        totalAnswers=0;
        correctAnswers=0;

    }

    public void timer(){

        final TextView textView = findViewById(R.id.textView);
        linearLayout=findViewById(R.id.LinearLayout);

        new CountDownTimer(30000+100, 1000) {
            @Override
            public void onTick(long l) {
                int remainingSeconds = (int) l / 1000;
                if (remainingSeconds < 10) {
                    textView.setText("0" + Integer.toString(remainingSeconds));
                } else {
                    textView.setText(Integer.toString(remainingSeconds));
                }
            }

            @Override
            public void onFinish() {

                textView.setText("00");
                textView7.setClickable(false);
                textView6.setClickable(false);
                textView4.setClickable(false);
                textView5.setClickable(false);
                textView8=findViewById(R.id.textView8);
                textView8.append(correctAnswers+"/"+totalAnswers);
                linearLayout.setVisibility(View.VISIBLE);

            }

        }.start();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        update();

        timer();

    }
}
