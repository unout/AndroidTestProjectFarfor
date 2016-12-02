package com.example.user.testproject11.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.user.testproject11.R;
import com.example.user.testproject11.fragments.CategoryListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new CategoryListFragment())
                    .commit();
        } else {
            Toast.makeText(this, "fragment_container = null !!!!!", Toast.LENGTH_LONG).show();
        }
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        ImageButton refresh = (ImageButton) findViewById(R.id.refresh);
//        refresh.setOnClickListener(this);

//        contactsLayout = (LinearLayout) findViewById(R.id.contacts_layout);
//        contactsLayout.setVisibility(View.VISIBLE);

    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
