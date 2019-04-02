package com.tiagoleichs.bismarck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tiagoleichs.bismarck.QuizMultipleContract.*;

import java.util.ArrayList;

public class QuizMultipleDbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Bismarck.db";
    private static final int DATABASE_VERSION = 8;

    private  SQLiteDatabase db;

    public QuizMultipleDbhelper(Context context){
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizMultipleContract.QuestionsMultipleTable.TABLE_NAME2 + " ( " +
                QuestionsMultipleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsMultipleTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsMultipleTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsMultipleTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsMultipleTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsMultipleTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsMultipleTable.COLUMN_OPTION5 + " TEXT, " +
                QuestionsMultipleTable.COLUMN_KAPITEL + " TEXT," +
                QuestionsMultipleTable.COLUMN_ANSWER_NR1 + " INTEGER, " +
                QuestionsMultipleTable.COLUMN_ANSWER_NR2 + " INTEGER, " +
                QuestionsMultipleTable.COLUMN_ANSWER_NR3 + " INTEGER " +

                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();

    }

    private void fillQuestionsTable() {
        QuestionMultiple q1 = new QuestionMultiple("Frage?AB '%1$s' ", "Aaa", "Bbb", "Ccc", "Ddd", "Eee", QuestionMultiple.KAPITEL1, 1, 1, 2);
        addQuestionMultiple(q1);
        QuestionMultiple q2 = new QuestionMultiple("FrageABC", "A", "B", "C", "D", "E", QuestionMultiple.KAPITEL1, 1, 2, 3);
        addQuestionMultiple(q2);
        QuestionMultiple q3 = new QuestionMultiple("Frage?", "A", "B", "C", "D", "E", QuestionMultiple.KAPITEL2, 1, 1, 2);
        addQuestionMultiple(q3);
        QuestionMultiple q4 = new QuestionMultiple("Frage?", "A", "B", "C", "D", "E", QuestionMultiple.KAPITEL3, 1, 1, 2);
        addQuestionMultiple(q4);
        QuestionMultiple q5 = new QuestionMultiple("Frage?", "A", "B", "C", "D", "E", QuestionMultiple.KAPITEL4, 1, 2, 3);
        addQuestionMultiple(q5);
    }

    private void addQuestionMultiple(QuestionMultiple questionMultiple) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsMultipleTable.COLUMN_QUESTION, questionMultiple.getQuestionM());
        cv.put(QuestionsMultipleTable.COLUMN_OPTION1, questionMultiple.getOption1());
        cv.put(QuestionsMultipleTable.COLUMN_OPTION2, questionMultiple.getOption2());
        cv.put(QuestionsMultipleTable.COLUMN_OPTION3, questionMultiple.getOption3());
        cv.put(QuestionsMultipleTable.COLUMN_OPTION4, questionMultiple.getOption4());
        cv.put(QuestionsMultipleTable.COLUMN_OPTION5, questionMultiple.getOption5());
        cv.put(QuestionsMultipleTable.COLUMN_KAPITEL, questionMultiple.getKapitel());
        cv.put(QuestionsMultipleTable.COLUMN_ANSWER_NR1, questionMultiple.getAnswerNr1());
        cv.put(QuestionsMultipleTable.COLUMN_ANSWER_NR2, questionMultiple.getAnswerNr2());
        cv.put(QuestionsMultipleTable.COLUMN_ANSWER_NR3, questionMultiple.getAnswerNr3());

        db.insert(QuestionsMultipleTable.TABLE_NAME2, null, cv);
    }

    public ArrayList<QuestionMultiple> getQuestions(String kapitelm) {
        ArrayList<QuestionMultiple> questionMultipleList = new ArrayList<>();
        db = getReadableDatabase();

         String[] selectionArgs = new String[] {kapitelm};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsMultipleTable.TABLE_NAME2 +
                " WHERE " + QuestionsMultipleTable.COLUMN_KAPITEL + " = ?", selectionArgs);
        if (c.moveToFirst()) {
            do {
                QuestionMultiple questionMultiple = new QuestionMultiple();
                questionMultiple.setQuestionM(c.getString(c.getColumnIndex(QuestionsMultipleTable.COLUMN_QUESTION)));
                questionMultiple.setOption1(c.getString(c.getColumnIndex(QuestionsMultipleTable.COLUMN_OPTION1)));
                questionMultiple.setOption2(c.getString(c.getColumnIndex(QuestionsMultipleTable.COLUMN_OPTION2)));
                questionMultiple.setOption3(c.getString(c.getColumnIndex(QuestionsMultipleTable.COLUMN_OPTION3)));
                questionMultiple.setOption4(c.getString(c.getColumnIndex(QuestionsMultipleTable.COLUMN_OPTION4)));
                questionMultiple.setOption5(c.getString(c.getColumnIndex(QuestionsMultipleTable.COLUMN_OPTION5)));
                questionMultiple.setKapitel(c.getString(c.getColumnIndex(QuestionsMultipleTable.COLUMN_KAPITEL)));
                questionMultiple.setAnswerNr1(c.getInt(c.getColumnIndex(QuestionsMultipleTable.COLUMN_ANSWER_NR1)));
                questionMultiple.setAnswerNr2(c.getInt(c.getColumnIndex(QuestionsMultipleTable.COLUMN_ANSWER_NR2)));
                questionMultiple.setAnswerNr3(c.getInt(c.getColumnIndex(QuestionsMultipleTable.COLUMN_ANSWER_NR3)));
                questionMultipleList.add(questionMultiple);


            } while (c.moveToNext());
        }
        c.close();
        return questionMultipleList;
    }

    //to upload the app if any change was made, need to change the Database_Version to upnext number too
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsMultipleTable.TABLE_NAME);
        onCreate(db);

    }


}
