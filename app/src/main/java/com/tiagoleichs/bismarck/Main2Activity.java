package com.tiagoleichs.bismarck;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    //private static final long COUNTDOWN_IN_MILLIS = 30000;

    private static final String KEY_SCORE = "keyScore",
            KEY_QUESTION_COUNT = "keyQuestionCount",
            KEY_MILLIS_LEFT = "keyMillisLeft",
            KEY_ANSWERED = "keyAnswered",
            KEY_QUESTION_LIST = "keyQuestionList";

    private static final int TYPEOFBOLEAN_A = 2;
    private static final int TYPEOFBOLEAN_B = 3;

    private TextView txtquestionnumber,
            txtscore0,
           // txttime,
            txtquestion,
            txtdifficulty;

    private CheckBox chBox1, chBox2, chBox3, chBox4, chBox5;
    private Button rbtnconfirm;

    //to change the colors
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

   // private CountDownTimer countDownTimer;
   // private long timeLeftInMillis;


    private ArrayList<QuestionMultiple> questionMultipleList;
    private List<CheckBox> listCheckBox;
    private int questionCounter;
    private int questionCountTotal;
    private QuestionMultiple currentQeustion;

    private int score = 10;
    private boolean answered;
    private boolean typeA;
    private boolean typeB;
    private int answertype;


    private long backPressedtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtquestion = findViewById(R.id.txtquestion);
        txtquestionnumber = findViewById(R.id.txtquestionnumber);
        txtscore0 = findViewById(R.id.txtscore02);
        txtscore0.setText("Score: " + score);
        //txttime = findViewById(R.id.txttime);
        txtdifficulty = findViewById(R.id.txtdifficulty);


        chBox1 = findViewById(R.id.checkBox);
        chBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int checked = listCheckBox.indexOf(chBox1) +1;
                if (listCheckBox.indexOf(chBox1) +1 == currentQeustion.getAnswerNr1() ||
                        listCheckBox.indexOf(chBox1) +1 == currentQeustion.getAnswerNr2() ||
                        listCheckBox.indexOf(chBox1) +1 == currentQeustion.getAnswerNr3())
                {
                    chBox1.setTextColor(Color.GREEN);
                    answertype ++;
                }else {
                    chBox1.toggle();
                    score --;
                }
            }
        });


        chBox2 = findViewById(R.id.checkBox2);
        chBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked = listCheckBox.indexOf(chBox2) +1;
                if (checked == currentQeustion.getAnswerNr1() ||
                        checked == currentQeustion.getAnswerNr2() || checked == currentQeustion.getAnswerNr3())
                {
                    chBox2.setTextColor(Color.GREEN);
                    answertype ++;
                }else {
                    chBox2.toggle();
                    score --;
                }
            }
        });
        chBox3 = findViewById(R.id.checkBox3);
        chBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked = listCheckBox.indexOf(chBox3) +1;
                if (checked == currentQeustion.getAnswerNr1() ||
                        checked == currentQeustion.getAnswerNr2() || checked == currentQeustion.getAnswerNr3())
                {
                    chBox3.setTextColor(Color.GREEN);
                    answertype ++;
                }else {
                    chBox3.toggle();
                    score --;
                }
            }
        });
        chBox4 = findViewById(R.id.checkBox4);
        chBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked = listCheckBox.indexOf(chBox4) +1;
                if (checked == currentQeustion.getAnswerNr1() ||
                        checked == currentQeustion.getAnswerNr2() || checked == currentQeustion.getAnswerNr3())
                {
                    chBox4.setTextColor(Color.GREEN);
                    answertype ++;
                }else {
                    chBox4.toggle();
                    score --;
                    txtscore0.setText("Score: " + score);
                }
            }
        });
        chBox5 = findViewById(R.id.checkBox5);
        chBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked = listCheckBox.indexOf(chBox5) +1;
                if (checked == currentQeustion.getAnswerNr1() ||
                        checked == currentQeustion.getAnswerNr2() || checked == currentQeustion.getAnswerNr3())
                {
                    chBox5.setTextColor(Color.GREEN);
                    answertype ++;
                }else {
                    chBox5.toggle();
                    score --;
                }
            }
        });

       listCheckBox = new ArrayList<CheckBox>();
        listCheckBox.add(chBox1);
        listCheckBox.add(chBox2);
        listCheckBox.add(chBox3);
        listCheckBox.add(chBox4);
        listCheckBox.add(chBox5);






        rbtnconfirm = findViewById(R.id.rbtnconfirm);

        textColorDefaultRb = chBox1.getTextColors();
       // textColorDefaultCd = txttime.getTextColors();

        Intent intent = getIntent();
        String kapitelM = intent.getStringExtra(MainActivity.EXTRA_DIFFICULTY);

        txtdifficulty.setText("Difficulty: " + kapitelM);


        // if (savedInstanceState == null ) {


        QuizMultipleDbhelper dbHelper2 = new QuizMultipleDbhelper(this);
        questionMultipleList = dbHelper2.getQuestions(kapitelM);
        questionCountTotal = questionMultipleList.size();
        Collections.shuffle(questionMultipleList);


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
                    if (
                            chBox1.isChecked() || chBox2.isChecked() || chBox3.isChecked() || chBox4.isChecked() || chBox5.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(Main2Activity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });



    }



    private void onClickEvent() {

    }

    private void showNextQuestion() {


        chBox1.setTextColor(textColorDefaultRb);
        chBox2.setTextColor(textColorDefaultRb);
        chBox3.setTextColor(textColorDefaultRb);
        chBox4.setTextColor(textColorDefaultRb);
        chBox5.setTextColor(textColorDefaultRb);

        if(chBox1.isChecked()){
            chBox1.toggle();
        }
        if(chBox2.isChecked()){
            chBox2.toggle();
        }
        if(chBox3.isChecked()){
            chBox3.toggle();
        }
        if(chBox4.isChecked()){
            chBox4.toggle();
        }
        if(chBox5.isChecked()){
            chBox5.toggle();
        }





        //RGbtn.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQeustion = questionMultipleList.get(questionCounter);

            txtquestion.setText(currentQeustion.getQuestionM());
            chBox1.setText(currentQeustion.getOption1());

            chBox2.setText(currentQeustion.getOption2());
            chBox3.setText(currentQeustion.getOption3());
            chBox4.setText(currentQeustion.getOption4());
            chBox5.setText(currentQeustion.getOption5());

            int a = currentQeustion.getAnswerNr1();
            int b = currentQeustion.getAnswerNr2();
            if (a == b){
                typeA = true;
                typeB = false;
            }else{
                typeB = true;
                typeA = false;
            };

            questionCounter++;
            txtquestionnumber.setText
                    ("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            rbtnconfirm.setText("Confirm");
            answertype = 0;


            //timeLeftInMillis = COUNTDOWN_IN_MILLIS;
           // startCountDown();
        } else {
            finishQuiz();
        }

    }
    /*
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
*/
    private void checkAnswer() {
        answered = true;

        if(typeA && TYPEOFBOLEAN_A == answertype ){

                score++;
                txtscore0.setText("Score: " + score);
            showSolution();

        } else if(typeB && TYPEOFBOLEAN_B == answertype){

                score++;
                txtscore0.setText("Score: " + score);
            showSolution();

        }else {
            Toast.makeText(Main2Activity.this, "Something is wrong", Toast.LENGTH_LONG).show();
        }



        //countDownTimer.cancel();

        // given a name for the RadioButton, find it and get the selected Id to save it in the variable rbSelected
        /*
        RadioButton rbSelected = findViewById(RGbtn.getCheckedRadioButtonId());
        int answerNr = RGbtn.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQeustion.getAnswerNr1() && answerNr == currentQeustion.getAnswerNr2() && answerNr == currentQeustion.getAnswerNr3()) {
            score++;
            txtscore0.setText("Score: " + score);
        }
        */

    }


    private void showSolution() {
        chBox3.setTextColor(Color.RED);
        chBox2.setTextColor(Color.RED);
        chBox1.setTextColor(Color.RED);
        chBox4.setTextColor(Color.RED);
        chBox5.setTextColor(Color.RED);


        switch (currentQeustion.getAnswerNr1()){
            case 1:
                chBox1.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 2:
                chBox2.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 3:
                chBox3.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 4:
                chBox4.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 5:
                chBox5.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;

        }
        switch (currentQeustion.getAnswerNr2()){
            case 1:
                chBox1.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 2:
                chBox2.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 3:
                chBox3.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 4:
                chBox4.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 5:
                chBox5.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;

        }
        switch (currentQeustion.getAnswerNr3()){
            case 1:
                chBox1.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 2:
                chBox2.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 3:
                chBox3.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 4:
                chBox4.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
                break;
            case 5:
                chBox5.setTextColor(Color.GREEN);
                txtquestion.setText("Korrekt sind:");
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
/*
    //to not keep the count down in background
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            //not to crash if the count down is running
            countDownTimer.cancel();
        }
    }
*/
    //to save the screen when rotaded
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        //outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionMultipleList);
    }
}


