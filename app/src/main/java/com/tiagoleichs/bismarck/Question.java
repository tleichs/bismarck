package com.tiagoleichs.bismarck;

import android.os.Parcel;
import android.os.Parcelable;

//informations for questions and answers
public class Question implements Parcelable{

    public static final String KAPITEL1 = "K1",
            KAPITEL2 = "K2",
            KAPITEL3 = "K3",
            KAPITEL4 = "K4",
            UEBUNGEN1_4 = "U1_4";

    private String question, option1, option2, option3, option4, kapitel;
    private int answerNr;


   //empty constructor, for database
    public Question() {

   }

   //constructor
    public Question(String question, String option1, String option2, String option3, String option4, int answerNr, String kapitel) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.kapitel = kapitel;
    }

    protected Question(Parcel in) {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        answerNr = in.readInt();
        kapitel = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeInt(answerNr);
        dest.writeString(kapitel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }


    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    public String getKapitel() {
        return kapitel;
    }

    public void setKapitel(String difficulty) {
        this.kapitel = kapitel;
    }

    public static String [] getAllKapiteln () {
        return new String[] {
                KAPITEL1,
                KAPITEL2,
                KAPITEL3,
                KAPITEL4,
                UEBUNGEN1_4
        };
    }
}
