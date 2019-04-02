package com.tiagoleichs.bismarck;

import android.provider.BaseColumns;

public final class uebung3Contract {
    public uebung3Contract() {
    }
    public static class Uebung3Table implements BaseColumns{
        public static final String
        TABLE_NAME = "uebung3",
        COLUMN_VERB = "verb",
        COLUMN_ICH = "ich",
                COLUMN_DU = "du",
                COLUMN_ER = "er",
                COLUMN_WIR = "wir",
                COLUMN_IHR = "ihr",
                COLUMN_SIE = "sie",
        COLUMN_ANSWER1 = "answer1",
                COLUMN_ANSWER2 = "answer2",
                COLUMN_ANSWER3 = "answer3",
        COLUMN_KAPITEL = "kapitel";



    }
}
