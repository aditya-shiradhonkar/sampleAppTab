
package framework.network;

/**
 * Created by aniruddhatr on 7/18/2016. Class represents the generic response body which will be
 * received from the server
 */
public class ResponseBody {
    private int mRequestType;
    private Response mResponse;
    private Throwable mThrowable;

    public int getRequestType() {
        return mRequestType;
    }

    public void setRequestType(int requestType) {
        this.mRequestType = requestType;
    }

    public Response getResponse() {
        return mResponse;
    }

    public void setResponse(Response response) {
        this.mResponse = response;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public void setThrowable(Throwable throwable) {
        this.mThrowable = throwable;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "mRequestType=" + mRequestType +
                ", mResponse=" + mResponse +
                ", mThrowable=" + mThrowable +
                '}';
    }
}
