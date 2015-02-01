package com.helm.portfolio.ui.fragments;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.models.App;
import com.helm.portfolio.ui.presenters.DetailsFragmentPresenter;
import com.helm.portfolio.ui.reciclerview.PhotosAdapter;
import com.helm.portfolio.ui.views.DetailsFragmentView;
import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.ItemSelectionSupport;
import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.TwoWayView;

import javax.inject.Inject;
import java.util.List;


public class  DetailsFragment extends BaseFragment implements DetailsFragmentView{

    public static String DETAILS_POSITION = "DETAILS_POSITION";
    public static String DETAILS_APP = "DETAILS_APP";

    @InjectView(R.id.details_fragment_rv)
    TwoWayView recyclerView;
    @Inject
    DetailsFragmentPresenter detailsFragmentPresenter;
    @Inject
    Context context;


    @InjectView(R.id.app_title)
    TextView title;
    @InjectView(R.id.app_status)
    TextView status;
    @InjectView(R.id.fragment_details_description)
    TextView description;

    public static DetailsFragment newInstance(int position, App app){
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(DETAILS_POSITION, position);
        arguments.putSerializable(DETAILS_APP, app);
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
        App app = getShownApp();
        setupApp(app);
    }

    private void setupApp(App app) {
        status.setText(app.getOs()+" "+app.getStatus());
        title.setText(app.getTitle());
        description.setText(app.getDescription());
        initializeRecyclerView(app.getPhotos(context));

    }

    private void initializeRecyclerView(List<Drawable> photos) {

        recyclerView.setHasFixedSize(true);
        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerView);

        recyclerView.setAdapter(new PhotosAdapter(photos, context));
        recyclerView.setOrientation(TwoWayLayoutManager.Orientation.HORIZONTAL);
        final ItemSelectionSupport itemSelection = ItemSelectionSupport.addTo(recyclerView);
        itemSelection.setChoiceMode(ItemSelectionSupport.ChoiceMode.MULTIPLE);
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View view, int i, long l) {

                itemSelection.setItemChecked(i, true);
                Log.e("Click", ""+i);

            }
        });
        itemSelection.setItemChecked(2, true);

    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_details;
    }


    public int getShownIndex() {
        return getArguments().getInt(DETAILS_POSITION);
    }

    public App getShownApp(){
        return ((App) getArguments().getSerializable(DETAILS_APP));
    }


}
