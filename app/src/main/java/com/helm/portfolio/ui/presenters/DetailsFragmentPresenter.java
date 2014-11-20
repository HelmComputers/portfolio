/*
Created by Helm  20/11/14.
*/


package com.helm.portfolio.ui.presenters;

import com.helm.portfolio.ui.views.DetailsFragmentView;

import javax.inject.Inject;

public class DetailsFragmentPresenter {


    DetailsFragmentView view;

    @Inject
    public DetailsFragmentPresenter() {
    }


    public void setView(DetailsFragmentView view) {
        this.view = view;
    }



}
