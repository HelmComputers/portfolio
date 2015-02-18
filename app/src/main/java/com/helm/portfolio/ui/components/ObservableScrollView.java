/*
Created by Helm  06/02/2015.
*/


package com.helm.portfolio.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
    private OnScrollListener listener;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public interface OnScrollListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    public void setOnScrollViewListener(OnScrollListener listener){
        this.listener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.e("ObservableScrollView", "onScrollChanged " + t);
        listener.onScrollChanged(l,t,oldl,oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
