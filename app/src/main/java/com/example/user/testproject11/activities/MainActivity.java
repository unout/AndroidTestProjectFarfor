package com.example.user.testproject11.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.R;
import com.example.user.testproject11.fragments.CategoryListFragment;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Manager.OnUpdateListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Manager.getInstance().setListener(this);
        Manager.getInstance().calling(this);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new CategoryListFragment())
                    .commit();
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() >= 0) {
            getFragmentManager().popBackStack();
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
