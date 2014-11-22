/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.dependencyinjection;

import android.content.Context;
import com.helm.portfolio.HelmApplication;
import com.helm.portfolio.ui.fragments.DetailsFragment;
import com.helm.portfolio.ui.fragments.MasterFragment;
import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                HelmApplication.class,
                MasterFragment.class,
                DetailsFragment.class
        },
        library = true
)
public class RootModule {

    private final Context context;

    public RootModule(Context context){
        this.context = context;
    }

    @Provides
    Context provideApplicationContext() {
        return context;
    }

}
