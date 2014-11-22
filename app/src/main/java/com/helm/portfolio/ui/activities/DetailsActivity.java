/*
Created by Helm  17/11/14.
*/


package com.helm.portfolio.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.fragments.BaseFragment;
import com.helm.portfolio.ui.fragments.DetailsFragment;
import com.helm.portfolio.ui.models.App;

public class DetailsActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE &&
                getResources().getBoolean(R.bool.isTablet)){
            finish();
            return;
        }
        if(savedInstanceState == null) {
            int position = getIntent().getExtras().getInt(DetailsFragment.DETAILS_POSITION);
            App app = ((App) getIntent().getExtras().getSerializable(DetailsFragment.DETAILS_APP));
            DetailsFragment detailsFragment = DetailsFragment.newInstance(position, app);
            setUpFragment(R.id.details_container, detailsFragment, MasterActivity.DETAILS_FRAGMENT_TAG);
        }
    }

    public void setUpFragment(int containerId, BaseFragment fragment, String tag ){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_details;
    }


}
