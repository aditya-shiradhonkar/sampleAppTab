
package example.com.sampleapptab.appframework.network.creator;

import example.com.sampleapptab.appframework.global.ConstantsApp;
import example.com.sampleapptab.login.model.AuthenticationResponseModel;
import example.com.sampleapptab.login.model.IAuthenticationRequests;
import framework.global.Logger;
import framework.network.IRequestCreator;
import framework.network.RequestBody;
import framework.network.communication.GenericCallBack;
import retrofit2.Call;
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

            case ConstantsApp.AUTHENTICATION_DETAILS: {
                IAuthenticationRequests absDayEndReport = (IAuthenticationRequests) retrofit
                        .create(requestBody.getiRetrofitRequest());
                Call<AuthenticationResponseModel> call = absDayEndReport
                        .sendAuthenticationDetails(requestBody.getFieldMap().get("grant_type"),
                                requestBody.getFieldMap().get("mac"));
                call.enqueue(new GenericCallBack<AuthenticationResponseModel>(requestBody));
            }
                break;

            default:
                break;
        }
    }
}
