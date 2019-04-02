package com.tiagoleichs.bismarck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    //constants
    private static final String DATABASE_NAME = "Bismarck.db";
    private static final int DATABASE_VERSION = 5;

    private  SQLiteDatabase db;
    //
    public QuizDbHelper(Context context) {
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizMultipleContract.QuestionsMultipleTable.TABLE_NAME + " ( " +
                QuizMultipleContract.QuestionsMultipleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizMultipleContract.QuestionsMultipleTable.COLUMN_QUESTION + " TEXT, " +
                QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION1 + " TEXT, " +
                QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION2 + " TEXT, " +
                QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION3 + " TEXT, " +
                QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION4 + " TEXT, " +
                QuizMultipleContract.QuestionsMultipleTable.COLUMN_ANSWER_NR1 + " INTEGER, " +
                QuizMultipleContract.QuestionsMultipleTable.COLUMN_KAPITEL + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }



    //to upload the app if any change was made, need to change the Database_Version to upnext number too
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizMultipleContract.QuestionsMultipleTable.TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionsTable() {
        Question q1 = new Question("question1", "A", "B,", "C", "D",1, Question.KAPITEL1);
        addQuestion(q1);
        Question q2 = new Question("1Medium: A is correct", "A", "B,", "C", "D",1, Question.KAPITEL2);
        addQuestion(q2);
        Question q3 = new Question("1Medium: B is correct", "A", "B,", "C", "D",2, Question.KAPITEL3);
        addQuestion(q3);
        Question q4 = new Question("1Hard: A is correct", "A", "B,", "C", "D",1, Question.KAPITEL4);
        addQuestion(q4);
        Question q5 = new Question("1Hard: B is correct", "A", "B,", "C", "D",2, Question.UEBUNGEN1_4);
        addQuestion(q5);
        Question q6 = new Question("1Hard: C is correct", "A", "B,", "C", "D",3, Question.UEBUNGEN1_4);
        addQuestion(q6);

    }

    private void addQuestion (Question quetion) {
        ContentValues cv = new ContentValues();
        cv.put(QuizMultipleContract.QuestionsMultipleTable.COLUMN_QUESTION, quetion.getQuestion());
        cv.put(QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION1, quetion.getOption1());
        cv.put(QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION2, quetion.getOption2());
        cv.put(QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION3, quetion.getOption3());
        cv.put(QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION4, quetion.getOption4());
        cv.put(QuizMultipleContract.QuestionsMultipleTable.COLUMN_ANSWER_NR1, quetion.getAnswerNr());
        cv.put(QuizMultipleContract.QuestionsMultipleTable.COLUMN_KAPITEL, quetion.getKapitel());

        db.insert(QuizMultipleContract.QuestionsMultipleTable.TABLE_NAME, null, cv);
    }

/*
    public ArrayList<Question> getAllQuestions () {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
  */
    //to retrieve the database
    public ArrayList<Question> getAllQuestions (String kapitel) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String [] selectionArgs = new String[] {kapitel};
        Cursor c = db.rawQuery("SELECT * FROM " + QuizMultipleContract.QuestionsMultipleTable.TABLE_NAME +
                " WHERE " + QuizMultipleContract.QuestionsMultipleTable.COLUMN_KAPITEL + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizMultipleContract.QuestionsMultipleTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizMultipleContract.QuestionsMultipleTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizMultipleContract.QuestionsMultipleTable.COLUMN_ANSWER_NR1)));
                question.setKapitel(c.getString(c.getColumnIndex(QuizMultipleContract.QuestionsMultipleTable.COLUMN_KAPITEL)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
