package com.example.user.testproject11.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.testproject11.R;
import com.example.user.testproject11.model.Offer;
import com.example.user.testproject11.support.PicassoBigCache;

import java.util.List;


public class OfferPreviewAdapter extends RecyclerView.Adapter<OfferPreviewAdapter.ViewHolder> {

    private final Context mContext;
    private List<Offer> mOffers;
    private OnItemClickListener mOnItemClickListener;
    private int mOfferIconWidth;
    private int mOfferIconHeight;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public OfferPreviewAdapter(Context context, List<Offer> offers, OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mOffers = offers;
        this.mOnItemClickListener = onItemClickListener;
        this.mOfferIconWidth = (int) mContext.getResources().getDimension(R.dimen.article_icon_width);
        this.mOfferIconHeight = (int) mContext.getResources().getDimension(R.dimen.article_icon_height);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int positionOffer = getItemCount() - 1 - position;
        Offer o = mOffers.get(positionOffer);

        holder.mOfferName.setText(o.getName());
        holder.mOfferPrice.setText(o.getPrice());
        String weight = "";
        for (int j = 0; j < o.getParams().size(); j++) {
            if (o.getParams().get(j).getName().equals("Вес"))
                weight = o.getParams().get(j).getContent();
        }
        if (weight != null && !weight.equals("")) {
            holder.mOfferWeight.setText("Вес: ".concat(weight));
        }
        String pic = o.getPicture();
        if (pic != null) {
            PicassoBigCache.INSTANCE.getPicassoBigCache(mContext)
                    .load(pic)
                    .error(R.drawable.no_image)
                    .resize(mOfferIconWidth, mOfferIconHeight)
                    .centerCrop()
                    .into(holder.mOfferPicture);

        }
    }

    public void checkAdapterIsEmpty(TextView emptyView) {
        if (this.getItemCount() > 0) {
            emptyView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return mOffers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mOfferPicture;
        private TextView mOfferName;
        private TextView mOfferPrice;
        private TextView mOfferWeight;

        ViewHolder(View itemView) {
            super(itemView);
            mOfferPicture = (ImageView) itemView.findViewById(R.id.tvPicture);
            mOfferName = (TextView) itemView.findViewById(R.id.tvName);
            mOfferPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            mOfferWeight = (TextView) itemView.findViewById(R.id.tvWeight);
        }
    }
}
