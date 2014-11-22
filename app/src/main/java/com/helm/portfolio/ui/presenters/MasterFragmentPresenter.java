/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.presenters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.models.App;
import com.helm.portfolio.ui.models.Apps;
import com.helm.portfolio.ui.reciclerview.AppsAdapter;
import com.helm.portfolio.ui.views.MasterFragmentView;
import com.helm.portfolio.utils.AppsXmlParser;
import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.ItemSelectionSupport;

import javax.inject.Inject;

public class MasterFragmentPresenter {

    private final Context context;
    public  MasterFragmentView view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Apps apps;
    ItemSelectionSupport itemSelectionSupport;
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
        Apps apps = getApps();
        adapter = new AppsAdapter(apps.asList(), context);
        recyclerView.setAdapter(adapter);
        itemSelectionSupport = ItemSelectionSupport.addTo(recyclerView);
        itemSelectionSupport.setChoiceMode(ItemSelectionSupport.ChoiceMode.SINGLE);
        final ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerView);
        itemClickSupport.setOnItemClickListener( new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View view, int i, long l) {
                itemSelectionSupport.setItemChecked(i, true);
                onListItemClicked(i);
            }
        });
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
