package com.example.user.testproject11;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FarforApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder().build());
//        Manager.getInstance().calling(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        this.deleteDatabase(this.databaseList()[0]);
    }
}
