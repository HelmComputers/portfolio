package com.helm.portfolio.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.InjectView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.fragments.MasterFragment;


public class MasterActivity extends BaseActivity {

    public static String MASTER_FRAGMENT_TAG = "MASTER_FRAGMENT";

    @InjectView(R.id.master_activity_master_container)
    FrameLayout frameLayout;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MasterFragment masterFragment = new MasterFragment();
        fragmentTransaction.replace(frameLayout.getId(), masterFragment, MASTER_FRAGMENT_TAG);
        fragmentTransaction.commit();

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
}
