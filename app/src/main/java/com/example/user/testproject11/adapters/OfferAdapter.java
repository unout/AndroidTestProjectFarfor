package com.example.user.testproject11.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.testproject11.R;
import com.example.user.testproject11.adapters.viewholders.OfferViewHolder;
import com.example.user.testproject11.model.Offer;

public class OfferAdapter extends RecyclerView.Adapter<OfferViewHolder> {

    private LayoutInflater lInflater;
    private Offer mOffer;
    private Context mContext;
    private RecyclerView mRecyclerView;


    public OfferAdapter(Context context, Offer o) {
        mOffer = o;
        mContext = context;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = lInflater.inflate(R.layout.cat_item, parent, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        holder.setHolder(mOffer, mContext);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}