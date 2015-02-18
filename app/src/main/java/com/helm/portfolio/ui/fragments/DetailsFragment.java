package com.helm.portfolio.ui.fragments;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.components.ObservableScrollView;
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


public class DetailsFragment extends BaseFragment implements DetailsFragmentView, ObservableScrollView.OnScrollListener {

    public static String DETAILS_POSITION = "DETAILS_POSITION";
    public static String DETAILS_APP = "DETAILS_APP";
    @InjectView(R.id.scrollView)
    ObservableScrollView scrollView;
    @InjectView(R.id.header_session) View headerBox;
    @InjectView(R.id.details_container)View detailsContainer;
    @InjectView(R.id.details_fragment_rv)
    TwoWayView recyclerView;
    @InjectView(R.id.session_photo_container) View photoViewContainer;
    @InjectView(R.id.session_photo)
    ImageView photoView;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
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
    private int defaultToolbarHeight = 0;
    private int photoHeightPixels;
    private int maxHeaderElevation;
    private int headerHeightPixels;
    private boolean hasPhoto;
    private float PHOTO_ASPECT_RATIO =1.7777777f;
    public static final String TRANSITION_NAME_PHOTO = "photo";


    public static DetailsFragment newInstance(int position, App app) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(DETAILS_POSITION, position);
        arguments.putSerializable(DETAILS_APP, app);
        detailsFragment.setArguments(arguments);
        return detailsFragment;
    }

    public DetailsFragment() {
        // Required empty public constructo
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
        maxHeaderElevation = getResources().getDimensionPixelSize(
                R.dimen.max_header_elevation);
        scrollView.setOnScrollViewListener(this);
        ViewTreeObserver vto = scrollView.getViewTreeObserver();
        if (vto.isAlive()) {
            vto.addOnGlobalLayoutListener(mGlobalLayoutListener);
        }
        ((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);
   //     toolbar.setNavigationIcon(R.drawable.ic_up );


                hasPhoto = true;
        photoView.setImageDrawable(getActivity().getDrawable(R.drawable.microblog_android_2_portrait));
        recomputePhotoAndScrollingMetrics();

        ViewCompat.setTransitionName(photoView, TRANSITION_NAME_PHOTO);
    }

    private void setupApp(App app) {
        status.setText(app.getOs() + " " + app.getStatus());
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
                Log.e("Click", "" + i);

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

    public App getShownApp() {
        return ((App) getArguments().getSerializable(DETAILS_APP));
    }


    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {

        if(defaultToolbarHeight == 0){
            int[] x = new int[2];
            toolbar.getLocationOnScreen(x);
            defaultToolbarHeight = x[1]+toolbar.getHeight();
        }

        int[] y = new int[2];

        int scrollY = scrollView.getScrollY();
        headerBox.getLocationOnScreen(y);




        if(defaultToolbarHeight>= y[1] && headerBox.getVisibility()  == View.VISIBLE){
            Log.d("DetailsFragment", "visible");
            headerBox.setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = toolbar.getLayoutParams();
            params.height = (y[1]+headerBox.getHeight())+defaultToolbarHeight;
            toolbar.setLayoutParams(params );
            toolbar.setBackgroundColor(getActivity().getResources().getColor(R.color.material_blue_500));
        }

        if(defaultToolbarHeight <= +y[1] && headerBox.getVisibility()  == View.INVISIBLE){
            headerBox.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = toolbar.getLayoutParams();
            params.height = 112;
            toolbar.setLayoutParams(params );
            toolbar.setBackgroundColor(getActivity().getResources().getColor(android.R.color.transparent));
        }
        float newTop = Math.max(photoHeightPixels, scrollY);
        headerBox.setTranslationY(newTop);
        float gapFillProgress = 1;
        if(photoHeightPixels != 0){
            if (photoHeightPixels != 0) {
                gapFillProgress = Math.min(Math.max(getProgress(scrollY,
                        0,
                        photoHeightPixels), 0), 1);
            }
        }

        ViewCompat.setElevation(headerBox, gapFillProgress * maxHeaderElevation);


        // Move background photo (parallax effect)
         photoViewContainer.setTranslationY(scrollY * 0.5f);
    }

    private float getProgress(int value, int min, int max) {
        return (value - min) / (float) (max - min);
    }


    private ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener
            = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            recomputePhotoAndScrollingMetrics();
        }
    };

    private void recomputePhotoAndScrollingMetrics() {
        headerHeightPixels = headerBox.getHeight();

        photoHeightPixels = 0;
       if (hasPhoto) {
            photoHeightPixels = (int) (photoView.getWidth() / PHOTO_ASPECT_RATIO);
            photoHeightPixels = Math.min(photoHeightPixels, scrollView.getHeight() * 2 / 3);
        }


        ViewGroup.LayoutParams lp;
        lp = photoViewContainer.getLayoutParams();
        if (lp.height != photoHeightPixels) {
            lp.height = photoHeightPixels;
            photoViewContainer.setLayoutParams(lp);
        }


        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams)
                detailsContainer.getLayoutParams();
        if (mlp.topMargin != headerHeightPixels + photoHeightPixels) {
            mlp.topMargin = headerHeightPixels + photoHeightPixels;
            detailsContainer.setLayoutParams(mlp);
        }

        onScrollChanged(0, 0, 0, 0); // trigger scroll handling
    }
}
