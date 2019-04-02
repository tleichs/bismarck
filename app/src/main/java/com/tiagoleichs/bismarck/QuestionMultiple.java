package com.tiagoleichs.bismarck;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionMultiple implements Parcelable{

    public static final String KAPITEL1 = "K1",
            KAPITEL2 = "K2",
            KAPITEL3 = "K3",
            KAPITEL4 = "K4",
            UEBUNGEN1_4 = "U1_4";

    private String question, option1, option2, option3, option4, option5, kapitel;
    private int answerNr1, answerNr2, answerNr3;

    public QuestionMultiple() {
    }

    public QuestionMultiple(String question, String option1, String option2,
                            String option3, String option4, String option5, String kapitel, int answerNr1, int answerNr2, int answerNr3) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.kapitel = kapitel;
        this.answerNr1 = answerNr1;
        this.answerNr2 = answerNr2;
        this.answerNr3 = answerNr3;
    }

    protected QuestionMultiple(Parcel in) {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        option5 = in.readString();
        kapitel = in.readString();
        answerNr1 = in.readInt();
        answerNr2 = in.readInt();
        answerNr3 = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(option5);
        dest.writeString(kapitel);
        dest.writeInt(answerNr1);
        dest.writeInt(answerNr2);
        dest.writeInt(answerNr3);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionMultiple> CREATOR = new Creator<QuestionMultiple>() {
        @Override
        public QuestionMultiple createFromParcel(Parcel in) {
            return new QuestionMultiple(in);
        }

        @Override
        public QuestionMultiple[] newArray(int size) {
            return new QuestionMultiple[size];
        }
    };



    public String getQuestionM() {
        return question;
    }

    public void setQuestionM(String question) {
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

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getKapitel() {
        return kapitel;
    }

    public void setKapitel(String kapitel) {
        this.kapitel = kapitel;
    }

    public int getAnswerNr1() {
        return answerNr1;
    }

    public void setAnswerNr1(int answerNr1) {
        this.answerNr1 = answerNr1;
    }

    public int getAnswerNr2() {
        return answerNr2;
    }

    public void setAnswerNr2(int answerNr2) {
        this.answerNr2 = answerNr2;
    }

    public int getAnswerNr3() {
        return answerNr3;
    }

    public void setAnswerNr3(int answerNr3) {
        this.answerNr3 = answerNr3;
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
