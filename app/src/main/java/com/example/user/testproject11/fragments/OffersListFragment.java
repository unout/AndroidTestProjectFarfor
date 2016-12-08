package com.example.user.testproject11.fragments;

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

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.adapters.OffersListAdapter;
import com.example.user.testproject11.model.Offer;

import io.realm.RealmResults;

public class OffersListFragment extends Fragment {

    private static final String OFFER_POSITION = "position";
    private Context mContext;

    private final OffersListAdapter.OnItemClickListener mOnItemClickListener = new OffersListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            OfferFragment offerFragment = OfferFragment.newInstance(position);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, offerFragment)
                    .addToBackStack(null)
                    .commit();
        }
    };

    public OffersListFragment() {
    }

    public static OffersListFragment newInstance(int position) {
        OffersListFragment fragment = new OffersListFragment();
        Bundle args = new Bundle();
        args.putInt(OFFER_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

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
        int position = 0;
        if (getArguments() != null) {
            position = getArguments().getInt(OFFER_POSITION);
        }
        Manager.getInstance().setOffers(Manager.getInstance().getCategories().get(position).getId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_offer_list, container, false);

        final RealmResults<Offer> offers = Manager.getInstance().getOffers();

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.mRVOfferList);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        OffersListAdapter offersListAdapter = new OffersListAdapter(
                mContext,
                offers,
                mOnItemClickListener);
        mRecyclerView.setAdapter(offersListAdapter);

        return v;
    }
}
