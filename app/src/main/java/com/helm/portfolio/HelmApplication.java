/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio;

import android.app.Application;
import com.helm.portfolio.dependencyinjection.RootModule;
import dagger.ObjectGraph;

public class HelmApplication extends Application{


    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
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
