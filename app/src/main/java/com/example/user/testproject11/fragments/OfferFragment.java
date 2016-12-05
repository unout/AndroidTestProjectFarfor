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

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.adapters.OfferAdapter;
import com.example.user.testproject11.model.Offer;


public class OfferFragment extends Fragment {

    private static final String OFFER_POSITION = "position";
    private AppCompatActivity mAppCompatActivity;
    private Offer mOffer;
    private Context mContext;

    public OfferFragment() {
    }

    public static OfferFragment newInstance(int position) {
        OfferFragment fragment = new OfferFragment();
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
        mOffer = Manager.getInstance().getOffers().get(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OfferAdapter mCatAdapter = new OfferAdapter(mContext, mOffer);
        return inflater.inflate(R.layout.view_offer, container, false);
    }
}
