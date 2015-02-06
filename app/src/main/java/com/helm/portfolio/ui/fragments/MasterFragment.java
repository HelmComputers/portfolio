package com.helm.portfolio.ui.fragments;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.InjectView;
import butterknife.OnClick;
import com.helm.portfolio.R;
import com.helm.portfolio.ui.models.App;
import com.helm.portfolio.ui.models.Apps;
import com.helm.portfolio.ui.presenters.MasterFragmentPresenter;
import com.helm.portfolio.ui.reciclerview.AppsAdapter;
import com.helm.portfolio.ui.views.MasterFragmentView;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.widget.DividerItemDecoration;

import javax.inject.Inject;

public class MasterFragment extends BaseFragment implements MasterFragmentView {


    @InjectView(R.id.master_fragment_recycler)
    RecyclerView recyclerView;

    @Inject
    MasterFragmentPresenter masterFragmentPresenter;

    public Callback callback;

    public MasterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof Callback){
            callback = ((Callback) activity);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        masterFragmentPresenter.setView(this);
        masterFragmentPresenter.initialize();

    }

    @Override
    public void initializeRecyclerView(Apps apps) {

        recyclerView.setHasFixedSize(true);
        AppsAdapter adapter = new AppsAdapter(apps.asList(), getActivity());
        recyclerView.setAdapter(adapter);

     //   final ItemSelectionSupport itemSelectionSupport = ItemSelectionSupport.addTo(recyclerView);
       // itemSelectionSupport.setChoiceMode(ItemSelectionSupport.ChoiceMode.SINGLE);
      //  itemSelectionSupport.

        final Drawable divider = getActivity().getResources().getDrawable(R.drawable.list_divider);
        recyclerView.addItemDecoration(new DividerItemDecoration(divider));
        final ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerView);

        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, final View view, int i, long l) {
                masterFragmentPresenter.onListItemClicked(i);
            }
        });
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_master;
    }


    @OnClick(R.id.fab) public void onClickFab() {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setType("message/rfc822");
            Uri uri = Uri.parse(getString(R.string.helm_mail));
            intent.setData(uri);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Crouton.makeText(getActivity(),getString(R.string.app_not_found), Style.ALERT).show();
        }
    }

    @Override
    public void onListItemClicked(int position, App app) {
        callback.onListItemClicked(position, app);
    }

    public App getDefaultApp() {
        return masterFragmentPresenter.getDefaultApp();
    }


    public interface Callback{

        public void onListItemClicked(int item, App app);

    }

}
