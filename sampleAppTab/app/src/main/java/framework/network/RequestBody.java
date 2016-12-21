
package framework.network;

import java.util.Map;

import framework.helper.BaseHelper;
import framework.model.IRequestBody;
import framework.model.IRetrofitResponseCallback;

/**
 * Created by aniruddhatr on 7/6/2016. The class represents the generic request body to be sent to
 * the server
 */

public class RequestBody {
    private int mRequestType;
    private Class mIRetrofitRequest;
    private BaseHelper mCallback;
    private Map<String, String> mQueryParametersMap;
    private IRequestBody mIRequestBody;
    private Map<String, String> mPathParametersMap;
    private Map<String, String> mFieldMap;
    private Map<String, String> mHeaderMap;

    public RequestBody(IRetrofitResponseCallback iRetrofitResponseCallback, int requestType) {
        mRequestType = requestType;
        mCallback = iRetrofitResponseCallback.getICallBackCreator()
                .createCallback(iRetrofitResponseCallback, requestType);
    }

    public int getRequestType() {
        return mRequestType;
    }

    public Class getiRetrofitRequest() {
        return mIRetrofitRequest;
    }

    public void setiRetrofitRequest(Class iRetrofitRequest) {
        this.mIRetrofitRequest = iRetrofitRequest;
    }

    public BaseHelper getCallback() {
        return mCallback;
    }

    public Map<String, String> getQueryParameters() {
        return mQueryParametersMap;
    }

    public void setQueryParameters(Map<String, String> queryParameters) {
        this.mQueryParametersMap = queryParameters;
    }

    public IRequestBody getiRequestBody() {
        return mIRequestBody;
    }

    public void setiRequestBody(IRequestBody iRequestBody) {
        this.mIRequestBody = iRequestBody;
    }

    public Map<String, String> getPathParameters() {
        return mPathParametersMap;
    }

    public void setPathParameters(Map<String, String> pathParameters) {
        this.mPathParametersMap = pathParameters;
    }

    public Map<String, String> getFieldMap() {
        return mFieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.mFieldMap = fieldMap;
    }

    public Map<String, String> getHeaderMap() {
        return mHeaderMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.mHeaderMap = headerMap;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "mRequestType=" + mRequestType +
                ", mIRetrofitRequest=" + mIRetrofitRequest +
                ", mCallback=" + mCallback +
                ", mQueryParametersMap=" + mQueryParametersMap +
                ", mIRequestBody=" + mIRequestBody +
                ", mPathParametersMap=" + mPathParametersMap +
                ", mFieldMap=" + mFieldMap +
                '}';
    }
}
