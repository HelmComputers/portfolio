/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import butterknife.ButterKnife;
import com.helm.portfolio.HelmApplication;
import com.helm.portfolio.dependencyinjection.RootModule;
import dagger.ObjectGraph;

public abstract class BaseActivity extends ActionBarActivity{

    private ObjectGraph activityScopeGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        injectView();
    }

    public abstract int getActivityLayout();

    private void injectDependencies() {
        HelmApplication helmApplication = (HelmApplication) getApplication();
        activityScopeGraph = helmApplication.plus(new RootModule(this));
    }

    private void injectView() {
        ButterKnife.inject(this);
    }

    public void inject(Object object){

        activityScopeGraph.inject(object);

    }
}
