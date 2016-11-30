package com.example.user.testproject11;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.user.testproject11.model.Catalog;
import com.example.user.testproject11.model.Category;
import com.example.user.testproject11.model.Offer;
import com.example.user.testproject11.support.Constants;
import com.example.user.testproject11.support.NetworkUtils;
import com.example.user.testproject11.tasks.LoadTask;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Manager {

    private static volatile Manager instance;
    private LoadTask.OnUpdateListener mListener = null;
    private RealmResults<Offer> mOffers;
    private int mCat;
    private RealmResults<Category> mCategories;

    private Manager() {
    }

    public static Manager getInstance() {
        Manager man = instance;
        if (man == null) {
            synchronized (Manager.class) {
                man = instance;
                if (man == null) {
                    instance = man = new Manager();
                }
            }
        }
        return man;
    }

    public interface OnUpdateListener {
        void onUpdateFinished(int resultCode);
    }

    public void calling(final Context context) {

        if (context != null && !NetworkUtils.isOnline(context)){
            setInitFinished(Constants.CODE_NETWORK_ERROR);
        } else {

            final Realm realm = Realm.getDefaultInstance();

            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();

            Service service = restAdapter.create(Service.class);
            Call<Catalog> call = service.getCatalog();
            call.enqueue(new Callback<Catalog>() {
                @Override
                public void onResponse(Call<Catalog> call, Response<Catalog> response) {
                    Manager.getInstance().initDb(response, realm);
                    setInitFinished(Constants.CODE_SUCCESS);
                    Toast.makeText(context, "call.enqueue...onResponse. context = " + context,
                            Toast.LENGTH_LONG).show();
                    Log.d(Constants.myLogs, "call.enqueue...onResponse. context = " + context);
                    setInitFinished(Constants.CODE_SUCCESS);
                }

                @Override
                public void onFailure(Call<Catalog> call, Throwable t) {
                    Toast.makeText(context, "call.enqueue...onFailure. context = " + context,
                            Toast.LENGTH_LONG).show();
                    Log.d(Constants.myLogs, "call.enqueue...onFailure. context = " + context);
                    setInitFinished(Constants.CODE_COMMON_ERROR);
                }
            });
        }
    }

    public RealmResults<Offer> getOffers() {
        return mOffers;
    }
    public void setOffers(int cat) {
        mOffers = Realm.getDefaultInstance()
                .where(Offer.class)
                .equalTo("categoryId", cat)
                .findAll();
    }
    public RealmResults<Category> getCategories() {
        return mCategories;
    }

    public void setCategories() {
        mCategories = Realm.getDefaultInstance().where(Category.class).findAll();
    }

    public void initDb(Response<Catalog> catalogResponse, Realm realm) {
        try {

            realm.beginTransaction();
            realm.deleteAll();
            realm.insert(catalogResponse.body().getShop());
            realm.commitTransaction();

        } finally {
            realm.close();
        }
    }

    private void setInitFinished(final int resultCode) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {

            @Override
            public void run() {
                if (mListener != null) {
                    mListener.onUpdateFinished(resultCode);
                }
            }
        });
    }
}