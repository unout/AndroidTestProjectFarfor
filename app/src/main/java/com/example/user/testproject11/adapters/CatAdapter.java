package com.example.user.testproject11.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.testproject11.R;
import com.example.user.testproject11.model.Category;
import com.example.user.testproject11.support.Constants;

import io.realm.RealmResults;

public class CatAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private RealmResults<Category> categories;

    public CatAdapter(Context context, RealmResults<Category> cats) {
        ctx = context;
        categories = cats;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.cat_item, parent, false);
        }

        Category c = getCategory(position);
        
        String name = c.getContent();
        ((TextView) view.findViewById(R.id.catItemName)).setText(name);

        String title = name.toLowerCase();
        title = Constants.translateTitle(title);
        if (title != null && !title.equals("")) {
            int drawableResourceId = ctx.getResources().getIdentifier(title, "drawable", ctx.getPackageName());
            ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(drawableResourceId);
        }
        return view;
    }

    private Category getCategory(int position) {
        return ((Category) getItem(position));
    }

}
