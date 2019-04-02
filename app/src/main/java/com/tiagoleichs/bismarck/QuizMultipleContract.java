package com.tiagoleichs.bismarck;

import android.provider.BaseColumns;

public class QuizMultipleContract {
    // to not creat an object from the class by accident
    private QuizMultipleContract() {}
    //baseColumns, constants for ID and count
    public static class QuestionsMultipleTable implements BaseColumns {
        //public to access out of the class, static to use without needing an instance of the QuestionsTable
        public static final String
                TABLE_NAME = "quiz_questions",
                TABLE_NAME2 = "quiz_questions2",
                COLUMN_QUESTION = "question",
                COLUMN_OPTION1 = "option1",
                COLUMN_OPTION2 = "option2",
                COLUMN_OPTION3 = "option3",
                COLUMN_OPTION4 = "option4",
                COLUMN_OPTION5 = "option5",
                COLUMN_ANSWER_NR1 = "answer_nr1",
                COLUMN_ANSWER_NR2 = "answer_nr2",
                COLUMN_ANSWER_NR3 = "answer_nr3",
                COLUMN_KAPITEL = "kapitel";

    }
}
