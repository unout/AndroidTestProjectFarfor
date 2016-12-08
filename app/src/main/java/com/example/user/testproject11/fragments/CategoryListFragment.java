package com.example.user.testproject11.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.adapters.CatAdapter;


public class CategoryListFragment extends Fragment implements Manager.OnUpdateListener {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;
    private ProgressBar progressBar;

    private final CatAdapter.OnItemClickListener mOnItemClickListener = new CatAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            OffersListFragment offersListFragment = OffersListFragment.newInstance(position); //(mOfferNumber - 1 - position);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, offersListFragment)
                    .addToBackStack(null)
                    .commit();
        }
    };

    public CategoryListFragment() {
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
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
        }
    }
    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Manager.getInstance().setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category_list, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.mRVCatList);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mEmptyView = (TextView) v.findViewById(R.id.mEmptyView);

        if (Manager.getInstance().getCategories().size() > 0) {

            CatAdapter catAdapter = new CatAdapter(mContext,
                    Manager.getInstance().getCategories(),
                    mOnItemClickListener);
            mRecyclerView.setAdapter(catAdapter);
        }
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onUpdateFinished(int resultCode) {
        switch (resultCode) {
            case 1:
                CatAdapter mCatAdapter = new CatAdapter(mContext,
                        Manager.getInstance().getCategories(),
                        mOnItemClickListener);
                mRecyclerView.setAdapter(mCatAdapter);
                break;
            case 2:
                String network_error = getString(R.string.conn_err);
                mEmptyView.setText(R.string.conn_err);
                mEmptyView.setVisibility(View.VISIBLE);
                Toast.makeText(mContext, network_error, Toast.LENGTH_LONG).show();
                break;
            case 3:
            default:
                break;
        }
        progressBar.setVisibility(View.GONE);
    }
}