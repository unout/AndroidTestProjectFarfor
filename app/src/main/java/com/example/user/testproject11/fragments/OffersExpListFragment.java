package com.example.user.testproject11.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.adapters.OffersExpListAdapter;
import com.example.user.testproject11.model.Offer;

import io.realm.RealmResults;


public class OffersExpListFragment extends Fragment {

    private static final String OFFER_POSITION = "position";
    private AppCompatActivity mAppCompatActivity;
//    private Offer mOffer;

    public OffersExpListFragment() {
    }

    public static OffersExpListFragment newInstance(int position) {
        OffersExpListFragment fragment = new OffersExpListFragment();
        Bundle args = new Bundle();
        args.putInt(OFFER_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mAppCompatActivity = (AppCompatActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must extends AppCompatActivity");
        }
    }

    /*
     * Deprecated on API 23
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                this.mAppCompatActivity = (AppCompatActivity) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must extends AppCompatActivity");
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int position = 0;
        if (getArguments() != null) {
            position = getArguments().getInt(OFFER_POSITION);
        }

        int numberOffers = Manager.getInstance().getOffers().size();
//            mOffer =
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_explist_offer, container, false);
        ExpandableListView expLV = (ExpandableListView) v.findViewById(R.id.explv);

        final RealmResults<Offer> offers = Manager.getInstance().getOffers();

        if (offers.size() != 0) {
            expLV.setVisibility(View.VISIBLE);
            expLV.removeAllViewsInLayout();
            OffersExpListAdapter ba = new OffersExpListAdapter(mAppCompatActivity, offers);
            expLV.setAdapter(ba);
        } else {
            Toast.makeText(mAppCompatActivity, R.string.conn_or_load, Toast.LENGTH_SHORT).show();
        }
//        PicassoBigCache.INSTANCE.getPicassoBigCache(mAppCompatActivity)
//                .load(mOffer.getPicture())
//                .error(R.drawable.no_image)
//                .into((ImageView) v.findViewById(R.id.tvPicture));
//
//        ((TextView) v.findViewById(R.id.tvName)).setText(mOffer.getName());
//        ((TextView) v.findViewById(R.id.tvPrice)).setText(mOffer.getPrice());
//        ((TextView) v.findViewById(tvDescription)).setText(mOffer.getDescription());
//        String weight = "";
//        for (int j = 0; j < mOffer.getParams().size(); j++) {
//            if (mOffer.getParams().get(j).getName().equals("Вес"))
//                weight = mOffer.getParams().get(j).getContent();
//        }
//        if (weight != null && !weight.equals("")) {
//            ((TextView) v.findViewById(R.id.tvWeight)).setText(weight);
//        }

//        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
//        mAppCompatActivity.setSupportActionBar(toolbar);
//        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setTitle(null);
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
//        }
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().onBackPressed();
//            }
//        });

//        toolbar.setTitle(Manager.getInstance().getCategories().g);

        return v;
    }
}
