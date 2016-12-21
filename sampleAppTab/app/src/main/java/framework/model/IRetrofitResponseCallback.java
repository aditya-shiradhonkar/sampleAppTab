
package framework.model;

import android.content.Context;

import framework.network.ICallbackCreator;
import framework.network.IRequestCreator;
import framework.network.RequestBody;
import framework.network.ResponseBody;
import framework.network.creator.IRequestBodyCreator;
import framework.network.creator.IResponseHandler;

/**
 * Created by aniruddhatr on 6/28/2016. All the retrofit responses should implement this interface
 */
public interface IRetrofitResponseCallback {
    void onResponse(RequestBody requestBody, ResponseBody responseBody);

    void onFailure(RequestBody requestBody, ResponseBody responseBody);

    Context getApplicationContext();

    IRequestBodyCreator getRequestBodyCreator();

    IResponseHandler getResponseHandler();

    IRequestCreator getIRequestCreator();

    ICallbackCreator getICallBackCreator();

    String getBaseUrl();
}
