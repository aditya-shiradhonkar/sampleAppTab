
package example.com.sampleapptab.appframework.ui;

import android.content.Context;
import android.content.SharedPreferences;

import example.com.sampleapptab.appframework.global.ConstantsApp;
import example.com.sampleapptab.appframework.model.IRetrofitResponseCallbackApp;
import example.com.sampleapptab.appframework.network.IRequestBodyCreatorApp;
import example.com.sampleapptab.appframework.network.IResponseHandlerApp;
import example.com.sampleapptab.appframework.network.creator.CallbackCreator;
import example.com.sampleapptab.appframework.network.creator.RequestBodyCreator;
import example.com.sampleapptab.appframework.network.creator.RequestCreator;
import example.com.sampleapptab.appframework.network.creator.ResponseHandler;
import framework.global.Logger;
import framework.network.ICallbackCreator;
import framework.network.IRequestCreator;

/**
 * Created by aniruddhatr on 10/4/2016. Class represents the application specific BaseFragment which
 * will return required things
 */

public abstract class BaseFragment extends framework.ui.BaseFragment
        implements IRetrofitResponseCallbackApp {
    private static final String TAG = "BaseFragment";

    public BaseFragment() {
        Logger.i(TAG, "Initialized application specific BaseFragment");
    }

    @Override
    public IRequestBodyCreatorApp getRequestBodyCreator() {
        return new RequestBodyCreator();
    }

    @Override
    public IResponseHandlerApp getResponseHandler() {
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
                .getSharedPreferences(ConstantsApp.BASE_URL_PREFERENCES_NAME, Context.MODE_APPEND);
        return sharedPreferences.getString(ConstantsApp.BACKEND_BASE_URL_PREFERENCE,
                ConstantsApp.BACKEND_BASE_URL);
    }

    public void notifyAdapters() {
        // empty
    }
}
