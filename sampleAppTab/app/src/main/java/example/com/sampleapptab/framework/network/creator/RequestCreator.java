
package example.com.sampleapptab.framework.network.creator;

import framework.global.Logger;
import framework.network.IRequestCreator;
import framework.network.RequestBody;
import retrofit2.Retrofit;

/**
 * Created by aniruddhatr on 10/4/2016. This class helps in creating the requests
 */

public class RequestCreator implements IRequestCreator {
    private static final String TAG = "RequestCreator";

    public RequestCreator() {

    }

    /**
     * Method creates the request by checking the request type
     *
     * @param retrofit Retrofit on which request to be thrown
     * @param requestBody Which contains all the information about request to be thrown
     */
    @Override
    public void createRequest(Retrofit retrofit, RequestBody requestBody) {
        Logger.i(TAG, "Inside createRequest :" + requestBody);
        switch (requestBody.getRequestType()) {

            /*
             * case Constants.AUTHENTICATION_DETAILS: { IAuthenticationRequests absDayEndReport =
             * (IAuthenticationRequests) retrofit.create(requestBody.getiRetrofitRequest());
             * Call<AuthenticationResponseModel> call =
             * absDayEndReport.sendAuthenticationDetails(requestBody.getQueryParameters().get(
             * "client_id"), requestBody.getQueryParameters().get("client_secret"),
             * requestBody.getQueryParameters().get("grant_type"),
             * requestBody.getQueryParameters().get("username"),
             * requestBody.getQueryParameters().get("password")); call.enqueue(new
             * GenericCallBack<AuthenticationResponseModel>(requestBody)); } break;
             */

            default:
                break;
        }
    }
}
