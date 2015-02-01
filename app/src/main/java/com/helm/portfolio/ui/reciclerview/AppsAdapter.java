/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.reciclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.models.App;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {


    private final List<App> apps;
    private Context context;
    private SparseBooleanArray selectedItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView status;
        public ImageView icon;
        public ViewHolder(RelativeLayout itemView) {
            super(itemView);

           title = ((TextView) itemView.findViewById(R.id.app_title));
           status = ((TextView) itemView.findViewById(R.id.app_status)) ;
           icon = ((ImageView) itemView.findViewById(R.id.app_icon));
        }


    }

    public AppsAdapter(List<App> apps, Context context){
        this.apps = apps;
        this.context = context;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.master_fragment_rv_item, null);
        return new ViewHolder(((RelativeLayout) v));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        App app = apps.get(i);
        String title = app.getTitle();
        String status = app.getStatus();
        Drawable icon = app.getIcon(context);

        viewHolder.title.setText(title);
        viewHolder.status.setText(status);
        viewHolder.icon.setImageDrawable(icon);
        viewHolder.itemView.setActivated(selectedItems.get(i,false));
    }




    @Override
    public int getItemCount() {
        return apps.size();
    }
}
