package com.tiagoleichs.bismarck;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore",
    KEY_QUESTION_COUNT = "keyQuestionCount",
     KEY_MILLIS_LEFT = "keyMillisLeft",
     KEY_ANSWERED = "keyAnswered",
     KEY_QUESTION_LIST = "keyQuestionList";

    private TextView txtquestionnumber,
            txtscore0,
            txttime,
            txtquestion,
            txtdifficulty;
    private RadioGroup RGbtn;
    private RadioButton rbtn1,
            rbtn2,
            rbtn3;
    private Button rbtnconfirm;

    //to change the colors
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;


    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQeustion;

    private int score;
    private boolean answered;

    private long backPressedtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtquestion = findViewById(R.id.txtquestion);
        txtquestionnumber = findViewById(R.id.txtquestionnumber);
        txtscore0 = findViewById(R.id.txtscore02);
        txttime = findViewById(R.id.txttime);
        txtdifficulty = findViewById(R.id.txtdifficulty);

        RGbtn = findViewById(R.id.RGbtn);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);

        rbtnconfirm = findViewById(R.id.rbtnconfirm);

        textColorDefaultRb = rbtn1.getTextColors();
        textColorDefaultCd = txttime.getTextColors();

        Intent intent = getIntent();
        String kapitel = intent.getStringExtra(MainActivity.EXTRA_DIFFICULTY);

        txtdifficulty.setText("Difficulty" + kapitel);


       // if (savedInstanceState == null ) {


        QuizDbHelper dbHelper = new QuizDbHelper(this);
            questionList = dbHelper.getAllQuestions(kapitel);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
       /* } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQeustion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }
*/
        rbtnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked()) {
                    checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

    }

    private void showNextQuestion() {
        rbtn1.setTextColor(textColorDefaultRb);
        rbtn2.setTextColor(textColorDefaultRb);
        rbtn3.setTextColor(textColorDefaultRb);

        RGbtn.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQeustion = questionList.get(questionCounter);

            txtquestion.setText(currentQeustion.getQuestion());
            rbtn1.setText(currentQeustion.getOption1());
            rbtn2.setText(currentQeustion.getOption2());
            rbtn3.setText(currentQeustion.getOption3());

             questionCounter++;
             txtquestionnumber.setText
                    ("Question: " + questionCounter + "/" + questionCountTotal);
             answered = false;
             rbtnconfirm.setText("Confirm");

             timeLeftInMillis = COUNTDOWN_IN_MILLIS;
             startCountDown();
        } else {
            finishQuiz();
        }

    }
    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            //changing the text
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText ();

            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();

            }
        }.start();
    }

    private void updateCountDownText (){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txttime.setText(timeFormatted);

        if (timeLeftInMillis < 1000) {
            txttime.setTextColor(Color.RED);
        }else {
            txttime.setTextColor(textColorDefaultCd);
        }

    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();


        // given a name for the RadioButton, find it and get the selected Id to save it in the variable rbSelected
        RadioButton rbSelected = findViewById(RGbtn.getCheckedRadioButtonId());
        int answerNr = RGbtn.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQeustion.getAnswerNr()) {
            score++;
            txtscore0.setText("Score: " + score);
        }
    showSolution();
    }


    private void showSolution() {
    rbtn1.setTextColor(Color.RED);
        rbtn2.setTextColor(Color.RED);
        rbtn3.setTextColor(Color.RED);

        switch (currentQeustion.getAnswerNr()){
            case 1:
                rbtn1.setTextColor(Color.GREEN);
                txtquestion.setText("Answer 1 is correct");
                break;
            case 2:
                rbtn2.setTextColor(Color.GREEN);
                txtquestion.setText("Answer 2 is correct");
                break;
            case 3:
                rbtn3.setTextColor(Color.GREEN);
                txtquestion.setText("Answer 3 is correct");
                break;

        }

        if (questionCounter < questionCountTotal) {
            rbtnconfirm.setText("Next");
        } else {
            rbtnconfirm.setText("Finish");
        }
    }

    private void finishQuiz() {
        Intent restulIntent = new Intent();
        restulIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, restulIntent);
        fileList();
    }

    @Override
    public void onBackPressed() {
        if (backPressedtime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Press back again", Toast.LENGTH_SHORT).show();
        }
            backPressedtime = System.currentTimeMillis();


    }

    //to not keep the count down in background
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            //not to crash if the count down is running
            countDownTimer.cancel();
        }
    }

    //to save the screen when rotaded
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}
