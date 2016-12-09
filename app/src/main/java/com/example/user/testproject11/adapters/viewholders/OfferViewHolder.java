package com.example.user.testproject11.adapters.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.testproject11.R;
import com.example.user.testproject11.model.Offer;
import com.example.user.testproject11.support.PicassoBigCache;

public class OfferViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder {

    private ImageView mOfferIcon;
    private TextView mOfferName;
    private TextView mOfferPrice;
    private TextView mOfferWeight;
    private TextView mOfferDescription;

    public OfferViewHolder(View itemView) {
        super(itemView);
        mOfferIcon = (ImageView) itemView.findViewById(R.id.tvPicture);
        mOfferName = (TextView) itemView.findViewById(R.id.tvName);
        mOfferPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        mOfferWeight = (TextView) itemView.findViewById(R.id.tvWeight);
        mOfferDescription = (TextView) itemView.findViewById(R.id.tvDescription);
    }

    public void setHolder(Offer o, Context context) {

        if (o.getPicture() != null) {
            PicassoBigCache.INSTANCE.getPicassoBigCache(context)
                    .load(o.getPicture())
                    .error(R.drawable.no_image)
                    .resize(240, 160)
                    .centerCrop()
                    .into(mOfferIcon);

        }
        mOfferName.setText(o.getName());
        mOfferPrice.setText(o.getPrice());
        if (mOfferDescription != null) {
            mOfferDescription.setText(o.getDescription());
        }
        if (o.getParams() != null && o.getParams().size() != 0) {
            for (int i = 0; i < o.getParams().size(); i++) {
                if (o.getParams().get(i).getName().equals("Вес")) {
                    mOfferWeight.setText(o.getParams().get(i).getContent());
                }
            }
        }
    }
}