package com.example.user.testproject11.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.testproject11.R;
import com.example.user.testproject11.model.Category;
import com.example.user.testproject11.support.Constants;

import io.realm.RealmResults;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {
    private Context ctx;
    private LayoutInflater lInflater;
    private RealmResults<Category> categories;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public CatAdapter(Context context, RealmResults<Category> cats, OnItemClickListener onItemClickListener) {
        ctx = context;
        categories = cats;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = lInflater.inflate(R.layout.cat_item, parent, false);
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

        int offerPosition = getItemCount() - 1 - position;

        Category c = categories.get(offerPosition);
        String name = c.getContent();
        holder.mCatName.setText(name);

        String title = name.toLowerCase();
        title = Constants.translateTitle(title);
        if (title != null && !title.equals("")) {
            holder.mCatIcon
                    .setImageResource(ctx.getResources()
                            .getIdentifier(title, "drawable", ctx.getPackageName()));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mCatIcon;
        private TextView mCatName;

        ViewHolder(View itemView) {
            super(itemView);
            mCatIcon = (ImageView) itemView.findViewById(R.id.catIcon);
            mCatName = (TextView) itemView.findViewById(R.id.catItemName);
        }
    }
}
