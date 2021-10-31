package com.example.bmicalculator;

import android.os.Parcel;
import android.os.Parcelable;

public class BMIinfo implements Parcelable {
    private String name, gender;
    private int height, weight,age;

    public BMIinfo(String name, String gender, int height, int weight, int age) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    protected BMIinfo(Parcel in) {
        name = in.readString();
        gender = in.readString();
        height = in.readInt();
        weight = in.readInt();
        age = in.readInt();
    }

    public static final Creator<BMIinfo> CREATOR = new Creator<BMIinfo>() {
        @Override
        public BMIinfo createFromParcel(Parcel in) {
            return new BMIinfo(in);
        }

        @Override
        public BMIinfo[] newArray(int size) {
            return new BMIinfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeInt(height);
        dest.writeInt(weight);
        dest.writeInt(age);
    }
}
