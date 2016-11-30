package com.example.user.testproject11.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.testproject11.R;
import com.example.user.testproject11.model.Offer;
import com.example.user.testproject11.support.PicassoBigCache;

import io.realm.RealmResults;

import static com.example.user.testproject11.R.id.tvDescription;
import static com.example.user.testproject11.R.id.tvPrice;

public class BoxAdapter extends BaseExpandableListAdapter {

    private RealmResults<Offer> mOffers;
    private Context mContext;

    public BoxAdapter(Context context, RealmResults<Offer> offers) {
        mContext = context;
        mOffers = offers;
    }

    @Override
    public int getGroupCount() {
        return mOffers.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Offer getGroup(int groupPosition) {
        return mOffers.get(groupPosition);
    }

    @Override
    public Offer getChild(int groupPosition, int childPosition) {
        return mOffers.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mOffers.get(groupPosition).getCategoryId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mOffers.get(groupPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private View getViewInit(View convertView, ViewGroup parent, @LayoutRes int res) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater =
                    (LayoutInflater) mContext
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(res, parent, false);
        }
        return v;
    }

    private void groupViewInit(Offer o, View v) {

        ((TextView) v.findViewById(R.id.tvName)).setText(o.getName());
        ((TextView) v.findViewById(tvPrice))
                .setText("Цена: ".concat(o.getPrice()));

        String weight = "";
        for (int j = 0; j < o.getParams().size(); j++) {
            if (o.getParams().get(j).getName().equals("Вес"))
                weight = o.getParams().get(j).getContent();
        }
        if (weight != null) {
            ((TextView) v.findViewById(R.id.tvWeight)).setText("Вес: ".concat(weight));
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        View v = getViewInit(convertView, parent, R.layout.view_item);
        Offer o = getGroup(groupPosition);

        groupViewInit(o, v);

        if (o.getPicture() != null && !o.getPicture().equals("")) {
            ImageView tvPicture = (ImageView) v.findViewById(R.id.tvPicture);
            PicassoBigCache.INSTANCE.getPicassoBigCache(mContext)
                    .load(o.getPicture())
                    .error(R.drawable.no_image)
                    .resize(130, 110)
                    .into(tvPicture);
        }
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        View v = getViewInit(convertView, parent, R.layout.view_offer);
        Offer o = getChild(groupPosition, childPosition);

        groupViewInit(o, v);

        if (o.getPicture() != null && !o.getPicture().equals("")) {
            ImageView tvPicture = (ImageView) v.findViewById(R.id.tvPicture);
            PicassoBigCache.INSTANCE.getPicassoBigCache(mContext)
                    .load(o.getPicture())
                    .error(R.drawable.no_image)
                    .resize(380, 320)
                    .into(tvPicture);
        }

        ((TextView) v.findViewById(tvDescription))
                .setText(o.getDescription());

        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}