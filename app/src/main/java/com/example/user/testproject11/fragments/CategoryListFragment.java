package com.example.user.testproject11.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.adapters.CatAdapter;
import com.example.user.testproject11.model.Category;
import com.example.user.testproject11.support.Constants;

import io.realm.RealmResults;

public class CategoryListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Manager.OnUpdateListener {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;
    private CatAdapter mCatAdapter;
    private RealmResults<Category> mCats;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public CategoryListFragment() {
    }

    private final CatAdapter.OnItemClickListener mOnItemClickListener = new CatAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            OffersExpListFragment offersExpListFragment = OffersExpListFragment.newInstance(position); //(mOfferNumber - 1 - position);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, offersExpListFragment)
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

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
            this.mContext = activity.getApplicationContext();
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Manager.getInstance().setListener(this);
        Manager.getInstance().calling(mContext);
        Toast.makeText(mContext, "onCreate. mContext = " + mContext, Toast.LENGTH_LONG).show();
        Log.d(Constants.myLogs, "onCreate. mContext = " + mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category_list, container, false);
        ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBarInCatList);
        progressBar.setVisibility(View.VISIBLE);

//        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
//        mToolbar.setTitle(getString(R.string.categories));

        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRecyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mCatAdapter = new CatAdapter(mContext, mCats, mOnItemClickListener);
        mRecyclerView.setAdapter(mCatAdapter);

        mEmptyView = (TextView) v.findViewById(R.id.mEmptyView);

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
        switch (resultCode) {
            case 1:
                if (Manager.getInstance().getCategories().size() != 0 && this.getView() != null) {

                    mCatAdapter = new CatAdapter(mContext,
                            Manager.getInstance().getCategories(),
                            mOnItemClickListener);
                    mRecyclerView.setAdapter(mCatAdapter);
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