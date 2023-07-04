package com.lemonydbook.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Personon implements Parcelable {

    private String name;
    private int grade;

    public Personon(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    protected Personon(Parcel in) {
        this.name = in.readString();
        this.grade = in.readInt();
    }

    public static final Creator<Personon> CREATOR = new Creator<Personon>() {
        @Override
        public Personon createFromParcel(Parcel in) {
            return new Personon(in);
        }

        @Override
        public Personon[] newArray(int size) {
            return new Personon[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(grade);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
