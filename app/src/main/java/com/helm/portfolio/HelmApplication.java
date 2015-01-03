/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.helm.portfolio.dependencyinjection.RootModule;
import dagger.ObjectGraph;
import io.fabric.sdk.android.Fabric;

public class HelmApplication extends Application{


    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        initializeDependencyInjector();
    }

    private void initializeDependencyInjector() {
        objectGraph = ObjectGraph.create(new RootModule(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }

    public ObjectGraph plus(Object injection){
        return objectGraph.plus(injection);
    }

}
