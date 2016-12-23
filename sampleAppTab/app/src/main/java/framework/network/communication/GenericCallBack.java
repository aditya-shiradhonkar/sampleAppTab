
package framework.network.communication;

import java.util.ArrayList;

import framework.global.Logger;
import framework.model.IResponseBody;
import framework.network.RequestBody;
import framework.network.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenericCallBack<T> implements Callback<T> {

    private static final String TAG = "GenericCallBack";
    private final RequestBody mRequestBody;

    public GenericCallBack(RequestBody requestBody) {
        mRequestBody = requestBody;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Logger.i(TAG, "onResponse");
        ResponseBody responseBody = new ResponseBody();
        responseBody.setRequestType(mRequestBody.getRequestType());
        IResponseBody iResponseBody;
        if (response.body() instanceof ArrayList) {
            ResponseArrayList responseArrayList = new ResponseArrayList<>();
            responseArrayList.addAll((ArrayList) response.body());
            iResponseBody = responseArrayList;
        } else {
            iResponseBody = (IResponseBody) response.body();
        }
        framework.network.Response networkResponse = new framework.network.Response(response.raw(),
                iResponseBody, response.errorBody(), response.code());
        responseBody.setResponse(networkResponse);
        mRequestBody.getCallback().onResponse(mRequestBody, responseBody);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Logger.i(TAG, "onFailure :" + t);
        ResponseBody responseBody = new ResponseBody();
        responseBody.setRequestType(mRequestBody.getRequestType());
        responseBody.setThrowable(t);
        mRequestBody.getCallback().onFailure(mRequestBody, responseBody);
    }
}
