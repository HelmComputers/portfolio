/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Apps implements Parcelable {

    private final List<App> apps;

    public Apps(){
        apps = new ArrayList<App>();
    }


    public void addApp( App app ){
        apps.add(app);
    }


    public List<App> asList(){
        return apps;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.apps);
    }

    public Apps (final Parcel in){
        apps = new ArrayList<App>();
        in.readList(this.apps, App.class.getClassLoader());
    }

    public static Creator<Apps> CREATOR = new Creator<Apps>() {
        @Override
        public Apps createFromParcel(Parcel source) {
            return new Apps(source);
        }

        @Override
        public Apps[] newArray(int size) {
            return new Apps[size];
        }
    };
}
