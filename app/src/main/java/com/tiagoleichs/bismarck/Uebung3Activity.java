package com.tiagoleichs.bismarck;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Uebung3Activity extends AppCompatActivity {

    private TextView textView, txt1, txt2, txt3, txt4, txt5, txt6;
    private EditText editText, editText2, editText3;
    private Button button;

    private String edttext1, edttext2, edttext3;

    private ArrayList<Uebung3> uebung3List;
    private int questionCounter;
    private int questionCountTotal;
    private Uebung3 currentQeustion;

    private boolean answered;

    private List<TextView> txtList;
    private List<String> databaseAnswer;
    private int i;
    private int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebung3);

        textView = findViewById(R.id.textView);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);




        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

        button = findViewById(R.id.button);


        Intent intent = getIntent();
        String kapitelU = intent.getStringExtra(MainActivity.EXTRA_DIFFICULTY);

        Uebung3DbHelper uebung3DbHelper = new Uebung3DbHelper(this);
        uebung3List = uebung3DbHelper.getUebung3(kapitelU);
        questionCountTotal = uebung3List.size();
        Collections.shuffle(uebung3List);

        showNextQuestion();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttext1 = editText.getText().toString();
                edttext2 = editText2.getText().toString();
                edttext3 = editText3.getText().toString();
                if(!answered) {
                    if (edttext1.equals("") || edttext2.equals("") || edttext3.equals("")) {
                        Toast.makeText(Uebung3Activity.this,"Schreibe eine Antwort.", Toast.LENGTH_LONG).show();
                    } else {
                        checkAnswer();
                    }
                }else{
                showNextQuestion();
            }
            }
        });



    }



    private void showNextQuestion() {
        if (questionCounter < questionCountTotal) {
            currentQeustion = uebung3List.get(questionCounter);

            textView.setText(currentQeustion.getVerb());
            txt1.setText(currentQeustion.getIch());
            txt2.setText(currentQeustion.getDu());
            txt3.setText(currentQeustion.getEr());
            txt4.setText(currentQeustion.getWir());
            txt5.setText(currentQeustion.getIhr());
            txt6.setText(currentQeustion.getSie());

            txt1.setTextColor(Color.BLACK);
            txt2.setTextColor(Color.BLACK);
            txt3.setTextColor(Color.BLACK);
            txt4.setTextColor(Color.BLACK);
            txt5.setTextColor(Color.BLACK);
            txt6.setTextColor(Color.BLACK);




            questionCounter++;
            answered = false;
            button.setText("Confirm");
        }else {
            finishQuiz();
        }
        }

    private void finishQuiz() {
        Toast.makeText(Uebung3Activity.this, "Gratuliere", Toast.LENGTH_LONG).show();
    }



    private void checkAnswer() {
        answered = true;



        txtList = new ArrayList<>();

        if (currentQeustion.getIch().equals("")){
            txtList.add(txt1);
        }
        if (currentQeustion.getDu().equals("")){
            txtList.add(txt2);
        }
        if (currentQeustion.getEr().equals("")){
            txtList.add(txt3);
        }
        if (currentQeustion.getWir().equals("")){
            txtList.add(txt4);
        }
        if (currentQeustion.getIhr().equals("")){
            txtList.add(txt5);
        }
        if (currentQeustion.getSie().equals("")){
            txtList.add(txt6);
        }

        databaseAnswer = new ArrayList();
        databaseAnswer.add(currentQeustion.getAnswer1());
        databaseAnswer.add(currentQeustion.getAnswer2());
        databaseAnswer.add(currentQeustion.getAnswer3());

        if(edttext1.equals(currentQeustion.getAnswer1())  )
        {
            txtList.get(0).setText(currentQeustion.getAnswer1());


        }
        if(edttext2.equals(currentQeustion.getAnswer2())){
                txtList.get(1).setText(currentQeustion.getAnswer2());

        }
        if(edttext3.equals(currentQeustion.getAnswer3())){
            txtList.get(2).setText(currentQeustion.getAnswer3());
        }

            if(edttext1.equals(currentQeustion.getAnswer1()) && edttext2.equals(currentQeustion.getAnswer2()) && edttext3.equals(currentQeustion.getAnswer3())){
            showSolution();
            }

        else {Toast.makeText(Uebung3Activity.this, "Somwthing is wrong", Toast.LENGTH_LONG).show();

        }
    }

    private void showSolution() {




        for (i = 0; i < txtList.size(); i++) {
            for (y = 0; y < databaseAnswer.size(); y++) {

                i = y;
                txtList.get(i).setText(databaseAnswer.get(y));
                txtList.get(i).setTextColor(Color.GREEN);

            }
        }

        editText3.getText().clear();
        editText.getText().clear();
        editText2.getText().clear();




        if (questionCounter < questionCountTotal) {
            button.setText("Next");
        } else {
            button.setText("Finish");
        }
    }
}
