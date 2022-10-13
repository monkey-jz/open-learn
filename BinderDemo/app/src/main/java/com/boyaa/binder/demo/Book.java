package com.boyaa.binder.demo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Auther: JerryZhu
 * @datetime: 2020/12/16
 */
public class Book implements Parcelable {

    private String mName;

    public Book(String name) {
        mName = name;
    }

    protected Book(Parcel in) {
        mName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
    }

    public void readFromParcel(Parcel dest) {
        mName = dest.readString();
    }
}
