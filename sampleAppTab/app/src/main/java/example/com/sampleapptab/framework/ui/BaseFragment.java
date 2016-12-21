
package example.com.sampleapptab.framework.ui;

import android.content.Context;
import android.content.SharedPreferences;

import example.com.sampleapptab.framework.global.Constants;
import example.com.sampleapptab.framework.model.IRetrofitResponseCallback;
import example.com.sampleapptab.framework.network.IRequestBodyCreator;
import example.com.sampleapptab.framework.network.IResponseHandler;
import example.com.sampleapptab.framework.network.creator.CallbackCreator;
import example.com.sampleapptab.framework.network.creator.RequestBodyCreator;
import example.com.sampleapptab.framework.network.creator.RequestCreator;
import example.com.sampleapptab.framework.network.creator.ResponseHandler;
import framework.global.Logger;
import framework.network.ICallbackCreator;
import framework.network.IRequestCreator;

/**
 * Created by aniruddhatr on 10/4/2016. Class represents the application specific BaseFragment which
 * will return required things
 */

public abstract class BaseFragment extends framework.ui.BaseFragment
        implements IRetrofitResponseCallback {
    private static final String TAG = "BaseFragment";

    public BaseFragment() {
        Logger.i(TAG, "Initialized application specific BaseFragment");
    }

    @Override
    public IRequestBodyCreator getRequestBodyCreator() {
        return new RequestBodyCreator();
    }

    @Override
    public IResponseHandler getResponseHandler() {
        return new ResponseHandler();
    }

    @Override
    public IRequestCreator getIRequestCreator() {
        return new RequestCreator();
    }

    @Override
    public ICallbackCreator getICallBackCreator() {
        return new CallbackCreator();
    }

    @Override
    public String getBaseUrl() {
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(Constants.BASE_URL_PREFERENCES_NAME, Context.MODE_APPEND);
        return sharedPreferences.getString(Constants.BACKEND_BASE_URL_PREFERENCE,
                Constants.BACKEND_BASE_URL);
    }

    public void notifyAdapters() {
        // empty
    }
}
