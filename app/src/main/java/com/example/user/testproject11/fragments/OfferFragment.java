package com.example.user.testproject11.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.model.Offer;
import com.example.user.testproject11.support.PicassoBigCache;

public class OfferFragment extends Fragment {

    private static final String OFFER_POSITION = "position";
    private AppCompatActivity mAppCompatActivity;

    public OfferFragment() {
    }

    public static OfferFragment newInstance(int position) {
        OfferFragment fragment = new OfferFragment();
        Bundle args = new Bundle();
        args.putInt(OFFER_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position = 0;
        if (getArguments() != null) {
            position = getArguments().getInt(OFFER_POSITION);
        }
        
        Offer o = Manager.getInstance().getOffers().get(position);
        View v = inflater.inflate(R.layout.view_offer, container, false);
        ((TextView) v.findViewById(R.id.tvName)).setText(o.getName());
        ((TextView) v.findViewById(R.id.tvPrice)).setText(o.getPrice());
        if (o.getPicture() != null) {
            PicassoBigCache.INSTANCE.getPicassoBigCache(mAppCompatActivity)
                    .load(o.getPicture())
                    .error(R.drawable.no_image)
                    .resize(400, 320)
                    .centerCrop()
                    .into(((ImageView) v.findViewById(R.id.tvPicture)));
        }
        if (o.getDescription() != null) {
            ((TextView) v.findViewById(R.id.tvDescription)).setText(o.getDescription());
        }
        if (o.getParams() != null && o.getParams().size() != 0) {
            for (int i = 0; i < o.getParams().size(); i++) {
                if (o.getParams().get(i).getName().equals("Вес")) {
                    ((TextView) v.findViewById(R.id.tvWeight)).setText(o.getParams().get(i).getContent());
                }
            }
        }
        return v;
    }
}
