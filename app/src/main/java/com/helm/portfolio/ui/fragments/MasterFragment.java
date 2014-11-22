package com.helm.portfolio.ui.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.InjectView;
import butterknife.OnClick;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.models.App;
import com.helm.portfolio.ui.presenters.MasterFragmentPresenter;
import com.helm.portfolio.ui.views.MasterFragmentView;

import javax.inject.Inject;

public class MasterFragment extends BaseFragment implements MasterFragmentView {


    @InjectView(R.id.master_fragment_recycler)
    RecyclerView recyclerView;

    @Inject
    MasterFragmentPresenter masterFragmentPresenter;

    public Callback callback;

    public MasterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof Callback){
            callback = ((Callback) activity);
        }
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


    @OnClick(R.id.fab) public void onClickFab() {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("message/rfc822");
        Uri uri = Uri.parse(getString(R.string.helm_mail));
        intent.setData(uri);

        startActivity(intent);
    }

    @Override
    public void onListItemClicked(int position, App app) {
        callback.onListItemClicked(position, app);
    }

    public App getDefaultApp() {
        return masterFragmentPresenter.getDefaultApp();
    }


    public interface Callback{

        public void onListItemClicked(int item, App app);

    }

}
