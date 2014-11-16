/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.reciclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.models.App;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {


    private final List<App> apps;
    private final Context cotext;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView status;
        public ImageView icon;
        public TextView os;
        public ViewHolder(CardView itemView) {
            super(itemView);

           title = ((TextView) itemView.findViewById(R.id.app_title));
           status = ((TextView) itemView.findViewById(R.id.app_status)) ;
           icon = ((ImageView) itemView.findViewById(R.id.app_icon));
           os = ((TextView) itemView.findViewById(R.id.app_os));
        }
    }

    public AppsAdapter(List<App> apps, Context cotext){
        this.apps = apps;
        this.cotext = cotext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.master_fragment_rv_item, null);

        return new ViewHolder(((CardView) v));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        App app = apps.get(i);
        String title = app.getTitle();
        String status = app.getStatus();
        String os = app.getOs();
        Drawable icon = app.getIcon(cotext);

        viewHolder.title.setText(title);
        viewHolder.status.setText(status);
        viewHolder.icon.setImageDrawable(icon);
        viewHolder.os.setText(os);
    }




    @Override
    public int getItemCount() {
        return apps.size();
    }
}
