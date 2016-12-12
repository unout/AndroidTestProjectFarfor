package com.example.user.testproject11.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.fragments.CategoryListFragment;
import com.example.user.testproject11.fragments.MyMapFragment;
import com.example.user.testproject11.support.OverviewSwipeRefreshLayout;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Manager.OnUpdateListener {

    private OverviewSwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Manager.getInstance().setListener(this);
        Manager.getInstance().calling(this);

        mSwipeRefreshLayout = (OverviewSwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);

        ImageButton mapButton = (ImageButton) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, MyMapFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new CategoryListFragment())
                    .commit();
        }

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() >= 0) {
            getFragmentManager().popBackStack();
            mSwipeRefreshLayout.setOnRefreshListener(this);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRefresh() {
        Manager.getInstance().calling(this);
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
    public void onUpdateFinished(int resultCode) {
        mSwipeRefreshLayout.setRefreshing(false);
        switch (resultCode) {
            case 1:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CategoryListFragment())
                        .commit();
                break;
            case 2:
                String network_error = getString(R.string.conn_err);
                Toast.makeText(this, network_error, Toast.LENGTH_LONG).show();
                break;
            case 3:
            default:
                break;
        }
    }
}
