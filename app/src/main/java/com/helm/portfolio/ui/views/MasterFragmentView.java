/*
Created by Helm  16/11/14.
*/


package com.helm.portfolio.ui.views;

import com.helm.portfolio.ui.models.App;
import com.helm.portfolio.ui.models.Apps;

public interface MasterFragmentView {
    void initializeRecyclerView(Apps apps);

    void onListItemClicked(int position, App app);
}
