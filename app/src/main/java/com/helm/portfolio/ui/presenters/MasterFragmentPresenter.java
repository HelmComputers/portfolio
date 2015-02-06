/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.presenters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.models.App;
import com.helm.portfolio.ui.models.Apps;
import com.helm.portfolio.ui.views.MasterFragmentView;
import com.helm.portfolio.utils.AppsXmlParser;

import javax.inject.Inject;

public class MasterFragmentPresenter {

    private final Context context;
    public  MasterFragmentView view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Apps apps;
    @Inject
    public MasterFragmentPresenter(Context context){
        this.context = context;
    }

    public void setView(MasterFragmentView view) {
        this.view = view;
    }

    public void initialize( ) {

        Apps apps = getApps();
        view.initializeRecyclerView(apps);
    }


    public void onListItemClicked(int position){
        view.onListItemClicked(position, apps.asList().get(position));
    };

    private Apps getApps() {
        apps = new Apps();
        try {
            apps  = AppsXmlParser.parse(context.getResources().openRawResource(R.raw.apps));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apps;

    }

    public App getDefaultApp() {
        return getApps().asList().get(1);
    }
}
