/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.presenters;

import com.helm.portfolio.ui.views.MasterFragmentView;

import javax.inject.Inject;

public class MasterFragmentPresenter {

    public MasterFragmentView view;

    @Inject
    public MasterFragmentPresenter(){

    }

    public void setView(MasterFragmentView view) {
        this.view = view;
    }
}
