package com.helm.portfolio.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.models.Apps;
import com.helm.portfolio.ui.reciclerview.AppsAdapter;
import com.helm.portfolio.utils.AppsXmlParser;

public class MasterFragment extends Fragment {

    @InjectView(R.id.master_fragment_recycler)
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Context context;

    public MasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        View v = inflater.inflate(R.layout.fragment_master, container, false);
        ButterKnife.inject(this, v);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Apps apps = getApps();
        adapter = new AppsAdapter(apps.asList(), context);
        recyclerView.setAdapter(adapter);
        return v;
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
