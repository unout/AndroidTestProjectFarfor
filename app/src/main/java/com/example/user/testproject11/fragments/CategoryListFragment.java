package com.example.user.testproject11.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.adapters.CatAdapter;
import com.example.user.testproject11.adapters.OfferPreviewAdapter;
import com.example.user.testproject11.model.Category;
import com.example.user.testproject11.support.Constants;

import io.realm.RealmResults;

public class CategoryListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Manager.OnUpdateListener {

    private Context mContext;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public CategoryListFragment() {
    }

    private final OfferPreviewAdapter.OnItemClickListener mOnItemClickListener = new OfferPreviewAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {
            OfferExpListFragment offerExpListFragment = OfferExpListFragment.newInstance(position); //(mOfferNumber - 1 - position);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, offerExpListFragment)
                    .addToBackStack(null)
                    .commit();
        }
    };

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
        this.mContext = context;
    }

    /**
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /**
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Manager.getInstance().calling(mContext);
        Toast.makeText(mContext, "onCreate. mContext = " + mContext, Toast.LENGTH_LONG).show();
        Log.d(Constants.myLogs, "onCreate. mContext = " + mContext);
    }

    private void fillCat(Menu m) {
        RealmResults<Category> categories = Manager.getInstance().getCategories();

        if (categories != null && categories.size() != 0) {
            for (int i = 0; i < categories.size(); i++) {
                m.add(0, i, 0, categories.get(i).getContent());
                MenuItem item = m.getItem(i);
                String title = item.getTitle().toString().toLowerCase();
                title = Constants.translateTitle(title);
                if (title != null && !title.equals("")) {
                    int drawableResourceId = getResources().getIdentifier(title, "drawable", mContext.getPackageName());
                    Drawable drawable = ContextCompat.getDrawable(mContext, drawableResourceId);
                    item.setIcon(drawable);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category_list, container, false);
        ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBarInCatList);
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(mContext, "onCreatreView. mContext = " + mContext, Toast.LENGTH_LONG).show();
        Log.d(Constants.myLogs, "onCreatreView. mContext = " + mContext);

//        fillCat(m);
//        m.add(3, Constants.ITEMID1, Constants.ORDER1, R.string.map);
//        m.add(3, Constants.ITEMID2, Constants.ORDER2, R.string.contacts);

//        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
//        mToolbar.setTitle(getString(R.string.categories));
//
//        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerView);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

//        mOfferPreviewAdapter = new OfferPreviewAdapter(mContext, mOffers, mOnItemClickListener);
//        mRecyclerView.setAdapter(mOfferPreviewAdapter);

//        mEmptyView = (TextView) v.findViewById(R.id.mEmptyView);

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.destroyDrawingCache();
            mSwipeRefreshLayout.clearAnimation();
        }
        super.onPause();
    }

    @Override
    public void onRefresh() {
        Manager.getInstance().calling(mContext);
    }

    @Override
    public void onUpdateFinished(int resultCode) {
        Toast.makeText(mContext, "onUpdateFinished. resultCode = " + resultCode, Toast.LENGTH_LONG).show();
        Log.d(Constants.myLogs, "onUpdateFinished. resultCode = " + resultCode);
        Toast.makeText(mContext, "onUpdateFinished. resultCode = " + resultCode, Toast.LENGTH_LONG).show();
        Log.d(Constants.myLogs, "onUpdateFinished. resultCode = " + resultCode);
        Toast.makeText(mContext, "onUpdateFinished. resultCode = " + resultCode, Toast.LENGTH_LONG).show();
        Log.d(Constants.myLogs, "onUpdateFinished. resultCode = " + resultCode);
        Toast.makeText(mContext, "onUpdateFinished. resultCode = " + resultCode, Toast.LENGTH_LONG).show();
        Log.d(Constants.myLogs, "onUpdateFinished. resultCode = " + resultCode);
        Toast.makeText(mContext, "onUpdateFinished. resultCode = " + resultCode, Toast.LENGTH_LONG).show();
        Log.d(Constants.myLogs, "onUpdateFinished. resultCode = " + resultCode);
        switch (resultCode) {
            case 1:
                if (Manager.getInstance().getOffers().size() != 0 && this.getView() != null) {

                    ListView catList = (ListView) this.getView().findViewById(R.id.catList);

                    CatAdapter adapter = new CatAdapter(mContext,
                            Manager.getInstance().getCategories());

                    catList.setAdapter(adapter);
                }
                mSwipeRefreshLayout.setRefreshing(false);
                break;
            case 2:
                String network_error = getString(R.string.conn_err);
                Toast.makeText(mContext, network_error, Toast.LENGTH_LONG).show();
                mSwipeRefreshLayout.setRefreshing(false);
                break;
            case 3:
            default:
                mSwipeRefreshLayout.setRefreshing(false);
                break;
        }
    }
}