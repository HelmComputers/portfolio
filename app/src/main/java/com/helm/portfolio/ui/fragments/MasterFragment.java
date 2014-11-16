package com.helm.portfolio.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.InjectView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.presenters.MasterFragmentPresenter;
import com.helm.portfolio.ui.views.MasterFragmentView;

import javax.inject.Inject;

public class MasterFragment extends BaseFragment implements MasterFragmentView {

    @InjectView(R.id.master_fragment_recycler)
    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Inject
    Context context;
    @Inject
    MasterFragmentPresenter masterFragmentPresenter;

    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        masterFragmentPresenter.setView(this);
        masterFragmentPresenter.initialize(recyclerView);


    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_master;
    }




}
