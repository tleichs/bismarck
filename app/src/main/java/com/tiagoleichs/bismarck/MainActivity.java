package com.tiagoleichs.bismarck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import static com.tiagoleichs.bismarck.Question.KAPITEL1;
import static com.tiagoleichs.bismarck.Question.KAPITEL2;
import static com.tiagoleichs.bismarck.Question.KAPITEL3;
import static com.tiagoleichs.bismarck.Question.KAPITEL4;
import static com.tiagoleichs.bismarck.Question.UEBUNGEN1_4;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    public CircleMenu circleMenu;

/*
    String difficulty[]= {
            "Easy",
            "Medium",
            "Hard",
            "Easy",
            "Medium",
    };
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.login, R.drawable.logout)
                .addSubMenu(Color.parseColor("#1a237a"), R.drawable.verfugbar)
                .addSubMenu(Color.parseColor("#1a237a"), R.drawable.verfugbar)
                .addSubMenu(Color.parseColor("#1a237a"), R.drawable.verfugbar)
              // .addSubMenu(Color.parseColor("#1a237a"), R.drawable.verfugbar)
              // .addSubMenu(Color.parseColor("#1a237a"), R.drawable.verfugbar)
               // .addSubMenu(Color.parseColor("#1a237a"), R.drawable.verfugbar)
              //  .addSubMenu(Color.parseColor("#1a237a"), R.drawable.verfugbar)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new //instanciar, colocar na memória o objeto.
                                        Intent(MainActivity.this, Uebung3Activity.class);
                                intent.putExtra(EXTRA_DIFFICULTY, KAPITEL1);
                                //ForResult get a result from the activity that starts
                                startActivityForResult(intent, REQUEST_CODE_QUIZ);
                                break;



                            case 1:
                                Intent intent3 = new Intent(MainActivity.this, QuizActivity.class);
                                intent3.putExtra(EXTRA_DIFFICULTY, KAPITEL1);
                                //ForResult get a result from the activity that starts
                                startActivityForResult(intent3, REQUEST_CODE_QUIZ);
                                break;

                            case 2:
                                Intent intent4 = new Intent(MainActivity.this, Main2Activity.class);
                                intent4.putExtra(EXTRA_DIFFICULTY, UEBUNGEN1_4);
                                //ForResult get a result from the activity that starts
                                startActivityForResult(intent4, REQUEST_CODE_QUIZ);
                                break;


                        }






                    }
                });
    }

}
