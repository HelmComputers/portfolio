/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.presenters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.models.Apps;
import com.helm.portfolio.ui.reciclerview.AppsAdapter;
import com.helm.portfolio.ui.views.MasterFragmentView;
import com.helm.portfolio.utils.AppsXmlParser;

import javax.inject.Inject;

public class MasterFragmentPresenter {

    private final Context context;
    public MasterFragmentView view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Inject
    public MasterFragmentPresenter(Context context){
        this.context = context;
    }

    public void setView(MasterFragmentView view) {
        this.view = view;
    }

    public void initialize( RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Apps apps = getApps();
        adapter = new AppsAdapter(apps.asList(), context);
        recyclerView.setAdapter(adapter);
    }




    private Apps getApps() {
        Apps apps = new Apps();
        try {
            apps  = AppsXmlParser.parse(context.getResources().openRawResource(R.raw.apps));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apps;

    }

}
