package com.helm.portfolio.ui.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.InjectView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.presenters.DetailsFragmentPresenter;
import com.helm.portfolio.ui.reciclerview.PhotosAdapter;
import com.helm.portfolio.ui.views.DetailsFragmentView;
import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.TwoWayView;

import javax.inject.Inject;


public class DetailsFragment extends BaseFragment implements DetailsFragmentView{

    public static String DETAILS_POSITION = "DETAILS_POSITION";
    @InjectView(R.id.details_fragment_rv)
    TwoWayView recyclerView;
    @Inject
    DetailsFragmentPresenter detailsFragmentPresenter;
    @Inject
    Context context;

    public static DetailsFragment newInstance(int position){
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(DETAILS_POSITION, position);
        detailsFragment.setArguments(arguments);
        return detailsFragment;
    }
    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Details Fragment:", String.valueOf(getShownIndex()));

        detailsFragmentPresenter.setView(this);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {

        recyclerView.setHasFixedSize(true);
        //ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerView);

        recyclerView.setAdapter(new PhotosAdapter(context));
        recyclerView.setOrientation(TwoWayLayoutManager.Orientation.HORIZONTAL);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_details;
    }


    public int getShownIndex() {
        return getArguments().getInt(DETAILS_POSITION);
    }


}
