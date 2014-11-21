/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.models;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class App implements Serializable {

    private final List<String> photos;
    private final String description;
    String title;
    String icon;
    String status;
    String os;

    public App(String title, String icon, String status,String os, List<String> photos,String description) {
        this.title = title;
        this.icon = icon;
        this.status = status;
        this.os = os;
        this.photos = photos;
        this.description = description;
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

    public List<Drawable> getPhotos(Context context){
        Resources resources  = context.getResources();
        List<Drawable> imageDrawable = new ArrayList<Drawable>();
        for (String photo : photos) {
            final int resourceId = resources.getIdentifier(photo, "drawable", context.getPackageName());
            imageDrawable.add(resources.getDrawable(resourceId));
        }
        return imageDrawable;
    }

    public String getDescription() {
        return description;
    }
}
