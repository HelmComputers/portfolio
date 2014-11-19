package com.helm.portfolio.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.InjectView;
import butterknife.Optional;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.fragments.BaseFragment;
import com.helm.portfolio.ui.fragments.DetailsFragment;
import com.helm.portfolio.ui.fragments.MasterFragment;


public class MasterActivity extends BaseActivity implements MasterFragment.Callback{

    public static String MASTER_FRAGMENT_TAG = "MASTER_FRAGMENT";
    public static String DETAILS_FRAGMENT_TAG = "DETAILS_FRAGMENT";

    @InjectView(R.id.master_activity_master_container)
    FrameLayout frameLayoutMaster;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @Optional
    @InjectView(R.id.details_container)
    FrameLayout frameLayoutDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setUpMasterFragment();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_master;
    }


    private void setUpMasterFragment() {

        MasterFragment masterFragment = new MasterFragment();
        setUpFragment( frameLayoutMaster.getId(),masterFragment, MASTER_FRAGMENT_TAG);

        if (frameLayoutDetails != null) {
            DetailsFragment detailsFragment = DetailsFragment.newInstance(0);
            setUpFragment(frameLayoutDetails.getId(), detailsFragment, DETAILS_FRAGMENT_TAG);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_master, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void setUpFragment(int containerId, BaseFragment fragment, String tag ){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onListItemClicked(int item) {
        if (frameLayoutDetails == null) {
            Intent i = new Intent(this, DetailsActivity.class);
            i.putExtra(DetailsFragment.DETAILS_POSITION, item);
            startActivity(i);
        }else{
            DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().
                    findFragmentByTag(DETAILS_FRAGMENT_TAG);
            if (detailsFragment == null || detailsFragment.getShownIndex() != item) {
                DetailsFragment newDetailsFragment = DetailsFragment.newInstance(item);
                setUpFragment(frameLayoutDetails.getId(), newDetailsFragment, DETAILS_FRAGMENT_TAG);
            }
        }
    }
}
