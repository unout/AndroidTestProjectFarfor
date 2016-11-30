package com.example.user.testproject11.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.user.testproject11.Manager;
import com.example.user.testproject11.Service;
import com.example.user.testproject11.model.Catalog;
import com.example.user.testproject11.support.Constants;
import com.example.user.testproject11.support.NetworkUtils;

import java.io.IOException;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class LoadTask extends AsyncTask<Void, Void, Void> {

    private OnUpdateListener mListener = null;

    private Context mContext;

    public interface OnUpdateListener {
        void onUpdateFinished(int resultCode);
    }

    public LoadTask(OnUpdateListener listener, Context context) {
        this.mListener = listener;
        this.mContext = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... act) {
        if (mContext != null && !NetworkUtils.isOnline(mContext)) {
            setInitFinished(Constants.CODE_NETWORK_ERROR);
            return null;
        } else {

            Realm realm = Realm.getDefaultInstance();

            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();

            Service service = restAdapter.create(Service.class);
            Call<Catalog> call = service.getCatalog();
            try {
                Response<Catalog> catalogResponse = call.execute();
                Manager.getInstance().initDb(catalogResponse, realm);
                setInitFinished(Constants.CODE_SUCCESS);

            } catch (IOException e) {
                e.printStackTrace();
                setInitFinished(Constants.CODE_COMMON_ERROR);
            }
            return null;
        }
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
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