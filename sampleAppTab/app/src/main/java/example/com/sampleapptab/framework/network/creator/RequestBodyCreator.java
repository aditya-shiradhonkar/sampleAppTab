
package example.com.sampleapptab.framework.network.creator;

import example.com.sampleapptab.framework.global.Constants;
import framework.model.IRetrofitResponseCallback;
import framework.network.RequestBody;

/**
 * Created by aniruddhatr on 11/28/2016.
 */

public class RequestBodyCreator extends AbstractRequestBodyCreator {
    private static final String TAG = "RequestBodyCreator";

    public RequestBody createAuthenticationRequestBody(
            IRetrofitResponseCallback iRetrofitResponseCallback, String userName, String password) {

        RequestBody requestBody = new RequestBody(iRetrofitResponseCallback,
                Constants.AUTHENTICATION_DETAILS);
        // requestBody.setiRetrofitRequest(IAuthenticationRequests.class);

        return requestBody;
    }

}
