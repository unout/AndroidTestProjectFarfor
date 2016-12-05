package com.example.user.testproject11.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.testproject11.R;
import com.example.user.testproject11.adapters.viewholders.OfferViewHolder;
import com.example.user.testproject11.model.Offer;
import com.example.user.testproject11.support.Constants;

import io.realm.RealmResults;

public class OffersListAdapter extends RecyclerView.Adapter<OfferViewHolder> {
    private Context mContext;
    private LayoutInflater lInflater;
    private RealmResults<Offer> offersCat;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public OffersListAdapter(Context context, RealmResults<Offer> offersCat, OffersListAdapter.OnItemClickListener onItemClickListener) {
        this.offersCat = offersCat;
        mContext = context;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mOnItemClickListener = onItemClickListener;
        Log.d(Constants.myLogs, "    OffersListAdapter    ");
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = lInflater.inflate(R.layout.view_item, parent, false);
        final OfferViewHolder viewHolder = new OfferViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });

        Log.d(Constants.myLogs, "    onCreateViewHolder    ");
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        holder.setHolder(offersCat.get(position), mContext);
        Log.d(Constants.myLogs, "    onBindViewHolder    ");
    }

    @Override
    public int getItemCount() {
        return offersCat.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}