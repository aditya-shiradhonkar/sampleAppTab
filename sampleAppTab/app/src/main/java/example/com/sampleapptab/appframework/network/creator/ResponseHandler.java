
package example.com.sampleapptab.appframework.network.creator;

import example.com.sampleapptab.app.SampleAppTabApplication;
import example.com.sampleapptab.login.model.AuthenticationResponseModel;
import framework.global.Logger;
import framework.network.RequestBody;
import framework.network.Response;

/**
 * Created by aniruddhatr on 11/28/2016.
 */

public class ResponseHandler extends AbstractResponseHandler {
    private static final String TAG = "ResponseHandler";

    public void handleAuthenticationResponse(int status, RequestBody requestBody,
            Response retrofitResponse) {
        AuthenticationResponseModel authenticationResponseModel = (AuthenticationResponseModel) retrofitResponse
                .body();

        Logger.i(TAG, "handleAuthenticationResponse : " + authenticationResponseModel);
        SampleAppTabApplication.setAccessToken(authenticationResponseModel.getAccessToken());
    }
}
