package com.helm.portfolio.ui.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.InjectView;
import com.helm.portfolio.R;


public class DetailsFragment extends BaseFragment {

    public static String DETAILS_POSITION = "DETAILS_POSITION";
    @InjectView(R.id.fragment_details_text)
    TextView fragmentText;

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
        fragmentText.setText(String.valueOf(getShownIndex()));
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_details;
    }


    public int getShownIndex() {
        return getArguments().getInt(DETAILS_POSITION);
    }


}
