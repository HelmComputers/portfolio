/*
Created by Helm  20/11/14.
*/


package com.helm.portfolio.ui.reciclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.helm.portfolio.R;

import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {


    private final Context context;
    private final List<Drawable> photos;

    public PhotosAdapter( Context context) {

        this.context = context;
        photos = new ArrayList<Drawable>();
        photos.add(context.getResources().getDrawable(R.drawable.microblog_android_1_portrait));
        photos.add(context.getResources().getDrawable(R.drawable.microblog_android_1_portrait));
        photos.add(context.getResources().getDrawable(R.drawable.microblog_android_3_portrait));
        photos.add(context.getResources().getDrawable(R.drawable.microblog_android_4_portrait));


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        final View view = LayoutInflater.from(context).inflate(R.layout.photos_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Drawable b = photos.get(i);
        viewHolder.image.setImageDrawable(b);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = ((ImageView) itemView.findViewById(R.id.photos));
        }
    }
}
