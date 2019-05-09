package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextVeiw;
    Button playAgainButton;
    CountDownTimer counDownTimer;
    ConstraintLayout constraintLayoutTwo;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void chooseAnswer(View view){


        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {

            resultTextView.setText("Correct !");
            score++;


        }else{
            resultTextView.setText("Wrong :(");
        }

//        resultTextView.setVisibility(View.VISIBLE);
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));

        newQuestion();


    }

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        constraintLayoutTwo.setVisibility(View.VISIBLE);


    }

    public void newQuestion(){

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer =  rand.nextInt(4);

        answers.clear();

        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }

            else{
                int wrongAnswer = rand.nextInt(21);
                while (wrongAnswer == a+b){

                    wrongAnswer = rand.nextInt(21);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));




    }


    public void playAgain(View view){


        // score reset
        scoreTextView.setText("0/0");

        // timer reset
        counDownTimer.start();
        // play again invisible
        playAgainButton.setVisibility(View.INVISIBLE);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextVeiw = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        constraintLayoutTwo = findViewById(R.id.constraintTwo);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);




        goButton = findViewById(R.id.goButton);

        newQuestion();

        counDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextVeiw.setText(String.valueOf(millisUntilFinished / 1000 )+'s');

            }

            @Override
            public void onFinish() {

                resultTextView.setText("Done !");
                playAgainButton.setVisibility(View.VISIBLE);

            }
        };
        counDownTimer.start();

    }
}
