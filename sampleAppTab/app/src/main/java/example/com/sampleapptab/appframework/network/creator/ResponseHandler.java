
package example.com.sampleapptab.appframework.network.creator;

import framework.network.RequestBody;
import framework.network.Response;

/**
 * Created by aniruddhatr on 11/28/2016.
 */

public class ResponseHandler extends AbstractResponseHandler {
    private static final String TAG = "ResponseHandler";

    public void handleAuthenticationResponse(int status, RequestBody requestBody,
            Response retrofitResponse) {
        /*
         * AuthenticationResponseModel authenticationResponseModel = (AuthenticationResponseModel)
         * retrofitResponse .body();
         */
    }
}