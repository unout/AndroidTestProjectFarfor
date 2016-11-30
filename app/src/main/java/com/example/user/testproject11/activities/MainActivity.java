package com.example.user.testproject11.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.fragments.CategoryListFragment;
import com.example.user.testproject11.support.Constants;

public class MainActivity extends AppCompatActivity implements Manager.OnUpdateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        ImageButton refresh = (ImageButton) findViewById(R.id.refresh);
//        refresh.setOnClickListener(this);

//        initNavView();

//        contactsLayout = (LinearLayout) findViewById(R.id.contacts_layout);
//        contactsLayout.setVisibility(View.VISIBLE);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CategoryListFragment())
                .commit();

    }

//    private void initNavView() {
//
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        Menu m = navigationView.getMenu();
//
//        // fillCat();
//        m.add(3, Constants.ITEMID1, Constants.ORDER1, R.string.map);
//        m.add(3, Constants.ITEMID2, Constants.ORDER2, R.string.contacts);
//
//        MenuItem mi = m.getItem(m.size() - 1);
//        mi.setTitle(mi.getTitle());
//
//        navigationView.setNavigationItemSelectedListener(this);
//    }

//    private void fillCat() {
//        Menu m = navigationView.getMenu();
//        mCat = Manager.getInstance().getCat();
//
//        if (mCat.size() != 0) {
//            for (int i = 0; i < mCat.size(); i++) {
//                m.add(0, i, 0, mCat.get(i).getContent());
//                MenuItem item = m.getItem(i);
//                String title = item.getTitle().toString().toLowerCase();
//                title = Constants.translateTitle(title);
//                if (title != null && !title.equals("")) {
//                    int drawableResourceId = getResources().getIdentifier(title, "drawable", getPackageName());
//                    Drawable drawable = ContextCompat.getDrawable(this, drawableResourceId);
//                    item.setIcon(drawable);
//                }
//            }
//        }
//    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        fillCat();
//        getMenuInflater().inflate(R.menu.cat_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        for (int i = 0; i < mCat.size(); i++) {
//            if (item.getTitle().toString().equals(mCat.get(i).getContent())) {
//                setAdapter(mCat.get(i).getId());
//            }
//        }
//
//        if (item.getTitle().toString().equals(getResources().getString(R.string.map))) {
//            startActivity(new Intent(this, MapsActivity.class));
//        } else if (item.getTitle().toString().equals(getResources().getString(R.string.contacts))) {
//            setAdapter(Constants.CONTACT);
//        }
//
//        return true;
//    }


//    private void setAdapter(int cat) {
//
//        ExpandableListView expLV = (ExpandableListView) findViewById(R.id.explv);
//
//        if (cat != Constants.CONTACT) {
//            final RealmResults<Offer> offers = Manager.getInstance().getOffers(cat);
//
//            if (offers.size() != 0) {
//                contactsLayout.setVisibility(View.GONE);
//                expLV.setVisibility(View.VISIBLE);
//                expLV.removeAllViewsInLayout();
//                BoxAdapter ba = new BoxAdapter(getApplicationContext(), offers);
//                expLV.setAdapter(ba);
//            } else {
//                Toast.makeText(this, R.string.conn_or_load, Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            expLV.setVisibility(View.INVISIBLE);
//            contactsLayout.setVisibility(View.VISIBLE);
//        }
//    }

//    @Override
//    public void onClick(View view) {
//        if (mCat.size() == 0) {
//            progressBar.setVisibility(View.VISIBLE);
////            new LoadTask(this, this).execute();
//        } else {
//            Toast.makeText(this, R.string.already, Toast.LENGTH_SHORT).show();
//        }
//    }


    @Override
    public void onUpdateFinished(int resultCode) {
        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        switch (resultCode) {
            case 1:
//                if (isOfferAdded) {
//
//                    fillCat();
//                OfferPreviewAdapter offerPreviewAdapter =
//                        new OfferPreviewAdapter(this, offers, mOnItemClickListener);
//                mRecyclerView.setAdapter(offerPreviewAdapter);
//                TextView emptyView = (TextView) findViewById(R.id.mEmptyView);

//                offerPreviewAdapter.checkAdapterIsEmpty(emptyView);
                Toast.makeText(this, "MainActivity.onUpdateFinished. resultCode = " + resultCode, Toast.LENGTH_LONG).show();
                Log.d(Constants.myLogs, "MainActivity.onUpdateFinished. resultCode = " + resultCode);
                mSwipeRefreshLayout.setRefreshing(false);
                break;
            case 2:
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getBaseContext(), R.string.conn_err, Toast.LENGTH_SHORT).show();
                break;

            case 3:
            default:
//                fillCat();
                Toast.makeText(this, R.string.yet, Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
                break;
        }
    }
}
