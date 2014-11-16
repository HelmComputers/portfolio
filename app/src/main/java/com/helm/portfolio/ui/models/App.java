/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.models;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class App {

    String title;
    String icon;
    String status;
    String os
            ;
    public App(String title, String icon, String status, String os) {
        this.title = title;
        this.icon = icon;
        this.status = status;
        this.os = os;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon(Context context) {
        Resources resources =  context.getResources();
        final int resourceId = resources.getIdentifier(icon,"drawable", context.getPackageName());
        return resources.getDrawable(resourceId);

    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}