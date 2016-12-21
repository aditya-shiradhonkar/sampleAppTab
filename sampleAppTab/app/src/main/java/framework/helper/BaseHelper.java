
package framework.helper;

import framework.global.Logger;
import framework.model.IRetrofitResponseCallback;
import framework.network.RequestBody;
import framework.network.ResponseBody;
import framework.network.communication.NetworkCommunication;

/**
 * Created by aniruddhatr on 6/10/2016. Class is the base class for all the specific helpers
 */
public class BaseHelper {
    private static final String TAG = "BaseHelper";
    protected NetworkCommunication mNetworkCommunication;
    protected IRetrofitResponseCallback mIRetrofitResponseCallback;

    protected BaseHelper() {

    }

    public BaseHelper(IRetrofitResponseCallback iRetrofitResponseCallback) {
        mNetworkCommunication = NetworkCommunication.getInstance(iRetrofitResponseCallback);
        mNetworkCommunication.setIRequestCreator(iRetrofitResponseCallback.getIRequestCreator());
        mIRetrofitResponseCallback = iRetrofitResponseCallback;
    }

    public void sendRequest(RequestBody requestBody) {
        Logger.i(TAG, "sendRequest :" + requestBody);
        mNetworkCommunication.createRequest(requestBody);
    }

    public void onResponse(RequestBody requestBody, ResponseBody responseBody) {
        Logger.i(TAG, "onResponse :responseBody :" + responseBody);
        mIRetrofitResponseCallback.onResponse(requestBody, responseBody);
    }

    public void onFailure(RequestBody requestBody, ResponseBody responseBody) {
        Logger.i(TAG, "onFailure :responseBody :" + responseBody);
        mIRetrofitResponseCallback.onFailure(requestBody, responseBody);
    }
}
