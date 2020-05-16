package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

// Importing all the packages and stuff I need

public class MainActivity extends AppCompatActivity {

    EditText time;
    // time will show the time
    Button b1, b2, b3, b4, goButton,playAgain,goTime;
    //Buttons names as used
    // b1 through b4 are options

    Button easy,medium,hard;
    // Buttons to choose difficulty of the game

    GridLayout grid;
    TextView timer, score, question, info;
    int x1, x2, correctAnswer, t;
    // x1 and x2 are the numbers that I will be using
    int wrongAnswer1, wrongAnswer2, wrongAnswer3;
    int answerSelected, s = 0;
    int noOfQuestions=0,timeInputted;
    long timeLeft;
    char difficulty;
    int range;
    LinearLayout difficultyLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = (Button) findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        goTime=(Button)findViewById(R.id.goWithTime);
        question = (TextView) findViewById(R.id.question);
        timer = (TextView) findViewById(R.id.timer);
        score = (TextView) findViewById(R.id.score);
        info = (TextView) findViewById(R.id.info);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        grid = (GridLayout) findViewById(R.id.grid);
        time=(EditText)findViewById(R.id.timeInput);
        playAgain=(Button)findViewById(R.id.playAgain);
        easy=(Button)findViewById(R.id.easy);
        medium=(Button)findViewById(R.id.medium);
        hard=(Button)findViewById(R.id.hard);
        difficultyLayout=(LinearLayout)findViewById(R.id.difficultyLayout);

        // Since I coded this in Windows, I have cast the views as needed
    }



    public void easyPressed(View view)
    {
        difficulty ='e';
        difficultyLayout.setVisibility(View.INVISIBLE);
        time.setVisibility(View.VISIBLE);
        goTime.setVisibility(View.VISIBLE);
    }
    public void mediumPressed(View view)
    {
        difficulty ='m';
        difficultyLayout.setVisibility(View.INVISIBLE);
        time.setVisibility(View.VISIBLE);
        goTime.setVisibility(View.VISIBLE);
    }
    public void hardPressed(View view)
    {
        difficulty ='h';
        difficultyLayout.setVisibility(View.INVISIBLE);
        time.setVisibility(View.VISIBLE);
        goTime.setVisibility(View.VISIBLE);
    }

    public void go(View view)
    {
        difficultyLayout.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
    }

    public void goAfterTime(View view)
    {
        time.setVisibility(View.INVISIBLE);
        goTime.setVisibility(View.INVISIBLE);
        timeInputted=Integer.parseInt(time.getText().toString());
        timeInputted*=1000;
        timeInputted+=100;
        timer.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        info.setVisibility(View.VISIBLE);
        grid.setVisibility(View.VISIBLE);
        letsPlayAgain(playAgain);
    }

    public void letsPlayAgain(View view)
    {
        s=0;
        noOfQuestions=0;
        timer.setText(String.valueOf(timeInputted)+"s");
        score.setText(String.valueOf(s)+"/"+String.valueOf(noOfQuestions));
        info.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        generate();
        new CountDownTimer(timeInputted,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft=1;
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                info.setText(String.valueOf(s)+"/"+String.valueOf(noOfQuestions));
                playAgain.setVisibility(View.VISIBLE);
                timeLeft=0;
            }
        }.start();
    }

    public void generate()
    {
        Random rand = new Random();
        if(difficulty =='e')
        {
            range = 20;
            x1 = rand.nextInt(20) + 1;
            x2 = rand.nextInt(20) + 1;
        }
        else if(difficulty =='m')
        {
            range = 40;
            x1 = rand.nextInt(40) -20;
            x2 = rand.nextInt(40) -20;
        }
        else {
            range = 200;
            x1 = rand.nextInt(200) - 100;
            x2 = rand.nextInt(200) - 100;
        }
        question.setText(Integer.toString(x1) + " + " + Integer.toString(x2));

        correctAnswer = x1 + x2;
        wrongAnswer1 = correctAnswer - (rand.nextInt(8) + 1);
        wrongAnswer2 = correctAnswer - (rand.nextInt(4) + 1);
        wrongAnswer3 = correctAnswer + (rand.nextInt(6) + 1);

        // adding one because a zero will have given two correct answers

        b1.setText(String.valueOf(correctAnswer));
        b2.setText(String.valueOf(wrongAnswer1));
        b3.setText(String.valueOf(wrongAnswer2));
        b4.setText(String.valueOf(wrongAnswer3));


        // To randomise the process of putting wrong answer in different box every time
        t = rand.nextInt(4) + 1;
        if (t == 1) {
            b1.setText(String.valueOf(correctAnswer));
            b2.setText(String.valueOf(wrongAnswer1));
            b3.setText(String.valueOf(wrongAnswer2));
            b4.setText(String.valueOf(wrongAnswer3));
        }
        if (t == 2) {
            b3.setText(String.valueOf(correctAnswer));
            b2.setText(String.valueOf(wrongAnswer1));
            b1.setText(String.valueOf(wrongAnswer2));
            b4.setText(String.valueOf(wrongAnswer3));
        }
        if (t == 3) {
            b4.setText(String.valueOf(correctAnswer));
            b2.setText(String.valueOf(wrongAnswer1));
            b1.setText(String.valueOf(wrongAnswer2));
            b3.setText(String.valueOf(wrongAnswer3));
        }
        if (t == 4) {
            b2.setText(String.valueOf(correctAnswer));
            b1.setText(String.valueOf(wrongAnswer1));
            b3.setText(String.valueOf(wrongAnswer2));
            b4.setText(String.valueOf(wrongAnswer3));
        }
    }


    public void choose(View view) {
        if (timeLeft != 0) {
            Button selected = (Button) view;
            answerSelected = Integer.parseInt(selected.getText().toString());
            if (correctAnswer == answerSelected) {
                //yay
                info.setText("Correct");
                s++;
            } else {
                info.setText("Incorrect");
            }
            noOfQuestions++;
            score.setText(String.valueOf(s) + "/" + String.valueOf(noOfQuestions));
            generate();
        }
    }

}
