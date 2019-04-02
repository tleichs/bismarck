package com.tiagoleichs.bismarck;

import android.os.Parcel;
import android.os.Parcelable;

public class Uebung3 implements Parcelable{

    public static final String KAPITEL1 = "K1",
            KAPITEL2 = "K2",
            KAPITEL3 = "K3",
            KAPITEL4 = "K4",
            UEBUNGEN1_4 = "U1_4";


    private String verb, ich, du, er, wir, ihr, sie, answer1, answer2, answer3, kapitel;

    public Uebung3(String verb, String ich, String du, String er, String wir, String ihr, String sie, String answer1, String answer2, String answer3, String kapitel) {

        this.verb = verb;
        this.ich = ich;
        this.du = du;
        this.er = er;
        this.wir = wir;
        this.ihr = ihr;
        this.sie = sie;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.kapitel = kapitel;
    }

    public Uebung3() {
    }


    protected Uebung3(Parcel in) {
        verb = in.readString();
        ich = in.readString();
        du = in.readString();
        er = in.readString();
        wir = in.readString();
        ihr = in.readString();
        sie = in.readString();
        answer1 = in.readString();
        answer2 = in.readString();
        answer3 = in.readString();
        kapitel = in.readString();
    }

    public static final Creator<Uebung3> CREATOR = new Creator<Uebung3>() {
        @Override
        public Uebung3 createFromParcel(Parcel in) {
            return new Uebung3(in);
        }

        @Override
        public Uebung3[] newArray(int size) {
            return new Uebung3[size];
        }
    };

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getIch() {
        return ich;
    }

    public void setIch(String ich) {
        this.ich = ich;
    }

    public String getDu() {
        return du;
    }

    public void setDu(String du) {
        this.du = du;
    }

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }

    public String getWir() {
        return wir;
    }

    public void setWir(String wir) {
        this.wir = wir;
    }

    public String getIhr() {
        return ihr;
    }

    public void setIhr(String ihr) {
        this.ihr = ihr;
    }

    public String getSie() {
        return sie;
    }

    public void setSie(String sie) {
        this.sie = sie;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getKapitel() {
        return kapitel;
    }

    public void setKapitel(String kapitel) {
        this.kapitel = kapitel;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(verb);
        dest.writeString(ich);
        dest.writeString(du);
        dest.writeString(er);
        dest.writeString(wir);
        dest.writeString(ihr);
        dest.writeString(sie);
        dest.writeString(answer1);
        dest.writeString(answer2);
        dest.writeString(answer3);
        dest.writeString(kapitel);
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
